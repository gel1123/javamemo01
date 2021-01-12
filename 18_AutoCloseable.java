class App {

	static class Hoge implements AutoCloseable {
		public String name;

		public Hoge(String str) {
			this.name = str;
		} 
		@Override
		public void close() {
			App.w("-- close " + this.name + " --");
		}
	}

	public static void main(String... args) {
		Hoge h1 = new App.Hoge("h1");
		h1.close();

		try (Hoge h2 = new App.Hoge("h2");
			Hoge h3 = new App.Hoge("h3")) {
			w("<< try-with-resources [none catch] >>");
		}
		w("---- end ----");
		w("The order of closing resource is 'stack'");
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
