package hr.java.vjezbe.baza;

import java.io.FileReader;
import java.io.IOException;
import java.sql.Connection;
import java.sql.Date;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.sql.Timestamp;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.ZoneId;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.Properties;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import hr.java.vjezbe.entitet.Ispit;
import hr.java.vjezbe.entitet.Predmet;
import hr.java.vjezbe.entitet.Profesor;
import hr.java.vjezbe.entitet.Student;
import hr.java.vjezbe.iznimke.BazaPodatakaException;

public class BazaPodataka {

	private static final Logger logger = LoggerFactory.getLogger(BazaPodataka.class);
	private static final String DATABASE_FILE = "src\\hr\\java\\vjezbe\\baza\\database.properties";

	private static Connection spajanjeNaBazu() throws SQLException, IOException {
		Properties svojstva = new Properties();
		svojstva.load(new FileReader(DATABASE_FILE));
		String urlBazePodataka = svojstva.getProperty("bazaPodatakaUrl");
		String korisnickoIme = svojstva.getProperty("korisnickoIme");
		String lozinka = svojstva.getProperty("lozinka");
		Connection veza = DriverManager.getConnection(urlBazePodataka, korisnickoIme, lozinka);
		return veza;
	}

	public static List<Profesor> dohvatiProfesorePremaKriterijima(Profesor profesor) throws BazaPodatakaException {
		List<Profesor> listaProfesora = new ArrayList<>();
		try (Connection veza = spajanjeNaBazu()) {
			StringBuilder sqlUpit = new StringBuilder("SELECT * FROM PROFESOR WHERE 1 = 1");
			if (Optional.ofNullable(profesor).isEmpty() == false) {
				if (Optional.ofNullable(profesor).map(Profesor::getId).isPresent()
						&& Optional.ofNullable(profesor).map(Profesor::getId).get() > 0) {
					sqlUpit.append(" AND ID = " + profesor.getId());
				}
				if (Optional.ofNullable(profesor.getSifra()).map(String::isBlank).orElse(true) == false) {
					sqlUpit.append(" AND SIFRA LIKE '%" + profesor.getSifra() + "%'");
				}
				if (Optional.ofNullable(profesor.getIme()).map(String::isBlank).orElse(true) == false) {
					sqlUpit.append(" AND IME LIKE '%" + profesor.getIme() + "%'");
				}
				if (Optional.ofNullable(profesor.getPrezime()).map(String::isBlank).orElse(true) == false) {
					sqlUpit.append(" AND PREZIME LIKE '%" + profesor.getPrezime() + "%'");
				}
				if (Optional.ofNullable(profesor.getTitula()).map(String::isBlank).orElse(true) == false) {
					sqlUpit.append(" AND TITULA LIKE '%" + profesor.getTitula() + "%'");
				}
			}
			Statement upit = veza.createStatement();
			ResultSet resultSet = upit.executeQuery(sqlUpit.toString());
			while (resultSet.next()) {
				Long id = resultSet.getLong("id");
				String sifra = resultSet.getString("sifra");
				String ime = resultSet.getString("ime");
				String prezime = resultSet.getString("prezime");
				String titula = resultSet.getString("titula");
				Profesor noviProfesor = new Profesor(sifra, ime, prezime, titula, id);
				listaProfesora.add(noviProfesor);
			}
		} catch (SQLException | IOException ex) {
			String poruka = "Došlo je do pogreške u radu s bazom podataka";
			logger.error(poruka, ex);
			throw new BazaPodatakaException(poruka, ex);
		}
		return listaProfesora;
	}

