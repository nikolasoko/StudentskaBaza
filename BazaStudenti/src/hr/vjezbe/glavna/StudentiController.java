package hr.vjezbe.glavna;

import java.io.IOException;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

import hr.java.vjezbe.baza.BazaPodataka;
import hr.java.vjezbe.entitet.Student;
import javafx.beans.property.SimpleStringProperty;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.control.DatePicker;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.layout.BorderPane;
import javafx.util.Callback;

public class StudentiController {

	List<Student> studenti = new ArrayList<>();
	@FXML
	private TableView<Student> tablicaStudenata;

	@FXML
	private TableColumn<Student, String> jmbag;

	@FXML
	private TableColumn<Student, String> prezime;

	@FXML
	private TableColumn<Student, String> ime;

	@FXML
	private TableColumn<Student, String> datumRodjenjaTableColumn;
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
		ObservableList<Student> listaStudenata = FXCollections.observableArrayList(studenti);
		jmbag.setCellValueFactory(new PropertyValueFactory<Student, String>("jmbag"));
		prezime.setCellValueFactory(new PropertyValueFactory<Student, String>("prezime"));
		ime.setCellValueFactory(new PropertyValueFactory<Student, String>("ime"));
		datumRodjenjaTableColumn.setCellValueFactory(
				new Callback<TableColumn.CellDataFeatures<Student, String>, ObservableValue<String>>() {
					@Override
					public ObservableValue<String> call(TableColumn.CellDataFeatures<Student, String> student) {
						SimpleStringProperty property = new SimpleStringProperty();
						DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd.MM.yyyy.");
						property.setValue(student.getValue().getDatumrodjenja().format(formatter));
						return property;
					}
				});

		tablicaStudenata.setItems(listaStudenata);

	}
	
	
	@FXML
	public void pretraga() {
		List<Student> filtrirani = new ArrayList<>();
		String Jmbag = unosJmbaga.getText();
		String Ime = unosImena.getText();
		String Prezime = unosPrezimena.getText();
		// DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd.MM.yyyy.");
		// LocalDate Datum = LocalDate.parse(DatumTekst, formatter);
		// String DatumTekst = unosDatuma.getValue().
		LocalDate Datum = unosDatuma.getValue();
		unosDatuma.getEditor().textProperty().isEmpty().addListener(new ChangeListener<Boolean>() {
		      @Override
		      public void changed(final ObservableValue<? extends Boolean> observable, 
		                final Boolean oldValue, final Boolean newValue) {
		           if (newValue){
		              unosDatuma.setValue(null);
		            }
		      }
		});

		if (Jmbag.isEmpty() && Ime.isEmpty() && Prezime.isEmpty() && Datum == null) {
			filtrirani = studenti;
		} else {
			if (Datum != null) {
				filtrirani = studenti.stream()
						.filter(p -> p.getJmbag().contains(Jmbag) && p.getIme().contains(Ime)
								&& p.getPrezime().contains(Prezime) && p.getDatumrodjenja().equals(Datum))
						.collect(Collectors.toList());
			} else {
				filtrirani = studenti.stream().filter(p -> p.getJmbag().contains(Jmbag) && p.getIme().contains(Ime)
						&& p.getPrezime().contains(Prezime)).collect(Collectors.toList());
			}
		}
		ObservableList<Student> listaStudenata = FXCollections.observableArrayList(filtrirani);
		jmbag.setCellValueFactory(new PropertyValueFactory<Student, String>("jmbag"));
		prezime.setCellValueFactory(new PropertyValueFactory<Student, String>("prezime"));
		ime.setCellValueFactory(new PropertyValueFactory<Student, String>("ime"));
		datumRodjenjaTableColumn.setCellValueFactory(
				new Callback<TableColumn.CellDataFeatures<Student, String>, ObservableValue<String>>() {
					@Override
					public ObservableValue<String> call(TableColumn.CellDataFeatures<Student, String> student) {
						SimpleStringProperty property = new SimpleStringProperty();
						DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd.MM.yyyy.");
						property.setValue(student.getValue().getDatumrodjenja().format(formatter));
						return property;
					}
				});

		tablicaStudenata.setItems(listaStudenata);
		
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
