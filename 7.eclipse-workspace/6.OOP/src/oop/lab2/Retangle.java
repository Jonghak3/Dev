package oop.lab2;

public class Retangle {
	int width = 5;
	int height = 8;
	
	int calculateArea() {
		return width*height;
	}
	int calculatePerimeter() {
		return 2*(width+height);
	}
	
	boolean isSquare() {
		
		return width == height;
	}
	
	
}