	public static List<Student> dohvatiStudentePremaKriterijima(Student student) throws BazaPodatakaException {
		List<Student> listaStudenata = new ArrayList<>();
		try (Connection veza = spajanjeNaBazu()) {
			StringBuilder sqlUpit = new StringBuilder("SELECT * FROM STUDENT WHERE 1 = 1");
			if (Optional.ofNullable(student).isEmpty() == false) {
				if (Optional.ofNullable(student).map(Student::getId).isPresent()
						&& Optional.ofNullable(student).map(Student::getId).get() > 0) {
					sqlUpit.append(" AND ID = " + student.getId());
				}
				if (Optional.ofNullable(student.getJmbag()).map(String::isBlank).orElse(true) == false) {
					sqlUpit.append(" AND JMBAG LIKE '%" + student.getJmbag() + "%'");
				}
				if (Optional.ofNullable(student.getIme()).map(String::isBlank).orElse(true) == false) {
					sqlUpit.append(" AND IME LIKE '%" + student.getIme() + "%'");
				}
				if (Optional.ofNullable(student.getPrezime()).map(String::isBlank).orElse(true) == false) {
					sqlUpit.append(" AND PREZIME LIKE '%" + student.getPrezime() + "%'");
				}
				if (Optional.ofNullable(student.getDatumrodjenja()).isPresent()) {
					sqlUpit.append(" AND DATUM_RODJENJA LIKE '%"
							+ student.getDatumrodjenja().format(DateTimeFormatter.ISO_DATE) + "%'");
				}
			}
			Statement upit = veza.createStatement();
			ResultSet resultSet = upit.executeQuery(sqlUpit.toString());
			while (resultSet.next()) {
				Long id = resultSet.getLong("id");
				String ime = resultSet.getString("ime");
				String prezime = resultSet.getString("prezime");
				String jmbag = resultSet.getString("jmbag");
				LocalDate datumRodjenja = resultSet.getTimestamp("datum_rodjenja").toInstant()
						.atZone(ZoneId.systemDefault()).toLocalDate();
				Student noviStudent = new Student(ime, prezime, jmbag, datumRodjenja, id);
				listaStudenata.add(noviStudent);
			}
		} catch (SQLException | IOException ex) {
			String poruka = "Došlo je do pogreške u radu s bazom podataka";
			logger.error(poruka, ex);
			throw new BazaPodatakaException(poruka, ex);
		}
		return listaStudenata;
	}

	public static List<Predmet> dohvatiPredmetePremaKriterijima(Predmet predmet) throws BazaPodatakaException {
		List<Predmet> listaPredmeta = new ArrayList<>();
		try (Connection veza = spajanjeNaBazu()) {
			StringBuilder sqlUpit = new StringBuilder("SELECT * FROM PREDMET WHERE 1 = 1");
			if (Optional.ofNullable(predmet).isEmpty() == false) {
				if (Optional.ofNullable(predmet).map(Predmet::getId).isPresent()
						&& Optional.ofNullable(predmet).map(Predmet::getId).get() > 0) {
					sqlUpit.append(" AND ID = " + predmet.getId());
				}
				if (Optional.ofNullable(predmet.getNaziv()).map(String::isBlank).orElse(true) == false) {
					sqlUpit.append(" AND NAZIV LIKE '%" + predmet.getNaziv() + "%'");
				}
				if (Optional.ofNullable(predmet.getBrojEctsBodova()).isPresent()) {
					sqlUpit.append(" AND BROJ_ECTS_BODOVA LIKE '%" + predmet.getBrojEctsBodova() + "%'");
				}
				if (Optional.ofNullable(predmet.getSifra()).map(String::isBlank).orElse(true) == false) {
					sqlUpit.append(" AND SIFRA LIKE '%" + predmet.getSifra() + "%'");
				}
				if (Optional.ofNullable(predmet).map(Predmet::getNositelj).isPresent()) {
					long idNositelja = predmet.getNositelj().getId();
					sqlUpit.append(" AND PROFESOR_ID LIKE '%" + idNositelja + "%'");
				}
			}
			Statement upit = veza.createStatement();
			ResultSet resultSet = upit.executeQuery(sqlUpit.toString());
			while (resultSet.next()) {
				Long id = resultSet.getLong("id");
				String naziv = resultSet.getString("naziv");
				Integer broj_ects_bodova = resultSet.getInt("broj_ects_bodova");
				String sifra = resultSet.getString("sifra");
				Long idProfesora = resultSet.getLong("profesor_id");
				Profesor pomocni = new Profesor(null, null, null, null, idProfesora);
				List<Profesor> profesori = dohvatiProfesorePremaKriterijima(pomocni);
				Predmet noviPredmet = new Predmet(sifra, naziv, broj_ects_bodova, profesori.get(0), id);
				listaPredmeta.add(noviPredmet);
			}
		} catch (SQLException | IOException ex) {
			String poruka = "Došlo je do pogreške u radu s bazom podataka";
			logger.error(poruka, ex);
			throw new BazaPodatakaException(poruka, ex);
		}
		return listaPredmeta;
	}

