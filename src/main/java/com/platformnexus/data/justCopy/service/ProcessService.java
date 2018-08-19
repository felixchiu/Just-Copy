package com.platformnexus.data.justCopy.service;

import com.platformnexus.data.justCopy.data.source.service.OriginDataService;
import com.platformnexus.data.justCopy.data.target.service.UpdateDataService;
import com.platformnexus.data.justCopy.util.TokenGenerator;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.List;
import java.util.Map;

@Slf4j
@Service
public class ProcessService {

    @Autowired
    private OriginDataService originDataService;

    @Autowired
    private UpdateDataService updateDataService;

    @Autowired
    private TokenGenerator tokenGenerator;

    @Value("${query.source.columns}")
    private String[] sourceColumns;

    @Value("${query.source.file}")
    private String sourceFile;

    @Value("${query.target.file}")
    private String targetFile;

    @Value("${query.preTarget.file}")
    private String preTargetFile;

    @Value("${query.postTarget.file}")
    private String postTargetFile;


    public void process() {

        String token = tokenGenerator.nextString();

        try {
            String sourceSql = new String(Files.readAllBytes(Paths.get(sourceFile)));
            String targetSql = new String(Files.readAllBytes(Paths.get(targetFile)));
            if (preTargetFile != null && new File(preTargetFile).exists()) {
                String preTargetSql = new String(Files.readAllBytes(Paths.get(preTargetFile)));
                log.info("Running Pre-Target SQL: {}", preTargetSql);
                updateDataService.updateData(preTargetSql);
            }

            List<Map<String, Object>> originData = originDataService.generateData(sourceSql);
            for (Map<String, Object> item : originData) {
                String[] insertItems = new String[sourceColumns.length + 1];
                insertItems[0] = token;
                int i = 1;
                for (String column : sourceColumns) {
                    insertItems[i] = getStringValue(item.get(column));
                    i++;
                }

                updateDataService.updateData(String.format(targetSql, insertItems));
            }
            if (postTargetFile != null && new File(postTargetFile).exists()) {
                String postTargetSql = new String(Files.readAllBytes(Paths.get(postTargetFile)));
                log.info("Running Post-Target SQL: {}", postTargetSql);
                updateDataService.updateData(postTargetSql);
            }

        } catch (IOException e) {
            e.printStackTrace();
        }


    }

    private String getStringValue(Object value) {
        return value != null ? value.toString() : "";
    }


}
