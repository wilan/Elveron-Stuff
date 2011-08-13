import java.util.*;
import java.io.*;

public class Data implements Serializable
{
	public static void main(String args[]) throws Exception
	{
		allRaces(new File("."));
	}

	public static void allRaces(File dir) throws Exception {
		if (dir.isDirectory()) {
			String[] children = dir.list();
			for (int i=0; i<children.length; i++) {
				if(children[i].indexOf(".class") >= 0)
				{
					Class c = Class.forName(children[i].substring(0, children[i].indexOf(".")));
					if(!(c.getName().equals("Races")) && Class.forName("Races").isAssignableFrom(c))
					{
						System.out.println(c.getName());
					}
				}
			}
		}
	}

	HashMap<String, RaceElement> raceInfo;

	public Data()
	{
		raceInfo = new HashMap();
	}

	class RaceElement implements Serializable
	{
		String tavName;
		double[] unitArr;
		
		public RaceElement()
		{
			tavName = "tavern";
			unitArr = new double[7];
		}
	}
}
