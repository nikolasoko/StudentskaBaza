package hr.vjezbe.glavna;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

import hr.java.vjezbe.baza.BazaPodataka;
import hr.java.vjezbe.entitet.Profesor;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.layout.BorderPane;

public class ProfesoriController {

	List<Profesor> profesori = new ArrayList<>();

	@FXML
	private TableView<Profesor> tablicaProfesora;

	@FXML
	private TableColumn<Profesor, String> sifra;

	@FXML
	private TableColumn<Profesor, String> prezime;

	@FXML
	private TableColumn<Profesor, String> ime;

	@FXML
	private TableColumn<Profesor, String> titula;
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
		Profesor prazni =new Profesor(null, null, null, null, -1);
		profesori = BazaPodataka.dohvatiProfesorePremaKriterijima(prazni);
		ObservableList<Profesor> listaProfesora = FXCollections.observableArrayList(profesori);
		sifra.setCellValueFactory(new PropertyValueFactory<Profesor, String>("sifra"));
		prezime.setCellValueFactory(new PropertyValueFactory<Profesor, String>("prezime"));
		ime.setCellValueFactory(new PropertyValueFactory<Profesor, String>("ime"));
		titula.setCellValueFactory(new PropertyValueFactory<Profesor, String>("titula"));

		tablicaProfesora.setItems(listaProfesora);

	}

	@FXML
	public void pretraga() {
		List<Profesor> filtrirani = new ArrayList<>();
		String Sifra = unosSifre.getText();
		String Ime = unosImena.getText();
		String Prezime = unosPrezimena.getText();
		String Titula = unosTitule.getText();
		if (Sifra.isEmpty() && Ime.isEmpty() && Prezime.isEmpty() && Titula.isEmpty()) {
			filtrirani = profesori;
		} else {
			filtrirani = profesori.stream()
					.filter(p -> p.getSifra().contains(Sifra) && p.getIme().contains(Ime)
							&& p.getPrezime().contains(Prezime) && p.getTitula().contains(Titula))
					.collect(Collectors.toList());
		}
		ObservableList<Profesor> listaProfesora = FXCollections.observableArrayList(filtrirani);
		sifra.setCellValueFactory(new PropertyValueFactory<Profesor, String>("sifra"));
		prezime.setCellValueFactory(new PropertyValueFactory<Profesor, String>("prezime"));
		ime.setCellValueFactory(new PropertyValueFactory<Profesor, String>("ime"));
		titula.setCellValueFactory(new PropertyValueFactory<Profesor, String>("titula"));

		tablicaProfesora.setItems(listaProfesora);

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
}
