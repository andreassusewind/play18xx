package com.play18xx.material;

import java.io.Serializable;
import java.util.Comparator;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlAttribute;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlTransient;

@XmlRootElement
@XmlAccessorType(value = XmlAccessType.FIELD)
public class CorporationPosition implements Serializable {
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	@XmlTransient
	private Corporation corp;
	@XmlElement
	private int row; // begins with 0
	@XmlElement
	private int col; // begins with 0
	@XmlElement
	private int stack; // default 8 BUT on changing positions first default must be 9 as in the setStack this is compared also with corp(this) ;-)
	@XmlAttribute
	private int value = 0;

	public CorporationPosition() {
	}

	public CorporationPosition(Basic basic, Corporation corp, int value) {
		for (int i = 0; i < basic.getStockmarket().getRows(); i++) {
			for (int j = 0; j < basic.getStockmarket().getCols(); j++) {
				if (basic.getStockmarket().getCode()[i][j] == 5 && basic.getStockmarket().getValue()[i][j] == value) {
					this.row = i;
					this.col = j;
					this.stack = 8;
					setStack(basic);
				}
			}
		}
		this.corp = corp;
		setValue(basic);
	}

	private void setStack(Basic basic) {
		for (Corporation corp : basic.getCorporations()) {
			if (corp.getMarker().getValue() > 0) {
				if (corp.getMarker().getRow() == this.row && corp.getMarker().getCol() == this.col) { 
					if (this.stack >= corp.getMarker().getStack()) {
						this.stack = corp.getMarker().getStack() - 1;
					}
				}
			}
		}
	}

	public void setUp(Basic basic) {
		if (this.row > 0) {
			this.row = this.row - 1;
			this.stack = 9;
			setStack(basic);
		}
		setValue(basic);
	}

	public void setLeft(Basic basic) {
		if (this.col > 0) {
			this.col = this.col - 1;
			this.stack = 9;
			setStack(basic);
		} else {
			setDown(basic);
		}
		setValue(basic);
	}

	public void setRight(Basic basic) {
		if (this.col < basic.getStockmarket().getCols() - 1) {
			if (basic.getStockmarket().getCode()[this.row][this.col + 1] > 0) {
				this.col = this.col + 1;
				this.stack = 9;
				setStack(basic);
			}
		} else {
			if (this.row > 0) {
				if (basic.getStockmarket().getCode()[this.row - 1][this.col] > 0) {
					setUp(basic);
				}
			}
		}
		setValue(basic);
	}

	public void setDown(Basic basic) {
		if (basic.getStockmarket().getCode()[this.row + 1][this.col] > 0) {
			this.row = this.row + 1;
			this.stack = 9;
			setStack(basic);
		}
		setValue(basic);
	}
	
	public boolean CountToShareLimit(Basic basic) {
		if(basic.getStockmarket().getCode()[this.row][this.col] > 1 
				&& basic.getStockmarket().getCode()[this.row][this.col] < 5) {
			return false;
		}
		else {return true;}
	}
	
	public boolean CountToCorpLimit(Basic basic) {
		if(basic.getStockmarket().getCode()[this.row][this.col] > 2 
				&& basic.getStockmarket().getCode()[this.row][this.col] < 5) {
			return false;
		}
		else {return true;}
	}

	public static Comparator<CorporationPosition> ValueComparator = new Comparator<CorporationPosition>() {
		public int compare(CorporationPosition s1, CorporationPosition s2) {
			int value1 = s1.getValue();
			int value2 = s2.getValue();
			return value2 - value1; // descending order
		}
	};
	public static Comparator<CorporationPosition> RowComparator = new Comparator<CorporationPosition>() {
		public int compare(CorporationPosition s1, CorporationPosition s2) {
			int value1 = s1.getRow();
			int value2 = s2.getRow();
			return value1 - value2; // ascending order
		}
	};
	public static Comparator<CorporationPosition> StackComparator = new Comparator<CorporationPosition>() {
		public int compare(CorporationPosition s1, CorporationPosition s2) {
			int value1 = s1.getStack();
			int value2 = s2.getStack();
			return value2 - value1; // descending order
		}
	};

	private void setValue(Basic basic) {
		this.value = basic.getStockmarket().getValue()[this.row][this.col];
	}

	public Corporation getCorp() {
		return corp;
	}

	public void setCorp(Corporation corp) {
		this.corp = corp;
	}
	
	public int getRow() {
		return row;
	}

	public void setRow(int row) {
		this.row = row;
	}

	public int getCol() {
		return col;
	}

	public void setCol(int col) {
		this.col = col;
	}

	public int getStack() {
		return stack;
	}

	public void setStack(int stack) {
		this.stack = stack;
	}

	public int getValue() {
		return value;
	}

	public void setValue(int value) {
		this.value = value;
	}
}
