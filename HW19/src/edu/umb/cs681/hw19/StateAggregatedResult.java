package edu.umb.cs681.hw19;

public class StateAggregatedResult {
    private String StateName;
    private double AvgToxpi;
    private int NumberOfCounties;
    private double SumToxpi;

    public StateAggregatedResult(String stateName) {
        StateName = stateName;
        AvgToxpi = 0d;
        NumberOfCounties = 0;
        SumToxpi = 0d;
    }

    public String getStateName() {
        return StateName;
    }

    public double getAvgToxpi() {
        if(AvgToxpi == 0d)
            AvgToxpi = SumToxpi/NumberOfCounties;
        return AvgToxpi;
    }

    public void addCountryResult(double toxpi) {
        NumberOfCounties++;
        SumToxpi += toxpi;
    }

    public void recalculateAverageToxpi() {
        AvgToxpi = SumToxpi/NumberOfCounties;
    }
}
