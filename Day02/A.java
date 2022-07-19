import java.awt.*;
import java.awt.event.*;
import javax.swing.*;
import java.net.*;

class A extends JFrame {
	Container cp;
	ImageIcon ii;
	JLabel la;
	JPanel p;

	ImageIcon ii1, ii2, ii3;
	JLabel la1, la2, la3;
	JButton b;
	
	void init(){
		cp = getContentPane();

		/*
		String fname = "imgs/puppy.gif";
		Class c = getClass();
		URL url = c.getResource(fname);
		ii = new ImageIcon(url); //ImageIcon(URL location)*/
		ii = new ImageIcon(getClass().getResource("imgs/puppy.gif"));
		//la = new JLabel(ii);
		b = new JButton(ii);
		ActionListener listener = (e) -> {
			int answer = JOptionPane.showConfirmDialog(
				null, "재밌나요?", "질문", JOptionPane.YES_NO_CANCEL_OPTION, 
					JOptionPane.QUESTION_MESSAGE, ii2);
			//System.out.println("answer: " + answer);

			if(answer == JOptionPane.YES_OPTION){
				JOptionPane.showMessageDialog(null, "재밌군요. 다행이네요 ㅎㅎ");
			}else if(answer == JOptionPane.NO_OPTION){	
				JOptionPane.showMessageDialog(null, "노잼 ㅠㅠ");
			}else{
				JOptionPane.showMessageDialog(null, "아쉽네요. 대답이 없으시군요");
			}
		};
		b.addActionListener(listener);
		//cp.add(la);
		cp.add(b);

		p = new JPanel(new GridLayout(1, 3));
		/*Color color = new Color(24, 134, 237); //RGB
		p.setBackground(color);*/
		p.setBackground(Color.WHITE);
		cp.add(p, BorderLayout.NORTH);
		ii1 = new ImageIcon(getClass().getResource("imgs/p_hello.png"));
		ii2 = new ImageIcon(getClass().getResource("imgs/p_play.png"));
		ii3 = new ImageIcon(getClass().getResource("imgs/p_study.png"));
		la1 = new JLabel(ii1);
		la2 = new JLabel(ii2);
		la3 = new JLabel(ii3);
		p.add(la1);
		p.add(la2);
		p.add(la3);
		
		setUI();
	}
	void setUI(){
		setTitle("Image Test");
		//setSize(300, 300);
		pack();
		setVisible(true);
		setLocation(200, 100);

		setResizable(false);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
	}
	public static void main(String[] args) {
		A a = new A();
		a.init();
	}
}
