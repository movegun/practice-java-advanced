import java.sql.*;
import java.io.*;

class Sqlplus 
{
	//String url = "jdbc:oracle:thin:@localhost:1521:JAVA";
	Connection con;
	Statement stmt;
	String sql,url,ip,sid,id,pwd;
	BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

	void set(){
		try{
			p("Oracle IP : ");
			String ip = br.readLine();
			if (ip.length()==0)ip="127.0.0.1"; this.ip=ip;

			p("Oracle SID : ");
			String sid = br.readLine();
			if (sid.length()==0) sid="JAVA"; this.sid=sid;
			
			p("User ID : ");
			String id = br.readLine();
			if (id.length()==0) id="scott"; this.id=id;

			p("User Password : ");
			String pwd = br.readLine();
			if (pwd.length()==0) pwd="tiger"; this.pwd=pwd;
		}catch(IOException ie){
			p("set() 에러발생 : "+ie);
		}

		pln("세팅 성공!");
	}

	void init(){
		try{
			url = "jdbc:oracle:thin:@localhost:1521:JAVA";
			Class.forName("oracle.jdbc.driver.OracleDriver");
			con = DriverManager.getConnection(url,id,pwd);
			stmt = con.createStatement(ResultSet.TYPE_SCROLL_SENSITIVE, ResultSet.CONCUR_UPDATABLE);
			pln("Oracle 시작!");
		}catch(ClassNotFoundException cnfe){
		}catch(SQLException se){
			p("init() 에러발생 : "+se);
		}
	}

	void sql(){
		try{
			while (true){
				p("SQL>");
				sql = br.readLine();
				sql = sql.trim();

				int idx = sql.indexOf(" ");
				String firstword = sql.substring(0,idx);

				if (firstword.equals("create")){
					createTnoExist(sql);
				}else if (firstword.equals("drop")){
					dropT(sql);
				}else if (firstword.equals("purge")){
					purge(sql);
				}else if (firstword.equals("insert")){
					insertD(sql);
				}else if (firstword.equals("update")){
					updateD(sql);
				}else if (firstword.equals("delete")){
					deleteD(sql);
				}
				else if (firstword.equals("select")){
					selectD(sql);
				}
			}
		}catch(IOException io){}
	}

	void createT(String sql){
		try{
			stmt.execute(sql);
			pln("생성성공");
		}catch(SQLException se){
			pln("다음과 같은 이유로 테이블 생성실패 \n" + se);
		}
	}

	void createTnoExist(String sql){
		int idx1=sql.indexOf(" ");
		int idx2=sql.indexOf(" ",8);
		String tname=sql.substring(idx1,idx2);
		
		String t = "select TABLE_NAME from user_tables where TABLE_NAME='"+tname+"'";
		ResultSet rs = null;
		try{
			rs = stmt.executeQuery(t);
			if(!rs.next()){
				createT(sql);
			}else pln("테이블이 이미 존재합니다.");
		}catch(SQLException se){
			pln("createTnoExist 실패"+ se);
		}
	}

	void dropT(String sql){
		try{
			stmt.execute(sql);
			pln("drop 성공");
		}catch(SQLException se){
			pln("drop 실패 : "+se);
		}			
	}

	void purge(String sql){
		try{
			stmt.execute(sql);
			pln("Purge Recyclebin 성공");
		}catch(SQLException se){
			pln("Purge Recyclebin 실패 : " +se);
		}
	}

	void insertD(String sql){
		try{
			stmt.executeUpdate(sql);
			pln("insert 성공");
		}catch(SQLException se){
			pln("예외발생"+se);
		}
	}

	void updateD(String sql){
		try{
			stmt.executeUpdate(sql);
			pln("update 성공");
		}catch(SQLException se){
			pln("update 실패" + se);
		}
	}

	void deleteD(String sql){
		try{
			stmt.executeUpdate(sql);
			pln("delete 성공");
		}catch(SQLException se){
			pln("delete 실패"+ se);
		}
	}

	void selectD(String sql){
		ResultSet rs = null;					//** ResultSet 은 표처럼 나옴 꼭 검색해서 다시보기
		ResultSetMetaData rsmd = null;
		try{
			rs = stmt.executeQuery(sql);
			rsmd = rs.getMetaData();

			int cc = rsmd.getColumnCount();
			
			
			for (int i =1; i<=cc ;i++ ){
				p(rsmd.getColumnName(i)+"	");
			}
			pln("");

			for (int j=1;j<=cc ;j++ )p("----------");
			pln("");

			while(rs.next()){
				for (int j=1;j<=cc ;j++ ){
					String data = rs.getString(j);
					p(data+"	");
				}
				pln("");
			}
		}catch(SQLException se){
			pln("selectD 실패"+se);
		}finally{
			try{
				if(rs != null) rs.close();
			}catch(SQLException se){
			}
		}
	}

	void p(String str){System.out.print(str);}
	void pln(String str){System.out.println(str);}

	public static void main(String[] args) 
	{
		Sqlplus sq = new Sqlplus();
		sq.set();
		sq.init();
		sq.sql();
	}
}
