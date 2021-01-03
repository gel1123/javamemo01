import java.io.*;


class UseConsole {
	public static void main(String... args) {
		Console c = null;
		String s = null;
		char[] p = null;
		// It may not be supported by 'eclipse'
		if (
			( (c = System.console()) == null )
			|| ( (s = c.readLine()) == null )
		) {
			w("[none input]");
			return;
		}
		w("[Console::reawdLine] " + s);
		if (
			((p = c.readPassword()) == null)
		)  {
			w("[none password]");
			return;
		}
		w("[Console::reawdPassword] " + String.valueOf(p));
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
