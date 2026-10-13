package com.zjgsu.tfy.model;

public class Visit {
    private Long id;
    private Long petId;
    private String description;
    private String visitDate;

    public Visit() {}

    public Visit(Long id, Long petId, String description, String visitDate) {
        this.id = id;
        this.petId = petId;
        this.description = description;
        this.visitDate = visitDate;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Long getPetId() {
        return petId;
    }

    public void setPetId(Long petId) {
        this.petId = petId;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getVisitDate() {
        return visitDate;
    }

    public void setVisitDate(String visitDate) {
        this.visitDate = visitDate;
    }
}