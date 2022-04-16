package com.api.hospital.model.entities;

import com.api.hospital.listenner.BaseAuditListener;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.SuperBuilder;

import javax.persistence.Column;
import javax.persistence.EntityListeners;
import javax.persistence.MappedSuperclass;
import java.time.LocalDateTime;

@Data
@AllArgsConstructor
@NoArgsConstructor
@SuperBuilder
@MappedSuperclass
@EntityListeners(BaseAuditListener.class)
public class BaseAudit {

    @Column(name = "CreatedBy")
    private String createdBy;

    @Column(name = "CreatedAt")
    private LocalDateTime createdAt;

    @Column(name = "ModifiedBy")
    private String modifiedBy;

    @Column(name = "ModifiedAt")
    private LocalDateTime modifiedAt;
}
