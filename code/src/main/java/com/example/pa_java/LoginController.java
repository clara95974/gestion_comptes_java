package com.example.pa_java;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.stage.Stage;

import java.math.BigInteger;
import java.nio.charset.StandardCharsets;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.sql.*;


public class LoginController {
    @FXML
    private Label jl_error;

    @FXML
    private TextField tf_mail;

    @FXML
    private PasswordField pf_mdp;

    /*
    Fonc pour verif si champs vide
     */
    @FXML
    protected void submit(Scene scene) throws NoSuchAlgorithmException {
        if (tf_mail.getText().isBlank())
            jl_error.setText("Veuillez saisir un email");
        else if (pf_mdp.getText().isBlank())
            jl_error.setText("Veuillez saisir un mot de passe");
        else
            connexion(tf_mail.getText(), pf_mdp.getText(),scene);//verifier identifiant
    }

    /**
     * @param input
     * @return
     * @throws NoSuchAlgorithmException
     */
    //chiffrer mdp en sha256
    public static byte[] getSHA(String input) throws NoSuchAlgorithmException
    {
        // Static getInstance method is called with hashing SHA
        MessageDigest md = MessageDigest.getInstance("SHA-256");

        // digest() method called
        // to calculate message digest of an input
        // and return array of byte
        return md.digest(input.getBytes(StandardCharsets.UTF_8));
    }

    //chiffrer transform Byte en StringHex
    public static String toHexString(byte[] hash)
    {
        // Convert byte array into signum representation
        BigInteger number = new BigInteger(1, hash);

        // Convert message digest into hex value
        StringBuilder hexString = new StringBuilder(number.toString(16));

        // Pad with leading zeros
        while (hexString.length() < 64)
        {
            hexString.insert(0, '0');
        }

        return hexString.toString();
    }

        /*
        Fonc pour verif champs avec bdd + load home page
         */
    private void connexion(String mail, String mdp, Scene scene) throws NoSuchAlgorithmException {
        try {
            PreparedStatement stmt;
            ResultSet result ;
            Connection con ;
            con = DriverManager.getConnection("jdbc:mysql://localhost:3306/PointLand", "root", "root"); //co bdd

            stmt = con.prepareStatement("Select * from entreprise where entreprise_mail = ?");//verif email
            stmt.setString(1, mail);
            result = stmt.executeQuery();
            if (!result.isBeforeFirst()) {
                jl_error.setText("Aucun compte en correspond à cet email");
            } else {
                try {
                    stmt = con.prepareStatement("Select * from entreprise where entreprise_mail = ? and entreprise_pwd = ?");//verif combinaison
                    stmt.setString(1, mail);
                    //stmt.setString(2, mdp);
                    stmt.setString(2, toHexString(getSHA(mdp)));
                    result = stmt.executeQuery();



                    if (!result.isBeforeFirst()) {
                        jl_error.setText("Indentifiant invalid");
                    } else {//changer page

                        result.next();
                        int userId = result.getInt("entreprise_id");
                        String username = result.getString("entreprise_name");
                        String usersiret = result.getString("entreprise_siret");
                        int usercotisation = result.getInt("id_contribution");

                        FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("home_view.fxml"));
                        Parent root = fxmlLoader.load();

                        HomeController controller = fxmlLoader.getController();
                        controller.setUser(userId, username,usersiret, usercotisation);

                        scene.setRoot(root);
                        con.close();//fermer la connection
                    }

                } catch (SQLException e) {
                    e.printStackTrace();
                    System.out.println("Erreur");
                    System.exit(0);
                }

            }

        } catch (Exception e) {
            e.printStackTrace();
            System.exit(0);
        }
    }

    /*
    Fonc pour lancer submit
     */
    @FXML
    public void onclicksubmit(ActionEvent event) throws NoSuchAlgorithmException {

        Node node = (Node) event.getSource();
        Stage stage = (Stage) node.getScene().getWindow();
        Scene scene = stage.getScene();

        submit(scene);
    }

}