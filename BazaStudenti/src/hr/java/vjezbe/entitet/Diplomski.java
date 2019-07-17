package hr.java.vjezbe.entitet;

/**
 * Sadrži metode vezane za odreðivanje studenta za rekotrovu nagradu
 * 
 * @author Nikola
 *
 */
public interface Diplomski extends Visokoskolska {
	/**
	 * Odreðuje studenta za rekotorvu nagradu
	 * 
	 * @return Vraæa nagraðenog studenta
	 */
	public Student odrediStudentaZaRektorovuNagradu();

}
