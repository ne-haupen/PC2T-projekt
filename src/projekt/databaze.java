package projekt;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.StreamCorruptedException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.LinkedHashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Scanner;


public class databaze {
	private LinkedHashMap<Integer, student> studenti = new LinkedHashMap();
	Scanner sc = new Scanner(System.in);
	void precistData() {
		String pwd = System.getProperty("user.dir");
		File[] path = new File(pwd).listFiles();
		LinkedList<File> data = new LinkedList();
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
					sum += s.getPrumer();
					pocet++;
				}
				
			}
			System.out.println("V humanitnim oboru je prumer: " + sum/pocet);
			break;
		case 2:
			for(student s: studenti.values()) {
				if(s.obor == "technicky obor") {
					sum += s.getPrumer();
					pocet++;
				}
			}
			System.out.println("V technickem oboru je prumer: " + sum/pocet);
			break;
		case 3:
			for(student s: studenti.values()) {
				if(s.obor == "kombinovany obor") {
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
		String jmeno = "";
		while(novyStudent.maJmeno() == false) {
			System.out.println("zadejte jmeno studenta");
			if(sc.hasNextLine()) {
				jmeno = sc.nextLine();
			}
			novyStudent.setJmeno(jmeno);
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
