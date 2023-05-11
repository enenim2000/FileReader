package com.dot.FileReader.repository;

import com.dot.FileReader.model.UserAccessLog;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

/**
 * Repository class for UserAccessLog
 */
public interface UserAccessLogRepository extends BaseRepository<UserAccessLog> {

    @Query(value = "select ip, count(ip) as request_number, concat('request greater than hourly limit of ', :limit) as comment from user_access_log where date between :start and :end group by ip having count(ip) > :limit",
            nativeQuery = true)
    List<Object[]> findBlockedIps(@Param("start") String start, @Param("end") String end, @Param("limit") int limit);

}