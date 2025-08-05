package model;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;

import exception.ShopException;

public class Customer extends Person {
	private LocalDateTime joinDate; // 가입일
	private List<CartItem> cartItems;
	
    public Customer(String id, String name, String email) {
        super(id, name, email);
        this.joinDate = LocalDateTime.now(); // 생성자에서 가입일 자동 설정
    }

    @Override
    public String getUserType() {
        return "고객";
    }
    
    public LocalDateTime getJoinDate() { return joinDate; }
    
    @Override
    public String toString() {
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd");
        return super.toString() + 
               String.format(", 가입일: %s", joinDate.format(formatter));
    }
    

}
