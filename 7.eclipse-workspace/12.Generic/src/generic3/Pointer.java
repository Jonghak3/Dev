package generic3;

public class Pointer<T, V> {
	T x;
	V y;
	public Pointer(T x, V y) {
		//super();
		this.x = x;
		this.y = y;
	}
	public T getX() {
		return x;
	}
	public V getY() {
		return y;
	}
	
}
