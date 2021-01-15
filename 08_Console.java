import java.io.Console;


class UseConsole {
	public static void main(String... args) {
		Console c = null;
		String s = null;
		char[] p = null;

		System.out.print("input something string: ");
		// It may not be supported by 'eclipse'
		if (
			( (c = System.console()) == null )
			|| ( (s = c.readLine()) == null )
		) {
			w("[none input]");
			return;
		}
		w("[Console::readLine] " + s);
		if (
			((p = c.readPassword("[%s]: ", "password")) == null)
		)  {
			w("[none password]");
			return;
		}
		w("[Console::readPassword] " + String.valueOf(p));
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
