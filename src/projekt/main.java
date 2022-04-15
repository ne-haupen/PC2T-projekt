package projekt;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.Statement;

public class main {
	public static void main(String[] args) {
		databaze db = new databaze();
		//db.deleteDB();
		//db.dbInit();
		db.resetDB();
		db.pridatStudenta();
		db.pridatZnamku(1);
		db.pridatZnamku(1);
		
		db.saveDataToDB();
		
		db.readSQL();
}
}