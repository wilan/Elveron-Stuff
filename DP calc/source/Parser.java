import java.util.*;
import javax.swing.*;
import java.awt.*;
import java.io.*;

//Parser will crash if YOU are named Farsight, Land, Building, or Improvement
//Parser will also crash on a target called Races:
public class Parser
{
	public static void sortRaceList() throws Exception
	{
		Scanner s = new Scanner(new File("raceList.txt"));
		TreeSet<String> ts = new TreeSet();
		while(s.hasNext())
			ts.add(s.next());
		
		PrintStream ps = new PrintStream(new File("raceList.txt"));
		Iterator<String> it = ts.iterator();
		while(it.hasNext())
			ps.println(it.next());
	}
	
	private Races kingdom;
	
	public Parser() throws Exception
	{
		kingdom = null;
	}
	
	public boolean isInit()
	{
		return kingdom!=null;
	}
	public JTable modData()
	{
		return kingdom.modTable();
	}
	public JTable totalData()
	{
		return kingdom.totalData();
	}
	
	public JTable unitData()
	{
		return kingdom.unitData();
	}
	
	public boolean isOpRequired(int i)
	{
		return kingdom.isOpRequired(i);
	}
	
	public void toggleShield()
	{
		kingdom.toggleShield();
	}
	
	public void toggleHero(int slot)
	{
		if(kingdom != null)
			kingdom.toggleHero(slot);
	}
	
	public boolean addOp(String op) throws Exception
	{
		if(kingdom == null)
		{
			//First do a scan for the race via FS, if no race prompt the user to select a race
			scanForRace(op);
			if(kingdom == null)
			{
				if(!selectRaceManually())
					return false;
			}
			addOp(op);
		}
		Scanner s = new Scanner(op);
		//Algorithm:
		//Lesser (Skip 25 disregard)
		//Farsight,
		//Building
		//Land
		//Improvement
		while(s.hasNext())
		{
			String word = s.next();
			if(word.equals("Lesser"))
			{
				for(int i = 0; i < 50; i++)
				{
					s.next(); //skip the garbage make sure you gloss the name
				}
			}
			if(word.equals("Farsight,"))
			{
				System.out.println("FS");
				kingdom.parseFS(s);
			}
			if(word.equals("Building"))
			{
				System.out.println("B");
				kingdom.parseSOB(s);
			}
			if(word.equals("Improvement"))
			{
				System.out.println("IMP");	
				kingdom.parseSOI(s);
			}
			if(word.equals("Military"))
			{
				System.out.println("Mil");
				kingdom.parseSOM(s);
			}
		}
		return true;
	}
	public void scanForRace(String op) throws Exception
	{
		Scanner s = new Scanner(op);
		while(s.hasNext())
		{
			String word = s.next();
			if(word.equals("Farsight,"))//only enter on a farsight don't allow for kingdoms named "Race:"
			{
				while(true)
				{
					word = s.next();
					if(word.equals("Race:"))
					{
						String name = s.next();
						String next = s.next();
						while(!next.equals("Peasants:"))
						{
							name = name+next;
							next = s.next();
						}
						kingdom = NewRace(name);
						return;
					}
				}
			}
		}
	}
	
	public boolean selectRaceManually() throws Exception
	{
		//select a race, 6 race per list + next
		Scanner s = new Scanner(getClass().getResourceAsStream("/racelist.txt"));
		TreeSet<String> races = new TreeSet();
		
		while(true)
		{
			races = new TreeSet();
			while(s.hasNext())
				races.add(s.next());
			String[] racesArr = new String[races.size()+1];
			int idx = 0;
			Iterator<String> it = races.iterator();
			while(it.hasNext())
			{
				racesArr[idx] = it.next();
				idx++;
			}
			racesArr[idx] = "Cancel";
			String selected = (String) (JOptionPane.showInputDialog(new Frame(), "No farsight detected, select a race.", "" , 1, null, racesArr, racesArr[0]));
				
			if(selected == null || selected.equals("Cancel"))
			{
				//do something
				return false;
			}
			else
			{
				kingdom = NewRace((String)(selected));
				break;
			}
		}
		return true;
	}
	
	public Races NewRace(String r)
	{
		try
		{
			Class c = Class.forName(r);
			return (Races)(c.newInstance());
		}
		catch(Exception e)
		{
			System.out.println(r + ": Race does not exist");
			throw new NullPointerException("Race does not exist");
		}
	}
}