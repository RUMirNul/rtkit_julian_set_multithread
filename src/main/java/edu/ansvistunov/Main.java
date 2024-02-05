package edu.ansvistunov;

public class Main {
    public static void main(String[] args) {
        try {
            JuliaFractal juliaFractal = new JuliaFractal(Configurator.configure(args));
            juliaFractal.execute();
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
    }
}