import java.io.*;
import java.util.*;

public class CipherRunner {
	public static void main(String[] args) {
		Scanner kb = new Scanner(System.in);
		String start = "y";
		do {
			System.out.println("-------------------------------------------");
			System.out.print("Would you like to encode or decode (e/d)? ");
			String choice = kb.nextLine().trim();
			if(!(choice.equals("e")||choice.equals("d"))) {
				throw new IllegalArgumentException("must enter an e or d");
			}
			
			System.out.print("Enter the text you want to encode/decode: ");
			String text = kb.nextLine().trim();
			
			System.out.print("Enter the key you want to use: ");
			String[] k = kb.nextLine().trim().split("");
			int[] key = new int[k.length];
			for(int x = 0;x<k.length;x++) {
				key[x] = Integer.parseInt(k[x])-1;
			}
			
			TranspositionCipher cip = new TranspositionCipher(text,key.length);
			if(choice.equals("e")) {
				System.out.println("Original message: \"" + text + "\"");
				System.out.println("Cipher key : " + Arrays.toString(k).replaceAll("[\\[\\], ]", ""));
				System.out.println("Encrypted message: \"" + cip.encode(key) + "\"");
			}
			else if(choice.equals("d")) {
				System.out.println("Encrypted message: \"" + text + "\"");
				System.out.println("Cipher key : " + Arrays.toString(k).replaceAll("[\\[\\], ]", ""));
				System.out.println("Original message: \"" + cip.decode(key) + "\"");
			}
			cip.printMat();
			
			System.out.print("\nDo you want to go again (y/n)? ");
			start = kb.nextLine();
		} while(start.equals("y"));
		kb.close();
	}
}
