import java.util.*;

public class Elemental extends Races
{
	public static final double[] DP = {3, 4.5, 6, 7.5, 0, 0, 0};
	public double[] inTraining;
	public Elemental()
	{
		super();
		inTraining = new double[Races.NUMUNITS];
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
		tempTraining = new double[Races.NUMUNITS];
		super.parseSOM(s);	
	}
	
	public void addSOM(double[] home, double[] out)
	{
		unitsHome = min(home, unitsHome);
		unitsOut = max(out, unitsOut);
		inTraining = min(inTraining, tempTraining);
	}
	
	public double extraMods()
	{
		return 0;
	}
	
	public double extraDP()
	{
		if(inTraining != null)
		{
			return inTraining[1]*3/Races.OFFSET+inTraining[2]*4.5/Races.OFFSET+inTraining[3]*6/Races.OFFSET;
		}
		return 0;
	}
	
	public double getDefense(int i)
	{
		return DP[i];
	}
}