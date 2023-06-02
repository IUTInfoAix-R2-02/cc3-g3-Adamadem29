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
import javafx.scene.control.Label;
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

    private ListView<Line> lesLignes;

    @FXML
    private Line cpt1Line;

    @FXML
    private Line cpt2Line;

    @FXML
    private Line cpt3Line;

    @FXML
    private Line cpt4Line;

    @FXML
    private Line cpt5Line;

    @FXML
    private Line cpt6Line;

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

    private ListView<TextField> lesTextField;
    @FXML
    private TextField cpt1Field;

    @FXML
    private TextField cpt2Field;

    @FXML
    private TextField cpt3Field;

    @FXML
    private TextField cpt4Field;

    @FXML
    private TextField cpt5Field;

    @FXML
    private TextField cpt6Field;

    @FXML
    private Label errorLbl1;

    @FXML
    private Label errorLbl2;


    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        lesCercles = new ListView();
        lesCercles.getItems().addAll(cpt1Circle,cpt2Circle,cpt3Circle,cpt4Circle,cpt5Circle,cpt6Circle);
        lesTextField = new ListView();
        lesTextField.getItems().addAll(cpt1Field,cpt2Field,cpt3Field,cpt4Field,cpt5Field,cpt6Field);
        lesLignes = new ListView();
        lesLignes.getItems().addAll(cpt1Line,cpt2Line,cpt3Line,cpt4Line,cpt5Line,cpt6Line);
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
            errorLbl1.setVisible(true);
            errorLbl2.setVisible(true);
            return;
        }
        errorLbl1.setVisible(false);
        errorLbl2.setVisible(false);
        int centerX = getXRadarChart(value, axe);
        int centerY = getYRadarChart(value, axe);
        Circle leCercle = lesCercles.getItems().get(axe-1);
        leCercle.setCenterX(centerX);
        leCercle.setCenterY(centerY);
        leCercle.setVisible(true);
    }

    @FXML
    public void handleViderButtonClick(ActionEvent event){
        errorLbl1.setVisible(false);
        errorLbl2.setVisible(false);
        for (Circle cercle : lesCercles.getItems()){
            cercle.setVisible(false);
        }
        for (TextField champ : lesTextField.getItems()){
            champ.setText("");
        }
    }

    @FXML
    public void handleTracerButtonClick(ActionEvent event){
        Line laLigne;
        Circle lePoint1;
        Circle lePoint2;
        for (TextField champ : lesTextField.getItems()){
            champ.setOnAction(handleOnActionField(event));
        }
        for (int i=0; i<lesLignes.getItems().size(); ++i){
            laLigne = lesLignes.getItems().get(i);
            lePoint1 = lesCercles.getItems().get(i);
            if (i==5){
                lePoint2 = lesCercles.getItems().get(0);
            }
            else{
                lePoint2 = lesCercles.getItems().get(i+1);
            }
            laLigne.setStartX(lePoint1.getCenterX());
            laLigne.setStartY(lePoint1.getCenterY());
            laLigne.setEndX(lePoint2.getCenterX());
            laLigne.setEndY(lePoint2.getCenterY());
            laLigne.setVisible(true);
        }
    }

}
