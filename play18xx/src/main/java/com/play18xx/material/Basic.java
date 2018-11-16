package com.play18xx.material;

import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectOutputStream;
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
//	private PaneOperatingRound_OLD POR = new PaneOperatingRound_OLD();

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
			this.Corporations.add(new Corporation(CorpNames[i], CorpStations[i]));
		}
		for (int i = 0; i < PrivateQuantity; i++) {
			this.Privates.add(new Private(PrivNames[i], PrivParVal[i], PrivRev[i], PrivSpecial[i]));
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
		// if there is no corp order for operation round
//		if (this.Gameplay.getOperationroundCorpOrder().size() == 0) 
		System.out.print(this.Gameplay.isOperationRound());
		if(this.Gameplay.isStockmarketRound())
		{ 
			System.out.print(this.Gameplay.isOperationRound());
			// clear the corppos on stockmarket
			this.getStockmarket().getCorporationPositions().clear(); 
			for (Corporation corp : this.Corporations) {
				// if first cert (president) is sold to player
				if (corp.getCertificates().get(0).getOwner() < 10) {
					// take marker to stockmarket corppos list
					this.getStockmarket().getCorporationPositions().add(corp.getMarker()); 
				}
			}
			//sort corpos on stockmarket list
			Stockmarket.sortCorporationPosition(this.getStockmarket().getCorporationPositions()); 

			for (CorporationPosition cp : this.getStockmarket().getCorporationPositions()) {
				if (cp.getCorp().getSoldShares() == 100) {
					cp.getCorp().getMarker().setUp(this);
				} // set up corp markers for fully sold corps
			}

			this.getStockmarket().getCorporationPositions().clear(); // clear the corppos on stockmarket (AGAIN)
			for (Corporation corp : this.Corporations) {
				if (corp.getInitialShares() <= 40) { // open corps with min 60% sold shares AND copy markers corppos to
														// stockmarket corppos list
					corp.setOpen(true);
					this.getStockmarket().getCorporationPositions().add(corp.getMarker());
				}
			}
			// sort corppos on stockmarket list (AGAIN)
			Stockmarket.sortCorporationPosition(this.getStockmarket().getCorporationPositions()); 
			for (CorporationPosition pos : this.Stockmarket.getCorporationPositions()) {
				this.Gameplay.getOperationroundCorpOrder().add(pos.getCorp());
			}
		}
		this.getGameplay().setOperationRound(true);
		this.getGameplay().setStockmarketRound(false);
		System.out.print(this.Gameplay.isOperationRound());
		this.buildGraphics();
		this.getTP().setSelectedIndex(1);
	}

	public void switchToStockMarketRound() {
		this.Gameplay.getOperationroundCorpOrder().clear();
		this.getGameplay().setOperationRound(false);
		this.getGameplay().setStockmarketRound(true);
		this.buildGraphics();
		this.getTP().setSelectedIndex(1);
	}

	public void asave() {
		try {
			// write object to file
			//FileOutputStream fos = new FileOutputStream("/home/andreas/eclipse-workspace/play18xx/save.1830");
			FileOutputStream fos = new FileOutputStream("../save.1830");
			ObjectOutputStream oos = new ObjectOutputStream(fos);
			oos.writeObject(this);
			oos.close();

		} catch (FileNotFoundException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
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
