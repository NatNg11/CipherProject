import java.util.*;
import java.io.*;

public class TranspositionCipher {
	char[][] mat;
	public TranspositionCipher(String text, int len) {
		if(len>text.length()) { throw new IllegalArgumentException("key is too long"); }
		mat = new char[(int)(Math.ceil(text.length()/(double)len))][len];
		
		int index = 0;
		for(int x = 0;x<mat.length;x++) {
			for(int y = 0;y<mat[x].length;y++) {
				if(index>=text.length()) { mat[x][y] = ' '; } 
				else {mat[x][y] = text.charAt(index++); }
			}
		}
		
		for(char[] a : mat) {
			System.out.println(Arrays.toString(a));
		}
		System.out.println();
	}
	
	public String encode(int[] key) {
		String ret = "";
		for(int y : key) {
			for(int x = 0;x<mat.length;x++) {
				if(mat[x][y]!=' ') {
					ret += mat[x][y];
				}
			}
		}
		return ret;
	}
	
	public String decode() {
		return "";
	}
}
