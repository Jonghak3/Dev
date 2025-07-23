package object;

public class StringHashCodeDemo {

	public static void main(String[] args) {
		String str1 = new String("hello");
		String str2 = new String("hello");
		
		System.out.println(str1.hashCode());	//"hello" 문자열의 해시 코드값
		System.out.println(str2.hashCode());
		
	}
}
