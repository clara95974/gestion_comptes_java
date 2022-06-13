package com.example.pa_java;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.stage.Stage;

import java.io.IOException;
import java.sql.*;


public class OptionController extends MenupageController {
    public int userId;

    @FXML
    private Label opt_nm_entreprise;

    @FXML
    private Label jl_error;


    @FXML
    private Label opt_siret_entreprise;

    @FXML
    private Label opt_tel_entreprise;

    @FXML
    private Label opt_mail_entreprise;

    @FXML
    private Label date_contrat;

    @FXML
    private TextField nm_modif_entreprise;

    @FXML
    private TextField siret_modif_entreprise;

    @FXML
    private TextField tel_modif_entreprise;

    @FXML
    private TextField mail_modif_entreprise;


    public void setUser(int userId, int page) throws SQLException {
        this.userId = userId;

        PreparedStatement stmt;
        ResultSet result;
        Connection con;
        con = DriverManager.getConnection("jdbc:mysql://localhost:3306/PointLand", "root", "root"); //co bdd

        stmt = con.prepareStatement("Select * from entreprise where entreprise_id = ?");//verif combinaison
        stmt.setString(1, String.valueOf(userId));
        result = stmt.executeQuery();
        result.next();
        if (page == 1) {
            opt_nm_entreprise.setText(result.getString("entreprise_name"));
            opt_siret_entreprise.setText(result.getString("entreprise_siret"));
            opt_tel_entreprise.setText(result.getString("entreprise_tel"));
            opt_mail_entreprise.setText(result.getString("entreprise_mail"));
            date_contrat.setText(result.getString("entreprise_dte_registration") + " - " + result.getString("entreprise_dte_end"));
        } else{
            nm_modif_entreprise.setText(result.getString("entreprise_name"));
            siret_modif_entreprise.setText(result.getString("entreprise_siret"));
            tel_modif_entreprise.setText(result.getString("entreprise_tel"));
            mail_modif_entreprise.setText(result.getString("entreprise_mail"));
        }
    }



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


    /*
    Fonc pour modif info entreprise
    load page modify view
     */
    public void modifier_info(ActionEvent event) throws IOException, SQLException {
        Node node = (Node) event.getSource();
        Stage stage = (Stage) node.getScene().getWindow();
        Scene scene = stage.getScene();

        FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("modify_view.fxml"));
        Parent root = fxmlLoader.load();

        OptionController controller = fxmlLoader.getController();
        controller.setUser(userId,2);
        scene.setRoot(root);
    }


    /*
    Fonc pour annuler action
    load option view
     */
    public void annule(ActionEvent event) throws IOException, SQLException {
        Node node = (Node) event.getSource();
        Stage stage = (Stage) node.getScene().getWindow();
        Scene scene = stage.getScene();

        FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("option_view.fxml"));
        Parent root = fxmlLoader.load();
        OptionController controller = fxmlLoader.getController();
        controller.setUser(userId,1);
        scene.setRoot(root);
    }


    /*
    Fonc pour valider changement
    update bdd + load option view
     */
    public void validate(ActionEvent event) throws SQLException, IOException {
        jl_error.setText("");
        if (nm_modif_entreprise.getText().isBlank() || siret_modif_entreprise.getText().isBlank() || tel_modif_entreprise.getText().isBlank() || mail_modif_entreprise.getText().isBlank())
            jl_error.setText("Veuillez saisir toutes les infos");
        else {
            boolean b = true;
            try {
                int isnb = Integer.parseInt(tel_modif_entreprise.getText());
            } catch (NumberFormatException e) {
                b = false;
            }
            if (!b || tel_modif_entreprise.getText().length() != 10 || Character.getNumericValue(tel_modif_entreprise.getText().charAt(0)) != 0) {
                jl_error.setText("Veuillez saisir un numeros de telephone valide");
            }
            if (!mail_modif_entreprise.getText().matches("^[\\w-\\.]+@([\\w-]+\\.)+[\\w-]{2,4}"))
                jl_error.setText("Veuillez saisir un mail valide");
            if (siret_modif_entreprise.getText().length() != 14)
                jl_error.setText("Veuillez saisir un siret valide");

            if (jl_error.getText().isBlank()) {
                PreparedStatement stmt;
                Connection con;
                con = DriverManager.getConnection("jdbc:mysql://localhost:3306/PointLand", "root", "root"); //co bdd

                stmt = con.prepareStatement("UPDATE entreprise set entreprise_name=?,entreprise_siret = ?,entreprise_tel = ?,entreprise_mail = ? where entreprise_id = ?");//verif combinaison
                stmt.setString(1, nm_modif_entreprise.getText());
                stmt.setString(2, siret_modif_entreprise.getText());
                stmt.setString(3, tel_modif_entreprise.getText());
                stmt.setString(4, mail_modif_entreprise.getText());
                stmt.setString(5, String.valueOf(userId));
                int nbmaj = stmt.executeUpdate();
                if (nbmaj == 1) {
                    annule(event);
                } else {
                    jl_error.setText("Erreur lors de la modification");
                }
                con.close();
            }

        }
    }
}
