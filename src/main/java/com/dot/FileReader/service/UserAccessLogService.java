package com.dot.FileReader.service;

import com.dot.FileReader.enums.Duration;
import com.dot.FileReader.model.BlockedIpTable;
import com.dot.FileReader.model.UserAccessLog;
import com.dot.FileReader.repository.UserAccessLogRepository;
import com.dot.FileReader.util.DateUtil;
import org.springframework.stereotype.Service;

import java.math.BigInteger;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

@Service
public class UserAccessLogService {

    private final UserAccessLogRepository accessLogRepository;

    public UserAccessLogService(UserAccessLogRepository accessLogRepository) {
        this.accessLogRepository = accessLogRepository;
    }

    public void saveAll(List<UserAccessLog> records) {
        accessLogRepository.saveAllAndFlush(records);
    }

    public void save(UserAccessLog records) {
        accessLogRepository.saveAndFlush(records);
    }

    public List<BlockedIpTable> getBlockedIps(String start, String duration, int limit) {

        LocalDateTime startDate = DateUtil.parse(start);
        String endOfDay = DateUtil.today() + " 23:59:59.999";
        LocalDateTime endOfCurrentDay = DateUtil.parse(endOfDay);
        System.out.println("End of Day: " + endOfDay);
        LocalDateTime endDate = Duration.HOURLY.getValue().equalsIgnoreCase(duration) ?
                startDate.plusHours(1) : endOfCurrentDay;

        System.out.println("Start date: " + startDate + " End date: " + endDate.toString());

        List<Object[]> results = accessLogRepository.findBlockedIps(start, endDate.toString(), limit);
        List<BlockedIpTable> blockedIps = new ArrayList<>();
        for (Object[] row : results) {
            BlockedIpTable ipTable = new BlockedIpTable();
            ipTable.setIp((String) row[0]);
            ipTable.setRequestNumber(row[1].toString());
            ipTable.setComment("Blocked because the total number of request made is " + ipTable.getRequestNumber() + ", which is greater than the " + duration + " rate limit of " + limit);
            blockedIps.add(ipTable);
        }

        return blockedIps;
    }
}
