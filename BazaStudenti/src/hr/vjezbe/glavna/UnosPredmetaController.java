package hr.vjezbe.glavna;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.OptionalLong;
import java.util.stream.Collectors;

import hr.java.vjezbe.baza.BazaPodataka;
import hr.java.vjezbe.entitet.Predmet;
import hr.java.vjezbe.entitet.Profesor;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.ComboBox;
import javafx.scene.control.TextField;
import javafx.scene.layout.BorderPane;

public class UnosPredmetaController {
	public static final String NL = System.getProperty("line.separator");
	public static final String Predmeti = "dat/predmeti.txt";

	List<Predmet> predmeti = new ArrayList<>();
	List<Profesor> profesori = new ArrayList<>();
	@FXML
	private TextField unosSifre;
	@FXML
	private TextField unosNaziva;
	@FXML
	private TextField unosEctsa;
	@FXML
	private ComboBox<String> nositelji;

	@FXML
	public void initialize() {
		Predmet prazniPredmet = new Predmet(null, null, null, null, -1);
		predmeti = BazaPodataka.dohvatiPredmetePremaKriterijima(prazniPredmet);
		Profesor prazniProfeosor = new Profesor(null, null, null, null, -1);
		profesori = BazaPodataka.dohvatiProfesorePremaKriterijima(prazniProfeosor);
		List<String> imena = profesori.stream()
				   .map(s -> s.getIme() + " " + s.getPrezime() + " " + s.getId())
				   .collect(Collectors.toList());
		ObservableList<String> listaprofesora =FXCollections.observableArrayList(imena);
		nositelji.setItems(listaprofesora);
	}

	@FXML
	public void unos() {
		String Sifra = unosSifre.getText();
		String Naziv = unosNaziva.getText();
		String TextBroj = unosEctsa.getText();
		String Nositelj = nositelji.getValue();
		boolean noisteljiPrazno = nositelji.getSelectionModel().isEmpty();

		OptionalLong maksimalniId = predmeti.stream().mapToLong(predmet -> predmet.getId()).max();
		if (isStringEmpty(Sifra, Naziv, TextBroj) || noisteljiPrazno) {
			String poruka = "";
			if (Sifra.isEmpty()) {
				poruka = "Šifra je obavezan podatak";
				poruka += NL;
			}
			if (Naziv.isEmpty()) {
				poruka += "Naziv je obavezan podatak";
				poruka += NL;
			}
			if (TextBroj.isEmpty()) {
				poruka += "Ects je obavezan podatak";
				poruka += NL;
			}
			if (noisteljiPrazno) {
				poruka += "Nositelj je obavezan podatak";
				poruka += NL;
			}
			Alert alert = new Alert(AlertType.ERROR);
			alert.setTitle("Pogrešan unos podataka");
			alert.setHeaderText("Molimo ispravite sljedeæe podatke");
			alert.setContentText(poruka);

			alert.showAndWait();
		} else {
			Integer Ects = Integer.valueOf(TextBroj);
			String idNositeljaString=Nositelj.substring(Nositelj.lastIndexOf(" ") + 1,Nositelj.length());
			long idNositelja= Long.parseLong(idNositeljaString);
			List<Profesor> nositelji =BazaPodataka.dohvatiProfesorePremaKriterijima(new Profesor(null, null, null, null, idNositelja));
			Predmet noviPredmet = new Predmet(Sifra,Naziv, Ects ,nositelji.get(0),maksimalniId.getAsLong() + 1);
			BazaPodataka.spremiNoviPredmet(noviPredmet);
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
