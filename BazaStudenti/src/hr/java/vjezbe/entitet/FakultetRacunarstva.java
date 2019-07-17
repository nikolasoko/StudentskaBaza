package hr.java.vjezbe.entitet;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import hr.java.vjezbe.iznimke.NemoguceOdreditiProsjekStudenta;
import hr.java.vjezbe.iznimke.PostojiViseNajmladjihStudenataException;

/**
 * Predstavlja objekt Fakultet raèunarstva kao obrazovnu ustanovu
 * 
 * @author Nikola
 *
 */
public class FakultetRacunarstva extends ObrazovnaUstanova implements Diplomski, Serializable {

	private static final Logger logger = LoggerFactory.getLogger(FakultetRacunarstva.class);

	/**
	 * Konstruktor
	 * 
	 * @param nazivUstanove ime ustanove
	 * @param predmeti      polje predmeta
	 * @param profesori     polje profesora
	 * @param studenti      polje studenata
	 * @param ispiti        polje ispitnih rokova
	 */
	public FakultetRacunarstva(String nazivUstanove, List<Predmet> predmeti, List<Profesor> profesori, List<Student> studenti,
			List<Ispit> ispiti,long id) {
		super(nazivUstanove, predmeti, profesori, studenti, ispiti,id);
	}

	@Override
	public BigDecimal izracunajKonacnuOcjenuStudijaZaStudenta(List<Ispit> ispiti, int ocjenaPismenogDiplomskog,
			int ocjenaObraneDiplomskogRada) {
		BigDecimal konacnaOcjena;
		BigDecimal prosjek;
		try {
			prosjek = odrediProsjekOcjenaNaIspitima(ispiti);
		} catch (NemoguceOdreditiProsjekStudenta e) {
			// TODO Auto-generated catch block
			logger.info(e.getMessage(), e);
			prosjek = new BigDecimal(1);
			konacnaOcjena = new BigDecimal(1);
			return konacnaOcjena;
		}
		BigDecimal ocjene = new BigDecimal(ocjenaObraneDiplomskogRada).add(new BigDecimal(ocjenaPismenogDiplomskog));
		konacnaOcjena = prosjek.multiply(new BigDecimal(3)).add(ocjene).divide(new BigDecimal(5));
		return konacnaOcjena;
	}

	@Override
	public Student odrediStudentaZaRektorovuNagradu() throws PostojiViseNajmladjihStudenataException {
		Student najbolji = studenti.get(0);
		BigDecimal maxProsjek = new BigDecimal(0);
		try {
			Student najmladji = odrediNajmladjegStudenta();
		} catch (PostojiViseNajmladjihStudenataException e) {
			System.out.println("Program završava s izvoðenjem.");
			logger.info(e.getMessage(), e);
			System.out.println(e.getMessage());
			throw new PostojiViseNajmladjihStudenataException();
		}
		for (Student student : studenti) {
			List<Ispit> polozeniIspiti = filtrirajIspitePoStudentu(ispiti, student);
			BigDecimal trenutniProsjek = new BigDecimal(1);
			try {
				trenutniProsjek = odrediProsjekOcjenaNaIspitima(polozeniIspiti);
			} catch (NemoguceOdreditiProsjekStudenta e) {
				// TODO Auto-generated catch block
				trenutniProsjek = new BigDecimal(1);
			}
			if (trenutniProsjek.compareTo(maxProsjek) == 0) {
				if (najbolji.getDatumrodjenja().compareTo(student.getDatumrodjenja()) < 0) {
					maxProsjek = trenutniProsjek;
					najbolji = student;
				}
			}
			if (trenutniProsjek.compareTo(maxProsjek) > 0) {
				maxProsjek = trenutniProsjek;
				najbolji = student;
			}

		}
		return najbolji;
	}

	@Override
	public Student odrediNajuspjesnijegStudentaNaGodini(int godina) {
		int maxIzvrstnihOcjena = 0;
		Student najbolji = null;
		for (Student trenutniStudent : studenti) {
			int brojIzvrstnih = 0;
			List<Ispit> polozeniIspiti = filtrirajIspitePoStudentu(ispiti, trenutniStudent);
			for (Ispit ispit : polozeniIspiti) {
				if (ispit.getOcjena() == Ocjena.Izvrstan.getOcjena() && ispit.getDatumIVrijeme().getYear() == godina) {
					brojIzvrstnih++;
				}
			}
			if (brojIzvrstnih > maxIzvrstnihOcjena) {
				najbolji = trenutniStudent;
				maxIzvrstnihOcjena = brojIzvrstnih;
			}
		}
		return najbolji;
	}

	/**
	 * Odreðuje najmlaðeg studenta meðu studentima
	 * 
	 * @return Vraæa najmlaðeg studenta
	 * @throws PostojiViseNajmladjihStudenataException baca iznimku ako postoji više
	 *                                                 od jednog najmlaðeg studenta
	 *                                                 s istim datumom roðenja
	 */
	public Student odrediNajmladjegStudenta() throws PostojiViseNajmladjihStudenataException {
		Student najmladji = studenti.get(0);
		for (Student student : studenti) {
			if (najmladji.getDatumrodjenja().compareTo(student.getDatumrodjenja()) < 0) {
				najmladji = student;
			}
		}
		for (Student student : studenti) {
			if ((najmladji.getDatumrodjenja().compareTo(student.getDatumrodjenja()) == 0)
					&& (najmladji.equals(student) == false)) {
				throw new PostojiViseNajmladjihStudenataException(
						"Pronaðeno je više najmlaðih studenata s istim datumom roðenja, a to su " + najmladji.getIme()
								+ " " + najmladji.getPrezime() + " i " + student.getIme() + " " + student.getPrezime());
			}
		}
		return najmladji;
	}

}
