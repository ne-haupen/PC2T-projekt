package projekt;
import java.util.Date;
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
	String obor;
	student(int id){
		this.id = id;
	}
	String getJmeno() {
		return jmeno;
	}
	String getPrijmeni() {
		return prijmeni;
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
			DateFormat format = new SimpleDateFormat("dd.MM.yyyy");
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
