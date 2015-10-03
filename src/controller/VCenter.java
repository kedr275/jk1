package controller;

import com.vmware.vim25.*;
import com.vmware.vim25.mo.Folder;
import com.vmware.vim25.mo.InventoryNavigator;
import com.vmware.vim25.mo.ServiceInstance;
import com.vmware.vim25.mo.VirtualMachine;

import java.net.URL;

public class VCenter {

    public static void ppp(String vmname) throws Exception
    {
        long start = System.currentTimeMillis();
        ServiceInstance si = new ServiceInstance(new URL("https://10.126.96.23/sdk"), "esx\\sudarkin", "Qwerty123", true);
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



            Controllerw1.idVmHostname.setText(vm.getName());
            Controllerw1.idVmOS.setText(vminfo.getGuestFullName());
            Controllerw1.idVmRAM.setText(Integer.toString(hw.getMemoryMB()));
            Controllerw1.idVmCpu.setText(Integer.toString(hw.getNumCPU()));

            VirtualDevice[] devices = hw.getDevice();
            for (int i = 0; i < devices.length; i++) {
                VirtualDevice device = devices[i];
                Description deviceInfo = device.getDeviceInfo();
                int p = device.getKey();
                if (p >= 2000 && p < 2020 ) {
                    Controllerw1.idVmHDD.setText(deviceInfo.getLabel() + " : " + deviceInfo.getSummary());
                }
            }

            Controllerw1.idVmIP.setText(vm.getGuest().getIpAddress());








        }
        si.getServerConnection().logout();
    }


}


