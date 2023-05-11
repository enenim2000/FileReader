package com.dot.FileReader.service;

import com.dot.FileReader.model.BlockedIpTable;
import com.dot.FileReader.repository.BlockedIpTableRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class BlockedIpTableService {

    private final BlockedIpTableRepository ipTableRepository;

    public BlockedIpTableService(BlockedIpTableRepository ipTableRepository) {
        this.ipTableRepository = ipTableRepository;
    }

    public void saveAll(List<BlockedIpTable> records) {
        ipTableRepository.saveAllAndFlush(records);
    }
}
