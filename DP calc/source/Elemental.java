import java.util.*;

public class Elemental extends Race
{
	public double[] inTraining;
	public Elemental()
	{
		super("Elemental");
		inTraining = new double[Race.NUMUNITS];
		for(int i = 0; i < inTraining.length; i++)
			inTraining[i] = 10000000;
	}
	
	public void processMil(String str, int i, double[] temphome, double[] tempout, HashMap<String, Integer> maper)
	{
		super.processMil(str, i, temphome, tempout, maper);
		String[] mil = str.split("\\s");
		String unitsHome = mil[mil.length-1];
		unitsHome = unitsHome.replaceAll(",", "");
		unitsHome = unitsHome.replaceAll("\\(", " ");
		unitsHome = unitsHome.replaceAll("\\)", "");
		tempTraining[i] = new Double(unitsHome.split("\\s")[1]);
	}
	
	double[] tempTraining;
	public void parseSOM(Scanner s) throws Exception
	{
		tempTraining = new double[Race.NUMUNITS];
		super.parseSOM(s);	
	}
	
	public void addSOM(double[] home, double[] out)
	{
		unitsHome = min(home, unitsHome);
		unitsOut = max(out, unitsOut);
		inTraining = min(inTraining, tempTraining);
	}
	
	public double extraDP()
	{
		if(inTraining != null)
		{
			return inTraining[1]*3/Race.OFFSET+inTraining[2]*4.5/Race.OFFSET+inTraining[3]*6/Race.OFFSET;
		}
		return 0;
	}
}
