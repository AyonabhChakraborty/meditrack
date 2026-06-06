package com.airtribe.meditrack.entity;

import java.time.LocalDateTime;

public abstract class MedicalEntity {

    private static int instanceCounter = 0;

    static{
        System.out.println("MedicalEntity class loaded. Initializing resources...");
    }

    private final String id;
    private final LocalDateTime createdAt;


    {
        instanceCounter++;
        System.out.println("Creating instance #" + instanceCounter + " of MedicalEntity.");
    }


    protected MedicalEntity(String id) {
        this.id = id;
        this.createdAt = LocalDateTime.now();
    }

    public abstract String describe(); //To be implemented by subclasses for specific descriptions.

    public String getId(){
        return id;
    }

    public LocalDateTime getCreatedAt(){
        return createdAt;
    }

    public static int getInstanceCount(){
        return instanceCounter;
    }
     
}