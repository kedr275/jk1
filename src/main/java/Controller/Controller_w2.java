package Controller;

import com.vmware.vim25.*;
import com.vmware.vim25.mo.Folder;
import com.vmware.vim25.mo.InventoryNavigator;
import com.vmware.vim25.mo.ServiceInstance;
import com.vmware.vim25.mo.VirtualMachine;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;

import java.beans.Expression;
import java.net.URL;
import java.util.ResourceBundle;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.TimeUnit;


import static Controller.Parserexel.*;

/**
 * Created by jeka on 01.12.2015.
 */
public class Controller_w2 implements Initializable {


    @FXML public TableView<NameSheet> TableView1;
    @FXML public TableColumn<NameSheet, Integer> TableColumn1;
    @FXML public Button bnGO;
    @FXML public TextField idHostname;
    @FXML public TextField idStatus;
    @FXML public TextArea idIP;
    @FXML public TextField idAccountLogin;
    @FXML public TextField idAccountPass;
    @FXML public TextField idVirt;
    @FXML public TextField idCPU;
    @FXML public TextField idRam;
    @FXML public TextArea idHDD;
    @FXML public TextField idOS;
    @FXML public TextField idFullName;
    @FXML public TextField idIProot;
    @FXML public TextField idType;
    @FXML public TextField idMask;

    @FXML public TextField idVmHostname;
    @FXML public TextField idVmOS;
    @FXML public TextField idVmRAM;
    @FXML public TextField idVmCpu;
    @FXML public TextArea idVmHDD;
    @FXML public TextArea idVmIP;

    @FXML public TextArea idSetHDD;
    @FXML public TextArea idSetIP;
    @FXML public TextField idSetHostname;
    @FXML public TextField idSetOS;
    @FXML public TextField idSetRam;
    @FXML public TextField idSetCpu;



    private static ObservableList<NameSheet> usersData = FXCollections.observableArrayList();

    public static void initData() {
        System.out.println("Error in initData");
        System.out.println(GlobalVars.FileNameIP);
        int count = count_row(0, GlobalVars.FileNameIP);
        System.out.println(count);
        for (int i = 0; i <= count; i++) {
             System.out.println(parse(GlobalVars.FileNameIP, i));
            boolean add = usersData.add(new NameSheet(parse(GlobalVars.FileNameIP, i)));
        }

    }

    public static void delData() {

        usersData.clear();


    }

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        TableColumn1.setCellValueFactory(new PropertyValueFactory<NameSheet, Integer>("name"));
        //tabview1.setPrefHeight(800);
        //tabview1.setPrefWidth(450);
        TableView1.setColumnResizePolicy(TableView.CONSTRAINED_RESIZE_POLICY);

