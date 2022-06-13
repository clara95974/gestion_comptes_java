package com.example.pa_java;

import javafx.event.ActionEvent;
import javafx.scene.Node;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.stage.Stage;

import java.io.IOException;
import java.sql.SQLException;

public class EmployepageController extends MenupageController{

    public Label nm_profil;
    public Label pn_profil;
    public Label mail_profil;
    public Label pays_profil;
    public Label date_profil;
    private int userId;
    private Employe employe;

    public void setUser(int userId, Employe selectedItem) {
        this.userId = userId;
        this.employe = selectedItem;
        this.nm_profil.setText(employe.name);
        this.pn_profil.setText(employe.firstname);
        this.mail_profil.setText(employe.mail);
        this.pays_profil.setText(employe.country);
        this.date_profil.setText(employe.date_inscrip);
    }

    /**
     * @param event
     * @throws IOException
     * @throws SQLException
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
            case 1 -> loadhomepage(scene, userId);
            case 2 -> loadconversionpage(scene, userId);
            case 3 -> loadoptionpage(scene, userId);
            default -> System.out.println("erreur");
        }

    }
}
