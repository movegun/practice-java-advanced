import java.io.*;
import java.net.*;

class Server extends Thread
{
	ServerSocket ss;
	Socket s;

	int port =1997;
	String ipClient;

	InputStream is;
	OutputStream os;
	DataInputStream dis;
	DataOutputStream dos;

	Server(){
		try{
			ss= new ServerSocket(port); //
			System.out.println("포트"+port+"에서 접속 기다리는중");
			s = ss.accept(); // 대기

			InetAddress ia = s.getInetAddress(); // s (socket)의 아이피주소 반환
			System.out.println("@@@@@@@"+ia+"@@@@@@@");
			ipClient = ia.getHostAddress();     //타입때문에 2번의 과정을 거친듯  상대방 아이피 알고싶을 때 2줄

			System.out.println(ipClient+"가 접속완료");
			System.out.println("@stop = 종료");

			is = s.getInputStream();
			dis = new DataInputStream(is);
			os = s.getOutputStream();
			dos = new DataOutputStream(os);
			start();
			send();
		}catch(IOException ie){
		}finally{
			try{
				s.close();
				ss.close();
			}catch(IOException ie){}
		}
	}
	public void run(){
		listen();
	}
	void listen(){
		String msg = "";
		try{
			while (true){
				msg = dis.readUTF();
				System.out.println("Client>> "+ msg);
				if (msg.equals("@stop")){
					System.out.println("대화 종료");
					System.exit(0);
				}
			}
		}catch(IOException ie){
			System.out.println("대화상대없음 3초후 종료");
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
		String line = "";
		try{
			while(true){				
				line = bi.readLine();
				dos.writeUTF(line);
				dos.flush();
				if (line.equals("@stop")) System.exit(0);
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
		Server s = new Server();
	}
}
