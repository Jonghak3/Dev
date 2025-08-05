package main;

import java.util.Scanner;

import exception.CustomerNotFoundException;
import exception.InsufficientStockException;
import exception.ProductNotFoundException;
import exception.ShopException;
import service.ShoppingMall;

public class ShoppingMallSystem {
	private Scanner scanner;
	private ShoppingMall mall;
	
	public ShoppingMallSystem() throws ShopException {
		this.mall = new ShoppingMall("시험용");
		this.scanner = new Scanner(System.in);
		initializeData();
	}
	
	private void initializeData() throws ShopException {
		// 기본 상품 데이터
        mall.addProduct("P001", "갤럭시 스마트폰", 800000, 10, "전자제품");
        mall.addProduct("P002", "애플 노트북", 1500000, 5, "전자제품");
        mall.addProduct("P003", "무선 이어폰", 150000, 20, "전자제품");
        mall.addProduct("P004", "게이밍 키보드", 120000, 8, "컴퓨터");
        mall.addProduct("P005", "모니터", 300000, 3, "컴퓨터");
        mall.addProduct("P006", "운동화", 89000, 15, "의류");
        mall.addProduct("P007", "청바지", 65000, 12, "의류");
        mall.addProduct("P008", "백팩", 45000, 7, "가방");
        
        // 기본 고객 데이터
        mall.addCustomer("C001", "김철수", "kim@email.com");
        mall.addCustomer("C002", "이영희", "lee@email.com");
        mall.addCustomer("C003", "박민수", "park@email.com");
        
        // 기본 관리자 데이터
        mall.addManager("M001", "정관리", "admin@mall.com", "운영팀", 1001);
		
	}

	public static void main(String[] args) throws ShopException {
		ShoppingMallSystem system = new ShoppingMallSystem();
		system.run();
	}

	private void run() {
		
		while(true) {
			displayMainMenu();
			
			int choice = Integer.parseInt(scanner.nextLine());
			
			switch(choice) {
			case 1:			//상품관리
				handleProductManagement();
			case 3:
				handleCartManagement();
			case 4:
				handleOrderManagement();
			case 0:
				System.out.println("쇼핑몰 관리 시스템을 종료합니다. 감사합니다.");
				scanner.close();
				return;
			}
		}
		
	}

	private void handleOrderManagement() {
		while(true) {
			System.out.println("\n=== 주문 관리 ===");
			System.out.println("1. 주문하기");
			System.out.println("2. 고객 주문 내역 보기");
			System.out.println("0. 메인 메뉴로 돌아가기");
			System.out.print("선택하세요: ");
			
			int choice = Integer.parseInt(scanner.nextLine());
			
			switch (choice) {
			case 1:		//주문하기
				order();
			case 0:	//메인 메뉴
				return;
				default:
					System.out.println("잘못된 선택입니다.");
					break;
			}
		}
		
	}

	private void order() {
		// TODO Auto-generated method stub
		
	}

	private void handleCartManagement() {
		while(true) {
			System.out.println("\n=== 장바구니 관리 ===");
			System.out.println("1. 장바구니에 상품 추가");
			System.out.println("2. 장바구니 보기");
			System.out.println("0. 메인 메뉴로 돌아가기");
			System.out.print("선택하세요: ");
			
			int choice = Integer.parseInt(scanner.nextLine());
			
			switch (choice) {
			case 1:		//장바구니에 상품 추가
				addProductToCart();
			case 0:	//메인 메뉴
				return;
				default:
					System.out.println("잘못된 선택입니다.");
					break;
			}
		}
		
	}

	private void addProductToCart() {
		System.out.println("고객ID를 입력하세요: ");
		String customerId = scanner.nextLine().trim();
		
		System.out.println("제품ID를 입력하세요: ");
		String productId = scanner.nextLine().trim();
		
		System.out.println("수량을 입력하세요: ");
		String quantitymk1 = scanner.nextLine().trim();
		
		if(customerId.isEmpty()||productId.isEmpty()||quantitymk1.isEmpty()) {
			System.out.println("모든 필드를 입력해주세요.");
			return;
		}
		
		int quantity = Integer.parseInt(quantitymk1);
		try {
			mall.addToCart(customerId, productId, quantity);
		} catch (CustomerNotFoundException e) {
			//e.printStackTrace();
			System.out.println(e.getMessage());
		} catch (ProductNotFoundException e) {
//			e.printStackTrace();
			System.out.println(e.getMessage());
		} catch (InsufficientStockException e) {
//			e.printStackTrace();
			System.out.println(e.getMessage());
		}
		
	}

	private void handleProductManagement() {
		while(true) {
			System.out.println("\n=== 상품 관리 ===");
			System.out.println("1. 상품 추가");
			System.out.println("2. 전체 상품 보기");
			System.out.println("3. 구매 가능한 상품 보기");
			System.out.println("4. 상품 검색 (이름)");
			System.out.println("5. 카테고리별 상품 보기");
			System.out.println("6. 재고 부족 상품 보기 (5개 이하)");
			System.out.println("0. 메인 메뉴로 돌아가기");
			System.out.print("선택하세요: ");
			
			int choice = Integer.parseInt(scanner.nextLine());
			
			switch (choice) {
			case 1:		//상품 추가
				addNewProduct();
			case 0:	//메인 메뉴
				return;
				default:
					System.out.println("잘못된 선택입니다.");
					break;
			}
		}
		
	}

	private void addNewProduct() {
		System.out.println("제품ID를 입력하세요: ");
		String productId = scanner.nextLine().trim();
		
		System.out.println("제품명을 입력하세요: ");
		String name = scanner.nextLine().trim();
		
		System.out.println("가격을 입력하세요: ");
		String pricemk1 = scanner.nextLine().trim();
		
		System.out.println("재고수량을 입력하세요: ");
		String stockmk1 = scanner.nextLine().trim();
		
		System.out.println("카테고리를 입력하세요: ");
		String category = scanner.nextLine().trim();
		
		if(productId.isEmpty()||name.isEmpty()||pricemk1.isEmpty()||stockmk1.isEmpty()||category.isEmpty()) {
			System.out.println("모든 필드를 입력해주세요.");
			return;
		}
		
		int price = Integer.parseInt(pricemk1);
		int stock = Integer.parseInt(stockmk1);
		try {
			mall.addProduct(productId, name, price, stock, category);
		} catch (ShopException e) {
			//e.printStackTrace();
			System.out.println(e.getMessage());
		} catch (Exception e) {
			System.out.println("예상치 못한 오류가 발생했습니다: "+e.getMessage());
		}
		
	}

	private void displayMainMenu() {
		System.out.println("=== 메인 메뉴 ===");
		System.out.println("1. 상품 관리");
		System.out.println("2. 고객 관리");
		System.out.println("3. 장바구니 관리");
		System.out.println("4. 주문 관리");
		System.out.println("5. 통계 정보");
		System.out.println("0. 종료");
	}

}
