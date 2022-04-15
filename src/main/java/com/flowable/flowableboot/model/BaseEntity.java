package com.flowable.flowableboot.model;

import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;

import javax.persistence.Column;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.MappedSuperclass;
import java.time.Instant;

@MappedSuperclass
public class BaseEntity {
    @Id
    @GeneratedValue
    private Long id;

    @CreationTimestamp
    @Column(name = "createdDate", updatable = false, nullable = false)
    protected Instant createdDate;

    @UpdateTimestamp
    @Column(name = "updatedDate")
    protected Instant updatedDate;

    public BaseEntity() {
        this.id = id;
        this.createdDate = Instant.now();
        this.updatedDate = createdDate;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Instant getCreatedDate() {
        return createdDate;
    }

    public void setCreatedDate(Instant createdDate) {
        this.createdDate = createdDate;
    }

    public Instant getUpdatedDate() {
        return updatedDate;
    }

    public void setUpdatedDate(Instant updatedDate) {
        this.updatedDate = updatedDate;
    }
}
