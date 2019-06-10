package com.bin;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.sql.Timestamp;
import java.text.SimpleDateFormat;
import java.util.Date;

public class Printer {
	
	static void printToFile(Order o) {
		SimpleDateFormat simpleDateFormat = new SimpleDateFormat("MMdd");
		String dateAsString = simpleDateFormat.format(new Date());
		try {
			File f;
			f = new File("src/main/java/com/log/", dateAsString + ".txt");
			f.createNewFile();
			
			FileWriter filetowrite = new FileWriter(f, true);
			BufferedWriter bw = new BufferedWriter(filetowrite);
			
			bw.write(o.toString());
			bw.newLine();
			bw.flush();
			bw.close();
			
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	static void printAvg(int o) {
		SimpleDateFormat simpleDateFormat = new SimpleDateFormat("YYYY-MM-dd");
		String dateAsString = simpleDateFormat.format(new Date());
		String averageString = dateAsString + " : " + o;
		try {
			File f;
			f = new File("src/main/java/com/log/", "Average" + ".txt");
			f.createNewFile();
			
			FileWriter filetowrite = new FileWriter(f, true);
			BufferedWriter bw = new BufferedWriter(filetowrite);
			
			bw.write(averageString);
			bw.newLine();
			bw.flush();
			bw.close();
			
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

}
