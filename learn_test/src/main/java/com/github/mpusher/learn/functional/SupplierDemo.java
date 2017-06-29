package com.github.mpusher.learn.functional;

import java.util.function.Supplier;

/**
 * Created by Sean.liu on 2017/6/29.
 */
public class SupplierDemo {

    static class Vehicle{
        public void drive(){
            System.out.println("Driving vehicle ...");
        }
    }
    static class Car extends Vehicle{
        @Override
        public void drive(){
            System.out.println("Driving car...");
        }
    }

    static void driveVehicle(Supplier<? extends Vehicle> supplier){
        Vehicle vehicle = supplier.get();
        vehicle.drive();
    }

    public static void main(String[] args) {
        //Using Lambda expression
        driveVehicle(()-> new Vehicle());
        driveVehicle(()-> new Car());

        //Using method expression
        driveVehicle(Vehicle::new);
        driveVehicle(Car::new);
    }
}
