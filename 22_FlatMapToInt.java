import java.util.Arrays;
import java.util.stream.IntStream;
import java.util.stream.Stream;

class UseFlatMap {
	public static void main(String... args) {
		w("---- start ----");
		int[][] array = {
				{ 1, 2, 3 },
				{ 4, 5, 6 }
		};
		//Stream<Integer[]> s = Stream.of(array[0], array[1]); // <= compile error
		Stream<int[]> s = Stream.of(array[0], array[1]);
		//IntStream int_s = s.flatMapToInt(i_array -> Arrays.stream(i_array));
		IntStream int_s = s.flatMapToInt(Arrays::stream);
		int_s.forEach(System.out::print);
		w("\n---- end ----");
	}

	public static void w(Object s) {
		if (s == null) {
			w("null");
			return;
		}
		Boolean ea = false;
		assert ea = true;
		if (!ea && s instanceof String) {
			System.out.println(s);
		} else {
			System.out.println("[" + s.getClass().getName() + "] " + s);
		}
	}
}
