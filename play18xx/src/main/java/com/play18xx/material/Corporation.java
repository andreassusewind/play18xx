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
public class Corporation implements Serializable {
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	@XmlAttribute
	private String Name;
	@XmlElementWrapper(name = "Certificates") 
	@XmlElement
	private List<Certificate> Certificates = new ArrayList<Certificate>();
	@XmlElementWrapper(name = "InitialStock") 
	@XmlElement
	private List<Certificate> InitialStock = new ArrayList<Certificate>();
	@XmlElementWrapper(name = "BankPool") 
	@XmlElement
	private List<Certificate> BankPool = new ArrayList<Certificate>();
	@XmlElementWrapper(name = "Privates") 
	@XmlElement
	private List<Private> Privates = new ArrayList<Private>();
	@XmlElement
	private String PrivateDone = "";
	@XmlElement
	private int ShareParValue;
	@XmlElement
	private CorporationPosition Marker;
	@XmlElement
	private int Money;
	@XmlElement
	private String TileDone = "";
	@XmlElementWrapper(name = "Trains") 
	@XmlElement
	private List<Train> Trains = new ArrayList<Train>();
	@XmlElement
	private String TrainDone = "";
	@XmlElementWrapper(name = "Stations") 
	@XmlElement
	private List<Station> Stations = new ArrayList<Station>();
	@XmlElement
	private String StationDone = "";
	@XmlElementWrapper(name = "Dividends") 
	@XmlElement
	private List<Dividend> Dividends = new ArrayList<Dividend>();
	@XmlElement
	private String DividendDone = "";
	@XmlElement
	private Player President;
	@XmlElement
	private boolean Open;
	@XmlElement
	private String OpRoundDone = "";

	public Corporation() {
	}

	public Corporation(String Name, int StationQuantity) {
		this.Name = Name;

		this.Certificates.add(new Certificate(this, 20, true)); // Set President share
		this.InitialStock.add(new Certificate(this, 20, true));
		for (int i = 0; i < 8; i++) {
			this.Certificates.add(new Certificate(this, 10, false));// Set Normal shares
			this.InitialStock.add(new Certificate(this, 10, false));
		}

		ShareParValue = 0;
		this.Marker = new CorporationPosition();

		int[] StationCosts = { 0, 40, 100, 100, 100, 100 }; // Set Stations
		for (int i = 0; i < StationQuantity; i++) {
			this.Stations.add(new Station(StationCosts[i]));
		}

		this.Open = false;
	}

	public int getSoldShares() {
		int SoldShares = 0;
		for (int i = 0; i < this.Certificates.size(); i++) {
			if (this.Certificates.get(i).getOwner() < 10) {
				SoldShares = SoldShares + this.Certificates.get(i).getPercentValue();
			}
		}
		return (SoldShares);
	}

	public int getInitialShares() {
		int InitialShares = 0;
		for (Certificate cert : this.Certificates) {
			if (cert.getOwner() == 91) {
				InitialShares = InitialShares + cert.getPercentValue();
			}
		}
		return InitialShares;
	}

	public int getCorporationNumber(Basic basic) {
		// gets the List Number in a Corporation List (i.e. in Basic Class)
		for (int i = 1; i <= basic.getCorporations().size(); i++) {
			if (basic.getCorporations().get(i - 1).getName().equals(this.Name)) {
				return (i - 1);
			}
		}
		return 99;
	}

	public static int getCorporationNumber(Basic basic, String name) {
		// gets the List Number in a Corporation List (i.e. in Basic Class)
		for (int i = 1; i <= basic.getCorporations().size(); i++) {
			if (basic.getCorporations().get(i - 1).getName().equals(name)) {
				return (i - 1);
			}
		}
		return (99);
	}

	public int[] getPlayerShares(List<Player> players) {
		int[] revenue = new int[players.size()];

		for (Certificate cert : this.Certificates) {
			if (cert.getOwner() < 10) {
				revenue[cert.getOwner()] = revenue[cert.getOwner()] + cert.getPercentValue();
			}
		}
		return revenue;
	}

	public int[] getPlayerIncome(List<Player> players, int income) {
		int[] pincome = new int[players.size()];
		int[] revenue = new int[players.size()];
		for (Certificate cert : this.Certificates) {
			if (cert.getOwner() < 10) {
				revenue[cert.getOwner()] = revenue[cert.getOwner()] + cert.getPercentValue();
			}
		}
		for (int i = 0; i < pincome.length; i++) {
			pincome[i] = (int) ((double) revenue[i] / 100 * income);
		}
		return pincome;
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

	public List<Certificate> getCertificates() {
		return Certificates;
	}

	public void setCertificates(List<Certificate> certificates) {
		Certificates = certificates;
	}

	public List<Certificate> getInitialStock() {
		return InitialStock;
	}

	public void setInitialStock(List<Certificate> initialStock) {
		InitialStock = initialStock;
	}

	public List<Certificate> getBankPool() {
		return BankPool;
	}

	public void setBankPool(List<Certificate> bankPool) {
		BankPool = bankPool;
	}

	public List<Private> getPrivates() {
		return Privates;
	}

	public void setPrivates(List<Private> privates) {
		Privates = privates;
	}

	public String getPrivateDone() {
		return PrivateDone;
	}

	public void setPrivateDone(String privateDone) {
		PrivateDone = privateDone;
	}

	public int getShareParValue() {
		return ShareParValue;
	}

	public void setShareParValue(int shareParValue) {
		ShareParValue = shareParValue;
	}

	public CorporationPosition getMarker() {
		return Marker;
	}

	public void setMarker(CorporationPosition marker) {
		Marker = marker;
	}

	public int getMoney() {
		return Money;
	}

	public void setMoney(int money) {
		Money = money;
	}

	public String getTileDone() {
		return TileDone;
	}

	public void setTileDone(String tileDone) {
		TileDone = tileDone;
	}

	public List<Train> getTrains() {
		return Trains;
	}

	public void setTrains(List<Train> trains) {
		Trains = trains;
	}

	public String getTrainDone() {
		return TrainDone;
	}

	public void setTrainDone(String trainDone) {
		TrainDone = trainDone;
	}

	public List<Station> getStations() {
		return Stations;
	}

	public void setStations(List<Station> stations) {
		Stations = stations;
	}

	public String getStationDone() {
		return StationDone;
	}

	public void setStationDone(String stationDone) {
		StationDone = stationDone;
	}

	public List<Dividend> getDividends() {
		return Dividends;
	}

	public void setDividends(List<Dividend> dividends) {
		Dividends = dividends;
	}

	public String getDividendDone() {
		return DividendDone;
	}

	public void setDividendDone(String dividendDone) {
		DividendDone = dividendDone;
	}

	public Player getPresident() {
		return President;
	}

	public void setPresident(Player president) {
		President = president;
	}

	public boolean isOpen() {
		return Open;
	}

	public void setOpen(boolean open) {
		Open = open;
	}

	public String getOpRoundDone() {
		return OpRoundDone;
	}

	public void setOpRoundDone(String opRoundDone) {
		OpRoundDone = opRoundDone;
	}

}
