package tools;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;

public class FileIO {

	public static String readFile(String path) {
		
		System.out.println("Starting to read from file...");
		
		String content = "";
		BufferedReader br = null;
		FileReader fr = null;

		try {

			fr = new FileReader(path);
			br = new BufferedReader(fr);

			String currentLine;

			br = new BufferedReader(new FileReader(path));

			while ((currentLine = br.readLine()) != null) {
				content += currentLine;
			}

		} catch (IOException e) {

			e.printStackTrace();

		} finally {

			try {

				if (br != null)
					br.close();

				if (fr != null)
					fr.close();

			} catch (IOException ex) {

				ex.printStackTrace();

			}

		}
		
		System.out.println("Reading completed.");
		
		return content;
	}
}
