package com.megatravel.agent.model;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;

@Entity
public class SpojUslugaJedinica {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    
    @ManyToOne
    private SmestajnaJedinica smestajnaJedinica;
    
    @ManyToOne
    private Usluga usluga;
	
    public SpojUslugaJedinica() { }

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public SmestajnaJedinica getSmestajnaJedinica() {
		return smestajnaJedinica;
	}

	public void setSmestajnaJedinica(SmestajnaJedinica smestajnaJedinica) {
		this.smestajnaJedinica = smestajnaJedinica;
	}

	public Usluga getUsluga() {
		return usluga;
	}

	public void setUsluga(Usluga usluga) {
		this.usluga = usluga;
	}
    
}
