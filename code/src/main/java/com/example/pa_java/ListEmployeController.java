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

public class ListEmployeController extends MenupageController{
    public ListView<Employe> listview_employe;
    private int userId;

    public void setUser(int userId) throws SQLException {
        this.userId=userId;


        PreparedStatement stmt;
        ResultSet result;
        Connection con;
        con = DriverManager.getConnection("jdbc:mysql://localhost:3306/PointLand", "root", "root"); //co bdd

        stmt = con.prepareStatement("Select * from client where id_entreprise = ?");//recup donnee employe
        stmt.setInt(1, userId);
        result = stmt.executeQuery();
        ObservableList<Employe> employes = FXCollections.observableArrayList();
        while(result.next()){// while employe, mettre dans tab Employé
            int id = result.getInt("client_id");
            String name = result.getString("client_name");
            String firstname =result.getString("client_firstname");
            String mail = result.getString("client_mail");
            String country =result.getString("client_country");
            String date_inscrip =result.getString("client_date_inscription");
            Employe Newemploye= new Employe(id,name,firstname,mail,country,date_inscrip);
            employes.add(Newemploye);

        }
        con.close();
        listview_employe.getItems().addAll(employes);//creer list observable
        listview_employe.getSelectionModel().setSelectionMode(SelectionMode.SINGLE);
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
    Fonc pour load profil page
    return profil page
     */
    public void voir_profil(ActionEvent event) throws IOException {
        if (!(listview_employe.getSelectionModel().getSelectedItem() == null)){
            Node node = (Node) event.getSource();
            Stage stage = (Stage) node.getScene().getWindow();
            Scene scene = stage.getScene();

            FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("employepage.fxml"));
            Parent root = fxmlLoader.load();

            EmployepageController controller = fxmlLoader.getController();
            controller.setUser(userId,listview_employe.getSelectionModel().getSelectedItem());
            scene.setRoot(root);
        }
    }
}
