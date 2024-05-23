package com.outreach.rest.runner;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.CommandLineRunner;
import org.springframework.core.annotation.Order;
import org.springframework.core.io.Resource;
import org.springframework.core.io.ResourceLoader;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Component;
import org.springframework.util.FileCopyUtils;

import java.io.InputStreamReader;
import java.nio.charset.StandardCharsets;

@Component
@Order(1)
public class DatabaseSetupRunner implements CommandLineRunner {
    private final JdbcTemplate jdbcTemplate;
    private final ResourceLoader resourceLoader;

    private static final Logger logger = LoggerFactory.getLogger(DatabaseSetupRunner.class);

    public DatabaseSetupRunner(JdbcTemplate jdbcTemplate, ResourceLoader resourceLoader) {
        this.jdbcTemplate = jdbcTemplate;
        this.resourceLoader = resourceLoader;
    }

    @Override
    public void run(String... args) throws Exception {
        logger.info("Checking if data is present...");
        if (!isDataPresent()) {
            logger.info("No data is present, inserting data now...");
            insertInitialData();
        }
    }

    private boolean isDataPresent() {
        try {
            Integer inventoryCount = jdbcTemplate.queryForObject("SELECT COUNT(*) FROM store_inventory", Integer.class);
            Integer storeCount = jdbcTemplate.queryForObject("SELECT COUNT(*) FROM stores", Integer.class);
            logger.info("Rows present: " + (inventoryCount + storeCount));
            return inventoryCount != null && inventoryCount > 0 && storeCount != null && storeCount > 0;
        } catch (Exception e) {
            return false;
        }
    }

    private void insertInitialData() {
        try {
            Resource resource = resourceLoader.getResource("classpath:data.sql");
            String sql = FileCopyUtils.copyToString(new InputStreamReader(resource.getInputStream(), StandardCharsets.UTF_8));
            String[] sqlStatements = sql.split(";");
            for (String statement : sqlStatements) {
                if (!statement.trim().isEmpty()) {
                    jdbcTemplate.execute(statement);
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
