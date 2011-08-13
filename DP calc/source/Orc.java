import java.util.*;

public class Orc extends Races
{
	public static final double[] DP = {0, 3, 6, 7.5, 0, 2, 0};
	public Orc()
	{
		super();
	}
	
	public double extraMods()
	{
		return -(taverns*.1);
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
		return "TortureChamber";
	}
}