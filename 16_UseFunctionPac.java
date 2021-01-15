import java.util.function.BiFunction;
import java.util.function.BinaryOperator;
import java.util.function.Consumer;
import java.util.function.Function;
import java.util.function.Predicate;
import java.util.function.Supplier;
import java.util.function.UnaryOperator;

class UseFunctionPac {

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
		w_consumer(new Consumer<String>() {
			@Override
			public void accept(String str) {
				w(str);
			}
		}, "anonymous");
		w_function(str -> str.length(), "lambda");
		w_function(new Function<String, Integer>() {
			@Override
			public Integer apply(String str) {
				return str.length();
			}
		}, "anonymous");
		w_bifunction((s1, s2) -> (s1+s2).length(),
			"Bi", "Function");
		w_unaryoperator(i -> i*i);

		//_____________________________________

		w("---- exe BinaryOperator ----");
		BinaryOperator<String> b = (s1, s2) -> s1+s2;
		w(b.apply("Bi", "Ope"));

		w("---- use 'Method Reference' on lambda ----");
		Consumer<String> c = System.out::println;
		c.accept("[SAM-Signature] void xxx(String)");

		w("---- use 'Constructor Reference' on lambda ----");
		Function<String, Integer> f = Integer::new;
		w(f.apply("10"));

		w("---- use 'Instance Method Ref' on lambda ----");
		f = String::length; // Integer xxx(String)
		w(f.apply("instance")); // Integer str.xxx()
		w(new Function<String, Integer>() {
			@Override
			public Integer apply(String s) {
				return s.length();
			}
		}.apply("anonymous"));

		w("---- use 'Object Method Ref' on lambda ----");
		String str = "obj";
		Function<String, String> f2 = str::concat;
		w(f2.apply(" met ref"));
	}

	//_______________________________________

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
	public static void w_function(Function<String, Integer> f
		, String self) {
		w("---- exe Function ----");
		w("\""+self+"\" length is " + f.apply(self));
	}
	public static void w_bifunction(
		BiFunction<String, String, Integer> b,
		String s1, String s2) {
		w("---- exe BiFunction ----");
		w("["+s1+"] + " + "["+s2+"] length is "
			+ b.apply(s1, s2));
	}
	public static void w_unaryoperator(
		UnaryOperator<Integer> u) {
		w("---- exe UnaryOperator ----");
		w(u.apply("UnaryOperator".length()));
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
