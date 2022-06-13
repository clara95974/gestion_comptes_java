package com.example.pa_java;


import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.stage.Stage;

import java.io.IOException;
import java.sql.*;

public class HomeController extends MenupageController {
    public int userId;


    @FXML
    private Label jl_username;

    @FXML
    private Label jl_usersiret;

    @FXML
    private Label jl_usercotisation;

    @FXML
    private Label jl_usernbproduit;

    @FXML
    private Label jl_usernbemployé;

    public void setUser(int userId, String username, String usersiret, int userCotisation) {
        this.userId = userId;

        jl_username.setText(username);
        jl_usersiret.setText(usersiret);

        try {
            PreparedStatement stmt;
            ResultSet result;
            Connection con;
            con = DriverManager.getConnection("jdbc:mysql://localhost:3306/PointLand", "root", "root"); //co bdd

            stmt = con.prepareStatement("Select contribution_prix from contribution where contribution_id = ?");//verif email
            stmt.setInt(1, userCotisation);
            result = stmt.executeQuery();

            result.next();
            jl_usercotisation.setText(result.getInt("contribution_prix") + "€" );

            stmt = con.prepareStatement("Select count(item_id) from item where id_entreprise = ?");//verif email
            stmt.setInt(1, userId);
            result = stmt.executeQuery();

            result.next();
            jl_usernbproduit.setText(String.valueOf(result.getInt("count(item_id)")));

            stmt = con.prepareStatement("Select count(client_id) from client where id_entreprise = ?");//verif email
            stmt.setInt(1, userId);
            result = stmt.executeQuery();

            result.next();
            jl_usernbemployé.setText(String.valueOf(result.getInt("count(client_id)")));

            con.close();
        } catch (SQLException e) {
            throw new RuntimeException(e);
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
    Fonc pour changer scene
    return cotisation page
     */

    public void loadcotisation(ActionEvent event) throws IOException, SQLException {
        Node node = (Node) event.getSource();
        Stage stage = (Stage) node.getScene().getWindow();
        Scene scene = stage.getScene();

        FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("cotisation_view.fxml"));
        Parent root = fxmlLoader.load();

        CotisationController controller = fxmlLoader.getController();
        controller.setUser(userId);

        scene.setRoot(root);
    }

    /*
    Fonc pour changer scene
    return employe page
     */
    public void loademploye(ActionEvent event) throws IOException, SQLException {
        Node node = (Node) event.getSource();
        Stage stage = (Stage) node.getScene().getWindow();
        Scene scene = stage.getScene();

        FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("listemploye_view.fxml"));
        Parent root = fxmlLoader.load();

        ListEmployeController controller = fxmlLoader.getController();
        controller.setUser(userId);

        scene.setRoot(root);
    }

    /*
    Fonc pour changer scene
    return produit page
     */
    public void loadproduit(ActionEvent event) throws IOException, SQLException {
        Node node = (Node) event.getSource();
        Stage stage = (Stage) node.getScene().getWindow();
        Scene scene = stage.getScene();

        FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("listproduct_view.fxml"));
        Parent root = fxmlLoader.load();

        ListProductController controller = fxmlLoader.getController();
        controller.setUser(userId);

        scene.setRoot(root);
    }
}
