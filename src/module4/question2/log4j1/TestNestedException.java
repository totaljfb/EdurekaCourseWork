package module4.question2.log4j1;

import org.apache.log4j.Logger;

public class TestNestedException {
	private static final Logger logger = Logger.getLogger(TestNestedException.class);
	
	public static void main(String[] args) {
		logger.info("Entering main method: ");
		TestNestedException the = new TestNestedException();
		int[] array1 = {2,4,6};
		int[] array2 = {1,2,3,4};
			try {
				the.divideArrays(array1, array2);
			} catch (ArrayDataException e) {
				logger.error(e.getMessage(),e);
			} catch (ArrayDataException1 e) {
				logger.error(e.getMessage(),e);
			}	
		logger.info("Exiting main method");
	}
	
	public int[] divideArrays(int[] array1, int[] array2) throws ArrayDataException, ArrayDataException1{
		int[] result = null;
		logger.debug("A debug level message is here.");
		if(array1.length != array2.length) {
			throw new ArrayDataException("Cannot divide arrays of different lengths.");
		}
		return result;
	}

}
