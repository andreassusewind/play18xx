package xml;

import java.io.File;
import java.util.ArrayList;
import java.util.List;

import javax.xml.bind.JAXBContext;
import javax.xml.bind.JAXBException;
import javax.xml.bind.Marshaller;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlElementWrapper;
import javax.xml.bind.annotation.XmlRootElement;

import com.play18xx.material.Basic;
import com.play18xx.material.Corporation;
import com.play18xx.material.Player;
import com.play18xx.material.Private;
import com.play18xx.material.Gameplay;
import com.play18xx.material.Stockmarket;

@XmlRootElement(name = "Basic")
public class XMLBasicSave {
	
	@XmlElementWrapper(name = "Players") 
	@XmlElement(name = "Player")
	private List<Player> Players = new ArrayList<Player>();
	@XmlElementWrapper(name = "Corporations") 
	@XmlElement(name = "Corporation")
	private List<Corporation> Corporations = new ArrayList<Corporation>();
	@XmlElementWrapper(name = "Privates") 
	@XmlElement(name = "Private")
	private List<Private> Privates = new ArrayList<Private>();
	@XmlElement(name = "Gameplay")
	private Gameplay Gameplay;
	@XmlElement(name = "Stockmarket")
	private Stockmarket Stockmarket;
	


	public XMLBasicSave() {}
	
	public XMLBasicSave getXMLBasic() { return this; }
	
	public XMLBasicSave(Basic basic) {
		for(Player var : basic.getPlayers()) { this.Players.add(var); }
		for(Private var : basic.getPrivates()) { this.Privates.add(var); }
		for(Corporation var : basic.getCorporations()) { this.Corporations.add(var); }
		this.Gameplay = basic.getGameplay();
		this.Stockmarket = basic.getStockmarket();
	}

	public void savetoxml() {
		  try {
				//File file = new File("/home/andreas/eclipse-workspace/play18xx/save.xml");
			  	File file = new File("../save.xml");
				JAXBContext jaxbContext = JAXBContext.newInstance(xml.XMLBasicSave.class);
				Marshaller jaxbMarshaller = jaxbContext.createMarshaller();

				jaxbMarshaller.setProperty(Marshaller.JAXB_FORMATTED_OUTPUT, true);

				jaxbMarshaller.marshal(this, file);

		  } catch (JAXBException e) {
				e.printStackTrace();
		  }
	}
}




/*
@XmlTransient
@XmlRootElement
@XmlSeeAlso({XMLPrivates.class})
public class XMLBasic {
	
	XMLPrivates Privates = new XMLPrivates();
	
	public XMLBasic() {}
	
	public XMLBasic(Basic basic) {
		this.Privates = new XMLPrivates(basic);
	}
	
	@XmlElement(name = "Basic")
	public XMLBasic getXMLBasic() {
		return this;
	}
	
	@XmlElement(name = "Private")
	public XMLPrivates getPrivates() {
		return this.Privates;
	}
	
	
}
*/