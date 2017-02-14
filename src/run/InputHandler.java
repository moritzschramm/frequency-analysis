package run;

import java.util.Scanner;

import tools.FileIO;

public class InputHandler {

	public static void handle(String args[]) {
		
		String input = "";
		
		if(args.length == 0 || args.length > 2) {	// input text via command line
			
			Scanner in = new Scanner(System.in);
			
			if(args.length > 2)
				System.out.println("Error reading command line argument. Make sure you have surrounded the words with quotation marks. You can now re-enter your text.\n");
			
			System.out.print("Enter encrypted string: ");
			
			in.useDelimiter("\n");			// else, in.next() will only return first word
			
			input = in.next();
			
			in.close();
			
		} else if(args[0].equals("-f") && args.length == 2) {	// text in file
			
			String path = args[1];
			
			input = FileIO.readFile(path);
			
		} else if(args.length == 1) {	// text as argument
			
			input = args[0];	
		
		} else {						// everything else will show help
			
			if(args.length > 0) {
				if(args[0] != "--help") {
					
					System.out.println("Unknown or wrong argument.\n");
				}
			}
			
			InputHandler.showHelp();
			
			return;
		}
		
		// The text analysis starts here
		
		TextAnalyzer analyzer = new TextAnalyzer(input);
		
		analyzer.analyze();
		
		System.out.println("Printing results:\n");
		
		analyzer.printResults();
	}
	
	public static void showHelp() {
		
		System.out.println("usage:");
		System.out.println("\t<programName> <EncryptedStringSurroundedByQutationMarks>");
		System.out.println("\t<programName> -f <PathToFile>");
		System.out.println("\t<programName>");
	}
}
