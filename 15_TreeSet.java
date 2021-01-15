import java.util.TreeSet;

class UseTreeSet {

	private static final long S = System.currentTimeMillis();

	static class Product implements Comparable<Product> {
		public int id;
		public String name;

		public Product(int i, String n) {
			this.id = i;
			this.name = n;
		}

		@Override
		public int compareTo(Product p) {
			int diff = this.id - p.id;
			w("-- the comparison process is "
					+ "in progress [" + diff + "] --");
			w("[[self]] " + this);
			w("[[target]] " + p);
			return diff;
		}

		@Override
		public String toString() {
			return this.id + ": " + this.name;
		}
	}

	public static void main(String... args) {
		w(new TreeSet<Product>() {
			{
				this.add(new Product(3, "item3"));
				this.add(new Product(1, "item1"));
				this.add(new Product(2, "item2"));
			}
		});
	}

	public static void w(Object s) {
		if (s == null) {
			w("null");
			return;
		}
		if (s instanceof String) {
			System.out.println(s);
		} else {
			System.out.println("[" + s.getClass().getName() + "] " + s);
		}
	}

	public static long lap() {
		return System.currentTimeMillis() - S;
	}
}
