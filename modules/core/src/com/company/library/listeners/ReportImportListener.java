package com.company.library.listeners;

import com.haulmont.cuba.core.global.Resources;
import com.haulmont.cuba.core.sys.events.AppContextStartedEvent;
import com.haulmont.cuba.security.app.Authenticated;
import com.haulmont.reports.ReportImportExportAPI;
import org.apache.commons.io.IOUtils;
import org.slf4j.Logger;
import org.springframework.context.event.EventListener;
import org.springframework.stereotype.Component;

import javax.inject.Inject;
import java.io.InputStream;
import java.util.Objects;

/**
 * Automatically imports reports from Reports.zip to the database on every server start.
 */
@Component("library_ReportImportListener")
public class ReportImportListener {

    @Inject
    private ReportImportExportAPI reportImportExportAPI;
    @Inject
    private Logger log;
    @Inject
    private Resources resources;

    @Authenticated
    @EventListener
    public void applicationContextStarted(AppContextStartedEvent event) {
        try {
            byte[] reportsZip;
            try (InputStream is = resources.getResourceAsStream("/com/company/library/data/Reports.zip")) {
                Objects.requireNonNull(is);
                reportsZip = IOUtils.toByteArray(is);
            }
            reportImportExportAPI.importReports(reportsZip);
        } catch (Exception e) {
            log.error("Failed to import reports on server start", e);
        }
    }
}