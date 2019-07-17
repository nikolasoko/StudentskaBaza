package hr.java.vjezbe.entitet;

/**
 * Sadr�i metode vezane za odre�ivanje studenta za rekotrovu nagradu
 * 
 * @author Nikola
 *
 */
public interface Diplomski extends Visokoskolska {
	/**
	 * Odre�uje studenta za rekotorvu nagradu
	 * 
	 * @return Vra�a nagra�enog studenta
	 */
	public Student odrediStudentaZaRektorovuNagradu();

}
