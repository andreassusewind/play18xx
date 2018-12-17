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
	private int Index;
	@XmlElement
	private int Money;
	@XmlElementWrapper(name = "SoldCorps") 
	@XmlElement
	private List<Integer> SoldCorps = new ArrayList<Integer>();

	public Player() {
	}

	public Player(String Name, int Playernumber, int Money) {
		this.Name = Name;
		this.Index = Playernumber;
		this.Money = Money;
	}
	
	public int getPlayersCertsCount(Basic basic) {
		int pc = 0;
		for(Corporation corp : basic.getCorporations()) {
			if(corp.getMarker().CountToShareLimit(basic)) {
				pc = pc + corp.getPlayersCertsCount(this);
			}
		}
		for(Private priv : basic.getPrivates()) {
			if(priv.getOwner() == this.Index) {pc++;}
		}
		return pc;
	}
	
	public int getPlayersCertsOverall(Basic basic) {
		int pc = 0;
		for(Corporation corp : basic.getCorporations()) {
				pc = pc + corp.getPlayersCertsCount(this);
		}
		for(Private priv : basic.getPrivates()) {
			if(priv.getOwner() == this.Index) {pc++;}
		}
		return pc;
		
	}
	
	public int getPlayersCertsValue(Basic basic) {
		int pcv = 0;
		for(Corporation corp : basic.getCorporations()) {
			for(Certificate cert : corp.getCertificates()) {
				if(cert.getOwner() == this.Index) {
					pcv = pcv + (corp.getMarker().getValue() * cert.getPercentValue()/10);
				}
			}
		}
		return (pcv);
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

	public int getIndex() {
		return Index;
	}

	public void setIndex(int index) {
		Index = index;
	}

	public int getMoney() {
		return Money;
	}

	public void setMoney(int money) {
		Money = money;
	}

	public List<Integer> getSoldCorps() {
		return SoldCorps;
	}

	public void setSoldCorps(List<Integer> soldCorps) {
		SoldCorps = soldCorps;
	}
}
