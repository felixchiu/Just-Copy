package com.platformnexus.data.justCopy.data.target.service;


import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.dao.DataAccessException;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;

@Service
@Slf4j
public class UpdateDataService {

    @Autowired
    @Qualifier("target_jdbc")
    private JdbcTemplate jdbcTemplate;

    @Async("target_update_executor")
    public List<Map<String, Object>> generateData(String sql) {
        try {
            return jdbcTemplate.queryForList(sql);
        } catch (DataAccessException e) {
            e.printStackTrace();
            log.error("[NOTIFY SUPPORT] - {}", e.getMessage());
        }
        return null;
    }


}
