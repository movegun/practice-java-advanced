import java.io.*;

class C
{
	FileInputStream fis;
	PrintStream ps = System.out;
	String fname = "경로"

	C(){
		try{
			fis = new FileInputStream(fname);
			readWrite();
		}catch(FileNotFoundException fe){
			ps.println(fname+"찾을 수 없음");
		}catch(IOException ie){
		}
	}
	void readWrite()








	public static void main(String[] args) 
	{
		System.out.println("Hello World!");
	}
}
