import java.util.*;

public class Spirit extends Races
{
	public static final double[] DP = {2, 2, 4, 2, 0, 0, 0};
	public Spirit()
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