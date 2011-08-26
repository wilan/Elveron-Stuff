import java.util.*;
import javax.swing.*;
import java.awt.event.*;
import java.awt.*;
import java.io.*;
import java.util.regex.Pattern;
import java.util.regex.Matcher;

public class StatChange extends JFrame
{
	public static void main(String args[]) throws Exception
	{
		JOptionPane.showMessageDialog(new Frame(), "The format is obvious, do not mess with the format. that is all.");
		new StatChange();
	}

	private JButton remove;
	private JButton add;
	private JButton modify;
	public StatChange() throws Exception
	{
		setSize(400, 100);
		setVisible(true);
		add = new JButton("Add Race");    
		add.addActionListener(new ActionListener()
		{
			public void actionPerformed(ActionEvent e)
			{
				//Load Race
        HashMap<String, String> races = loadRaces();
				String name = JOptionPane.showInputDialog(new Frame(), "Name of race?", "");

        if(name == null)
        {
          //do nothing
        }else
        {
					name = formatName(name);
          //Prompt new unit options and tavern names
          //overwrite file
          String raceInfo = races.get(name);
					if(raceInfo != null)
					{
						JOptionPane.showMessageDialog(new Frame(), "Race already exists, remove or modify it instead.");
						return;
					}
					String unitArr = JOptionPane.showInputDialog(new Frame(), "Defense of units?", "0 3 7 4 0 0 0");
					String tavName = JOptionPane.showInputDialog(new Frame(), "Name of tavern?", "tavern");
          if(tavName != null && unitArr != null)
					{
						races.put(name, name+"|"+unitArr+"|"+tavName);
         	 	writeFile(races);
        	}
				}
			}   
		}); 

		remove = new JButton("Remove Race");             
		remove.addActionListener(new ActionListener(){
			public void actionPerformed(ActionEvent e){ 
				//Load Races
				HashMap<String, String> races = loadRaces();
				//Show options
				String[] options = new String[races.size()];
				int i = 0;
				for(String s: races.keySet())
				{
					options[i] = s;
					i++;
				}
				Arrays.sort(options);
				String selected = (String) (JOptionPane.showInputDialog(new Frame(), "Select a race to remove.", "" , 1, null, options, options[0]));

				if(selected == null)
				{
					//do nothing
				}else
				{
					//Delete race
					//overwrite file
					removeRace(selected, races);
					writeFile(races);
				}
			}   
		});

		modify = new JButton("Modify Race");               
		modify.addActionListener(new ActionListener(){
			public void actionPerformed(ActionEvent e){ 
				//Load Race
				HashMap<String, String> races = loadRaces();	
				String[] options = new String[races.size()];
        int i = 0;
        for(String s: races.keySet())
        {
          options[i] = s;
          i++;
        }
				Arrays.sort(options);
        String selected = (String) (JOptionPane.showInputDialog(new Frame(), "Select race to modify.", "" , 1, null, options, options[0]));
          
				if(selected == null || selected.equals("Cancel"))
        {
          //do nothing
        }else
        {
          //Prompt new unit options and tavern names
          //overwrite file
					String raceInfo = races.get(selected);
					String unitArr = JOptionPane.showInputDialog(new Frame(), "Defense of units?", raceInfo.split("\\|")[1]);
					String tavName = JOptionPane.showInputDialog(new Frame(), "Tavern name?", raceInfo.split("\\|")[2]);
					if(tavName != null && unitArr != null)
					{
						races.put(selected, selected+"|"+unitArr+"|"+tavName);
						writeFile(races);
					}
        }
			}   
		}); 


		JPanel buttonPanel = new JPanel();
		buttonPanel.setLayout(new FlowLayout());
		buttonPanel.add(add);
		buttonPanel.add(remove);
		buttonPanel.add(modify);
		getContentPane().setLayout(new FlowLayout());
		getContentPane().add(buttonPanel);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
	}

	public HashMap<String, String> loadRaces()
	{
		HashMap<String, String> races = new HashMap();
		try{
			Scanner sc = new Scanner(new File("stats.txt"));
			while(sc.hasNextLine())
			{
				String race = sc.nextLine();
				Pattern p = Pattern.compile("(.*)\\|(.*)\\|(.*)");
				Matcher m = p.matcher(race);
				if(m.find())
					races.put(m.group(1), race);
			}
			sc.close();
		}catch(Exception e){}
		return races;
	}

	public void writeFile(HashMap<String, String> races)
	{
		try
		{
			PrintStream ps = new PrintStream(new File("stats.txt"));
			for(String s: races.keySet())
			{
				ps.println(races.get(s));
			}
			ps.close();
		}catch(Exception e){}
	}

	public String formatName(String name)
	{
		//First letter of every word is capitalized
		//Words are concatted
		String[] comps = name.split("\\s");
		for(int i = 0; i < comps.length; i++)
		{
			comps[i] = comps[i].substring(0,1).toUpperCase() + comps[i].substring(1, comps[i].length()).toLowerCase();
		}
		String ret = "";
		for(int i = 0; i < comps.length; i++)
			ret = ret+comps[i];
		return ret;
	}

	public void removeRace(String name, HashMap<String, String> races)
	{
		races.remove(name);
	}
}
