package com.rss.platform.portal.model;

import org.springframework.web.multipart.MultipartFile;

public class FormData {
    private MultipartFile file;
    private String appID;
    private String moduleID;

    public MultipartFile getFile() {
        return file;
    }

    public void setFile(MultipartFile file) {
        this.file = file;
    }

    public String getAppID() {
        return appID;
    }

    public void setAppID(String appID) {
        this.appID = appID;
    }

    public String getModuleID() {
        return moduleID;
    }

    public void setModuleID(String moduleID) {
        this.moduleID = moduleID;
    }
}