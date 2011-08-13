import java.util.*;

public class DarkDwarf extends Races
{
	public static final double[] DP = {0, 3, 6, 2, 0, 0, 0};
	public DarkDwarf()
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

