import java.util.*;

public class Human extends Races
{
	public static final double[] DP = {0, 3, 7, 4, 1, 0, 0};
	public Human()
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




