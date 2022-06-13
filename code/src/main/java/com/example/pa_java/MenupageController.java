package com.example.pa_java;

import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;

import java.io.IOException;
import java.sql.*;

public class MenupageController {
    /*
    Fonc pour menu
    return Home page
     */
    public void loadhomepage(Scene scene, int userId) throws IOException, SQLException {
        FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("home_view.fxml"));
        Parent root = fxmlLoader.load();

        PreparedStatement stmt;
        ResultSet result ;
        Connection con ;
        con = DriverManager.getConnection("jdbc:mysql://localhost:3306/PointLand", "root", "root"); //co bdd

        stmt = con.prepareStatement("Select * from entreprise where entreprise_id = ?");//verif combinaison
        stmt.setString(1, String.valueOf(userId));
        result = stmt.executeQuery();
        result.next();
        String username = result.getString("entreprise_name");
        String usersiret = result.getString("entreprise_siret");
        int usercotisation = result.getInt("id_contribution");

        HomeController controller = fxmlLoader.getController();
        controller.setUser(userId, username,usersiret, usercotisation);

        scene.setRoot(root);
    }
        /*
        Fonc pour menu
        return conversion page
         */
    public void loadconversionpage(Scene scene,int userId) throws IOException {
        FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("conversion_view.fxml"));
        Parent root = fxmlLoader.load();
        ConversionController controller = fxmlLoader.getController();
        controller.setUser(userId);
        scene.setRoot(root);


    }

    /*
    Fonc pour menu
    return option page
     */
    public void loadoptionpage(Scene scene,int userId) throws IOException, SQLException {
        FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("option_view.fxml"));
        Parent root = fxmlLoader.load();

        OptionController controller = fxmlLoader.getController();
        controller.setUser(userId,1);

        scene.setRoot(root);
    }
}
