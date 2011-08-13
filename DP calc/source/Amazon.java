import java.util.*;

public class Amazon extends Races
{
	public static final double[] DP = {0, 3, 0, 7, 6, 0, 0};
	public Amazon()
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

