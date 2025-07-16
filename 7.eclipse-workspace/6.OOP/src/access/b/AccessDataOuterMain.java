package access.b;

import access.a.AccessData;

public class AccessDataOuterMain {

	public static void main(String[] args) {
		AccessData data = new AccessData();
		
		data.publicField = 11;
		data.publicMethod();
		
		// 다른 패키지 default 호출 불가
//		data.defaultField = 22;
//		data.defaultMethod();

		//private 호출 불가
//		data.privateField = 22;
//		data.defaultMethod();
		
		data.innerAccess();
	}
}
