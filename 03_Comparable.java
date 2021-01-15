import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;

class ExeHogeComparable {

	public static void main(String[] args) {
		System.out.println("---- sample 'comparable' ----");
		Hoge h1 = new Hoge(1, "hoge");
		Hoge h2 = new Hoge(2, "fuge");
		Hoge h3 = new Hoge(3, "age");
		Hoge h4 = new Hoge(4, "sage");
		List<Hoge> list = new ArrayList<>(Arrays.asList(h2, h3, h1, h4));
		Collections.sort(list);
		System.out.println(list);
	}
}

class Hoge implements Comparable<Hoge> {
	public int id;
	public String name;

	public Hoge(int i, String n) {
		this.id = i;
		this.name = n;
	}

	@Override
	public int compareTo(Hoge h) {
		return this.id - h.id;
	}

	@Override
	public String toString() {
		return ((Integer) this.id).toString() + ": " + this.name;
	}
}
