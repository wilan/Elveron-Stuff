import java.util.*;
import javax.swing.*;
import java.awt.*;
import java.io.*;
import java.util.regex.Pattern;
import java.util.regex.Matcher;

public class StatChange
{
	public static void main(String args[]) throws Exception
	{
    Scanner s = new Scanner(new File("source/racelist.txt"));
    TreeSet<String> races = new TreeSet();

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
		String name = (String) (JOptionPane.showInputDialog(new Frame(), "Name of race?", "" , 1, null, racesArr, racesArr[0]));

		if(name == null)
			System.exit(0);
		Scanner sc = new Scanner(new File("./source/"+name+".java"));
		String write = "";
		//find the array
		while(sc.hasNextLine())
		{
			String line = sc.nextLine();
			Pattern p = Pattern.compile("(\\{.*\\})");
			Matcher m = p.matcher(line);
			if(m.find())
			{
				String unitArr = m.group(1);
				String newArr = JOptionPane.showInputDialog(new Frame(), "Edit units", unitArr);
				if(newArr == null)
					newArr = unitArr;
				line = line.replaceAll(p.toString(), newArr);	
			}
			write = write + line + "\n";
		}
		sc.close();
		PrintStream ps = new PrintStream(new File("./source/"+name+".java"));
		ps.println(write);
		System.exit(0);
	}
}
