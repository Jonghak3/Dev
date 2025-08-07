package oop.lab2;

public class RetangleMain {

	public static void main(String[] args) {
		Retangle retangle = new Retangle();
		retangle.width = 5;
		retangle.height = 8;
		
		int area = retangle.calculateArea();
		System.out.println("넓이 : "+area);
		
		int perimeter = retangle.calculatePerimeter();
		System.out.println("둘레 길이 : "+perimeter);
		
		boolean square = retangle.isSquare();
		System.out.println("정사각형 여부 : "+square);
	}
}
