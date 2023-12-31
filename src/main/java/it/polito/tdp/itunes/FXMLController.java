/**
 * Sample Skeleton for 'Scene.fxml' Controller Class
 */

package it.polito.tdp.itunes;

import java.net.URL;
import java.util.ResourceBundle;
import java.util.Set;

import it.polito.tdp.itunes.model.Model;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;

public class FXMLController {

	private Model model;
	
    @FXML // ResourceBundle that was given to the FXMLLoader
    private ResourceBundle resources;

    @FXML // URL location of the FXML file that was given to the FXMLLoader
    private URL location;

    @FXML // fx:id="btnComponente"
    private Button btnComponente; // Value injected by FXMLLoader

    @FXML // fx:id="btnCreaGrafo"
    private Button btnCreaGrafo; // Value injected by FXMLLoader

    @FXML // fx:id="btnSet"
    private Button btnSet; // Value injected by FXMLLoader

    @FXML // fx:id="cmbA1"
    private ComboBox<String> cmbA1; // Value injected by FXMLLoader

    @FXML // fx:id="txtDurata"
    private TextField txtDurata; // Value injected by FXMLLoader

    @FXML // fx:id="txtResult"
    private TextArea txtResult; // Value injected by FXMLLoader

    @FXML // fx:id="txtX"
    private TextField txtX; // Value injected by FXMLLoader

    @FXML
    void doComponente(ActionEvent event) {
    	
    	String title="";
    	title=cmbA1.getValue();
    	
    	if(title==null) {
    		txtResult.appendText("Inserire un album.\n");
    		return;
    	}
    	
    	int aid = this.model.getIdVerticeDaTitle(title);
    	
    	Set<Integer> connessa = model.getComponente(aid) ;
    	
    	txtResult.appendText("Componente connessa - "+ title +"\n");
    	txtResult.appendText("Dimensione componente: "+ connessa.size()+"\n");
    	txtResult.appendText("Durata componente: "+ this.model.getDurataComponente(connessa) +"\n");
    	
    }

    @FXML
    void doCreaGrafo(ActionEvent event) {
    	
    	String s = txtDurata.getText() ;
    	
    	if(s.equals("")) {
    		txtResult.setText("Inserire una durata.\n");
    		return ;
    	}
    	
    	double durata = 0.0 ;

    	try {
	    	durata = Double.parseDouble(s) ;
    	} catch(NumberFormatException e) {
    		txtResult.setText("Inserire un numero.\n");
    		return ;
    	}
    
    	
    	int d=0;
    	d=(int)(durata*60000);
    	
    	
    	
    	this.model.creaGrafo(d);
    	d=0;
    	
//    	stampa grafo
    	this.txtResult.setText("Grafo creato.\n");
    	this.txtResult.appendText("Ci sono " + this.model.nVertici() + " vertici\n");
    	this.txtResult.appendText("Ci sono " + this.model.nArchi() + " archi\n\n");
    	
    	cmbA1.getItems().setAll(this.model.titleVertici());
    }

    @FXML
    void doEstraiSet(ActionEvent event) {

    }

    @FXML // This method is called by the FXMLLoader when initialization is complete
    void initialize() {
        assert btnComponente != null : "fx:id=\"btnComponente\" was not injected: check your FXML file 'Scene.fxml'.";
        assert btnCreaGrafo != null : "fx:id=\"btnCreaGrafo\" was not injected: check your FXML file 'Scene.fxml'.";
        assert btnSet != null : "fx:id=\"btnSet\" was not injected: check your FXML file 'Scene.fxml'.";
        assert cmbA1 != null : "fx:id=\"cmbA1\" was not injected: check your FXML file 'Scene.fxml'.";
        assert txtDurata != null : "fx:id=\"txtDurata\" was not injected: check your FXML file 'Scene.fxml'.";
        assert txtResult != null : "fx:id=\"txtResult\" was not injected: check your FXML file 'Scene.fxml'.";
        assert txtX != null : "fx:id=\"txtX\" was not injected: check your FXML file 'Scene.fxml'.";

    }
    
    public void setModel(Model model) {
    	this.model = model;
    	
//    	cmbA1.getItems().addAll(this.model.getAllNameAlbums());
    }

}
