package DatabaseProgramming.Gamelib.Utility;

public class Validation {
	
	public static boolean validateStrings(String[] userInputs) {
			
			for(String string : userInputs) {
				if(string.trim().isEmpty()) {
					return false;
				}
			}
			
			return true;
	}
	
	public static boolean validateNumbers(String[] userInputs) {
			
			for(String string : userInputs) {
				if(string.trim().isEmpty()) {
					return false;
				}
				
				try {
					Integer.parseInt(string.trim());
				}
				catch(NumberFormatException e) {
					return false;
				}
				
			}
			
			return true;
	}

}
