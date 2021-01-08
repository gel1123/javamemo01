package pac01.p1;

public interface Interface02 {

	static String method01() {
		return "[interface02] static-method";
	}

	//default String method02() { // collides with Interface01
	//	return "[interface02] default-method";
	//}
}
