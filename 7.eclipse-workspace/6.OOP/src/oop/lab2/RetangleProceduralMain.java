package oop.lab2;

public class RetangleProceduralMain {

	public static void main(String[] args) {
		int width = 5;
		int height = 8;
		int area = calculateArea(width, height);
		System.out.println("넓이 : "+area);
		
		int perimeter = calculatePerimeter(width, height);
		System.out.println("둘레 길이 : "+perimeter);
		
		boolean square = isSquare(width, height);
		System.out.println("정사각형 여부 : "+square);
	}

	private static boolean isSquare(int width, int height) {
		
		return width == height;
	}

	private static int calculatePerimeter(int width, int height) {
		return 2*(width+height);
	}

	private static int calculateArea(int width, int height) {
		return width*height;
	}
}
