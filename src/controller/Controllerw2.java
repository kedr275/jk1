package controller;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import pojo.GlobalVars;
import pojo.NameSheet;


import javax.xml.bind.annotation.XmlElementDecl;
import java.net.URL;
import java.util.ResourceBundle;

import static controller.Parserexel.*;

/**
 * Created by jeka on 30.09.2015.
 */
public class Controllerw2 implements Initializable{

    private ObservableList<NameSheet> usersData = FXCollections.observableArrayList();

    @FXML private TableView<NameSheet> idTabView;
    @FXML private TableColumn<NameSheet, String> idtabIP;
    @FXML private TableColumn<NameSheet, String> idtabLogin;
    @FXML private TableColumn<NameSheet, String> idtabPassword;
    @FXML private TableColumn<NameSheet, String> idtabHostname;


    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        initData();

        idTabView.setColumnResizePolicy(TableView.CONSTRAINED_RESIZE_POLICY);

        idtabHostname.setCellValueFactory(new PropertyValueFactory<NameSheet, String>("name"));
        idtabLogin.setCellValueFactory(new PropertyValueFactory<NameSheet, String>("login"));
        idtabPassword.setCellValueFactory(new PropertyValueFactory<NameSheet, String>("password"));
        idtabIP.setCellValueFactory(new PropertyValueFactory<NameSheet, String>("ip"));


        idTabView.setItems(usersData);


    }

    private void initData() {

        int NumCount = count_row(0, GlobalVars.FileNameIP);
        int NumSheet = count_sheet(GlobalVars.FileNameBI);
        System.out.println(NumCount);
        for (int i = 0 ;i<NumCount;i++) {
            int k = 1;
            String NameIP = parse(GlobalVars.FileNameIP,0,i,0);
            for (int p = 1; p < NumSheet;p++) {
                String NameBI = parser_name(p, GlobalVars.FileNameBI);
                String pName = (NameBI + " ");
//                System.out.println("**" + NameIP + "**" + pName + "**");
                if (NameIP.equals(pName)) {
                    if (i == 1) {
                        k = 0;
                    }
                    usersData.add(new NameSheet(NameIP, parse(GlobalVars.FileNameBI, p, (12 - k), 2), parse(GlobalVars.FileNameBI, p, (12 - k), 3),string_substring(GlobalVars.FileNameBI,p,(8-k),2,0)));
                }
            }
        }

    }
}
