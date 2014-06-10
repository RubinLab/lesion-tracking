package edu.stanford.isis.epad.plugin.lesiontracking.server;

import java.awt.BasicStroke;
import java.awt.Color;
import java.awt.Paint;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.io.OutputStream;
import java.text.DecimalFormat;
import java.util.ArrayList;
import java.util.List;

import javax.imageio.ImageIO;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.jfree.chart.ChartFactory;
import org.jfree.chart.ChartUtilities;
import org.jfree.chart.JFreeChart;
import org.jfree.chart.axis.AxisLocation;
import org.jfree.chart.axis.NumberAxis;
import org.jfree.chart.plot.CategoryPlot;
import org.jfree.chart.plot.PlotOrientation;
import org.jfree.chart.renderer.category.LineAndShapeRenderer;
import org.jfree.data.category.DefaultCategoryDataset;

import com.google.gwt.i18n.client.NumberFormat;

import edu.stanford.isis.epad.plugin.lesiontracking.client.recist.CalculationResult;
import edu.stanford.isis.epad.plugin.lesiontracking.client.recist.ImageAnnotationUtility;
import edu.stanford.isis.epad.plugin.lesiontracking.client.recist.RECISTCalculator;
import edu.stanford.isis.epad.plugin.lesiontracking.shared.ImageAnnotation;
import edu.stanford.isis.epad.plugin.lesiontracking.shared.SharedNumberFormat;

public class RECISTChartServlet extends HttpServlet
{
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException
    {
        String patientUID = req.getParameter("patient_uid");
        if(patientUID == null)
        {
            resp.getOutputStream().print("Must specify a patient_uid parameter");
            resp.getOutputStream().close();
            return;
        }

        String metric = req.getParameter("metric");
        if(metric == null)
        {
            resp.getOutputStream().print("Must specify a metric parameter");
            resp.getOutputStream().close();
            return;
        }

        List<String> selectedMetrics = new ArrayList<String>();
        selectedMetrics.add(metric);

        List<ImageAnnotation> imageAnnotations;
		try {
			imageAnnotations = new LesionTrackingServiceImpl().getImageAnnotationsForPatient(patientUID);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			return;
		}
		
        ImageAnnotation[][] imageAnnotationsByStudyDate = RECISTCalculator.loadAndSortAIMFilesByStudyDate(imageAnnotations);
        CalculationResult cr = RECISTCalculator.calculateRECIST(imageAnnotationsByStudyDate, selectedMetrics.toArray(new String[selectedMetrics.size()]), new SharedNumberFormat(){
			@Override
			public String format(double d) {
				return new DecimalFormat().format(d);
			}});

        resp.setContentType("image/jpeg");
        File file = drawSeriesGraphToFile(cr, getServletContext().getRealPath("/") + "/images/recist_chart_" + (int)Math.ceil(Math.random() * 10000) + ".jpg");
        BufferedImage bi = ImageIO.read(file);
        OutputStream out = resp.getOutputStream();
        ImageIO.write(bi, "jpg", out);
        out.close();
    }

    public static File drawSeriesGraphToFile(CalculationResult calculationResult, String filename)
    {
        File file = new File(filename);

        String[] metricSums = calculationResult.getMetricSums()[0];
        double[] metricSumsDouble = new double[metricSums.length];
        for(int i = 0; i < metricSums.length; i++)
            metricSumsDouble[i] = Double.parseDouble(metricSums[i].replaceAll(",", "").trim());

        DefaultCategoryDataset defaultCategoryDataset = new DefaultCategoryDataset();

        String metric = calculationResult.getMetrics()[0];
        for( int i = 0; i< metricSumsDouble.length; i++ )
            defaultCategoryDataset.addValue(metricSumsDouble[i], "Sum of " + metric + "s", calculationResult.getStudyDates()[0][i]);
        JFreeChart lineChart = ChartFactory.createBarChart("Sum of " + metric + "s vs. Study Date", "Study Date", "Sum of " + metric + "s (" + calculationResult.getMetricUnits()[0] + ")", defaultCategoryDataset, PlotOrientation.VERTICAL, true, true, false);

        lineChart.setBackgroundPaint(new Color(0xF0, 0xF0, 0xF0));

        final CategoryPlot categoryPlot = lineChart.getCategoryPlot();
        categoryPlot.setDomainAxisLocation(AxisLocation.BOTTOM_OR_LEFT);
        categoryPlot.setRangeAxisLocation(AxisLocation.TOP_OR_LEFT);

        Paint[] colors = new Paint[] {new Color(164,0,29)};

        LineAndShapeRenderer customRenderer = new LineAndShapeRenderer()
        {
            private static final long	serialVersionUID	= 7084157025739340431L;
            private Paint[] colors;

            public LineAndShapeRenderer setColors(final Paint[] colors)
            {
                this.colors = colors;
                return this;
            }

            public Paint getItemPaint(final int row, final int column)
            {
                return this.colors[column % this.colors.length];
            }
        }.setColors(colors);

        customRenderer.setSeriesStroke(0, new BasicStroke(3.0f));
        categoryPlot.setRenderer(customRenderer);

        ((NumberAxis)categoryPlot.getRangeAxis()).setAutoRangeIncludesZero(false);
        ((NumberAxis)categoryPlot.getRangeAxis()).setNumberFormatOverride(new DecimalFormat());

        try
        {
            lineChart.setAntiAlias(true);
            ChartUtilities.saveChartAsJPEG(file, 1.0f, lineChart, 512, 384);
            return file;
        }
        catch(IOException e)
        {
            e.printStackTrace();
        }

        return null;
    }
}
