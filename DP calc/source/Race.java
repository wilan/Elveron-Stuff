import java.util.*;
import javax.swing.*;
import java.util.regex.*;
import java.io.*;
/**Notes
 * We will assume 0% taverns without getting SOB
 * It is the user's responsibility to not get conflicting information
 */
public class Race
{
	//Stuffs
	public static HashMap<String, RaceInfo> baseVals;
	
	public static void initialize(){
		baseVals = new HashMap();
		String stats = "stats.txt";
		try
		{
			Scanner s = new Scanner(new File(stats));
			//Format: human|0 3 7 4 1 0 0|tavern
			while(s.hasNextLine())
			{
				String race = s.nextLine();
			  Pattern p = Pattern.compile("(.*)\\|(.*)\\|(.*)");
		    Matcher m = p.matcher(race);
				if(m.find())
					baseVals.put(m.group(1), new RaceInfo(m.group(2), m.group(3).toLowerCase()));
			}
		}catch(Exception e)
		{
			e.printStackTrace();
			System.out.println("Missing file: " + stats);
			System.exit(0);
		}
	}
	
	public static final int NUMUNITS = 7;
	public static final double OFFSET = .85;
	public static final int NUMHEROES = 2;
	
	//FS only
	protected double[] totalUnits;
	
	//som does not divide .85
	protected double[] unitsHome;
	protected double[] unitsOut;
	
	public static final int MILITARY = 0, FARSIGHT = 1, IMPROVEMENT = 2, BUILDING = 3, LAND = 4;
	protected boolean[] ops;
	
	protected String unitNames[];
	protected RaceInfo info;
	public static final double BIGNUMBER = 1000000;
	public Race(String name)
	{
		info = baseVals.get(name);
		totalUnits = new double[NUMUNITS];
		unitsHome = new double[NUMUNITS];
		unitsOut = new double[NUMUNITS];
		unitNames = new String[NUMUNITS];
		
		for(int i = 0; i < NUMUNITS; i++)
		{
			totalUnits[i] = BIGNUMBER;
			unitsHome[i] = BIGNUMBER;
			unitNames[i] = i+"";
			unitsOut[i] = 0;
		}
		shield = SHIELD;
		heroes = new double[NUMHEROES];
		taverns = 0;
		imps = new double[2];
		ops = new boolean[5];
		ops[MILITARY] = false;
		ops[FARSIGHT] = false;
		ops[IMPROVEMENT] = false;
		ops[BUILDING] = false;
		ops[LAND] = true;
	}
	
	public boolean isOpRequired(int i)
	{
		return !ops[i];
	}
	
	public void parseSOL(Scanner s) throws Exception
	{
		ops[LAND] = true;
	}
	
	public void parseFS(Scanner s) throws Exception
	{
		//extract the unit numbers
		String word = s.next();
		while(!word.equals("Military"))
			word = s.next();
		double[] units = new double[NUMUNITS];
		int i = 0;
		while(i < NUMUNITS)
		{
			String line = s.nextLine();
			line = line.replaceAll(",", "");
			Pattern p = Pattern.compile("(.*):\\s+(.*)");
			Matcher m = p.matcher(line);
			if(m.find())
			{
				unitNames[i] = m.group(1);
				units[i] = new Integer(m.group(2));
				i++;
			}
		}

		totalUnits = units;
		ops[FARSIGHT] = true;
	}
	
	public static final int K_DPMOD_INDEX = 3;
	public static final int R_DPMOD_INDEX = 8;
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
				i++;
			}
			word = s.next();
		}
		ops[IMPROVEMENT] = true;
