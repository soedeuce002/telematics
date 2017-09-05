package com.reeattiy;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

import com.fasterxml.jackson.core.JsonGenerationException;
import com.fasterxml.jackson.databind.JsonMappingException;
import com.fasterxml.jackson.databind.ObjectMapper;

public class TelematicsService {

    public static void report(VehicleInfo vehicleInfo) throws IOException {

        ObjectMapper mapper = new ObjectMapper();
        String vinNum = vehicleInfo.getVIN();
        mapper.writeValue(new File(vinNum + ".json"), vehicleInfo);
        String json = mapper.writeValueAsString(vehicleInfo);
        System.out.println("Your vehicle information: " + json);

    }

    public static ArrayList<VehicleInfo> convertJsonToObject() throws IOException {
        File file = new File(".");
        ArrayList<VehicleInfo> list = new ArrayList<>();
        for (File f : file.listFiles()) {
            if (f.getName().endsWith(".json")) {
            ObjectMapper map = new ObjectMapper();
            VehicleInfo vehInfo = map.readValue(f, VehicleInfo.class);
            list.add(vehInfo);
        }
    } return list;
}

    public static void updateDashboard(ArrayList<VehicleInfo> vehicles) throws IOException {
        double averageOdometer = 0;
        double averageGasConsumption = 0;
        double averageMilesSinceOilChange = 0;
        double averageEngineSize = 0;
        String vehicleHTML = "";

        String fileName = "dashboard.html";
        File file = new File(fileName);
        for(VehicleInfo vehicle : vehicles) {
            averageOdometer += vehicle.getOdometer();
            averageGasConsumption += vehicle.getGasConsumption();
            averageMilesSinceOilChange += vehicle.getMilesLastOilChange();
            averageEngineSize += vehicle.getEngineSize();


            vehicleHTML +=
                    "<tr>\n" +
                    "<th>Odometer (miles) |</th><th>Consumption (gallons) |</th><th>Last Oil Change |</th><th>Engine Size (liters)</th>\n" +
                    "</tr>\n" +
                    "<tr>\n" +
                    "<td align=\"center\">" + vehicle.getVIN() + "</td><td align=\"center\">" +
                    vehicle.getVIN()+ "/td><td align=\"center\">" + vehicle.getVIN() + "/td align=\"center\">" +
                    "<td align=\"center\">" + vehicle.getVIN() + "/td>\n" +
                    "        </tr>";
        }
        averageOdometer /= vehicles.size();
        averageGasConsumption /= vehicles.size();
        averageMilesSinceOilChange /= vehicles.size();
        averageEngineSize /= vehicles.size();

        String main = "<!DOCTYPE html>\n" +
                "<html lang=\"en\">\n" +
                "<head>\n" +
                "    <meta charset=\"UTF-8\">\n" +
                "    <title>Vehicle Telematics Dashboard</title>\n" +
                "</head>\n" +
                "<body>\n" +
                "<h1 align=\"center\">Averages for # vehicles</h1>\n" +
                "<table align=\"center\">\n" +
                "    <tr>\n" +
                "        <th>Odometer (miles) |</th>" +
                "        <th>Consumption (gallons) |</th>" +
                "        <th>Last Oil Change |</th>" +
                "        <th>Engine Size (liters)</th>\n" +
                "    </tr>\n" +
                "    <tr>\n" +
                "        <td align=\"center\">" + averageOdometer + "</td>" +
                "        <td align=\"center\">" + averageGasConsumption + "</td>" +
                "        <td align=\"center\">" + averageMilesSinceOilChange + "</td>" +
                "        <td align=\"center\">" + averageEngineSize +"</td>\n" +
                "    </tr>\n" +
                "</table>\n" +
                "<br>" +
                "<h1 align=\"center\">History</h1>\n";


        main += "<table align=\"center\" border=\"1\">\n" + vehicleHTML  +
                "</table>\n" + "</body>\n" + "</html>";

        try {
            FileOutputStream stream = new FileOutputStream(file, false);
            byte[] myBytes = main.getBytes();
            stream.write(myBytes);
            stream.close();
        }
        catch(FileNotFoundException e) {
            e.printStackTrace();
        }
        catch (IOException e) {
            e.printStackTrace();
        }
    }

}
