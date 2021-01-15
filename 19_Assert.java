import static pac01.Test01.*;
import static pac01.Test02.*;

class UseAssert {

	public static void main(String... args) {
		w("---- start ----");
		w("usage: [java -ea App] <= enable-assertions");
		w("usage: [java -da App] <= disable-assertions");
		w("usage: [java -ea:... App] <= only default package");
		String str1 = "ho" + new StringBuilder().append("ge");
		w(str1);
		String str2 = new String("hoge");
		w(str2);
		Boolean b1 = str1 == str2;
		w(b1);
		try { assert b1; } catch (AssertionError e) { w(e); }
		w("usage: [java -ea:pac01... App]");
		w("usage: [java -ea:pac01/Test01 App]");
		w("usage: [java -ea:pac01/Test02 App]");
		try { t1(); } catch (AssertionError e) { w(e); }
		try { t2(); } catch (AssertionError e) { w(e); }
		w("---- end ----");
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
