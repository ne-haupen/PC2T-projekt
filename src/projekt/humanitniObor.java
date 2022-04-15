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
	TreeMap<String, Date> znameni = new TreeMap();
	void setZnameni() {
		DateFormat format = new SimpleDateFormat("dd.MM");
		try {
			znameni.put("Vodnar", format.parse("20.1"));
			znameni.put("Ryby", format.parse("18.2"));
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
		} catch (ParseException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	public humanitniObor(int generovatID) {
		super(generovatID, "humanitni obor");
	}
	public humanitniObor() {
		super();
		// TODO Auto-generated constructor stub
	}
	@Override 
	public void dovednost() {
		setZnameni();
		Date narozeni = super.getNarozeni();
		String z = "Kozoroh";
		int rok = narozeni.getYear();
		narozeni.setYear(70);
		for(String s : znameni.keySet()) {
			  if(narozeni.after(znameni.get(s))) {
				  z = s;
			  }else {
				  narozeni.setYear(rok);
				  System.out.println("Me znameni je " + z.toString());
				  return;
			  }
			}
		return;
	}
}
