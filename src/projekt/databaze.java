package projekt;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.StreamCorruptedException;
import java.util.Arrays;
import java.util.LinkedHashMap;
import java.util.LinkedList;
import java.util.Scanner;
import java.sql.*;

public class databaze {
	private LinkedHashMap<Integer, student> studenti = new LinkedHashMap<Integer, student>();
	Scanner sc = new Scanner(System.in);
	void resetDB(){
		Connection c = null;
	      Statement stmt = null;
	      try {
	         Class.forName("org.postgresql.Driver");
	         c = DriverManager
	            .getConnection("jdbc:postgresql://localhost:5432/studentidb",
	            "general", "123");
	         System.out.println("Opened database successfully");

	         stmt = c.createStatement();
	         String sql = "DROP TABLE DATA";
	         stmt.executeUpdate(sql);
	         stmt.close();
	         c.close();
	      } catch ( Exception e ) {
	         System.err.println( e.getClass().getName()+": "+ e.getMessage() );
	         e.printStackTrace();
	         System.exit(0);
	      }
	      dbInit();
	      System.out.println("Table reseted successfully");
	}
	
	void dbInit() {
		Connection c = null;
	      Statement stmt = null;
	      try {
	         Class.forName("org.postgresql.Driver");
	         c = DriverManager
	            .getConnection("jdbc:postgresql://localhost:5432/studentidb",
	            "general", "123");
	         System.out.println("Opened database successfully");

	         stmt = c.createStatement();
	         String sql = "CREATE TABLE DATA" +
	            "(ID INT PRIMARY KEY     NOT NULL," +
	            " JMENO           TEXT    NOT NULL, " +
	            " PRIJMENI           TEXT    NOT NULL, " +
	            " AGE            INT NOT NULL, " +
	            " ZNAMKY         TEXT NOT NULL," +
	            " OBOR			TEXT NOT NULL," +
	            " PRUMER         REAL)";
	         stmt.executeUpdate(sql);
	         stmt.close();
	         c.close();
	      } catch ( Exception e ) {
	         System.err.println( e.getClass().getName()+": "+ e.getMessage() );
	         e.printStackTrace();
	         System.exit(0);
	      }
	      System.out.println("Table created successfully");
	}
	
	void saveDataToDB() {
		Connection c = null;
	      Statement stmt = null;
	      try {
	         Class.forName("org.postgresql.Driver");
	         c = DriverManager
	            .getConnection("jdbc:postgresql://localhost:5432/studentidb",
	            "general", "123");
	         c.setAutoCommit(false);
	         System.out.println("Opened database successfully");
	         stmt = c.createStatement();
	         
	         String sql = new String();
	         
	         for(int s: studenti.keySet()) {
	        	 student current = studenti.get(s);
	        	 int id = s;
	        	 String jmeno = current.getJmeno();
	        	 String prijmeni = current.getPrijmeni();
	        	 int age = current.vek();
	        	 String znamky = current.getZnamkyString();
	        	 String obor = current.obor;
	        	 float prumer = current.getPrumer();
	        	 
	        	 sql = String.format("INSERT INTO DATA (ID, JMENO, PRIJMENI, AGE, ZNAMKY, OBOR, PRUMER)"
	        			 + " VALUES (%d, '%s', '%s', %d, '%s', '%s', %f)", id, jmeno, prijmeni, age, znamky, obor, prumer);
	        	 System.out.println(sql);
	        	 stmt.executeUpdate(sql);
	         }
	         stmt.close();
	         c.commit();
	         c.close();
	      } catch (Exception e) {
	         System.err.println( e.getClass().getName()+": "+ e.getMessage() );
	         e.printStackTrace();
	         System.exit(0);
	      }
	      System.out.println("Records created successfully");

	      
	}

	void readSQL() {
		Connection c = null;
		Statement stmt = null;
		if(studenti.size() != 0) {
			System.out.println("Databaze neni prazdna a bude prepsana, pokracovat?");
			System.out.println("1. ano");
			System.out.println("2. ne");
			int o = sc.nextInt();
			
			switch(o) {
			case 1:
				break;
			default:
				return;
			}
		}
		studenti.clear();
		try {
			Class.forName("org.postgresql.Driver");
			c = DriverManager.getConnection("jdbc:postgresql://localhost:5432/studentidb", "general", "123");
			c.setAutoCommit(false);
			System.out.println("Opened database successfully");

			stmt = c.createStatement();
			ResultSet rs = stmt.executeQuery("SELECT * FROM DATA;");
			while (rs.next()) {
				int id = rs.getInt("id");
				String name = rs.getString("jmeno");
				String prijmeni = rs.getString("prijmeni");
				int age = rs.getInt("age");
				String znamky = rs.getString("znamky");
				String obor = rs.getString("obor");
				float prumer = rs.getFloat("prumer");
				student s = null;
				
				switch(obor) {
				case "humanitni obor":
					s = new humanitniObor();
					break;
				case "technicky obor":
					s = new technickyObor();
					break;
				case "kombinovany obor":
					s = new kombinovanyObor();
					break;
				}
				s.dbSetup(id, name, prijmeni, age, znamky, prumer);
				studenti.put(id, s);
			}
			rs.close();
			stmt.close();
			c.close();
		} catch (Exception e) {
			System.err.println(e.getClass().getName() + ": " + e.getMessage());
			e.printStackTrace();
			System.exit(0);
		}
		System.out.println("Operation done successfully");

	}

