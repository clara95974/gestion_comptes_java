package com.example.pa_java;

import javafx.event.ActionEvent;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.stage.Stage;

import java.io.IOException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;

public class AddProductController extends MenupageController{
    private int userId;

    public TextField add_nm;
    public TextField add_brand;
    public TextField add_ref;
    public TextField add_type;
    public TextArea add_description;
    public TextField add_price_buy;
    public TextField add_point_w;
    public TextField add_price_sell;
    public TextField add_point_l;
    public Label jl_error;

    /**
     * @param userId
     */
    public void setUser(int userId) {
        this.userId = userId;
    }

    /**
     * Fonc pour menu
     *     return Home page
     *     return conversion page
     *     return option page
     * @param event
     * @throws IOException
     * @throws SQLException
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

    /**
     * @param event
     * @throws SQLException
     * @throws IOException
     *
     * Fonc pour add product
     *     verif si aucun champs null
     *     connexion bdd puis Insert into
     *     return List produit page
     */
    public void add(ActionEvent event) throws SQLException, IOException {

        jl_error.setText("");
        if (add_nm.getText().isBlank() || add_brand.getText().isBlank() || add_ref.getText().isBlank() || add_type.getText().isBlank() || add_description.getText().isBlank()|| add_price_buy.getText().isBlank() || add_price_sell.getText().isBlank() || add_point_l.getText().isBlank() || add_point_w.getText().isBlank())
            jl_error.setText("Veuillez saisir toutes les infos");
        else {
            boolean b = true;
            try {
                Double isnb1 = Double.parseDouble(add_price_buy.getText());
                Double isnb2 = Double.parseDouble(add_price_sell.getText());
            } catch (NumberFormatException e) {
                b = false;
            }
            if (!b || Double.parseDouble(add_price_buy.getText())<=0.0 || Double.parseDouble(add_price_sell.getText())<=0.0) {
                jl_error.setText("Veuillez saisir un prix correct");
            }

            b = true;
            try {
                int isnb1 = Integer.parseInt(add_point_l.getText());
                int isnb2 = Integer.parseInt(add_point_w.getText());
            } catch (NumberFormatException e) {
                b = false;
            }
            if (!b || Integer.parseInt(add_point_l.getText())<=0 || Integer.parseInt(add_point_w.getText())<=0) {
                jl_error.setText("Veuillez saisir un nombre de point correct");
            }


            if (jl_error.getText().isBlank()) {
                PreparedStatement stmt;
                Connection con;
                con = DriverManager.getConnection("jdbc:mysql://localhost:3306/PointLand", "root", "root"); //co bdd

                stmt = con.prepareStatement("INSERT into item (item_name,item_type,item_brand,item_description,item_price_buy,item_price_sell,item_ref,item_point_l, item_point_w,item_image,id_entreprise) Values(?, ?, ?, ?,?, ?,?,?,?,?,?)");
                stmt.setString(1, String.valueOf(add_nm.getText()));
                stmt.setString(2,  String.valueOf(add_type.getText()));
                stmt.setString(3,  String.valueOf(add_brand.getText()));
                stmt.setString(4,  String.valueOf(add_description.getText()));
                stmt.setString(5,  String.valueOf(add_price_buy.getText()));
                stmt.setString(6,  String.valueOf(add_price_sell.getText()));
                stmt.setString(7,  String.valueOf(add_ref.getText()));
                stmt.setString(8,  String.valueOf(add_point_l.getText()));
                stmt.setString(9,  String.valueOf(add_point_w.getText()));
                stmt.setString(10, "null");
                stmt.setString(11, String.valueOf(userId));
                int nbmaj = stmt.executeUpdate();
                if (nbmaj == 1) {
                    Node node = (Node) event.getSource();
                    Stage stage = (Stage) node.getScene().getWindow();
                    Scene scene = stage.getScene();

                    FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("listproduct_view.fxml"));
                    Parent root = fxmlLoader.load();
                    ListProductController controller = fxmlLoader.getController();
                    controller.setUser(userId);
                    scene.setRoot(root);
                } else {
                    jl_error.setText("Erreur lors de l'ajout");
                }
                con.close();
            }
        }
    }

}
