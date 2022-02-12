package mortageplan;

import org.junit.Assert;
import org.junit.Test;



public class mortageTest {

	@Test
	public void isnumerictest() {
		Mortage num = new Mortage();
		Assert.assertEquals(true, num.isNumeric("2"));
		Assert.assertEquals(true, num.isNumeric("2.32"));
		Assert.assertEquals(false, num.isNumeric(""));
		Assert.assertEquals(false, num.isNumeric("ada"));
	}
	
	@Test
	public void powerOftest() {
		Mortage num = new Mortage();
		Assert.assertEquals(4, num.powerOf(2, 2),0);
		Assert.assertEquals(1, num.powerOf(2, 0),0);
		Assert.assertEquals(0, num.powerOf(0, 2),0);
	}
}
