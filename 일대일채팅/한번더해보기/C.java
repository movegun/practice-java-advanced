import java.io.*;

//File -> Moniter 
class C 
{
	FileInputStream fis;
	PrintStream ps = System.out;
	//String fname = "C.java";
	//String fname = "../../Java/�����ڹ�.java"; //���
	String fname = "C:/LDG/Advanced/�ϴ���ä��/���ٸ�/test.txt"; //����
	
	C(){
		try{
			fis = new FileInputStream(fname);
			readWrite();
		}catch(FileNotFoundException fe){
			ps.println(fname+"�̶� ������ ã�� �� ����");
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