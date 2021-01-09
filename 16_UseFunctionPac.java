import java.util.function.*;

class App {

	private static final long S = System.currentTimeMillis();

	public static void main(String... args) {
		w_supplier(() -> "lambda");
		w_supplier(new Supplier<String>() {
			@Override
			public String get() {
				return "anonymous";
			}
		});
		w_predicate(str -> str.equals("lambda"));
		w_predicate(new Predicate<String>() {
			@Override
			public boolean test(String str) {
				return str.equals("anonymous");
			}
		});
		w_consumer(str -> w(str), "lambda");
	}
	public static void w_supplier(Supplier<String> s) {
		w("---- exe Supplier ----");
		w(s.get());
	}
	public static void w_predicate(Predicate<String> p) {
		w("---- exe Predicate ----");
		w(p.test("lambda"));
	}
	public static void w_consumer(Consumer<String> c
		, String self) {
		w("---- exe Consumer ----");
		c.accept(self);
	}
	public static void w(Object s) {
		if (s == null) {
			w("null");
			return;
		}
		if (s instanceof String) {
			System.out.println(s);
		} else {
			System.out.println("["+s.getClass().getName()+"] "+s);
		}
	}
	public static long lap() {
		return System.currentTimeMillis() - S;
	}
}
