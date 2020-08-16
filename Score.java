import java.util.Scanner;


class Frame {
	int[] throwArr;
	boolean isStrike = false;
	boolean isSpare = false;

	Frame nextFrame;

	// returns the total of the next n throws
	public int next(int n){
		int rv = 0;
		Frame curr = nextFrame;
		for(int i=0; curr != null && i < n; i++){
			for(int j=0; j < curr.throwArr.length; j++){
				rv += curr.throwArr[j];
			}
			curr = curr.nextFrame;
		}

		return rv;
	}

	public int frameScore() {
		if(isStrike){
			return 10 + next(2);
		}else if(isSpare){
			return 10 + next(1);
		}
		// open frame
		return throwArr[0] + throwArr[1];
	}

	public String toString() {
		return String.valueOf(frameScore());
	}

	// gets implicitly called by the FinalFrame constructor
	public Frame() {
		isStrike = false;
		isSpare = false;

		nextFrame = null;
		throwArr = new int[2];
	}

	public Frame(String str, Frame n) {
		nextFrame = n;

		// checking if the frame is a strike
		if(str.charAt(0) == 'X'){
			throwArr = new int[1];
			throwArr[0] = 10;
			isStrike = true;
			return;
		}

		throwArr =  new int[2];

		// checking for a spare
		if(str.charAt(1) == '/') {
			throwArr[0] = Character.getNumericValue(str.charAt(0));
			throwArr[1] = 10-throwArr[0];
			isSpare = true;
			return;
		}

		// open frame
		throwArr[0] = Character.getNumericValue(str.charAt(0));
		throwArr[1] = Character.getNumericValue(str.charAt(1));
	}
}


class FinalFrame extends Frame {
	public int frameScore() {
		int rv = 0;
		for(int i=0; i < throwArr.length; i++){
			rv += throwArr[i];
		}

		return rv;
	}

	public FinalFrame(String str) {
		// checking if there was a bonus throw
		if(str.length() >= 3){
			throwArr =  new int[3];
		}

		for(int i=0; i < throwArr.length; i++){
			if(str.charAt(i) == 'X'){
				throwArr[i] = 10;
				continue;
			}else if(str.charAt(i) == '/'){
				throwArr[i] = 10-throwArr[i-1];
			}else{
				throwArr[i] = Character.getNumericValue(str.charAt(i));
			}
		}
	}
}


class Game {
	Frame[] frames;

	private int finalScore() {
		int rv = 0;
		for(Frame f : frames){
			rv += f.frameScore();
		}

		return rv;
	}

	public String toString() {
		return String.valueOf(finalScore());
	}

	// separates the string and constructs the frames
	public Game(String str) {
		String[] frameStrings = str.split("-");
		frames = new Frame[10];

		// keeps track of the previous Frame we created in the loop
		Frame prev = null;

		for(int i=9; i >= 0; i--){
			// makes a FinalFrame instance instead at the start of the loop
			if(i == 9){
				frames[i] = new FinalFrame(frameStrings[i]);
			}else{
				frames[i] = new Frame(frameStrings[i], prev);
			}
			prev = frames[i];
		}
	}
}


class Score {
	public static void main(String[] args) {
		Scanner in = new Scanner(System.in);
		String results = in.nextLine();

		Game game = new Game(results);

		System.out.println(game.toString());
	}
}
