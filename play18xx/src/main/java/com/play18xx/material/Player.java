package com.play18xx.material;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlAttribute;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlElementWrapper;
import javax.xml.bind.annotation.XmlRootElement;

@XmlRootElement
@XmlAccessorType(value = XmlAccessType.FIELD)
public class Player implements Serializable {
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	@XmlAttribute
	private String Name;
	@XmlElement
	private int Playernumber;
	@XmlElement
	private int Money;
	@XmlElementWrapper(name = "Certificates") 
	@XmlElement
	private List<Certificate> Certificates = new ArrayList<Certificate>();
	@XmlElementWrapper(name = "SoldCorps") 
	@XmlElement
	private List<Corporation> SoldCorps = new ArrayList<Corporation>();
	@XmlElementWrapper(name = "Privates") 
	@XmlElement
	private List<Private> Privates = new ArrayList<Private>();

	public Player() {
	}

	public Player(String Name, int Playernumber, int Money) {
		if (startmethods.Play1830.verbose) {
			System.out.println("Player.Player");
		}
		this.Name = Name;
		this.Playernumber = Playernumber;
		this.Money = Money;
	}

	public int findPrivate(Private priv) {
		int index = 0;
		for (Private privs : Privates) {
			if (privs.getName().equals(priv.getName())) {
				break;
			} else {
				index = index + 1;
			}
		}
		return index;
	}
	
	public boolean soldCorp(Corporation corp) {
		for(Corporation solds : this.getSoldCorps()) { if(solds.equals(corp)) { return true; } }
		return false;
	}
	
	public void setCertCorps(Basic basic) {
		if (startmethods.Play1830.verbose) { System.out.println("Player.setCertCorps"); }
		for(Certificate cert : this.getCertificates()) {
			for(Corporation corp : basic.getCorporations()) {
				if(cert.getName().split("-")[0].equals(corp.getName())) {cert.setCorporation(corp);};
			}
		}
	}

	public void increaseMoney(int diff) {
		this.Money = this.Money + diff;
	}

	public void decreaseMoney(int diff) {
		this.Money = this.Money - diff;
	}

	public String getName() {
		return Name;
	}

	public void setName(String name) {
		Name = name;
	}

	public int getPlayernumber() {
		return Playernumber;
	}

	public void setPlayernumber(int playernumber) {
		Playernumber = playernumber;
	}

	public int getMoney() {
		return Money;
	}

	public void setMoney(int money) {
		Money = money;
	}

	public List<Certificate> getCertificates() {
		return Certificates;
	}

	public void setCertificates(List<Certificate> certificates) {
		Certificates = certificates;
	}

	public List<Corporation> getSoldCorps() {
		return SoldCorps;
	}

	public void setSoldCorps(List<Corporation> soldCorps) {
		SoldCorps = soldCorps;
	}

	public List<Private> getPrivates() {
		return Privates;
	}

	public void setPrivates(List<Private> privates) {
		Privates = privates;
	}
}
