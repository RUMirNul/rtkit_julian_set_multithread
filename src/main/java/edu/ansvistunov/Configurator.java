package edu.ansvistunov;

public class Configurator {

    public static Config configure(String[] args) {
        if (args.length != 6) {
            throw new IllegalArgumentException("Ошибка конфигурации. Недостаточно или много параметров конфигурации.");
        }

        Config config = new Config();

        for (int i = 0; i < args.length; i++) {
            switch (args[i]) {
                case "-d" -> {
                    String temp = args[i + 1];
                    String[] widthAndHeight = temp.split(";");
                    config.setWidth(Integer.parseInt(widthAndHeight[0]));
                    config.setHeight(Integer.parseInt(widthAndHeight[1]));
                    i++;
                }
                case "-c" -> {
                    String temp = args[i + 1];
                    String[] realAndImaginaryPart = temp.split(";");
                    config.setRealPart(Double.parseDouble(realAndImaginaryPart[0]));
                    config.setImaginaryPart(Double.parseDouble(realAndImaginaryPart[1]));
                    i++;
                }
                case "-o" -> {
                    String path = args[i + 1];
                    config.setOutputPath(path);
                    i++;
                }
            }
        }

        return config;
    }
}
