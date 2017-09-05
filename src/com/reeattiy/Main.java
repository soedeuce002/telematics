package com.reeattiy;

import java.io.IOException;
import java.util.Scanner;
import java.util.ArrayList;


public class Main {

    public static void main(String[] args) {
	// write your code here

        VehicleInfo vehicle1 = new VehicleInfo();

        Scanner input = new Scanner(System.in);

        System.out.println("Please input your vehicle's VIN:  ");
        vehicle1.setVIN(input.nextLine());

        System.out.println("Please input the current odometer mileage:  ");
        vehicle1.setOdometer(input.nextDouble());

        System.out.println("Please input your vehicle's gas consumption:  ");
        vehicle1.setGasConsumption(input.nextDouble());

        System.out.println("Please input the mileage at your vehicle's oil was last changed:  ");
        vehicle1.setMilesLastOilChange(input.nextDouble());

        System.out.println("Please input your vehicle's engine size:  ");
        vehicle1.setEngineSize(input.nextDouble());

        System.out.println(vehicle1);

        ArrayList<VehicleInfo> list = new ArrayList<>();

        try {
            TelematicsService.report(vehicle1);
        } catch (IOException e) {
            e.printStackTrace();
        }

        try {
            list = TelematicsService.convertJsonToObject();
        } catch (IOException e) {
            e.printStackTrace();
        }

        try {
            TelematicsService.updateDashboard(list);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