//System.out.println(imps[0] + ", " + imps[1]);
	}
	
	public void processMil(String str, int i, double[] temphome, double[] tempout, HashMap<String, Integer> maper)
	{
		String[] mil = str.split("\\s");
		String name = "";
		for(int j = 0; j < mil.length-13;j++)
		{
			name+=mil[j];
		}
		maper.put(name, i);
		
		String unitsHome = mil[mil.length-1];
		unitsHome = unitsHome.replaceAll(",", "");
		unitsHome = unitsHome.replaceAll("\\(", " ");
		unitsHome = unitsHome.split("\\s")[0];
		temphome[i] = new Double(unitsHome);
	}
	
	public void parseSOM(Scanner s) throws Exception
	{
		HashMap<String, Integer> maper = new HashMap();
		s.nextLine();
		s.nextLine();
		s.nextLine();
		s.nextLine();
		int i = 0;
		double[] temphome = new double[NUMUNITS];
		double[] tempout = new double[NUMUNITS];
		while(i < NUMUNITS)
		{
			//Format of a home line:
			//NAME 12 DASH Stuff
			processMil(s.nextLine(), i, temphome, tempout, maper);
//System.out.println(unitsHome);
			i++;
		}
		s.nextLine();
		s.nextLine();
		s.nextLine();
		s.nextLine();
		s.nextLine();
		while(true) //break on hero
		{
			String line = s.nextLine();
			String[] mil = line.split("\\s");
			String name = "";
			for(int j = 0; j < mil.length-19; j++)
			{
				name+=mil[j];
			}
			if(name.equals("Hero"))
			{
				//update som and exit
				/*for(int j = 0; j < NUMUNITS; j++)
				{
					System.out.println(tempout[j]);
				}*/
				addSOM(temphome, tempout);
				
				break;
			}
			else
			{
				String unitsOut = mil[mil.length-1];
				unitsOut = unitsOut.replaceAll(",", "");
				tempout[maper.get(name)] = new Double(unitsOut);
			}
		}
		ops[MILITARY] = true;
	}
	
	public void addSOM(double[] home, double[] out)
	{
		unitsHome = min(home, unitsHome);
		unitsOut = max(out, unitsOut);
	}
	
	public double[] min(double[] New, double[] old)
	{
		if(old == null)
			return New;
		if(New.length != NUMUNITS || old.length != NUMUNITS)
			throw new NullPointerException("Bad array length");
		else
		{
			double[] retVal = new double[NUMUNITS];
			for(int i = 0; i < NUMUNITS; i++)
			{
				if(New[i] < old[i])
					retVal[i] = New[i];
				else
					retVal[i] = old[i];
			}
			return retVal;
		}
	}
	
	public double[] max(double[] New, double[] old)
	{
		if(old == null)
			return New;
		if(New.length != NUMUNITS || old.length != NUMUNITS)
			throw new NullPointerException("Bad array length");
		else
		{
			double[] retVal = new double[NUMUNITS];
			for(int i = 0; i < NUMUNITS; i++)
			{
				if(New[i] > old[i])
					retVal[i] = New[i];
				else
					retVal[i] = old[i];
			}
			return retVal;
		}
	}
	
	public double getDefense(int i)
	{
		return info.unitArr[i];
	}
	
	public double extraDP()
	{
		return 0;
	}
	public double extraMods()
	{
		return 0;
	}
	
	public void parseSOB(Scanner s) throws Exception
	{
		//extract the number of taverns
		String word = s.next();
		while(!word.equals("Breakdown"))
			word = s.next();
		s.nextLine();
		while(true)
		{
			String line = s.nextLine();
			if(line.equals("Incoming Buildings"))return;
			String[] lineSplit = line.split("\\s");
			String buildingName = "";
			for(int i = 0; i < lineSplit.length-3; i++)
			{
				buildingName += lineSplit[i];
			}
			if(buildingName.equalsIgnoreCase(tavernName()))
			{
				taverns = new Double(lineSplit[lineSplit.length-1].replaceAll("%", ""));
				ops[BUILDING] = true;
				return;
			}
		}

	}

	public String tavernName()
	{
		return info.tavName;
	}
	
	public double getRawDefense()
	{
		double[] minArr = minUnits();
		double total = 0;
		for(int i = 0; i < NUMUNITS; i++)
		{
			total = total + minArr[i]*getDefense(i);
		}
		return (total+extraDP());
	}
	
	//imps
	protected double[] imps;
	protected double shield;
	protected double[] heroes;
	protected double taverns;
	
	public static final double SHIELD = 5;
	public void toggleShield()
	{
		if(shield == 0) //1 for on 0 for off, no others please
			shield = SHIELD;
		else
			shield = 0;
	}
	
	public void addImps(double[] input)
	{
		imps = input;
	}
	
	public void taverns(double percent)
	{
		taverns = percent;
	}
	
	public static final int KYL = 0;
	public static final int LSD = 1;
	public static final double[] DEFHEROES = {6, 5};
	public void toggleHero(int slot)
	{
		if(heroes[slot] == 0)
		{
			heroes[slot] = DEFHEROES[slot];
		}
		else
			heroes[slot] = 0;
	}
	
	public static final String[] MODTYPES = {"Taverns", "KIMPS", "RIMPS", "Shield", "Kylian/Dina", "LSD/Shade", "Extras"};
	public JTable modTable()
	{
		String[] columnNames = {"Mods", "Value"};
		Object[][] data = new Object[7][2];
		for(int i = 0; i < 7; i++)
		{
			data[i][0] = MODTYPES[i];
		}
		data[0][1] = taverns*.7 + "%";
		data[1][1] = imps[0]  + "%";
		data[2][1] = imps[1]  + "%";
		data[3][1] = shield  + "%";
		data[4][1] = heroes[KYL]  + "%";
		data[5][1] = heroes[LSD]  + "%";
		data[6][1] = extraMods()  + "%";
		
		JTable retVal = new JTable(data, columnNames);
		retVal.setEnabled(false);
		//retVal.setFillsViewportHeight(true);
		return retVal;
	}
	
	public JTable totalData()
	{
		String[] columnNames = {"Totals", ""};
		Object[][] data = new Object[3][2];
		data[0][0] = "Raw DP";
		data[0][1] = getRawDefense();
		data[1][0] = "Mods Factor";
		data[1][1] = getMods();
		data[2][0] = "Mod Defense";
		data[2][1] = getModDefense();
	
		JTable retVal = new JTable(data, columnNames);
		retVal.setEnabled(false);
		//retVal.setFillsViewportHeight(true);
		return retVal;
	}
	
	public JTable unitData()
	{
		String[] columnNames = {"Unit Name", "DP Val", "Min Away", "Max Home", "Farsight", "Calculated"};
		Object[][] data = new Object[NUMUNITS][columnNames.length];
		double[] minArr = minUnits();
		for(int i = 0; i < NUMUNITS; i++)
		{
			data[i][0] = unitNames[i];
			data[i][1] = getDefense(i);
			data[i][2] = (int)(unitsOut[i]*OFFSET);
			data[i][3] = (int)(unitsHome[i]/OFFSET);
			data[i][4] = totalUnits[i];
			data[i][5] = (int)(minArr[i]);
		}
		JTable retVal = new JTable(data, columnNames);
		retVal.setEnabled(false);
		//retVal.setFillsViewportHeight(true);
		return retVal;
	}
	
	public double getMods()
	{
		//1+taverns+magic shield+kylian+dina+shade+LSD+imps+rimps
		double base = imps[0]+imps[1]+shield+taverns*.7+heroes[0]+heroes[1]+extraMods();
		base = base/100;
		base = base+1;
		return base;
	}
	
	public double getModDefense()
	{
		return getRawDefense()*getMods();
	}
	
	public double[] minUnits()
	{
		double[] retVal;
		if(totalUnits == null)
		{
			//no farsight just do som
			if(unitsHome != null)
			{
				retVal = new double[NUMUNITS];
				for(int i = 0;i < NUMUNITS; i++)
				{
					retVal[i] = unitsHome[i]/OFFSET; //want to maximize the som
				}
				return retVal;
			}
			else
				return null;
		}
		else
		{
			//SOM AND FS
			if(unitsHome != null)
			{
				double[] temp1 = new double[NUMUNITS];
				double[] temp2 = new double[NUMUNITS];
				for(int i = 0; i < NUMUNITS; i++)
				{
					//SOM/.85 or FS-SENT*.85
					temp1[i] = unitsHome[i]/OFFSET;
					temp2[i] = totalUnits[i]-unitsOut[i]*OFFSET;
				}
				return min(temp1, temp2);
			}
			else
			{
				return totalUnits;
			}
		}
	}
	
	public String toString()
	{
		return getClass().getName();
	}
}
