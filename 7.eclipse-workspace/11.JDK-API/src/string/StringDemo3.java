package string;

public class StringDemo3 {

	public static void main(String[] args) {
		String javaStr=new String("java");
		String androidKotlin = new String("androidKotlin");
		System.out.println(javaStr.toString());
		System.out.println("javaStr 문자열객체 주소값 : "+ System.identityHashCode(javaStr));
		System.out.println();
		
		//문자열 javaStr와 문자열 androidKotlin을 연결하여 javaStr에 대입
		javaStr = javaStr.concat(androidKotlin);
		System.out.println(javaStr.toString());
		System.out.println("연결된 javaStr 문자열객체 주소값 : "+ System.identityHashCode(javaStr));
		
	}
}
