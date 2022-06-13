package com.example.pa_java;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.ListView;
import javafx.scene.control.SelectionMode;
import javafx.stage.Stage;

import java.io.IOException;
import java.sql.*;

public class ListProductController extends MenupageController{
    private int userId;

    public ListView<Produit> listview_produit;

    public void setUser(int userId) throws SQLException {
        this.userId = userId;

        PreparedStatement stmt;
        ResultSet result;
        Connection con;
        con = DriverManager.getConnection("jdbc:mysql://localhost:3306/PointLand", "root", "root"); //co bdd

        stmt = con.prepareStatement("Select * from item where id_entreprise = ?");//recup donnee employe
        stmt.setInt(1, userId);
        result = stmt.executeQuery();
        ObservableList<Produit> produits = FXCollections.observableArrayList();
        while(result.next()){// while produit, mettre dans tab Produit
            int id = result.getInt("item_id");
            String type = result.getString("item_type");
            String brand_item =result.getString("item_brand");
            String name = result.getString("item_name");
            String description =result.getString("item_description");
            double prix_buy = result.getDouble("item_price_buy");
            double prix_sell =result.getDouble("item_price_sell");
            String ref =result.getString("item_ref");
            int point_l = result.getInt("item_point_l");
            int point_w =result.getInt("item_point_w");
            Produit Newproduit= new Produit(id,type,brand_item,name,description,prix_buy,prix_sell,ref,point_l,point_w);
            produits.add(Newproduit);

        }
        con.close();
        listview_produit.getItems().addAll(produits);//creer list observable
        listview_produit.getSelectionModel().setSelectionMode(SelectionMode.SINGLE);

    }

    /*
    Fonc pour load modif produit
    return modif produit page
     */
    public void modif_produit(ActionEvent event) throws SQLException, IOException {

        if (!(listview_produit.getSelectionModel().getSelectedItem() == null)){
            Node node = (Node) event.getSource();
            Stage stage = (Stage) node.getScene().getWindow();
            Scene scene = stage.getScene();

            FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("produitpage.fxml"));
            Parent root = fxmlLoader.load();

            Produitpage controller = fxmlLoader.getController();
            controller.setUser(userId,listview_produit.getSelectionModel().getSelectedItem());
            scene.setRoot(root);
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


    public void add_produit(ActionEvent event) throws IOException {
                Node node = (Node) event.getSource();
                Stage stage = (Stage) node.getScene().getWindow();
                Scene scene = stage.getScene();

                FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("addproduct_view.fxml"));
                Parent root = fxmlLoader.load();
                AddProductController controller = fxmlLoader.getController();
                controller.setUser(userId);
                scene.setRoot(root);
        }

    public void supprimer_produit(ActionEvent event) throws SQLException, IOException {
        if (!(listview_produit.getSelectionModel().getSelectedItem() == null)){
            PreparedStatement stmt;
            Connection con;
            con = DriverManager.getConnection("jdbc:mysql://localhost:3306/PointLand", "root", "root"); //co bdd

            stmt = con.prepareStatement("DELETE from item where item_id = ?");
            stmt.setInt(1, listview_produit.getSelectionModel().getSelectedItem().id);
            stmt.executeUpdate();
            int nbmaj = stmt.executeUpdate();
            if (nbmaj == 1) {
                con.close();
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
    }
}
