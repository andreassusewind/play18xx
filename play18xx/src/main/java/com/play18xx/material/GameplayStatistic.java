package com.play18xx.material;

import javax.xml.bind.annotation.XmlElement;

public class GameplayStatistic {
	@XmlElement
	private int StockmarketRoundCounter;
	@XmlElement
	private int OperationroundCounter;
	@XmlElement
	private int PlayerMoney;
	@XmlElement
	private int CorporationMoney;
	@XmlElement
	private int FreeFlowMoney;
	
	public GameplayStatistic() {
		
	}

	public GameplayStatistic(Basic basic) {
		this.StockmarketRoundCounter = basic.getGameplay().getStockmarketRoundCounter();
		this.OperationroundCounter   = basic.getGameplay().getOperationroundCounter();
		int pm = 0;
		for(Player player : basic.getPlayers()) {
			pm = pm + player.getMoney();
		}
		this.PlayerMoney = pm;
		int cm = 0;
		for(Corporation corp : basic.getCorporations()) {
			cm = cm + corp.getMoney();
		}
		this.CorporationMoney = cm;
		this.FreeFlowMoney = cm + pm;
	}

	public int getStockmarketRoundCounter() {
		return StockmarketRoundCounter;
	}

	public void setStockmarketRoundCounter(int stockmarketRoundCounter) {
		StockmarketRoundCounter = stockmarketRoundCounter;
	}

	public int getOperationroundCounter() {
		return OperationroundCounter;
	}

	public void setOperationroundCounter(int operationroundCounter) {
		OperationroundCounter = operationroundCounter;
	}

	public int getPlayerMoney() {
		return PlayerMoney;
	}

	public void setPlayerMoney(int playerMoney) {
		PlayerMoney = playerMoney;
	}

	public int getCorporationMoney() {
		return CorporationMoney;
	}

	public void setCorporationMoney(int corporationMoney) {
		CorporationMoney = corporationMoney;
	}

	public int getFreeFlowMoney() {
		return FreeFlowMoney;
	}

	public void setFreeFlowMoney(int freeFlowMoney) {
		FreeFlowMoney = freeFlowMoney;
	}
}
