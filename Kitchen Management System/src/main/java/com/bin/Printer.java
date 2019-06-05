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
		SimpleDateFormat simpleDateFormat = new SimpleDateFormat("MMddhhmmss");
		String dateAsString = simpleDateFormat.format(new Date());
		try {
			File f;
			f = new File("src/main/java/com/log/", dateAsString + ".txt");
			f.createNewFile();
			
			FileWriter filetowrite = new FileWriter(f);
			BufferedWriter bw = new BufferedWriter(filetowrite);
			
			bw.write(o.toString());
			bw.flush();
			bw.close();
			
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

}
