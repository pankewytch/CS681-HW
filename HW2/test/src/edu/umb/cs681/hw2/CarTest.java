package edu.umb.cs681.hw2;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import edu.umb.cs681.hw2.*;

import java.util.ArrayList;
import java.util.stream.Collectors;
import java.util.stream.Stream;

import static org.junit.jupiter.api.Assertions.*;

public class CarTest {
    private ArrayList<Car> testCarList = null;

    @BeforeEach
    public void createCarList() {
        testCarList = new ArrayList<>();
        testCarList.add(new Car(3000, 1000, 2003, "Toyota", "a", false));
        testCarList.add(new Car(4000, 2000, 2005, "Toyota", "b", false));
        testCarList.add(new Car(5000, 3000, 2002, "Toyota", "c", false));
        testCarList.add(new Car(2000, 2000, 2004, "Toyota", "d", false));
        testCarList.add(new Car(1000, 2000, 2008, "Toyota", "e", false));
        testCarList.add(new Car(6000, 2000, 1993, "Toyota", "g", false));
    }

    @Test
    public void CarMileageSortingWithLambdaExpressionStreamTest() {
        ArrayList<Car> cut = testCarList.stream()
                .sorted((Car car1, Car car2) -> car1.getMiles()-car2.getMiles())
                .collect(Collectors.toCollection(ArrayList::new));
        assertEquals("a", cut.get(2).getModel());
        assertEquals("b", cut.get(3).getModel());
        assertEquals("c", cut.get(4).getModel());
        assertEquals("d", cut.get(1).getModel());
        assertEquals("e", cut.get(0).getModel());
        assertEquals("g", cut.get(5).getModel());
    }

    @Test
    public void CarMileageSortingWithSavedFunctionInterfaceStreamTest() {
        ArrayList<Car> cut = testCarList.stream()
                .sorted(Car.getMilesComparator())
                .collect(Collectors.toCollection(ArrayList::new));
        assertEquals("a", cut.get(2).getModel());
        assertEquals("b", cut.get(3).getModel());
        assertEquals("c", cut.get(4).getModel());
        assertEquals("d", cut.get(1).getModel());
        assertEquals("e", cut.get(0).getModel());
        assertEquals("g", cut.get(5).getModel());
    }

    @Test
    public void CarYearSortingLambdaExpressionStreamTest() {
        ArrayList<Car> cut = testCarList.stream()
                .sorted((Car car1, Car car2) -> car2.getYear()-car1.getYear())
                .collect(Collectors.toCollection(ArrayList::new));
        assertEquals("a", cut.get(3).getModel());
        assertEquals("b", cut.get(1).getModel());
        assertEquals("c", cut.get(4).getModel());
        assertEquals("d", cut.get(2).getModel());
        assertEquals("e", cut.get(0).getModel());
        assertEquals("g", cut.get(5).getModel());
    }

    @Test
    public void CarYearSortingSavedFunctionalInterfaceStreamTest() {
        ArrayList<Car> cut = testCarList.stream()
                .sorted(Car.getYearComparator())
                .collect(Collectors.toCollection(ArrayList::new));
        assertEquals("a", cut.get(3).getModel());
        assertEquals("b", cut.get(1).getModel());
        assertEquals("c", cut.get(4).getModel());
        assertEquals("d", cut.get(2).getModel());
        assertEquals("e", cut.get(0).getModel());
        assertEquals("g", cut.get(5).getModel());
    }

    @Test
    public void CarPriceSortingWithLambdaExpressionStreamTest() {
        ArrayList<Car> cut = testCarList.stream()
                .sorted((Car car1, Car car2) -> car1.getPrice()-car2.getPrice())
                .collect(Collectors.toCollection(ArrayList::new));
        assertEquals("a", cut.get(0).getModel());
        assertEquals("b", cut.get(1).getModel());
        assertEquals("c", cut.get(5).getModel());
        assertEquals("d", cut.get(2).getModel());
        assertEquals("e", cut.get(3).getModel());
        assertEquals("g", cut.get(4).getModel());
    }

    @Test
    public void CarPriceSortingWithStoredFunctionInterfaceStreamTest() {
        ArrayList<Car> cut = testCarList.stream()
                .sorted(Car.getPriceComparator())
                .collect(Collectors.toCollection(ArrayList::new));
        assertEquals("a", cut.get(0).getModel());
        assertEquals("b", cut.get(1).getModel());
        assertEquals("c", cut.get(5).getModel());
        assertEquals("d", cut.get(2).getModel());
        assertEquals("e", cut.get(3).getModel());
        assertEquals("g", cut.get(4).getModel());
    }

    @Test
    public void CarParetoSortingWithStoredFunctionInterfaceStreamTest() {
        ArrayList<Car> cut = testCarList.stream()
                .map(car -> {
                    car.setDominationCount(testCarList);
                    return car;
                })
                .sorted(Car.getParetoComparator())
                .collect(Collectors.toCollection(ArrayList::new));
        assertEquals("a", cut.get(0).getModel());
        assertEquals("b", cut.get(2).getModel());
        assertEquals("c", cut.get(4).getModel());
        assertEquals("d", cut.get(3).getModel());
        assertEquals("e", cut.get(1).getModel());
        assertEquals("g", cut.get(5).getModel());
    }

    @Test
    public void CarParetoSortingWithLambdaExpressionStreamTest() {
        ArrayList<Car> cut = testCarList.stream()
                .map(car -> {
                    car.setDominationCount(testCarList);
                    return car;
                })
                .sorted((Car car1, Car car2) -> car1.getDominationCount()-car2.getDominationCount())
                .collect(Collectors.toCollection(ArrayList::new));
        assertEquals("a", cut.get(0).getModel());
        assertEquals("b", cut.get(2).getModel());
        assertEquals("c", cut.get(4).getModel());
        assertEquals("d", cut.get(3).getModel());
        assertEquals("e", cut.get(1).getModel());
        assertEquals("g", cut.get(5).getModel());
    }
}
