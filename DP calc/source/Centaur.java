import java.util.*;

public class Centaur extends Races
{
	public static final double[] DP = {0, 3, 2, 7, 6, 0, 0};
	public Centaur()
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