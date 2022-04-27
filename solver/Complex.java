package solver;

import java.util.regex.Pattern;

import static java.lang.Double.parseDouble;
import static java.lang.Math.pow;
import static java.lang.Math.sqrt;

public final class Complex {
    public static final Complex ZERO = new Complex(0, 0);
    public static final Complex ONE = new Complex(1, 0);
    private static final Pattern ONLY_REAL = Pattern.compile("[-+]?\\d+(\\.\\d*)?");
    private static final Pattern ONLY_IMAGINARY = Pattern.compile("[-+]?\\d*(\\.\\d*)?i");
    private static final Pattern ONLY_I = Pattern.compile("[-+]?i");
    private static final Pattern RE_IM = Pattern.compile("(?<=\\d)(?=[-+])");


    private double real;
    private double imaginary;

    public Complex(final double real, final double imaginary) {
        this.real = real;
        this.imaginary = imaginary;
    }

    public Complex(final String complexNumber) {
        if (ONLY_IMAGINARY.matcher(complexNumber).matches()) {
            real = 0;
            imaginary = parseDouble(complexNumber
                    .replace('i', ONLY_I.matcher(complexNumber).matches() ? '1' : ' '));
        } else if (ONLY_REAL.matcher(complexNumber).matches()) {
            real = parseDouble(complexNumber);
            imaginary = 0;
        } else {
            real = parseDouble(RE_IM.split(complexNumber)[0]);
            final var secondPart = RE_IM.split(complexNumber)[1];
            imaginary = parseDouble(secondPart.replace('i', ONLY_I.matcher(secondPart).matches() ? '1' : ' '));
        }
    }

    public Complex add(final Complex other) {
        return new Complex(this.real + other.real, this.imaginary + other.imaginary);
    }

    public Complex subtract(final Complex other) {
        return new Complex(real - other.real, imaginary - other.imaginary);
    }

    public Complex divide(final Complex other) {
        Complex output = this.multiply(other.conjugate());
        double div = pow(other.mod(), 2);
        return new Complex(output.real / div, output.imaginary / div);
    }

    public double mod() {
        return sqrt(pow(real, 2) + pow(imaginary, 2));
    }

    public Complex multiply(final Complex other) {
        return new Complex(
                real * other.real - imaginary * other.imaginary,
                real * other.imaginary + imaginary * other.real);
    }

    public Complex conjugate() {
        return new Complex(real, -imaginary);
    }

    @Override
    public String toString() {
        final String im;
        if (imaginary == 0) {
            im = "";
        } else if (imaginary < 0)
            im = imaginary + "i";
        else
            im = "+" + imaginary + "i";
        return real + im;
    }

    @Override
    public final boolean equals(Object z) {
        if (!(z instanceof Complex))
            return false;
        Complex a = (Complex) z;
        return (real == a.real) && (imaginary == a.imaginary);
    }

}
