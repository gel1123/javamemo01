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
			Statement s = c.createStatement(
				ResultSet.TYPE_SCROLL_INSENSITIVE,
				ResultSet.CONCUR_UPDATABLE)
		) {

			DatabaseMetaData m = c.getMetaData();
			w("[[ is supported the type of 'forward-only'? ]]");
			w(m.supportsResultSetType(
				ResultSet.TYPE_FORWARD_ONLY));
			w("[[ is supported the type of 'scroll insensitive'? ]]");
			w(m.supportsResultSetType(
				ResultSet.TYPE_SCROLL_INSENSITIVE));
			w("[[ is supported the type of 'scroll sensitive'? ]]");
			w(m.supportsResultSetType(
				ResultSet.TYPE_SCROLL_SENSITIVE));

			ResultSet r = s.executeQuery(
				"SELECT * FROM 01_free;");

			w("-- r.getType() --");
			final int type;
			w(type = r.getType());
			if (ResultSet.TYPE_FORWARD_ONLY == type) {
				w("the type of 'forward only'");
			} else if (ResultSet.TYPE_SCROLL_INSENSITIVE == type) {
				w("the type of 'scroll-insensitive'");
			} else if (ResultSet.TYPE_SCROLL_SENSITIVE == type) {
				w("the type of 'scroll-sensitive'");
			} else {
				w("unknown type");
			}
			if (r.isBeforeFirst()) w("<< r.isBeforeFirst >>");
			w("-- while (r.next()) --");
			while (r.next()) {
				if (r.isFirst()) w("<< r.isFirst >>");
				if (r.isLast()) w("<< r.isLast >>");
				w(r.getInt("id"));
				w(r.getString("name"));
			}
			if (r.isAfterLast()) w("<< r.isAfterLast >>");
			w("-- while (r.previous()) --");
			while (r.previous()) {
				if (r.isFirst()) w("<< r.isFirst >>");
				if (r.isLast()) w("<< r.isLast >>");
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
			while (r2 != null && r2.next()) {
				w(r2.getInt("id"));
				w(r2.getString("name"));
			}
			try {
				while (r.previous()) {
					w(r.getInt("id"));
					w(r.getString("name"));
				}
			} catch (SQLException e) {
				w("---- error ----");
				w("The variable [r] is already closed.");
				w(e);
			}
			w("---- SELECT * FROM table; ----");
			r = s.executeQuery("SELECT * FROM 01_free;");
			while (r.next()) w(
				r.getInt("id") + ": " + r.getString("name"));
			w("-- r.absolute(3) --");
			r.absolute(3);
			w(r.getInt("id") + ": " + r.getString("name"));
			w("-- r.moveToInsertRow() --"); // must move on update
			r.moveToInsertRow();
			r.updateInt(1, 10);
			r.updateString(2, "i_user1");
			r.insertRow();
			w("[insert row] "
				+ r.getInt("id") + ": " + r.getString("name"));
			r.updateInt(1, 11);
			r.updateString(2, "i_user2");
			r.insertRow();
			w("[insert row] "
				+ r.getInt("id") + ": " + r.getString("name"));
			w("-- r.moveToCurrentRow() --");
			r.moveToCurrentRow();
			w("[current row] "
				+ r.getInt("id") + ": " + r.getString("name"));

			w("---- deleteRow() ----");
			while (r.next()) {
				w("[try delete-row] "
					+ r.getInt("id") + ": " + r.getString("name"));
				r.deleteRow();
			}
			w("---- SELECT * FROM table; ----");
			r = s.executeQuery("SELECT * FROM 01_free;");
			while (r.next()) w(
				r.getInt("id") + ": " + r.getString("name"));

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
