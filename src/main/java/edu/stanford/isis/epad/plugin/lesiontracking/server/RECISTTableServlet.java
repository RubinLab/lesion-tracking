package edu.stanford.isis.epad.plugin.lesiontracking.server;

import java.awt.Color;
import java.awt.Graphics2D;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.io.OutputStream;
import java.text.DecimalFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Map;

import javax.imageio.ImageIO;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import edu.stanford.isis.epad.plugin.lesiontracking.client.recist.Lesion;
import edu.stanford.isis.epad.plugin.lesiontracking.shared.ImageAnnotation;
import edu.stanford.isis.epad.plugin.lesiontracking.shared.SharedNumberFormat;

public class RECISTTableServlet extends HttpServlet {
	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp)
			throws ServletException, IOException {
		String patientID = req.getParameter("patient_uid");
		String projectID = req.getParameter("projectID");
		String username = req.getParameter("username");
		String session = req.getParameter("session");
		String server = req.getParameter("server");

		if (patientID == null) {
			resp.getOutputStream()
					.print("Must specify a patient_uid parameter");
			resp.getOutputStream().close();
			return;
		}

		String metric = req.getParameter("metric");
		if (metric == null) {
			resp.getOutputStream().print("Must specify a metric parameter");
			resp.getOutputStream().close();
			return;
		}

		List<String> selectedMetrics = new ArrayList<String>();
		selectedMetrics.add(metric);

		Map<Date, List<ImageAnnotation>> imageAnnotations;
		try {
			imageAnnotations = new TrackingServiceImpl()
					.getImageAnnotationsForPatient(projectID, patientID,
							username, session, server);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			return;
		}

		//ImageAnnotation[][] imageAnnotationsByStudyDate = RECISTCalculator
		//		.loadAndSortAIMFilesByStudyDate(imageAnnotations, server);
		//CalculationResult cr = null;
		/*RECISTCalculator.calculateRECIST(
				imageAnnotationsByStudyDate,
				selectedMetrics.toArray(new String[selectedMetrics.size()]),
				new SharedNumberFormat() {
					@Override
					public String format(double d) {
						return new DecimalFormat().format(d);
					}
				});
		resp.setContentType("image/jpeg");
		File file = downloadRECISTTableImage(cr, getServletContext()
				.getRealPath("/"));
		BufferedImage bi = ImageIO.read(file);
		OutputStream out = resp.getOutputStream();
		ImageIO.write(bi, "jpg", out);
		out.close();
	*/
	}

	/*
	public static File downloadRECISTTableImage(CalculationResult cr,
			String realPath) {
		String[] metrics = cr.getMetrics();

		int rowSize = 20;
		int colSize = 200;
		int titleColumnOffset = 75;
		String[] metricUnits = cr.getMetricUnits();

		BufferedImage image = new BufferedImage(colSize
				* (cr.getNumberOfTimePointsInStudy() + 2), rowSize
				* metrics.length * (8 + cr.getLesions().length),
				BufferedImage.TYPE_3BYTE_BGR);
		Graphics2D g = image.createGraphics();
		g.setColor(Color.WHITE);
		g.fillRect(0, 0, image.getWidth(), image.getHeight());
		g.setColor(Color.BLACK);

		int currentRow = 1;

		for (int i = 0; i < metrics.length; i++) {
			g.drawString(metrics[i], 0, currentRow * rowSize);

			currentRow++;

			g.drawString("Lesion ID", titleColumnOffset + colSize, currentRow
					* rowSize);
			g.drawString("Baseline", titleColumnOffset + 2 * colSize,
					currentRow * rowSize);
			g.drawString("Follow Up 1", titleColumnOffset + 3 * colSize,
					currentRow * rowSize);
			g.drawString("Follow Up 2", titleColumnOffset + 4 * colSize,
					currentRow * rowSize);

			currentRow++;

			Lesion[] lesions = cr.getLesions();

			for (int j = 0; j < lesions.length; j++) {
				Lesion lesion = lesions[j];
				g.drawString(lesion.getLesionID(), titleColumnOffset + colSize,
						currentRow * rowSize);

				List<String> metricValues = lesion
						.getMeasurementsForMetric(metrics[i]);
				for (int k = 0; k < metricValues.size(); k++) {
					String measurement = metricValues.get(k);
					g.drawString(measurement, titleColumnOffset + (k + 2)
							* colSize, currentRow * rowSize);
				}

				currentRow++;
			}

			currentRow++;
			g.drawString(metrics[i] + " (" + metricUnits[i] + ")", 0, rowSize
					* currentRow);
			for (int j = 0; j < cr.getNumberOfTimePointsInStudy(); j++) {
				String value = cr.getMetricSums()[i][j];
				if (value == null || "N/A".equals(value))
					value = "";
				g.drawString(value, titleColumnOffset + colSize + j * colSize,
						rowSize * currentRow);
			}

			currentRow++;
			g.drawString("Change from Baseline (%)", 0, rowSize * currentRow);
			for (int j = 0; j < cr.getNumberOfTimePointsInStudy(); j++) {
				String value = cr.getResponseRatesSinceBaseline()[i][j];
				if (value == null || "N/A".equals(value))
					value = "";
				g.drawString(value, titleColumnOffset + colSize + j * colSize,
						rowSize * currentRow);
			}

			currentRow++;
			g.drawString("Change from Nadir (%)", 0, rowSize * currentRow);
			for (int j = 0; j < cr.getNumberOfTimePointsInStudy(); j++) {
				String value = cr.getResponseRatesSinceNadir()[i][j];
				if (value == null || "N/A".equals(value))
					value = "";
				g.drawString(value, titleColumnOffset + colSize + j * colSize,
						rowSize * currentRow);
			}

			currentRow++;
			g.drawString("Target Lesion Response Category", 0, rowSize
					* currentRow);
			for (int j = 0; j < cr.getNumberOfTimePointsInStudy(); j++) {
				String value = cr.getResponseCategories()[i][j];
				if (value == null || "N/A".equals(value))
					value = "";
				g.drawString(value, titleColumnOffset + colSize + j * colSize,
						rowSize * currentRow);
			}

			currentRow += 2;
		}

		try {
			try {
				realPath += "/images/";
			} catch (NullPointerException npe) {
			}
			return writeImage(
					image,
					realPath + "recist_table_"
							+ (int) Math.ceil(Math.random() * 10000) + ".jpg");
		} catch (IOException e) {
			e.printStackTrace();
		}

		return null;
	}*/

	private static File writeImage(BufferedImage image, String fileName)
			throws IOException {
		if (fileName == null)
			return null;

		int offset = fileName.lastIndexOf(".");

		if (offset == -1) {
			String message = "file suffix was not specified";
			throw new IOException(message);
		}

		String type = fileName.substring(offset + 1);

		File file = new File(fileName);
		ImageIO.write(image, type, file);
		return file;
	}
}
