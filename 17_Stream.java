import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.TreeMap;
import java.util.stream.DoubleStream;
import java.util.stream.Stream;

class UseStream {

	private static final long S = System.currentTimeMillis();

	public static void main(String... args) {

		w("<< Execute 'Intermediate Operation' "
			+"when 'Terminal Operation' is executed.>>");

		// ---- forEach(new Consumer<T>() { accept(T) {} }) ----
		Arrays.asList(0, 1, 2) // <= Data source
			.stream() // <= get Stream Object
			.map(i -> i*i) // <= Intermediate Operation
			.forEach(System.out::println); // <= Terminal Operation

		// ---- "forEach()" owned by ----
		//    "Stream", <= default void forEach(Consumer)
		//    "Collection", <= default void forEach(Consumer)
		//    "Iterable", <= default void forEach(Consumer)
		//    "Map" <= default void forEach(BiConsumer)
		//    "EntrySet", <= default void forEach(Consumer)

		// ---- Map is not implemented Collection-Interface. ----
		(new TreeMap<String, Integer>() { // anonymous class
			{ // instance initializer
				this.put("status01", 100);
				this.put("status02", 200);
				this.put("status03", 300);
			}
		}).forEach((key, val) -> {
			w(key + ": " + val);
		});
		(new TreeMap<String, Integer>() { // anonymous class
			{ // instance initializer
				this.put("st01", 10);
				this.put("st02", 20);
				this.put("st03", 30);
			}
		}).entrySet().forEach(e -> {
			w(e.getKey() + ": " + e.getValue());
		});
		double[] doubleArray1 = {0.1, 0.2, 0.3, 0.4};
		w(DoubleStream
			.concat(
				Arrays.stream(doubleArray1, 0, 3),
				DoubleStream.of(0.01, 0.02))
			.mapToObj(d -> d)
			.peek(d -> w("[peek1] "+d))
			.sorted((e1, e2) -> e1 < e2 ? -1 : e1 == e2 ? 0 : 1)
			.peek(d -> w("[peek2] "+d))
			.findAny()); // 'findAny()' puts performance first

		w(Stream
			.of(
				Arrays.asList("str1 ", "str2 "),
				Arrays.asList("str3 ", "str1 "),
				new ArrayList<String>()
			)
			.flatMap(List::stream) // list -> list.stream()
			.distinct()
			.reduce((s1, s2) -> s1+s2)
			.orElseGet(() -> "[none result] "+lap()));
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
