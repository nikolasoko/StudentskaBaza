package hr.java.vjezbe.iznimke;

public class BazaPodatakaException extends RuntimeException {

	/**
	 * 
	 */
	private static final long serialVersionUID = -7854180259430320327L;

	public BazaPodatakaException(String poruka, Throwable uzrok) {
		super(poruka, uzrok);
	}

}
