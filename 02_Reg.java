import java.util.regex.Matcher;
import java.util.regex.Pattern;

class Reg {

	public static void main(String[] args) {
		if (args == null || args.length < 1)
			System.out.println("No arguments");

		String regexp = "# You can also write comments.\n"
				+ "[^0-9]+";
		Pattern pattern = Pattern.compile(regexp, Pattern.COMMENTS);
		Matcher matcher = pattern.matcher(args[0]);

		System.out.println(matcher.matches());
		System.out.println(matcher.find());
		System.out.println(matcher.replaceAll(""));
	}
}
