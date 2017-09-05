package com.reeattiy;

public class VehicleInfo {

    private String VIN;
    private double odometer;
    private double gasConsumption;
    private double milesLastOilChange;
    private double engineSize;

    public VehicleInfo(){

    }


    public String getVIN() {
        return VIN;
    }

    public double getOdometer() {
        return odometer;
    }

    public double getGasConsumption() {
        return gasConsumption;
    }

    public double getMilesLastOilChange() {
        return milesLastOilChange;
    }

    public double getEngineSize() {
        return engineSize;
    }

    public void setVIN(String VIN) {
        this.VIN = VIN;
    }

    public void setOdometer(double odometer) {
        this.odometer = odometer;
    }

    public void setGasConsumption(double gasConsumption) {
        this.gasConsumption = gasConsumption;
    }

    public void setMilesLastOilChange(double milesLastOilChange) {
        this.milesLastOilChange = milesLastOilChange;
    }

    public void setEngineSize(double engineSize) {
        this.engineSize = engineSize;
    }


    @Override
    public String toString() {
        return "VehicleInfo{" +
                "VIN=" + VIN +
                ", odometer=" + odometer +
                ", gasConsumption=" + gasConsumption +
                ", milesLastOilChange=" + milesLastOilChange +
                ", engineSize=" + engineSize +
                '}';
    }
}
