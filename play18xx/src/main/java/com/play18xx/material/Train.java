package com.play18xx.material;

import java.io.Serializable;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlAttribute;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;

@XmlRootElement
@XmlAccessorType(value = XmlAccessType.FIELD)
public class Train implements Serializable {
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	@XmlAttribute
	private int DistancePrimary;
	@XmlElement
	private int DistanceSecondary;
	@XmlElement
	private int Cost;
	@XmlElement
	private int Rust;

	public Train() {
	}

	public Train(int DistancePrim, int Cost, int Rust) {
		this.DistancePrimary = DistancePrim;
		this.Cost = Cost;
		this.Rust = Rust;
	}

	/// NOT YET IMPLEMENTED --> MUST ALSO IMPLEMENTED IN GAMEPLAY.GAMEPLAY
	public Train(int DistancePrim, int DistanceSec, int Cost, int Rust) {

	}

	public int getDistancePrimary() {
		return DistancePrimary;
	}

	public void setDistancePrimary(int distancePrimary) {
		DistancePrimary = distancePrimary;
	}

	public int getDistanceSecondary() {
		return DistanceSecondary;
	}

	public void setDistanceSecondary(int distanceSecondary) {
		DistanceSecondary = distanceSecondary;
	}

	public int getCost() {
		return Cost;
	}

	public void setCost(int cost) {
		Cost = cost;
	}

	public int getRust() {
		return Rust;
	}

	public void setRust(int rust) {
		Rust = rust;
	}
}
