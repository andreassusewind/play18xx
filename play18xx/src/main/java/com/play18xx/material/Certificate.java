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
	@XmlElement
	private int PercentValue;
	@XmlElement
	private int Owner;// Owner 0-9 Player --- 91 InitialStock --- 92 BankPool
	@XmlElement
	private boolean President;

	public Certificate() {
	}

	public Certificate(Corporation Corporation, int PercentValue, boolean President) {
		this.Name = Corporation.getName() + "-" + PercentValue;
		this.PercentValue = PercentValue;
		this.Owner = 91; 
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
