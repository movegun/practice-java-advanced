import java.io.*;

class C
{
	FileInputStream fis;
	PrintStream ps = System.out;
	String fname = "���"

	C(){
		try{
			fis = new FileInputStream(fname);
			readWrite();
		}catch(FileNotFoundException fe){
			ps.println(fname+"ã�� �� ����");
		}catch(IOException ie){
		}
	}
	void readWrite()








	public static void main(String[] args) 
	{
		System.out.println("Hello World!");
	}
}
