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
			LocalDate.of(1994, 3, 15)
		)) {
			System.out.println("_____________________");
			System.out.println(o.getClass().getName());
			System.out.println(o);
		}
	}
}
