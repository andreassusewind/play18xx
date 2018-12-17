package com.play18xx.material;

import java.io.Serializable;

import javax.xml.bind.annotation.XmlAttribute;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;

import com.play18xx.graphic.WindowBuy;

@XmlRootElement
public class Private implements Serializable {
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	@XmlAttribute
	private String Name;
	@XmlElement
	private int Index;
	@XmlElement
	private int ParValue;
	@XmlElement
	private int Revenue;
	@XmlElement
	private String SpecialFunction;
	@XmlElement
	private int Owner; // Owner 0-7 Player --- 30-40 corp --- 91 InitialStock --- 92 BankPool --- 99
						// Out of game

	public Private() {
	}

	public Private(String Name, int ParValue, int Revenue, String SpecialFunction, int Index) {
		this.Name = Name;
		this.Index = Index;
		this.ParValue = ParValue;
		this.Revenue = Revenue;
		this.SpecialFunction = SpecialFunction;
		this.Owner = 91; // Owner 0-7 Player --- 30-40 corp --- 91 InitialStock --- 92 BankPool --- 99
							// Out of game
	}

	public void BuyPrivate(Basic basic, int playernumber) {
		int index = 99;
		switch (this.SpecialFunction) {
		case "CA": // the CA owner get an 10% share of the PRR
			for (int i = 0; i < basic.getCorporations().size(); i++) { // Find PRR Corporation
				if (basic.getCorporations().get(i).getName().equals("PRR")) {
					index = i;
				}
			}
			basic.getCorporations().get(index).getCertificates().get(1).setOwner(playernumber);
			break;
		case "BO": // the BO owner get the 20% president share of the B&O --> Sets ParValue...
			for (int i = 0; i < basic.getCorporations().size(); i++) { // Find B&O Corporation
				if (basic.getCorporations().get(i).getName().equals("B&O")) {
					index = i;
				}
			}
			basic.getCorporations().get(index).getCertificates().get(0).setOwner(playernumber);
			// ***** WindowBuy for President Certificate must be implemented
			WindowBuy.setWindowBuy(basic, basic.getCorporations().get(index),
					basic.getPlayers().get(playernumber), 48);
			break;
		}
	}

	public void distributeRevenue(Basic basic) {
		if (this.Owner < 10) {
			basic.getPlayers().get(this.Owner).increaseMoney(this.Revenue);
		}
		if (this.Owner > 20 && this.Owner < 50) {
			basic.getCorporations().get(this.Owner - 30).increaseMoney(this.Revenue);
		}
	}

	private static int getPrivateNumber(Basic basic, String name) {
		for (int i = 1; i <= basic.getPrivates().size(); i++) {
			if (basic.getPrivates().get(i - 1).getName().equals(name)) {
				return (i - 1);
			}
		}

		return 99;
	}

	public static void closePrivates(Basic basic) {
		int corpindex = basic.getCorporationIndex("B&O");//Corporation.getCorporationNumber(basic, "B&O");
		int privindex;

		if (basic.getGameplay().getPhase() >= 5) { // if Phase 5 has started
			for (Private priv : basic.getPrivates()) {
				priv.setOwner(99);
			}
		}

		if (basic.getCorporations().get(corpindex).getTrains().size() > 0) { // if B&O has a train
			privindex = Private.getPrivateNumber(basic, "BO");
			basic.getPrivates().get(privindex).setOwner(99);
		}
	};

	public String getName() {
		return Name;
	}

	public void setName(String name) {
		Name = name;
	}

	public int getParValue() {
		return ParValue;
	}

	public void setParValue(int parValue) {
		ParValue = parValue;
	}

	public int getRevenue() {
		return Revenue;
	}

	public void setRevenue(int revenue) {
		Revenue = revenue;
	}

	public String getSpecialFunction() {
		return SpecialFunction;
	}

	public void setSpecialFunction(String specialFunction) {
		SpecialFunction = specialFunction;
	}

	public int getOwner() {
		return Owner;
	}

	public String getOwner(Basic basic) {
		String Owner = "";
		if (this.Owner < 10) {
			Owner = basic.getPlayers().get(this.Owner).getName();
		}
		if (this.Owner > 20 && this.Owner < 50) {
			Owner = basic.getCorporations().get(this.Owner - 30).getName();
		}
		if (this.Owner == 91) {
			Owner = "Initial_Stock";
		}
		if (this.Owner == 99) {
			Owner = "Out of Game";
		}
		return Owner;
	}
	
	public void setOwner(int owner) {
		Owner = owner;
	}
}
