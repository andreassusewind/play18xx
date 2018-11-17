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
	@XmlElement
	private int Index;
	@XmlElementWrapper(name = "Certificates") 
	@XmlElement // Owner 0-9 Player --- 91 InitialStock --- 92 BankPool
	private List<Certificate> Certificates = new ArrayList<Certificate>();
	@XmlElement
	private int ShareParValue = 0;
	@XmlElement
	private CorporationPosition Marker = new CorporationPosition();
	@XmlElement
	private int Money = 0;
	@XmlElementWrapper(name = "Trains") 
	@XmlElement
	private List<Train> Trains = new ArrayList<Train>();
	@XmlElementWrapper(name = "Stations") 
	@XmlElement
	private List<Station> Stations = new ArrayList<Station>();
	@XmlElementWrapper(name = "Dividends") 
	@XmlElement
	private List<Dividend> Dividends = new ArrayList<Dividend>();
	@XmlElement
	private int President = 99;
	@XmlElement
	private boolean Open = false;
	@XmlElement
	private boolean PrivateDone = false;
	@XmlElement
	private boolean TileDone = false;
	@XmlElement
	private boolean StationDone = false;
	@XmlElement
	private boolean DividendDone = false;
	@XmlElement
	private boolean TrainDone = false;
	@XmlElement
	private boolean OpRoundDone = false;

	public Corporation() {
	}

	public Corporation(String Name, int StationQuantity, int index) {
		this.Name = Name;
		this.Index = index;
		this.Certificates.add(new Certificate(this, 20, true));
		for (int i = 0; i < 8; i++) {
			this.Certificates.add(new Certificate(this, 10, false));
		}
		int[] StationCosts = { 0, 40, 100, 100, 100, 100 };
		for (int i = 0; i < StationQuantity; i++) {
			this.Stations.add(new Station(StationCosts[i]));
		}
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

	public int[] getPlayerShares(List<Player> players) {  // delete List<Player> and set for the size() a maximum function
		int[] revenue = new int[players.size()];
		for (Certificate cert : this.Certificates) {
			if (cert.getOwner() < 10) {
				revenue[cert.getOwner()] = revenue[cert.getOwner()] + cert.getPercentValue();
			}
		}
		return revenue;
	}
	
	public List<Certificate> getInitialStock(){
		List<Certificate> certs = new ArrayList<Certificate>();
		for(Certificate cert : this.Certificates) {
			if(cert.getOwner() == 91) {certs.add(cert);}
		}
		return certs;
	}

	public int[] getPlayerIncome(int playernumber, int income) {  // Could be deleted....
		int[] pincome = new int[playernumber];
		int[] revenue = new int[playernumber];
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

	public List<Certificate> getCertificates() {
		return Certificates;
	}

	public void setCertificates(List<Certificate> certificates) {
		Certificates = certificates;
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

	public List<Train> getTrains() {
		return Trains;
	}

	public void setTrains(List<Train> trains) {
		Trains = trains;
	}

	public List<Station> getStations() {
		return Stations;
	}

	public void setStations(List<Station> stations) {
		Stations = stations;
	}

	public List<Dividend> getDividends() {
		return Dividends;
	}

	public void setDividends(List<Dividend> dividends) {
		Dividends = dividends;
	}

	public int getPresident() {
		return President;
	}

	public void setPresident(int president) {
		President = president;
	}

	public boolean isOpen() {
		return Open;
	}

	public void setOpen(boolean open) {
		Open = open;
	}

	public boolean isPrivateDone() {
		return PrivateDone;
	}

	public void setPrivateDone(boolean privateDone) {
		PrivateDone = privateDone;
	}

	public boolean isTileDone() {
		return TileDone;
	}

	public void setTileDone(boolean tileDone) {
		TileDone = tileDone;
	}

	public boolean isStationDone() {
		return StationDone;
	}

	public void setStationDone(boolean stationDone) {
		StationDone = stationDone;
	}

	public boolean isDividendDone() {
		return DividendDone;
	}

	public void setDividendDone(boolean dividendDone) {
		DividendDone = dividendDone;
	}

	public boolean isTrainDone() {
		return TrainDone;
	}

	public void setTrainDone(boolean trainDone) {
		TrainDone = trainDone;
	}

	public boolean isOpRoundDone() {
		return OpRoundDone;
	}

	public void setOpRoundDone(boolean opRoundDone) {
		OpRoundDone = opRoundDone;
	}
}
