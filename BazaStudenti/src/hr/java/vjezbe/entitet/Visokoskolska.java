package hr.java.vjezbe.entitet;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.*;
import hr.java.vjezbe.iznimke.NemoguceOdreditiProsjekStudenta;

/**
 * Sadrži metode koje raèunaju konaène ocjene i filtiraju Ispitne rokove
 * 
 * @author Nikola
 *
 */
public interface Visokoskolska {
	/**
	 * Izraèunava konaènu ocjenu studenta ukuljuèujuæi ocjene s ispita i završnog
	 * rada
	 * 
	 * @param ispiti                     polje Ispita koje je student položio
	 * @param ocjenaPismenogZavrsnogRada ocjena iz pismenog dijela završnog rada
	 * @param ocjenaObraneZavrsnogRada   ocjena usmenog dijela završnog rada
	 * @return vraæa konaènu ocjenu tipa BigDecimal izraèunatoj po formuli unutar
	 *         metode
	 * @throws NemoguceOdreditiProsjekStudenta baca iznimku u sluèaju da student ima
	 *                                         ispit ocjenjen s ocjenom
	 *                                         nedovoljan(1)
	 */
	public BigDecimal izracunajKonacnuOcjenuStudijaZaStudenta(List<Ispit> ispiti, int ocjenaPismenogZavrsnogRada,
			int ocjenaObraneZavrsnogRada) throws NemoguceOdreditiProsjekStudenta;

	/**
	 * Odreðuje prosjek ispita koje je student položio
	 * 
	 * @param ispiti polje Ispita koje sadrži one ispite koje je Student polagao na
	 *               ispitnom roku
	 * @return vraæa Studentov prosjek na položenim ispitima
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
	 * Filtrira sve ispite i vraæa natrag samo one koje je student položio
	 * 
	 * @param SviIspiti polje koje sadrži sve ispite koje student polagao
	 * @return vraæa polje ispita koji su položeni
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
	 * Filtrira sve ispite i odabire samo one na koje je izašao dotièni student
	 * 
	 * @param SviIspiti polje svih ispita na odreðenom uèilištu
	 * @param student   Student za kojeg se filtriraju ispiti
	 * @return vraæa polje Ispita filtriranih po studentu
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
