package com.megatravel.korisniciservice.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import javax.persistence.UniqueConstraint;
import javax.validation.constraints.Min;

@Entity
@Table(
	    name = "spoj_agent_smestaj", 
	    uniqueConstraints = {@UniqueConstraint(columnNames={"smestaj_id", "agent_id"})}
)
public class SpojAgentSmestaj {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @Column(name = "smestaj_id", nullable = false, updatable = false)
    @Min(0)
	private Long smestajId;
    
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
    
}
