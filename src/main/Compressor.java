package main;
import java.io.*;
import java.util.ArrayList;
import java.util.HashMap;

public class Compressor {

	private FileReader fr;
	private BufferedReader br;
	private ArrayList<Character> text;
	private ArrayList<Character> encodedtext;
	
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
				currentline += (templine + "\n");
				char[] chars = currentline.toCharArray();
				for (int i = 0; i < chars.length; i++) {
					text.add(chars[i]);
				}
			}
		} catch (IOException e) {
			e.printStackTrace();
		}
		
	}
	
	public ArrayList<Character> swencode(ArrayList<Character> input, int windowsize, int searchlength) {	
		return null;
	}
	
	public void huffman(String file_path) {
		
	}
	
	// returns array of leaf nodes
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
	
	public ArrayList<Character> getOriginal() {
		return text;
	}
	
	public ArrayList<Character> getEncoded() {
		return encodedtext;
	}
	
}
