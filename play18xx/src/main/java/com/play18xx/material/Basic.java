package com.play18xx.material;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlRootElement;

import com.play18xx.graphic.FrameMain;
import com.play18xx.graphic.PaneConfiguration;
import com.play18xx.graphic.PaneCorporation;
import com.play18xx.graphic.PaneOperatingRound;
import com.play18xx.graphic.PanePlayer;
import com.play18xx.graphic.PaneStockmarket;

@XmlRootElement
@XmlAccessorType(value = XmlAccessType.FIELD)
public class Basic implements Serializable {
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	private List<Player> Players = new ArrayList<Player>();
	private List<Corporation> Corporations = new ArrayList<Corporation>();
	private List<Private> Privates = new ArrayList<Private>();
	private Gameplay Gameplay;
	private Stockmarket Stockmarket;
	private FrameMain TP;

	public Basic() {
		this.TP = new FrameMain();
		PaneConfiguration.setPaneConfiguration(this);
	}

	public Basic(int PlayerQuantity, String[] PlayerNames, int PlayerMoney, int CorporationQuantity, String[] CorpNames,
			int[] CorpStations, int PrivateQuantity, String[] PrivNames, int[] PrivParVal, int[] PrivRev,
			String[] PrivSpecial, int MaxMoney, int MaxCertificates, int[] TrainDistances, int[] TrainQuantities,
			int[] TrainCosts, int[] TrainRusts, String FilePathStockmarket, FrameMain tp) {

		for (int i = 0; i < PlayerQuantity; i++) {
			this.Players.add(new Player(PlayerNames[i], i, PlayerMoney));
		}
		for (int i = 0; i < CorporationQuantity; i++) {
			this.Corporations.add(new Corporation(CorpNames[i], CorpStations[i], i));
		}
		for (int i = 0; i < PrivateQuantity; i++) {
			this.Privates.add(new Private(PrivNames[i], PrivParVal[i], PrivRev[i], PrivSpecial[i], i));
		}

		this.Gameplay = new Gameplay(MaxMoney, MaxCertificates, TrainDistances, TrainQuantities, TrainCosts,
				TrainRusts);
		this.Stockmarket = new Stockmarket(FilePathStockmarket);
		this.TP = tp;

		buildGraphics();
	}

	public void buildGraphics() {
		// refreshing functions
		Private.closePrivates(this);

		// build all graphic panels
		PaneStockmarket.setPaneStockmarket(this);
		PaneOperatingRound.setPaneOperatingRound(this);
		PanePlayer.setPanePlayer(this);
		PaneCorporation.setPaneCorporation(this);
	}

	public void switchToOperationRound() {
		if(this.Gameplay.isStockmarketRound())
		{ 
			// get all CorporationPositions of President sold corps
			this.getStockmarket().getCorporationPositions().clear(); 
			for (Corporation corp : this.Corporations) {
				if (corp.getCertificates().get(0).getOwner() < 10) {
					this.getStockmarket().getCorporationPositions().add(corp.getMarker()); 
				}
			}
			com.play18xx.material.Stockmarket.sortCorporationPosition(this.getStockmarket().getCorporationPositions()); 
			// in sorting order set all fully sold corps marker one up
			for (CorporationPosition cp : this.getStockmarket().getCorporationPositions()) {
				if (cp.getCorp().getSoldShares() == 100) { cp.getCorp().getMarker().setUp(this); }
			}
			// get all CorporationPositions of min 60% sold corps
			this.getStockmarket().getCorporationPositions().clear();
			for (Corporation corp : this.Corporations) {
				if (corp.getInitialShares() <= 40) { 
					corp.setOpen(true);
					this.getStockmarket().getCorporationPositions().add(corp.getMarker());
				}
			}
			com.play18xx.material.Stockmarket.sortCorporationPosition(this.getStockmarket().getCorporationPositions()); 
			// in sorting order write the corp of CorporationPositions on the OperationroundCorpOrder
			for (CorporationPosition pos : this.Stockmarket.getCorporationPositions()) {
				this.Gameplay.getOperationroundCorpOrder().add(pos.getCorp());
			}
			
			this.getGameplay().setOperationRound(true);
			this.getGameplay().setStockmarketRound(false);
		}
		this.buildGraphics();
		this.getTP().setSelectedIndex(1);
	}

	public void newOperationRound() {
		System.out.println("Basic:newOperationRound - not implemented");
/*		basic.getGameplay().setOperationroundCounter(basic.getGameplay().getOperationroundCounter() + 1);
		basic.getTP().refreshPOR();
		basic.buildGraphics();
		basic.getTP().setSelectedIndex(tabpos);*/
		
	}
	
	public void switchToStockMarketRound() {
		if(this.Gameplay.isOperationRound()) {
			this.getTP().refreshPOR();
			
			for(Corporation corp : this.Gameplay.getOperationroundCorpOrder()) {
				corp.resetDoneFlags();
			}
			
			for(Player player : this.Players) {
				player.setSoldCorps(new ArrayList<Integer>());
			}
			
			this.Gameplay.setPrivatesDone(false);
			this.Gameplay.setPassNumber(0);
			this.Gameplay.setOperationroundCounter(1);
			this.Gameplay.increaseStockmarketRoundCounter();
			this.Gameplay.getOperationroundCorpOrder().clear();
			this.getGameplay().setOperationRound(false);
			this.getGameplay().setStockmarketRound(true);
		}
		this.buildGraphics();
		this.getTP().setSelectedIndex(0);
	}
	
	public int getCorporationIndex(String Name) {
		for(Corporation corp : this.Corporations) {
			if(corp.getName().equals(Name)) {return corp.getIndex();}
		}
		return 99;
	}
	
	public List<Private> getPlayerPrivates(int player){
		List<Private> privs = new ArrayList<Private>();
		for(Private priv : this.Privates) {
			if(priv.getOwner() == player) {privs.add(priv);}
		}
		return privs;
	}
	
	public List<Certificate> getPlayerCertificates(int player) {
		List<Certificate> certs= new ArrayList<Certificate>();
		for(Corporation corp : this.Corporations) {
			for(Certificate cert : corp.getCertificates()) {
				if(cert.getOwner() == player) {certs.add(cert);}
			}
		}
		return certs;
	}
	
	public List<Player> getPlayers() {
		return Players;
	}

	public void setPlayers(List<Player> players) {
		Players = players;
	}

	public List<Corporation> getCorporations() {
		return Corporations;
	}

	public void setCorporations(List<Corporation> corporations) {
		Corporations = corporations;
	}

	public List<Private> getPrivates() {
		return Privates;
	}

	public void setPrivates(List<Private> privates) {
		Privates = privates;
	}

	public Gameplay getGameplay() {
		return Gameplay;
	}

	public void setGameplay(Gameplay gameplay) {
		Gameplay = gameplay;
	}

	public Stockmarket getStockmarket() {
		return Stockmarket;
	}

	public void setStockmarket(Stockmarket stockmarket) {
		Stockmarket = stockmarket;
	}

	public FrameMain getTP() {
		return TP;
	}

	public void setTP(FrameMain tP) {
		TP = tP;
	}
}
