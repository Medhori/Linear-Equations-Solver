package solver;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.PrintWriter;
import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        try(final var input = new Scanner(new File(args[1]));
        final var output = new PrintWriter(new File(args[3]))) {
            new Running(input, output).run();

        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }

    }
}







