package edu.stanford.isis.epad.plugin.lesiontracking.shared;

//import java.io.Serializable;
import java.util.ArrayList;
import com.google.gwt.user.client.rpc.IsSerializable; // Serializable didn't work

public abstract class AIMElement implements IsSerializable
{
    public static String IMAGE                         = "Image",
                         IMAGE_ANNOTATION 			   = "ImageAnnotation",
                         IMAGE_COLLECTION              = "ImageCollection",
                         ANATOMIC_ENTITY_COLLECTION    = "AnatomicEntityCollection",
                         ANATOMIC_ENTITY 			   = "AnatomicEntity",
                         DATE_TIME					   = "DateTime",
                         GEOMETRIC_SHAPE_COLLECTION    = "GeometricShapeCollection",
                         GEOMETRIC_SHAPE 			   = "GeometricShape",
                         SPATIAL_COORDINATE_COLLECTION = "SpatialCoordinateCollection",
                         SPATIAL_COORDINATE 		   = "SpatialCoordinate",
                         IMAGING_PHYSICAL_ENTITY	   = "ImagingPhysicalEntity",
                         IMAGING_PHYSICAL_ENTITY_COLLECTION = "ImagingPhysicalEntityCollection",
                         IMAGE_REFERENCE_COLLECTION    = "ImageReferenceCollection",
                         IMAGE_REFERENCE 			   = "ImageReference",
                         IMAGE_REFERENCE_ENTITY		   = "ImageReferenceEntity",
                         IMAGE_REFERENCE_ENTITY_COLLECTION = "ImageReferenceEntityCollection",
                         IMAGE_SERIES              	   = "ImageSeries",
                         IMAGE_STUDY				   = "ImageStudy",
                         IMAGING_OBSERVATION_CHARACTERISTIC = "ImagingObservationCharacteristic",
                         IMAGING_OBSERVATION_COLLECTION = "ImagingObservationCollection",
                         IMAGING_OBSERVATION_CHARACTERISTIC_COLLECTION = "ImagingObservationCharacteristicCollection",
                         IMAGING_OBSERVATION		   = "ImagingObservation",
                         STUDY 						   = "Study",
                         SERIES 					   = "Series",
                         CALCULATION_DATA_COLLECTION   = "CalculationDataCollection",
                         CALCULATION_DATA 			   = "CalculationData",
                         CALCULATION_RESULT_COLLECTION = "CalculationResultCollection",
                         CALCULATION_RESULT 		   = "CalculationResult",
                         CALCULATION_COLLECTION 	   = "CalculationCollection",
                         CALCULATION 				   = "Calculation",
                         DATA_COLLECTION 			   = "DataCollection",
                         DATA 						   = "Data",
                         PATIENT 					   = "Patient",
                         PERSON						   = "Person",
                         START_DATE					   = "StartDate",
                         TYPE_CODE					   = "TypeCode",
                         CALCULATION_ENTITY			   = "CalculationEntity",
                         CALCULATION_ENTITY_COLLECTION = "CalculationEntityCollection",
                         IMAGING_OBSERVATION_ENTITY_COLLECTION = "ImagingObservationEntityCollection",
                         IMAGING_OBSERVATION_ENTITY    = "ImagingObservationEntity",
                         DESCRIPTION				   = "Description",
                         VALUE						   = "Value",
                         LABEL						   = "Label";

    private ArrayList<ArrayList<AIMElement>> aimElements = new ArrayList<ArrayList<AIMElement>>();
    private ArrayList<AIMAttribute> aimAttributes = new ArrayList<AIMAttribute>();

