package hr.java.vjezbe.iznimke;

/**
 * Klasa koja predstavlja iznimke ba�ene kad se nemo�e odrediti prosjek studenta
 * 
 * @author Nikola
 *
 */
public class NemoguceOdreditiProsjekStudenta extends Exception {

	private static final long serialVersionUID = -5830169482744301927L;

	/**
	 * Konstruktor bez parametra
	 */
	public NemoguceOdreditiProsjekStudenta() {
		super("Nemoguce odrediti prosjek studenta!");
	}

	/**
	 * Konstruktor koji prima poruku o gre�ci
	 * 
	 * @param message poruka o gre�ci
	 */
	public NemoguceOdreditiProsjekStudenta(String message) {
		super(message);
	}

	/**
	 * Konstruktor koji prima uzro�nu iznimku i poruku o gre�ci
	 * 
	 * @param message poruke o gre�ci
	 * @param uzrok   uzro�na iznimka
	 */
	public NemoguceOdreditiProsjekStudenta(String message, Throwable uzrok) {
		super(message, uzrok);
	}

	/**
	 * Konstrutkor koji prima uzro�nu iznimku kao parametar
	 * 
	 * @param uzrok
	 */
	public NemoguceOdreditiProsjekStudenta(Throwable uzrok) {
		super(uzrok);
	}
}
