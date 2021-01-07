import java.sql.*;

class App {

	private static final String URL
		= "jdbc:mysql://127.0.0.1/sandbox"; // port is 80 
	private static final String USER = "user1";
	private static final String PASS = "";
	private static final long S = System.currentTimeMillis();

	public static void main(String... args) {

		// if JDBC 3.0 :
		// Class.forName("[JDBC Driver's Class Name]");

		// JDBC 4.0
		try (Connection c = DriverManager
			.getConnection(URL, USER, PASS);
			Statement s = c.createStatement()) {
			ResultSet r = s.executeQuery("SELECT * FROM 01_free");
			while (r.next()) {
				w(r.getInt("id"));
				w(r.getString("name"));
			}
		} catch (Exception e) {
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
