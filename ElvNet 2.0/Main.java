import java.util.Scanner;
import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.util.ArrayList;
import java.util.TreeMap;
import java.io.*;

public class Main extends JFrame
{
	private DataManager dm;
	private JButton calc;
	private TextArea input, output;
	private PrintStream out;
	
	public Main()
	{
		super("Elveron - Change in networth");
		setSize(500, 500);
		setVisible(true);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		dm = new DataManager();
		
		calc = new JButton("Calculate Change");       
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
		try{out = new PrintStream(new File("output.txt"));}catch(IOException ioe){System.out.println("IOException");}
		output.setText("");
		Scanner s = new Scanner(input.getText());

		while(s.hasNextLine())
		{
			String rawKd = s.nextLine();
			try
			{
				Kingdom k = new Kingdom(rawKd);
				dm.importData(k);
			}
			catch(Exception e)
			{
				System.out.println("Invalid input: " + rawKd);
			}
		}
		
		ArrayList<String> data = dm.getData();
		for(int i = 0; i < data.size(); i++)
		{
			out.println(data.get(i));
			output.append(data.get(i) + "\n");
		}
		dm.writeFile();
		out.close();
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