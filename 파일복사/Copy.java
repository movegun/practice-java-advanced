import java.io.*;

class Copy // 파일 복사(경로를 통해 인식)+ 파일 잘라내기(복사+삭제 // 경로를 통해 생성) + 디렉토리 여부 + 생성 +스트림.close
{
	FileInputStream fis;
	FileOutputStream fos;
	BufferedInputStream bis;
	BufferedOutputStream bos;
	BufferedReader bi = new BufferedReader(new InputStreamReader(System.in));
	String path1, path2, fname;

	void select() throws IOException{
		System.out.print("[1]. 복사 [2] 잘라내기	입력: ");
		String num = bi.readLine();
		num = num.trim();
		try{
			int i = Integer.parseInt(num);
			if (i==1){
				System.out.println("복사과정 시행 ");
				getInfo();
			}else if (i==2){
				System.out.println("잘라내기 시행 ");
				getInfo();
				delete();
			}else{
				System.out.println("[1] 또는 [2]만 입력가능");
				select();
			}
		}catch(NumberFormatException ne){
			System.out.println("숫자만 입력 가능합니다.");
			select();
		}		
	}

	void getInfo() throws IOException {
		System.out.print("복사할 원본파일 위치입력 : ");
		this.path1 = bi.readLine();
		int lidx = path1.lastIndexOf("\\");
		this.fname = path1.substring(lidx+1);
		System.out.println("복사할 파일명 : ["+fname+"]");
		System.out.print("파일을 이동시킬 위치입력 : ");
		this.path2 = bi.readLine();
		makeD();
		copy();
	}

	void makeD()throws IOException{
		File file = new File(path2);
		if (file.exists()){ // exist >> T/F 반환
		}else{
			System.out.print("이동시킬 디렉토리 존재안함. 디렉토리생성? [y]/[n] 입력 :");
			String str = bi.readLine();	
			if (str.equals("y")){
				file.mkdirs(); // clone 이 가진값으로 생성
			}else if (str.equals("n")){
				System.out.println("파일 이름 다시 확인");
				getInfo();
			}else {
				System.out.println("[y] / [n]/ 中 입력");
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
