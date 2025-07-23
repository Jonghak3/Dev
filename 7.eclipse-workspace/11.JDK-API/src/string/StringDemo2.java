package string;

public class StringDemo2 {

	public static void main(String[] args) {
		String javaStr=new String("java");
		System.out.println("javaStr 문자열객체 주소값 : "+ System.identityHashCode(javaStr));
		System.out.println();
		
		StringBuilder buffer = new StringBuilder(javaStr);
		System.out.println(buffer);
		System.out.println("buffer 주소값 : "+ System.identityHashCode(buffer));
		
		//문자열 추가
		buffer.append(" and");
		buffer.append(" android");
		buffer.append(" kotlin programming");
		System.out.println(buffer);
		System.out.println("buffer 주소값2 : "+ System.identityHashCode(buffer));
		
	}
}
