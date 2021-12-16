package edu.umb.cs681.hw19;


public class Driver {

    public static void main(String[] args) {
        if(args.length < 1)
            throw new IllegalArgumentException("The file path is null, please enter a file path to run this program");
        System.out.println("Argument is " + args[0]);
        String filePath = args[0];
        DataProcessor dataProcessor = new DataProcessor(filePath);
        int states = dataProcessor.generateStateList();
        if(states != 51)
            throw new IllegalStateException("something went wrong, you dont have 50 states");
        states = dataProcessor.initializeStates();
        dataProcessor.printStateData();
    }
}
