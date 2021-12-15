package edu.umb.cs681.hw16;

import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.Arrays;

public class PathList {
    private static Path path1 = Paths.get("../../../../../TestFiles/a.html");
    private static Path path2 = Paths.get("../../../../../TestFiles/b.html");
    private static Path path3 = Paths.get("../../../../../TestFiles/c.html");
    private static Path path4 = Paths.get("../../../../../TestFiles/d.html");
    private static Path path5 = Paths.get("../../../../../TestFiles/e.html");
    public static ArrayList<Path> pathsArrayList = new ArrayList<>(Arrays.asList(path1, path2, path3, path4, path5));
}
