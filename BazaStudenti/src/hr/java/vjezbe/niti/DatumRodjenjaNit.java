package hr.java.vjezbe.niti;

import java.util.List;

import hr.java.vjezbe.baza.BazaPodataka;
import hr.java.vjezbe.entitet.Student;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;

public class DatumRodjenjaNit implements Runnable {
	public static final String NL = System.getProperty("line.separator");
	@Override
	public void run() {

		// TODO Auto-generated method stub

	}

	public DatumRodjenjaNit() {
		List<Student> slavljenici=BazaPodataka.dohvatiStudenteKojiImajuRodjendan();
		Alert alert = new Alert(AlertType.INFORMATION);
		alert.setTitle("Roðendan studenta");
		alert.setHeaderText("Èestitajte roðendan sljedeæim studentima:");
		String imena="";
		for (Student temp:slavljenici) {
			imena+=temp.getIme() + " " + temp.getPrezime();
			imena+=NL;
		}
		alert.setContentText(imena);
		alert.show();
	}

}
