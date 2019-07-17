package hr.java.vjezbe.entitet;

/**
 * Predstavlja entitet osobe koja je definirana imenom i prezimenom
 * 
 * @author Nikola
 *
 */
public abstract class Osoba extends Entitet{
	private String ime;
	private String prezime;

	/**
	 * Konstruktor klase Osoba
	 * 
	 * @param ime     ime osobe
	 * @param prezime prezime osobe
	 */
	public Osoba(String ime, String prezime, long id) {
		super(id);
		this.ime = ime;
		this.prezime = prezime;
	}

	/**
	 * 
	 * @return Vraæa Ime osobe
	 */
	public String getIme() {
		return ime;
	}

	/**
	 * 
	 * @param ime Postavlja ime osobe
	 */
	public void setIme(String ime) {
		this.ime = ime;
	}

	/**
	 * 
	 * @return vraæa Prezime Osobe
	 */
	public String getPrezime() {
		return prezime;
	}

	/**
	 * 
	 * @param prezime Postavlja prezime osobe
	 */
	public void setPrezime(String prezime) {
		this.prezime = prezime;
	}
}
