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

public class Produitpage extends MenupageController{
    public TextField modif_nm;
    public TextField modif_brand;
    public TextField modif_ref;
    public TextField modif_type;
    public TextArea modif_description;
    public TextField modif_price_buy;
    public TextField modif_point_w;
    public TextField modif_price_sell;
    public TextField modif_point_l;
    public Label jl_error;
    private int userId;
    private Produit item;

    public void setUser(int userId, Produit selectedItem) throws SQLException {
        this.userId = userId;
        this.item= selectedItem;

        modif_nm.setText(selectedItem.name);
        modif_brand.setText(selectedItem.brand_item);
        modif_ref.setText(selectedItem.ref);
        modif_type.setText(selectedItem.type);
        modif_description.setText(selectedItem.description);
        modif_price_buy.setText(String.valueOf(selectedItem.prix_buy));
        modif_price_sell.setText(String.valueOf(selectedItem.prix_sell));
        modif_point_l.setText(String.valueOf(selectedItem.point_lose));
        modif_point_w.setText(String.valueOf(selectedItem.point_win));
    }

    /*
    Fonc pour valider changement
    update bdd + load listproduit view
     */
    public void modifier_info(ActionEvent event) throws SQLException, IOException {
        jl_error.setText("");
        if (modif_nm.getText().isBlank() || modif_brand.getText().isBlank() || modif_ref.getText().isBlank() || modif_type.getText().isBlank() || modif_description.getText().isBlank()|| modif_price_buy.getText().isBlank() || modif_price_sell.getText().isBlank() || modif_point_l.getText().isBlank() || modif_point_w.getText().isBlank())
            jl_error.setText("Veuillez saisir toutes les infos");
        else {
            boolean b = true;
            try {
                Double isnb1 = Double.parseDouble(modif_price_buy.getText());
                Double isnb2 = Double.parseDouble(modif_price_sell.getText());
            } catch (NumberFormatException e) {
                b = false;
            }
            if (!b || Double.parseDouble(modif_price_buy.getText())<=0.0 || Double.parseDouble(modif_price_sell.getText())<=0.0) {
                jl_error.setText("Veuillez saisir un prix correct");
            }

            b = true;
            try {
                int isnb1 = Integer.parseInt(modif_point_l.getText());
                int isnb2 = Integer.parseInt(modif_point_w.getText());
            } catch (NumberFormatException e) {
                b = false;
            }
            if (!b || Integer.parseInt(modif_point_l.getText())<=0 || Integer.parseInt(modif_point_w.getText())<=0) {
                jl_error.setText("Veuillez saisir un nombre de point correct");
            }


            if (jl_error.getText().isBlank()) {
                PreparedStatement stmt;
                Connection con;
                con = DriverManager.getConnection("jdbc:mysql://localhost:3306/PointLand", "root", "root"); //co bdd

                stmt = con.prepareStatement("UPDATE item set item_name=?,item_type = ?,item_brand = ?,item_description = ?,item_price_buy=?,item_price_sell = ?,item_ref = ?,item_point_l = ?, item_point_w = ? where item_id = ?");
                stmt.setString(1, modif_nm.getText());
                stmt.setString(2, modif_type.getText());
                stmt.setString(3, modif_brand.getText());
                stmt.setString(4, modif_description.getText());
                stmt.setString(5, modif_price_buy.getText());
                stmt.setString(6, modif_price_sell.getText());
                stmt.setString(7, modif_ref.getText());
                stmt.setString(8, modif_point_l.getText());
                stmt.setString(9, modif_point_w.getText());
                stmt.setString(10, String.valueOf(item.id));
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

    /*
    Fonc pour annuler action
    load listproduit view
     */
    public void annule(ActionEvent event) throws IOException, SQLException {
        Node node = (Node) event.getSource();
        Stage stage = (Stage) node.getScene().getWindow();
        Scene scene = stage.getScene();

        FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("listproduct_view.fxml"));
        Parent root = fxmlLoader.load();
        ListProductController controller = fxmlLoader.getController();
        controller.setUser(userId);
        scene.setRoot(root);
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
}
