package projekt;
import java.util.Date;
import java.time.LocalDate;
import java.time.Period;
import java.time.format.DateTimeFormatter;
import java.io.Serializable;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.LinkedList;

public abstract class student implements Serializable{
	private String jmeno, prijmeni;
	private Date narozeni;
	private int id;
	private LinkedList<Integer> znamky = new LinkedList();
	private float prumer=0;
	DateFormat format = new SimpleDateFormat("dd.MM.yyyy");
	String obor;
	
	student(int id){
		this.id = id;
	}
	
	public student() {
		// TODO Auto-generated constructor stub
	}

	void dbSetup(int id, String name, String prijmeni, int age, String znamky, float prumer) {
		this.id = id;
		this.jmeno = name;
		this.prijmeni = prijmeni;
		for (int i = 0; i < znamky.length(); i++) {
			this.znamky.add(Character.getNumericValue(znamky.charAt(i)));
        }
		updatePrumer();
		
	}
	String getJmeno() {
		return jmeno;
	}
	String getPrijmeni() {
		return prijmeni;
	}
	String getZnamkyString() {
		String s = new String();
		for(int n: znamky) {
			s += ((Integer)n).toString();
		}
		return s;
	}
	boolean maJmeno() {
		if(jmeno == null) {
			return false;
		}else if(jmeno.isEmpty() && prijmeni.isEmpty()) {
			return false;
		}
		return true; 
	}
	boolean maDatum() {
		if(narozeni ==null || narozeni.toString().isEmpty() == true) {
			return false;
		}
		return true;
	}
	int getID() {
		return this.id;
	}
	float getPrumer() {
		return this.prumer;
	}
	int vek() {
		
		/*
		 * kontrola jesli je vlozeny datum narozeni skutecni, nebo alespon pred aktualnim datem
		 */
	    Date born = getNarozeni();
	    LocalDate currentDate = LocalDate.now();
	    return currentDate.getYear()-born.getYear();

	}
	void setJmeno(String jmeno) {
		String[] data = jmeno.split(" ");
		if(data.length != 2) {
			System.out.println("zadan spatny format jmena");
			return;
		}
		this.jmeno = data[0];
		this.prijmeni = data[1];
	}
	void setNarozeni(String datum) {
		try {
			this.narozeni = format.parse(datum);
			narozeni.setYear(narozeni.getYear()+1900);
		}catch(Exception e) {
			System.out.println("byl zadan spatny format data narozeni");
		}
	}
	Date getNarozeni() {
		return this.narozeni;
	}
	void pridatZnamku(int i) {
		if(i>5 || i<1) {
			System.out.println("Znamkuje se od 1 do 5");
			return;
		}
		znamky.add(i);
		System.out.println("znamka pridana");
		updatePrumer();
	}
	void updatePrumer() {
		float prumer = 0;
		for(Integer i: this.znamky) {
			prumer += i/this.znamky.size();
		}
		this.prumer = prumer;
	}
	abstract public void dovednost();
}
