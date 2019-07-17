package hr.vjezbe.glavna;

import java.io.IOException;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

import hr.java.vjezbe.baza.BazaPodataka;
import hr.java.vjezbe.entitet.Ispit;
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
import javafx.scene.layout.BorderPane;
import javafx.util.Callback;

public class IspitiController {

	List<Ispit> ispiti = new ArrayList<>();

	@FXML
	private TableView<Ispit> tablicaIspita;

	@FXML
	private TableColumn<Ispit, String> naziv;

	@FXML
	private TableColumn<Ispit, String> ime;

	@FXML
	private TableColumn<Ispit, String> prezime;
	@FXML
	private TableColumn<Ispit, String> ocjenaTekst;
	@FXML
	private TableColumn<Ispit, String> datumColumn;

	@FXML
	private TextField unosNaziva;
	@FXML
	private TextField unosImena;
	@FXML
	private TextField unosPrezimena;
	@FXML
	private TextField unosOcjene;
	@FXML
	private DatePicker unosDatuma;

	@FXML
	public void initialize() {
		Ispit prazni = new Ispit(null, null, null ,null, -1);
		ispiti = BazaPodataka.dohvatiIspitePremaKriterijima(prazni);
		ObservableList<Ispit> listaIspita = FXCollections.observableArrayList(ispiti);
		naziv.setCellValueFactory(new Callback<TableColumn.CellDataFeatures<Ispit, String>, ObservableValue<String>>() {
			@Override
			public ObservableValue<String> call(TableColumn.CellDataFeatures<Ispit, String> ispit) {
				SimpleStringProperty property = new SimpleStringProperty();
				property.setValue(ispit.getValue().getPredmet().getNaziv());
				return property;
			}
		});
		ime.setCellValueFactory(new Callback<TableColumn.CellDataFeatures<Ispit, String>, ObservableValue<String>>() {
			@Override
			public ObservableValue<String> call(TableColumn.CellDataFeatures<Ispit, String> ispit) {
				SimpleStringProperty property = new SimpleStringProperty();
				property.setValue(ispit.getValue().getStudent().getIme());
				return property;
			}
		});
		prezime.setCellValueFactory(
				new Callback<TableColumn.CellDataFeatures<Ispit, String>, ObservableValue<String>>() {
					@Override
					public ObservableValue<String> call(TableColumn.CellDataFeatures<Ispit, String> ispit) {
						SimpleStringProperty property = new SimpleStringProperty();
						property.setValue(ispit.getValue().getStudent().getPrezime());
						return property;
					}
				});
		ocjenaTekst.setCellValueFactory(
				new Callback<TableColumn.CellDataFeatures<Ispit, String>, ObservableValue<String>>() {
					@Override
					public ObservableValue<String> call(TableColumn.CellDataFeatures<Ispit, String> ispit) {
						SimpleStringProperty property = new SimpleStringProperty();
						property.setValue(ispit.getValue().getOcjena().toString());
						return property;
					}
				});
		datumColumn.setCellValueFactory(
				new Callback<TableColumn.CellDataFeatures<Ispit, String>, ObservableValue<String>>() {
					@Override
					public ObservableValue<String> call(TableColumn.CellDataFeatures<Ispit, String> ispit) {
						SimpleStringProperty property = new SimpleStringProperty();
						DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd.MM.yyyy.'T'HH:mm");
						String pomocni = ispit.getValue().getDatumIVrijeme().format(formatter);
						property.setValue(pomocni);
						return property;
					}
				});
		tablicaIspita.setItems(listaIspita);

	}

	@FXML
	public void pretraga() {
		List<Ispit> filtrirani = new ArrayList<>();
		String Naziv = unosNaziva.getText();
		String Ime = unosImena.getText();
		String Prezime = unosPrezimena.getText();
		String OcjenaTekst = unosOcjene.getText();
		LocalDate Datum = unosDatuma.getValue();
		unosDatuma.getEditor().textProperty().isEmpty().addListener(new ChangeListener<Boolean>() {
			@Override
			public void changed(final ObservableValue<? extends Boolean> observable, final Boolean oldValue,
					final Boolean newValue) {
				if (newValue) {
					unosDatuma.setValue(null);
				}
			}
		});
		if (Naziv.isEmpty() && Ime.isEmpty() && Prezime.isEmpty() && OcjenaTekst.isEmpty() && Datum == null) {
			filtrirani = ispiti;
		} else {
			if (Datum != null) {
				filtrirani = ispiti.stream()
						.filter(p -> p.getPredmet().getNaziv().contains(Naziv) && p.getStudent().getIme().contains(Ime)
								&& p.getStudent().getPrezime().contains(Prezime)
								&& p.getOcjena().toString().contains(OcjenaTekst)
								&& (p.getDatumIVrijeme().getDayOfYear() == Datum.getDayOfYear()
										&& p.getDatumIVrijeme().getYear() == Datum.getYear()))
						.collect(Collectors.toList());
			} else {
				filtrirani = ispiti.stream()
						.filter(p -> p.getPredmet().getNaziv().contains(Naziv) && p.getStudent().getIme().contains(Ime)
								&& p.getStudent().getPrezime().contains(Prezime)
								&& p.getOcjena().toString().contains(OcjenaTekst))
						.collect(Collectors.toList());
			}
		}
		ObservableList<Ispit> listaIspita = FXCollections.observableArrayList(filtrirani);
		naziv.setCellValueFactory(new Callback<TableColumn.CellDataFeatures<Ispit, String>, ObservableValue<String>>() {
			@Override
			public ObservableValue<String> call(TableColumn.CellDataFeatures<Ispit, String> ispit) {
				SimpleStringProperty property = new SimpleStringProperty();
				property.setValue(ispit.getValue().getPredmet().getNaziv());
				return property;
			}
		});
		ime.setCellValueFactory(new Callback<TableColumn.CellDataFeatures<Ispit, String>, ObservableValue<String>>() {
			@Override
			public ObservableValue<String> call(TableColumn.CellDataFeatures<Ispit, String> ispit) {
				SimpleStringProperty property = new SimpleStringProperty();
				property.setValue(ispit.getValue().getStudent().getIme());
				return property;
			}
		});
		prezime.setCellValueFactory(
				new Callback<TableColumn.CellDataFeatures<Ispit, String>, ObservableValue<String>>() {
					@Override
					public ObservableValue<String> call(TableColumn.CellDataFeatures<Ispit, String> ispit) {
						SimpleStringProperty property = new SimpleStringProperty();
						property.setValue(ispit.getValue().getStudent().getPrezime());
						return property;
					}
				});
		ocjenaTekst.setCellValueFactory(
				new Callback<TableColumn.CellDataFeatures<Ispit, String>, ObservableValue<String>>() {
					@Override
					public ObservableValue<String> call(TableColumn.CellDataFeatures<Ispit, String> ispit) {
						SimpleStringProperty property = new SimpleStringProperty();
						property.setValue(ispit.getValue().getOcjena().toString());
						return property;
					}
				});
		datumColumn.setCellValueFactory(
				new Callback<TableColumn.CellDataFeatures<Ispit, String>, ObservableValue<String>>() {
					@Override
					public ObservableValue<String> call(TableColumn.CellDataFeatures<Ispit, String> ispit) {
						SimpleStringProperty property = new SimpleStringProperty();
						DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd.MM.yyyy.'T'HH:mm");
						String pomocni = ispit.getValue().getDatumIVrijeme().format(formatter);
						property.setValue(pomocni);
						return property;
					}
				});
		tablicaIspita.setItems(listaIspita);

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
