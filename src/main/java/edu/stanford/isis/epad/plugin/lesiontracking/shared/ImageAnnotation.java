package edu.stanford.isis.epad.plugin.lesiontracking.shared;

public class ImageAnnotation extends AIMElement
{
    private static final String[] ALL_ELEMENT_NAMES = { ANATOMIC_ENTITY_COLLECTION, IMAGING_OBSERVATION_COLLECTION, GEOMETRIC_SHAPE_COLLECTION, CALCULATION_COLLECTION,
    													PATIENT, PERSON, IMAGE_REFERENCE_COLLECTION, DATE_TIME, IMAGE_REFERENCE_ENTITY_COLLECTION, IMAGING_PHYSICAL_ENTITY_COLLECTION,
    													CALCULATION_ENTITY_COLLECTION, IMAGING_OBSERVATION_ENTITY_COLLECTION };
    private static final int ANATOMIC_ENTITY_COLLECTION_INDEX = 0,
    						 IMAGING_OBSERVATION_COLLECTION_INDEX   = 1,
                             GEOMETRIC_SHAPE_COLLECTION_INDEX = 2,
                             CALCULATION_COLLECTION_INDEX     = 3,
                             PATIENT_INDEX 					  = 4,
                             PERSON_INDEX					  = 5,
                             IMAGE_REFERENCE_COLLECTION_INDEX = 6,
                             DATE_TIME_INDEX				  = 7,
                             IMAGE_REFERENCE_ENTITY_COLLECTION_INDEX = 8,
                             IMAGING_PHYSICAL_ENTITY_COLLECTION_INDEX = 9,
                             CALCULATION_ENTITY_COLLECTION_INDEX = 10,
                             IMAGING_OBSERVATION_ENTITY_COLLECTION_INDEX = 11;

    private static final String[] ALL_ATTRIBUTE_NAMES = { AIMAttribute.NAME, AIMAttribute.UNIQUE_IDENTIFIER };
    private static final int NAME_INDEX = 0,
                             UNIQUE_IDENTIFIER_INDEX = 1;

    private String aimFilename = "";

    public ImageAnnotation()
    {

    }

    public ImageAnnotation(String aimFilename)
    {
        this.aimFilename = aimFilename;
    }

    public String getAIMFilename()
    {
        return aimFilename;
    }

    public int getNumberOfAnatomicEntityCollections()
    {
        return getAllAIMElements().get(ANATOMIC_ENTITY_COLLECTION_INDEX).size();
    }

    public AnatomicEntityCollection getAnatomicEntityCollection(int i)
    {
        return (AnatomicEntityCollection)getAllAIMElements().get(ANATOMIC_ENTITY_COLLECTION_INDEX).get(i);
    }

    public int getNumberOfImagingObservationCollections()
    {
        return getAllAIMElements().get(IMAGING_OBSERVATION_COLLECTION_INDEX).size();
    }

    public ImagingObservationCollection getImagingObservationCollection(int i)
    {
        return (ImagingObservationCollection)getAllAIMElements().get(IMAGING_OBSERVATION_COLLECTION_INDEX).get(i);
    }
    
    public int getNumberOfImagingObservationEntityCollections()
    {
        return getAllAIMElements().get(IMAGING_OBSERVATION_ENTITY_COLLECTION_INDEX).size();
    }

    public ImagingObservationEntityCollection getImagingObservationEntityCollection(int i)
    {
        return (ImagingObservationEntityCollection)getAllAIMElements().get(IMAGING_OBSERVATION_ENTITY_COLLECTION_INDEX).get(i);
    }
    
    public int getNumberOfGeometricShapeCollections()
    {
        return getAllAIMElements().get(GEOMETRIC_SHAPE_COLLECTION_INDEX).size();
    }

    public GeometricShapeCollection getGeometricShapeCollection(int i)
    {
        return (GeometricShapeCollection)getAllAIMElements().get(GEOMETRIC_SHAPE_COLLECTION_INDEX).get(i);
    }

    public int getNumberOfCalculationCollections()
    {
        return getAllAIMElements().get(CALCULATION_COLLECTION_INDEX).size();
    }

    public CalculationCollection getCalculationCollection(int i)
    {
        return (CalculationCollection)getAllAIMElements().get(CALCULATION_COLLECTION_INDEX).get(i);
    }

    public int getNumberOfCalculationEntityCollections()
    {
        return getAllAIMElements().get(CALCULATION_ENTITY_COLLECTION_INDEX).size();
    }

    public CalculationEntityCollection getCalculationEntityCollection(int i)
    {
        return (CalculationEntityCollection)getAllAIMElements().get(CALCULATION_ENTITY_COLLECTION_INDEX).get(i);
    }
    
    public int getNumberOfPatients()
    {
        return getAllAIMElements().get(PATIENT_INDEX).size();
    }

    public Patient getPatient(int i)
    {
        return (Patient)getAllAIMElements().get(PATIENT_INDEX).get(i);
    }

    public int getNumberOfPersons()
    {
        return getAllAIMElements().get(PERSON_INDEX).size();
    }

    public Person getPerson(int i)
    {
        return (Person)getAllAIMElements().get(PERSON_INDEX).get(i);
    }

    public int getNumberOfImageReferenceCollections()
    {
        return getAllAIMElements().get(IMAGE_REFERENCE_COLLECTION_INDEX).size();
    }

    public ImageReferenceCollection getImageReferenceCollection(int i)
    {
        return (ImageReferenceCollection)getAllAIMElements().get(IMAGE_REFERENCE_COLLECTION_INDEX).get(i);
    }
    
    public int getNumberOfImageReferenceEntityCollections()
    {
        return getAllAIMElements().get(IMAGE_REFERENCE_ENTITY_COLLECTION_INDEX).size();
    }

    public ImageReferenceEntityCollection getImageReferenceEntityCollection(int i)
    {
        return (ImageReferenceEntityCollection)getAllAIMElements().get(IMAGE_REFERENCE_ENTITY_COLLECTION_INDEX).get(i);
    } 
    
    public int getNumberOfImagingPhysicalEntityCollections()
    {
        return getAllAIMElements().get(IMAGING_PHYSICAL_ENTITY_COLLECTION_INDEX).size();
    }

    public ImagingPhysicalEntityCollection getImagingPhysicalEntityCollection(int i)
    {
        return (ImagingPhysicalEntityCollection)getAllAIMElements().get(IMAGING_PHYSICAL_ENTITY_COLLECTION_INDEX).get(i);
    }

    public String getName()
    {
        return AIMElement.IMAGE_ANNOTATION;
    }

    public String getNameAttribute()
    {
        return getAllAIMAttributes().get(NAME_INDEX).getValue();
    }

    public String getUniqueIdentifier()
    {
        return getAllAIMAttributes().get(UNIQUE_IDENTIFIER_INDEX).getValue();
    }

    public String[] getAllAIMElementNames()
    {
        return ALL_ELEMENT_NAMES;
    }

    public String[] getAllAIMAttributeNames()
    {
        return ALL_ATTRIBUTE_NAMES;
    }
}
