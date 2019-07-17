package hr.vjezbe.glavna;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

import hr.java.vjezbe.baza.BazaPodataka;
import hr.java.vjezbe.entitet.Predmet;
import javafx.beans.property.SimpleStringProperty;
import javafx.beans.value.ObservableValue;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.layout.BorderPane;
import javafx.util.Callback;

public class PredmetiController {
	List<Predmet> predmeti = new ArrayList<>();

	@FXML
	private TableView<Predmet> tablicaPredmeta;

	@FXML
	private TableColumn<Predmet, String> sifra;

	@FXML
	private TableColumn<Predmet, String> naziv;

	@FXML
	private TableColumn<Predmet, String> textBroj;
	@FXML
	private TableColumn<Predmet, String> nositeljIme;
	@FXML
	private TableColumn<Predmet, String> nositeljPrezime;

	@FXML
	private TextField unosSifre;
	@FXML
	private TextField unosNaziva;
	@FXML
	private TextField unosEctsa;
	@FXML
	private TextField unosImeNositelja;
	@FXML
	private TextField unosPrezimeNositelja;

	@FXML
	public void initialize() {
		Predmet prazni= new Predmet(null, null, null, null, -1);
		predmeti = BazaPodataka.dohvatiPredmetePremaKriterijima(prazni);
		ObservableList<Predmet> listaPredmeta = FXCollections.observableArrayList(predmeti);
		sifra.setCellValueFactory(new PropertyValueFactory<Predmet, String>("sifra"));
		naziv.setCellValueFactory(new PropertyValueFactory<Predmet, String>("naziv"));
		textBroj.setCellValueFactory(
				new Callback<TableColumn.CellDataFeatures<Predmet, String>, ObservableValue<String>>() {
					@Override
					public ObservableValue<String> call(TableColumn.CellDataFeatures<Predmet, String> predmet) {
						SimpleStringProperty property = new SimpleStringProperty();
						property.setValue(predmet.getValue().getBrojEctsBodova().toString());
						return property;
					}
				});
		nositeljIme.setCellValueFactory(
				new Callback<TableColumn.CellDataFeatures<Predmet, String>, ObservableValue<String>>() {
					@Override
					public ObservableValue<String> call(TableColumn.CellDataFeatures<Predmet, String> predmet) {
						SimpleStringProperty property = new SimpleStringProperty();
						property.setValue(predmet.getValue().getNositelj().getIme());
						return property;
					}
				});
		nositeljPrezime.setCellValueFactory(
				new Callback<TableColumn.CellDataFeatures<Predmet, String>, ObservableValue<String>>() {
					@Override
					public ObservableValue<String> call(TableColumn.CellDataFeatures<Predmet, String> predmet) {
						SimpleStringProperty property = new SimpleStringProperty();
						property.setValue(predmet.getValue().getNositelj().getPrezime());
						return property;
					}
				});

		tablicaPredmeta.setItems(listaPredmeta);

	}

	@FXML
	public void pretraga() {
		List<Predmet> filtrirani = new ArrayList<>();
		String Sifra = unosSifre.getText();
		String Naziv = unosNaziva.getText();
		String TextBroj = unosEctsa.getText();
		String ImeNositelj = unosImeNositelja.getText();
		String PrezimeNositelja = unosPrezimeNositelja.getText();
		if (Sifra.isEmpty() && Naziv.isEmpty() && TextBroj.isEmpty() && ImeNositelj.isEmpty()
				&& PrezimeNositelja.isEmpty()) {
			filtrirani = predmeti;
		} else {
			if (TextBroj.isEmpty()) {
				filtrirani = predmeti.stream()
						.filter(p -> p.getSifra().contains(Sifra) && p.getNaziv().contains(Naziv)
								&& p.getNositelj().getIme().contains(ImeNositelj)
								&& p.getNositelj().getPrezime().contains(PrezimeNositelja))
						.collect(Collectors.toList());
			} else {
				filtrirani = predmeti.stream().filter(p -> p.getSifra().contains(Sifra) && p.getNaziv().contains(Naziv)
						&& p.getBrojEctsBodova().toString().equals(TextBroj)).collect(Collectors.toList());
			}
		}
		ObservableList<Predmet> listaPredmeta = FXCollections.observableArrayList(filtrirani);
		sifra.setCellValueFactory(new PropertyValueFactory<Predmet, String>("sifra"));
		naziv.setCellValueFactory(new PropertyValueFactory<Predmet, String>("naziv"));
		textBroj.setCellValueFactory(
				new Callback<TableColumn.CellDataFeatures<Predmet, String>, ObservableValue<String>>() {
					@Override
					public ObservableValue<String> call(TableColumn.CellDataFeatures<Predmet, String> predmet) {
						SimpleStringProperty property = new SimpleStringProperty();
						property.setValue(predmet.getValue().getBrojEctsBodova().toString());
						return property;
					}
				});
		nositeljIme.setCellValueFactory(
				new Callback<TableColumn.CellDataFeatures<Predmet, String>, ObservableValue<String>>() {
					@Override
					public ObservableValue<String> call(TableColumn.CellDataFeatures<Predmet, String> predmet) {
						SimpleStringProperty property = new SimpleStringProperty();
						property.setValue(predmet.getValue().getNositelj().getIme());
						return property;
					}
				});
		nositeljPrezime.setCellValueFactory(
				new Callback<TableColumn.CellDataFeatures<Predmet, String>, ObservableValue<String>>() {
					@Override
					public ObservableValue<String> call(TableColumn.CellDataFeatures<Predmet, String> predmet) {
						SimpleStringProperty property = new SimpleStringProperty();
						property.setValue(predmet.getValue().getNositelj().getPrezime());
						return property;
					}
				});

		tablicaPredmeta.setItems(listaPredmeta);

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
