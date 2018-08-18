package com.platformnexus.data.justCopy.data.process.entity;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;
import org.hibernate.annotations.GenericGenerator;
import org.springframework.format.annotation.DateTimeFormat;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import java.io.Serializable;
import java.sql.Timestamp;

@MappedSuperclass
@EntityListeners(EntityListener.class)
@Data
public class BaseEntity implements Serializable {

    @JsonProperty("id")
    @Id
    @Column(name = "id", unique = true)
    @GeneratedValue(generator = "token-uuid")
    @GenericGenerator(name = "token-uuid", strategy = "com.platformnexus.data.justCopy.util.InquisitiveUUIDGenerator")
    protected String id;

    @NotNull
    @JsonProperty("created_datetime")
    @Column(name="created_datetime", updatable = false)
    @JsonFormat(shape= JsonFormat.Shape.STRING)
    @DateTimeFormat(iso= DateTimeFormat.ISO.DATE)
    protected Timestamp createdDatetime;


}