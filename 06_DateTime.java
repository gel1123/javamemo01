import java.time.*;
import java.time.chrono.*;
import java.time.format.*;
import java.time.temporal.*;
import java.time.zone.*;
import java.util.Arrays;

class UseDateTimeAPI {
	public static void main(String... args) {

		for (Object o : Arrays.asList(
			LocalDate.now(),
			LocalTime.now(),
			LocalDateTime.now(),
			LocalDate.of(1994, 3, 15),
			LocalDate.from(LocalDateTime.now()),
			LocalTime.from(LocalDateTime.now()),
			OffsetDateTime.now(),
			ZonedDateTime.now(),
			LocalDateTime.from(ZonedDateTime.now()),
			LocalDateTime.from(OffsetDateTime.now())
		)) {
			w("_____________________");
			w(o.getClass().getName());
			w(o);
		}

		for (DayOfWeek w : DayOfWeek.values()) {
			w(w);
		}
		
		w(
			"---- LocalDate.parse(\"1994-03-15\") ----"
		);
		w(LocalDate.parse("1994-03-15"));

		w("---- compare date ----");
		LocalDate b1 = LocalDate.parse("2016-09-14");
		LocalDate b2 = LocalDate.parse("2019-11-23");	
		LocalDate b3 = LocalDate.parse("2019-11-23");	

		w("b1 is" + b1);
		w("b2 is " + b2);
		w("b3 is " + b3);
		w("b1.isAfter(b2)");
		w(b1.isAfter(b2));
		w("b1.isBefore(b2)");
		w(b1.isBefore(b2));
		w("b1.isEqual(b2)");
		w(b1.isEqual(b2));
		
		w("b3.isAfter(b2)");
		w(b3.isAfter(b2));
		w("b3.isBefore(b2)");
		w(b3.isBefore(b2));
		w("b3.isEqual(b2)");
		w(b3.isEqual(b2));

		w("_____________________");
		w("\n---- ChronoUnit ----");
		LocalDateTime localDateTime = LocalDateTime.now();
		w("localDateTime is "
			+ localDateTime);
		for (ChronoUnit u
			: ChronoUnit.values()) {
			w("-- " + u + " --");
			w(u.getDuration());

			try {
				w("localDateTime.isSupported(u)");
				w(localDateTime.isSupported(u));
				w("localDateTime.minus(1, u)");
				w(localDateTime.minus(1, u));
				w("localDateTime.plus(1, u)");
				w(localDateTime.plus(1, u));
				w("localDateTime.truncatedTo(u)");	
				w(localDateTime.truncatedTo(u));	
				w("localDateTime.until("
					+ "LocalDateTime.now(), u)");
				w(localDateTime.until(
					LocalDateTime.now(), u));
			} catch (Exception e) {
				w(e);
			}
		}
	}
	public static void w(Object s) {
		System.out.println(s);
	}
}
