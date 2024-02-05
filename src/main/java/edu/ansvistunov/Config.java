package edu.ansvistunov;

public class Config {
    private Integer width;
    private Integer height;
    private Double realPart;
    private Double imaginaryPart;
    private String outputPath;

    public Config(Integer width, Integer height, Double realPart, Double imaginaryPart, String outputPath) {
        this.width = width;
        this.height = height;
        this.realPart = realPart;
        this.imaginaryPart = imaginaryPart;
        this.outputPath = outputPath;
    }

    public Config() {
    }

    public Integer getWidth() {
        return width;
    }

    public void setWidth(Integer width) {
        this.width = width;
    }

    public Integer getHeight() {
        return height;
    }

    public void setHeight(Integer height) {
        this.height = height;
    }

    public Double getRealPart() {
        return realPart;
    }

    public void setRealPart(Double realPart) {
        this.realPart = realPart;
    }

    public Double getImaginaryPart() {
        return imaginaryPart;
    }

    public void setImaginaryPart(Double imaginaryPart) {
        this.imaginaryPart = imaginaryPart;
    }

    public String getOutputPath() {
        return outputPath;
    }

    public void setOutputPath(String outputPath) {
        this.outputPath = outputPath;
    }
}