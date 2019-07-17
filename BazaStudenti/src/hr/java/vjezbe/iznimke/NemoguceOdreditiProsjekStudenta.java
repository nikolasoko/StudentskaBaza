package hr.java.vjezbe.iznimke;

/**
 * Klasa koja predstavlja iznimke baèene kad se nemože odrediti prosjek studenta
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
	 * Konstruktor koji prima poruku o grešci
	 * 
	 * @param message poruka o grešci
	 */
	public NemoguceOdreditiProsjekStudenta(String message) {
		super(message);
	}

	/**
	 * Konstruktor koji prima uzroènu iznimku i poruku o grešci
	 * 
	 * @param message poruke o grešci
	 * @param uzrok   uzroèna iznimka
	 */
	public NemoguceOdreditiProsjekStudenta(String message, Throwable uzrok) {
		super(message, uzrok);
	}

	/**
	 * Konstrutkor koji prima uzroènu iznimku kao parametar
	 * 
	 * @param uzrok
	 */
	public NemoguceOdreditiProsjekStudenta(Throwable uzrok) {
		super(uzrok);
	}
}
