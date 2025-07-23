package interface6;

public class MyClassTest {

	public static void main(String[] args) {
		MyClass myClass = new MyClass();
		X xClass = myClass;
		xClass.x();
		
		Y yClass = myClass;
		yClass.y();
		
		MyClass iClass = myClass;
		iClass.x();
		iClass.y();
		iClass.myMethod();
		
	}
}
