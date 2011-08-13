import java.util.*;

public class Barbarian extends Races
{
	public static final double[] DP = {3, 8, 2, 0, 3, 0, 0};
	public Barbarian()
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