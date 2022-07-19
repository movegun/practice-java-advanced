import java.awt.*;
import java.awt.event.*;
import javax.swing.*;
import java.net.*;

class B extends JFrame
{
	Container cp;
	JLabel laImg;
	ImageIcon ii;
	JPanel p;
	JLabel laMsg1, laMsg2;

	void init(){
		cp = getContentPane();
		ii = new ImageIcon(getClass().getResource("imgs/move.gif"));
		laImg = new JLabel(ii);
		cp.add(laImg);

		p = new JPanel(new GridLayout(2,1));
		laMsg1 = new JLabel();
		laMsg1.setForeground(Color.blue);	
		laMsg1.setText(" 열심히 달리는 것은 중요하지요");
		laMsg2 = new JLabel();
		laMsg2.setForeground(Color.red);	
		laMsg2.setText(" 하지만 방향은 더 중요할 수 있지요");
		p.add(laMsg1);
		p.add(laMsg2);
		cp.add(p, BorderLayout.SOUTH);

		Font f1 = new Font("굴림", Font.BOLD, 24);
		laMsg1.setFont(f1);
		Font f2 = new Font("궁서체", Font.PLAIN, 20);
		laMsg2.setFont(f2);

		setUI();
	}
	void setUI(){
		setTitle("Font Test");
		//setSize(300, 300);
		pack();
		setVisible(true);
		setLocation(200, 100);

		setResizable(false);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
	}
	public static void main(String[] args) {
		B b = new B();
		b.init();
	}
}
