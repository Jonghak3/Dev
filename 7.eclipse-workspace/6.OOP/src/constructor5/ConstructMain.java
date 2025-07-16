package constructor5;

public class ConstructMain {

	public static void main(String[] args) {
		MemberConstructor member1 = new MemberConstructor("user1", 15, 80);
		MemberConstructor member2 = new MemberConstructor("user2", 16);
		
		MemberConstructor[] members = {member1, member2};
		for(MemberConstructor m: members) {
			System.out.println("이름 : "+m.name+" 나이 : "+m.age+" 성적 : "+m.grade);
		}
	}
}
