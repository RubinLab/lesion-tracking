package edu.stanford.isis.epad.plugin.lesiontracking.server;

public class UnitConversion
{
	private static final String[] UNITS = {"mm", "cm"};

	private String sourceUnit = "", targetUnit = "";
	
	private float conversionFactor = 1;

	private void calculateConversionFactor()
	{
		int indexOfSource = 0, indexOfTarget = 0;
	
		for(int i = 0; i < UNITS.length; i++)
		{
			if(UNITS[i].equalsIgnoreCase(sourceUnit))
				indexOfSource = i;
			
			if(UNITS[i].equalsIgnoreCase(targetUnit))
				indexOfTarget = i;
		}
		
		float conversionFactor = 1;
		if(indexOfSource < indexOfTarget)
			for(int i = indexOfSource; i < indexOfTarget; i++ )
				conversionFactor /= 10.0;
		else
			for(int i = indexOfSource; i > indexOfTarget; i-- )
				conversionFactor *= 10.0;
		
		this.conversionFactor = conversionFactor;
	}
	public float convertToTargetUnit(double value)
	{
		float keep=(float) (value * conversionFactor);
		return keep;
	}
	
	public UnitConversion(String sourceUnit, String targetUnit)
	{
		this.sourceUnit = sourceUnit;
		this.targetUnit = targetUnit;
		
		calculateConversionFactor();
	}
}
