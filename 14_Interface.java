
class App implements
	pac01.Interface01,
	pac01.p1.Interface02

{

	private static final long S = System.currentTimeMillis();

	public static void main(String... args) {
		w(pac01.Interface01.method01());
		w(new App().method02());
		w(lam(() -> "exe with a lambda expression!"));
		w(lam(new pac01.Interface01() {
			@Override
			public String getStr() {
				return "exe with a anonymous class!";
			}
		}));
		w(lam2((int i, String s) -> "[int i] "+i
			+", [String s] "+s));
		w(lam2((i, s) -> "[i] "+i
			+", [s] "+s));
	}
	public static String lam(pac01.Interface01 i) {
		return i.getStr();
	}
	public static String lam2(pac01.Interface03 i) {
		return i.getStr(0, "lam2");
	}
	@Override
	public String getStr() {
		return "The abstract-method used internally"
			+ " by default-method"
			+ " was implements in App class";
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
