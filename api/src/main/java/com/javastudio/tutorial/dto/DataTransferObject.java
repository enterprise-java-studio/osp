package com.javastudio.tutorial.dto;

import java.util.Date;

/**
 * Data Transfer Object
 */
public abstract class DataTransferObject {

    protected static final String NEW_LINE = System.getProperty("line.separator");

    public DataTransferObject() {
    }

    public DataTransferObject(Long id) {
        this.id = id;
    }

    private Long id;

    private Date createOn;

    private Date updateOn;

    private Long version;

    private Boolean deleted;

    private String description;

    public Date getCreateOn() {
        return createOn;
    }

    public Date getUpdateOn() {
        return updateOn;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Boolean isDeleted() {
        return deleted;
    }

    public void setDeleted(Boolean deleted) {
        this.deleted = deleted;
    }

    public Long getVersion() {
        return version;
    }

    public void setCreateOn(Date createOn) {
        this.createOn = createOn;
    }

    public void setUpdateOn(Date updateOn) {
        this.updateOn = updateOn;
    }

    public void setVersion(Long version) {
        this.version = version;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

}
