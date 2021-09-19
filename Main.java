/**
 * Program returns dates from today's date till desired date.
 */

public class Main {
	/**
	 * End date provided as constant integers.
	 */
	public final static int year = 2021;
	public final static int month = 12;
	public final static int day = 25;
	
	public static void main(String[] args) {
		try 
		{
			new DateProvider(year, month, day);
			
			DateProvider.findDatesInInterval();
			
			System.out.println("\n\nSame functionality but without java.util.Calendar and java.util.Date:");
			
			DateProvider.findDatesInInterval_();
		} 
		catch(IllegalArgumentException e)
		{
			e.printStackTrace();
			return;
		}
	}
}
