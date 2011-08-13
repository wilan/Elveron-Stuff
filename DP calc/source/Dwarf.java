import java.util.*;

public class Dwarf extends Races
{
	public static final double[] DP = {0, 3, 5.5, 0, 0, 0, 0};
	public Dwarf()
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