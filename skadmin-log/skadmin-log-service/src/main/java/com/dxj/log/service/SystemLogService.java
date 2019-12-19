
package com.dxj.log.service;

import com.dxj.common.base.BaseService;
import com.dxj.common.vo.SearchVo;
import com.dxj.log.entity.SystemLog;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

@Service
public interface SystemLogService extends BaseService<SystemLog, String> {

    /**
     * 分页搜索获取日志
     *
     * @param type
     * @param key
     * @param searchVo
     * @param pageable
     * @return
     */
    Page<SystemLog> findByCondition(Integer type, String key, SearchVo searchVo, Pageable pageable);

    /**
     * 删除所有
     */
    void deleteAll();
}
