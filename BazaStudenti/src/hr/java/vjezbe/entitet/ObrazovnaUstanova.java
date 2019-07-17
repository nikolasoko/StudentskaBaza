package hr.java.vjezbe.entitet;

import java.util.ArrayList;
import java.util.List;

import hr.java.vjezbe.iznimke.NemoguceOdreditiProsjekStudenta;

/**
 * Apastraktna klasa koja predstavlja entitet Obrazovne ustanove
 * 
 * @author Nikola
 *
 */
public abstract class ObrazovnaUstanova extends Entitet{
	public String nazivUstanove;
	public List<Predmet> predmeti = new ArrayList<>();
	public List<Profesor> profesori=new ArrayList<>();
	public List<Student> studenti = new ArrayList<>();
	public List<Ispit> ispiti= new ArrayList<>();

	/**
	 * Konstruktor apstraktne klase Obrazovna Ustanova
	 * 
	 * @param nazivUstanove ime ustanove
	 * @param predmeti      polje predmeta vezanih za ustanovu
	 * @param profesori     polje profesora
	 * @param studenti      polje objekata tipa Student
	 * @param ispiti        polje Ispita
	 */
	public ObrazovnaUstanova(String nazivUstanove, List<Predmet> predmeti, List<Profesor> profesori,
			List<Student> studenti, List<Ispit> ispiti, long id) {
		super(id);
		this.nazivUstanove = nazivUstanove;
		this.predmeti = predmeti;
		this.profesori = profesori;
		this.studenti = studenti;
		this.ispiti = ispiti;
	}

	/**
	 * Odreðuje najuspješnijeg studenta na godini
	 * 
	 * @param godina godina studija za koju se gleda uspješnost studenata
	 * @return vraæa najuspješnijeg studenta
	 * @throws NemoguceOdreditiProsjekStudenta baca iznimku ako je nemoguæe odrediti
	 *                                         prosjek studenta
	 */
	public abstract Student odrediNajuspjesnijegStudentaNaGodini(int godina) throws NemoguceOdreditiProsjekStudenta;

	/**
	 * 
	 * @return Vraæa naziv ustanove
	 */
	public String getnazivUstanove() {
		return nazivUstanove;
	}

	/**
	 * 
	 * @param nazivUstanove Postavlja naziv ustanove
	 */
	public void setnazivUstanove(String nazivUstanove) {
		this.nazivUstanove = nazivUstanove;
	}

	/**
	 * 
	 * @return vraæa polje Predmeta
	 */
	public List<Predmet> getPredmeti() {
		return predmeti;
	}

	/**
	 * 
	 * @param predmeti Postavlja polje predmeta
	 */
	public void setPredmeti(List<Predmet> predmeti) {
		this.predmeti = predmeti;
	}

	/**
	 * 
	 * @return Vraæa polje profesora
	 */
	public List<Profesor> getProfesori() {
		return profesori;
	}

	/**
	 * 
	 * @param profesori Postavlja polje profesora
	 */
	public void setProfesori(List<Profesor> profesori) {
		this.profesori = profesori;
	}

	/**
	 * 
	 * @return Vraæa polje svih studenata
	 */
	public List<Student> getStudenti() {
		return studenti;
	}

	/**
	 * 
	 * @param studenti Postavlja polje studenata
	 */
	public void setStudenti(List<Student> studenti) {
		this.studenti = studenti;
	}

	/**
	 * 
	 * @return Vraæa polje Ispita
	 */
	public List<Ispit> getIspiti() {
		return ispiti;
	}

	/**
	 * 
	 * @param ispiti Postavlja polje ispta
	 */
	public void setIspiti(List<Ispit> ispiti) {
		this.ispiti = ispiti;
	}
	public Integer BrojStudenata() {
		return this.studenti.size();
	}

}
