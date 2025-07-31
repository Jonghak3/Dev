package decorator;

import java.io.File;
import java.io.IOException;

public class FileTest {

	public static void main(String[] args) {
		//해당 경로에 File 클래스 생성. 아직 실제 파일이 만들어진 것은 아님.
		File file = new File("D:\\choongang\\JAVA\\FileTest\\newFile.txt");
		
		File parentDir = file.getParentFile();
		if(!parentDir.exists()) {
			parentDir.mkdirs();
		}
		
		try {
			file.createNewFile();
			System.out.println(file.isFile());
			System.out.println(file.isDirectory());
			System.out.println(file.getName());
			System.out.println(file.getAbsolutePath());
			System.out.println(file.getPath());
			System.out.println(file.canRead());
			System.out.println(file.canWrite());
			
			file.delete();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
}
