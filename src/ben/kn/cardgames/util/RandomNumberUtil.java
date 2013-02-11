package ben.kn.cardgames.util;

import java.util.Random;

/**
 * Utility class for creating random numbers. 
 *
 * @author Ben Knear (bknear@gmail.com)
 */
public class RandomNumberUtil {
	private Random ranGen = new Random();

	public int generateRandomBetween(int min, int max) {
		int randomNumber = ranGen.nextInt(max - min + 1);
		randomNumber += min;
		return randomNumber;
	}
}