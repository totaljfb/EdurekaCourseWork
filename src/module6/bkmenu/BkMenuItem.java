package module6.bkmenu;

public class BkMenuItem {
	protected String name = null;
	protected String price = null;
	protected String description = null;
	protected String calories = null;
	
	@Override
	public String toString(){
		return name + "/" + price + "/" + calories + " (" + description + ")";
	}
	
}
