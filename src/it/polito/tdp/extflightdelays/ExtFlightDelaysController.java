/**
 * Sample Skeleton for 'ExtFlightDelays.fxml' Controller Class
 */

package it.polito.tdp.extflightdelays;

import java.net.URL;
import java.util.List;
import java.util.ResourceBundle;

import it.polito.tdp.extflightdelays.model.Model;
import it.polito.tdp.extflightdelays.model.Vicini;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;

public class ExtFlightDelaysController {
	private Model model;

    @FXML // ResourceBundle that was given to the FXMLLoader
    private ResourceBundle resources;

    @FXML // URL location of the FXML file that was given to the FXMLLoader
    private URL location;

    @FXML // fx:id="txtResult"
    private TextArea txtResult; // Value injected by FXMLLoader

    @FXML // fx:id="btnCreaGrafo"
    private Button btnCreaGrafo; // Value injected by FXMLLoader

    @FXML // fx:id="cmbBoxStati"
    private ComboBox<String> cmbBoxStati; // Value injected by FXMLLoader

    @FXML // fx:id="btnVisualizzaVelivoli"
    private Button btnVisualizzaVelivoli; // Value injected by FXMLLoader

    @FXML // fx:id="txtT"
    private TextField txtT; // Value injected by FXMLLoader

    @FXML // fx:id="txtG"
    private TextField txtG; // Value injected by FXMLLoader

    @FXML // fx:id="btnSimula"
    private Button btnSimula; // Value injected by FXMLLoader

    @FXML
    void doCreaGrafo(ActionEvent event) {
    	try {
    		model.creaGrafo();
    		txtResult.appendText("Grafo creato!\n");
    		txtResult.appendText("Num vertici: "+model.nVertici()+" Num archi: "+model.nArchi()+"\n");
    		
    		cmbBoxStati.getItems().addAll(model.getStati());
    		btnCreaGrafo.setDisable(true);
    		btnVisualizzaVelivoli.setDisable(false);
    		btnSimula.setDisable(true);
            txtG.setDisable(true);
            txtT.setDisable(true);
    		
    		
    	} catch (Exception e) {
    		txtResult.appendText("Si è verificato un errore nell'esecuzione\n");
    		return;
    	}

    }

    @FXML
    void doSimula(ActionEvent event) {

    }

    @FXML
    void doVisualizzaVelivoli(ActionEvent event) {
    	try {
    	String stato = cmbBoxStati.getValue();
    	List<Vicini> vicini = model.getVicini(stato);
    	txtResult.appendText("Elenco degli stati collegati allo stato "+stato+"\n");
    	for(Vicini v: vicini) {
    		txtResult.appendText(v+ "\n");
    		}
    	btnCreaGrafo.setDisable(true);
    	btnVisualizzaVelivoli.setDisable(true);
    	btnSimula.setDisable(false);
        txtG.setDisable(false);
        txtT.setDisable(false);
    
    	}catch (Exception e) {
    		txtResult.appendText("Si è verificato un errore nell'esecuzione\n");
    		return;
    	}
    }
    
    public void setModel(Model model) {
		this.model = model;	
	}

    @FXML // This method is called by the FXMLLoader when initialization is complete
    void initialize() {
        assert txtResult != null : "fx:id=\"txtResult\" was not injected: check your FXML file 'ExtFlightDelays.fxml'.";
        assert btnCreaGrafo != null : "fx:id=\"btnCreaGrafo\" was not injected: check your FXML file 'ExtFlightDelays.fxml'.";
        assert cmbBoxStati != null : "fx:id=\"cmbBoxStati\" was not injected: check your FXML file 'ExtFlightDelays.fxml'.";
        assert btnVisualizzaVelivoli != null : "fx:id=\"btnVisualizzaVelivoli\" was not injected: check your FXML file 'ExtFlightDelays.fxml'.";
        assert txtT != null : "fx:id=\"txtT\" was not injected: check your FXML file 'ExtFlightDelays.fxml'.";
        assert txtG != null : "fx:id=\"txtG\" was not injected: check your FXML file 'ExtFlightDelays.fxml'.";
        assert btnSimula != null : "fx:id=\"btnSimula\" was not injected: check your FXML file 'ExtFlightDelays.fxml'.";
        
        btnCreaGrafo.setDisable(false);
        btnSimula.setDisable(true);
        btnVisualizzaVelivoli.setDisable(true);
        txtG.setDisable(true);
        txtT.setDisable(true);
    }
}