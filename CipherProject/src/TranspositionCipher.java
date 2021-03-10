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
				if(index>=text.length()) { mat[x][y] = '-'; } 
				else {mat[x][y] = text.charAt(index++); }
			}
		}
		
		String ret = "";
		for(int y : key) {
			for(int x = 0;x<mat.length;x++) {
					ret += mat[x][y];
			}
		}
		
		return ret;
	}
	
	public String decode(int[] key) {
		int r = text.length()/keylen, c = keylen;
		mat = new char[r][c];
		int index = 0;
		System.out.println(text);
		for (int i = 0; i < c; i++) {
			for (int j = 0; j < r; j++) {
				mat[j][i] = text.charAt(index++);
			}
		}
		char[][] copy = new char[r][c];
		int k = 0;
		for (int item : key) {
			for (int i = 0; i < r; i++) {
				copy[i][item] = mat[i][k];
			}
			k++;
		}
		String ret = "";
		for (int i = 0; i < r; i++) {
			ret += new String(copy[i]);
		}
		return ret.replaceAll("-","");
	}
	
	public void printMat() {
		System.out.println();
		for(char[] a : mat) {
			System.out.println(Arrays.toString(a));
		}
	}
}
