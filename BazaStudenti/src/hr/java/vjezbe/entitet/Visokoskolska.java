package hr.java.vjezbe.entitet;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.*;
import hr.java.vjezbe.iznimke.NemoguceOdreditiProsjekStudenta;

/**
 * Sadr�i metode koje ra�unaju kona�ne ocjene i filtiraju Ispitne rokove
 * 
 * @author Nikola
 *
 */
public interface Visokoskolska {
	/**
	 * Izra�unava kona�nu ocjenu studenta ukulju�uju�i ocjene s ispita i zavr�nog
	 * rada
	 * 
	 * @param ispiti                     polje Ispita koje je student polo�io
	 * @param ocjenaPismenogZavrsnogRada ocjena iz pismenog dijela zavr�nog rada
	 * @param ocjenaObraneZavrsnogRada   ocjena usmenog dijela zavr�nog rada
	 * @return vra�a kona�nu ocjenu tipa BigDecimal izra�unatoj po formuli unutar
	 *         metode
	 * @throws NemoguceOdreditiProsjekStudenta baca iznimku u slu�aju da student ima
	 *                                         ispit ocjenjen s ocjenom
	 *                                         nedovoljan(1)
	 */
	public BigDecimal izracunajKonacnuOcjenuStudijaZaStudenta(List<Ispit> ispiti, int ocjenaPismenogZavrsnogRada,
			int ocjenaObraneZavrsnogRada) throws NemoguceOdreditiProsjekStudenta;

	/**
	 * Odre�uje prosjek ispita koje je student polo�io
	 * 
	 * @param ispiti polje Ispita koje sadr�i one ispite koje je Student polagao na
	 *               ispitnom roku
	 * @return vra�a Studentov prosjek na polo�enim ispitima
	 * @throws NemoguceOdreditiProsjekStudenta baca iznimku ako student ima ocjenu nedovoljan(1) iz bilokojeg ispita
	 */
	default public BigDecimal odrediProsjekOcjenaNaIspitima(List<Ispit> ispiti) throws NemoguceOdreditiProsjekStudenta {
		int suma = 0;
		int kolicina = 0;
		for (Ispit ispit : ispiti) {
			if (ispit.getOcjena() > 1) {
				kolicina++;
				suma += ispit.getOcjena();
			}
			if (ispit.getOcjena() == 1) {
				throw new NemoguceOdreditiProsjekStudenta();
			}
		}
		BigDecimal BigDecSuma = new BigDecimal(suma);
		BigDecimal BigDecKolicina = new BigDecimal(kolicina);
		BigDecimal prosjek = BigDecSuma.divide(BigDecKolicina);
		return prosjek;
	}

	/**
	 * Filtrira sve ispite i vra�a natrag samo one koje je student polo�io
	 * 
	 * @param SviIspiti polje koje sadr�i sve ispite koje student polagao
	 * @return vra�a polje ispita koji su polo�eni
	 */
	private List<Ispit> filtrirajPolozeneIspite(List<Ispit> SviIspiti) {
		List<Ispit> PolozeniIspiti = new ArrayList<>();
		for (Ispit ispit : SviIspiti) {
			if (ispit.getOcjena() > 1) {
				PolozeniIspiti.add(ispit);
			}
		}
		return PolozeniIspiti;
	}

	/**
	 * Filtrira sve ispite i odabire samo one na koje je iza�ao doti�ni student
	 * 
	 * @param SviIspiti polje svih ispita na odre�enom u�ili�tu
	 * @param student   Student za kojeg se filtriraju ispiti
	 * @return vra�a polje Ispita filtriranih po studentu
	 */
	default public List<Ispit> filtrirajIspitePoStudentu(List<Ispit> SviIspiti, Student student) {
		List<Ispit> PristupljeniIspiti = new ArrayList<>();
		/*for (Ispit ispit : SviIspiti) {
			if (ispit.getStudent().equals(student)) {
				PristupljeniIspiti.add(ispit);
			}
		}
		*/
		PristupljeniIspiti=SviIspiti.stream().filter(x->student.equals(x.getStudent())).collect(Collectors.toList());
		return PristupljeniIspiti;
	}
}
