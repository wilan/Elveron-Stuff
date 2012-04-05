import java.util.*;
import java.util.regex.*;
public class Goblin extends Race
{
	public static final double[] DP = {0, 2, 4, 2, 0, 0, 0};
	

	private double extraD;
	public Goblin()
	{
		super("Goblin");
		extraD = 3.6;
		ops[LAND] = false;
	}
	
	//OVERRIDE THE SOI, repeat code poor style	
	public void parseSOL(Scanner s) throws Exception
	{
		while(true)
		{
			String word = s.next();
			if(word.equals("Mountain"))
			{
				String line = s.nextLine();
				Pattern p = Pattern.compile("(\\d+\\.\\d+)");
				Matcher m = p.matcher(line);
				if(m.find())
				{
					extraD = new Double(m.group(0))/25.0;
					break;
				}
			}
		}
		ops[LAND] = true;
	}
	
	public double getDefense(int i)
	{
		if(i == 2)
			return DP[2]+extraD;
		return DP[i];
	}
}
