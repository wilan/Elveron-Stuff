import java.util.*;

public class Leshkin extends Race
{
	public static final double[] DP = {2, 4, 2, 7, 0, 0, 0};
	
	private double aDef;

	public Leshkin()
	{
		super("Leshkin");
		aDef = 10;
	}
	
	public static final int K_DPMOD_INDEX = 3;
	public static final int ADEF_INDEX = 4;
	public static final int R_DPMOD_INDEX = 8;
	
	//OVERRIDE THE SOI, repeat code poor style	
	public void parseSOI(Scanner s) throws Exception
	{
		//look for the percents.  Take the correct ones.
		s.nextLine();
		String word = s.next();
		int i = 1;
		while(i <= 10)
		{
			if(word.indexOf("%") >= 0)
			{
				if(i == K_DPMOD_INDEX)
					imps[0] = new Double(word.replaceAll("%", ""));
				else if(i == R_DPMOD_INDEX)
					imps[1] = new Double(word.replaceAll("%", ""));
				else if(i == ADEF_INDEX)
					aDef = new Double(word.replaceAll("%", ""));
				i++;
			}
			word = s.next();
		}
		ops[IMPROVEMENT] = true;
//System.out.println(imps[0] + ", " + imps[1]);
	}
	
	public double getDefense(int i)
	{
		if(i == 4)
			return aDef;
		else
			return super.getDefense(i);
	}
}
