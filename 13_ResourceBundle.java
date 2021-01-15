import java.io.File;
import java.net.MalformedURLException;
import java.net.URL;
import java.net.URLClassLoader;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;
import java.util.Locale;
import java.util.ResourceBundle;

class UseResourceBundle {

	private static final long S = System.currentTimeMillis();

	public static void main(String... args) {

		try {
			final File p = Paths.get("./prop").toFile();
			final URLClassLoader loader = new URLClassLoader(
				new URL[] {p.toURI().toURL()});

			Locale ja = Locale.JAPAN;
			Locale us = Locale.US;

			List<Locale> list = new ArrayList<Locale>() {
				{
					this.add(ja);
					this.add(us);
				}
			};
			for (Locale locale : list) {
				w("______________________");
				w(locale);
				ResourceBundle rb1 = ResourceBundle.getBundle(
					"pac01.Resource", locale
				);
				ResourceBundle rb2 = ResourceBundle.getBundle(
					"source", locale, loader
				);
				w("<< getBundle from class file >>");
				w("-- rb1.getString(\"var\") --");
				w(rb1.getString("var"));
				w("-- rb1.getString(\"initializer\") --");
				w(rb1.getString("initializer"));

				w("<< getBundle from properties file >>");
				w("-- rb2.getString(\"function\") --");
				w(rb2.getString("function"));
				w("-- rb2.getString(\"const\") --");
				w(rb2.getString("const"));
			};
		} catch (MalformedURLException e) {
			w(e);
		}
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
	public static long lap() {
		return System.currentTimeMillis() - S;
	}
}
