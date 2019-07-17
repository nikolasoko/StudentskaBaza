package hr.vjezbe.util;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

import hr.java.vjezbe.entitet.Ispit;
import hr.java.vjezbe.entitet.Predmet;
import hr.java.vjezbe.entitet.Profesor;
import hr.java.vjezbe.entitet.Student;

public class Datoteke {
	
	public static final String PROFESORI = "dat/profesori.txt";
	public static final String STUDENTI = "dat/studenti.txt";
	public static final String PREDMETI = "dat/predmeti.txt";
	public static final String ISPITI = "dat/ispiti.txt";
	
	

	public static List<Profesor> dohvatiProfesore() {
		List<Profesor> profesori = new ArrayList<>();
		try (BufferedReader in = new BufferedReader(new FileReader(PROFESORI))) {
			System.out.println("Uèitavanje profesora...");
			String line;
			while ((line = in.readLine()) != null) {
				long id = Long.parseLong(line);
				String sifra = in.readLine();
				String ime = in.readLine();
				String prezime = in.readLine();
				String titula = in.readLine();
				profesori.add(new Profesor(sifra, ime, prezime, titula, id));

			}
		} catch (IOException e) {
			System.err.println(e);
		}
		return profesori;
	}

	public static List<Student> dohvatiStudente() {
		List<Student> studenti = new ArrayList<>();
		try (BufferedReader in = new BufferedReader(new FileReader(STUDENTI))) {
			System.out.println("Uèitavanje studenata...");
			String line;
			while ((line = in.readLine()) != null) {
				long id = Long.parseLong(line);
				String ime = in.readLine();
				String prezime = in.readLine();
				String jmbag = in.readLine();
				line = in.readLine();
				DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd.MM.yyyy.");
				LocalDate datumrodjenja = LocalDate.parse(line, formatter);
				studenti.add(new Student(ime, prezime, jmbag, datumrodjenja, id));

			}
		} catch (IOException e) {
			System.err.println(e);
		}
		return studenti;
	}
	public static List<Predmet> dohvatiPredmete() {
		List <Predmet> predmeti = new ArrayList<>();
		Map<Profesor, List<Predmet>> popisProfesora = new HashMap<>();
		List <Profesor> profesori = Datoteke.dohvatiProfesore();
		List<Student> studenti = Datoteke.dohvatiStudente();
		try (BufferedReader in = new BufferedReader(new FileReader(PREDMETI))) {
			System.out.println("Uèitavanje predmeta...");
			String line;
			while ((line = in.readLine()) != null) {
				Profesor nositelj = null;
				long id = Long.parseLong(line);
				String sifra = in.readLine();
				String naziv = in.readLine();
				line = in.readLine();
				Integer ects = Integer.valueOf(line);
				line = in.readLine();
				long idProfesora = Long.parseLong(line);
				for (Profesor trenutni : profesori) {
					if (trenutni.getId() == idProfesora) {
						nositelj = trenutni;
					}
				}
				Predmet predmet = new Predmet(sifra, naziv, ects, nositelj, id);
				line = in.readLine();
				List<Long> idStudenata = Arrays.stream(line.split("\\s")).map(Long::parseLong)
						.collect(Collectors.toList());
				for (long trenutniId : idStudenata) {
					for (Student student : studenti) {
						if (student.getId() == trenutniId) {
							predmet.dodajStudenta(student);
						}
					}
				}
				if (popisProfesora.containsKey(predmet.getNositelj())) {
					List<Predmet> sviPredmeti = popisProfesora.get(predmet.getNositelj());
					sviPredmeti.add(predmet);
					popisProfesora.put(predmet.getNositelj(), sviPredmeti);
				} else {
					List<Predmet> sviPredmeti = new ArrayList<>();
					sviPredmeti.add(predmet);
					popisProfesora.put(predmet.getNositelj(), sviPredmeti);
				}
				predmeti.add(predmet);
			}
		} catch (IOException e) {
			System.err.println(e);
		}
		return predmeti;
	}
	public static List<Ispit> dohvatiIspite() {
		List<Predmet> predmeti =Datoteke.dohvatiPredmete();
		List <Ispit> ispiti = new ArrayList<>();
		List <Student> studenti =Datoteke.dohvatiStudente();
		try (BufferedReader in = new BufferedReader(new FileReader(ISPITI))) {
			System.out.println("Uèitavanje ispita i ocjena...");
			String line;
			while ((line = in.readLine()) != null) {
				long id = Long.parseLong(line);
				line = in.readLine();
				long idPredmeta = Long.parseLong(line);
				line = in.readLine();
				long idStudenta = Long.parseLong(line);
				line = in.readLine();
				Integer ocjena = Integer.valueOf(line);
				line = in.readLine();
				DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd.MM.yyyy.'T'HH:mm");
				LocalDateTime datumIspita = LocalDateTime.parse(line, formatter);
				Predmet predmet = null;
				;
				Student student = null;
				;
				for (Predmet trenutni : predmeti) {
					if (trenutni.getId() == idPredmeta) {
						predmet = trenutni;
					}
				}
				for (Student trenutni : studenti) {
					if (trenutni.getId() == idStudenta) {
						student = trenutni;
					}
				}
				ispiti.add(new Ispit(predmet, student, ocjena, datumIspita, id));
			}
		} catch (IOException e) {
			System.err.println(e);
		}
		return ispiti;
	}
}
