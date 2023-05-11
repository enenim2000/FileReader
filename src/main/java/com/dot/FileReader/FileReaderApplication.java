package com.dot.FileReader;


import com.dot.FileReader.enums.Duration;
import com.dot.FileReader.model.BlockedIpTable;
import com.dot.FileReader.service.BlockedIpTableService;
import com.dot.FileReader.service.FileProcessingService;
import com.dot.FileReader.service.UserAccessLogService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.ApplicationArguments;
import org.springframework.boot.ApplicationRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import java.util.List;

@SpringBootApplication
public class FileReaderApplication implements ApplicationRunner {

	@Autowired
	FileProcessingService fileProcessingService;

	@Autowired
	UserAccessLogService userAccessLogService;

	@Autowired
	BlockedIpTableService blockedIpTableService;

	public static void main(String[] args) {
		SpringApplication.run(FileReaderApplication.class, args);
	}

	@Override
	public void run(ApplicationArguments args) {

		String filePath = "";
		String start = "";
		String duration = "";
		String limit = "";

		if ( args.getOptionValues("accessFile") != null) {
			filePath = args.getOptionValues("accessFile").get(0);
		}

		if ( args.getOptionValues("start") != null) {
			start = args.getOptionValues("start").get(0).replace(".", " ");
		}

		if ( args.getOptionValues("duration") != null) {
			duration = args.getOptionValues("duration").get(0);
		}

		if (args.getNonOptionArgs() != null && args.getNonOptionArgs().size() > 0) {
			limit = args.getNonOptionArgs().get(0).replace("-limit=", "").trim();
		}

		if (!filePath.trim().equals("") ) {
			int limitNumeric = Integer.parseInt(limit);

			if (!Duration.isValid(duration)) {
				System.out.println("Invalid duration, hourly or daily expected.");
				return;
			}

			fileProcessingService.processFile(filePath);

			//Retrieved IPs that have made more than required request limit
			List<BlockedIpTable> blockedIps = userAccessLogService.getBlockedIps(start, duration, limitNumeric);

			//Log IPs to the Blocked IPs Table
			blockedIpTableService.saveAll(blockedIps);

			//print blocked ips to console
			blockedIps.forEach(System.out::println);
		}
	}
}
