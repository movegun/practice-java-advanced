import java.sql.*;

class M
{
	String url = "jdbc:oracle:thin:@localhost:1521:JAVA";
	Connection con;
	Statement stmt;
	M(){
		try{
			Class.forName("oracle.jdbc.driver.OracleDriver");
			con = DriverManager.getConnection(url,"scott","tiger");
			stmt = con.createStatement();
		}catch(ClassNotFoundException cnfe){
		}catch(SQLException se){
		}
		selectDD();
	}
	
	String tname = "JDBCT";


	void createT(){
		String sql = "create table "+tname+"(NO number(2) primary key, NAME varchar2(10), RDATE date)";
		try{
			stmt.execute(sql);
			pln(tname+"���̺� ���� ����");
		}catch(SQLException se){
			pln(tname+"���̺� ���� ����: " + se);
		}
	}

	void createTnoExist(){
		String sql = "create table "+tname+"(NO number(2) primary key, NAME varchar2(10), RDATE date)";
		try{
			stmt.execute(sql);
			pln(tname+"���̺� ���� ����");
		}catch(SQLException se){
		}
	}


	void selectD(){
		String sql = "select TABLE_NAME from user_tables where TABLE_NAME='"+tname+"'";
		ResultSet rs = null;
		try{
			int i = 0;
			rs = stmt.executeQuery(sql);
			pln("��ȣ\t�̸�\t��¥");
			pln("----------------------------");
			while(rs.next()){
				int no = rs.getInt(1);
				String name = rs.getString(2);
				//String rdate = rs.getString(3);
				Date rdate = rs.getDate(3);
				pln(no+"\t"+name+"\t" +rdate);

				i++;
			}
			pln("----------------------------");
			pln("�� " +i+ "�� row�� �˻���");

		}catch(SQLException se){
			pln("selectD() ����: " + se);
		}finally{
			try{
				if(rs != null) rs.close();
			}catch(SQLException se){}
		}
	}
	Boolean selectDD(){
		String sql = "select TABLE_NAME from user_tables where TABLE_NAME='"+tname+"'";
		ResultSet rs = null;
		try{
			int i = 0;
			rs = stmt.executeQuery(sql);
			return rs.next();
			
		}catch(SQLException se){
			pln("selectD() ����: " + se);
		}finally{
			try{
				if(rs != null) rs.close();
			}catch(SQLException se){}
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
		new M();
	}
}
