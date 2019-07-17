package hr.java.vjezbe.entitet;

public enum Ocjena {
	Izvrstan(5), VrloDobar(4), Dobar(3), Dovoljan(2), Nedovoljan(1);

	private int ocjena;

	private Ocjena(int ocjena) {
		this.ocjena = ocjena;
	}

	public int getOcjena() {
		return ocjena;
	}

	public void setOcjena(int ocjena) {
		this.ocjena = ocjena;
	}

}
