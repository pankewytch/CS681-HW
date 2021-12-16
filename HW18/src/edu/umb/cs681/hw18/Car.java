package edu.umb.cs681.hw18;

import java.util.ArrayList;

public class Car {
    private int miles, price, year, dominationCount;
    private String make, model;
    private boolean isNew;
    private static CarComparator milesComparator = (Car car1, Car car2) ->
            car1.getMiles()-car2.getMiles();
    private static CarComparator yearComparator = (Car car1, Car car2) ->
            car2.getYear()-car1.getYear();
    private static CarComparator priceComparator = (Car car1, Car car2) ->
            car1.getPrice()-car2.getPrice();
    private static CarComparator paretoComparator = (Car car1, Car car2) ->
            car1.getDominationCount()-car2.getDominationCount();

    public Car(int miles, int price, int year, String make, String model, boolean isNew) {
        this.miles = miles;
        this.price = price;
        this.year = year;
        this.make = make;
        this.model = model;
        this.isNew = isNew;
    }

    public static CarComparator getMilesComparator() {
        return milesComparator;
    }

    public static CarComparator getYearComparator() {
        return yearComparator;
    }

    public static CarComparator getPriceComparator() {
        return priceComparator;
    }

    public static CarComparator getParetoComparator() {
        return paretoComparator;
    }

    public int getMiles() {
        return miles;
    }

    public int getPrice() {
        return price;
    }

    public int getYear() {
        return year;
    }

    public String getMake() {
        return make;
    }

    public String getModel() {
        return model;
    }

    public boolean isNew() {
        return isNew;
    }

    public void setDominationCount(ArrayList<Car> carList) {
        for(Car car:carList) {
            int priceDomination = car.getPrice() - this.price;
            int yearDomination = this.year - car.getYear();
            int mileageDomination = car.getMiles() - this.miles;
            if(priceDomination <= 0 && yearDomination <= 0 && mileageDomination <= 0) {
                if(priceDomination < 0 || yearDomination < 0 || mileageDomination < 0) {
                    dominationCount++;
                }
            }
        }
    }

    public int getDominationCount() {
        return this.dominationCount;
    }

    public static void main(String[] args) {
        ArrayList<Car> carList = new ArrayList<>();
        carList.add(new Car(3000, 1000, 2003, "Toyota", "a", false));
        carList.add(new Car(4000, 2000, 2005, "Tesla", "b", false));
        carList.add(new Car(5000, 3000, 2002, "Kia", "c", false));
        carList.add(new Car(2000, 2000, 2004, "Hyundai", "d", false));
        carList.add(new Car(1000, 2000, 2008, "VW", "e", false));
        carList.add(new Car(6000, 2000, 1993, "Audi", "g", false));
        for(Car car:carList) {
            car.setDominationCount(carList);
        }
        for(Car car:carList) {
            System.out.println("Domination count for car ["+car.getModel()+"] is ["+car.getDominationCount()+"]");
        }
        int modelCount = carList.stream()
                .parallel()
                .map(Car::getModel)
                .reduce(0,
                        (result, model) -> ++result,
                        Integer::sum);
        System.out.println("Total Number of car Brands: " + modelCount);
    }
}
