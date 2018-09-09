package com.platformnexus.data.justCopy.data.source.service;


import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.dao.DataAccessException;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;

@Service
@Slf4j
public class OriginDataService {

    @Autowired
    @Qualifier("source_jdbc")
    private JdbcTemplate jdbcTemplate;

    public List<Map<String, Object>> generateData(String sql) {
        try {
            return jdbcTemplate.queryForList(sql.replaceAll("\n", " "));
        } catch (DataAccessException e) {
            e.printStackTrace();
            log.error("[NOTIFY SUPPORT] - {}", e.getMessage());
        }
        return null;
    }


}
