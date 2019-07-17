package hr.java.vjezbe.entitet;

/**
 * Predstavlja entitet Profesora
 * 
 * @author Nikola
 *
 */
public class Profesor extends Osoba {
	private String sifra;
	private String ime;
	private String prezime;
	private String titula;

	/**
	 * Konstruktor objekta tipa Profesor
	 * 
	 * @param sifra   �ifra profesora
	 * @param ime     ime profesora
	 * @param prezime prezime profesora
	 * @param titula  titula profesora
	 */
	public Profesor(String sifra, String ime, String prezime, String titula, long id) {
		super(ime, prezime,id);
		this.sifra = sifra;
		this.ime = ime;
		this.prezime = prezime;
		this.titula = titula;
	}

	/**
	 * 
	 * @return vra�a �ifru profesora
	 */
	public String getSifra() {
		return sifra;
	}

	/**
	 * 
	 * @param sifra postavlja �ifru profesora
	 */
	public void setSifra(String sifra) {
		this.sifra = sifra;
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
	 * @return vra�a titulu profesora
	 */
	public String getTitula() {
		return titula;
	}

	/**
	 * 
	 * @param titula postavlja titulu profesora
	 */
	public void setTitula(String titula) {
		this.titula = titula;
	}

}
