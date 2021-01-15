import java.util.ArrayList;
import java.util.Arrays;
import java.util.Comparator;
import java.util.List;

class ExeHogeComparator {

	static class Hoge {
		public int id;
		public String name;

		public Hoge(int i, String n) {
			this.id = i;
			this.name = n;
		}

		@Override
		public String toString() {
			return ((Integer) this.id).toString() + ": " + this.name;
		}
	}

	static class HogeComparator implements Comparator<Hoge> {
		@Override
		public int compare(Hoge h1, Hoge h2) {
			return h1.id - h2.id;
		}
	}

	public static void main(String[] args) {
		System.out.println("---- sample 'comparator' ----");
		Hoge h1 = new Hoge(1, "hoge");
		Hoge h2 = new Hoge(2, "fuge");
		Hoge h3 = new Hoge(3, "age");
		Hoge h4 = new Hoge(4, "sage");
		List<Hoge> list = new ArrayList<>(Arrays.asList(h2, h3, h1, h4));
		list.sort(new HogeComparator());
		System.out.println(list);
	}
}
