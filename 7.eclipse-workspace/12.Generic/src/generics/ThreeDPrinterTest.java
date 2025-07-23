package generics;

public class ThreeDPrinterTest {

	public static void main(String[] args) {
		ThreeDPrinter3 printer = new ThreeDPrinter3();
		
		Powder p = new Powder();
		printer.setMaterial(p);	//자동형변환
		Powder p2 = (Powder) printer.getMaterial();	//직접 형 변환을 해야 함
		
	}
}
