import java.util.Arrays;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;
import java.util.stream.Stream;

class UseCollectors {
	public static void main(String... args) {
		w("---- start ----");
		w("[[[ class Hoge ]]]");
		class Hoge {
			private String id;
			private String name;

			Hoge(String i, String n) {
				this.id = i;
				this.name = n;
			}

			public String getId() {
				return this.id;
			}

			public String getName() {
				return this.name;
			}
		}
		Hoge h1 = new Hoge("1", "none");
		Hoge h2 = new Hoge("2", "obj2");
		Hoge h3 = new Hoge("3", "obj3");
		Hoge h4 = new Hoge("4", "none");
		Hoge[] h = { h1, h2, h3, h4 };
		Stream<Hoge> stream = Arrays.stream(h);
		Map<String, List<String>> map = stream.collect(
				Collectors.groupingBy(
						Hoge::getName,
						Collectors.mapping(
								Hoge::getId, Collectors.toList())));
		w(map);

		w("[[[ class Fuge ]]]");
		class Fuge {
			private int id;
			private String type;
			private String name;

			public Fuge(int id, String type, String name) {
				this.id = id;
				this.type = type;
				this.name = name;
			}

			public int getId() {
				return this.id;
			}

			public String getType() {
				return this.type;
			}

			public String getName() {
				return this.name;
			}

			@Override
			public String toString() {
				return "{" + this.id + "," + this.type + "," + this.name + "}";
			}
		}
		w(Arrays.asList(
				new Fuge(1, "t1", "fuge1"),
				new Fuge(2, "t2", "fuge2"),
				new Fuge(3, "t1", "fuge3")).stream()
				//.collect(Collectors.groupingBy((Fuge f) -> {return f.getType()}))
				.collect(Collectors.groupingBy(Fuge::getType)));
		w("---- end ----");
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
