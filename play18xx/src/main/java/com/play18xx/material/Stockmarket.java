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
			System.out.println("Config file for Corporations not found");
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
		 * 95: If the game begins there is no by option until the Privates are sold
		 * 
		 * 97: If you sell certificates in a corporation, you may not later buy certificates in that corporation in the same stock round.
		 */

		int Buyoption = 99;
		if(basic.getGameplay().getStockmarketRoundCounter()==0) {return 95;}

		if (corp.getInitialStock().size() > 0) { // if shares are on the initial stock
			if (corp.getInitialStock().get(0).isPresident()) { // 39: President share is lying in Initial Stock -> set
																// Par Value (Drop-Down-List)
				Buyoption = 39;
			} else { // 38: Normal share is lying in Initial Stock or Bank Pool -> buy from bank or
						// initial (Drop-Down-List)
				Buyoption = 38;
			}
		}
		
		
		
		if(player.soldCorp(corp)) { return 97; }

		return (Buyoption);
	}

	public int getSelloption(Basic basic, Corporation corp, Player player) {
		/*
		 * SELL OPTIONS: :
		 * 
		 * 49: Player has certs of corp
		 * 
		 * 95: If the game begins there is no by option until the Privates are sold
		 * 
		 * 
		 * 98: Certificates may not be sold in the first stock round
		 * 
		 */
		int Selloption = 99;
		
		if(basic.getGameplay().getStockmarketRoundCounter()==0) {return 95;}
		if(basic.getGameplay().getStockmarketRoundCounter()==1) {return 98;}
		
		for(Certificate cert : player.getCertificates()) { if( cert.getCorporation().equals(corp) ) { return 49; } }


		return (Selloption);
	}

	public void pass(Basic basic) {
		basic.getGameplay().setCurrentPlayer((basic.getGameplay().getCurrentPlayer() + 1) % basic.getPlayers().size());
		basic.getGameplay().setPassNumber(basic.getGameplay().getPassNumber() + 1);
	}

	public static void sortCorporationPosition(List<CorporationPosition> CorporationPositions) {
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
}
