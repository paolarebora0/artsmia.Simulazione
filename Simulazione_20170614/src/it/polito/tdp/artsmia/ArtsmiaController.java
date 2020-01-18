/**
 * Sample Skeleton for 'Artsmia.fxml' Controller Class
 */

package it.polito.tdp.artsmia;

import java.net.URL;
import java.util.List;
import java.util.ResourceBundle;

import it.polito.tdp.artsmia.model.Model;
import it.polito.tdp.artsmia.model.Mostre;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.ChoiceBox;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;

public class ArtsmiaController {

	private Model model;
    @FXML // ResourceBundle that was given to the FXMLLoader
    private ResourceBundle resources;

    @FXML // URL location of the FXML file that was given to the FXMLLoader
    private URL location;

    @FXML // fx:id="boxAnno"
    private ChoiceBox<Integer> boxAnno; // Value injected by FXMLLoader

    @FXML // fx:id="txtFieldStudenti"
    private TextField txtFieldStudenti; // Value injected by FXMLLoader

    @FXML // fx:id="txtResult"
    private TextArea txtResult; // Value injected by FXMLLoader

    public void setModel(Model model) {
    	this.model = model;
    	getAllComboItems();   	
    }
    
    public void getAllComboItems() {
    	List<Integer> anni = model.getListaAnni();
    	boxAnno.getItems().setAll(anni);
    }
    
    @FXML
    void handleCreaGrafo(ActionEvent event) {
    	
    	txtResult.clear();
    	int anno = boxAnno.getValue();
    	model.creaGrafo(anno);
    	txtResult.appendText("Grafo creato: "+model.getVertexSize() +" vertici e "+model.getEdgesSize()+" archi.\n");
    	if(model.isConnected() == true) {
    		txtResult.appendText("Grafo fortemente connesso\n");
    	} else {
    		txtResult.appendText("Grafo NON fortemente connesso\n");
    	}
    	
//    	if(model.isStronglyConnected() == true) {
//    		txtResult.appendText("Grafo fortemente connesso 2\n");
//    	} else {
//    		txtResult.appendText("Grafo NON fortemente connesso 2\n");
//    	}
    	
//    	Mostre m = model.mostraConPiuOpere();
    	txtResult.appendText("La mostra con più opere: \n");
    	txtResult.appendText(model.mostraConPiuOpere().toString());
    	txtResult.appendText("\nCon: ");
    	txtResult.appendText(model.getNumeroMaxOpere()+" opere \n");
    
    }

    @FXML
    void handleSimula(ActionEvent event) {

    }

    @FXML // This method is called by the FXMLLoader when initialization is complete
    void initialize() {
        assert boxAnno != null : "fx:id=\"boxAnno\" was not injected: check your FXML file 'Artsmia.fxml'.";
        assert txtFieldStudenti != null : "fx:id=\"txtFieldStudenti\" was not injected: check your FXML file 'Artsmia.fxml'.";
        assert txtResult != null : "fx:id=\"txtResult\" was not injected: check your FXML file 'Artsmia.fxml'.";

    }
    
}
