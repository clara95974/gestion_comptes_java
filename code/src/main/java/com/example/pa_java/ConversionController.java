package com.example.pa_java;

import javafx.event.ActionEvent;
import javafx.scene.Node;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.stage.Popup;
import javafx.stage.Stage;

import java.io.IOException;
import java.sql.SQLException;

public class ConversionController extends MenupageController {
    public int userId;
    public TextField tf_points;
    int points=0;

    /**
     * @param userId
     */
    public void setUser(int userId) {
        this.userId = userId;
    }

    /**
     * @param event
     * @throws IOException
     * @throws SQLException
     *
     * Fonc pour menu
     *     return Home page
     *     return conversion page
     *     return option page
     */
    public void menu_bar(ActionEvent event) throws IOException, SQLException {

        Node node = (Node) event.getSource();
        Stage stage = (Stage) node.getScene().getWindow();
        Scene scene = stage.getScene();

        Node node_data = (Node) event.getSource() ;
        String data = (String) node_data.getUserData();
        int btn_nb = Integer.parseInt(data);
        switch (btn_nb) {
            case 1:
                loadhomepage(scene, userId);
                break;
            case 2:
                loadconversionpage(scene,userId);
                break;
            case 3:
                loadoptionpage(scene,userId);
                break;
            default:
                System.out.println("erreur");
                break;
        }

    }

    /**
     * Fonc pour add point
     *         ajout 1 point a var point
     */
    public void addpoints(){

        if (!tf_points.getText().isBlank())
            points = Integer.parseInt(tf_points.getText());

        points += 1;
        tf_points.setText(String.valueOf(points));
    }

    /**
     * Fonc pour remove point
     *         enlever 1 point a var point
     */
    public void removepoints(){
        if (!tf_points.getText().isBlank())
            points = Integer.parseInt(tf_points.getText());
        if (points>0){
            points -= 1;

            tf_points.setText(String.valueOf(points));
        }
    }



    /**
     * @param event
     * Fonc pour convert point
     *  pres des euros qu'on peux obtenir grace aux points
     */
    public void convertirpoints(ActionEvent event) {
            Label label;
            Button btn_close;

            btn_close = new Button("close");

            Node node = (Node) event.getSource();
            Stage stage = (Stage) node.getScene().getWindow();
            stage.setTitle("pointland");
        if (!(tf_points.getText().isBlank())){
            points = Integer.parseInt(tf_points.getText());

            double euros = points*0.015;
            label = new Label(String.valueOf(euros));

        }else {
            label = new Label("Veuillez saisir le nombre de points à convertir");
        }

            Popup conv = new Popup();

            label.setStyle("-fx-background-color: grey; -fx-border-radius: 10; -fx-border-color: black;");
            conv.getContent().add(label);
            conv.getContent().add(btn_close);
            label.setMinWidth(390);
            label.setMinHeight(189);

            conv.show(stage);

        btn_close.setOnAction(event1 -> conv.hide());
    }
}
