package com.airtribe.meditrack.entity;

import java.time.LocalDateTime;

/**
 * Abstract base for all domain entities with common behavior.
 */
public abstract class MedicalEntity {
    private final String id;
    private final LocalDateTime createdAt;

    protected MedicalEntity(String id) {
        this.id = id;
        this.createdAt = LocalDateTime.now();
    }

    public String getId() {
        return id;
    }

    public LocalDateTime getCreatedAt() {
        return createdAt;
    }

    public abstract String getDisplayName();
}
