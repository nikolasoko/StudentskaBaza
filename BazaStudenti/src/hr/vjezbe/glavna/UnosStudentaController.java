package hr.vjezbe.glavna;

import java.io.IOException;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.OptionalLong;

import hr.java.vjezbe.baza.BazaPodataka;
import hr.java.vjezbe.entitet.Student;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.DatePicker;
import javafx.scene.control.TextField;
import javafx.scene.layout.BorderPane;

public class UnosStudentaController {
	public static final String NL = System.getProperty("line.separator");
	public static final String Studenti = "dat/studenti.txt";

	List<Student> studenti = new ArrayList<>();
	@FXML
	private TextField unosJmbaga;
	@FXML
	private TextField unosPrezimena;
	@FXML
	private TextField unosImena;
	@FXML
	private DatePicker unosDatuma;

	@FXML
	public void initialize() {
		Student prazni = new Student(null, null, null, null, -1);
		studenti = BazaPodataka.dohvatiStudentePremaKriterijima(prazni);
	}

	@FXML
	public void unos() {
		String Jmbag = unosJmbaga.getText();
		String Prezime = unosPrezimena.getText();
		String Ime = unosImena.getText();
		LocalDate Datum = unosDatuma.getValue();
		OptionalLong maksimalniId = studenti.stream().mapToLong(student -> student.getId()).max();
		if (isStringEmpty(Jmbag, Ime, Prezime) || (Datum == null)) {
			String poruka = "";
			if (Jmbag.isEmpty()) {
				poruka = "Šifra je obavezan podatak";
				poruka += NL;
			}
			if (Ime.isEmpty()) {
				poruka += "Ime je obavezan podatak";
				poruka += NL;
			}
			if (Prezime.isEmpty()) {
				poruka += "Prezime je obavezan podatak";
				poruka += NL;
			}
			if (Datum == null) {
				poruka += "Datum je obavezan podatak";
				poruka += NL;
			}
			Alert alert = new Alert(AlertType.ERROR);
			alert.setTitle("Pogrešan unos podataka");
			alert.setHeaderText("Molimo ispravite sljedeæe podatke");
			alert.setContentText(poruka);

			alert.showAndWait();
		} else {
			Student noviStudent = new Student(Ime,Prezime,Jmbag, Datum,maksimalniId.getAsLong() + 1);
			BazaPodataka.spremiNovogStudenta(noviStudent);
		}
	}

	@FXML
	public void prikaziUnosProfesora() {
		BorderPane root;
		try {
			root = (BorderPane) FXMLLoader.load(getClass().getResource("UnosProfesori.fxml"));
			Main.setMainPage(root);
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	@FXML
	public void prikaziUnosPredmeta() {
		BorderPane root;
		try {
			root = (BorderPane) FXMLLoader.load(getClass().getResource("UnosPredmeta.fxml"));
			Main.setMainPage(root);
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	@FXML
	public void prikaziUnosStudenta() {
		BorderPane root;
		try {
			root = (BorderPane) FXMLLoader.load(getClass().getResource("UnosStudenta.fxml"));
			Main.setMainPage(root);
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	@FXML
	public void prikaziUnosIspita() {
		BorderPane root;
		try {
			root = (BorderPane) FXMLLoader.load(getClass().getResource("UnosIspita.fxml"));
			Main.setMainPage(root);
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	@FXML
	public void prikaziPretraguProfesora() {
		BorderPane root;
		try {
			root = (BorderPane) FXMLLoader.load(getClass().getResource("Profesori.fxml"));
			Main.setMainPage(root);
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	@FXML
	public void prikaziPretraguStudenta() {
		BorderPane root;
		try {
			root = (BorderPane) FXMLLoader.load(getClass().getResource("Studenti.fxml"));
			Main.setMainPage(root);
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	@FXML
	public void prikaziPretraguPredmeta() {
		BorderPane root;
		try {
			root = (BorderPane) FXMLLoader.load(getClass().getResource("Predmeti.fxml"));
			Main.setMainPage(root);
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	@FXML
	public void prikaziPretraguIspita() {
		BorderPane root;
		try {
			root = (BorderPane) FXMLLoader.load(getClass().getResource("Ispiti.fxml"));
			Main.setMainPage(root);
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	public static boolean isStringEmpty(String... strings) {
		for (String s : strings)
			if (s == null || s.isEmpty())
				return true;
		return false;
	}
}
