package com.zjgsu.tfy.model;

public class Pet {
    private Long id;
    private String name;
    private String birthDate;
    private String petType;

    public Pet() {}

    public Pet(Long id, String name, String birthDate, String petType) {
        this.id = id;
        this.name = name;
        this.birthDate = birthDate;
        this.petType = petType;
    }

    public Long getId() { return id; }
    public void setId(Long id) { this.id = id; }
    public String getName() { return name; }
    public void setName(String name) { this.name = name; }
    public String getBirthDate() { return birthDate; }
    public void setBirthDate(String birthDate) { this.birthDate = birthDate; }
    public String getPetType() { return petType; }
    public void setPetType(String petType) { this.petType = petType; }
}