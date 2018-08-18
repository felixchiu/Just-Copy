package com.platformnexus.data.justCopy.data.process.entity;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;

@Entity(name="scheduledEvent")
@Table(name="scheduled_event", indexes = {
        @Index(name = "idx_event_type", columnList="type"),
        @Index(name = "idx_event_created_datetime", columnList="created_datetime")
    }
)
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class ScheduledEvent extends BaseEntity {

    @Column(name="type")
    private String type;

    @Lob
    @Column(name="source_sql")
    private String source_sql;

    @Lob
    @Column(name="pre_target_sql")
    private String preTarget_sql;

    @Lob
    @Column(name="target_sql")
    private String target_sql;

    @Lob
    @Column(name="post_target_sql")
    private String postTarget_sql;

}
