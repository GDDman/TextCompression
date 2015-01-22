package main;

import java.io.File;
import java.io.FileInputStream;

public class Decompressor {

	private byte[] bytes = null;
	
	public void read(String path, String infopath) {
		
		File file = new File(path);
		FileInputStream fis = null;
		bytes = new byte[(int) file.length()];
		try {
			fis = new FileInputStream(file);
			fis.read(bytes);
			fis.close();
		} 
		catch (Exception e){
			e.printStackTrace();
		}
	}
	
	public String signedIntToBinary(int input) {
		
		String temp = "";
		int num = input;
		if (input < 0) {
			temp += "1";
			num += 128;
		}
		else {
			temp += "0";
		}
		
		for (int i = 6; i >= 0; i--) {
			int placevalue = (int) Math.pow(2, i);
			if (num >= placevalue) {
				num -= placevalue;
				temp += "1";
			}
			else {
				temp += "0";
			}
		}
		return temp;
	}
	
	//CONVERSION
	
	public String bytesToBinary() {
		
		String temp = "";
		int value = 0;
		for (int i = 0; i < bytes.length; i++) {
			value = (int) bytes[i];
			temp += signedIntToBinary(value);
		}
		return temp;
	}
	
}
