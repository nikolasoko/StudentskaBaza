package hr.java.vjezbe.entitet;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

public class Sveuciliste<T extends ObrazovnaUstanova> implements Serializable{
	List<T> zbirka;

	public Sveuciliste() {
		super();
		this.zbirka = new ArrayList<>();
	}

	public void dodajObrazovnuUstanovu(T objekt) {
		this.zbirka.add(objekt);
	}
	public T dohvatiObrazovnuUstanovu (int indeks) {
		return this.zbirka.get(indeks);
	}

	public List<T> getZbirka() {
		return zbirka;
	}

	public void setZbirka(List<T> zbirka) {
		this.zbirka = zbirka;
	}

}
