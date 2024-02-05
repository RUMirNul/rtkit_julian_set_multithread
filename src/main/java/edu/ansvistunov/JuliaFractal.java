package edu.ansvistunov;

import javax.imageio.ImageIO;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class JuliaFractal {
    private final Integer width;
    private final Integer height;
    private final Double realPart;
    private final Double imaginaryPart;
    private final String outputPath;

    public JuliaFractal(Config config) {
        this.width = config.getWidth();
        this.height = config.getHeight();
        this.realPart = config.getRealPart();
        this.imaginaryPart = config.getImaginaryPart();
        this.outputPath = config.getOutputPath();
    }

    private boolean isValid() {
        return width != null && height != null && realPart != null && imaginaryPart != null && outputPath != null;
    }

    public void execute() {
        if (!isValid()) throw new IllegalArgumentException("Ошибка конфигурации расчёта фрактала Жулиана");

        generateJuliaFractal();
    }

    private void generateJuliaFractal() {
        BufferedImage image = new BufferedImage(width, height, BufferedImage.TYPE_INT_ARGB);
        final int cores = Runtime.getRuntime().availableProcessors();
        ExecutorService executor = Executors.newFixedThreadPool(cores);


        final int chunkSize = height / cores;

        for (int i = 0; i < cores; i++) {
            final int start = i * chunkSize;
            final int end = (i == cores - 1) ? height : (i + 1) * chunkSize;
            executor.execute(() -> generateJuliaFractalChunk(image, start, end));
        }

        executor.shutdown();
        while (!executor.isTerminated()) {
            // Ждём, когда все потоки закончат работу
        }

        try {
            File output = new File(outputPath);
            String fileExtension = outputPath.substring(outputPath.lastIndexOf(".") + 1);
            ImageIO.write(image, fileExtension, output);
            System.out.println("Фрактал сохранен в файл: " + outputPath);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private void generateJuliaFractalChunk(BufferedImage image, int start, int end) {
        double zoom = 1.5; // Коэффициент масштабирования
        int maxIterations = 1000;

        for (int y = start; y < end; y++) {
            for (int x = 0; x < width; x++) {
                double zx = zoom * (x - width / 2) / (0.5 * width);
                double zy = zoom * (y - height / 2) / (0.5 * height);
                double temp;

                int iterations = 0;
                while (zx * zx + zy * zy < 64 && iterations < maxIterations) {
                    temp = zx * zx - zy * zy + realPart;
                    zy = 2.0 * zx * zy + imaginaryPart;
                    zx = temp;
                    iterations++;
                }

                int color;
                if (iterations == maxIterations) {
                    color = 0xFF0000FF; // Синий цвет для фона
                } else {
                    iterations = iterations + 1 - (int) (Math.log(Math.log(zx * zx + zy * zy)) / Math.log(2));
                    int blue = Math.min(255, iterations * 10);
                    color = (255 << 24) | blue;
                }

                image.setRGB(x, y, color);
            }
        }
    }
}
