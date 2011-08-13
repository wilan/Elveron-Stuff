import java.util.*;

public class Gryphon extends Races
{
	public static final double[] DP = {3, 4, 6, 0, 10, 0, 0};
	public Gryphon()
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
}