package nexum.cadastropessoas.domain;

import java.time.Instant;
import java.util.UUID;

public class People {
    private UUID uuid;
    private String name;
    private String document;
    private String email;
    private String phone;
    private Instant created;
    private Instant lastUpdated;

    // Getters and Setters
    public UUID getUuid() {
        return uuid;
    }
    public void setUuid(UUID uuid) {
        this.uuid = uuid;
    }

    public String getName() {
        return name;
    }
    public void setName(String name) {
        this.name = name;
    }
    
    public String getDocument() {
        return document;
    }
    public void setDocument(String document) {
        this.document = document;
    }
    
    public String getEmail() {
        return email;
    }
    public void setEmail(String email) {
        this.email = email;
    }
    
    public String getPhone() {
        return phone;
    }
    public void setPhone(String phone) {
        this.phone = phone;
    }
    
    public Instant getCreated() {
        return created;
    }
    public void setCreated(Instant created) {
        this.created = created;
    }
    
    public Instant getLastUpdated() {
        return lastUpdated;
    }
    public void setLastUpdated(Instant lastUpdated) {
        this.lastUpdated = lastUpdated;
    }
    
    // Constructors
    public People(String name, String document) {
        this.name = name;
        this.document = document;
    }
    
}
