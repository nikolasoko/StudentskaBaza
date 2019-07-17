package hr.java.vjezbe.entitet;

import java.time.LocalDate;

/**
 * Predstavlja entitet Studenta
 * 
 * @author Nikola
 *
 */
public class Student extends Osoba {
	private String ime;
	private String prezime;
	private String jmbag;
	private LocalDate datumrodjenja;

	/**
	 * Konstruktor Studenta
	 * 
	 * @param ime           ime studenta
	 * @param prezime       prezime studenta
	 * @param jmbag         jmbag studenta
	 * @param datumrodjenja datum roðenja studenta
	 */
	public Student(String ime, String prezime, String jmbag, LocalDate datumrodjenja,long id) {
		super(ime, prezime,id);
		this.ime = ime;
		this.prezime = prezime;
		this.jmbag = jmbag;
		this.datumrodjenja = datumrodjenja;
	}

	public String getIme() {
		return ime;
	}

	public void setIme(String ime) {
		this.ime = ime;
	}

	public String getPrezime() {
		return prezime;
	}

	public void setPrezime(String prezime) {
		this.prezime = prezime;
	}

	/**
	 * 
	 * @return vraæa JMBAG studenta
	 */
	public String getJmbag() {
		return jmbag;
	}

	/**
	 * 
	 * @param jmbag postavlja jmbag studenta
	 */
	public void setJmbag(String jmbag) {
		this.jmbag = jmbag;
	}

	/**
	 * 
	 * @return vraæa datum roðenja
	 */
	public LocalDate getDatumrodjenja() {
		return datumrodjenja;
	}

	/**
	 * 
	 * @param datumrodjenja postavlja datum roðenja
	 */
	public void setDatumrodjenja(LocalDate datumrodjenja) {
		this.datumrodjenja = datumrodjenja;
	}
}
