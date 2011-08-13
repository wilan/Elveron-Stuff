import java.util.*;

public class Troll extends Races
{
	public static final double[] DP = {3, 5, 10, 15, 10, 3, 0};
	public Troll()
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