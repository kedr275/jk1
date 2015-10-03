package controller;

import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.event.ActionEvent;
import javafx.stage.FileChooser;
import javafx.stage.Modality;
import javafx.stage.Stage;
import pojo.GlobalVars;


import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

/**
 * Created by jeka on 23.09.2015.
 */
public class Controllerw0 implements Initializable {

    @FXML public static Button bntfindfile1;
    @FXML public static Button bntfindfile2;
    @FXML public static Button bntok;
    @FXML public static TextField txtlogin;
    @FXML public static TextField txtipset;
    @FXML public static TextField txtbiset;


    public String filesearch() {
        Stage stage = new Stage();
        //Fill stage with content
        FileChooser fileopen = new FileChooser();
        fileopen.setTitle("");
        fileopen.getExtensionFilters().addAll(
                //    new FileChooser.ExtensionFilter("All Images", "*.*"),
                new FileChooser.ExtensionFilter("Exel", "*.xlsx")
        );

        GlobalVars.FileName = fileopen.showOpenDialog(stage);

        String filepatch = GlobalVars.FileName.getAbsolutePath();


    return filepatch;
    }

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {

    }
    @FXML private void bnfindfile1act(ActionEvent event) {

        Controllerw0.txtipset.setText(filesearch());
        GlobalVars.FileNameIP = GlobalVars.FileName;

    }
    @FXML private void bnfindfile2act(ActionEvent event) {
        Controllerw0.txtbiset.setText(filesearch());
        GlobalVars.FileNameBI = GlobalVars.FileName;
    }

    @FXML private void bntokact (ActionEvent event) throws IOException {
        //hide this current window (if this is whant you want)
        ((Node)(event.getSource())).getScene().getWindow().hide();

        FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("../view/w.fxml"));
        Parent root = (Parent) fxmlLoader.load();
        Stage stage = new Stage();
        stage.initModality(Modality.APPLICATION_MODAL);
        stage.setOpacity(1);
        stage.setTitle("");
        stage.setResizable(false);
        stage.setScene(new Scene(root));
        stage.showAndWait();



    }
}
