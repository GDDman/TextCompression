package main;

import java.util.ArrayList;
import java.util.Map;

public class Test {
	
	public static void main(String[] args) {
		
		Compressor comp = new Compressor();
		comp.read("src/res/test.txt");
		
		/*
		for (char c: comp.getOriginal()) {
			System.out.print(c);
		}
		*/
		
		for (HuffmanNode n: comp.getProbabilities()) {
			System.out.println(n.getCharacter() + ": " + n.getValue() + ", ");
		}
		
		comp.huffman();
		
		for (Map.Entry<Character, ArrayList<Integer>> entry : comp.getHCodes().entrySet()) {
		    System.out.println(entry.getKey()+" : "+entry.getValue().toString());
		}
		
		comp.writeToFile("src/res/output.txt", comp.getOriginal());
		
	}
}
