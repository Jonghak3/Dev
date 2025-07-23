package generics;

public class GenericPrinterTest {

	public static void main(String[] args) {
		//제네릭 자료형 (Generic Type), 매개변수화된 자료형(parameterized type)
		GenericPrinter<Powder> powderPrinter = new GenericPrinter<>();
		powderPrinter.setMaterial(new Powder());
		System.out.println(powderPrinter);
		System.out.println();
		
		//             대입된 자료형
		GenericPrinter<Plastic> plasticPrinter = new GenericPrinter<Plastic>();
		plasticPrinter.setMaterial(new Plastic());
		System.out.println(plasticPrinter);
		
		//대입된 자료형 <Powder>를 명시하지 않음
		GenericPrinter powderPrinter2 = new GenericPrinter();
		powderPrinter2.setMaterial(new Powder());
		Powder powder = (Powder) powderPrinter2.getMaterial();	//강제 형변환
		System.out.println(powderPrinter2);
	}
}
