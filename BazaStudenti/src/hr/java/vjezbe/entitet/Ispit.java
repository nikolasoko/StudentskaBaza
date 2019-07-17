package hr.java.vjezbe.entitet;

import java.time.LocalDateTime;

/**
 * Predstavlja entitet ispitnog odreðenog predmeta
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
	 * @param student       student koji polaže ispit na roku
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
	 * @return vraæa predmet
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
	 * @return vraæa studenta koji polaže
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
	 * @return vraæa ocjenu koju je dobio student
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
	 * @return vraæa datum i vrijeme održavanja ispita
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
