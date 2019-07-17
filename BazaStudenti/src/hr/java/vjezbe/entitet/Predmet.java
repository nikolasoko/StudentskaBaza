package hr.java.vjezbe.entitet;

import java.util.HashSet;
import java.util.Set;

/**
 * Predstavlja entitet predmeta na obrazovnoj ustanovi
 * 
 * @author Nikola
 *
 */
public class Predmet extends Entitet{
	private String sifra;
	private String naziv;
	private Integer brojEctsBodova;
	private Profesor nositelj;
	private Set<Student> upisaniStudenti = new HashSet<>();

	/**
	 * Konstruktor Predmeta koji inicijalizira podatke o predmetu
	 * 
	 * @param sifra                 šifra predmeta
	 * @param naziv                 naziv predmeta
	 * @param brojEctsBodova        broj ECTSA bodova koje predmet nosi
	 * @param nositelj              Profesor nositelj predmeta
	 */
	public Predmet(String sifra, String naziv, Integer brojEctsBodova, Profesor nositelj, long id) {
		super(id);
		this.sifra = sifra;
		this.naziv = naziv;
		this.brojEctsBodova = brojEctsBodova;
		this.nositelj = nositelj;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((brojEctsBodova == null) ? 0 : brojEctsBodova.hashCode());
		result = prime * result + ((naziv == null) ? 0 : naziv.hashCode());
		result = prime * result + ((nositelj == null) ? 0 : nositelj.hashCode());
		result = prime * result + ((sifra == null) ? 0 : sifra.hashCode());
		result = prime * result + ((upisaniStudenti == null) ? 0 : upisaniStudenti.hashCode());
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Predmet other = (Predmet) obj;
		if (brojEctsBodova == null) {
			if (other.brojEctsBodova != null)
				return false;
		} else if (!brojEctsBodova.equals(other.brojEctsBodova))
			return false;
		if (naziv == null) {
			if (other.naziv != null)
				return false;
		} else if (!naziv.equals(other.naziv))
			return false;
		if (nositelj == null) {
			if (other.nositelj != null)
				return false;
		} else if (!nositelj.equals(other.nositelj))
			return false;
		if (sifra == null) {
			if (other.sifra != null)
				return false;
		} else if (!sifra.equals(other.sifra))
			return false;
		if (upisaniStudenti == null) {
			if (other.upisaniStudenti != null)
				return false;
		} else if (!upisaniStudenti.equals(other.upisaniStudenti))
			return false;
		return true;
	}

	/**
	 * 
	 * @return Vraæa šifru predmeta
	 */
	public String getSifra() {
		return sifra;
	}

	/**
	 * 
	 * @param sifra Postavlja šifru predmeta
	 */
	public void setSifra(String sifra) {
		this.sifra = sifra;
	}

	/**
	 * 
	 * @return Vraæa naziv predmeta
	 */
	public String getNaziv() {
		return naziv;
	}

	/**
	 * 
	 * @param naziv Postavlja naziv predmeta
	 */
	public void setNaziv(String naziv) {
		this.naziv = naziv;
	}

	/**
	 * 
	 * @return Vraæa broj ECTS bodova
	 */
	public Integer getBrojEctsBodova() {
		return brojEctsBodova;
	}

	/**
	 * 
	 * @param brojEctsBodova Postavlja broj ects bodova
	 */
	public void setBrojEctsBodova(Integer brojEctsBodova) {
		this.brojEctsBodova = brojEctsBodova;
	}

	/**
	 * 
	 * @return Vraæa profesora nositelja
	 */
	public Profesor getNositelj() {
		return nositelj;
	}

	/**
	 * 
	 * @param nositelj Postavlja profesora nositelja
	 */
	public void setNositelj(Profesor nositelj) {
		this.nositelj = nositelj;
	}

	/**
	 * 
	 * @return vraæa Polje upisanih studenata
	 */
	public Set<Student> getUpisaniStudenti() {
		return upisaniStudenti;
	}

	/**
	 * 
	 * @param upisaniStudenti Postavlja polje upisanih studenata
	 */
	public void setUpisaniStudenti(Set<Student> upisaniStudenti) {
		this.upisaniStudenti = upisaniStudenti;

	}

	/**
	 * 
	 * @param student Dodaje studenta u polje studenata
	 */
	public void dodajStudenta(Student student) {
		this.upisaniStudenti.add(student);
	}
	public int getbrojUpisanih() {
		return upisaniStudenti.size();
	}
}
