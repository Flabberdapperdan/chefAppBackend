package config;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;


@Entity
public class Ingredient {

	// primary key	AutoIncrement
	@Id
	@GeneratedValue( strategy = GenerationType.AUTO)
	private long id;
	private String naam;
	private int kosten;
	public long getId() {
		return id;
	}
	public void setId(long id) {
		this.id = id;
	}
	public String getNaam() {
		return naam;
	}
	public void setNaam(String naam) {
		this.naam = naam;
	}
	public int getKosten() {
		return kosten;
	}
	public void setKosten(int kosten) {
		this.kosten = kosten;
	}
	

}



