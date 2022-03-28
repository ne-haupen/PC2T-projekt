package projekt;

public class technickyObor extends student {
	String obor = "technicky obor";
	public technickyObor(int generovatID) {
		super(generovatID);
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
