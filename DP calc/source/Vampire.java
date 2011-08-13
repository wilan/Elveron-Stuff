import java.util.*;
import javax.swing.*;
public class Vampire extends Races
{
	public static final double[] DP = {1.5, 6, 5, 4, 2, 0, 0};
	
	private boolean isNight;
	public Vampire()
	{
		super();
		Object[] options = { "Day", "Night" };
		int choice = JOptionPane.showOptionDialog(null, "Is it day or night?", "Time Selection", JOptionPane.DEFAULT_OPTION, JOptionPane.WARNING_MESSAGE, null, options, options[0]);
		if(choice != 1)
			isNight = false;
		else
			isNight = true;
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
		if(isNight)
		{
			if(i == 1) return 3;
			return DP[i];
		}
		else
			return DP[i];
	}
}