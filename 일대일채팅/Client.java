import java.io.*;
import java.net.*;
class Client extends Thread
{
	String serverIp = "192.168.0.47";
	int port = 1997;
//	String name = "�̵���";
	String line;

	Socket s;
	InputStream is;
	OutputStream os;
	DataInputStream dis;
	DataOutputStream dos;

	void access(){
		try{
			s = new Socket( serverIp , port );
			System.out.println("������ ���� ����");
			
			is = s.getInputStream();
			dis = new DataInputStream(is);
			os = s.getOutputStream();
			dos = new DataOutputStream(os);
			start();
			send();
		}catch(UnknownHostException ue){
			System.out.println("���� ip Ȯ�ιٶ�");
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
		String msg = "";
		try{
			while(true){
				msg = dis.readUTF();
				System.out.println("Server>>" + msg);
			}
		}catch(IOException ie){
			System.out.println("�����߻� 3���� ����");
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
		try{
			while (true){
				String line = bi.readLine();
				dos.writeUTF(line);
			//	dos.flush();
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
		Client c = new Client();
		c.access();
	}
}
