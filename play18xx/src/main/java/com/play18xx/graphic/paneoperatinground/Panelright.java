package com.play18xx.graphic.paneoperatinground;

import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Insets;
import java.awt.Point;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.List;

import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.event.DocumentEvent;
import javax.swing.event.DocumentListener;

import com.play18xx.material.Basic;
import com.play18xx.material.Certificate;
import com.play18xx.material.Corporation;
import com.play18xx.material.Dividend;
import com.play18xx.material.Player;
import com.play18xx.material.Private;
import com.play18xx.material.Station;
import com.play18xx.material.Train;

public class Panelright extends JPanel{
	private final static int tabpos = 1;

	JPanel player = new JPanel();
	int lastincome;
	int trainbuyindex;
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	public void setPanel(Basic basic) {
		this.setLayout(new GridBagLayout());
	}

	public void setPanelTile(Basic basic, Corporation corp) {
		this.setLayout(new GridBagLayout());
		GridBagConstraints c = new GridBagConstraints();

		JButton tile80 = new JButton("River Tile - 80");
		c.gridx = 0;
		c.gridy = 1;
		c.gridwidth = 1;
		c.anchor = GridBagConstraints.CENTER;
		c.insets = new Insets(10, 0, 0, 0);
		tile80.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				corp.decreaseMoney(80);
				corp.setTileDone(true);
				basic.getTP().getPOR().getPanelright().removeAll();
				basic.getTP().getPOR().getPanemiddel().removeAll();
				basic.getTP().getPOR().getPanemiddel().setPanelCorporation(basic, corp);
				basic.buildGraphics();
				basic.getTP().setSelectedIndex(tabpos);
			}
		});
		this.add(tile80, c);

		JButton tile120 = new JButton("Mountain Tile - 120");
		c.gridx = 0;
		c.gridy = 2;
		c.gridwidth = 1;
		c.anchor = GridBagConstraints.CENTER;
		c.insets = new Insets(10, 0, 0, 0);
		tile120.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				corp.decreaseMoney(120);
				corp.setTileDone(true);
				basic.getTP().getPOR().getPanelright().removeAll();
				basic.getTP().getPOR().getPanemiddel().removeAll();
				basic.getTP().getPOR().getPanemiddel().setPanelCorporation(basic, corp);
				basic.buildGraphics();
				basic.getTP().setSelectedIndex(tabpos);
			}
		});
		this.add(tile120, c);

		JButton non = new JButton("no tile or free of charge");
		c.gridx = 0;
		c.gridy = 3;
		c.gridwidth = 1;
		c.anchor = GridBagConstraints.CENTER;
		c.insets = new Insets(10, 0, 0, 0);
		non.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				corp.setTileDone(true);
				basic.getTP().getPOR().getPanelright().removeAll();
				basic.getTP().getPOR().getPanemiddel().removeAll();
				basic.getTP().getPOR().getPanemiddel().setPanelCorporation(basic, corp);
				basic.buildGraphics();
				basic.getTP().setSelectedIndex(tabpos);
			}
		});
		this.add(non, c);
		
		for(Private priv : basic.getPrivates()) {
			if(priv.getName().equals("DH") && priv.getOwner() == corp.getIndex()+30) {
				int stationcost = 9999;
				Station Stationsave = new Station(0);
				for (Station station : corp.getStations()) {
					if (station.isBuild()) {}
					else {
						stationcost = station.getCost();
						Stationsave = station;
						break;
					}
				}

				Station stationtouse = Stationsave;

				
				JButton dh = new JButton("DH special function");
				c.gridy = 4;
				dh.addActionListener(new ActionListener() {
					public void actionPerformed(ActionEvent e) {
						corp.decreaseMoney(120);
						stationtouse.setBuild(true);
						
						corp.setTileDone(true);
						basic.getTP().getPOR().getPanelright().removeAll();
						basic.getTP().getPOR().getPanemiddel().removeAll();
						basic.getTP().getPOR().getPanemiddel().setPanelCorporation(basic, corp);
						basic.buildGraphics();
						basic.getTP().setSelectedIndex(tabpos);
					}
				});
				if(stationcost == 9999) dh.setEnabled(false);
				this.add(dh, c);
				
				
			}
		}
	}
	
	public void setPanelStation(Basic basic, Corporation corp) {
		this.setLayout(new GridBagLayout());
		GridBagConstraints c = new GridBagConstraints();

		int stationcost = 9999;
		Station Stationsave = new Station(0);
		for (Station station : corp.getStations()) {
			if (station.isBuild()) {}
			else {
				stationcost = station.getCost();
				Stationsave = station;
				break;
			}
		}

		Station stationtouse = Stationsave;

		if (stationcost == 9999) {
			JLabel label = new JLabel("No station could be build");
			c.gridx = 0;
			c.gridy = 1;
			c.gridwidth = 1;
			c.anchor = GridBagConstraints.CENTER;
			c.insets = new Insets(10, 0, 0, 0);
			this.add(label, c);
		} 
		else {
			JLabel label = new JLabel("Build station for " + stationcost);
			c.gridx = 0;
			c.gridy = 1;
			c.gridwidth = 1;
			c.anchor = GridBagConstraints.CENTER;
			c.insets = new Insets(10, 0, 0, 0);
			this.add(label, c);

			JButton station = new JButton("Build station");
			c.gridx = 0;
			c.gridy = 2;
			c.gridwidth = 1;
			c.anchor = GridBagConstraints.CENTER;
			c.insets = new Insets(10, 0, 0, 0);
			station.addActionListener(new ActionListener() {
				public void actionPerformed(ActionEvent e) {
					corp.decreaseMoney(stationtouse.getCost());
					stationtouse.setBuild(true);
					corp.setStationDone(true);
					basic.getTP().getPOR().getPanelright().removeAll();
					basic.getTP().getPOR().getPanemiddel().removeAll();
					basic.getTP().getPOR().getPanemiddel().setPanelCorporation(basic, corp);
					basic.buildGraphics();
					basic.getTP().setSelectedIndex(tabpos);
				}
			});
			this.add(station, c);
		}
		
		JButton non = new JButton("build no station");
		c.gridx = 0;
		c.gridy = 3;
		c.gridwidth = 1;
		c.anchor = GridBagConstraints.CENTER;
		c.insets = new Insets(10, 0, 0, 0);
		non.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				corp.setStationDone(true);
				basic.getTP().getPOR().getPanelright().removeAll();
				basic.getTP().getPOR().getPanemiddel().removeAll();
				basic.getTP().getPOR().getPanemiddel().setPanelCorporation(basic, corp);
				basic.buildGraphics();
				basic.getTP().setSelectedIndex(tabpos);
			}
		});
		this.add(non, c);

	}
	
	public void setPanelDividend(Basic basic, Corporation corp) {
		this.setLayout(new GridBagLayout());
		GridBagConstraints c = new GridBagConstraints();
		
		if (corp.getTrains().size() > 0) {
			JLabel label = new JLabel(corp.getName() + " has " + corp.getTrains().size() + " Train(s)");
			c.gridx = 0;
			c.gridy = 0;
			c.gridwidth = 4;
			c.anchor = GridBagConstraints.CENTER;
			c.insets = new Insets(10, 0, 0, 0);
			this.add(label, c);

			String trains = "Distances:  ";
			for (Train train : corp.getTrains()) {
				trains = trains + train.getDistancePrimary() + "    ";
			}
			label = new JLabel(trains);
			c.gridx = 0;
			c.gridy = 1;
			c.gridwidth = 4;
			c.anchor = GridBagConstraints.CENTER;
			c.insets = new Insets(10, 0, 0, 0);
			this.add(label, c);

			label = new JLabel("Trains run with an income of: ");
			c.gridx = 0;
			c.gridy = 2;
			c.gridwidth = 2;
			c.anchor = GridBagConstraints.CENTER;
			c.insets = new Insets(40, 0, 0, 0);
			this.add(label, c);

			if(corp.getDividends().size()>0) 
				{lastincome = corp.getDividends().get(corp.getDividends().size()-1).getDividend();} 
			else {lastincome = 50;}
			JTextField income = new JTextField(String.valueOf(lastincome), 5);
			c.gridx = 0;
			c.gridy = 3;
			c.gridwidth = 4;
			c.anchor = GridBagConstraints.CENTER;
			c.insets = new Insets(10, 0, 0, 0);
			this.add(income, c);
			int[] revenue = corp.getPlayerShares();
			int[] pincome = corp.getPlayerIncome(Integer.parseInt((String) income.getText()));
			DocumentListener incomelistener = new DocumentListener() {
				@Override
				public void insertUpdate(DocumentEvent e) {
					lastincome = Integer.parseInt(income.getText());
					basic.getTP().getPOR().getPanelright().remove(player);
					int[] revenue = corp.getPlayerShares();
					int[] pincome = corp.getPlayerIncome(Integer.parseInt((String) income.getText()));
					player = setPanelDividendtoPlayer(basic, revenue, pincome);
					c.gridx = 0;
					c.gridy = 6;
					c.gridwidth = 4;
					c.anchor = GridBagConstraints.CENTER;
					basic.getTP().getPOR().getPanelright().add(player,c);
				}

				@Override
				public void removeUpdate(DocumentEvent e) {
					lastincome = Integer.parseInt(income.getText());
				}

				@Override
				public void changedUpdate(DocumentEvent e) {
					lastincome = Integer.parseInt(income.getText());
					basic.getTP().getPOR().getPanelright().remove(player);
					int[] revenue = corp.getPlayerShares();
					int[] pincome = corp.getPlayerIncome(Integer.parseInt((String) income.getText()));
					player = setPanelDividendtoPlayer(basic, revenue, pincome);
					c.gridx = 0;
					c.gridy = 6;
					c.gridwidth = 4;
					c.anchor = GridBagConstraints.CENTER;
					basic.getTP().getPOR().getPanelright().add(player,c);
				}
			};
			income.getDocument().addDocumentListener(incomelistener);


			JButton toplayer = new JButton("income to player");
			c.gridx = 0;
			c.gridy = 4;
			c.gridwidth = 4;
			c.anchor = GridBagConstraints.CENTER;
			c.insets = new Insets(10, 0, 0, 0);
			toplayer.addActionListener(new ActionListener() {
				public void actionPerformed(ActionEvent e) {
					int[] pincome = corp.getPlayerIncome(Integer.parseInt((String) income.getText()));
					for(int i=0; i<pincome.length; i++) {
						basic.getPlayers().get(i).increaseMoney(pincome[i]);
					}
					corp.increaseMoney((int) ( ( (double)corp.getBankStockShare() / 100 ) * Integer.parseInt((String) income.getText())));
					corp.getMarker().setRight(basic);
					corp.getDividends().add(new Dividend(
							Integer.parseInt((String) income.getText()), 
							corp.getTrains(),
							basic.getGameplay().getStockmarketRoundCounter(),
							true));
					corp.setDividendDone(true);
					basic.getTP().getPOR().getPanelright().removeAll();
					basic.getTP().getPOR().getPanemiddel().removeAll();
					basic.getTP().getPOR().getPanemiddel().setPanelCorporation(basic, corp);
					basic.buildGraphics();
					basic.getTP().setSelectedIndex(tabpos);
				}
			});
			this.add(toplayer, c);

			JButton tocorp = new JButton("income to corporation");
			c.gridx = 0;
			c.gridy = 5;
			c.gridwidth = 4;
			c.anchor = GridBagConstraints.CENTER;
			c.insets = new Insets(10, 0, 40, 0);
			tocorp.addActionListener(new ActionListener() {
				public void actionPerformed(ActionEvent e) {
					corp.increaseMoney(Integer.parseInt((String) income.getText()));
					corp.getMarker().setLeft(basic);
					corp.getDividends().add(new Dividend(
							Integer.parseInt((String) income.getText()), 
							corp.getTrains(),
							basic.getGameplay().getStockmarketRoundCounter(),
							false));
					corp.setDividendDone(true);
					basic.getTP().getPOR().getPanelright().removeAll();
					basic.getTP().getPOR().getPanemiddel().removeAll();
					basic.getTP().getPOR().getPanemiddel().setPanelCorporation(basic, corp);
					basic.buildGraphics();
					basic.getTP().setSelectedIndex(tabpos);
				}
			});
			this.add(tocorp, c);

			player = setPanelDividendtoPlayer(basic, revenue, pincome);
			
			c.gridx = 0;
			c.gridy = 6;
			c.gridwidth = 4;
			c.anchor = GridBagConstraints.CENTER;
			this.add(player, c);

		} else {
			JLabel label = new JLabel("Corporation has no trains");
			c.gridx = 0;
			c.gridy = 0;
			c.gridwidth = 1;
			c.anchor = GridBagConstraints.CENTER;
			c.insets = new Insets(10, 0, 0, 0);
			this.add(label, c);

			JButton nodiv = new JButton("Trains could not run");
			c.gridx = 0;
			c.gridy = 2;
			c.gridwidth = 1;
			c.anchor = GridBagConstraints.CENTER;
			c.insets = new Insets(10, 0, 0, 0);
			nodiv.addActionListener(new ActionListener() {
				public void actionPerformed(ActionEvent e) {
					corp.getMarker().setLeft(basic);
					corp.setDividendDone(true);
					basic.getTP().getPOR().getPanelright().removeAll();
					basic.getTP().getPOR().getPanemiddel().removeAll();
					basic.getTP().getPOR().getPanemiddel().setPanelCorporation(basic, corp);
					basic.buildGraphics();
					basic.getTP().setSelectedIndex(tabpos);
				}
			});
			this.add(nodiv, c);
		}
	}

	public JPanel setPanelDividendtoPlayer(Basic basic, int[] revenue, int[] pincome) {
		player = new JPanel();
		player.setLayout(new GridBagLayout());
		GridBagConstraints c = new GridBagConstraints();
		
		for (int i = 0; i < revenue.length; i++) {
			JLabel label = new JLabel(basic.getPlayers().get(i).getName());
			c.gridx = 0;
			c.gridy = 6 + i;
			c.gridwidth = 1;
			c.anchor = GridBagConstraints.CENTER;
			c.insets = new Insets(10, 0, 0, 0);
			player.add(label, c);

			label = new JLabel("    " + revenue[i] + " %");
			c.gridx = 1;
			c.gridy = 6 + i;
			c.gridwidth = 1;
			c.anchor = GridBagConstraints.CENTER;
			c.insets = new Insets(10, 0, 0, 0);
			player.add(label, c);

			label = new JLabel("   -->   ");
			c.gridx = 2;
			c.gridy = 6 + i;
			c.gridwidth = 1;
			c.anchor = GridBagConstraints.CENTER;
			c.insets = new Insets(10, 0, 0, 0);
			player.add(label, c);

			label = new JLabel("" + pincome[i]);
			c.gridx = 3;
			c.gridy = 6 + i;
			c.gridwidth = 1;
			c.anchor = GridBagConstraints.CENTER;
			c.insets = new Insets(10, 0, 0, 0);
			player.add(label, c);
		}
		
		return player;
	}

	public void setPanelTrain(Basic basic, Corporation corp) {
		this.setLayout(new GridBagLayout());
		GridBagConstraints c = new GridBagConstraints();
		
		JLabel label = new JLabel("Buy a " + basic.getGameplay().getTrains().get(0).getDistancePrimary()
				+ " train from the stack for " + basic.getGameplay().getTrains().get(0).getCost());
		c.gridx = 0;
		c.gridy = 1;
		c.gridwidth = 2;
		c.anchor = GridBagConstraints.CENTER;
		c.insets = new Insets(10, 0, 0, 0);
		this.add(label, c);

		JButton stack = new JButton("Buy from stack");
		c.gridx = 0;
		c.gridy = 2;
		c.gridwidth = 2;
		c.anchor = GridBagConstraints.CENTER;
		c.insets = new Insets(10, 0, 0, 0);
		stack.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				corp.decreaseMoney(basic.getGameplay().getTrains().get(0).getCost());
				corp.getTrains().add(basic.getGameplay().getTrains().get(0));
				basic.getGameplay().setLastTrain(basic.getGameplay().getTrains().get(0));
				basic.getGameplay().getTrains().remove(0);
				basic.getGameplay().rustTrains(basic);
				basic.getGameplay().closePrivates(basic);
				
				
				basic.getTP().getPOR().getPanelright().removeAll();
				basic.getTP().getPOR().getPanelright().setPanelTrain(basic, corp);
				basic.getTP().getPOR().getPanemiddel().removeAll();
				
				List<Corporation> corps = basic.getGameplay().exceedTrains(basic);
				if(corps.size()>0) {basic.getTP().getPOR().getPanemiddel().setPanelExceedTrain(basic, corps);
									basic.getTP().getPOR().getPanelleft().removeAll(); }
				else                basic.getTP().getPOR().getPanemiddel().setPanelCorporation(basic, corp);

				basic.buildGraphics();
				basic.getTP().setSelectedIndex(tabpos);
			}
		});
		if(basic.getGameplay().getTrains().get(0).getCost() > corp.getMoney()) {stack.setEnabled(false);}
		else stack.setEnabled(true);
		this.add(stack, c);

		label = new JLabel("Buy a train from");
		c.gridx = 0;
		c.gridy = 3;
		c.gridwidth = 2;
		c.anchor = GridBagConstraints.CENTER;
		c.insets = new Insets(50, 0, 0, 0);
		this.add(label, c);

		String[] corpnames = new String[basic.getCorporations().size()];
		for (Corporation corps : basic.getCorporations()) {
			corpnames[corps.getIndex()] = corps.getName();
		}
		JComboBox<Object> corpbox = new JComboBox<Object>(corpnames);
		corpbox.setSelectedIndex(trainbuyindex);

		Corporation buycorp = basic.getCorporations().get(corpbox.getSelectedIndex());
		if (buycorp.getTrains().size() == 0) {
			label = new JLabel(buycorp.getName() + " has no train");
			c.gridx = 0;
			c.gridy = 5;
			c.gridwidth = 2;
			c.anchor = GridBagConstraints.CENTER;
			c.insets = new Insets(10, 0, 0, 0);
			this.add(label, c);
			label = new JLabel(" ");
			c.gridx = 0;
			c.gridy = 6;
			c.gridwidth = 2;
			c.anchor = GridBagConstraints.CENTER;
			c.insets = new Insets(107, 0, 0, 0);
			this.add(label, c);
		} else {
			label = new JLabel("Select train from " + buycorp.getName());
			c.gridx = 0;
			c.gridy = 5;
			c.gridwidth = 2;
			c.anchor = GridBagConstraints.CENTER;
			c.insets = new Insets(10, 0, 0, 0);
			this.add(label, c);

			String[] trainlist = new String[buycorp.getTrains().size()];
			int traincounter = 0;
			for (Train train : buycorp.getTrains()) {
				trainlist[traincounter] = "" + train.getDistancePrimary();
				traincounter = traincounter + 1;
			}

			JComboBox<Object> trainbox = new JComboBox<Object>(trainlist);
			trainbox.setSelectedIndex(0);
			c.gridx = 0;
			c.gridy = 6;
			c.gridwidth = 2;
			c.anchor = GridBagConstraints.CENTER;
			c.insets = new Insets(10, 0, 0, 0);
			this.add(trainbox, c);

			label = new JLabel("Value: ");
			JTextField trainvalue = new JTextField("50", 4);
			trainvalue.setText("50");
			c.gridx = 1;
			c.gridy = 7;
			c.gridwidth = 1;
			c.anchor = GridBagConstraints.CENTER;
			c.insets = new Insets(10, 0, 0, 0);
			this.add(label, c);
			c.gridx = 1;
			c.gridy = 8;
			c.gridwidth = 1;
			c.anchor = GridBagConstraints.CENTER;
			c.insets = new Insets(10, 0, 0, 0);
			this.add(trainvalue, c);

			JButton corpbuy = new JButton("Buy from " + buycorp.getName());
			c.gridx = 0;
			c.gridy = 9;
			c.gridwidth = 2;
			c.anchor = GridBagConstraints.CENTER;
			c.insets = new Insets(10, 0, 0, 0);
			corpbuy.addActionListener(new ActionListener() {
				public void actionPerformed(ActionEvent e) {
					corp.decreaseMoney(Integer.valueOf(trainvalue.getText()));
					buycorp.increaseMoney(Integer.valueOf(trainvalue.getText()));
					corp.getTrains().add(buycorp.getTrains().get(trainbox.getSelectedIndex()));
					buycorp.getTrains().remove(trainbox.getSelectedIndex());
					basic.getTP().getPOR().getPanelright().removeAll();
					basic.getTP().getPOR().getPanelright().setPanelTrain(basic, corp);
					basic.getTP().getPOR().getPanemiddel().removeAll();
					basic.getTP().getPOR().getPanemiddel().setPanelCorporation(basic, corp);
					basic.buildGraphics();
					basic.getTP().setSelectedIndex(tabpos);
				}
			});
			if(basic.getGameplay().getMaxTrainLimit() <= corp.getTrains().size()) {corpbuy.setEnabled(false);}
			else corpbuy.setEnabled(true);
			this.add(corpbuy, c);
		}
		ActionListener corpboxActionListener = new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				trainbuyindex = corpbox.getSelectedIndex();
				basic.getTP().getPOR().getPanelright().removeAll();
				basic.getTP().getPOR().getPanelright().setPanelTrain(basic, corp);
				basic.buildGraphics();
				basic.getTP().setSelectedIndex(tabpos);
			}
        };
        corpbox.addActionListener(corpboxActionListener);
		c.gridx = 0;
		c.gridy = 4;
		c.gridwidth = 2;
		c.anchor = GridBagConstraints.CENTER;
		c.insets = new Insets(10, 0, 0, 0);
		this.add(corpbox, c);
		
		
		if(basic.getGameplay().getBankTrains().size() > 0) {
			label = new JLabel("Buy train from open market");
			c.gridx = 0;
			c.gridy = 10;
			c.gridwidth = 2;
			c.anchor = GridBagConstraints.CENTER;
			c.insets = new Insets(50, 0, 0, 0);
			this.add(label, c);
			
			
			String openmarkettrains[] = new String[basic.getGameplay().getBankTrains().size()];
			for(int i=0; i<openmarkettrains.length; i++) {
				openmarkettrains[i] = basic.getGameplay().getBankTrains().get(i).getDistancePrimary() + " for " +
							basic.getGameplay().getBankTrains().get(i).getCost();
			}
			@SuppressWarnings({ "rawtypes", "unchecked" })
			JComboBox openmarketbox = new JComboBox(openmarkettrains);
			c.gridx = 0;
			c.gridy = 11;
			c.gridwidth = 2;
			c.anchor = GridBagConstraints.CENTER;
			c.insets = new Insets(10, 0, 0, 0);
			this.add(openmarketbox, c);
			
			
			JButton openmarket = new JButton("Buy from open market");
			c.gridx = 0;
			c.gridy = 12;
			c.gridwidth = 2;
			c.anchor = GridBagConstraints.CENTER;
			c.insets = new Insets(10, 0, 0, 0);
			ActionListener openmarketActionListener = new ActionListener() {
				@Override
				public void actionPerformed(ActionEvent e) {
					Train selectedTrain = basic.getGameplay().getBankTrains().get(openmarketbox.getSelectedIndex());
					
					corp.decreaseMoney(selectedTrain.getCost());
					corp.getTrains().add(selectedTrain);
					
					basic.getGameplay().getBankTrains().remove(openmarketbox.getSelectedIndex());

					
					basic.getTP().getPOR().getPanelright().removeAll();
					basic.getTP().getPOR().getPanelright().setPanelTrain(basic, corp);
					basic.buildGraphics();
					basic.getTP().setSelectedIndex(tabpos);
				}
	        };
	        openmarket.addActionListener(openmarketActionListener);
			this.add(openmarket, c);
		}

		JButton president = new JButton("Buy as president");
		c.gridx = 0;
		c.gridy = 13;
		c.gridwidth = 2;
		c.anchor = GridBagConstraints.CENTER;
		c.insets = new Insets(50, 0, 0, 0);
		ActionListener presidentActionListener = new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				Train train = basic.getGameplay().getTrains().get(0);
				String sellposition = "Trainstack";
				if(basic.getGameplay().getBankTrains().size() > 0) {
					for(int i=0; i<basic.getGameplay().getBankTrains().size(); i++) {
						if(basic.getGameplay().getBankTrains().get(i).getCost() < train.getCost()) {
							train = basic.getGameplay().getBankTrains().get(i);
							sellposition = "Bank Pool";
						}
					}
				}

				Player player = basic.getPlayers().get(corp.getPresident());
				List<Certificate> certs = player.getCertificates(basic);
				int[] checkboxes = new int[certs.size()];
				com.play18xx.graphic.WindowSell.sellfortrain(basic, 
						player, 
						corp, train, sellposition, certs,
						0, checkboxes, new Point(0,0));
				
				basic.getTP().getPOR().getPanelright().removeAll();
				basic.getTP().getPOR().getPanelright().setPanelTrain(basic, corp);
				basic.buildGraphics();
				basic.getTP().setSelectedIndex(tabpos);
			}
        };
        president.addActionListener(presidentActionListener);
		if(basic.getGameplay().getTrains().get(0).getCost() > corp.getMoney()) {president.setEnabled(true);}
		else president.setEnabled(false);
		this.add(president, c);

		JButton non = new JButton("Buy no train");
		c.gridx = 0;
		c.gridy = 14;
		c.gridwidth = 2;
		c.anchor = GridBagConstraints.CENTER;
		c.insets = new Insets(50, 0, 0, 0);
		non.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				corp.setTrainDone(true);
				basic.getTP().getPOR().getPanelright().removeAll();
				basic.getTP().getPOR().getPanemiddel().removeAll();
				basic.getTP().getPOR().getPanemiddel().setPanelCorporation(basic, corp);
				basic.buildGraphics();
				basic.getTP().setSelectedIndex(tabpos);
			}
		});
		this.add(non, c);
		
		
		if(basic.getGameplay().getLastTrain().getDistancePrimary() >= 6 && basic.getGameplay().getTrains().get(0).getDistancePrimary() != 99) {
			JButton diesel = new JButton("Buy Diesel Train - 1100");
			c.gridx = 0;
			c.gridy = 15;
			c.gridwidth = 2;
			c.anchor = GridBagConstraints.CENTER;
			c.insets = new Insets(50, 0, 0, 0);
			diesel.addActionListener(new ActionListener() {
				public void actionPerformed(ActionEvent e) {
					int lasttrain = basic.getGameplay().getTrains().size() - 1;
					corp.decreaseMoney(basic.getGameplay().getTrains().get(lasttrain).getCost());
					corp.getTrains().add(basic.getGameplay().getTrains().get(lasttrain));
					basic.getGameplay().setLastTrain(basic.getGameplay().getTrains().get(lasttrain));
					basic.getGameplay().getTrains().remove(lasttrain);
					basic.getGameplay().rustTrains(basic);
					basic.getGameplay().closePrivates(basic);
					
					
					basic.getTP().getPOR().getPanelright().removeAll();
					basic.getTP().getPOR().getPanelright().setPanelTrain(basic, corp);
					basic.getTP().getPOR().getPanemiddel().removeAll();
					
					List<Corporation> corps = basic.getGameplay().exceedTrains(basic);
					if(corps.size()>0) {basic.getTP().getPOR().getPanemiddel().setPanelExceedTrain(basic, corps);
										basic.getTP().getPOR().getPanelleft().removeAll(); }
					else                basic.getTP().getPOR().getPanemiddel().setPanelCorporation(basic, corp);

					basic.buildGraphics();
					basic.getTP().setSelectedIndex(tabpos);
				}
			});
			this.add(diesel, c);
		
			if(corp.getTrains().size() > 0) {
				String[] trainlist2 = new String[corp.getTrains().size()];
				int traincounter2 = 0;
				for (Train train : corp.getTrains()) {
					trainlist2[traincounter2] = "" + train.getDistancePrimary();
					traincounter2 = traincounter2 + 1;
				}


				JComboBox<Object> trainbox2 = new JComboBox<Object>(trainlist2);
				trainbox2.setSelectedIndex(0);
				c.gridx = 1;
				c.gridy = 16;
				c.gridwidth = 1;
				c.anchor = GridBagConstraints.CENTER;
				c.insets = new Insets(10, 0, 0, 0);
				this.add(trainbox2, c);
				
				JButton diesel2 = new JButton("Buy Diesel Train - 800");
				c.gridx = 0;
				c.gridy = 16;
				c.gridwidth = 1;
				c.anchor = GridBagConstraints.CENTER;
				diesel2.addActionListener(new ActionListener() {
					public void actionPerformed(ActionEvent e) {
						Train soldtrain = corp.getTrains().get(trainbox2.getSelectedIndex());
						corp.getTrains().remove(trainbox2.getSelectedIndex());
						basic.getGameplay().getBankTrains().add(soldtrain);
						int lasttrain = basic.getGameplay().getTrains().size() - 1;
						corp.decreaseMoney(800);
						corp.getTrains().add(basic.getGameplay().getTrains().get(lasttrain));
						basic.getGameplay().setLastTrain(basic.getGameplay().getTrains().get(lasttrain));
						basic.getGameplay().getTrains().remove(lasttrain);
						basic.getGameplay().rustTrains(basic);
						basic.getGameplay().closePrivates(basic);
						
						
						
						basic.getTP().getPOR().getPanelright().removeAll();
						basic.getTP().getPOR().getPanelright().setPanelTrain(basic, corp);
						basic.getTP().getPOR().getPanemiddel().removeAll();
						
						List<Corporation> corps = basic.getGameplay().exceedTrains(basic);
						if(corps.size()>0) {basic.getTP().getPOR().getPanemiddel().setPanelExceedTrain(basic, corps);
											basic.getTP().getPOR().getPanelleft().removeAll(); }
						else                basic.getTP().getPOR().getPanemiddel().setPanelCorporation(basic, corp);

						basic.buildGraphics();
						basic.getTP().setSelectedIndex(tabpos);
					}
				});
				this.add(diesel2, c);
			}
		}
		if(basic.getGameplay().getTrains().get(0).getDistancePrimary() == 99) {
			if(corp.getTrains().size() > 0) {
				String[] trainlist2 = new String[corp.getTrains().size()];
				int traincounter2 = 0;
				for (Train train : corp.getTrains()) {
					trainlist2[traincounter2] = "" + train.getDistancePrimary();
					traincounter2 = traincounter2 + 1;
				}


				JComboBox<Object> trainbox2 = new JComboBox<Object>(trainlist2);
				trainbox2.setSelectedIndex(0);
				c.gridx = 1;
				c.gridy = 16;
				c.gridwidth = 1;
				c.anchor = GridBagConstraints.CENTER;
				c.insets = new Insets(10, 0, 0, 0);
				this.add(trainbox2, c);
				
				JButton diesel2 = new JButton("Buy Diesel Train - 800");
				c.gridx = 0;
				c.gridy = 16;
				c.gridwidth = 1;
				c.anchor = GridBagConstraints.CENTER;
				diesel2.addActionListener(new ActionListener() {
					public void actionPerformed(ActionEvent e) {
						Train soldtrain = corp.getTrains().get(trainbox2.getSelectedIndex());
						corp.getTrains().remove(trainbox2.getSelectedIndex());
						basic.getGameplay().getBankTrains().add(soldtrain);
						int lasttrain = basic.getGameplay().getTrains().size() - 1;
						corp.decreaseMoney(800);
						corp.getTrains().add(basic.getGameplay().getTrains().get(lasttrain));
						basic.getGameplay().setLastTrain(basic.getGameplay().getTrains().get(lasttrain));
						basic.getGameplay().getTrains().remove(lasttrain);
						basic.getGameplay().rustTrains(basic);
						basic.getGameplay().closePrivates(basic);
						
						
						
						basic.getTP().getPOR().getPanelright().removeAll();
						basic.getTP().getPOR().getPanelright().setPanelTrain(basic, corp);
						basic.getTP().getPOR().getPanemiddel().removeAll();
						
						List<Corporation> corps = basic.getGameplay().exceedTrains(basic);
						if(corps.size()>0) {basic.getTP().getPOR().getPanemiddel().setPanelExceedTrain(basic, corps);
											basic.getTP().getPOR().getPanelleft().removeAll(); }
						else                basic.getTP().getPOR().getPanemiddel().setPanelCorporation(basic, corp);

						basic.buildGraphics();
						basic.getTP().setSelectedIndex(tabpos);
					}
				});
				this.add(diesel2, c);
			}
		}
	}

	
	public void setPanelPrivate(Basic basic, Corporation corp) {
		this.setLayout(new GridBagLayout());
		GridBagConstraints c = new GridBagConstraints();
		
		JLabel label = new JLabel("Buy a Private");
		c.gridx = 0;
		c.gridy = 2;
		c.gridwidth = 2;
		c.anchor = GridBagConstraints.CENTER;
		c.insets = new Insets(10, 0, 0, 0);
		this.add(label, c);

		String[] privlist = new String[basic.getPrivates().size()];
		int privcounter = 0;
		for (Private priv : basic.getPrivates()) {
			privlist[privcounter] = priv.getName();
			privcounter = privcounter + 1;
		}

		JComboBox<Object> privbox = new JComboBox<Object>(privlist);
		privbox.setSelectedIndex(0);
		c.gridx = 0;
		c.gridy = 3;
		c.gridwidth = 2;
		c.anchor = GridBagConstraints.CENTER;
		c.insets = new Insets(10, 0, 0, 0);
		this.add(privbox, c);

		label = new JLabel("Value: ");
		JTextField privvalue = new JTextField("50", 4);
		c.gridx = 0;
		c.gridy = 4;
		c.gridwidth = 1;
		c.anchor = GridBagConstraints.CENTER;
		c.insets = new Insets(10, 0, 0, 0);
		this.add(label, c);
		c.gridx = 1;
		c.gridy = 4;
		c.gridwidth = 1;
		c.anchor = GridBagConstraints.CENTER;
		c.insets = new Insets(10, 0, 0, 0);
		this.add(privvalue, c);

		JButton privbuy = new JButton("Buy Private");
		c.gridx = 0;
		c.gridy = 5;
		c.gridwidth = 2;
		c.anchor = GridBagConstraints.CENTER;
		c.insets = new Insets(10, 0, 0, 0);
		privbuy.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				Private buypriv = basic.getPrivates().get(privbox.getSelectedIndex());

				if (buypriv.getOwner() < 10) {
					corp.decreaseMoney(Integer.valueOf(privvalue.getText()));
					basic.getPlayers().get(buypriv.getOwner()).increaseMoney(Integer.valueOf(privvalue.getText()));
					buypriv.setOwner(corp.getIndex() + 30);
				}
				if (buypriv.getOwner() > 20 && buypriv.getOwner() < 50) {
					corp.decreaseMoney(Integer.valueOf(privvalue.getText()));
					basic.getCorporations().get(buypriv.getOwner() - 30).increaseMoney(Integer.valueOf(privvalue.getText()));
					buypriv.setOwner(corp.getIndex() + 30);
				}
				basic.getTP().getPOR().getPanelright().removeAll();
				basic.getTP().getPOR().getPanemiddel().removeAll();
				basic.getTP().getPOR().getPanemiddel().setPanelCorporation(basic, corp);
				basic.buildGraphics();
				basic.getTP().setSelectedIndex(tabpos);
			}
		});
		this.add(privbuy, c);
	}
	
	public void setPanelTrainExceed(Basic basic, Corporation corp) {
		this.setLayout(new GridBagLayout());
		GridBagConstraints c = new GridBagConstraints();
		
		JLabel label = new JLabel("Give train to Bank Pool");
		c.gridx = 0;
		c.gridy = 3;
		c.gridwidth = 2;
		c.anchor = GridBagConstraints.CENTER;
		c.insets = new Insets(50, 0, 0, 0);
		this.add(label, c);



		label = new JLabel("Select train from " + corp.getName());
		c.gridx = 0;
		c.gridy = 5;
		c.gridwidth = 2;
		c.anchor = GridBagConstraints.CENTER;
		c.insets = new Insets(10, 0, 0, 0);
		this.add(label, c);

		String[] trainlist = new String[corp.getTrains().size()];
		int traincounter = 0;
		for (Train train : corp.getTrains()) {
			trainlist[traincounter] = "" + train.getDistancePrimary();
			traincounter++;
		}

		JComboBox<Object> trainbox = new JComboBox<Object>(trainlist);
		trainbox.setSelectedIndex(0);
		c.gridx = 0;
		c.gridy = 6;
		c.gridwidth = 2;
		c.anchor = GridBagConstraints.CENTER;
		c.insets = new Insets(10, 0, 0, 0);
		this.add(trainbox, c);

		JButton corpbuy = new JButton("Get rid of Train");
		c.gridx = 0;
		c.gridy = 9;
		c.gridwidth = 2;
		c.anchor = GridBagConstraints.CENTER;
		c.insets = new Insets(10, 0, 0, 0);
		corpbuy.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				Train selectedTrain = corp.getTrains().get(trainbox.getSelectedIndex());
				
				basic.getGameplay().getBankTrains().add(selectedTrain);
				corp.getTrains().remove(trainbox.getSelectedIndex());
				
				basic.getTP().getPOR().getPanelright().removeAll();
				basic.getTP().getPOR().getPanemiddel().removeAll();
				basic.buildGraphics();
				basic.getTP().setSelectedIndex(tabpos);
			}
		});
		this.add(corpbuy, c);
	}
}
