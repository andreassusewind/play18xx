package com.play18xx.material;

import java.io.Serializable;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlAttribute;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;

@XmlRootElement
@XmlAccessorType(value = XmlAccessType.FIELD)
public class Station implements Serializable {
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	@XmlAttribute
	private int Cost;
	@XmlElement
	private boolean Build;

	public Station() {
	}

	public Station(int Cost) {
		this.Cost = Cost;
		this.Build = false;
	}

	public int getCost() {
		return Cost;
	}

	public void setCost(int cost) {
		Cost = cost;
	}

	public boolean isBuild() {
		return Build;
	}

	public void setBuild(boolean build) {
		Build = build;
	}
}