        TableView1.getSelectionModel().selectedItemProperty().addListener(new ChangeListener() {
            @Override
            public void changed(ObservableValue observableValue, Object oldValue, Object newValue) {
                //Check whether item is selected and set value of selected item to Label
                if (TableView1.getSelectionModel().getSelectedItem() != null) {
                    TableView.TableViewSelectionModel selectionModel = TableView1.getSelectionModel();
                    ObservableList selectedCells = selectionModel.getSelectedCells();
                    TablePosition tablePosition = (TablePosition) selectedCells.get(0);
                    Object val = tablePosition.getTableColumn().getCellData(newValue);


                    try {
                        ExecutorService executor = Executors.newSingleThreadExecutor();
                        executor.submit(() -> {
                            try {
                                showinfoset(tablePosition.getTableColumn().getCellData(newValue));
                            } catch (Exception e) {
                                e.printStackTrace();
                            }
                        });

                        try {
                            System.out.println("attempt to shutdown executor");
                            executor.shutdown();

                            executor.awaitTermination(5, TimeUnit.SECONDS);
                        } catch (InterruptedException e) {
                            System.err.println("tasks interrupted");
                        } finally {
                            if (!executor.isTerminated()) {
                                System.err.println("cancel non-finished tasks");
                            }
                            executor.shutdownNow();
                            System.out.println("shutdown finished");
                        }

                    } catch (Exception e) {
                        e.printStackTrace();
                    }



                    try {
                        ExecutorService executor = Executors.newSingleThreadExecutor();
                        executor.submit(() -> {
                            try {
                                showinfohpsm(tablePosition.getTableColumn().getCellData(newValue));
                            } catch (Exception e) {
                                e.printStackTrace();
                            }
                        });

                        try {
                            System.out.println("attempt to shutdown executor");
                            executor.shutdown();

                            executor.awaitTermination(5, TimeUnit.SECONDS);
                        } catch (InterruptedException e) {
                            System.err.println("tasks interrupted");
                        } finally {
                            if (!executor.isTerminated()) {
                                System.err.println("cancel non-finished tasks");
                            }
                            executor.shutdownNow();
                            System.out.println("shutdown finished");
                        }

                    } catch (Exception e) {
                        e.printStackTrace();
                    }

                    try {
                        ExecutorService executor = Executors.newSingleThreadExecutor();
                        executor.submit(() -> {
                            try {
                                showinfovcenter(tablePosition.getTableColumn().getCellData(newValue));
                            } catch (Exception e) {
                                e.printStackTrace();
                            }
                        });

                        try {
                            System.out.println("attempt to shutdown executor");
                            executor.shutdown();

                            executor.awaitTermination(5, TimeUnit.SECONDS);
                        } catch (InterruptedException e) {
                            System.err.println("tasks interrupted");
                        } finally {
                            if (!executor.isTerminated()) {
                                System.err.println("cancel non-finished tasks");
                            }
                            executor.shutdownNow();
                            System.out.println("shutdown finished");
                        }

                    } catch (Exception e) {
                        e.printStackTrace();
                    }


                }
            }
        });
    }

    @FXML
    private void bnGOAct (ActionEvent event) {
        System.out.println("Show Table");
        delData();
        TableView1.setItems(usersData);
        initData();
        TableView1.setItems(usersData);

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
//                idMask.setText(parse(GlobalVars.FileNameBI,i,(8-k),3));
                break;
            }
            else {System.out.println("No"); }
        }
    }

    public void ppp(String vmname) throws Exception
    {

        long start = System.currentTimeMillis();
        ServiceInstance si = new ServiceInstance(new URL("https://10.126.96.23/sdk"), GlobalVars.VCenteruser, GlobalVars.Vcenterpassword, true);
        long end = System.currentTimeMillis();
        System.out.println("time taken:" + (end-start));
        Folder rootFolder = si.getRootFolder();

        vmname = vmname.replace(" ","");
        VirtualMachine vm = (VirtualMachine) new InventoryNavigator(rootFolder).searchManagedEntity("VirtualMachine", vmname);

        if(vm==null)
        {
            System.out.println("VM " + vmname + " not found");
            si.getServerConnection().logout();
            throw new Exception("Source Virtual Machine Not Found");
        }
        else {

            String name = rootFolder.getName();

            VirtualMachineConfigInfo vminfo = vm.getConfig();
            VirtualHardware hw = vminfo.getHardware();
            VirtualMachineCapability vmc = vm.getCapability();



            idVmHostname.setText(vm.getName());
            idVmOS.setText(vminfo.getGuestFullName());
            idVmRAM.setText(Integer.toString(hw.getMemoryMB()));
            idVmCpu.setText(Integer.toString(hw.getNumCPU()));
            String deviceHDD = "";
            VirtualDevice[] devices = hw.getDevice();
            for (int i = 0; i < devices.length; i++) {
                VirtualDevice device = devices[i];
                Description deviceInfo = device.getDeviceInfo();
                int p = device.getKey();
                if (p >= 2000 && p < 2020 ) {
                    deviceHDD = (deviceHDD + deviceInfo.getLabel() + " : " + deviceInfo.getSummary() + "\n");
                }
            }
            idVmHDD.setText(deviceHDD);

            idVmIP.setText(vm.getGuest().getIpAddress());








        }
        si.getServerConnection().logout();
    }

    private void showinfovcenter (Object name) throws Exception {
        String vmname = (String) name;
        ppp(vmname);



    }

    private void showinfoset (Object name) {

        int count = count_row(0, GlobalVars.FileNameIP);
        for (int i = 0; i <= count; i++) {
            String SName = name.toString();
            String setname = parse(GlobalVars.FileNameIP, i);
            if(name.equals(setname)) {

                idSetHostname.setText(SName);
                String ip1 = parse(GlobalVars.FileNameIP, 0, i, 1);
                String ip2 = parse(GlobalVars.FileNameIP, 0, i, 2);
                String ip3 = parse(GlobalVars.FileNameIP, 0, i, 3);
                String ip4 = parse(GlobalVars.FileNameIP, 0, i, 4);
                idSetIP.setText(ip1 + "\n" + ip2 + "\n" + ip3 + "\n" + ip4);

                String HDD1 = parse(GlobalVars.FileNameIP, 0, i, 7);
                String HDD2 = parse(GlobalVars.FileNameIP, 0, i, 8);
                System.out.println(HDD1 + "***\n" +HDD2);
                idSetHDD.setText(HDD1 + "\n" + HDD2);

                idSetCpu.setText(parse(GlobalVars.FileNameIP, 0, i, 5));
                idSetRam.setText(parse(GlobalVars.FileNameIP, 0, i, 6));
                idSetOS.setText(parse(GlobalVars.FileNameIP, 0, i, 9));


            }
        }

    }



}