import java.util.*;

public class FlameWeaver extends Races
{
	public static final double[] DP = {1, 3, 3, 8, 0, 0, 0};
	public FlameWeaver()
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