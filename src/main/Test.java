package main;

public class Test {
	
	public static void main(String[] args) {
		
		Compressor comp = new Compressor();
		comp.read("src/res/test.txt");
		System.out.println("PROBABILITIES :");
		comp.printProbabilities();
		comp.huffman("src/res/encoded.txt");
		System.out.println("CODES :");
		comp.printCodes();
		System.out.println("TEXT : \n" + comp.textToString());
				
		Decompressor decomp = new Decompressor();
		decomp.read("src/res/encoded.txt", "");
		System.out.println(decomp.bytesToBinary());
		
	}
}
