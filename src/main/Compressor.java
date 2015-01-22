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
	private byte[] bytes;
	
	// read a text file and save the text as an array of characters (text)
	public void read(String path){
		
		try {
			fr = new FileReader(path);
			br = new BufferedReader(fr);
		}
		catch (Exception e) {
			e.printStackTrace();
			return;
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
	public void huffman(String path) {
		HuffmanTree tree = new HuffmanTree();
		tree.generateTree(getProbabilities());
		huffmancodes = tree.generateCodes();
		stringToBytes();
		bytesToFile(path);
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
	
	// CONVERSION
	
	public String textToString() {
		
		String all = "";
		
		for (Character c: text) {
			all += huffmancodes.get(c);
		}
		return all;
	}
	
	public void stringToBytes() {
		
		String encoded = textToString();
		int length = 0;
		String temp = "";
		int bytecounter = 0;
		
		if (encoded.length() % 8 == 0)
			length = encoded.length()/8;
		else 
			length = (int) (encoded.length()/8) + 1;
		
		bytes = new byte[length];
		
		for (int i = 0; i < encoded.length(); i++) {
			temp += encoded.charAt(i);
			if (temp.length() == 8) {
				bytes[bytecounter] = (byte) stringToSignedInt(temp);
				temp = "";
				bytecounter++;
			}
			if (i == encoded.length() - 1 && temp.length() != 8) {
				for (int j = 0; j < 8 - temp.length(); j++) {
					temp += "0";
				}
				bytes[bytecounter] = (byte) stringToSignedInt(temp);
			}
		}
		
	}
	
	public int stringToSignedInt(String input) {
		
		int num = 0;
		for (int i = input.length() - 1; i >= 0; i--) {		
			if (i == 0) {
				if (input.charAt(i) == '1')
					num -= 128;
				break;
			}	
			if (input.charAt(i) == '1')
				num += Math.pow(2, (input.length() - 1) - i);
		}
		return num;
	}
	
	// OUTPUT
	
	public void bytesToFile(String path) {
		
		if (bytes == null) return;
		try {
			FileOutputStream fos = new FileOutputStream(path);
			try {
				fos.write(bytes);
				fos.close();
			} catch (IOException e) {
				e.printStackTrace();
			}
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		}
	}
	
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
			if (entry.getKey() == '\n')
				System.out.println("newline : " + entry.getValue());				
			else 
				System.out.println(entry.getKey() + " : " + entry.getValue());
		}
	}
	
	public void printProbabilities() {
		for (HuffmanNode n: this.getProbabilities()) {
			if (n.getCharacter() == '\n')
				System.out.println("newline : " + n.getValue() + ", ");
			else
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
