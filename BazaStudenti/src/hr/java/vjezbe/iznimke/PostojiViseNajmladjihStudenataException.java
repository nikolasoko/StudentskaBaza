package hr.java.vjezbe.iznimke;

/**
 * Klasa koja predstavlja iznimku koja se baca kada postoji više najmladjih
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
		super("Pronaðeno je više najmlaðih studenata sa istim datumom roðenja");
	}

	/**
	 * Konstruktor koji prima poruku tipa String
	 * 
	 * @param message poruka o pogrešci
	 */
	public PostojiViseNajmladjihStudenataException(String message) {
		super(message);
	}

	/**
	 * Konstruktor s dva parametra, porukom i uzrokom iznimke
	 * 
	 * @param message poruka o pogrešci
	 * @param uzrok   uzrok uzroèna iznimka
	 */
	public PostojiViseNajmladjihStudenataException(String message, Throwable uzrok) {
		super(message, uzrok);
	}

	/**
	 * Konstrukotr koji prima uzroènu iznimku
	 * 
	 * @param uzrok uzroèna iznimka
	 */
	public PostojiViseNajmladjihStudenataException(Throwable uzrok) {
		super(uzrok);
	}
}
