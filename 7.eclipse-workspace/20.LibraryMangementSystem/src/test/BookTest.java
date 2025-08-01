package test;

import java.time.LocalDate;

import exception.BookAlreadyBorrowedException;
import model.Book;

public class BookTest {

	public static void main(String[] args) throws BookAlreadyBorrowedException {
		//1. 책 생성 테스트
		Book book = new Book("2025-1", "혼공", "신용권", "프로그래밍", LocalDate.of(2025, 1, 1));
		System.out.println("책 생성: "+book);
		System.out.println("대출 가능: "+book.isAvailable());
		
		//2. 대출테스트
		
		
		
		//4. 반납 테스트
		book.returnBook();
		System.out.println("반납 후 : "+ book);
		System.out.println("대출 가능: " + book.isAvailable()); 	//true
		
		//5. 연체 테스트
		book.borrowTo("M001");
		System.out.println("연체 여부 : "+ book.isOverdue());
	}
}
