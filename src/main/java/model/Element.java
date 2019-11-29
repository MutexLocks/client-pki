package model;

public class Element {
    private String keystorePath;
    private String keystorePassword;
    private String otherCertPath;
    private String inputFilePath;
    private String outputFilePath;

    public String getKeystorePath() {
        return keystorePath;
    }

    public void setKeystorePath(String keystorePath) {
        this.keystorePath = keystorePath;
    }

    public String getKeystorePassword() {
        return keystorePassword;
    }

    public void setKeystorePassword(String keystorePassword) {
        this.keystorePassword = keystorePassword;
    }

    public String getOtherCertPath() {
        return otherCertPath;
    }

    public void setOtherCertPath(String otherCertPath) {
        this.otherCertPath = otherCertPath;
    }

    public String getInputFilePath() {
        return inputFilePath;
    }

    public void setInputFilePath(String inputFilePath) {
        this.inputFilePath = inputFilePath;
    }

    public String getOutputFilePath() {
        return outputFilePath;
    }

    public void setOutputFilePath(String outputFilePath) {
        this.outputFilePath = outputFilePath;
    }
}
