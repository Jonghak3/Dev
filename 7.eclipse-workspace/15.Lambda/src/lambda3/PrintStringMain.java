package lambda3;

public class PrintStringMain {

	public static void main(String[] args) {
		PrintString lambdaStr = s -> System.out.println(s);
		lambdaStr.showString("hello Choongang!");
		
		showString2(lambdaStr);
		
		PrintString prStr = showString3();
		prStr.showString("hello ");
	}
	
	public static void showString2(PrintString p) {
		p.showString("hello Choongang2!");
	}
	
	public static PrintString showString3() {
		return s -> System.out.println(s+"Choongang3");
	}
}
