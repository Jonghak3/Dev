package service;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.UUID;

import exception.CustomerNotFoundException;
import exception.InsufficientStockException;
import exception.ProductNotFoundException;
import exception.ShopException;
import model.CartItem;
import model.Customer;
import model.Manager;
import model.Order;
import model.Product;

public class ShoppingMall {
    private HashMap<String, Product> products;      // 상품 관리
    private HashMap<String, Customer> customers;    // 고객 관리  
    private HashMap<String, Manager> managers;      // 관리자 관리
    private ArrayList<Order> orders;               // 주문 내역
    private HashMap<String, ArrayList<CartItem>> carts; // 고객별 장바구니
    private String mallName;
    private int orderCounter; // 주문 번호 생성용
	
    public ShoppingMall(String mallName) {
		//super();
		this.mallName = mallName;
		this.products = new HashMap<>();
		this.customers = new HashMap<>();
		this.managers = new HashMap<>();
		this.orders = new ArrayList<>();
		this.carts = new HashMap<>();
	}
    
    
 // 기본 CRUD
    public void addProduct(String productId, String name, int price, int stock, String category) throws ShopException {
    	if(products.containsKey(productId)) {
    		throw new ShopException("이미 존재하는 상품 ID입니다: "+productId);
    	}
    	Product product = new Product(productId, name, price, stock, category);
    	products.put(productId, product);
    	System.out.println("상품이 추가되었습니다: "+name);
    }
    void displayAllProducts() {
    	if(products.isEmpty()) {
			System.out.println("등록된 상품이 없습니다.");
			return;
		}
    	System.out.println("=== 전체 상품 목록 ===");
		for(Product product : products.values()) {
			System.out.println(product);
		}
		System.out.println("총 "+products.size()+"개의 상품이 등록되어 있습니다.\n");
    }
    void displayAvailableProducts() {	// 재고 있는 상품만
    	long availableProducts = products.values().stream().filter(Product::isInStock)
    							.peek(System.out::println).count();
    	if(availableProducts==0) {
    		System.out.println("상품이 없습니다.");
    	}
    	System.out.println();
    } 
    
 // 검색 기능  
    ArrayList<Product> searchProductsByName(String name) {
    	ArrayList<Product> results = new ArrayList<>();
		for(Product product : products.values()) {
			if(product.getName().toLowerCase().contains(name.toLowerCase())) {
				results.add(product);
			}
		}
    	
    	return results;
    }
    ArrayList<Product> getProductsByCategory(String category){
    	ArrayList<Product> results = new ArrayList<>();
		
		for(Product product : products.values()) {
			if(product.getCategory().toLowerCase().contains(category.toLowerCase())) {
				results.add(product);
			}
		}
		
		return results;
    }
    ArrayList<Product> getLowStockProducts() {	// 재고 부족 상품 (5개 이하)
    	ArrayList<Product> results = new ArrayList<>();
    	
    	for(Product product : products.values()) {
    		if(product.isLowStock()) {
    			results.add(product);
    		}
    	}
    	
    	return results;
    } 
    //고객 추가
    public void addCustomer(String id, String name, String email) throws ShopException {
    	if(customers.containsKey(id)) {
    		throw new ShopException("이미 존재하는 고객 ID입니다: "+id);
    	}
    	Customer customer = new Customer(id, name, email);
    	customers.put(id, customer);
    	System.out.println("고객이 등록되었습니다.");
    }
    
    //매니저 추가
    public void addManager(String id, String name, String email, String department, int employeeId) throws ShopException {
    	if(managers.containsKey(id)) {
    		throw new ShopException("이미 존재하는 매니저 ID입니다: "+id);
    	}
    	Manager manager = new Manager(id, name, email, department, employeeId);
    	managers.put(id, manager);
    	System.out.println("매니저가 등록되었습니다.");
    }
    void displayCustomerOrders(String customerId) throws CustomerNotFoundException {
    	
    }
    
    public void addToCart(String customerId, String productId, int quantity) 
    	    throws CustomerNotFoundException, ProductNotFoundException, InsufficientStockException {
    	if(products.containsKey(productId)) {
    		throw new ProductNotFoundException("존재하지 않는 상품입니다: "+productId);
    	}
    	if(customers.containsKey(customerId)) {
    		throw new CustomerNotFoundException("존재하지 않는 고객입니다: "+customerId);
    	}
    	Product foundProduct = products.get(productId);
    	
    	ArrayList<CartItem> customerCart = carts.get(customerId);
    	if(customerCart == null) {
    		customerCart = new ArrayList<CartItem>();
    		carts.put(customerId, customerCart);
    	}
    	
    	boolean productExist = false;
    	for(CartItem item : customerCart) {
    		if(item.getProduct().getProductId().equals(foundProduct.getProductId())) {
    		item.addQuantity(quantity);
    		System.out.println("장바구니에 상품이 추가되었습니다: "+foundProduct.getName()+" ("+quantity+"개)");
    		productExist = true;
    		break;
    		}
    	}
    	
    	if(!productExist) {
    		CartItem newItem = new CartItem(foundProduct, quantity);
    		customerCart.add(newItem);
    		System.out.println("장바구니에 상품이 추가되었습니다: "+foundProduct.getName()+" ("+quantity+"개)");
    	}
    	
    	
    }
    	void displayCart(String customerId) throws CustomerNotFoundException, ShopException, InsufficientStockException {
    		if(!customers.containsKey(customerId)) {
    			throw new CustomerNotFoundException("고객님의 ID를 찾을 수 없습니다."+customerId);
    		}
    		Customer customer = customers.get(customerId);
    		System.out.println("\n=== "+customer.getName()+"님의 장바구니 ===");
    		ArrayList<CartItem> customerCart = carts.get(customerId);
    		
    		if(customerCart.isEmpty()) {
    			throw new ShopException("장바구니가 비어 있습니다");
    		}
//    		for(CartItem item:customerCart) {
//    			if(item.getProduct().getStock()< item.getQuantity()) {
//    				throw new InsufficientStockException("재고가 부족합니다.");
//    			}
    		for(CartItem item:customerCart) {
    			System.out.println("- "+item.getProduct().getName()+" X "+item.getQuantity()+"개 ="+item.getTotalPrice());
    		}
    		}
    	
    	
    	
    	void placeOrder(String customerId) throws ShopException {
    		if(!customers.containsKey(customerId)) {
    			throw new CustomerNotFoundException("고객님의 ID를 찾을 수 없습니다."+customerId);
    		}
    		Customer customer = customers.get(customerId);
    		ArrayList<CartItem> customerCart = carts.get(customerId);
    		if(!customers.containsKey(customerId)) {
    			throw new CustomerNotFoundException("고객님의 ID를 찾을 수 없습니다: "+customerId);
    		}
    		if(customerCart==null||customerCart.isEmpty()) {
    			throw new ShopException("장바구니가 비어 있습니다");
    		}
    		for(CartItem item:customerCart) {
    			item.getProduct().reduceStock(item.getQuantity());
    			products.put(item.getProduct().getProductId(), item.getProduct());
    		}
    		Order order =new Order(UUID.randomUUID().toString(),customer , customerCart, customerId);
    		orders.add(order);
    		carts.remove(customerId);
    		
    		
    		
    	}
    }
    
    
