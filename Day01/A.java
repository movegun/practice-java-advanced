import java.io.*;

class A
{
	InputStream is = System.in;
	InputStream ps = System.out;

	void m(){
		byte[] bs= new byte[10];
		try{
			is.read(bs);
			String str = new String(bs);
			for (byte b : bs ){
				ps.println(b);
			}
			ps.pruntln(str);
		}catch(IOException ie){}
	}

	public static void main(String[] args) 
	{
		A a = new A();
		a.m();
	}
}
