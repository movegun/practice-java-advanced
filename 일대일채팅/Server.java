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
			System.out.println("��Ʈ"+port+"���� ���� ��ٸ�����");
			s = ss.accept(); // ���

			InetAddress ia = s.getInetAddress(); // s (socket)�� �������ּ� ��ȯ
			System.out.println("@@@@@@@"+ia+"@@@@@@@");
			ipClient = ia.getHostAddress();     //Ÿ�Զ����� 2���� ������ ��ģ��  ���� ������ �˰���� �� 2��

			System.out.println(ipClient+"�� ���ӿϷ�");
			System.out.println("@stop = ����");

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
					System.out.println("��ȭ ����");
					System.exit(0);
				}
			}
		}catch(IOException ie){
			System.out.println("��ȭ������ 3���� ����");
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
