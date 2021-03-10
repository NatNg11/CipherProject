import java.util.*;
import java.io.*;

public class TranspositionCipher {
	char[][] mat;
	String text;
	int keylen;
	public TranspositionCipher(String t, int l) {
		text = t;
		keylen = l;
		if(keylen>text.length()) { throw new IllegalArgumentException("key is too long"); }
	}
	
	public String encode(int[] key) {
		mat = new char[(int)(Math.ceil(text.length()/(double)keylen))][keylen];
		
		int index = 0;
		for(int x = 0;x<mat.length;x++) {
			for(int y = 0;y<mat[x].length;y++) {
				if(index>=text.length()) { mat[x][y] = ' '; } 
				else {mat[x][y] = text.charAt(index++); }
			}
		}
		
		String ret = "";
		for(int y : key) {
			for(int x = 0;x<mat.length;x++) {
				//if(mat[x][y]!=' ') {
					ret += mat[x][y];
				//}
			}
		}
		
		return ret;
	}
	
	public String decode() {
		return "";
	}
	
	public void printMat() {
		System.out.println();
		for(char[] a : mat) {
			System.out.println(Arrays.toString(a));
		}
	}
}
