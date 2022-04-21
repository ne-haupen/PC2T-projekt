package projekt;

import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.TreeMap;

public class kombinovanyObor extends student{
	TreeMap<String, Date> znameni = new TreeMap();
	public kombinovanyObor(int generovatID) {
		super(generovatID, "kombinovany obor");
	}

	public kombinovanyObor() {
		super();
		this.obor = "kombinovany obor";
		// TODO Auto-generated constructor stub
	}
	
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

	@Override 
	public void dovednost() {
		setZnameni();
		Date narozeni = super.getNarozeni();
		String z = "Kozoroh";
		System.out.println(znameni.get("Kozoroh"));
		int rok = narozeni.getYear();
		narozeni.setYear(70);
		System.out.println(narozeni);
		for(String s : znameni.keySet()) {
			  if(narozeni.after(znameni.get(s))) {
				  z = s;
			  }else {
				  narozeni.setYear(rok);
				  System.out.println("Me znameni je " + z.toString());
				  break;
			  }
			}
		if(super.getNarozeni().getYear()%4 == 0) {
			System.out.println("rok meho narozeni byl prestupny");
		}else {
			System.out.println("rok meho narozeni nebyl prestupny");
		}
		return;
	}
}
