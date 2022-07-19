import java.awt.*;
import java.awt.event.*;
import javax.swing.*;
import java.net.*;
import java.io.*;

class Test2 extends JFrame
{
	File file;
	FileReader fr;
	FileWriter fw;
	BufferedReader br;
	Container cp;
	JFileChooser fc = new JFileChooser(".");
	JButton open,save,clear;
	JPanel p;
	JTextPane tp;
	JScrollPane sp;
	String line;

	void init(){
		cp = getContentPane();
		
		clear = new JButton("clear");
		clear.addActionListener(new clear());
		cp.add(clear,BorderLayout.NORTH);

		p= new JPanel();
		p.setLayout(new GridLayout(1,2));
		cp.add(p,BorderLayout.SOUTH);
		
		open = new JButton("open");
		open.addActionListener(new open());
		p.add(open);

		save = new JButton("save");
		save.addActionListener(new save());
		p.add(save);

		tp = new JTextPane();
		sp = new JScrollPane(tp);
		cp.add(sp);
		
		setUI();
	}

	class clear	implements ActionListener{
		public void actionPerformed(ActionEvent ae){
			text.setText("");
			}
		}
	
	class open implements ActionListenr{
		public void actionperformed(ActionEvent ae){
			int i = fc.showOpenDialog(open);
			//////////////////////////////////
		}
	}

	



	

	void setUI(){
		setTitle("test2");
		setSize(500,500);
		setVisible(true);
		setLocation(500,500);

		setResizable(false);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
	}







	public static void main(String[] args) 
	{
		Test2 t2 = new Test2();
		t2.init();
	}
}
