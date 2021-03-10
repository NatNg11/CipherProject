import java.util.*;
import java.io.*;
import java.text.*;
import java.math.*;
import java.lang.Math.*;

public class HuffmanMain {
	
	private static final String CODE_FILE = "huffman.code";
	public void run() throws Exception {
		Scanner f = new Scanner(System.in);
		System.out.print("Give me a string to compress: ");
		String input = f.nextLine();
		//String input = "aba ab cabbb";
		
		HuffmanTree tree = new HuffmanTree(input);
		tree.save(new PrintStream(new File(CODE_FILE)));
		System.out.println("Level Order Traversal of the Compressed HuffmanTree");
		tree.levelOrder();
		
		System.out.println();
		
		System.out.print("Give me a file to read from (or press enter for default): ");
		input = f.nextLine();
		if(input.equals("")) input = CODE_FILE;
		HuffmanTree inp = new HuffmanTree(new Scanner(new File(input)));
		System.out.println("Level Order Traversal of the Compressed HuffmanTree");
		inp.levelOrder();
		
		System.out.println();
		
		System.out.print("Give me a bitstream to translate from the previous HuffmanTree: ");
		input = f.nextLine();
		inp.translate(input, new PrintStream(System.out));
		
		f.close();
	}

	public static void main(String[] args) throws Exception {
		new HuffmanMain().run();
	}

}