    public static AIMElement getAIMElementOfType(String name)
    {
        if(name.equalsIgnoreCase(ANATOMIC_ENTITY_COLLECTION))
            return new AnatomicEntityCollection();

        if(name.equalsIgnoreCase(ANATOMIC_ENTITY))
            return new AnatomicEntity();
        
        if(name.equalsIgnoreCase(DATE_TIME))
        	return new DateTime();

        if(name.equalsIgnoreCase(IMAGING_OBSERVATION_CHARACTERISTIC))
        	return new ImagingObservationCharacteristic();
        
        if(name.equalsIgnoreCase(IMAGING_OBSERVATION_COLLECTION))
        	return new ImagingObservationCollection();
        
        if(name.equalsIgnoreCase(IMAGING_OBSERVATION_CHARACTERISTIC_COLLECTION))
        	return new ImagingObservationCharacteristicCollection();
        
        if(name.equalsIgnoreCase(IMAGING_OBSERVATION))
        	return new ImagingObservation();
        
        if(name.equalsIgnoreCase(GEOMETRIC_SHAPE_COLLECTION))
            return new GeometricShapeCollection();

        if(name.equalsIgnoreCase(GEOMETRIC_SHAPE))
            return new GeometricShape();

        if(name.equalsIgnoreCase(SPATIAL_COORDINATE_COLLECTION))
            return new SpatialCoordinateCollection();

        if(name.equalsIgnoreCase(SPATIAL_COORDINATE))
            return new SpatialCoordinate();

        if(name.equalsIgnoreCase(IMAGE))
            return new Image();

        if(name.equalsIgnoreCase(IMAGE_COLLECTION))
            return new ImageCollection();

        if(name.equalsIgnoreCase(IMAGE_REFERENCE_COLLECTION))
            return new ImageReferenceCollection();

        if(name.equalsIgnoreCase(IMAGE_REFERENCE))
            return new ImageReference();
        
        if(name.equalsIgnoreCase(IMAGE_REFERENCE_ENTITY_COLLECTION))
            return new ImageReferenceEntityCollection();

        if(name.equalsIgnoreCase(IMAGE_REFERENCE_ENTITY))
            return new ImageReferenceEntity();

        if(name.equalsIgnoreCase(IMAGE_SERIES))
            return new ImageSeries();

        if(name.equalsIgnoreCase(IMAGE_STUDY))
            return new ImageStudy();

        if(name.equalsIgnoreCase(STUDY))
            return new Study();

        if(name.equalsIgnoreCase(SERIES))
            return new Series();

        if(name.equalsIgnoreCase(CALCULATION_RESULT_COLLECTION))
            return new CalculationResultCollection();

        if(name.equalsIgnoreCase(CALCULATION_RESULT))
            return new CalculationResult();

        if(name.equalsIgnoreCase(CALCULATION_COLLECTION))
            return new CalculationCollection();

        if(name.equalsIgnoreCase(CALCULATION))
            return new Calculation();

        if(name.equalsIgnoreCase(CALCULATION_DATA_COLLECTION))
            return new CalculationDataCollection();

        if(name.equalsIgnoreCase(CALCULATION_DATA))
            return new CalculationData();

        if(name.equalsIgnoreCase(DATA_COLLECTION))
            return new DataCollection();

        if(name.equalsIgnoreCase(DATA))
            return new Data();

        if(name.equalsIgnoreCase(PATIENT))
            return new Patient();

        if(name.equalsIgnoreCase(PERSON))
            return new Person();

        if(name.equalsIgnoreCase(START_DATE))
            return new StartDate();

        if(name.equalsIgnoreCase(IMAGING_PHYSICAL_ENTITY_COLLECTION))
        	return new ImagingPhysicalEntityCollection();
        
        if(name.equalsIgnoreCase(IMAGING_PHYSICAL_ENTITY))
        	return new ImagingPhysicalEntity();
        
        if(name.equalsIgnoreCase(TYPE_CODE))
        	return new TypeCode();
        
        if(name.equalsIgnoreCase(CALCULATION_ENTITY_COLLECTION))
        	return new CalculationEntityCollection();
        
        if(name.equalsIgnoreCase(CALCULATION_ENTITY))
        	return new CalculationEntity();
        
        if(name.equalsIgnoreCase(IMAGING_OBSERVATION_ENTITY_COLLECTION))
        	return new ImagingObservationEntityCollection();
        
        if(name.equalsIgnoreCase(IMAGING_OBSERVATION_ENTITY))
        	return new ImagingObservationEntity();
        
        if(name.equalsIgnoreCase(DESCRIPTION))
        	return new Description();
        
        if(name.equalsIgnoreCase(VALUE))
        	return new Value();
        
        if(name.equalsIgnoreCase(LABEL))
        	return new Label();

        System.out.println("No AIM element of type " + name);
        return null;
    }

    public abstract String getName();
    public abstract String[] getAllAIMElementNames();
    public abstract String[] getAllAIMAttributeNames();

    public AIMElement()
    {
        String[] allAIMElementNames = getAllAIMElementNames();

        for( int i = 0; i < allAIMElementNames.length; i++ )
            aimElements.add(new ArrayList<AIMElement>());

        String[] allAIMAttributes = getAllAIMAttributeNames();

        for( int i = 0; i < allAIMAttributes.length; i++ )
            aimAttributes.add(new AIMAttribute());
    }

    public ArrayList<ArrayList<AIMElement>> getAllAIMElements()
    {
        return aimElements;
    }

    public ArrayList<AIMAttribute> getAllAIMAttributes()
    {
        return aimAttributes;
    }

    public boolean addAIMElement(AIMElement aimElement)
    {
        String aimElementName = aimElement.getName();
        String[] allAIMElementNames = getAllAIMElementNames();

        for( int i = 0; i < allAIMElementNames.length; i++ )
            if( aimElementName.equalsIgnoreCase(allAIMElementNames[i]))
            {
                aimElements.get(i).add(aimElement);
                return true;
            }

        return false;
    }

    public boolean addAIMAttribute(AIMAttribute aimAttribute)
    {
        String aimAttributeName = aimAttribute.getName();
        String[] allAIMAttributeNames = getAllAIMAttributeNames();

        for(int i = 0; i < allAIMAttributeNames.length; i++ )
            if( aimAttributeName.equalsIgnoreCase(allAIMAttributeNames[i]))
            {
                aimAttributes.set(i, aimAttribute);
                return true;
            }

        return false;
    }

    public void outputAIMHeirarchy(int indent)
    {
        for(int i = 0; i < indent; i++ )
            System.out.print("    ");
        System.out.print(getName());
        outputAttributes();
        System.out.println();

        indent++;
        for( int i = 0; i < aimElements.size(); i++ )
            for(int j = 0;j < aimElements.get(i).size(); j++ )
            {
                aimElements.get(i).get(j).outputAIMHeirarchy(indent);
            }
        indent--;
    }

    private void outputAttributes()
    {
        System.out.print("    ");
        for(int i = 0; i < aimAttributes.size(); i++ )
                System.out.print(aimAttributes.get(i).getName() + ": " + aimAttributes.get(i).getValue() + "    ");
    }
}
