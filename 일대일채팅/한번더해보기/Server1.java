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

			InetAddress ia = s.getInetAddress(); // s�� �������ּҸ� ����ַ���
			ipClient = ia.getHostAddress(); // ���� ����Ʈ�迭 ������ ���ڿ�
			System.out.println(ipClient+" ���� �����Ͽ����ϴ�");
			
			//��ġ�� ����� ���ڿ� >> ����Ʈ >> �÷��� �Ҽ��ִ� ��ġ
			is = s.getInputStream();
			os = s.getOutputStream();
			dis = new DataInputStream(is);
			dos = new DataOutputStream(os); // �������� �������� ���Ͽ��� �о����
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
				System.out.println("����>>"+in);
			}
		}catch(IOException ie){
			System.out.println("�Ƹ� ��밡 ������ 3���� ����");
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
