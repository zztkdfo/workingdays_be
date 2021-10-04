package com.ncits.workingdays.domain;

import com.fasterxml.jackson.annotation.JsonIgnore;
import io.vavr.control.Option;
import lombok.*;
import lombok.experimental.SuperBuilder;
import org.springframework.data.annotation.CreatedBy;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.LastModifiedBy;
import org.springframework.data.annotation.LastModifiedDate;
import org.springframework.data.domain.Auditable;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;
import org.springframework.lang.Nullable;

import javax.persistence.Column;
import javax.persistence.EntityListeners;
import javax.persistence.MappedSuperclass;
import javax.persistence.Transient;
import java.io.Serializable;
import java.time.Instant;
import java.util.Date;
import java.util.Optional;

@Data
@Setter(AccessLevel.PROTECTED)
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@MappedSuperclass
@SuperBuilder(setterPrefix = "with", toBuilder = true)
@EntityListeners(AuditingEntityListener.class)
public abstract class AuditableEntity<ID extends Serializable> implements Auditable<String, ID, Instant> {
    @Column(name = "CREATED_BY", updatable = false)
    @CreatedBy
    private String createdBy;

    @Column(name = "UPDATED_BY")
    @LastModifiedBy
    private String updatedBy;

    @Column(name = "CREATED_DATE", updatable = false)
    @CreatedDate
    private Date createdDate;

    @Column(name = "UPDATED_DATE")
    @LastModifiedDate
    private Date updatedDate;

    @JsonIgnore
    @NonNull
    @Override
    public Optional<String> getCreatedBy() {
        return Optional.of(createdBy);
    }

    @JsonIgnore
    @Override
    public void setCreatedBy(@NonNull String createdBy){
        this.createdBy = createdBy;
    }

    @JsonIgnore
    @NonNull
    @Override
    public Optional<String> getLastModifiedBy() {
        return Optional.of(updatedBy);
    }

    @JsonIgnore
    @Override
    public void setLastModifiedBy(@NonNull String lastModifiedBy){
        this.updatedBy = lastModifiedBy;
    }

    @JsonIgnore
    @NonNull
    @Override
    public Optional<Instant> getLastModifiedDate() {
        return Option.of(updatedDate).map(Date::toInstant).toJavaOptional();
    }

    @JsonIgnore
    @Override
    public void setLastModifiedDate(@NonNull Instant lastModifiedDate){
        this.updatedDate = Option.of(lastModifiedDate).map(Date::from).getOrNull();
    }

    @JsonIgnore
    @NonNull
    @Override
    public Optional<Instant> getCreatedDate() {
        return Option.of(createdDate).map(Date::toInstant).toJavaOptional();
    }

    @JsonIgnore
    @Override
    public void setCreatedDate(@NonNull Instant createdDate){
        this.createdDate = Option.of(createdDate).map(Date::from).getOrNull();
    }

    @JsonIgnore
    @Override
    @Transient
    public boolean isNew() {
        return getId() == null;
    }

    protected AuditableEntity(@Nullable String createdBy, @Nullable String updatedBy, @Nullable Date createdDate, @Nullable Date updatedDate) {
        this.createdBy = createdBy;
        this.updatedBy = updatedBy;
        this.updatedDate = updatedDate;
        this.createdDate = createdDate;
    }

}
