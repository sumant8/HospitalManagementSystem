package practie;

import java.time.format.DateTimeFormatter;
import java.time.temporal.ChronoField;
import java.util.Locale;

public class DateConverter1 {

	public static void main(String[] args) {
		String s="January";
		String s1 = s.substring(0,1).toUpperCase()+s.substring(1).toLowerCase();
		int d=DateTimeFormatter.ofPattern("MMMM")
				.withLocale(Locale.ENGLISH).parse(s1)
				.get(ChronoField.MONTH_OF_YEAR);
		System.out.println(d);
	}

}
