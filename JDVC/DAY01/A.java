import java.sql.*;

class A
{
	void init(){
		// 1. ����̹� �ε�
		try{
			Class.forName("oracle.jdbc.driver.OracleDriver");
			pln("����̹� �ε� ����");
		}catch(ClassNotFoundException cnfe){
		}
		
		// 2. Connection ����
		String url="jdbc:oracle:thin:@localhost:1521:JAVA";
		Connection con = null;
		try{
			con= DriverManager.getConnection(url,"scott","tiger");
			pln("Connection ���� ����");
		}catch(SQLException se){}

		// 3. Statement���� sqlplus���� ��������ִ¿��� ���Ϳ���
		// (  SQL>                     ;(����)  ) �̿��� �̶�����

		Statement stmt = null;
		try{
			stmt = con.createStatement();
			pln("stmt ���� ����");
		}catch(SQLException se){}

		// 4. SQL �ۼ� / ���� / ����   -> B C D

		// 5.���� ��ü �ݱ�

		try{
			stmt.close();
			con.close();
			pln("close ����");
		}catch(SQLException se){}
	}



	void pln(String str){
		System.out.println(str);
	}

	public static void main(String[] args) 
	{
		A a = new A();
		a.init();
	}
}
