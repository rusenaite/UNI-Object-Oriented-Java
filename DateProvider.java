import java.time.LocalDate;
import java.time.Month;
import java.util.Calendar;
import java.util.Date;
import java.util.List;
import java.util.stream.Collectors;
import java.time.ZoneId;
import java.time.format.DateTimeFormatter;
import java.time.Period;

public class DateProvider {
	/**
	 * Private static integers for the constructor of the class, each with a setter and getter.
	 * Used for entered interval end date validation.
	 */
	private static int year;
	private static int month;
	private static int day;
	
	public void setYear(int _year) { 
		if (LocalDate.now().getYear() <= _year ) DateProvider.year = _year; 
		else throw new IllegalArgumentException("Year value has to stay in interval [ <CurrentYear> ; 999999999 ].");
	}
	public void setMonth(int _month) { 
		if (LocalDate.now().getMonthValue() <= _month && 1 <= _month && _month <= 12) DateProvider.month = _month;
		else throw new IllegalArgumentException("Month value has to stay in interval [ <CurrentMonth> ; 12 ].");
	}
	public void setDay(int _day) { 
		if (LocalDate.now().getDayOfMonth() <= _day && 1 <= _day && _day <= 31) DateProvider.day = _day;
		else throw new IllegalArgumentException("Day value has to stay in interval [ <CurrentDay> ; 31 ].");
	}
	
	public int getYear() { return year; }
	public int getMonth() { return month; }
	public int getDay() { return day; }
	
	public DateProvider(int year, int month, int day) {
		setYear(year);
		setMonth(month);
		setDay(day);
	}
	
	/**
	 * Method finds all the dates in the given interval (using java.util.Calendar and java.util.Date).
	 * @return Prints selected dates in the interval.
	 */
	public static void findDatesInInterval() {
		List<LocalDate> sameDayOfTheWeekList = null;
		
		LocalDate start = LocalDate.now();
		
		Calendar calEndDate = Calendar.getInstance();
		calEndDate.set(year, month - 1, day);
		
		// convert Calendar to LocalDate
        Date date = calEndDate.getTime();
	    LocalDate endDate = date.toInstant().atZone(ZoneId.systemDefault()).toLocalDate();
	    
	    sameDayOfTheWeekList = start.datesUntil(endDate, Period.ofWeeks(1)).collect(Collectors.toList());
	    
	    Month currentMonth = sameDayOfTheWeekList.get(0).getMonth().minus(1);

	    for (LocalDate dateItem : sameDayOfTheWeekList) {
	    	if(dateItem.getMonth() != currentMonth) {
	    		System.out.println();
	    		currentMonth = dateItem.getMonth();
	    		printDate(dateItem);
	    		System.out.print(String.format("%02d", dateItem.getDayOfMonth()) + ", ");
	    	} else {
	    		System.out.print(String.format("%02d", dateItem.getDayOfMonth()) + ", ");	    	
	    	}
	    }
	}
	
	/**
	 * Method prints year and month in "yyyy-MM: " format.
	 * @param dateObject
	 * @return Prints year-month in format.
	 */
	public static void printDate(LocalDate dateObject) {
		DateTimeFormatter dateFormat = DateTimeFormatter.ofPattern("yyyy-MM: ");  
		System.out.print(dateFormat.format(dateObject));  
	}
	
	/**
	 * Method finds all the dates in the given interval (without java.util.Calendar and java.util.Date).
	 * @return Prints selected dates in the interval.
	 */
	public static void findDatesInInterval_() {
		List<LocalDate> sameDayOfTheWeekList = null;
		
	    LocalDate start = LocalDate.now();
	    LocalDate end = LocalDate.of(year, month, day);
	    
	    sameDayOfTheWeekList = start.datesUntil(end, Period.ofWeeks(1)).collect(Collectors.toList());
	    
	    Month currentMonth = sameDayOfTheWeekList.get(0).getMonth().minus(1);

	    for (LocalDate dateItem : sameDayOfTheWeekList) {
	    	if(dateItem.getMonth() != currentMonth) {
	    		System.out.println();
	    		currentMonth = dateItem.getMonth();
	    		printDate(dateItem);
	    		System.out.print(String.format("%02d", dateItem.getDayOfMonth()) + ", ");
	    	} else {
	    		System.out.print(String.format("%02d", dateItem.getDayOfMonth()) + ", ");
	    	}
	    }
	}
}
