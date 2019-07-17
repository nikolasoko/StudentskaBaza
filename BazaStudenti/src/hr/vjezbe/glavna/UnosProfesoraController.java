package hr.vjezbe.glavna;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.OptionalLong;

import hr.java.vjezbe.baza.BazaPodataka;
import hr.java.vjezbe.entitet.Profesor;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.TextField;
import javafx.scene.layout.BorderPane;

public class UnosProfesoraController {

	public static final String NL = System.getProperty("line.separator");
	public static final String Profesori = "dat/profesori.txt";

	List<Profesor> profesori = new ArrayList<>();

	@FXML
	private TextField unosSifre;
	@FXML
	private TextField unosPrezimena;
	@FXML
	private TextField unosImena;
	@FXML
	private TextField unosTitule;

	@FXML
	public void initialize() {
		Profesor prazniProfeosor = new Profesor(null, null, null, null, -1);
		profesori = BazaPodataka.dohvatiProfesorePremaKriterijima(prazniProfeosor);
	}

	@FXML
	public void unos() {
		String Sifra = unosSifre.getText();
		String Ime = unosImena.getText();
		String Prezime = unosPrezimena.getText();
		String Titula = unosTitule.getText();
		OptionalLong maksimalniId = profesori.stream().mapToLong(profesor -> profesor.getId()).max();
		if (isStringEmpty(Sifra, Ime, Prezime, Titula)) {
			String poruka = "";
			if (Sifra.isEmpty()) {
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
			if (Titula.isEmpty()) {
				poruka += "Titula je obavezan podatak";
				poruka += NL;
			}
			Alert alert = new Alert(AlertType.ERROR);
			alert.setTitle("Pogrešan unos podataka");
			alert.setHeaderText("Molimo ispravite sljedeæe podatke");
			alert.setContentText(poruka);

			alert.showAndWait();
		} else {
			Profesor noviProfesor = new Profesor(Sifra, Ime, Prezime, Titula, maksimalniId.getAsLong() + 1);
			BazaPodataka.spremiNovogProfesora(noviProfesor);
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
