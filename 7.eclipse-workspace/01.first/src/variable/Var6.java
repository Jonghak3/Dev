package variable;

public class Var6 {

	public static void main(String[] args) {
		int num1 = 10 ;
		int num2 = 3 ;
		int add = num1 + num2;
		int substraction = num1 - num2;
		int multiplication = num1 * num2;
		int division = num1 / num2;
		int reminder = num1 % num2;
		System.out.println("=== 간단한 계산기 ===");
		System.out.println("숫자1: "+num1);
		System.out.println("숫자2: "+num2);
		System.out.println();
		System.out.println("덧셈: " + num1 + " + " + num2+ " = "+ add);
		System.out.println("뺄셈: " + num1 + " - " + num2+ " = "+ substraction );
		System.out.println("곱셈: " + num1 + " * " + num2+ " = "+ multiplication);
		System.out.println("나눗셈: " + num1 + " / " + num2+ " = "+ division);
		System.out.println("나머지: " + num1 + " % " + num2+ " = "+ reminder);
		

	}

}
