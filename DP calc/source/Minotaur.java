import java.util.*;

public class Minotaur extends Races
{
	public static final double[] DP = {0, 4, 2, 9, 3, 0, 0};
	public Minotaur()
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