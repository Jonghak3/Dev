package constructor7;

public class Book {

	String title;
	String author;
	int page;
	
	public Book() {
		// TODO Auto-generated constructor stub
	}

	public Book(String title, String author) {
		//super();
		this(title, author, 0);
	}

	public Book(String title, String author, int page) {
		//super();
		this.title = title;
		this.author = author;
		this.page = page;
	}
	
	void displayInfo() {
		System.out.println("제목 : "+title+", 저자 : "+author+", 페이지 : "+page);
	}

	
	
}
