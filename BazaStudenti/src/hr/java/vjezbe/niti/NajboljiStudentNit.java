package hr.java.vjezbe.niti;

import java.util.ArrayList;
import java.util.List;
import java.util.OptionalDouble;

import hr.java.vjezbe.baza.BazaPodataka;
import hr.java.vjezbe.entitet.Ispit;
import hr.java.vjezbe.entitet.Student;

public class NajboljiStudentNit implements Runnable {
	
	private String ime;

	@Override
	public void run() {
		// TODO Auto-generated method stub

	}
	public NajboljiStudentNit() {
		List<Student> studenti= new ArrayList<Student>();
		Student prazni = new Student(null, null, null, null, -1);
		studenti = BazaPodataka.dohvatiStudentePremaKriterijima(prazni);
		double maxProsjek=0;
		String najbolji = "";
		for (Student temp:studenti) {
			List<Ispit> listaIspitaZaStudenta = new ArrayList<Ispit>();
			Ispit ispitStudenta = new Ispit(null, temp, null, null, -1);
			listaIspitaZaStudenta = BazaPodataka.dohvatiIspitePremaKriterijima(ispitStudenta);
			OptionalDouble prosjek = listaIspitaZaStudenta.stream().mapToDouble(i ->
			i.getOcjena()).average();
			if(prosjek.isPresent()) {
				if (prosjek.getAsDouble() > maxProsjek) {
					najbolji=temp.getIme() + " " + temp.getPrezime();
					maxProsjek=prosjek.getAsDouble();
				}
			}

		}
		this.ime=najbolji + "(" + maxProsjek + ")";
	}
	public String getIme() {
		return ime;
	}
	public void setIme(String ime) {
		this.ime = ime;
	}

}
