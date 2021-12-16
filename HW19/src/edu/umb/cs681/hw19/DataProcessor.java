package edu.umb.cs681.hw19;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.*;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public class DataProcessor {
    private String FilePath;
    private ArrayList<StateAggregatedResult> States;
    private List<List<String>> Lines;

    public DataProcessor(String filePath) {
        States = new ArrayList<StateAggregatedResult>();
        FilePath = filePath;
        Path path = Paths.get(FilePath);
        try{
            Stream<String> lines = Files.lines(path);
            List<List<String>> matrix = lines.skip(1l).map(line -> {
                return Stream.of( line.split(",") ).map(value->value.substring(1, value.length() - (value.endsWith("\"") ? 1 : 0)))
                        .collect( Collectors.toList() ); }) .collect( Collectors.toList() );
            Lines = matrix;
        } catch (IOException ex) {}
    }

    public int generateStateList() {
        Set stateNames = new HashSet<String>();
        Lines.stream().parallel().forEach(line -> {
            String stateName = line.get(3);
            stateNames.add(stateName);
        });
        stateNames.stream().forEach(state -> {
            States.add(new StateAggregatedResult((String) state));
        });
        return States.size();
    }

    public int initializeStates() {
        HashMap stateData = new HashMap<String, List<Double>>();
        Lines.stream().parallel().forEach(line -> {
           String stateName = line.get(3).split(",")[0];
           if(!stateData.containsKey(stateName)) {
               stateData.put(stateName, new ArrayList<Double>());
           }
           List<Double> dataList = (List<Double>) stateData.get(stateName);
           dataList.add(Double.parseDouble(line.get(0)));
           stateData.replace(stateName, dataList);
        });
        States.stream().forEach(state -> {
            List<Double> dataList = (List<Double>) stateData.get(state.getStateName());
            dataList.stream().forEach(state::addCountryResult);
        });
        return stateData.size();
    }

    public void printStateData() {
        States.stream().parallel().forEach(StateAggregatedResult::recalculateAverageToxpi);
        States.stream().sorted((state1, state2) -> (int)((state1.getAvgToxpi() - state2.getAvgToxpi()) * 100000)).forEach(state -> {
            System.out.println("==========================");
            System.out.println("STATE NAME: " + state.getStateName());
            System.out.println("---RESULTS");
            System.out.println("ToxPi Average: " + state.getAvgToxpi());
        });
    }
}
