package pac01;

@FunctionalInterface
public interface Interface01 {

	static String method01() {
		return "[interface01] static-method";
	}
	default String method02() {
		return "[interface01] default-method => "+getStr();
	}
	String getStr();
}
