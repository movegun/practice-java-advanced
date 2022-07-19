import java.io.*;
import java.net.*;

class Server1 extends Thread
{
	ServerSocket ss;
	Socket s;

	int port = 2000;
	String ipClient;

	InputStream is;
	OutputStream os;
	DataInputStream dis;
	DataOutputStream dos;

	Server1(){
		try{
			ss = new ServerSocket(port);
			s = ss.accept();

			InetAddress ia = s.getInetAddress(); // s의 아이피주소를 찍어주려고
			ipClient = ia.getHostAddress(); // 위는 바이트배열 지금은 문자열
			System.out.println(ipClient+" 님이 접속하였습니다");
			
			//장치를 만들어 문자열 >> 바이트 >> 플러쉬 할수있는 장치
			is = s.getInputStream();
			os = s.getOutputStream();
			dis = new DataInputStream(is);
			dos = new DataOutputStream(os); // 소켓으로 내보내고 소켓에서 읽어오고
			start();
			send();
			
		}catch(IOException ie){
		}finally{
		}
	}

	public void run(){
		listen();
	}
	void listen(){
		String in = "";
		try{
			while (true){
				in = dis.readUTF();
				System.out.println("상대방>>"+in);
			}
		}catch(IOException ie){
			System.out.println("아마 상대가 나갔음 3초후 종료");
			try{
				Thread.sleep(3000);
			}catch(InterruptedException iee){}
			System.exit(0);
		}finally{
			try{
				dis.close();
				is.close();
			}catch(IOException ie){}
		}
	}

	void send(){
		BufferedReader bi = new BufferedReader(new InputStreamReader(System.in));
		String out = "";
		try{
			while (true){
				out = bi.readLine();
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
		Server1 s = new Server1();
	}
}
