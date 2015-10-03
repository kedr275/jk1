package controller;

import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.*;


import java.net.URL;
import java.util.Objects;
import java.util.ResourceBundle;

import javafx.scene.control.cell.PropertyValueFactory;
import pojo.GlobalVars;
import pojo.NameSheet;
import controller.VCenter;


import static controller.Parserexel.*;



/**
 * Created by jeka on 24.09.2015.
 */
public class Controllerw1 implements Initializable {

    @FXML private TableView<NameSheet> tabview1;
    @FXML private TableColumn<NameSheet, Integer> tabcolum1;
    @FXML private javafx.scene.control.TextArea idHostname;
    @FXML private javafx.scene.control.TextArea idStatus;
    @FXML private javafx.scene.control.TextArea idIP;
    @FXML private javafx.scene.control.TextArea idAccountLogin;
    @FXML private javafx.scene.control.TextArea idAccountPass;
    @FXML private javafx.scene.control.TextArea idVirt;
    @FXML private javafx.scene.control.TextArea idCPU;
    @FXML private javafx.scene.control.TextArea idRam;
    @FXML private javafx.scene.control.TextArea idHDD;
    @FXML private javafx.scene.control.TextArea idOS;
    @FXML private javafx.scene.control.TextArea idFullName;
    @FXML private javafx.scene.control.TextArea idIProot;
    @FXML private javafx.scene.control.TextArea idType;
    @FXML private javafx.scene.control.TextArea idMask;
    @FXML public static javafx.scene.control.TextArea idVmCpu;
    @FXML public static javafx.scene.control.TextArea idVmRAM;
    @FXML public static javafx.scene.control.TextArea idVmHDD;
    @FXML public static javafx.scene.control.TextArea idVmIP;
    @FXML public static javafx.scene.control.TextArea idVmHostname;
    @FXML public static javafx.scene.control.TextArea idVmOS;

    private ObservableList<NameSheet> usersData = FXCollections.observableArrayList();


    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
       initData();
        idHostname.setEditable(false);
        idStatus.setEditable(false);
        idIP.setEditable(false);
        idAccountLogin.setEditable(false);
        idAccountPass.setEditable(false);
        idVirt.setEditable(false);
        idCPU.setEditable(false);
        idRam.setEditable(false);
        idHDD.setEditable(false);
        idOS.setEditable(false);
        idFullName.setEditable(false);
        idIProot.setEditable(false);
        idType.setEditable(false);
        idMask.setEditable(false);
        idVmCpu.setEditable(false);
        idVmHDD.setEditable(false);
        idVmHostname.setEditable(false);
        idVmIP.setEditable(false);
        idVmOS.setEditable(false);
        idVmRAM.setEditable(false);


        // устанавливаем тип и значение которое должно хранится в колонке
        tabcolum1.setCellValueFactory(new PropertyValueFactory<NameSheet, Integer>("name"));
        //tabview1.setPrefHeight(800);
        //tabview1.setPrefWidth(450);
        tabview1.setColumnResizePolicy(TableView.CONSTRAINED_RESIZE_POLICY);
        tabview1.getSelectionModel().selectedItemProperty().addListener(new ChangeListener() {
                                                                            @Override
                                                                            public void changed(ObservableValue observableValue, Object oldValue, Object newValue) {
                                                                                //Check whether item is selected and set value of selected item to Label
                                                                                if (tabview1.getSelectionModel().getSelectedItem() != null) {
                                                                                    TableView.TableViewSelectionModel selectionModel = tabview1.getSelectionModel();
                                                                                    ObservableList selectedCells = selectionModel.getSelectedCells();
                                                                                    TablePosition tablePosition = (TablePosition) selectedCells.get(0);
                                                                                    Object val = tablePosition.getTableColumn().getCellData(newValue);
//                                                                                    System.out.println("Selected Value" + val);
                                                                                    showinfohpsm(tablePosition.getTableColumn().getCellData(newValue));
                                                                                    try {
                                                                                        showinfovcenter(tablePosition.getTableColumn().getCellData(newValue));
                                                                                    } catch (Exception e) {
                                                                                        e.printStackTrace();
                                                                                    }


                                                                                }
                                                                            }
        });

        // заполняем таблицу данными
       tabview1.setItems(usersData);

    }

    // подготавливаем данные для таблицы

    private void initData() {
       int count = count_row(0, GlobalVars.FileNameIP);
        for (int i = 0; i <= count; i++) {
          //  System.out.println(Parserexel.parse(GlobalVars.FileNameIP, i));
            boolean add = usersData.add(new NameSheet(Parserexel.parse(GlobalVars.FileNameIP, i)));
        }

    }


    private void showinfohpsm (Object name) {
      int NumCount = count_sheet(GlobalVars.FileNameBI);
        System.out.println(NumCount);
        for (int i = 0; i < NumCount; i++) {
            int k = 1;
            String prName = parser_name(i, GlobalVars.FileNameBI);
            String pName = (prName + " ");
            if (pName.equals(name)) {
                if (i == 1) { k = 0; }
                idHostname.setText(parse(GlobalVars.FileNameBI,i,(5-k),2));
                idStatus.setText(parse(GlobalVars.FileNameBI,i,(6-k),2));
                idIP.setText(parse(GlobalVars.FileNameBI,i,(8-k),2));
                idAccountLogin.setText(parse(GlobalVars.FileNameBI,i,(12-k),2));
                idAccountPass.setText(parse(GlobalVars.FileNameBI,i,(12-k),3));
                idVirt.setText(parse(GlobalVars.FileNameBI,i,(15-k),2));
                idCPU.setText(parse(GlobalVars.FileNameBI,i,(17-k),2));
                idRam.setText(parse(GlobalVars.FileNameBI,i,(18-k),2));
                idHDD.setText(parse(GlobalVars.FileNameBI,i,(20-k),3));
                idOS.setText(parse(GlobalVars.FileNameBI,i,(22-k),2));
                idFullName.setText(parse(GlobalVars.FileNameBI,i,(27-k),2));
                idIProot.setText(parse(GlobalVars.FileNameBI,i,(10-k),2));
                idType.setText(parse(GlobalVars.FileNameBI,i,(14-k),2));
                idMask.setText(parse(GlobalVars.FileNameBI,i,(8-k),3));
                break;
            }
            else {System.out.println("No"); }
        }
    }

    private void showinfovcenter (Object name) throws Exception {
        String vmname = (String) name;
        VCenter.ppp(vmname);



    }
}

