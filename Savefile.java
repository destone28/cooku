package cooku;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;

public class Savefile {
	

	public static void logga(String str_da_salvare,String file_salvataggio){
	
	File log = new File(file_salvataggio);

	try {
		if (log.exists()){
            System.out.println("Il file " + log + " esiste");
            try {
                FileWriter fw = new FileWriter(log,true); //log su file, true per append
                fw.append(str_da_salvare+"\n");
                fw.flush();
                fw.close();
            }
            catch(IOException e) {
                e.printStackTrace();
            }
		}
        else if (log.createNewFile()){
            System.out.println("Il file " + log + " è stato creato");
		try {
	        FileWriter fw = new FileWriter(log,true);  //log su file, true per append
	        fw.append(str_da_salvare+"\n");
	        fw.flush();
	        fw.close();
	    	}
	    catch(IOException e) {
	        e.printStackTrace();
	    	}
        }
        else
            System.out.println("Il file " + log + " non può essere creato");
     
    } catch (IOException e) {
        e.printStackTrace();
    }

}
}
