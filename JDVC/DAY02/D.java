import java.awt.*;
import java.awt.event.*;
import javax.swing.*;

import java.util.*; // Vector

class D extends JFrame
{
	JTable t;
	/*
	Object[] columnNames = {"번호","이름","날짜"};
	Object[][] rowData = {
		{10,"홍길동","22/06/01"},
		{20,"이동건","22/06/02"},
		{30,"김민수","22/06/03"},
		{40,"강호동","22/06/04"},		
		{20,"이동건","22/06/02"},
		{30,"김민수","22/06/03"},
		{40,"강호동","22/06/04"},	
		{20,"이동건","22/06/02"},
		{30,"김민수","22/06/03"},
		{40,"강호동","22/06/04"},	
		{30,"김민수","22/06/03"},
		{40,"강호동","22/06/04"},	
		{20,"이동건","22/06/02"},
		{30,"김민수","22/06/03"},
		{40,"강호동","22/06/04"},	
		{20,"이동건","22/06/02"},
		{30,"김민수","22/06/03"},
		{40,"강호동","22/06/04"},	
		{20,"이동건","22/06/02"},
		{30,"김민수","22/06/03"},
		{40,"강호동","22/06/04"}
	};*/

	Vector<String> columnNames;
	Vector<Vector<String>> rowData;

	D(){
		columnNames = new Vector<String>();
		columnNames.add("번호");
		columnNames.add("이름");
		columnNames.add("날짜");

		rowData= new Vector<Vector<String>>();
		Vector v1 = new Vector();
		v1.add("10"); v1.add("홍길동"); v1.add("22/06/01");
		Vector v2 = new Vector();
		v2.add("20"); v2.add("이동건"); v2.add("22/06/02");
		Vector v3 = new Vector();
		v3.add("30"); v3.add("김민수"); v3.add("22/06/03"); 
		Vector v4 = new Vector();
		v4.add("40"); v4.add("강호동"); v4.add("22/06/04");

		rowData.add(v1);rowData.add(v2);rowData.add(v3);rowData.add(v4);

		init();
	}

	void init(){
		Container cp = getContentPane();
		t = new JTable(rowData,columnNames);
		JScrollPane jsp = new JScrollPane(t);
		cp.add(jsp);

		setUI();
		test();
	}

	void test(){

		t.setValueAt("게이츠",0,1);
		Object data = t.getValueAt(2,1);
		System.out.println("data : "+data);

		int rc = t.getRowCount();
		int cc = t.getColumnCount();
		System.out.println(+rc+"행"+cc+"열");

		String cn = t.getColumnName(0);
		System.out.println("cn : "+cn);


	}

	void setUI(){
		setTitle("JTable Test1");
		setSize(500,200);
		setVisible(true);
		setLocationRelativeTo(null); // 해상도  상관없이 화면의 가운데 띄움
		setResizable(false);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
	}

	public static void main(String[] args) 
	{
		new D();
	}
}
