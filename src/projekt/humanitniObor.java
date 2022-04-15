package projekt;

import java.io.Serializable;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Map;
import java.util.Map.Entry;
import java.util.TreeMap;

public class humanitniObor extends student implements Serializable{
	String obor = "humanitni obor";
	TreeMap<String, Date> znameni = new TreeMap();
	void setZnameni() {
		DateFormat format = new SimpleDateFormat("dd.MM");
		try {
			znameni.put("Beran", format.parse("20.3"));
			znameni.put("Byk", format.parse("20.4"));
			znameni.put("Blizenci", format.parse("21.5"));
			znameni.put("Rak", format.parse("21.6"));
			znameni.put("Lev", format.parse("22.7"));
			znameni.put("Panna", format.parse("23.8"));
			znameni.put("Vahy", format.parse("23.9"));
			znameni.put("Stir", format.parse("23.10"));
			znameni.put("Strelec", format.parse("22.11"));
			znameni.put("Kozoroh", format.parse("21.12"));
			znameni.put("Vodnar", format.parse("20.1"));
			znameni.put("Ryby", format.parse("18.2"));
		} catch (ParseException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	public humanitniObor(int generovatID) {
		super(generovatID);
	}
	public humanitniObor() {
		super();
		// TODO Auto-generated constructor stub
	}
	@Override 
	public void dovednost() {
		setZnameni();
		Date narozeni = super.getNarozeni();
		String z = "";
		narozeni.setYear(70);
		for(Entry<String, Date> entry : znameni.entrySet()) {
			  if(narozeni.after(entry.getValue())) {
				  z = entry.getKey();
			  }else {
				  System.out.println("Me znameni je " + z);
				  return;
			  }
			}
		return;
	}
}
