import java.io.*;
import java.util.*;

public class HuffmanTree {
	
	private Queue<HuffmanNode> q;

	// String constructor
	public HuffmanTree(String s) throws FileNotFoundException {
		q = new PriorityQueue<>();
		// finds frequencies of letters
		char[] c = s.toCharArray();
		Map<Character,Integer> freqMap = new HashMap<>();
		for(char item : c) {
			if(!freqMap.containsKey(item))
				freqMap.put(item, 0);
			freqMap.put(item, freqMap.get(item)+1);
		}
		for(Character item : freqMap.keySet())
			q.offer(new HuffmanNode(item,freqMap.get(item)));
	
		// constructs Huffman tree
		while(q.size() > 1) {
			HuffmanNode l = q.poll(), r = q.poll();
			HuffmanNode te = new HuffmanNode(null,l.freq + r.freq);
			te.l = l; te.r = r;
			q.offer(te);
		}
	}
	
	// prints code of each huffman
	public void save(PrintStream output) {
		printTree(q.peek(),"",output);
	}
	private void printTree(HuffmanNode n, String path, PrintStream out) {
		if(n == null) return;
		if(n.l == null && n.r == null) {
			out.println((int)n.c);
			out.println(path);
		}
		printTree(n.l,path+"0",out);
		printTree(n.r,path+"1",out);
	}
	
	// Input bitstream constructor
	public HuffmanTree(Scanner f) throws FileNotFoundException {
		// reads in and creates tree from file
		q = new PriorityQueue<>();
		HuffmanNode root = new HuffmanNode(null,-1);
		while(f.hasNext()) {
			HuffmanNode current = root;
			char c = (char)Integer.parseInt(f.nextLine());
			char[] path = f.nextLine().toCharArray();
			for(char item : path) {
				if(item == '0') {
					if(current.l == null) current.l = new HuffmanNode(null,-1);
					current = current.l;
				}
				else {
					if(current.r == null) current.r = new HuffmanNode(null,-1);
					current = current.r;
				}
			}
			current.c = c;
			current = root;
		}
		q.offer(root);
	}

	public void translate(String in, PrintStream out) {
		HuffmanNode current = q.peek();
		char[] c = in.toCharArray();
		for(char item : c) {
			if(item == '0') current = current.l;
			else current = current.r;
			if(current.l == null && current.r == null) {
				out.print(current.c);
				current = q.peek();
			}
		}
		out.println();
	}
	
	public void levelOrder() {
		System.out.println(levelOrder(q.peek()));
	}
	private String levelOrder(HuffmanNode n) {
		Queue<HuffmanNode> q = new LinkedList<>();
		q.offer(n);
		String ret = "";
		while(!q.isEmpty()) {
			HuffmanNode temp = q.poll();
			if(temp == null) continue;
			ret += (temp + " | ");   
			q.offer(temp.l);
			q.offer(temp.r);
		}
		ret = ret.substring(0,ret.length()-3);
		return ret;
	}

	private static class HuffmanNode implements Comparable<HuffmanNode> {
		int freq;
		Character c;
		HuffmanNode l, r;
		public HuffmanNode(Character c, int freq) {
			this.c = c; this.freq = freq;
			l = r = null;
		}
		public String toString() {
			return "(" + c + " " + freq + ")";
		}
		@Override
		public int compareTo(HuffmanNode o) {
			return Integer.compare(this.freq,o.freq);
		}
	}
}
