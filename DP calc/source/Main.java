import java.util.*;
import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.io.*;

public class Main extends JFrame
{
	public static void main(String args[]) throws Exception
	{
		File dir1 = new File (".");
		System.out.println ("Current dir : " + dir1.getCanonicalPath());
		new Main();
	}
	
	public static void sortRaces() throws Exception
	{
		Scanner sc = new Scanner(new File("raceList.txt"));
		ArrayList<String> arr = new ArrayList();
		while(sc.hasNextLine())
		{
			arr.add(sc.nextLine());
		}
		Collections.sort(arr);
		PrintStream ps = new PrintStream(new File("raceList.txt"));
		for(String s: arr)
			ps.println(s);
	}
	
	private Parser p;
	private JButton calc;
	private JButton kyl;
	private JButton lsd;
	private JButton shield;
	private JButton restart;
	private TextArea input;
	private JPanel output;
	private JPanel topPanel;
	private JPanel opsPanel;
	private JLabel[] opsLabels;
	
	public Main() throws Exception
	{
		super("Elveron - DP Calc");
		setSize(1000, 800);
		setVisible(true);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		p = new Parser();
		
		kyl = new JButton("Toggle Kyl/Dina (6%)");       
        kyl.addActionListener(new ActionListener(){
			public void actionPerformed(ActionEvent e){
				p.toggleHero(Races.KYL);
				addTables();
			}
		});
		
		lsd = new JButton("Toggle LSD/Shade (5%)");       
        lsd.addActionListener(new ActionListener(){
			public void actionPerformed(ActionEvent e){
				p.toggleHero(Races.LSD);
				addTables();
			}
		});
		
		shield = new JButton("Toggle Magic Shield (5%)");       
        shield.addActionListener(new ActionListener(){
			public void actionPerformed(ActionEvent e){
				p.toggleShield();
				addTables();
			}
		});
		
		calc = new JButton("CALCULATE DP");
        calc.addActionListener(new ActionListener(){
			public void actionPerformed(ActionEvent e){
				calculate();
				addTables();
			}
		});
		
		restart = new JButton("New Calc");       
        restart.addActionListener(new ActionListener(){
			public void actionPerformed(ActionEvent e){
				try{
				restart();}
				catch(Exception ee){}
			}
		});
		
		input = new TextArea("", 20, 50, TextArea.SCROLLBARS_BOTH);
		initiate();
		validate();
	}
	
	public void restart() throws Exception
	{
		p = new Parser();
		getContentPane().removeAll();
		initiate();
		validate();
	}
	public void addTables()
	{
		if(p.isInit())
		{
			//rewrite the output based on new input
			JPanel outputPanel = new JPanel();
			outputPanel.setLayout(new BorderLayout());
			JTable unitData = p.unitData();
			JTable modData = p.modData();
			JTable totalData = p.totalData();
			outputPanel.add(new JScrollPane(unitData), BorderLayout.LINE_START);
			outputPanel.add(new JScrollPane(modData), BorderLayout.CENTER);
			if(output != null)
				getContentPane().remove(output);
			output = outputPanel;
			getContentPane().add(output);
			
			//rework the output on the top panel
			topPanel.removeAll();
			topPanel.add(input);
			topPanel.add(new JScrollPane(totalData), BorderLayout.LINE_END);
			
			//update the op required
			for(int i = 0; i < opsLabels.length; i++)
			{
				if(p.isOpRequired(i))
				{
					opsLabels[i].setForeground(Color.RED);
				}
				else
					opsLabels[i].setForeground(Color.GREEN);
			}
		}
		validate();
	}
	
	public void calculate()
	{
		try{
			if(!p.addOp(input.getText()))
				return;
			input.setText("");
		}catch(Exception ex){ex.printStackTrace();}
	}
	
	public void initiate()
	{
		Container pane = getContentPane();
		pane.setLayout(new BoxLayout(pane, BoxLayout.PAGE_AXIS));
		
		topPanel = new JPanel();
		topPanel.setLayout(new FlowLayout());
		topPanel.add(input);
		
		opsLabels = new JLabel[5];
		opsLabels[Races.MILITARY] = new JLabel("Military Spy");
		opsLabels[Races.LAND] = new JLabel("Land Spy");
		opsLabels[Races.IMPROVEMENT] = new JLabel("Improvement Spy");
		opsLabels[Races.FARSIGHT] = new JLabel("Farsight");
		opsLabels[Races.BUILDING] = new JLabel("Building Spy");
		opsPanel = new JPanel();
		opsPanel.setLayout(new GridLayout(5, 1));
		
		for(int i = 0; i < opsLabels.length; i++)
		{
			opsPanel.add(opsLabels[i]);
		}
		
		
		JPanel buttonPanel = new JPanel();
		buttonPanel.setLayout(new FlowLayout());
		buttonPanel.add(opsPanel);
		buttonPanel.add(lsd);
		buttonPanel.add(kyl);
		buttonPanel.add(shield);
		buttonPanel.add(restart);
		buttonPanel.add(calc);
		
		Label temp;
		temp = new Label("Input");
		temp.setComponentOrientation(ComponentOrientation.LEFT_TO_RIGHT);
		pane.add(temp);
		pane.add(topPanel);
		
		pane.add(buttonPanel);
		temp = new Label("Output");
		temp.setComponentOrientation(ComponentOrientation.LEFT_TO_RIGHT);
		pane.add(temp);
		
		
	}
}