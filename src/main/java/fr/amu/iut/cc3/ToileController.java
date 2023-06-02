package fr.amu.iut.cc3;

import javafx.beans.binding.Bindings;
import javafx.beans.property.DoubleProperty;
import javafx.beans.property.SimpleDoubleProperty;
import javafx.beans.property.SimpleListProperty;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.collections.FXCollections;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.control.Button;
import javafx.scene.control.ListView;
import javafx.scene.control.TextField;
import javafx.scene.input.KeyEvent;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Pane;
import javafx.scene.layout.VBox;
import javafx.scene.shape.Circle;
import javafx.scene.shape.Line;
import javafx.scene.text.Text;

import java.net.URL;
import java.util.ArrayList;
import java.util.List;
import java.util.ResourceBundle;

public class ToileController implements Initializable {

    private static int rayonCercleExterieur = 200;
    private static int angleEnDegre = 60;
    private static int angleDepart = 90;
    private static int noteMaximale = 20;

    private TextField sourceOfEvent;

    private ListView<Circle> lesCercles;

    @FXML
    private Circle cpt1Circle;

    @FXML
    private Circle cpt2Circle;

    @FXML
    private Circle cpt3Circle;

    @FXML
    private Circle cpt4Circle;

    @FXML
    private Circle cpt5Circle;

    @FXML
    private Circle cpt6Circle;

    @FXML
    private TextField cpt1Field;

    @FXML
    private


    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        lesCercles = new ListView();
        lesCercles.getItems().addAll(cpt1Circle,cpt2Circle,cpt3Circle,cpt4Circle,cpt5Circle,cpt6Circle);
    }

    int getXRadarChart(double value, int axe ){
        return (int) (rayonCercleExterieur + Math.cos(Math.toRadians(angleDepart - (axe-1)  * angleEnDegre)) * rayonCercleExterieur
                *  (value / noteMaximale));
    }

    int getYRadarChart(double value, int axe ){
        return (int) (rayonCercleExterieur - Math.sin(Math.toRadians(angleDepart - (axe-1)  * angleEnDegre)) * rayonCercleExterieur
                *  (value / noteMaximale))+10;
    }

    @FXML
    public void handleOnActionField(ActionEvent event){
        sourceOfEvent = (TextField) event.getSource();
        int axe = GridPane.getRowIndex(sourceOfEvent)+1;
        double value = Integer.parseInt(sourceOfEvent.getText());
        if (value>20 || value<0){

        }
        int centerX = getXRadarChart(value, axe);
        int centerY = getYRadarChart(value, axe);
        Circle leCercle = lesCercles.getItems().get(axe-1);
        leCercle.setCenterX(centerX);
        leCercle.setCenterY(centerY);
        leCercle.setVisible(true);
    }

}
