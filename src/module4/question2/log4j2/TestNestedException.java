package module4.question2.log4j2;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

public class TestNestedException {
	private static final Logger logger = LogManager.getLogger(TestNestedException.class);
	
	public static void main(String[] args) {
		logger.info("Entering main method: ");
		TestNestedException the = new TestNestedException();
		int[] array1 = {2,4,6};
		int[] array2 = {1,2,3,4};
			try {
				the.divideArrays(array1, array2);
			} catch (ArrayDataException e) {
				logger.info(e.getMessage(),e);
			} catch (ArrayDataException1 e) {
				logger.info(e.getMessage(),e);
			}	
		logger.info("Exiting main method");
	}
	
	public int[] divideArrays(int[] array1, int[] array2) throws ArrayDataException, ArrayDataException1{
		int[] result = null;
		if(array1.length != array2.length) {
			throw new ArrayDataException("Cannot divide arrays of different lengths.");
		}
		return result;
	}

}