	void precistData() {
		String pwd = System.getProperty("user.dir");
		File[] path = new File(pwd).listFiles();
		LinkedList<File> data = new LinkedList<File>();
		String fileName= "";
		int pocet = 0;
		int vyber = 0;
		if(studenti.size() != 0) {
			System.out.println("Databaze neni prazdna a bude prepsana, pokracovat?");
			System.out.println("1. ano");
			System.out.println("2. ne");
			int o = sc.nextInt();
			
			switch(o) {
			case 1:
				break;
			default:
				return;
			}
		}
		System.out.println("Vyberte soubor:");
		for(File s: path) {
			if(s.getName().endsWith(".txt")) {
				data.add(s);
				System.out.println(pocet+1 + ". " + s.getName());
				pocet++;
			}
		}
		if(pocet == 0) {
			System.out.println("zadna databaze neni ulozena");
			return;
		}
		while(vyber == 0) {
			System.out.println("vyberte moznost: ");
			vyber = sc.nextInt();
			if(vyber < 1 || vyber > pocet) {
				System.out.println("spatny rozsah vyberu");
				vyber = 0;
			}else {
				fileName = data.get(vyber-1).getName();
			}
		}
		try {
			FileInputStream fin = new FileInputStream(fileName);
			ObjectInputStream ois = new ObjectInputStream(fin);
			studenti.clear();
			studenti = (LinkedHashMap<Integer, student>) ois.readObject();
			ois.close();
		}catch (StreamCorruptedException e) {
			System.out.println("soubor neobsahuje databazi");
			return;
		} catch (IOException e) {
			e.printStackTrace();
		} catch (ClassNotFoundException e) {
			System.out.println(e.toString());
			e.printStackTrace();
		}
	}
	void nahratData() {
		if(studenti.size() == 0) {
			System.out.println("Databaze je prazdna, nejsou zadne data k ulozeni");
			return;
		}
		String pwd = System.getProperty("user.dir");
		File[] path = new File(pwd).listFiles();
		String fileName = "";
		while(fileName.isEmpty()) {
			System.out.println("Zadejte jmeno souboru: ");
			if(sc.hasNextLine()) {
				fileName = sc.nextLine();
				fileName = sc.nextLine();
			}
			if(!fileName.endsWith(".txt")) {
				fileName += ".txt";
			}
			for(File f: path) {
				//System.out.println(f.getName());
				if(f.getName().contains(fileName)) {
					System.out.println("soubor existuje");
					System.out.println("1. vybrat nove jmeno");
					System.out.println("2. prepsat");
					System.out.println("3. exit");
					int o = sc.nextInt();
					switch(o) {
					case 1:
						fileName = "";
						break;
					case 2:
						fileName = f.getName();
						break;
					default:
						return;
					}
				}
			}
		}
		try {
			FileOutputStream fos = new FileOutputStream(fileName);
	        ObjectOutputStream s = new ObjectOutputStream(fos);
	        s.writeObject(studenti);
	        s.close();
		} catch (IOException e) {
			System.out.println(e.toString());
			
		}
	}
	void pocetStudentuOboru() {
		int pocet = 0;
		System.out.println("Zadejte obor studenta");
		System.out.println("1... humanitni obor");
		System.out.println("2... technicky obor");
		System.out.println("3... kombinovany obor");
		
		int o = sc.nextInt();
		switch(o) {
		case 1:
			for(student s: studenti.values()) {
				if(s.obor.equals("humanitni obor")) {
					pocet += 1;
				}
				
			}
			System.out.println("V humanitnim oboru je: " + pocet + " studentu");
			break;
		case 2:
			for(student s: studenti.values()) {
				if(s.obor.equals("technicky obor")) {
					pocet += 1;
				}
			}
			System.out.println("V technickem oboru je: " + pocet + " studentu");
			break;
		case 3:
			for(student s: studenti.values()) {
				if(s.obor.equals("kombinovany obor")) {
					pocet += 1;
				}
			}
			System.out.println("V kombinovanem oboru je: " + pocet + " studentu");
			break;
		}
	}
	void celkovyPrumer() {
		float sum = 0;
		int pocet = 0;
		System.out.println("Zadejte obor studenta");
		System.out.println("1... humanitni obor");
		System.out.println("2... technicky obor");
		System.out.println("3... kombinovany obor");
		
		int o = sc.nextInt();
		switch(o) {
		case 1:
			for(student s: studenti.values()) {
				if(s.obor.equals("humanitni obor")) {
					sum += s.getPrumer();
					pocet++;
				}
				
			}
			System.out.println("V humanitnim oboru je prumer: " + sum/pocet);
			break;
		case 2:
			for(student s: studenti.values()) {
				if(s.obor.equals("technicky obor")) {
					sum += s.getPrumer();
					pocet++;
				}
			}
			System.out.println("V technickem oboru je prumer: " + sum/pocet);
			break;
		case 3:
			for(student s: studenti.values()) {
				if(s.obor.equals("kombinovany obor")) {
					sum += s.getPrumer();
					pocet++;
				}
			}
			System.out.println("V kombinovanem oboru je prumer: " + sum/pocet);
		}
	}
	void SerazenyVypisStudentu() {
		System.out.println("Zadejte obor studenta");
		System.out.println("1... humanitni obor");
		System.out.println("2... technicky obor");
		System.out.println("3... kombinovany obor");
		
		int o = sc.nextInt();
		LinkedHashMap<String, student> sort = new LinkedHashMap();
		switch(o){
		case 1:
			for(student s: studenti.values()) {
				if(s.obor.equals("humanitni obor")) {
					sort.put(s.getPrijmeni(), s);
				}
			}
			break;
		case 2:
			for(student s: studenti.values()) {
				if(s.obor.equals("technicky obor")) {
					sort.put(s.getPrijmeni(), s);
				}
			}
			break;
		case 3:
			for(student s: studenti.values()) {
				if(s.obor.equals("kombinovany obor")) {
					sort.put(s.getPrijmeni(), s);
				}
			}
			break;
		}
		
		String[] jmena = (String[]) sort.values().toArray();
		Arrays.sort(jmena);
		for(String s: jmena) {
			student r = sort.get(s);
			System.out.println("prijmeni studenta" + s);
			vypsatInformaceStudenta(r.getID());
			System.out.println();
		}
		
	}
	void spustitSchopnost(int id) {
		if(studentExistuje(id)) {
			studenti.get(id).dovednost();
		}
	}
	void vypsatInformaceStudenta(int id) {
		if(studentExistuje(id)) {
			@SuppressWarnings("deprecation")
			student s = studenti.get(id);
			System.out.println("ID: " + id +
								"\njmeno: " + s.getJmeno() +
								"\nprijmeni: " + s.getPrijmeni() +
								"\nobor: "	+ s.obor +
								"\nrok narozeni: " + s.getNarozeni().getYear() +
								"\nstudijni prumer:" + s.getPrumer()
								);
		}
	}
	void pridatZnamku(int id) {
		student s = studenti.get(id);
		
		System.out.println("zadejte znaku: ");
		
		int znamka = sc.nextInt();
		if(studentExistuje(id)) {
			s.pridatZnamku(znamka);
		}else {
			System.out.println("student neni v databazi");
		}
			
	}
	void propustitStudenta(int id) {
		if(studentExistuje(id)) {
			studenti.remove(id);
			System.out.println("student byl propusten");
		}
	}
	boolean studentExistuje(int id) {
		if(studenti.containsKey(id)) {
			return true;
		}else {
			System.out.println("student neni v databazi");
			return false;
		}
	}
	void pridatStudenta() {
		System.out.println("Zadejte obor studenta");
		System.out.println("1... humanitni obor");
		System.out.println("2... technicky obor");
		System.out.println("3... kombinovany obor");
		
		int o = sc.nextInt();
		
		student novyStudent = null;
		switch(o){
		case 1:
			novyStudent = new humanitniObor(generovatID());
			break;
		case 2:
			novyStudent = new technickyObor(generovatID());
			break;
		case 3:
			novyStudent = new kombinovanyObor(generovatID());
			break;
		}
		String jmeno = "";
		while(true) {
			System.out.print("zadejte jmeno studenta: ");
			while(!sc.hasNext())
			{
				sc.nextLine();
			}
			jmeno = sc.nextLine();
			jmeno = sc.nextLine();
			if(novyStudent.setJmeno(jmeno) == true) {
				break;
			}
		}
		while(novyStudent.maDatum() == false) {
			System.out.println("zadejte datum narozeni studenta");
			novyStudent.setNarozeni((sc.nextLine()));;
		}
		studenti.put(novyStudent.getID(), novyStudent);
	}
	private int generovatID() {
		if(studenti.size() == 0) {
			return 1;
		}else {
			student last = (student) studenti.values().toArray()[studenti.size()-1];
			return last.getID()+1;
		}
	}
}
