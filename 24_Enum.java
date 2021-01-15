enum Directions {
	NORTH, EAST, WEST, SOUTH, OTHER
};
enum Cards {
	SPADE, HEART, CLUB, DIAMOND, OTHER
}
class App {
	public static void main(String... args) {
		w("---- start ----");

		w("-- Directions.EAST.ordinal() --");
		w(Directions.EAST.ordinal());
		w("-- CARDS.HEART.ordinal() --");
		w(Cards.HEART.ordinal());
		w("-- Directions.EAST --");
		w(Directions.EAST);
		w("-- CARDS.HEART --");
		w(Cards.HEART);
		w("-- Directions.EAST.equals(Cards.HEART) --");
		w(Directions.EAST.equals(Cards.HEART));
		w("-- Directions.OTHER.equals(Cards.OTHER) --");
		w(Directions.OTHER.equals(Cards.OTHER));

		w("---- end ----");
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
