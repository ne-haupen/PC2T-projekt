package projekt;

import java.io.Serializable;

public class humanitniObor extends student{
	String obor = "humanitni obor";
	public humanitniObor(int generovatID) {
		super(generovatID);
	}
	String zverokruh() {
		return " ";
	}
	@Override 
	public void dovednost() {
		return;
	}
}
