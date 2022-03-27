package projekt;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Scanner;


public class databaze {
	private LinkedHashMap<Integer, student> studenti = new LinkedHashMap();
	Scanner sc = new Scanner(System.in);
	void precistData() {
		if(studenti.size() != 0) {
			System.out.println("Databaze neni prazdna a bude prepsana, pokracovat?");
			//pokracovat?
		}
		String fileName= "studenti.txt";
		try {
			String fileNameIn= "Test.txt";
			FileInputStream fin = new FileInputStream(fileName);
			ObjectInputStream ois = new ObjectInputStream(fin);
			studenti.clear();
			studenti = (LinkedHashMap<Integer, student>) ois.readObject();
			ois.close();
		} catch (IOException e) {
			e.printStackTrace();
		} catch (ClassNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	void nahratData() {
		if(studenti.size() == 0) {
			System.out.println("Databaze je prazdna, nejsou zadne data k ulozeni");
		}
		String fileName= "studenti.txt";
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
				if(s.obor == "humanitni obor") {
					pocet += 1;
				}
				
			}
			System.out.println("V humanitnim oboru je: " + pocet + " studentu");
			break;
		case 2:
			for(student s: studenti.values()) {
				if(s.obor == "technicky obor") {
					pocet += 1;
				}
			}
			System.out.println("V technickem oboru je: " + pocet + " studentu");
			break;
		case 3:
			for(student s: studenti.values()) {
				if(s.obor == "kombinovany obor") {
					pocet += 1;
				}
			}
			System.out.println("V kombinovanem oboru je: " + pocet + " studentu");
			break;
		}
	}
	void celkovyPrumer() {
		float sum = 0;
		for(student s: studenti.values()) {
			sum += s.getPrumer();
		}
		System.out.println("celkovy prumer vsech studentu je: " + sum/studenti.size());
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
				if(s.obor == "humanitni obor") {
					sort.put(s.getPrijmeni(), s);
				}
			}
			break;
		case 2:
			for(student s: studenti.values()) {
				if(s.obor == "technicky obor") {
					sort.put(s.getPrijmeni(), s);
				}
			}
			break;
		case 3:
			for(student s: studenti.values()) {
				if(s.obor == "kombinovany obor") {
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
								"\nrok narozeni: " + s.getNarozeni().getYear() +
								"\nstudijni prumer:" + s.getPrumer()
								);
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
		String jmeno;
		while(novyStudent.maJmeno() == false) {
			System.out.println("zadejte jmeno studenta");
			jmeno = sc.nextLine();
			novyStudent.setJmeno(jmeno);
		}
		while(novyStudent.maDatum() == false) {
			System.out.println("zadejte datum narozeni studenta");
			novyStudent.setNarozeni((sc.nextLine()));;
		}
		studenti.put(novyStudent.getID(), novyStudent);
	}
	int generovatID() {
		if(studenti.size() == 0) {
			return 1;
		}else {
			student last = (student) studenti.values().toArray()[studenti.size()-1];
			return last.getID()+1;
		}
	}
}
