package exception;

/*
 * 회원을 찾을 수 없을 때 발생하는 예외
 */
public class MemberNotFoundException extends LibraryException {

	public MemberNotFoundException(String message) {
		super(message);
	}

}
