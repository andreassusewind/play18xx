package com.play18xx.material;

import java.io.File;
import java.io.Serializable;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlElementWrapper;
import javax.xml.bind.annotation.XmlRootElement;

@XmlRootElement
@XmlAccessorType(value = XmlAccessType.FIELD)
public class Stockmarket implements Serializable {
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	@XmlElement
	private int[][] Code;
	@XmlElement
	private int[][] Value;
	@XmlElement
	private int Rows;
	@XmlElement
	private int Cols;
	@XmlElementWrapper(name = "CorporationPositions") 
	@XmlElement
	private List<CorporationPosition> CorporationPositions = new ArrayList<CorporationPosition>();

	public Stockmarket() {
	}

	public Stockmarket(String File) {
		Path FilePath = new File(File).toPath();

		String[] stringArray = { "" };
		try {
			List<String> stringList = Files.readAllLines(FilePath);
			stringArray = stringList.toArray(new String[] {});
		} catch (Exception e) {
			System.out.println("Config file for Stockmarket not found");
		}
		String[] columnDetail = stringArray[0].split("\t");

		this.Rows = (stringArray.length - 1) / 2;
		this.Cols = columnDetail.length;
		int[][] stockmarketarray = new int[this.Rows][this.Cols];
		for (int i = 0; i < ((stringArray.length - 1) / 2); i++) {
			columnDetail = stringArray[i].split("\t");
			for (int j = 0; j < columnDetail.length; j++) {
				stockmarketarray[i][j] = Integer.parseInt(columnDetail[j]);
			}
		}
		this.Code = stockmarketarray;

		int[][] stockmarketvalues = new int[this.Rows][this.Cols];
		for (int i = ((stringArray.length + 1) / 2); i < stringArray.length; i++) {
			columnDetail = stringArray[i].split("\t");
			for (int j = 0; j < columnDetail.length; j++) {
				stockmarketvalues[i - ((stringArray.length + 1) / 2)][j] = Integer.parseInt(columnDetail[j]);
			}
		}
		this.Value = stockmarketvalues;
	}

	public int getBuyoption(Basic basic, Corporation corp, Player player) {
		/*
		 * BUY OPTIONS: : Player has not enough money for min(Bank or initial share)
		 * 
		 * 10: Marker lies on a brown field on stock market (not a return value -> 11,12,13)
		 * 11: 10-> only initial stock (NOT ALLOWED)
		 * 12: 10-> only bank stock
		 * 13: 10-> bank AND initial stock (NOT ALLOWED)
		 * 
		 * 29: Shares are lying on Bank Stock AND player has enough money
		 * 
		 * 37: Shares are lying on Initial stock AND on Bank Stock (AND President share is NOT lying on Initial Stock) AND player has enough money
		 * 
		 * 38: Shares are lying on Initial stock AND President share is NOT lying on Initial Stock AND player has enough money
		 * 
		 * 39: Shares are lying on Initial stock AND President share is lying on Initial Stock AND player has enough money
		 * 
		 * : CorporationPosition is in Brown Colored Area -> Buy as much as you like :
		 * 51 + CorporationPosition is in Orange or lower Colored Area -> Buy more than
		 * 60% : 52 + CorporationPosition is in Yellow or lower Colored Area -> Buy more
		 * than MaxCertificate limit : 37: Normal share is lying in Initial Stock or
		 * Bank Pool -> buy from bank or initial (Drop-Down-List) 38: Normal share is
		 * lying in Initial Stock -> buy from initial 39: President share is lying in
		 * Initial Stock -> set Par Value (Drop-Down-List) 48: Player has bought
		 * BO-Private -> get President share of BaO; called by Private.BuyPrivate; not
		 * implemented here
		 * 
		 * 51: player has 60% of Corp 52: player exceeds MaxCertificates : player has
		 * sold share from Corp -> BankPool Certificates has player number : no normal
		 * share in Initial Stock or Bank Pool -> 38
		 * 
		 * 89: If the player holds 60% of an corporation it could be bought any further
		 * -- is overruled by...
		 * 
		 * 91: If the player has sold a Certificate of the Corporation it could not be bought any Certificate of the Corporation
		 * 
		 * 95: If the game begins (0 on StockmarketRoundCounter) there is no by option until the Privates are sold
		 * 
		 * 97: Player exceeds the shares limit
		 */

		if(basic.getGameplay().getStockmarketRoundCounter()==0) { return 95;}
		
		if(player.getSoldCorps().contains(corp.getIndex())) { return 91; }
		
		if(player.getPlayersCertsCount(basic) >= basic.getGameplay().getMaxCertificates()) { return 97; }
		
		if(corp.getPlayerShares(player) >= 60 && corp.getMarker().CountToCorpLimit(basic)) { return 89; }
		
		if(!corp.getMarker().CountToBuyLimit(basic)) {
			if(corp.getInitialStock().size() > 0) {
				if(corp.getBankStock().size() > 0 
					&& player.getMoney() >= Math.min(corp.getMarker().getValue(), corp.getShareParValue())) 
					{ return 13; }
				else                               { if(player.getMoney() >= corp.getShareParValue()) {return 11;} }
			}
			else {
				if(corp.getBankStock().size() > 0 && player.getMoney() >= corp.getMarker().getValue()) { return 12; }
			}
		}
		
		if(corp.getInitialStock().size() > 0 ) {
			if(corp.getInitialStock().get(0).isPresident()  
					&& player.getMoney() >= 134) { return 39; }
			
			if(!corp.getInitialStock().get(0).isPresident() 
					&& corp.getBankStock().size() > 0
					&& player.getMoney() >= Math.min(corp.getMarker().getValue(), corp.getShareParValue()))
					{ return 37;}
			
			if(!corp.getInitialStock().get(0).isPresident() 
					&& player.getMoney() >= corp.getShareParValue() ) { return 38; }
		}

		if(corp.getBankStock().size() > 0 && player.getMoney() >= corp.getMarker().getValue()) 
			{ return 29; }
		
		return 99;
	}

