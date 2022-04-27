package solver;

import java.io.PrintWriter;
import java.util.Scanner;


public class Running implements Runnable {
    private final Scanner scanner;
    private final PrintWriter writer;

    public Running(Scanner scanner, PrintWriter writer) {
        this.scanner = scanner;
        this.writer = writer;
    }

    public void run() {
        final int cols = scanner.nextInt() + 1;
        final int rows = scanner.nextInt();
        final var cells = scanner.tokens()
                .limit(rows * cols)
                .map(Complex::new)
                .toArray(Complex[]::new);

        final var equation = new LinearEquationSolver(rows, cols, cells);
        writer.print(equation.getSolution());
    }
}