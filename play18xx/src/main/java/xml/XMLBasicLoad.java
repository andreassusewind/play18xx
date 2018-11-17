package xml;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.List;

import javax.xml.bind.JAXBContext;
import javax.xml.bind.JAXBException;
import javax.xml.bind.Unmarshaller;
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
public class XMLBasicLoad {
	
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
	


	public XMLBasicLoad() {}
	
	public XMLBasicLoad getXMLBasic() { return this; }
	
	
	public static void loadfromxml(Basic basic) throws JAXBException, FileNotFoundException {
		//File file = new File("/home/andreas/eclipse-workspace/play18xx/save.xml");
		File file = new File("../save.xml");
		JAXBContext jaxbContext = JAXBContext.newInstance(XMLBasicLoad.class);
		Unmarshaller unmarshaller = jaxbContext.createUnmarshaller();
		XMLBasicLoad xmlbasic = (XMLBasicLoad) unmarshaller.unmarshal(file);
		
		basic.setPlayers(xmlbasic.Players);
		basic.setCorporations(xmlbasic.Corporations);
		basic.setPrivates(xmlbasic.Privates);
		basic.setGameplay(xmlbasic.Gameplay);
		basic.setStockmarket(xmlbasic.Stockmarket);
		
//		for(Player player : basic.getPlayers()) {player.setCertCorps(basic);}
		for(Corporation corp : basic.getCorporations()) {corp.getMarker().setCorp(corp);}
		basic.getGameplay().refreshOperationroundCorpOrder(basic);
	}
}