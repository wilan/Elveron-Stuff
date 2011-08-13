import java.util.*;

public class Elf extends Races
{
	public static final double[] DP = {2.5, 4, 5.5, 9, 0, 1.5, 0};
	public Elf()
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