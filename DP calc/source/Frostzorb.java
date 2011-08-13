import java.util.*;

public class Frostzorb extends Races
{
	public static final double[] DP = {0, 4, 7, 3, 0, 0, 0};
	public Frostzorb()
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