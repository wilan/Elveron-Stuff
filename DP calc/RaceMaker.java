import java.util.*;
import javax.swing.*;
import java.awt.*;
import java.io.*;

public class RaceMaker
{
	public static void main(String args[]) throws Exception
	{
		//ask for race name
		//ask for unit array
		//ask for tavern name
		String name = JOptionPane.showInputDialog(new Frame(), "What is the name of the race?");
		String unitArr = JOptionPane.showInputDialog(new Frame(), "Defense of units?", "{0,3,7,4,0,0,0}");
		String tavName = JOptionPane.showInputDialog(new Frame(), "Name of tavern?", "Tavern");
		
		if(name != null && unitArr != null && tavName != null)
		{
			Scanner sc = new Scanner(new File("racetemplate"));
			PrintStream ps = new PrintStream(new File("./source/name"+".java"));
			
			while(sc.hasNextLine())
			{
				String print = sc.nextLine();
				print = print.replaceAll("#name",name);
				print = print.replaceAll("#unitArr",unitArr);
				print = print.replaceAll("#tavName",tavName);
				ps.println(print);
			}
			ps.close();
			
			FileWriter fstream = new FileWriter("./source/racelist.txt",true);
			BufferedWriter out = new BufferedWriter(fstream);
			out.write(name);
			out.write("\r\n");
			out.close();
		}
		System.exit(0);
	}
}
