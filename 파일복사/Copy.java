import java.io.*;

class Copy // ���� ����(��θ� ���� �ν�)+ ���� �߶󳻱�(����+���� // ��θ� ���� ����) + ���丮 ���� + ���� +��Ʈ��.close
{
	FileInputStream fis;
	FileOutputStream fos;
	BufferedInputStream bis;
	BufferedOutputStream bos;
	BufferedReader bi = new BufferedReader(new InputStreamReader(System.in));
	String path1, path2, fname;

	void select() throws IOException{
		System.out.print("[1]. ���� [2] �߶󳻱�	�Է�: ");
		String num = bi.readLine();
		num = num.trim();
		try{
			int i = Integer.parseInt(num);
			if (i==1){
				System.out.println("������� ���� ");
				getInfo();
			}else if (i==2){
				System.out.println("�߶󳻱� ���� ");
				getInfo();
				delete();
			}else{
				System.out.println("[1] �Ǵ� [2]�� �Է°���");
				select();
			}
		}catch(NumberFormatException ne){
			System.out.println("���ڸ� �Է� �����մϴ�.");
			select();
		}		
	}

	void getInfo() throws IOException {
		System.out.print("������ �������� ��ġ�Է� : ");
		this.path1 = bi.readLine();
		int lidx = path1.lastIndexOf("\\");
		this.fname = path1.substring(lidx+1);
		System.out.println("������ ���ϸ� : ["+fname+"]");
		System.out.print("������ �̵���ų ��ġ�Է� : ");
		this.path2 = bi.readLine();
		makeD();
		copy();
	}

	void makeD()throws IOException{
		File file = new File(path2);
		if (file.exists()){ // exist >> T/F ��ȯ
		}else{
			System.out.print("�̵���ų ���丮 �������. ���丮����? [y]/[n] �Է� :");
			String str = bi.readLine();	
			if (str.equals("y")){
				file.mkdirs(); // clone �� ���������� ����
			}else if (str.equals("n")){
				System.out.println("���� �̸� �ٽ� Ȯ��");
				getInfo();
			}else {
				System.out.println("[y] / [n]/ �� �Է�");
				makeD();
			}
		}
	}

	void copy()throws IOException{
		File sourceFile = new File (path1);
		File cloneFile = new File (path2+"\\"+fname);
		fis = new FileInputStream(sourceFile);
		fos = new FileOutputStream(cloneFile);
		bis = new BufferedInputStream(fis,1024);
		bos = new BufferedOutputStream(fos,1024);

		byte bs[] = new byte[128];
		int i = 0;
		while ((i=bis.read(bs)) != -1){
			bos.write(bs, 0 , i);
		}
		bos.flush();
		bos.close();
		bis.close();
		fos.close();
		fis.close();
	}

	void delete(){
		File SourceFile = new File(path1);
		SourceFile.delete();
	}

	public static void main(String[] args) throws IOException
	{
		Copy c = new Copy();
		c.select();
	}
}
