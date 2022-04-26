package projekt;

public class technickyObor extends student {
	public technickyObor(int generovatID) {
		super(generovatID, "technicky obor");
	}
	public technickyObor() {
		super();
		this.obor = "technicky obor";
		// TODO Auto-generated constructor stub
	}
	@Override 
	public void dovednost() {
		if(super.getNarozeni().getYear()%4 == 0) {
			System.out.println("rok meho narozeni byl prestupny");
		}else {
			System.out.println("rok meho narozeni nebyl prestupny");
		}
		return;
	}
}