	public int getSelloption(Basic basic, Corporation corp, Player player) {
		/*
		 * SELL OPTIONS: :
		 * 
		 * 49: Player has certs of corp
		 * 
		 * 91: Note that the owner of the CA private company receives a free 
		 * share (10%) of the PRR. This share cannot be sold until the
		 * president’s certificate has been purchased, thereby setting the
		 * value of the PRR share. 
		 * --> Implemented for all Corporations; if the President share is not sold
		 * from initial stock no other share could sold
		 * 
		 * 93: If the corporation has min 50% shares in the BankPool
		 * 
		 * 95: If the game begins there is no by option until the Privates are sold
		 * 
		 * 
		 * 98: Certificates may not be sold in the first stock round
		 * 
		 */
		
		if(basic.getGameplay().getStockmarketRoundCounter()==0) {return 95;}
		if(basic.getGameplay().getStockmarketRoundCounter()==1) {return 98;}
		if(corp.getBankStockShare() >= 50) {return 93;}
		if(corp.getCertificates().get(0).getOwner() == 91) {return 91;}
		
		for(Certificate cert : corp.getCertificates()) {if(cert.getOwner() == player.getIndex()) {return 49;}}
		
		return 99;
	}

	public void pass(Basic basic) {
		basic.getGameplay().setCurrentPlayer((basic.getGameplay().getCurrentPlayer() + 1) % basic.getPlayers().size());
		basic.getGameplay().setPassNumber(basic.getGameplay().getPassNumber() + 1);
	}

	public void sortCorporationPosition() {
		Collections.sort(CorporationPositions, CorporationPosition.StackComparator);
		Collections.sort(CorporationPositions, CorporationPosition.RowComparator);
		Collections.sort(CorporationPositions, CorporationPosition.ValueComparator);
	}

	public static void sortaCorporationPosition(List<CorporationPosition> CorporationPositions) {
		Collections.sort(CorporationPositions, CorporationPosition.StackComparator);
		Collections.sort(CorporationPositions, CorporationPosition.RowComparator);
		Collections.sort(CorporationPositions, CorporationPosition.ValueComparator);
	}

	public int[][] getCode() {
		return Code;
	}

	public void setCode(int[][] code) {
		Code = code;
	}

	public int[][] getValue() {
		return Value;
	}

	public void setValue(int[][] value) {
		Value = value;
	}

	public int getRows() {
		return Rows;
	}

	public void setRows(int rows) {
		Rows = rows;
	}

	public int getCols() {
		return Cols;
	}

	public void setCols(int cols) {
		Cols = cols;
	}

	public List<CorporationPosition> getCorporationPositions() {
		return CorporationPositions;
	}

	public void setCorporationPositions(List<CorporationPosition> corporationPositions) {
		CorporationPositions = corporationPositions;
	}
	
	public void checkForOpenedCorps(List<Corporation> Corporations) {
		this.getCorporationPositions().clear();
		for(Corporation corp : Corporations) {
			if (corp.getInitialShares() <= 40) { 
				corp.setOpen(true);
				this.getCorporationPositions().add(corp.getMarker());
			}
		}
		sortCorporationPosition();
	}
	
	public void checkForFullSoldCorps(Basic basic, List<Corporation> Corporations) {
		this.getCorporationPositions().clear();
		for (Corporation corp : Corporations) {
			if (corp.getCertificates().get(0).getOwner() < 10) {
				this.getCorporationPositions().add(corp.getMarker()); 
			}
		}
		sortCorporationPosition();
		for (CorporationPosition cp : this.getCorporationPositions()) {
			if (cp.getCorp().getSoldShares() == 100) { cp.getCorp().getMarker().setUp(basic); }
		}
	}
}
