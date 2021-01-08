
class App implements
	pac01.Interface01,
	pac01.p1.Interface02

{

	private static final long S = System.currentTimeMillis();

	public static void main(String... args) {
		w(pac01.Interface01.method01());
		w(new App().method02());
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
