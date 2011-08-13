import java.util.*;

public class Terranoid extends Races
{
	public static final double[] DP = {1, 5, 2, 50, 0, 0, 0};
	
	public Terranoid()
	{
		super();
		ops[BUILDING] = true;
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