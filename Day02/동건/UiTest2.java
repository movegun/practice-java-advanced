import java.awt.*;
import java.awt.event.*;
import javax.swing.*;
import java.net.*;
import java.io.*;

class UiTest2 extends JFrame  
{
	Container cp;
	JFileChooser fc = new JFileChooser(".");
	JButton open, save, clear;
	JPanel p;
	JTextPane text;
	JScrollPane scroll;
	String line;
	File file;
	FileReader fr;
	FileWriter fw;
	BufferedReader br;


	void init(){
		cp = getContentPane();
		text = new JTextPane();
		scroll = new JScrollPane(text);
		cp.add(scroll);
		clear = new JButton("clear");
		clear.addActionListener(new clear());
		cp.add(clear, BorderLayout.NORTH);
		open = new JButton("open");
		open.addActionListener(new open());
		save = new JButton("save");
		save.addActionListener(new save());
		p = new JPanel();
		p.setLayout(new GridLayout(1,2));
		p.add(open); p.add(save);
		cp.add(p, BorderLayout.SOUTH);

		setUI();
	}
	void setUI(){
		setTitle("JFileChooser Test");
		setSize(400, 300);
		setVisible(true);
		setLocation(200, 100);

		setResizable(false);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
	}


	class clear	implements ActionListener{
		public void actionPerformed(ActionEvent ae){
			text.setText("");
		}
	}

	class open implements ActionListener{
		public void actionPerformed(ActionEvent ae){
			int i = fc.showOpenDialog(open);
			switch(i){
				case JFileChooser.CANCEL_OPTION: 
					JOptionPane.showMessageDialog(null, "Cancel", "Cancel", JOptionPane.INFORMATION_MESSAGE);
					break;
				case JFileChooser.APPROVE_OPTION:
				try{	
					text.setText("");
					file = fc.getSelectedFile();
					fr = new FileReader(file);
					br = new BufferedReader(fr);
					StringBuffer sb = new StringBuffer();
					while ((line = br.readLine()) != null){
						sb.append(line+"\n");
					}
					text.setText(sb.toString());
				}catch (IOException ie){
					System.out.println(ie);
				}
					System.out.println("APPROVE");
					break;
				case JFileChooser.ERROR_OPTION: 
					JOptionPane.showMessageDialog(null, "ERROR 발생", "Warning", JOptionPane.WARNING_MESSAGE);
					break;
			}
		}
	}

	class save implements ActionListener{
		public void actionPerformed(ActionEvent ae){
			int i = fc.showSaveDialog(save);
			switch(i){
				case JFileChooser.CANCEL_OPTION: 
					JOptionPane.showMessageDialog(null, "Cancel", "Cancel", JOptionPane.INFORMATION_MESSAGE);
					break;
				case JFileChooser.APPROVE_OPTION: 
					try{
						file = fc.getSelectedFile();
						fw = new FileWriter(file);
						String fname = file.getName();
						fw.write(text.getText());
						fw.flush();
						JOptionPane.showMessageDialog(null, fname+" Saved", "Save", JOptionPane.INFORMATION_MESSAGE);
					}catch (IOException ie){
						System.out.println(ie);
					}
					break;
				case JFileChooser.ERROR_OPTION: 
					JOptionPane.showMessageDialog(null, "ERROR 발생", "Warning", JOptionPane.WARNING_MESSAGE);
					break;
			
			}
		}
	}

	public static void main(String[] args) {
		UiTest2 u = new UiTest2();
		u.init();
	}
}