import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;

public class CalendarTest {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		SimpleDateFormat sdf1 = new SimpleDateFormat("yyyy-MM-dd");
//		SimpleDateFormat sdf2 = new SimpleDateFormat("yyyy-MM");
//		sdf1.setLenient(false);
//		sdf2.setLenient(false);
//		try {
//			System.out.println(sdf1.parse("2018-11-11"));
//			System.out.println(sdf1.format(sdf1.parse("2018-11-11")));
//			//System.out.println(sdf2.parse("2018-13"));
//		} catch (ParseException e) {
//			// TODO Auto-generated catch block
//			e.printStackTrace();
//		}
		try {
			Calendar cal = Calendar.getInstance();
			cal.setWeekDate(2018, 47, 1);
			String converted_date = sdf1.format(cal.getTime()); 
			System.out.println(cal.getTime());
			System.out.println(converted_date);
		}catch(Exception e) {
			e.printStackTrace();
		}
		
	}

}
