package com.poly.sof3021.ph29788.infrastructure.listener;

import com.poly.sof3021.ph29788.common.core.AuditEntity;
import jakarta.persistence.PrePersist;
import jakarta.persistence.PreUpdate;

public class AuditEntityListener {

    @PrePersist
    protected void onCreate(AuditEntity entity) {
        entity.setCreatedAt(getCurrentTime());
        entity.setUpdatedAt(getCurrentTime());
        // TODO: Set created_by based on current user or any other logic
        entity.setCreatedBy(getCurrentUser());
    }

    @PreUpdate
    protected void onUpdate(AuditEntity entity) {
        entity.setUpdatedAt(getCurrentTime());
        entity.setUpdatedBy(getCurrentUser());
    }

    private Long getCurrentTime() {
        return System.currentTimeMillis();
    }

    private String getCurrentUser() {
        return "tuannaph";
    }

}
