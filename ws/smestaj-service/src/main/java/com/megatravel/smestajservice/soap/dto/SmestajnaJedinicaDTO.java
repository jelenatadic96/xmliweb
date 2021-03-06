//
// This file was generated by the JavaTM Architecture for XML Binding(JAXB) Reference Implementation, v2.2.7 
// See <a href="http://java.sun.com/xml/jaxb">http://java.sun.com/xml/jaxb</a> 
// Any modifications to this file will be lost upon recompilation of the source schema. 
// Generated on: 2019.08.19 at 11:06:20 AM CEST 
//


package com.megatravel.smestajservice.soap.dto;

import java.util.ArrayList;
import java.util.List;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlType;

import com.megatravel.smestajservice.model.SmestajnaJedinica;


/**
 * <p>Java class for SmestajnaJedinicaDTO complex type.
 * 
 * <p>The following schema fragment specifies the expected content contained within this class.
 * 
 * <pre>
 * &lt;complexType name="SmestajnaJedinicaDTO">
 *   &lt;complexContent>
 *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
 *       &lt;sequence>
 *         &lt;element name="id" type="{http://www.w3.org/2001/XMLSchema}long"/>
 *         &lt;element name="opis" type="{http://www.w3.org/2001/XMLSchema}string"/>
 *         &lt;element name="kapacitet" type="{http://www.w3.org/2001/XMLSchema}int"/>
 *         &lt;element name="brojDanaZaOtkazivanje" type="{http://www.w3.org/2001/XMLSchema}int"/>
 *         &lt;element name="ocena" type="{http://www.w3.org/2001/XMLSchema}double"/>
 *         &lt;element name="adresa" type="{http://www.w3.org/2001/XMLSchema}long"/>
 *         &lt;element name="tip" type="{http://www.w3.org/2001/XMLSchema}long"/>
 *         &lt;element name="usluge" type="{http://www.megatravel.com/smestajservice/soap/dto}UslugaDTO" maxOccurs="unbounded" minOccurs="0"/>
 *         &lt;element name="cenovnici" type="{http://www.megatravel.com/smestajservice/soap/dto}CenovnikDTO" maxOccurs="unbounded" minOccurs="0"/>
 *       &lt;/sequence>
 *     &lt;/restriction>
 *   &lt;/complexContent>
 * &lt;/complexType>
 * </pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "SmestajnaJedinicaDTO", propOrder = {
    "id",
    "opis",
    "kapacitet",
    "brojDanaZaOtkazivanje",
    "ocena",
    "adresa",
    "tip",
    "usluge",
    "cenovnici"
})
public class SmestajnaJedinicaDTO {

    protected long id;
    @XmlElement(required = true)
    protected String opis;
    protected int kapacitet;
    protected int brojDanaZaOtkazivanje;
    protected double ocena;
    protected long adresa;
    protected long tip;
    protected List<UslugaDTO> usluge;
    protected List<CenovnikDTO> cenovnici;

    public SmestajnaJedinicaDTO() { }
    
    public SmestajnaJedinicaDTO(SmestajnaJedinica smestajnaJedinica) {
    	this.id = smestajnaJedinica.getId();
    	this.opis = smestajnaJedinica.getOpis();
    	this.kapacitet = smestajnaJedinica.getKapacitet();
    	this.brojDanaZaOtkazivanje = smestajnaJedinica.getBrojDanaZaOtkazivanje();
    	this.ocena = smestajnaJedinica.getOcena();
    	if(smestajnaJedinica.getAdresa() != null) this.adresa = smestajnaJedinica.getAdresa().getId();
    	this.cenovnici = CenovnikDTO.transformisi(smestajnaJedinica.getCenovnici());
    	this.tip = smestajnaJedinica.getTip().getId();
    }
    
    /**
     * Gets the value of the id property.
     * 
     */
    public long getId() {
        return id;
    }

    /**
     * Sets the value of the id property.
     * 
     */
    public void setId(long value) {
        this.id = value;
    }

    /**
     * Gets the value of the opis property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getOpis() {
        return opis;
    }

    /**
     * Sets the value of the opis property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setOpis(String value) {
        this.opis = value;
    }

    /**
     * Gets the value of the kapacitet property.
     * 
     */
    public int getKapacitet() {
        return kapacitet;
    }

    /**
     * Sets the value of the kapacitet property.
     * 
     */
    public void setKapacitet(int value) {
        this.kapacitet = value;
    }

    /**
     * Gets the value of the brojDanaZaOtkazivanje property.
     * 
     */
    public int getBrojDanaZaOtkazivanje() {
        return brojDanaZaOtkazivanje;
    }

    /**
     * Sets the value of the brojDanaZaOtkazivanje property.
     * 
     */
    public void setBrojDanaZaOtkazivanje(int value) {
        this.brojDanaZaOtkazivanje = value;
    }

    /**
     * Gets the value of the ocena property.
     * 
     */
    public double getOcena() {
        return ocena;
    }

    /**
     * Sets the value of the ocena property.
     * 
     */
    public void setOcena(double value) {
        this.ocena = value;
    }

    /**
     * Gets the value of the adresa property.
     * 
     * @return
     *     possible object is
     *     {@link AdresaDTO }
     *     
     */
    public long getAdresa() {
        return adresa;
    }

    /**
     * Sets the value of the adresa property.
     *  
     */
    public void setAdresa(long value) {
        this.adresa = value;
    }

    /**
     * Gets the value of the tip property.
     * 
     *     
     */
    public long getTip() {
        return tip;
    }

    /**
     * Sets the value of the tip property.
     *   
     */
    public void setTip(long value) {
        this.tip = value;
    }

    /**
     * Gets the value of the usluge property.
     * 
     * <p>
     * This accessor method returns a reference to the live list,
     * not a snapshot. Therefore any modification you make to the
     * returned list will be present inside the JAXB object.
     * This is why there is not a <CODE>set</CODE> method for the usluge property.
     * 
     * <p>
     * For example, to add a new item, do as follows:
     * <pre>
     *    getUsluge().add(newItem);
     * </pre>
     * 
     * 
     * <p>
     * Objects of the following type(s) are allowed in the list
     * {@link UslugaDTO }
     * 
     * 
     */
    public List<UslugaDTO> getUsluge() {
        if (usluge == null) {
            usluge = new ArrayList<UslugaDTO>();
        }
        return this.usluge;
    }

    /**
     * Gets the value of the cenovnici property.
     * 
     * <p>
     * This accessor method returns a reference to the live list,
     * not a snapshot. Therefore any modification you make to the
     * returned list will be present inside the JAXB object.
     * This is why there is not a <CODE>set</CODE> method for the cenovnici property.
     * 
     * <p>
     * For example, to add a new item, do as follows:
     * <pre>
     *    getCenovnici().add(newItem);
     * </pre>
     * 
     * 
     * <p>
     * Objects of the following type(s) are allowed in the list
     * {@link CenovnikDTO }
     * 
     * 
     */
    public List<CenovnikDTO> getCenovnici() {
        if (cenovnici == null) {
            cenovnici = new ArrayList<CenovnikDTO>();
        }
        return this.cenovnici;
    }

		public static List<SmestajnaJedinicaDTO> transformisi(List<SmestajnaJedinica> jedinice) {
		List<SmestajnaJedinicaDTO> rezultat = new ArrayList<>();
		for(SmestajnaJedinica jedinica : jedinice) {
			rezultat.add(new SmestajnaJedinicaDTO(jedinica));
		}
		return rezultat;
	}

}
