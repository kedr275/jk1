package Controller;

import javafx.application.Platform;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.stage.FileChooser;
import javafx.stage.Stage;

import java.io.IOException;
import java.io.OutputStream;
import java.io.PrintStream;
import java.net.URL;
import java.util.ResourceBundle;

import static Controller.Parserexel.count_row;

/**
 * Created by jeka on 26.11.2015.
 */
public class Controller_w1 implements Initializable {

    @FXML private static Button bnOk;
    @FXML private static Button bnClear;
    @FXML private static Button bnfindfile1;
    @FXML private static Button bnfindfile2;
    @FXML private static Button bnNo;

    @FXML private  TextField txtlogin;
    @FXML private  TextField txtipset;
    @FXML private  TextField txtbiset;
    @FXML private  PasswordField txtpasswd;
    @FXML public TextArea txtLog;


    public String filesearch() {
        Stage stage = new Stage();
        //Fill stage with content
        FileChooser fileopen = new FileChooser();
        fileopen.setTitle("");
        fileopen.getExtensionFilters().addAll(
                new FileChooser.ExtensionFilter("All Images", "*.*")
//                new FileChooser.ExtensionFilter("Exel", "*.xlsx", "*.xls")
        );

        GlobalVars.FileName = fileopen.showOpenDialog(stage);

        String filepatch = GlobalVars.FileName.getAbsolutePath();



        return filepatch;
    }


    @Override
    public void initialize(URL location, ResourceBundle resources) {

        OutputStream out = new OutputStream() {
            @Override
            public void write(int b) throws IOException {
                appendText(String.valueOf((char) b));
            }
        };
        System.setOut(new PrintStream(out, true));
        System.setErr(new PrintStream(out, true));


    }

    @FXML private void bnfindfile1act(ActionEvent event) {

         txtipset.setText(filesearch());
        GlobalVars.FileNameIP = GlobalVars.FileName;

    }
    @FXML private void bnfindfile2act(ActionEvent event) {
        txtbiset.setText(filesearch());
        GlobalVars.FileNameBI = GlobalVars.FileName;
    }


    @FXML private void bnOkAct (ActionEvent event) {
        GlobalVars.VCenteruser = txtlogin.getText();
        GlobalVars.Vcenterpassword = txtpasswd.getText();

//       Controller_w2.ShowTableColumns();



    }




    @FXML private void bnNoAct (ActionEvent event) {}
    @FXML private void bnClearAct (ActionEvent event) {

        txtLog.setText("");

    }

    public void appendText(String str) {
        Platform.runLater(() -> txtLog.appendText(str));
    }

}
