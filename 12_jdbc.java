import java.sql.*;

class App {

	private static final String URL
		= "jdbc:mysql://127.0.0.1/sandbox";
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
			ResultSet r = s.executeQuery("SELECT * FROM 01_free;");
			while (r.next()) {
				w(r.getInt("id"));
				w(r.getString("name"));
			}
			int i = s.executeUpdate(
				"INSERT INTO 01_free VALUE (4, 'hoge');");
			w("[executeUpdate] "+ i);
			boolean b = s.execute(
				"INSERT INTO 01_free VALUE (5, 'fuge');");
			w("[execute] " + b);
			w("[getUpdateCount()] " + s.getUpdateCount());
			ResultSet r2 = s.getResultSet();
			w("[getResultSet()]");
			while (r2.next()) {
				w(r2.getInt("id"));
				w(r2.getString("name"));
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
