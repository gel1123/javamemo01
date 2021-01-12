import java.lang.StringBuilder;
import java.lang.AssertionError;

class App {

	public static void main(String... args) {
		w("---- start ----");
		w("usage: [java -ea App] <= enable-assertions");
		w("usage: [java -da App] <= disable-assertions");
		String str1 = "ho" + new StringBuilder().append("ge");
		w(str1);
		String str2 = new String("hoge");
		w(str2);
		Boolean b1 = str1 == str2;
		w(b1);
		try { assert b1; } catch (AssertionError e) { w(e); }
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
}
