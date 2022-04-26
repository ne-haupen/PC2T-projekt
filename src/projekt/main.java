package projekt;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.Statement;
import java.util.Scanner;

public class main {
	public static void main(String[] args) {
		databaze db = new databaze();
		Scanner sc = new Scanner(System.in);
		boolean run = true;
		while(run) {
			System.out.println("vyberte moznost:");
			System.out.println("1... pridat studenta");
			System.out.println("2... zadat studentovi znamku");
			System.out.println("3... propustit studenta");
			System.out.println("4... vypsat informace studenta");
			System.out.println("5... spustit dovednost studenta");
			System.out.println("6... abecedne serazeny seznam studentu");
			System.out.println("7... vypis obecneho studijniho prumeru");
			System.out.println("8... vypis poctu studentu ve skupine");
			System.out.println("9... ulozit do txt souboru");
			System.out.println("10.. nacist txt soubor");
			System.out.println("11.. ulozit do sql databaze");
			System.out.println("12.. nacist z sql databaze");
			System.out.println("13.. exit");
			System.out.print("vyber: ");
			int vyber = sc.nextInt();
			int ph;
			switch(vyber) {
			case 1:
				db.pridatStudenta();
				System.out.println("student byl pridan");
				break;
			case 2:
				System.out.print("Zadejte id studenta, kteremu pridat znamku: ");
				ph = sc.nextInt();
				db.pridatZnamku(ph);
				break;
			case 3:
				System.out.print("Zadejte id studenta, ktereho propustit: ");
				ph = sc.nextInt();
				db.propustitStudenta(ph);
				break;
			case 4:
				System.out.print("Zadejte id studenta, ktereho informace chcete: ");
				ph = sc.nextInt();
				db.vypsatInformaceStudenta(ph);
				break;
			case 5:
				System.out.print("Zadejte id studenta, ktereho dovednost chcete spustit: ");
				ph = sc.nextInt();
				db.spustitSchopnost(ph);
				break;
			case 6:
				db.SerazenyVypisStudentu();
				break;
			case 7:
				db.celkovyPrumer();
				break;
			case 8:
				db.pocetStudentuOboru();
				break;
			case 9:
				db.nahratData();
				break;
			case 10:
				db.precistData();
				break;
			case 11:
				db.saveDataToDB();
				break;
			case 12:
				db.readSQL();
				break;
			case 13:
				run = false;
				break;
			case 14:
				db.dbInit();
				break;
			}
		}
}
}