package model;

public abstract class Person {
    protected String id;     // 고유 ID
    protected String name;   // 이름
    protected String email;  // 이메일
    
    //생성자
    public Person(String id, String name, String email) {
        this.id = id;
        this.name = name;
        this.email = email;
    }
    // Getter 메서드들
    public String getId() { return id; }
    public String getName() { return name; }
    public String getEmail() { return email; }
    
    public abstract String getUserType();
    
    @Override
    public String toString() {
        return String.format("ID: %s, 이름: %s, 이메일: %s, 유형: %s", 
                           id, name, email, getUserType());
    }
    
}
