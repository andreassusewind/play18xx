package com.play18xx.material;

import java.io.Serializable;
import java.util.Comparator;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlAttribute;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;

@XmlRootElement
@XmlAccessorType(value = XmlAccessType.FIELD)
public class Certificate implements Serializable {
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	@XmlAttribute
	private String Name;
/*	@XmlTransient
	private Corporation Corporation; */
	@XmlElement
	private int PercentValue;
	@XmlElement
	private int Owner;
	@XmlElement
	private boolean President;

	public Certificate() {
	}

	public Certificate(Corporation Corporation, int PercentValue, boolean President) {
		this.Name = Corporation.getName() + "-" + PercentValue;
//		this.Corporation = Corporation;
		this.PercentValue = PercentValue;
		this.Owner = 91; // Owner 0-7 Player --- 91 InitialStock --- 92 BankPool
		this.President = President;
	}

	public static Comparator<Certificate> NameComparator = new Comparator<Certificate>() {

		public int compare(Certificate s1, Certificate s2) {
			String CertName1 = s1.getName();
			String CertName2 = s2.getName();

			return CertName1.compareTo(CertName2);
		}
	};

	public String getName() {
		return Name;
	}

	public void setName(String name) {
		Name = name;
	}

/*	public Corporation getCorporation() {
		return Corporation;
	}

	public void setCorporation(Corporation corporation) {
		Corporation = corporation;
	}*/

	public int getPercentValue() {
		return PercentValue;
	}

	public void setPercentValue(int percentValue) {
		PercentValue = percentValue;
	}

	public int getOwner() {
		return Owner;
	}

	public void setOwner(int owner) {
		Owner = owner;
	}

	public boolean isPresident() {
		return President;
	}

	public void setPresident(boolean president) {
		President = president;
	}
}
