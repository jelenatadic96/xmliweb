package com.megatravel.agent.model;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;

@Entity
public class SpojAgentSmestaj {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne
	private SmestajnaJedinica smestajnaJedinica;
    
    @ManyToOne
    private Agent agent;
    
    public SpojAgentSmestaj() { }

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public Agent getAgent() {
		return agent;
	}

	public void setAgent(Agent agent) {
		this.agent = agent;
	}

	public SmestajnaJedinica getSmestajnaJedinica() {
		return smestajnaJedinica;
	}

	public void setSmestajnaJedinica(SmestajnaJedinica smestajnaJedinica) {
		this.smestajnaJedinica = smestajnaJedinica;
	}
    
}
