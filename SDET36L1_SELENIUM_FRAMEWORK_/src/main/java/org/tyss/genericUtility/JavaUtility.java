package org.tyss.genericUtility;

import java.text.SimpleDateFormat;
import java.time.format.DateTimeFormatter;
import java.time.temporal.ChronoField;
import java.util.Date;
import java.util.Locale;
import java.util.Random;

public final class JavaUtility {
	//generate random no
public int getRandomNumber() {
return new Random().nextInt(800);
}
//converting string to long data type
public long convertStringToLong(String stringData) {
	return Long.parseLong(stringData);
}
//print value
public void printStatement(String value) {
	System.out.println(value);
}
/**
 * To generate current date
 * @param Date
 */
public void GenerateCurrentDate(String Date){
	Date date=new Date();
	SimpleDateFormat sdf=new SimpleDateFormat("dd/MM/yyyy");
	String dt=sdf.format(date);
}
public int convertMonthFromStringToInt(String monthName,String strategy) {
	return DateTimeFormatter.ofPattern(strategy)
			.withLocale(Locale.ENGLISH)
			.parse(monthName)
			.get(ChronoField.MONTH_OF_YEAR);
}
}