package org.request.management.Dto;

public class ResponseMessage {
    private String message;
    private String fileUrl;
    private Long id;

    public ResponseMessage(String message, String fileUrl) {
        this.message = message;
        this.fileUrl = fileUrl;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public String getFileUrl() {
        return fileUrl;
    }

    public void setFileUrl(String fileUrl) {
        this.fileUrl = fileUrl;
    }
    
    public Long getId() {
    	return id;
    }
    public void setId(Long id) {
    	this.id=id;
    }
}
