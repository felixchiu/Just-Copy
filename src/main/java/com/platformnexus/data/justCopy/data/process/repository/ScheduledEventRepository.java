package com.platformnexus.data.justCopy.data.process.repository;

import com.platformnexus.data.justCopy.data.process.entity.ScheduledEvent;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;

@RepositoryRestResource
public interface ScheduledEventRepository extends JpaRepository<ScheduledEvent, String> {
}
