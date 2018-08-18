package com.platformnexus.data.justCopy.data.process.entity;


import javax.persistence.PrePersist;
import java.sql.Timestamp;
import java.util.Date;

/**
 * Created by Felix Chiu on 5/5/18.
 */
public class EntityListener {
    @PrePersist
    public void prePersist(BaseEntity entity) {
        entity.setCreatedDatetime(new Timestamp(new Date().getTime()));
    }

}