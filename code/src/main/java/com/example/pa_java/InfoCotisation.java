package com.example.pa_java;

import javafx.event.ActionEvent;
import javafx.scene.Node;
import javafx.scene.Scene;
import javafx.stage.Stage;

import java.io.IOException;
import java.sql.SQLException;

public class InfoCotisation extends MenupageController{
    private int userId;

    /*
    Fonc pour menu
    return Home page
    return conversion page
    return option page
     */
    public void menu_bar(ActionEvent event) throws IOException, SQLException {

        Node node = (Node) event.getSource();
        Stage stage = (Stage) node.getScene().getWindow();
        Scene scene = stage.getScene();

        Node node_data = (Node) event.getSource() ;
        String data = (String) node_data.getUserData();
        int btn_nb = Integer.parseInt(data);
        switch (btn_nb) {
            case 1 -> loadhomepage(scene, userId);
            case 2 -> loadconversionpage(scene, userId);
            case 3 -> loadoptionpage(scene, userId);
            default -> System.out.println("erreur");
        }

    }

    public void setUser(int userId) {
        this.userId = userId;
    }
}
