package hr.java.vjezbe.iznimke;

/**
 * Klasa koja predstavlja iznimku koja se baca kada postoji vi�e najmladjih
 * studenata.
 * 
 * @author Nikola
 *
 */
public class PostojiViseNajmladjihStudenataException extends RuntimeException {

	private static final long serialVersionUID = -296381963817458610L;

	/**
	 * Konstruktor bez parametara
	 */

	public PostojiViseNajmladjihStudenataException() {
		super("Prona�eno je vi�e najmla�ih studenata sa istim datumom ro�enja");
	}

	/**
	 * Konstruktor koji prima poruku tipa String
	 * 
	 * @param message poruka o pogre�ci
	 */
	public PostojiViseNajmladjihStudenataException(String message) {
		super(message);
	}

	/**
	 * Konstruktor s dva parametra, porukom i uzrokom iznimke
	 * 
	 * @param message poruka o pogre�ci
	 * @param uzrok   uzrok uzro�na iznimka
	 */
	public PostojiViseNajmladjihStudenataException(String message, Throwable uzrok) {
		super(message, uzrok);
	}

	/**
	 * Konstrukotr koji prima uzro�nu iznimku
	 * 
	 * @param uzrok uzro�na iznimka
	 */
	public PostojiViseNajmladjihStudenataException(Throwable uzrok) {
		super(uzrok);
	}
}
