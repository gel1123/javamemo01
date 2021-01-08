package pac01;

import java.util.ListResourceBundle;

public class Resource_en_US extends ListResourceBundle {
	@Override
	protected Object[][] getContents() {
		Object[][] o = {
			{"var", "variables"},
			{"initializer", "initializer"}
		};
		return o;
	}
}
