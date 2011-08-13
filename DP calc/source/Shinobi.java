import java.util.*;

public class Shinobi extends Races
{
	public static final double[] DP = {1, 3, 2, 7, 3, 0, 0};
	public Shinobi()
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



