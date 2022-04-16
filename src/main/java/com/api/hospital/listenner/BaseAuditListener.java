package com.api.hospital.listenner;

import com.api.hospital.model.entities.BaseAudit;

import javax.persistence.PrePersist;
import javax.persistence.PreUpdate;
import java.time.LocalDateTime;

public class BaseAuditListener {

    @PrePersist
    public void setModifiedAtAndCreatedAt(BaseAudit entity) {
        setModifiedAt(entity);
        entity.setCreatedAt(LocalDateTime.now());
    }

    @PreUpdate
    public void setModifiedAt(BaseAudit entity) {
        entity.setModifiedAt(LocalDateTime.now());
    }
}