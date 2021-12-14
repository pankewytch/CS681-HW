package edu.umb.cs681.hw11;

public final class ImmutableAddress {
    private final String street, city, state;
    private final int zipcode;

    public ImmutableAddress(String street, String city, String state, int zipcode) {
        this.street = street;
        this.city = city;
        this.state = state;
        this.zipcode = zipcode;
    }

    public int getZipcode() {
        return zipcode;
    }

    public String getStreet() {
        return street;
    }

    public String getCity() {
        return city;
    }

    public String getState() {
        return state;
    }

    @Override
    public boolean equals(Object obj) {
        if(!(obj instanceof ImmutableAddress)) return false;
        ImmutableAddress compare = (ImmutableAddress) obj;
        return compare.getCity().equals(city) && compare.getState().equals(state) && compare.getStreet().equals(street)
                && compare.getZipcode() == zipcode;
    }

    @Override
    public String toString() {
        return street + ", " + city + ", " + state + " " + zipcode;
    }

    public ImmutableAddress change(String street, String city, String state, int zipcode) {
        return new ImmutableAddress(street, city, state, zipcode);
    }

    public static void main(String[] args) {
        for(int i = 0; i <100; i++) {
            int finalI = i;
            new Thread(
                    () -> {
                        ImmutableAddress add1 = new ImmutableAddress(finalI + " Ivy Place", "Howell", "NJ", 17731);
                        ImmutableAddress add2 = add1.change(finalI + " Ivy Place", "Howell", "NJ", 18405);
                        System.out.print("\n\rAddress 1 to string: " + add1.toString() + "\n\rAddress 2 to string: " + add2.toString() + "\n\rAre addresses equal? " + add1.equals(add2) + "\n\r");
                    }
            ).start();
        }
    }
}
