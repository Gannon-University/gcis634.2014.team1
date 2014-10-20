package zodicSign;

public class zodicSign {

	int zodiacSign(int month, int day, int year) {

		int result = 0;

		if (month == 3 && day >= 21 || month == 4 && day <= 19) {

			result = 1;

		} else if (month == 4 || month == 5 && day <= 20) {

			result = 2;

		} else if (month == 5 || month == 6 && day <= 20) {

			result = 3;

		} else {

			result = 4;

		}

		return result;

	}

}
