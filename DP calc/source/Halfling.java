import java.util.*;

public class Halfling extends Races
{
	public static final double[] DP = {0, 1, 2, 3, 0, 0, 0};
	public Halfling()
	{
		super();
	}
	
	public double extraMods()
	{
		return 0;
	}
	
	public double extraDP()
	{
		return 0;
	}
	
	public double getDefense(int i)
	{
		return DP[i];
	}
	
	public String tavernName()
	{
		return "Pub";
	}
}