	public static List<Ispit> dohvatiIspitePremaKriterijima(Ispit ispit) throws BazaPodatakaException {
		List<Ispit> listaIspita = new ArrayList<>();
		try (Connection veza = spajanjeNaBazu()) {
			StringBuilder sqlUpit = new StringBuilder("SELECT * FROM ISPIT WHERE 1 = 1");
			if (Optional.ofNullable(ispit).isEmpty() == false) {
				if (Optional.ofNullable(ispit).map(Ispit::getId).isPresent()
						&& Optional.ofNullable(ispit).map(Ispit::getId).get() > 0) {
					sqlUpit.append(" AND ID = " + ispit.getId());
				}
				if (Optional.ofNullable(ispit).map(Ispit::getOcjena).isPresent()) {
					sqlUpit.append(" AND OCJENA LIKE '%" + ispit.getOcjena() + "%'");
				}
				if (Optional.ofNullable(ispit).map(Ispit::getPredmet).isPresent()) {
					long idPredmeta = ispit.getPredmet().getId();
					sqlUpit.append(" AND PREDMET_ID LIKE '%" + idPredmeta + "%'");
				}
				if (Optional.ofNullable(ispit).map(Ispit::getStudent).isPresent()) {
					long idStudenta = ispit.getStudent().getId();
					sqlUpit.append(" AND STUDENT_ID LIKE '%" + idStudenta + "%'");
				}
				if (Optional.ofNullable(ispit).map(Ispit::getDatumIVrijeme).isPresent()) {
					DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd'T'HH:mm:ss.SS");
					sqlUpit.append(" AND DATUM_I_VRIJEME LIKE '%" + ispit.getDatumIVrijeme().format(formatter) + "%'");
				}
			}
			Statement upit = veza.createStatement();
			ResultSet resultSet = upit.executeQuery(sqlUpit.toString());
			while (resultSet.next()) {
				Long id = resultSet.getLong("id");
				Long idPredmeta = resultSet.getLong("predmet_id");
				Long idStudenta = resultSet.getLong("student_id");
				Integer ocjena = resultSet.getInt("ocjena");
				LocalDateTime datum_i_vrijeme = resultSet.getTimestamp("datum_i_vrijeme").toLocalDateTime();
				Predmet pomocniPredmet = new Predmet(null, null, null, null, idPredmeta);
				Student pomocniStudent = new Student(null, null, null, null, idStudenta);
				List<Predmet> predmeti = dohvatiPredmetePremaKriterijima(pomocniPredmet);
				List<Student> studenti = dohvatiStudentePremaKriterijima(pomocniStudent);

				Ispit noviIspit = new Ispit(predmeti.get(0), studenti.get(0), ocjena, datum_i_vrijeme, id);
				listaIspita.add(noviIspit);
			}
		} catch (SQLException | IOException ex) {
			String poruka = "Došlo je do pogreške u radu s bazom podataka";
			logger.error(poruka, ex);
			throw new BazaPodatakaException(poruka, ex);
		}
		return listaIspita;
	}

	public static void spremiNovogProfesora(Profesor profesor) throws BazaPodatakaException {
		try (Connection veza = spajanjeNaBazu()) {
			PreparedStatement preparedStatement = veza
					.prepareStatement("INSERT INTO PROFESOR(ime, prezime, sifra, titula) VALUES (?, ?, ?, ?)");
			preparedStatement.setString(1, profesor.getIme());
			preparedStatement.setString(2, profesor.getPrezime());
			preparedStatement.setString(3, profesor.getSifra());
			preparedStatement.setString(4, profesor.getTitula());
			preparedStatement.executeUpdate();
		} catch (SQLException | IOException ex) {
			String poruka = "Došlo je do pogreške u radu s bazom podataka";
			logger.error(poruka, ex);
			throw new BazaPodatakaException(poruka, ex);
		}
	}

