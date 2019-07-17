package hr.java.vjezbe.entitet;

import java.time.LocalDateTime;

/**
 * Predstavlja entitet ispitnog odre�enog predmeta
 * 
 * @author Nikola
 *
 */
public class Ispit extends Entitet {
	private Predmet predmet;
	private Student student;
	private Ocjena ocjena;
	private LocalDateTime datumIVrijeme;

	/**
	 * Konstruktor objekta Ispit
	 * 
	 * @param predmet       predmet na ispitnom roku
	 * @param student       student koji pola�e ispit na roku
	 * @param ocjena        ocjena koju je dobio student
	 * @param datumIVrijeme datum i vrijeme ispita
	 */
	public Ispit(Predmet predmet, Student student, Integer ocjena, LocalDateTime datumIVrijeme, long id) {
		super(id);
		this.predmet = predmet;
		this.student = student;
		if (ocjena == null) {
			this.ocjena = null;
		} else {
			for (Ocjena ocjene : Ocjena.values()) {
				if (ocjene.getOcjena() == ocjena) {
					this.ocjena = ocjene;
				}

			}
		}
		this.datumIVrijeme = datumIVrijeme;
	}

	/**
	 * 
	 * @return vra�a predmet
	 */
	public Predmet getPredmet() {
		return predmet;
	}

	/**
	 * 
	 * @param predmet postavlja predmet
	 */
	public void setPredmet(Predmet predmet) {
		this.predmet = predmet;
	}

	/**
	 * 
	 * @return vra�a studenta koji pola�e
	 */
	public Student getStudent() {
		return student;
	}

	/**
	 * 
	 * @param student postavlja studenta
	 */
	public void setStudent(Student student) {
		this.student = student;
	}

	/**
	 * 
	 * @return vra�a ocjenu koju je dobio student
	 */
	public Integer getOcjena() {
		if (this.ocjena==null) return null;
		return ocjena.getOcjena();
	}

	/**
	 * 
	 * @param ocjena postavlja ocjenu
	 */
	public void setOcjena(Ocjena ocjena) {
		this.ocjena = ocjena;
	}

	/**
	 * 
	 * @return vra�a datum i vrijeme odr�avanja ispita
	 */
	public LocalDateTime getDatumIVrijeme() {
		return datumIVrijeme;
	}

	/**
	 * 
	 * @param datumIVrijeme postavlja datum i vrijeme
	 */
	public void setDatumIVrijeme(LocalDateTime datumIVrijeme) {
		this.datumIVrijeme = datumIVrijeme;
	}

}
