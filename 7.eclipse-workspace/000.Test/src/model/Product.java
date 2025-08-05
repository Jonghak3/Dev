package model;

import exception.InsufficientStockException;

public class Product {
    private String productId;       // 상품 ID
    private String name;           // 상품명
    private int price;            // 가격
    private int stock;            // 재고
    private String category;      // 카테고리
    private double rating;        // 평점 (0.0 ~ 5.0)
    private int reviewCount;      // 리뷰 개수
	
    //생성자
    public Product(String productId, String name, int price, int stock, String category) {
		//-super();
		this.productId = productId;
		this.name = name;
		this.price = price;
		this.stock = stock;
		this.category = category;
		this.rating = 0;
		this.reviewCount = 0;
	}
    
    //getter 메서드들
	public String getProductId() {
		return productId;
	}

	public String getName() {
		return name;
	}

	public int getPrice() {
		return price;
	}

	public int getStock() {
		return stock;
	}

	public String getCategory() {
		return category;
	}

	public double getRating() {
		return rating;
	}

	public int getReviewCount() {
		return reviewCount;
	}
	
	public void reduceStock(int quantity) throws InsufficientStockException {
		if(stock<quantity) {
			throw new InsufficientStockException("재고가 부족합니다: "+name);
		}
		stock -= quantity;
	}
	
	public void addStock(int quantity) {
		stock += quantity;
	}
	
	public boolean isInStock() {
		if(stock>0) 
			return true;
		else 
			return false;
	}
    
	public boolean isLowStock() {		// 재고 5개 이하 체크 (요구사항)
		if(stock <= 5)
			return true;
		else 
			return false;
	}
    
	public void addReview(double newRating) {
		double totalRating = rating*reviewCount+newRating;
		reviewCount++;
		rating = totalRating/reviewCount;
	}
	
	
    
	
}
