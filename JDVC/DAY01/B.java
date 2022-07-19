import java.sql.*;

class B // Statement
{
	String url = "jdbc:oracle:thin:@localhost:1521:JAVA";
	Connection con;
	Statement stmt;

	B(){
		try{
			Class.forName("oracle.jdbc.driver.OracleDriver");
			con = DriverManager.getConnection(url,"scott","tiger");
			stmt = con.createStatement();
		}catch(ClassNotFoundException cnfe){
		}catch(SQLException se){
		}



		dropT();

		createTnoExist();


/*		
		createT();
		
		insertD(10,"ȫ�浿");
		insertD(20,"�̵���");
		insertD(30,"��μ�");

		updateD(10,"��ȣ��");
		deleteD(10);
		selectD();

		closeAll();*/

	}

	void createT(){
		String tname = "TEST_TABLE";
		try{
			String sql = "create table "+tname+"(NO number(2) primary key, NAME varchar2(10), RDATE date)";
			stmt.execute(sql);
			pln("��������");
		}catch(SQLException se){
			pln("����" + se);
		}
	}

	void createTnoExist(){
		String tname = "TEST_TABLE";
		String sql = "select TABLE_NAME from user_tables where TABLE_NAME='"+tname+"'";
		ResultSet rs = null;
		try{
			rs = stmt.executeQuery(sql);
			if(!rs.next()){
				createT();
				pln("createTnoExist ����");
			}else pln("createTnoExist ����");
		}catch(SQLException se){
			pln("createTnoExist ����"+ se);
		}
	}

	void dropT(){
		String tname = "TEST_TABLE";
		try{
			String sql = "drop table "+tname;
			stmt.execute(sql);
			pln(tname+"�� drop ����");
		}catch(SQLException se){
			pln(tname+"�� drop ���� ���� : "+se);
		}			
	}

	void insertD(int no, String name){
		String tname = "TEST_TABLE";
		String sql = "insert into "+tname+" values("+no+",'"+name+"', SYSDATE)";
		try{
			stmt.executeUpdate(sql);
			pln("insert ����");
		}catch(SQLException se){
			pln("���ܹ߻�"+se);
		}
	}

	void updateD(int no,String name){
		String tname = "TEST_TABLE";
		String sql = "update "+tname+" set name='"+name+"' where NO="+no;
		try{
			stmt.executeUpdate(sql);
			pln("update ����");
		}catch(SQLException se){
			pln("update ����" + se);
		}
	}

	void deleteD(int no){
		String tname = "TEST_TABLE";
		String sql = "delete from "+tname+" where NO="+no;
		try{
			stmt.executeUpdate(sql);
			pln("delete ����");
		}catch(SQLException se){
			pln("delete ����"+ se);
		}
	}

	void selectD(){
		String tname="TEST_TABLE";
		String sql = "select * from "+tname;
		ResultSet rs = null;					//** ResultSet �� ǥó�� ���� �� �˻��ؼ� �ٽú���
		try{
			rs = stmt.executeQuery(sql);
			while(rs.next()){
				int no = rs.getInt(1);
				String name = rs.getString(2);
				Date rdate = rs.getDate(3);

				pln(no+"	"+name+"	"+rdate);
			}

		}catch(SQLException se){
			pln("selectD ����"+se);
		}finally{
			try{
				if(rs != null) rs.close();
			}catch(SQLException se){
			}
		}
	}

	void closeAll(){
		try{
			stmt.close();
			con.close();
		}catch(SQLException se){
		}
	}

	void pln(String str){
		System.out.println(str);
	}

	public static void main(String[] args) 

	{
		new B();
	}
}

