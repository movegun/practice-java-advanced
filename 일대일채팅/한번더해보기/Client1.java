import java.io.*;
import java.net.*;

class Client1 extends Thread
{
	String serverIp = "192.168.0.47";	
	int port = 2000;

	Socket s;
	InputStream is;
	OutputStream os;
	DataInputStream dis;
	DataOutputStream dos;

	void access(){
		try{
			s = new Socket(serverIp,port);
			System.out.println("서버아이피 및 포트에 연결됨");
	
			is = s.getInputStream();
			dis = new DataInputStream(is);
			os = s.getOutputStream();
			dos = new DataOutputStream(os);
			start();
			send();
		}catch(IOException ie){
		}finally{
			try{
				if ( s != null)s.close();
			}catch(IOException ie){}
		}
	}
	public void run(){
		listen();
	}
	void listen(){
		String in = "";
		try{
			while(true){
				in = dis.readUTF();
				System.out.println("Server >>"+in);
			}
		}catch(IOException ie){
			System.out.println("상대방 나감");
		}finally{
			try{
				dis.close();
				is.close();			
			}catch(IOException ie){}
		}
	}

	void send(){
		BufferedReader bi = new BufferedReader(new InputStreamReader(System.in));
		
		try{			
			while(true){
				String out = bi.readLine();
				dos.writeUTF(out);
				dos.flush();
			}
		}catch(IOException ie){
		}finally{
			try{
				dos.close();
				os.close();
			}catch(IOException ie){}
		}
	}

	public static void main(String[] args) 
	{
		Client1 c = new Client1();
		c.access();
	}
}
