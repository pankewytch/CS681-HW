package edu.umb.cs681.hw18;

import java.util.Comparator;

public abstract interface CarComparator extends Comparator<Car> {
    public abstract int compare(Car car1, Car car2);
}
