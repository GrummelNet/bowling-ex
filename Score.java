import java.util.Scanner;


class Frame {
	int firstThrow;
	int secondThrow;
	boolean isStrike = false;
	boolean isSpare = false;

	// Frame nextFrame;

	public int frameScore() {
		return firstThrow + secondThrow;
	}

	public String toString() {
		return String.valueOf(frameScore());
	}

	// takes in a string
	public Frame(String str) {
		// checking if the frame is a strike
		if(str.charAt(0) == 'X'){
			firstThrow = 10;
			secondThrow = 0;
			isStrike = true;
			return;
		}
		// checking for a spare
		if(str.charAt(1) == '/') {
			firstThrow = Character.getNumericValue(str.charAt(0));
			secondThrow = 10-firstThrow;
			isSpare = true;
			return;
		}

		// open frame
		firstThrow = Character.getNumericValue(str.charAt(0));
		secondThrow = Character.getNumericValue(str.charAt(1));
	}
}


// class FinalFrame extends Frame {
//
// }


class Score {
	static Frame frame;

	public static void main(String[] args) {
		Scanner in = new Scanner(System.in);
		String results = in.nextLine();

		frame = new Frame(results);

		System.out.println("Score is: " + frame);
	}
}
