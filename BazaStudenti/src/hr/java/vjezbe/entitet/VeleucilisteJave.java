package hr.java.vjezbe.entitet;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import hr.java.vjezbe.iznimke.NemoguceOdreditiProsjekStudenta;

/**
 * Predstavlja entitet veleuèilišta koje je odreðeno nazivom, poljem predmeta,
 * profesora, studenata i ispita.
 * 
 * @author Nikola
 *
 */
public class VeleucilisteJave extends ObrazovnaUstanova implements Visokoskolska, Serializable {
	private static final Logger logger = LoggerFactory.getLogger(VeleucilisteJave.class);

	/**
	 * Konstruktor za klasu VeleucilisteJave
	 * 
	 * @param nazivUstanove ime Veleuèilišta
	 * @param predmeti      polje Predmeta
	 * @param profesori     polje Profesora
	 * @param studenti      polje Studenata
	 * @param ispiti        polje Ispita
	 */
	public VeleucilisteJave(String nazivUstanove, List<Predmet> predmeti, List<Profesor> profesori, List<Student> studenti,
			List<Ispit> ispiti,long id) {
		super(nazivUstanove, predmeti, profesori, studenti, ispiti,id);
	}

	@Override
	public BigDecimal izracunajKonacnuOcjenuStudijaZaStudenta(List<Ispit> ispiti, int ocjenaPismenogZavrsnogRada,
			int ocjenaObraneZavrsnogRada) {
		BigDecimal konacnaOcjena;
		BigDecimal prosjek = new BigDecimal(1);
		try {
			prosjek = odrediProsjekOcjenaNaIspitima(ispiti);
		} catch (NemoguceOdreditiProsjekStudenta e) {
			// TODO Auto-generated catch block
			logger.info(e.getMessage(), e);
			prosjek = new BigDecimal(1);
			konacnaOcjena = new BigDecimal(1);
			return konacnaOcjena;
		}
		BigDecimal ocjene = new BigDecimal(ocjenaObraneZavrsnogRada).add(new BigDecimal(ocjenaPismenogZavrsnogRada));
		konacnaOcjena = prosjek.multiply(new BigDecimal(2)).add(ocjene).divide(new BigDecimal(4));
		return konacnaOcjena;
	}

	@Override
	public Student odrediNajuspjesnijegStudentaNaGodini(int godina) {
		BigDecimal maxProsjek = new BigDecimal(0);
		Student najboljiStudent = null;
		for (Student student : studenti) {
			List<Ispit> pristupljeniIspiti = filtrirajIspitePoStudentu(ispiti, student);
			List<Ispit> ispitiUGodini = new ArrayList<>();
			for (Ispit ispit : pristupljeniIspiti) {
				if (ispit.getDatumIVrijeme().getYear() == godina) {
					ispitiUGodini.add(ispit);
				}
			}
			BigDecimal trenutniProsjek = new BigDecimal(1);
			try {
				trenutniProsjek = odrediProsjekOcjenaNaIspitima(ispitiUGodini);
			} catch (NemoguceOdreditiProsjekStudenta e) {
				// TODO Auto-generated catch block
				logger.info(e.getMessage(), e);
				trenutniProsjek = new BigDecimal(1);
			}
			if (trenutniProsjek.compareTo(maxProsjek) >= 0) {
				najboljiStudent = student;
				maxProsjek = trenutniProsjek;
			}
		}
		return najboljiStudent;
	}

}
