package com.dot.FileReader.service;

import com.dot.FileReader.model.UserAccessLog;
import org.apache.commons.io.FileUtils;
import org.apache.commons.io.LineIterator;
import org.springframework.stereotype.Service;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

@Service
public class FileProcessingService {

    private final UserAccessLogService userAccessLogService;

    public FileProcessingService(UserAccessLogService userAccessLogService) {
        this.userAccessLogService = userAccessLogService;
    }


    public void processFile(String filePath) {
        int batchSize = 500;

        File file = new File(filePath);

        System.out.println("About to start processing file : " + file.getName());

        //Stream through the contents of the file
        try(LineIterator it = FileUtils.lineIterator(file, "UTF-8")) {
            List<UserAccessLog> userAccessLogs = new ArrayList<>();
            while (it.hasNext()) {
                String currentLine = it.nextLine();
                String[] values = currentLine.split("\\|");
                String date = values[0];
                userAccessLogs.add(new UserAccessLog(date, values[1], values[2], values[3], values[4]));
                if (userAccessLogs.size() >= batchSize) {
                    userAccessLogService.saveAll(userAccessLogs);
                    //System.out.println("New batch persisted");
                    userAccessLogs = new ArrayList<>();
                }
            }

            //Save any remaining records after the loop ends
            if (userAccessLogs.size() > 0) {
                userAccessLogService.saveAll(userAccessLogs);
                System.out.println("Remaining records persisted");
            }

            System.out.println("Done processing file : " + file.getName());

        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
