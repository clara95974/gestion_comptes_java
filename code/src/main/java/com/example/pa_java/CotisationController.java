package com.example.pa_java;

import javafx.event.ActionEvent;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.stage.Stage;

import java.io.IOException;
import java.sql.*;

public class CotisationController extends MenupageController{

    
    
    public int userId;
    public Label date_debut;
    public Label date_fin;
    public Label modal_contrat;
    public Label montant_contrat;


    /**
     * @param userId
     * @throws SQLException
     */
    public void setUser(int userId) throws SQLException {
        this.userId = userId;

        PreparedStatement stmt;
        ResultSet result;
        Connection con;
        con = DriverManager.getConnection("jdbc:mysql://localhost:3306/PointLand", "root", "root"); //co bdd

        stmt = con.prepareStatement("Select * from entreprise inner join contribution on entreprise.id_contribution=contribution.contribution_id where entreprise_id = ?");//verif combinaison
        stmt.setString(1, String.valueOf(userId));
        result = stmt.executeQuery();
        result.next();

        date_debut.setText(result.getString("entreprise_dte_registration"));
        date_fin.setText(result.getString("entreprise_dte_end"));
        modal_contrat.setText(result.getString("contribution_id"));
        montant_contrat.setText(result.getString("contribution_prix"));

        con.close();
    }


    /**
     * @param event
     * @throws SQLException
     * @throws IOException
     *
     * Fonc pour menu
     *         return Home page
     */
    public void retour(ActionEvent event) throws SQLException, IOException {
        Node node = (Node) event.getSource();
        Stage stage = (Stage) node.getScene().getWindow();
        Scene scene = stage.getScene();
        loadhomepage(scene, userId);
    }

    /**
     * @param event
     * @throws SQLException
     * @throws IOException
     *
     * Fonc pour renouveler contrat
     *         return Cotisation page
     *         add 5 ans a date fin contrat
     */
    public void renouveler_contrat(ActionEvent event) throws SQLException, IOException {
        String[] date_fin_tab = date_fin.getText().split("-");
        int new_annee= Integer.parseInt(date_fin_tab[0])+5;
        String new_date_fin = new_annee +"-"+date_fin_tab[1]+"-"+date_fin_tab[2];
        PreparedStatement stmt;
        Connection con;
        con = DriverManager.getConnection("jdbc:mysql://localhost:3306/PointLand", "root", "root");
        stmt = con.prepareStatement("UPDATE entreprise set entreprise_dte_end=? where entreprise_id = ?");//verif combinaison
        stmt.setString(1, new_date_fin);
        stmt.setString(2, String.valueOf(userId));
        int nbmaj = stmt.executeUpdate();
        if (nbmaj == 1) {
            Node node = (Node) event.getSource();
            Stage stage = (Stage) node.getScene().getWindow();
            Scene scene = stage.getScene();

            FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("cotisation_view.fxml"));
            Parent root = fxmlLoader.load();
            CotisationController controller = fxmlLoader.getController();
            controller.setUser(userId);
            scene.setRoot(root);
        }

    }

    /**
     * @param event
     * @throws IOException
     *
     * Fonc pour voir info sur contrat
     *         return Infocotisation page
     */
    public void info_modal(ActionEvent event) throws IOException {
        Node node = (Node) event.getSource();
        Stage stage = (Stage) node.getScene().getWindow();
        Scene scene = stage.getScene();

        FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("info_cotisation.fxml"));
        Parent root = fxmlLoader.load();
        InfoCotisation controller = fxmlLoader.getController();
        controller.setUser(userId);
        scene.setRoot(root);
    }
}
