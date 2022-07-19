import java.sql.*;

class A
{
	void init(){
		// 1. 드라이버 로딩
		try{
			Class.forName("oracle.jdbc.driver.OracleDriver");
			pln("드라이버 로딩 성공");
		}catch(ClassNotFoundException cnfe){
		}
		
		// 2. Connection 생성
		String url="jdbc:oracle:thin:@localhost:1521:JAVA";
		Connection con = null;
		try{
			con= DriverManager.getConnection(url,"scott","tiger");
			pln("Connection 생성 성공");
		}catch(SQLException se){}

		// 3. Statement생성 sqlplus에서 실행시켜주는역할 엔터역할
		// (  SQL>                     ;(엔터)  ) 이역할 이라고생각

		Statement stmt = null;
		try{
			stmt = con.createStatement();
			pln("stmt 생성 성공");
		}catch(SQLException se){}

		// 4. SQL 작성 / 전송 / 실행   -> B C D

		// 5.연결 객체 닫기

		try{
			stmt.close();
			con.close();
			pln("close 성공");
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
