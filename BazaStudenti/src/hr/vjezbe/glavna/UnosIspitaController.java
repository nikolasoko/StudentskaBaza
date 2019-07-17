package hr.vjezbe.glavna;

import java.io.IOException;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;
import java.util.OptionalLong;
import java.util.stream.Collectors;

import hr.java.vjezbe.baza.BazaPodataka;
import hr.java.vjezbe.entitet.Ispit;
import hr.java.vjezbe.entitet.Predmet;
import hr.java.vjezbe.entitet.Student;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.ComboBox;
import javafx.scene.control.DatePicker;
import javafx.scene.control.TextField;
import javafx.scene.layout.BorderPane;

public class UnosIspitaController {
	public static final String NL = System.getProperty("line.separator");
	public static final String Ispiti = "dat/ispiti.txt";

	List<Predmet> predmeti = new ArrayList<>();
	List<Student> studenti = new ArrayList<>();
	List<Ispit> ispiti = new ArrayList<>();

	@FXML
	private ComboBox<String> IzbornikPredmeta;
	@FXML
	private ComboBox<String> IzbornikStudenta;
	@FXML
	private TextField unosOcjene;
	@FXML
	private TextField unosVremena;
	@FXML
	private DatePicker unosDatuma;

	@FXML
	public void initialize() {
		Predmet prazniPredmet = new Predmet(null, null, null, null, -1);
		predmeti = BazaPodataka.dohvatiPredmetePremaKriterijima(prazniPredmet);
		Student prazniStudent = new Student(null, null, null, null, -1);
		studenti = BazaPodataka.dohvatiStudentePremaKriterijima(prazniStudent);
		Ispit prazniIspit = new Ispit(null, null, null, null, -1);
		ispiti = BazaPodataka.dohvatiIspitePremaKriterijima(prazniIspit);
		/*
		predmeti = Datoteke.dohvatiPredmete();
		studenti = Datoteke.dohvatiStudente();
		ispiti = Datoteke.dohvatiIspite();*/
		List<String> imenaStudenta = studenti.stream().map(s -> s.getIme() + " " + s.getPrezime() + " " + s.getId())
				.collect(Collectors.toList());
		ObservableList<String> listaStudenta = FXCollections.observableArrayList(imenaStudenta);
		IzbornikStudenta.setItems(listaStudenta);

		List<String> imenaPredmeta = predmeti.stream().map(s -> s.getNaziv() + " " + s.getId())
				.collect(Collectors.toList());
		ObservableList<String> listaPredmeta = FXCollections.observableArrayList(imenaPredmeta);
		IzbornikPredmeta.setItems(listaPredmeta);

	}

	@FXML
	public void unos() {
		String OdabraniPredmet = IzbornikPredmeta.getValue();
		String OdabraniStudent = IzbornikStudenta.getValue();
		String Ocjena = unosOcjene.getText();
		LocalDate Datum = unosDatuma.getValue();
		String Vrijeme = unosVremena.getText();
		boolean predmetiPrazno = IzbornikPredmeta.getSelectionModel().isEmpty();
		boolean studentiPrazno = IzbornikStudenta.getSelectionModel().isEmpty();
		OptionalLong maksimalniId = ispiti.stream().mapToLong(predmet -> predmet.getId()).max();
		if (isStringEmpty(Ocjena,Vrijeme)  || (Datum == null) || predmetiPrazno || studentiPrazno) {
			String poruka = "";
			if (predmetiPrazno) {
				poruka = "Predmet je obavezan podatak";
				poruka += NL;
			}
			if (studentiPrazno) {
				poruka += "Student je obavezan podatak";
				poruka += NL;
			}
			if (Ocjena.isEmpty()) {
				poruka += "Ocjena je obavezan podatak";
				poruka += NL;
			}
			if (Vrijeme.isEmpty()) {
				poruka += "Vrijeme je obavezan podatak";
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
			Integer ocjena = Integer.valueOf(Ocjena);
			String predmetIdString=OdabraniPredmet.substring(OdabraniPredmet.lastIndexOf(" ") + 1,OdabraniPredmet.length());
			long idPredmeta = Long.parseLong(predmetIdString);
			String studentIdString=OdabraniStudent.substring(OdabraniStudent.lastIndexOf(" ") + 1,OdabraniStudent.length());
			long idStudenta = Long.parseLong(studentIdString);
			DateTimeFormatter formatter1 = DateTimeFormatter.ofPattern("dd.MM.yyyy.");
			String datumiVrijemeString= Datum.format(formatter1) +  "T" + Vrijeme;
			DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd.MM.yyyy.'T'HH:mm");
			LocalDateTime datumIspita = LocalDateTime.parse(datumiVrijemeString, formatter);
			List<Predmet> predmetLista = BazaPodataka.dohvatiPredmetePremaKriterijima(new Predmet(null, null, null, null, idPredmeta));
			List<Student> studentiLista = BazaPodataka.dohvatiStudentePremaKriterijima(new Student(null, null, null, null, idStudenta));
			Ispit noviIspit = new Ispit(predmetLista.get(0), studentiLista.get(0), ocjena, datumIspita, maksimalniId.getAsLong() + 1);
			BazaPodataka.spremiNoviIspit(noviIspit);
			/*
			try (PrintWriter out = new PrintWriter(new FileWriter(new File(Ispiti), true))) {
				out.println();
				out.println(maksimalniId.getAsLong() + 1);
				String predmetId=OdabraniPredmet.substring(OdabraniPredmet.lastIndexOf(" ") + 1,OdabraniPredmet.length());
				out.println(predmetId);
				String studentId=OdabraniStudent.substring(OdabraniStudent.lastIndexOf(" ") + 1,OdabraniStudent.length());
				out.println(studentId);
				out.println(Ocjena);
				DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd.MM.yyyy.");
				String formattedString = Datum.format(formatter);
				out.print(formattedString);
				out.print("T" + Vrijeme);
			} catch (IOException e) {
				System.err.println(e);
			}*/
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
