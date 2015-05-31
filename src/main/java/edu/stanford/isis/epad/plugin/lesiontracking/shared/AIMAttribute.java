package edu.stanford.isis.epad.plugin.lesiontracking.shared;

import com.google.gwt.user.client.rpc.IsSerializable;  // Serializable didn't work

public class AIMAttribute implements IsSerializable
{
    public static final String X = "x",
                               Y = "y",
                               UNIT_OF_MEASURE = "unitOfMeasure",
                               VALUE = "value",
                               START_DATE = "startDate",
                               STUDY_DATE = "studyDate",
                               NAME = "name",
                               UNIQUE_IDENTIFIER = "uniqueIdentifier",
                               CODE_MEANING = "codeMeaning",
                               DESCRIPTION = "description",
                               INSTANCE_UID = "instanceUID",
                               SOP_INSTANCE_UID = "sopInstanceUID",
                               TYPE = "type",
                               COORDINATE_INDEX = "coordinateIndex",
                               CODE_SYSTEM = "codeSystem";

    private String name = "", value = "";

    public AIMAttribute()
    {
    }

    public AIMAttribute(String name, String value)
    {
        this.setName(name);
        this.value = value;
    }

    public void setName(String name)
    {
        this.name = name;
    }

    public void setValue(String value)
    {
        this.value = value;
    }

    public String getName()
    {
        return name;
    }

    public String getValue()
    {
        return value;
    }
}