	public static void spremiNovogStudenta(Student student) throws BazaPodatakaException {
		try (Connection veza = spajanjeNaBazu()) {
			PreparedStatement preparedStatement = veza
					.prepareStatement("INSERT INTO STUDENT(ime, prezime, jmbag, datum_rodjenja) VALUES (?, ?, ?, ?)");
			preparedStatement.setString(1, student.getIme());
			preparedStatement.setString(2, student.getPrezime());
			preparedStatement.setString(3, student.getJmbag());
			preparedStatement.setDate(4, Date.valueOf(student.getDatumrodjenja()));
			preparedStatement.executeUpdate();
		} catch (SQLException | IOException ex) {
			String poruka = "Došlo je do pogreške u radu s bazom podataka";
			logger.error(poruka, ex);
			throw new BazaPodatakaException(poruka, ex);
		}
	}

	public static void spremiNoviPredmet(Predmet predmet) throws BazaPodatakaException {
		try (Connection veza = spajanjeNaBazu()) {
			PreparedStatement preparedStatement = veza.prepareStatement(
					"INSERT INTO PREDMET(sifra, naziv, broj_ects_bodova, profesor_id) VALUES (?, ?, ?, ?)");
			preparedStatement.setString(1, predmet.getSifra());
			preparedStatement.setString(2, predmet.getNaziv());
			preparedStatement.setInt(3, predmet.getBrojEctsBodova());
			preparedStatement.setLong(4, predmet.getNositelj().getId());
			preparedStatement.executeUpdate();
		} catch (SQLException | IOException ex) {
			String poruka = "Došlo je do pogreške u radu s bazom podataka";
			logger.error(poruka, ex);
			throw new BazaPodatakaException(poruka, ex);
		}
	}

	public static void spremiNoviIspit(Ispit ispit) throws BazaPodatakaException {
		try (Connection veza = spajanjeNaBazu()) {
			PreparedStatement preparedStatement = veza.prepareStatement(
					"INSERT INTO ISPIT(predmet_id, student_id, ocjena, datum_i_vrijeme) VALUES (?, ?, ?, ?)");
			preparedStatement.setLong(1, ispit.getPredmet().getId());
			preparedStatement.setLong(2, ispit.getStudent().getId());
			preparedStatement.setInt(3, ispit.getOcjena());
			preparedStatement.setTimestamp(4, Timestamp.valueOf(ispit.getDatumIVrijeme()));
			preparedStatement.executeUpdate();
		} catch (SQLException | IOException ex) {
			String poruka = "Došlo je do pogreške u radu s bazom podataka";
			logger.error(poruka, ex);
			throw new BazaPodatakaException(poruka, ex);
		}
	}

	public static List<Student> dohvatiStudenteKojiImajuRodjendan() {
		List<Student> slavljenici = new ArrayList<Student>();
		LocalDate danas = LocalDate.now();
		try (Connection veza = spajanjeNaBazu()) {
			StringBuilder sqlUpit = new StringBuilder("SELECT * FROM STUDENT WHERE DAY(DATUM_RODJENJA) = "
					+ danas.getDayOfMonth() + " AND MONTH(DATUM_RODJENJA) = " + danas.getMonthValue());
			Statement upit = veza.createStatement();
			ResultSet resultSet = upit.executeQuery(sqlUpit.toString());
			while (resultSet.next()) {
				Long id = resultSet.getLong("id");
				String ime = resultSet.getString("ime");
				String prezime = resultSet.getString("prezime");
				String jmbag = resultSet.getString("jmbag");
				LocalDate datumRodjenja = resultSet.getTimestamp("datum_rodjenja").toInstant()
						.atZone(ZoneId.systemDefault()).toLocalDate();
				Student noviStudent = new Student(ime, prezime, jmbag, datumRodjenja, id);
				slavljenici.add(noviStudent);
			}
		} catch (SQLException | IOException ex) {
			String poruka = "Došlo je do pogreške u radu s bazom podataka";
			logger.error(poruka, ex);
			throw new BazaPodatakaException(poruka, ex);
		}
		return slavljenici;

	}
}
