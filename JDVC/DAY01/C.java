import java.sql.*;

class C
{
	String url = "jdbc:oracle:thin:@localhost:1521:JAVA";
	Connection con;
	PreparedStatement pstmt1, pstmt2, pstmt3;
	String sql1 = "insert into TEST_TABLE values(?,?,SYSDATE)";
	String sql2 = "select * from TEST_TABLE order by 1 desc";
	String sql3 = "select * from TEST_TABLE where NAME like ?";

	C(){
		try{
			Class.forName("oracle.jdbc.driver.OracleDriver");
			con = DriverManager.getConnection(url,"scott","tiger");
			pstmt1 = con.prepareStatement(sql1);
			pstmt2 = con.prepareStatement(sql2);
			pstmt3 = con.prepareStatement(sql3);
		}catch(ClassNotFoundException cnfe){
		}catch(SQLException se){
		}
	}
////////////////////////////////////////////////////////////////////
	void insertD(int no, String name){
		try{
			pstmt1.setInt(1, no);
			pstmt1.setString(2, name);

		}catch(SQLException se){
			pln("insertD() 실패"+se);
		}
	}
////////////////////////////////////////////////////////////////////
	void selectD(String na){
		ResultSet rs = null;
		try{
			na = "%"+na+"%";
			pstmt3.setString(1, na);
			rs = pstmt3.executeQuery();
			while (rs.next()){
				int no = rs.getInt(1);
				String name = rs.getString(2);
				Date rdate = rs.getDate(3);
				pln(no+"	"+name+"	"+rdate);
			}

		}catch(SQLException se){
			pln("selectD 실패"+ se);
		}finally{
			try{
				if (rs != null)rs.close();
			}catch(SQLException se){
				pln("rs.close() 실패"+se);
			}
		}
	}

	void pln(String str){
		System.out.println(str);
	}

	public static void main(String[] args) 
	{
		C c = new C();
		c.selectD("동");
	}
}
