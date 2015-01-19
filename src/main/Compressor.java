package main;
import java.io.*;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

public class Compressor {

	private FileReader fr;
	private BufferedReader br;
	private ArrayList<Character> text = new ArrayList<Character>();
	private ArrayList<Character> encodedtext = new ArrayList<Character>();
	private HashMap<Character, String> huffmancodes = null;
	
	// read a text file and save the text as an array of characters (text)
	public void read(String path){
		
		try {
			fr = new FileReader(path);
			br = new BufferedReader(fr);
		}
		catch (Exception e) {
			e.printStackTrace();
			System.exit(0);
		}
		
		String currentline = "";
		String templine = "";
		
		try {
			while ((templine = br.readLine()) != null) {
				currentline = (templine + '\n');
				char[] chars = currentline.toCharArray();
				for (int i = 0; i < chars.length; i++) {
					text.add(chars[i]);
				}
			}
		} catch (IOException e) {
			e.printStackTrace();
		}
		
	}
	
	// do sliding window encoding on the original text. More efficient for human language (repeating strings)
	public ArrayList<Character> swencode(ArrayList<Character> input, int windowsize, int searchlength) {	
		return null;
	}
	
	// do huffman encoding on the original text. More efficient for large files with smaller character variation. For english text, codes should be smaller than 8bits.
	public void huffman() {
		HuffmanTree tree = new HuffmanTree();
		tree.generateTree(getProbabilities());
		huffmancodes = tree.generateCodes();
	}
	
	// returns array of leaf nodes for usage in huffman encoding
	public ArrayList<HuffmanNode> getProbabilities() {
		
		boolean charexists = false;
		ArrayList<HuffmanNode> leafnodes = new ArrayList<HuffmanNode>();
		for (int i = 0; i < text.size(); i++) {
			charexists = false;
			for (int j = 0; j < leafnodes.size(); j++) {		
				if (leafnodes.get(j).getCharacter() == text.get(i)) {
					leafnodes.get(j).incvalue();
					charexists = true;
				}
			}
			if (!charexists) leafnodes.add(new HuffmanNode(1, text.get(i)));
		}
		return leafnodes;
	}
	
	// OUTPUT
	
	public void writeToFile(String path, ArrayList<Character> content) {
		
		StringBuilder builder = new StringBuilder(content.size());
		
		for (Character c: content) {
			builder.append(c);
		}		
		String output = builder.toString();
		
		try {
			FileWriter fw = new FileWriter(path);
			BufferedWriter bw = new BufferedWriter(fw);
			bw.write(output);
			bw.close();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	
	//PRINTING
	
	public void printCodes() {
		for (Map.Entry<Character, String> entry : huffmancodes.entrySet()) {
		    System.out.println(entry.getKey() + " : " + entry.getValue());
		}
	}
	
	public void printProbabilities() {
		for (HuffmanNode n: this.getProbabilities()) {
			System.out.println(n.getCharacter() + ": " + n.getValue() + ", ");
		}
	}
	
	// GETTERS AND SETTERS
	
	public ArrayList<Character> getOriginal() {
		return text;
	}
	
	public ArrayList<Character> getEncoded() {
		return encodedtext;
	}
	
	public HashMap<Character, String> getHCodes() {
		return huffmancodes;
	}
	
}
