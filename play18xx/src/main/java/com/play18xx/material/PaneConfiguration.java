package com.play18xx.material;

import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Insets;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.FileNotFoundException;
import java.io.Serializable;

import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.xml.bind.JAXBException;

import com.play18xx.app.App;

public class PaneConfiguration extends JPanel implements Serializable {
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	public static void setPaneConfiguration(Basic basic) {
		JPanel panel = new JPanel();

		panel.setLayout(new GridBagLayout());
		GridBagConstraints c = new GridBagConstraints();
		c.fill = GridBagConstraints.HORIZONTAL;
		c.weighty = 0.01;

		JLabel label = new JLabel("Konfiguration der " + App.gamename + " Umgebung");
		c.gridx = 0;
		c.gridy = 0;
		c.gridwidth = 16;
		panel.add(label, c);

// Set PlayerQuantity		
		label = new JLabel("Spieleranzahl:  ");
		c.gridx = 0;
		c.gridy = 3;
		c.gridwidth = 3;
		panel.add(label, c);

		String PQcomboList[] = { "2", "3", "4", "5", "6", "7", "8" };
		JComboBox<Object> PlayerQuantity = new JComboBox<Object>(PQcomboList);
		PlayerQuantity.setSelectedIndex(2);
		c.gridx = 3;
		c.gridy = 3;
		c.gridwidth = 1;
		panel.add(PlayerQuantity, c);

// Set Player.Name		
		JTextField[] PlayerNameField = new JTextField[8];
		for (int i = 1; i < 9; i++) {
			label = new JLabel("Spieler " + i + ":  ");
			c.gridx = 0;
			c.gridy = 3 + i;
			c.gridwidth = 1;
			panel.add(label, c);

			PlayerNameField[i - 1] = new JTextField("Player" + i, 10);
			c.gridx = 1;
			c.gridy = 3 + i;
			c.gridwidth = 3;
			panel.add(PlayerNameField[i - 1], c);
		}

// Set Gameplay.PlayerMoney
		label = new JLabel("Startgeld:  ");
		c.gridx = 0;
		c.gridy = 12;
		c.gridwidth = 1;
		panel.add(label, c);

		JTextField PlayerMoney = new JTextField("600", 10);
		c.gridx = 1;
		c.gridy = 12;
		c.gridwidth = 3;
		panel.add(PlayerMoney, c);

// Set Gameplay.MaxMoney
		label = new JLabel("Maximales Geld:  ");
		c.gridx = 0;
		c.gridy = 13;
		c.gridwidth = 1;
		panel.add(label, c);

		JTextField MaxMoney = new JTextField("12000", 10);
		c.gridx = 1;
		c.gridy = 13;
		c.gridwidth = 3;
		panel.add(MaxMoney, c);

// Set Gameplay.MaxCertificates
		label = new JLabel("Maximale Aktien:  ");
		c.gridx = 0;
		c.gridy = 14;
		c.gridwidth = 1;
		panel.add(label, c);

		JTextField MaxCertificates = new JTextField("16", 10);
		c.gridx = 1;
		c.gridy = 14;
		c.gridwidth = 3;
		panel.add(MaxCertificates, c);

// Set Dummy label for middel break
		label = new JLabel("        ");
		c.gridx = 4;
		c.gridy = 3;
		c.gridwidth = 1;
		panel.add(label, c);

// Set CorporationQuantity		
		label = new JLabel("Corporation Anzahl:  ");
		c.gridx = 5;
		c.gridy = 3;
		c.gridwidth = 3;
		panel.add(label, c);

		String CQcomboList[] = { "4", "5", "6", "7", "8", "9", "10" };
		JComboBox<Object> CorporationQuantity = new JComboBox<Object>(CQcomboList);
		CorporationQuantity.setSelectedIndex(4);
		c.gridx = 8;
		c.gridy = 3;
		c.gridwidth = 1;
		panel.add(CorporationQuantity, c);

// Set Corporation.Name
		String[] CorpTextFieldDefault = { "PRR", "NYC", "CPR", "B&O", "C&O", "Erie", "NNH", "B&M", "", "" };
		JTextField[] CorporationName = new JTextField[10];
		for (int i = 1; i < 11; i++) {
			label = new JLabel("Corporation " + i + ":  ");
			c.gridx = 5;
			c.gridy = 3 + i;
			c.gridwidth = 1;
			panel.add(label, c);

			CorporationName[i - 1] = new JTextField(CorpTextFieldDefault[i - 1], 10);
			c.gridx = 6;
			c.gridy = 3 + i;
			c.gridwidth = 3;
			panel.add(CorporationName[i - 1], c);
		}

// Set Corporation.Stations
		label = new JLabel("  Stationsmarker");
		c.gridx = 9;
		c.gridy = 3;
		c.gridwidth = 1;
		panel.add(label, c);
		int[] CorpStationsDefault = { 1, 1, 1, 1, 1, 1, 0, 2, 1, 1 };
		String CScomboList[] = { "2", "3", "4", "5", "6" };
		@SuppressWarnings("unchecked")
		JComboBox<Object>[] CorporationStations = new JComboBox[10];
		for (int i = 1; i < 11; i++) {
			c.gridx = 9;
			c.gridy = 3 + i;
			c.gridwidth = 1;
			CorporationStations[i - 1] = new JComboBox<Object>(CScomboList);
			CorporationStations[i - 1].setSelectedIndex(CorpStationsDefault[i - 1]);
			panel.add(CorporationStations[i - 1], c);
		}

// Set Dummy label for middel break
		label = new JLabel("        ");
		c.gridx = 10;
		c.gridy = 3;
		c.gridwidth = 1;
		panel.add(label, c);

// Set PrivateQuantity		
		label = new JLabel("Private Anzahl:  ");
		c.gridx = 11;
		c.gridy = 3;
		c.gridwidth = 2;
		panel.add(label, c);

		String PrQcomboList[] = { "4", "5", "6", "7", "8" };
		JComboBox<Object> PrivateQuantity = new JComboBox<Object>(PrQcomboList);
		PrivateQuantity.setSelectedIndex(2);
		c.gridx = 13;
		c.gridy = 3;
		c.gridwidth = 1;
		panel.add(PrivateQuantity, c);

// Set Private.Name
// Set Private.ParValue
// Set Private.Revenue
		String[] PrivateNameTextFieldDefault = { "SV", "CS", "DH", "MH", "CA", "BO", "", "" };
		String[] PrivateParValueTextFieldDefault = { "20", "40", "70", "110", "160", "220", "", "" };
		String[] PrivateRevenueTextFieldDefault = { "5", "10", "15", "20", "25", "30", "", "" };
		JTextField[] PrivateName = new JTextField[8];
		JTextField[] PrivateParValue = new JTextField[8];
		label = new JLabel("   Par Value   ");
		c.gridx = 14;
		c.gridy = 3;
		c.gridwidth = 1;
		panel.add(label, c);
		JTextField[] PrivateRevenue = new JTextField[8];
		label = new JLabel("   Revenue   ");
		c.gridx = 15;
		c.gridy = 3;
		c.gridwidth = 1;
		panel.add(label, c);
		for (int i = 1; i < 9; i++) {
			label = new JLabel("Private " + i + ":  ");
			c.gridx = 11;
			c.gridy = 3 + i;
			c.gridwidth = 1;
			panel.add(label, c);

			PrivateName[i - 1] = new JTextField(PrivateNameTextFieldDefault[i - 1], 10);
			c.gridx = 12;
			c.gridy = 3 + i;
			c.gridwidth = 2;
			panel.add(PrivateName[i - 1], c);

			PrivateParValue[i - 1] = new JTextField(PrivateParValueTextFieldDefault[i - 1], 10);
			c.gridx = 14;
			c.gridy = 3 + i;
			c.gridwidth = 1;
			panel.add(PrivateParValue[i - 1], c);

			PrivateRevenue[i - 1] = new JTextField(PrivateRevenueTextFieldDefault[i - 1], 10);
			c.gridx = 15;
			c.gridy = 3 + i;
			c.gridwidth = 1;
			panel.add(PrivateRevenue[i - 1], c);
		}

// Set Private.SpecialFunction
		label = new JLabel("   Function   ");
		c.gridx = 16;
		c.gridy = 3;
		c.gridwidth = 1;
		panel.add(label, c);
		@SuppressWarnings("unchecked")
		JComboBox<Object>[] PrivateSpecial = new JComboBox[8];
		for (int i = 1; i < 9; i++) {
			PrivateSpecial[i - 1] = new JComboBox<Object>(PrivateNameTextFieldDefault);
			PrivateSpecial[i - 1].setSelectedIndex(i - 1);
			c.gridx = 16;
			c.gridy = 3 + i;
			c.gridwidth = 1;
			panel.add(PrivateSpecial[i - 1], c);
		}

// Set Dummy label for middel break
		label = new JLabel("        ");
		c.gridx = 14;
		c.gridy = 12;
		c.gridwidth = 1;
		panel.add(label, c);

// Set Train.Settings
		label = new JLabel("Train");
		c.gridx = 13;
		c.gridy = 12;
		c.gridwidth = 1;
		panel.add(label, c);
		String[] TrainDistance = { "2", "3", "4", "5", "6", "99" };
		label = new JLabel("Quantity");
		c.gridx = 14;
		c.gridy = 12;
		c.gridwidth = 1;
		panel.add(label, c);
		String[] TrainQDefault = { "6", "5", "4", "3", "2", "6" };
		label = new JLabel("Costs");
		c.gridx = 15;
		c.gridy = 12;
		c.gridwidth = 1;
		panel.add(label, c);
		String[] TrainCDefault = { "80", "180", "300", "450", "630", "1100" };
		label = new JLabel("Rusts");
		c.gridx = 16;
		c.gridy = 12;
		c.gridwidth = 1;
		panel.add(label, c);
		String[] TrainRDefault = { "4", "6", "99", "100", "100", "100" };

		JTextField[] TrainQuantity = new JTextField[6];
		JTextField[] TrainCost = new JTextField[6];
		JTextField[] TrainRust = new JTextField[6];

		for (int i = 1; i < 7; i++) {
			label = new JLabel(TrainDistance[i - 1]);
			c.gridx = 13;
			c.gridy = 12 + i;
			c.gridwidth = 1;
			panel.add(label, c);
			TrainQuantity[i - 1] = new JTextField(TrainQDefault[i - 1], 10);
			c.gridx = 14;
			c.gridy = 12 + i;
			c.gridwidth = 1;
			panel.add(TrainQuantity[i - 1], c);
			TrainCost[i - 1] = new JTextField(TrainCDefault[i - 1], 10);
			c.gridx = 15;
			c.gridy = 12 + i;
			c.gridwidth = 1;
			panel.add(TrainCost[i - 1], c);
			TrainRust[i - 1] = new JTextField(TrainRDefault[i - 1], 10);
			c.gridx = 16;
			c.gridy = 12 + i;
			c.gridwidth = 1;
			panel.add(TrainRust[i - 1], c);
		}

		JButton start = new JButton("Start");
		c.gridx = 1;
		c.gridwidth = 2;
		c.gridy = 17;
		start.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {

				String[] PlayerNames = new String[Integer.valueOf((String) PlayerQuantity.getSelectedItem())];
				for (int i = 0; i < Integer.valueOf((String) PlayerQuantity.getSelectedItem()); i++) {
					PlayerNames[i] = PlayerNameField[i].getText();
				}
				String[] CorpNames = new String[Integer.valueOf((String) CorporationQuantity.getSelectedItem())];
				int[] CorpStations = new int[Integer.valueOf((String) CorporationQuantity.getSelectedItem())];
				for (int i = 0; i < Integer.valueOf((String) CorporationQuantity.getSelectedItem()); i++) {
					CorpNames[i] = CorporationName[i].getText();
					CorpStations[i] = Integer.valueOf((String) CorporationStations[i].getSelectedItem());
				}
				String[] PrivateNames = new String[Integer.valueOf((String) PrivateQuantity.getSelectedItem())];
				int[] PrivateParValues = new int[Integer.valueOf((String) PrivateQuantity.getSelectedItem())];
				int[] PrivateRevenues = new int[Integer.valueOf((String) PrivateQuantity.getSelectedItem())];
				String[] PrivateSpecials = new String[Integer.valueOf((String) PrivateQuantity.getSelectedItem())];
				for (int i = 0; i < Integer.valueOf((String) PrivateQuantity.getSelectedItem()); i++) {
					PrivateNames[i] = PrivateName[i].getText();
					PrivateParValues[i] = Integer.valueOf((String) PrivateParValue[i].getText());
					PrivateRevenues[i] = Integer.valueOf((String) PrivateRevenue[i].getText());
					PrivateSpecials[i] = (String) PrivateSpecial[i].getSelectedItem();
				}
				// IF TRAIN SETTINGS ARE CHANGED ---
				int[] TrainDistances = new int[7];
				int[] TrainQuantities = new int[7];
				int[] TrainCosts = new int[7];
				int[] TrainRusts = new int[7];
				for (int i = 0; i < 6; i++) {
					TrainDistances[i] = Integer.valueOf((String) TrainDistance[i]);
					TrainQuantities[i] = Integer.valueOf((String) TrainQuantity[i].getText());
					TrainCosts[i] = Integer.valueOf((String) TrainCost[i].getText());
					TrainRusts[i] = Integer.valueOf((String) TrainRust[i].getText());
				}
				/// NOT CODED: filepath variable
				String FilePathStockmarket = "/home/andreas/eclipse-workspace/play_1830/StockMarket.cfg";
				new Basic(Integer.valueOf((String) PlayerQuantity.getSelectedItem()), PlayerNames,
						Integer.valueOf((String) PlayerMoney.getText()),

						Integer.valueOf((String) CorporationQuantity.getSelectedItem()), CorpNames, CorpStations,

						Integer.valueOf((String) PrivateQuantity.getSelectedItem()), PrivateNames, PrivateParValues,
						PrivateRevenues, PrivateSpecials,

						Integer.valueOf((String) MaxMoney.getText()),
						Integer.valueOf((String) MaxCertificates.getText()), TrainDistances, TrainQuantities,
						TrainCosts, TrainRusts,

						FilePathStockmarket, basic.getTP());

			}
		});
		panel.add(start, c);

		JTextField WorkingDirectory = new JTextField("/home/andreas/eclipse-workspace/play18xx/", 150);

		JButton load = new JButton("Laden");
		c.gridx = 1;
		c.gridwidth = 2;
		c.gridy = 18;
		load.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
/*				BasicSave savedata = new BasicSave(basic);
				savedata.load(basic);
				basic.buildGraphics();
				basic.getTP().setSelectedIndex(0);*/
			}
		});
		panel.add(load, c);

		JButton loadxml = new JButton("Laden XML");
		c.gridx = 1;
		c.gridwidth = 2;
		c.gridy = 19;
		loadxml.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				try {
					xml.XMLBasicLoad.loadfromxml(basic);
				} catch (FileNotFoundException | JAXBException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}
				basic.buildGraphics();
				basic.getTP().setSelectedIndex(0);
			}
		});
		panel.add(loadxml, c);

		c.gridx = 1;
		c.gridwidth = 9;
		c.gridy = 20;
		panel.add(WorkingDirectory, c);

		JButton setWorkingDirectory = new JButton("Set Working Directory");
		c.gridx = 11;
		c.gridwidth = 2;
		c.gridy = 20;
		setWorkingDirectory.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				
			}
		});
		panel.add(setWorkingDirectory, c);
		
		basic.getTP().addTab("Konfiguration", panel);
	}
}
