package exception;

//고객을 찾을 수 없을 때 발생하는 예외
public class CustomerNotFoundException extends ShopException {

	public CustomerNotFoundException(String message) {
		super(message);
	}
}
