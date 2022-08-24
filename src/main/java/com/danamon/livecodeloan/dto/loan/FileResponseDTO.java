package com.danamon.livecodeloan.dto.loan;

public class FileResponseDTO {
    private String name;
    private String url;

    public FileResponseDTO(String name, String url) {
        this.name = name;
        this.url = url;
    }

    public FileResponseDTO() {
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }
}
