package com.dxj.log.service;

import com.dxj.common.vo.SearchVo;
import com.dxj.log.entity.EsLog;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

/**
 * @author Sinkiang
 */
@Service
public interface EsLogService {

    /**
     * 添加日志
     *
     * @param esLog
     * @return
     */
    EsLog saveLog(EsLog esLog);

    /**
     * 通过id删除日志
     *
     * @param id
     */
    void deleteLog(String id);

    /**
     * 删除全部日志
     */
    void deleteAll();

    /**
     * 分页搜索获取日志
     *
     * @param type
     * @param key
     * @param searchVo
     * @param pageable
     * @return
     */
    Page<EsLog> findByCondition(Integer type, String key, SearchVo searchVo, Pageable pageable);
}
