package I18n;
import java.util.Locale;
import java.util.ResourceBundle;
public class I18nDemo {

	public static void main(String[] args) {
		Locale locale = new Locale("en","IN");
		ResourceBundle bundle = ResourceBundle.getBundle("myres",locale);
		String country = bundle.getString("country_name");
		String capital = bundle.getString("capital_name");
		System.out.println("The capital of " + country + " is " + capital +".");
	}

}
