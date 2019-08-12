package com.team11.dataanalytics.dao;

import com.team11.dataanalytics.domain.SysLog;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface SysLogRepository extends JpaRepository<SysLog, String> {

}
