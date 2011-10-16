import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.util.*;
import java.io.*;
import java.util.regex.*;

public class Main extends JFrame
{
	private DataManager dm;
	private JButton calc;
	private TextArea input, output;
	private PrintStream out;
	
	public Main()
	{
		super("Hero Op Calc");
		setSize(500, 500);
		setVisible(true);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		dm = new DataManager();
		
		calc = new JButton("Calculate Op");       
        calc.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e){
				calculate();
			}
		});
		
		input = new TextArea("", 12, 20, TextArea.SCROLLBARS_BOTH);
		output = new TextArea("", 12, 20, TextArea.SCROLLBARS_BOTH);
		output.setEditable(false);
		initiate();
		validate();
	}
	
	public void initiate()
	{
		Container pane = getContentPane();
		pane.setLayout(new BoxLayout(pane, BoxLayout.PAGE_AXIS));
		
		Label temp;
		temp = new Label("Input");
		temp.setComponentOrientation(ComponentOrientation.LEFT_TO_RIGHT);
		pane.add(temp);
		pane.add(input);
		
		pane.add(calc);
		temp = new Label("Output");
		temp.setComponentOrientation(ComponentOrientation.LEFT_TO_RIGHT);
		pane.add(temp);
		pane.add(output);
	}
	
	public void calculate()
	{
		String result = parse(input.getText());
		output.setText(result);
	}
	
	public String parse(String input)
	{
		int ruler = 0; //ruler
		int heroes = 0; //heroes NOT in quest
		int quest = 0; //heroes in quest
		int total = 0; //ruler+heroes+quest
		Scanner sc = new Scanner(input);
		Pattern p = Pattern.compile("Main Hero Overview");
		
		//look for main hero
		while(sc.hasNextLine())
		{
			String l = sc.nextLine();
			Matcher m = p.matcher(l);
			if(m.matches())
			{
				p = Pattern.compile("Attack Power:\\s*(\\d*)");
				break;
			}
		}
		while(sc.hasNextLine())
		{
			String l = sc.nextLine();
			Matcher m = p.matcher(l);
			if(m.matches())
			{
				ruler+= new Integer(m.group(1));
				break;
			}
		}
		
		p = Pattern.compile("Experience:\\s*(\\d*)");
		while(sc.hasNextLine())
		{
			String l = sc.nextLine();
			Matcher m = p.matcher(l);
			if(m.matches())
			{
				int value = new Integer(m.group(1));
				p = Pattern.compile("Status:\\s*(.*)\\s*");
				while(sc.hasNextLine())
				{
					l = sc.nextLine();
					m = p.matcher(l);
					if(m.matches())
					{
						String status = m.group(1);
						if(status.equals("In Realm Service"))
							quest+= value;
						else
							heroes+= value;
							
						p = Pattern.compile("Experience:\\s*(\\d*)");
						break;
					}
				}
			}
		}
		
		total = ruler+heroes+quest;
		String ret = "";
		ret += "Ruler OP: \t" + ruler + "\n";
		ret += "Heroes Op: \t" + heroes + "\n";
		ret += "Quest Op: \t" + quest + "\n";
		ret += "SubTotal Op: \t" + (ruler+heroes) + "\n";
		ret += "Total Op: \t" + total + "\n";
		return ret;
	}
	
	public static void main(String args[])
	{
		test();
		new Main();
	}
	
	//useless test method
	public static void test()
	{
		
	}
	
	public static void println(String s)
	{
		System.out.println(s);
	}
}
