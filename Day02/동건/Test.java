import java.awt.*;
import java.awt.event.*;
import javax.swing.*;
import java.net.*;

class Test extends JFrame
{
	Container cp;
	JLabel laImg;
	ImageIcon ii1, ii2, ii3, ii4;
	JPanel p3;
	JLabel laImg1,laImg2,laImg3,laImg4;
	JButton b1,b2,b3,b4,b5,b6,b7,b8;

	void init(){
		cp = getContentPane();
		b1 = new JButton("��");
		b2 = new JButton("��");
		b3 = new JButton("��");
		b4 = new JButton("��");
		b1.addActionListener(e ->{
			JOptionPane.showMessageDialog(
				null, ii1, "�޼���",JOptionPane.INFORMATION_MESSAGE );
		});
		b2.addActionListener(e ->{
			String[] choice = {"Good", "Bad", "Normal"};
			JOptionPane.showInputDialog(
				null,"���� ��� �?", "����", JOptionPane.INFORMATION_MESSAGE,ii2, choice, choice[0]);
		});
		b3.addActionListener(e -> {
			int choi = JOptionPane.showConfirmDialog(
				null,"�����ұ��?","����",JOptionPane.YES_NO_OPTION, 
				JOptionPane.QUESTION_MESSAGE, ii3);
			if(choi == JOptionPane.YES_OPTION){
				System.exit(-1);
			}else{
			}
		});
		b4.addActionListener(e-> {
			JOptionPane.showMessageDialog(
				null, "�޼��� ����", "����", JOptionPane.INFORMATION_MESSAGE, ii4);
		});
		cp.add(b4, BorderLayout.NORTH);
		cp.add(b3, BorderLayout.SOUTH);
		cp.add(b2, BorderLayout.WEST);
		cp.add(b1, BorderLayout.EAST);


		p3=new JPanel(new GridLayout(2,2));
		ii1= new ImageIcon(getClass().getResource("imgs/p_girl.png")); //�̹��� ������.
		ii2= new ImageIcon(getClass().getResource("imgs/p_hello.png"));
		ii3= new ImageIcon(getClass().getResource("imgs/p_play.png"));
		ii4= new ImageIcon(getClass().getResource("imgs/p_study.png"));
		
	/*	laImg1 = new JLabel(ii1);
		laImg2 = new JLabel(ii2);
		laImg3 = new JLabel(ii3);
		laImg4 = new JLabel(ii4); */
		
		b5 = new JButton(); b5.setIcon(ii1);
		b6 = new JButton(); b6.setIcon(ii2);
		b7 = new JButton(); b7.setIcon(ii3);
		b8 = new JButton(); b8.setIcon(ii4);

		b5.addActionListener(e ->{
			JOptionPane.showMessageDialog(
				null, ii1, "�޼���",JOptionPane.INFORMATION_MESSAGE );
			});
		b6.addActionListener(e ->{
			String[] choice = {"Good", "Bad", "Normal"};
			JOptionPane.showInputDialog(
				null,"���� ��� �?", "����", JOptionPane.INFORMATION_MESSAGE,ii2, choice, choice[0]);
			});
		b7.addActionListener(e -> {
				int choi = JOptionPane.showConfirmDialog(
					null,"�����ұ��?","����",JOptionPane.YES_NO_OPTION, 
					JOptionPane.QUESTION_MESSAGE, ii3);
				if(choi == JOptionPane.YES_OPTION){
					System.exit(-1);
				}else{
				}
			});
		b8.addActionListener(e-> {
			JOptionPane.showMessageDialog(
				null, "�޼��� ����", "����", JOptionPane.INFORMATION_MESSAGE, ii4);
			});

		p3.add(b5);
		p3.add(b6);
		p3.add(b7);
		p3.add(b8);
		cp.add(p3, BorderLayout.CENTER);

		
		setUI();
	}

	void setUI(){
		setTitle("test");
		//pack();
		setSize(800,450);  //cp ũ��
		setVisible(true);
		setLocation(500,500); // ������� ��ġ

		setResizable(false);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

	}




	public static void main(String[] args) 
	{
		Test t = new Test();
		t.init();
	}
}
