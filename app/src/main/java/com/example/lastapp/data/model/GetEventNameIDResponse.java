package com.example.lastapp.data.model;

public class GetEventNameIDResponse {

    private String name, id;

    public void NameAndId (String name, String id) {
        this.setName(name);
        this.setId(id);
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

}
