package com.platformnexus.data.justCopy.service;

import com.platformnexus.data.justCopy.data.source.service.OriginDataService;
import lombok.extern.slf4j.Slf4j;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;

import javax.activation.DataSource;
import javax.mail.util.ByteArrayDataSource;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.List;
import java.util.Map;

@Slf4j
@Service
public class ReportingService {

    @Autowired
    private MailSenderService mailSenderService;

    @Autowired
    private OriginDataService originDataService;

    @Value("${report.address.to.list}")
    private String[] toAddresses;

    @Value("${report.address.from}")
    private String fromAddress;

    @Value("${report.message.subject}")
    private String subject;

    @Value("${report.name.prefix}")
    private String reportFileName;

    @Value("${report.name.suffix.datetimePattern}")
    private String reportFileNameDateFormat;

    @Value("${report.message.body}")
    private String emailBody;

    @Value("${report.source.sql.file}")
    private String fileName;

    /***
     * We only support output format Excel
     */
    private final static String EXPORT_APPLICATION_CONTENT = "application/vnd.openxmlformats-officedocument.spreadsheetml.sheet";
    private final static String reportFileNameSuffix = ".xlsx";

    @Scheduled(fixedRateString="${report.scheduled.fixedRate}")
    public void generateReport() {
        try {
            DataSource dataSource = packageDataSource(originDataService.generateData(new String(Files.readAllBytes(Paths.get(fileName)))));
            sendData(dataSource);
        } catch (IOException e) {
            e.printStackTrace();
            log.error("[NOTIFY SUPPORT] - {}", e.getMessage());
        } catch (Exception e) {
            e.printStackTrace();
            log.error("[NOTIFY SUPPORT] - {}", e.getMessage());
        }
    }

    public DataSource packageDataSource(List<Map<String, Object>> dataRecords) throws IOException {

        // Setup the Worksheet in a workbook
        XSSFWorkbook workbook = new XSSFWorkbook();
        XSSFSheet worksheet = workbook.createSheet(worksheetName);

        // Setup header
        Row header = worksheet.createRow(0);
        int headerCount = 0;
        for (String headerString : headers) {
            header.createCell(headerCount).setCellValue(headerString);
            headerCount++;
        }

        // Setup content
        int row = 1;
        log.info("Number of record(s) in Report: " + dataRecords.size());
        for (Map<String, Object> dataRecord : dataRecords) {
            Row thisRow = worksheet.createRow(row);
            headerCount = 0;
            for (String column: columns) {
                thisRow.createCell(headerCount).setCellValue(getStringValue(dataRecord.get(column)));
                headerCount++;
            }
            row++;
        }

        for (int i = 0; i < headers.length; i++) {
            worksheet.autoSizeColumn(i);
        }

        ByteArrayOutputStream outStream = new ByteArrayOutputStream();
        workbook.write(outStream);
        return new ByteArrayDataSource(outStream.toByteArray(), EXPORT_APPLICATION_CONTENT);
    }

    private void sendData(DataSource dataSource) {
        String timestampText = LocalDateTime.now().format(DateTimeFormatter.ofPattern(reportFileNameDateFormat));
        String attachmentFileName = reportFileName + timestampText + reportFileNameSuffix;
        for (String toAddress : toAddresses) {
            log.info("Sending report to {}", toAddress);
            mailSenderService.sendSummaryReport(subject, emailBody, toAddress , fromAddress, attachmentFileName, dataSource, true);
        }
        log.info("Report Sent");
    }

    private String getStringValue(Object value) {
        return value != null ? value.toString() : "";
    }
}
