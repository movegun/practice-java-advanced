import java.sql.*;
class D 
{
	String url = "jdbc:oracle:thin:@localhost:1521:JAVA";
	Connection con;
	CallableStatement cstmt;
	String sql = "call incre(?,?)";

	D(){
		try{
			Class.forName("oracle.jdbc.driver.OracleDriver");
			con = DriverManager.getConnection(url,"scott","tiger");
			cstmt = con.prepareCall(sql);
		}catch(ClassNotFoundException cnfe){
		}catch(SQLException se){
		}
	}

	void call(int empno, float rate){
		try{
			cstmt.setInt(1, empno);
			cstmt.setFloat(2, rate);
			cstmt.execute();
		}catch(SQLException se){
		}finally{
			try{
				cstmt.close();
				con.close();
			}catch(SQLException se){
			}
		}
	}

	public static void main(String[] args) 
	{
		D d = new D();
		d.call(7839, 0.5f);
	}
}
