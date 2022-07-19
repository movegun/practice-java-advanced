import java.sql.*;

class B
{
	String url = "jdbc:oracle:thin:@localhost:1521:JAVA";
	Connection con;
	Statement stmt;

	String sql = "select * from TEST_TABLE order by 1 desc";

	B(){
		try{
			Class.forName("oracle.jdbc.driver.OracleDriver");
			con = DriverManager.getConnection(url,"scott","tiger");
			stmt = con.createStatement(ResultSet.TYPE_SCROLL_SENSITIVE,ResultSet.CONCUR_UPDATABLE);

			createRs();
		}catch(ClassNotFoundException snfe){
		}catch(SQLException se){
		}finally{
			try{
				stmt.close();
				con.close();
			}catch(SQLException se){}
		}
	}

	void createRs(){
		ResultSet rs = null;
		try{
			rs = stmt.executeQuery(sql);
			ResultSetMetaData rsmd = rs.getMetaData();
			int cc = rsmd.getColumnCount();

			for (int i=1 ;i<=cc ;i++ ){
				String cn = rsmd.getColumnName(i);
				System.out.print(cn+"	");
			}

			System.out.println("");

			for (int j=1; j<=cc ;j++ )System.out.print("------------");

			System.out.println("");
			
			while (rs.next()){				
				for (int i=1;i<=cc ;i++ ){					
					String str = rs.getString(i);
					System.out.print(str+"	");
				}
				System.out.println("");
			}
		}catch(SQLException se){
			System.out.println("SQL오류"+se);
		}finally{
			try{
				if (rs!=null) rs.close();
			}catch(SQLException se){
				System.out.println("close오류");
			}
		}
	}

	void forward(ResultSet rs){
		try{
			System.out.println("<<순방향>>");
			while (rs.next()){
				int no = rs.getInt("NO");
				String name = rs.getString("NAME");
				Date rdate = rs.getDate("RDATE");

				System.out.println(no+"	"+name+"	"+rdate);
			}
		}catch(SQLException se){
			System.out.println(se);
		}
	}

	void backward(ResultSet rs){
		try{
			System.out.println("<<역방향>>");
			while (rs.previous()){
				int no = rs.getInt("NO");
				String name = rs.getString("NAME");
				Date rdate = rs.getDate("RDATE");

				System.out.println(no+"	"+name+"	"+rdate);
			}
		}catch(SQLException se){
			System.out.println(se);
		}
	}

	public static void main(String[] args) 
	{
		new B();		
	}
}
