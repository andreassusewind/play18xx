package com.play18xx.material;

import java.io.Serializable;
import java.util.List;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlAttribute;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlElementWrapper;
import javax.xml.bind.annotation.XmlRootElement;

@XmlRootElement
@XmlAccessorType(value = XmlAccessType.FIELD)
public class Dividend implements Serializable {
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	@XmlAttribute
	int Dividend;
	@XmlElement
	@XmlElementWrapper(name = "Trains") 
	List<Train> TrainSet;
	@XmlElement
	int GamePhase;
	@XmlElement
	boolean Payout;

	public Dividend() {
	}
	
	public Dividend(int div, List<Train> TrainSet, int GamePhase, boolean payout) {
		this.Dividend = div;
		this.TrainSet = TrainSet;
		this.GamePhase = GamePhase;
		this.Payout = payout;
	}

	public int getDividend() {
		return Dividend;
	}

	public void setDividend(int dividend) {
		Dividend = dividend;
	}

	public List<Train> getTrainSet() {
		return TrainSet;
	}

	public void setTrainSet(List<Train> trainSet) {
		TrainSet = trainSet;
	}

	public int getGamePhase() {
		return GamePhase;
	}

	public void setGamePhase(int gamePhase) {
		GamePhase = gamePhase;
	}

	public boolean isPayout() {
		return Payout;
	}

	public void setPayout(boolean payout) {
		Payout = payout;
	}
}
