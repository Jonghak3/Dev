package model;

public class CartItem {
    private Product product;  // 상품
    private int quantity;    // 수량
    
    
    
    public CartItem(Product product, int quantity) {
		//super();
		this.product = product;
		this.quantity = quantity;
	}
	public void addQuantity(int amount) {   // 수량 증가
    	quantity += amount;
    }
    public int getTotalPrice() {	 // 총 가격 계산
    	return product.getPrice()*quantity;
    }
	@Override
	public String toString() {
		return "CartItem [product=" + product +", price="+product.getPrice()+"원"+ ", quantity=" + quantity + "]";
	}
	public Product getProduct() {
		return product;
	}
	public int getQuantity() {
		return quantity;
	}
    
    
}
