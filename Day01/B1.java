import java.io.*;

class B
{
	InputStream is = System.in;
	OutputStream os = System.out;
	FileOutputStream fos;

	B{
		try{
			fos = new FileOutputStream("b.txt");
		}catch(FileNotFoundException fe){}
	}

	void m1(){
		try{
			int b = is.read();
			os.write(b);
			os.flush();
		}catch(IOException ie){
		}
		try{
			while(true){
				int b = is.read();
				if(b == 13)break;
				os.write(b);
				os.flush();
			}
		}catch(IOException ie){
		}finally{
			try{
				is.close();
				os.close();
			}catch(IOException ie){}
		}
	}
	void m2(){
		try{
			byte[] bs = new byte[8];
			while (true){
				int i = is.read(bs);
				//os.write(bs,0,i);
				//os.flush();
				fos.write(bs,0,i);
				fos.flush();
			}
		}catch(IOException ie){
		}finally{
			try{
				is.close();
			//	os.close();
				fos.close();
			}
		}
	}
	



	public static void main(String[] args) 
	{
		B b = new B();
		b.m2();
	}
}
