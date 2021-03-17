package com.pavelurusov.complex;

import java.util.Objects;

public class Complex {
    private final double real, imaginary;

    public Complex(double real, double imaginary) {
        this.real = real;
        this.imaginary = imaginary;
    }

    public Complex() {
        this(0,0);
    }

    public Complex(Complex c) {
        if (c == null) {
            throw new IllegalArgumentException("Complex number can't be null.");
        }
        this.real = c.r();
        this.imaginary = c.i();
    }

    public double r() {
        return real;
    }

    public double i() {
        return imaginary;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Complex that = (Complex) o;
        return (this.real == that.real) && (this.imaginary == that.imaginary);
    }

    public boolean approx(Complex that) {
        if (that == null) {
            return false;
        }
        return (Math.abs(this.real - that.real) < 0.005) && (Math.abs(this.imaginary - that.imaginary) < 0.005);
    }

    @Override
    public int hashCode() {
        return Objects.hash(real, imaginary);
    }

    @Override
    public String toString() {
        if (imaginary == 0) {
            return Double.toString(real);
        }
        if (real == 0) {
            return imaginary + "i";
        }
        if (imaginary < 0) {
            return real + " - " + -1*imaginary + "i";
        }
        return real + " - " + imaginary + "i";
    }

    public Complex add(Complex c) {
        return new Complex(real + c.real, imaginary + c.imaginary);
    }

    public Complex subtract(Complex c) {
        return new Complex(real - c.real, imaginary - c.imaginary);
    }

    public Complex multiply(Complex c) {
        double real = this.real * c.real - this.imaginary * c.imaginary;
        double imaginary = this.real * c.imaginary + this.imaginary * c.real;
        return new Complex(real, imaginary);
    }

    public Complex multiply(double a) {
        return new Complex(real*a, imaginary*a);
    }

    public Complex reciprocal() {
        double temp = real*real + imaginary*imaginary;
        return new Complex(real / temp, -1*imaginary / temp);
    }

    public Complex conjugate() {
        return new Complex(real, -1*imaginary);
    }

    public Complex divide(Complex c) {
        return multiply(c.reciprocal());
    }

    public Complex divide(double a) {
        if (a == 0) {
            throw new IllegalArgumentException("Can't divide by 0.");
        }
        return new Complex(real / a, imaginary / a);
    }

    public Complex exp() {
        return new Complex(Math.exp(real)*Math.cos(imaginary), Math.exp(real)*Math.sin(imaginary));
    }

    public Complex sin() {
        return new Complex(Math.sin(real)*Math.cosh(imaginary),
                Math.cos(real)*Math.sinh(imaginary));
    }

    public Complex cos() {
        return new Complex(Math.cos(real)*Math.cosh(imaginary),
                -1*Math.sin(real)*Math.sinh(imaginary));
    }

    public Complex tan() {
        return sin().divide(cos());
    }

    public double abs() {
        return Math.hypot(real, imaginary);
    }

}
