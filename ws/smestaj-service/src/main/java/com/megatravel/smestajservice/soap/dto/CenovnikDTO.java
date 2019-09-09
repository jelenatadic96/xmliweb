//
// This file was generated by the JavaTM Architecture for XML Binding(JAXB) Reference Implementation, v2.2.7 
// See <a href="http://java.sun.com/xml/jaxb">http://java.sun.com/xml/jaxb</a> 
// Any modifications to this file will be lost upon recompilation of the source schema. 
// Generated on: 2019.08.19 at 11:06:20 AM CEST 
//


package com.megatravel.smestajservice.soap.dto;

import java.time.ZoneId;
import java.util.ArrayList;
import java.util.Date;
import java.util.GregorianCalendar;
import java.util.List;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlSchemaType;
import javax.xml.bind.annotation.XmlType;
import javax.xml.datatype.DatatypeConfigurationException;
import javax.xml.datatype.DatatypeFactory;
import javax.xml.datatype.XMLGregorianCalendar;

import com.megatravel.smestajservice.model.Cenovnik;


/**
 * <p>Java class for CenovnikDTO complex type.
 * 
 * <p>The following schema fragment specifies the expected content contained within this class.
 * 
 * <pre>
 * &lt;complexType name="CenovnikDTO">
 *   &lt;complexContent>
 *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
 *       &lt;sequence>
 *         &lt;element name="id" type="{http://www.w3.org/2001/XMLSchema}long"/>
 *         &lt;element name="cenaPoNoci" type="{http://www.w3.org/2001/XMLSchema}double"/>
 *         &lt;element name="prviDanVazenja" type="{http://www.w3.org/2001/XMLSchema}dateTime"/>
 *         &lt;element name="poslednjiDanVazenja" type="{http://www.w3.org/2001/XMLSchema}dateTime"/>
 *         &lt;element name="smestaj" type="{http://www.w3.org/2001/XMLSchema}long"/>
 *       &lt;/sequence>
 *     &lt;/restriction>
 *   &lt;/complexContent>
 * &lt;/complexType>
 * </pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "CenovnikDTO", propOrder = {
    "id",
    "cenaPoNoci",
    "prviDanVazenja",
    "poslednjiDanVazenja",
    "smestaj"
})
public class CenovnikDTO {

    protected long id;
    protected double cenaPoNoci;
    @XmlElement(required = true)
    @XmlSchemaType(name = "dateTime")
    protected XMLGregorianCalendar prviDanVazenja;
    @XmlElement(required = true)
    @XmlSchemaType(name = "dateTime")
    protected XMLGregorianCalendar poslednjiDanVazenja;
    protected long smestaj;
    
	public CenovnikDTO() { }
	
	public CenovnikDTO(Cenovnik cenovnik) {
		this.id = cenovnik.getId();
		this.cenaPoNoci = cenovnik.getCenaPoNoci();
    	GregorianCalendar kalendar = new GregorianCalendar();
    	kalendar.setTime(Date.from(cenovnik.getPrviDanVazenja().atStartOfDay(ZoneId.systemDefault()).toInstant()));
    	try {
			this.prviDanVazenja = DatatypeFactory.newInstance().newXMLGregorianCalendar(kalendar);
		} catch (DatatypeConfigurationException e) {
			e.printStackTrace();
		}
    	kalendar.setTime(Date.from(cenovnik.getPoslednjiDanVazenja().atStartOfDay(ZoneId.systemDefault()).toInstant()));
    	try {
			this.poslednjiDanVazenja = DatatypeFactory.newInstance().newXMLGregorianCalendar(kalendar);
		} catch (DatatypeConfigurationException e) {
			e.printStackTrace();
		}
    	this.smestaj = cenovnik.getSmestajnaJedinica().getId();
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
     * Gets the value of the cenaPoNoci property.
     * 
     */
    public double getCenaPoNoci() {
        return cenaPoNoci;
    }

    /**
     * Sets the value of the cenaPoNoci property.
     * 
     */
    public void setCenaPoNoci(double value) {
        this.cenaPoNoci = value;
    }

    /**
     * Gets the value of the prviDanVazenja property.
     * 
     * @return
     *     possible object is
     *     {@link XMLGregorianCalendar }
     *     
     */
    public XMLGregorianCalendar getPrviDanVazenja() {
        return prviDanVazenja;
    }

    /**
     * Sets the value of the prviDanVazenja property.
     * 
     * @param value
     *     allowed object is
     *     {@link XMLGregorianCalendar }
     *     
     */
    public void setPrviDanVazenja(XMLGregorianCalendar value) {
        this.prviDanVazenja = value;
    }

    /**
     * Gets the value of the poslednjiDanVazenja property.
     * 
     * @return
     *     possible object is
     *     {@link XMLGregorianCalendar }
     *     
     */
    public XMLGregorianCalendar getPoslednjiDanVazenja() {
        return poslednjiDanVazenja;
    }

    /**
     * Sets the value of the poslednjiDanVazenja property.
     * 
     * @param value
     *     allowed object is
     *     {@link XMLGregorianCalendar }
     *     
     */
    public void setPoslednjiDanVazenja(XMLGregorianCalendar value) {
        this.poslednjiDanVazenja = value;
    }

	public static List<CenovnikDTO> transformisi(List<Cenovnik> cenovnici) {
		List<CenovnikDTO> rezultat = new ArrayList<CenovnikDTO>();
		for(Cenovnik cenovnik : cenovnici) {
			rezultat.add(new CenovnikDTO(cenovnik));
		}
		return rezultat;
	}

	public long getSmestaj() {
		return smestaj;
	}

	public void setSmestaj(long smestaj) {
		this.smestaj = smestaj;
	}

}
