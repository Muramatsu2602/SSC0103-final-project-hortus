package hortus;
import io.jsondb.JsonDBTemplate;
import io.jsondb.crypto.ICipher;
import io.jsondb.crypto.Default1Cipher;

import java.io.File;

public class SGBD {
 	private static String dbFilesLocation;
 	private static String baseScanPackage;
 	private static JsonDBTemplate jsonDBTemplate;
 	private static ICipher cipher;
 	
	public static JsonDBTemplate inicializarDB() {
		
		//Actual location on disk for database files, process should have read-write permissions to this folder
		dbFilesLocation = "src/hortus/dbFiles";
		
		File dbFilesFolder = new File(dbFilesLocation);
	
		// Java package name where POJO's are present
		baseScanPackage = "hortus.model";
	
			try {
				cipher = new Default1Cipher("1r8+24pibarAWgS85/Heeg==");
				jsonDBTemplate = new JsonDBTemplate(dbFilesLocation, baseScanPackage, cipher);
			
				return jsonDBTemplate;
			} catch(Exception e) {
				System.out.println("erro");
			}
			
		return jsonDBTemplate;	
	}
}
