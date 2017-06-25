package it.polito.tdp.music;

import java.net.URL;
import java.time.DayOfWeek;
import java.util.List;
import java.util.ResourceBundle;

import it.polito.tdp.music.model.BranoConAscolti;
import it.polito.tdp.music.model.Model;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.TextArea;

public class MusicController {

	private Model model;
    @FXML
    private ResourceBundle resources;

    @FXML
    private URL location;

    @FXML
    private ComboBox<DayOfWeek> boxGiorno;

    @FXML
    private Button btnCitta;

    @FXML
    private Button btnBraniComuni;

    @FXML
    private TextArea txtResult;

    @FXML
    void doCitta(ActionEvent event) {
    	DayOfWeek d = boxGiorno.getValue();
    	model.getCittaConPiuAscolti(d);
    	List<BranoConAscolti> lista = model.getBraniConAscolti(d);
    	for(BranoConAscolti b : lista){
    		txtResult.appendText(b.getBrano()+" "+b.getAscolti()+"\n");
    	}

    }

    @FXML
    void doMaxBraniComuni(ActionEvent event) {
    	DayOfWeek d = boxGiorno.getValue();
    	model.creaGrafo(d);
    	model.avviaRicorsione();
    }

    @FXML
    void initialize() {
        assert boxGiorno != null : "fx:id=\"boxGiorno\" was not injected: check your FXML file 'MusicB.fxml'.";
        assert btnCitta != null : "fx:id=\"btnCitta\" was not injected: check your FXML file 'MusicB.fxml'.";
        assert btnBraniComuni != null : "fx:id=\"btnBraniComuni\" was not injected: check your FXML file 'MusicB.fxml'.";
        assert txtResult != null : "fx:id=\"txtResult\" was not injected: check your FXML file 'MusicB.fxml'.";

    }

	public void setModel(Model model) {
		this.model = model;
		boxGiorno.getItems().addAll(model.getDays());
		
	}
}
