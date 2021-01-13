import java.util.AbstractMap;
import java.util.Map;

class Box<K,  V> {
	private K key;
	private V val;
	public Box(K k, V v) {
		this.key = k;
		this.val = v;
	}
	public Map.Entry<K, V> getPair() {
		return new AbstractMap
			.SimpleImmutableEntry<K, V>(this.key, this.val);
	}
}

class App {
	public static void main(String... args) {
		Map.Entry<String, Integer> e
			= new Box<String, Integer>("str", 101).getPair();
		w(e);
		w(e.getKey());
		w(e.getValue());
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
			System.out.println("["+s.getClass().getName()+"] "+s);
		}
	}
}
