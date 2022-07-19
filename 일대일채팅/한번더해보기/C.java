import java.io.*;

//File -> Moniter 
class C 
{
	FileInputStream fis;
	PrintStream ps = System.out;
	//String fname = "C.java";
	//String fname = "../../Java/동건자바.java"; //상대
	String fname = "C:/LDG/Advanced/일대일채팅/종다리/test.txt"; //절대
	
	C(){
		try{
			fis = new FileInputStream(fname);
			readWrite();
		}catch(FileNotFoundException fe){
			ps.println(fname+"이란 파일을 찾을 수 없음");
		}catch(IOException ie){}
	}
	void readWrite() throws IOException {
		byte bs[] = new byte[128];
		int i = 0;
		while((i=fis.read(bs)) != -1){
			System.out.println(i);
			ps.write(bs, 0, i);	
		}

		ps.flush();

		closeAll();
	}
	void closeAll(){
		try{
			fis.close();
			ps.close();
		}catch(IOException ie){}
	}
	public static void main(String[] args) {
		C c = new C();
	}
}