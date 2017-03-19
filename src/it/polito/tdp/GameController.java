/**
 * Sample Skeleton for 'GameFXML.fxml' Controller Class
 */

package it.polito.tdp;

import java.net.URL;
import java.util.ResourceBundle;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Label;
import javafx.scene.control.ProgressBar;
import javafx.scene.control.TextField;

public class GameController {

    @FXML // ResourceBundle that was given to the FXMLLoader
    private ResourceBundle resources;

    @FXML // URL location of the FXML file that was given to the FXMLLoader
    private URL location;

    @FXML // fx:id="cmbDifficolta"
    private ComboBox<Integer> cmbDifficolta; // Value injected by FXMLLoader

    @FXML // fx:id="btnGioca"
    private Button btnGioca; // Value injected by FXMLLoader

    @FXML // fx:id="txtNumeroInserito"
    private TextField txtNumeroInserito; // Value injected by FXMLLoader

    @FXML // fx:id="btnProva"
    private Button btnProva; // Value injected by FXMLLoader

    @FXML // fx:id="txtResult"
    private Label txtResult; // Value injected by FXMLLoader

    @FXML // fx:id="txtTentativi"
    private Label txtTentativi; // Value injected by FXMLLoader

    @FXML // fx:id="prgrBar"
    private ProgressBar prgrBar; // Value injected by FXMLLoader
    
    private int numeroInserito;
    private int numeroSegreto;
    private int difficolta;
    private int numeroTentativi;
    private int totTentativi;
    private final int margine = 3;
    private boolean inGame = false;

    @FXML
    void doGioca(ActionEvent event) {
    	
    	if(inGame){
    		inGame = false;
    		btnGioca.setText("Gioca!");
    		this.makeGUIVisible(false);
    		cmbDifficolta.setDisable(false);
    		return;
    	}else{
    		inGame = true;
    		this.makeGUIVisible(true);
    		cmbDifficolta.setDisable(true);
    		btnGioca.setText("Abbandona!");
    		txtResult.setText("Inserisci un numero");
    		txtNumeroInserito.clear();
    		txtNumeroInserito.setDisable(false);
    		btnProva.setDisable(false);
    	}
    	
    	if(cmbDifficolta.getValue() == null)
    		return;
    	
    	difficolta = cmbDifficolta.getValue();
    	
    	numeroSegreto = (int) (Math.random()*difficolta)+1;
    	
    	totTentativi = (int) (Math.log(difficolta)/Math.log(2.0)) + margine;
    	
    	numeroTentativi = 0;
    	
    	txtTentativi.setText(String.format("Tentativo %d/%d", numeroTentativi, totTentativi));
    	
    	prgrBar.setProgress((double)numeroTentativi/totTentativi);

    }

    @FXML
    void doProva(ActionEvent event) {
    	
    	try{
    		numeroInserito = Integer.parseInt(txtNumeroInserito.getText());
    	}catch(NumberFormatException e){
    		txtResult.setText("Devi inserire un numero!");
    		return;
    	}
    	
    	numeroTentativi ++ ;
    	
    	txtTentativi.setText(String.format("Tentativo %d/%d", numeroTentativi, totTentativi));
    	
    	prgrBar.setProgress((double)numeroTentativi/totTentativi);
    	
    	if(numeroInserito == numeroSegreto){  //ho vinto
    		txtResult.setText("Hai Vinto!");
    		txtNumeroInserito.setDisable(true);
    		btnProva.setDisable(true);
    		return;
    	}
    	if(numeroTentativi >= totTentativi){  //ho perso
    		txtResult.setText("Hai Perso! Era: " + numeroSegreto);
    		txtNumeroInserito.setDisable(true);
    		btnProva.setDisable(true);
    		return;
    	}
    	if(numeroInserito < numeroSegreto){
    		txtResult.setText("Troppo Piccolo");
    		return;
    	}
    	if(numeroInserito > numeroSegreto){
    		txtResult.setText("Troppo Grande");
    		return;
    	}

    }
    
    private void makeGUIVisible(boolean visible){
    	txtNumeroInserito.setVisible(visible);
    	btnProva.setVisible(visible);
    	txtResult.setVisible(visible);
    	txtTentativi.setVisible(visible);
    	prgrBar.setVisible(visible);
    }

    @FXML // This method is called by the FXMLLoader when initialization is complete
    void initialize() {
        assert cmbDifficolta != null : "fx:id=\"cmbDifficolta\" was not injected: check your FXML file 'GameFXML.fxml'.";
        assert btnGioca != null : "fx:id=\"btnGioca\" was not injected: check your FXML file 'GameFXML.fxml'.";
        assert txtNumeroInserito != null : "fx:id=\"txtNumeroInserito\" was not injected: check your FXML file 'GameFXML.fxml'.";
        assert btnProva != null : "fx:id=\"btnProva\" was not injected: check your FXML file 'GameFXML.fxml'.";
        assert txtResult != null : "fx:id=\"txtResult\" was not injected: check your FXML file 'GameFXML.fxml'.";
        assert txtTentativi != null : "fx:id=\"txtTentativi\" was not injected: check your FXML file 'GameFXML.fxml'.";
        assert prgrBar != null : "fx:id=\"prgrBar\" was not injected: check your FXML file 'GameFXML.fxml'.";
        
        cmbDifficolta.getItems().addAll(10,100,1000);
        if(cmbDifficolta.getItems().size() > 0)
        	cmbDifficolta.setValue(cmbDifficolta.getItems().get(0));

    }
}
