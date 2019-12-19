package com.dxj.log.service.impl;

import cn.hutool.core.date.DateUtil;
import cn.hutool.core.util.StrUtil;
import com.dxj.common.vo.SearchVo;
import com.dxj.log.dao.LogDao;
import com.dxj.log.entity.SystemLog;
import com.dxj.log.service.SystemLogService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.lang.Nullable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.persistence.criteria.*;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

/**
 * 日志接口实现
 *
 * @author Sinkiang
 */
@Slf4j
@Service
@Transactional
public class LogServiceImpl implements SystemLogService {

    @Autowired
    private LogDao logDao;

    @Override
    public LogDao getRepository() {
        return logDao;
    }

    @Override
    public Page<SystemLog> findByCondition(Integer type, String key, SearchVo searchVo, Pageable pageable) {

        return logDao.findAll(new Specification<SystemLog>() {
            @Nullable
            @Override
            public Predicate toPredicate(Root<SystemLog> root, CriteriaQuery<?> cq, CriteriaBuilder cb) {

                Path<String> nameField = root.get("name");
                Path<String> requestUrlField = root.get("requestUrl");
                Path<String> requestTypeField = root.get("requestType");
                Path<String> requestParamField = root.get("requestParam");
                Path<String> usernameField = root.get("username");
                Path<String> ipField = root.get("ip");
                Path<String> ipInfoField = root.get("ipInfo");
                Path<Integer> logTypeField = root.get("logType");
                Path<Date> createTimeField = root.get("createTime");

                List<Predicate> list = new ArrayList<>();

                //类型
                if (type != null) {
                    list.add(cb.equal(logTypeField, type));
                }

                //模糊搜索
                if (StrUtil.isNotBlank(key)) {
                    Predicate p1 = cb.like(requestUrlField, '%' + key + '%');
                    Predicate p2 = cb.like(requestTypeField, '%' + key + '%');
                    Predicate p3 = cb.like(requestParamField, '%' + key + '%');
                    Predicate p4 = cb.like(usernameField, '%' + key + '%');
                    Predicate p5 = cb.like(ipField, '%' + key + '%');
                    Predicate p6 = cb.like(ipInfoField, '%' + key + '%');
                    Predicate p7 = cb.like(nameField, '%' + key + '%');
                    list.add(cb.or(p1, p2, p3, p4, p5, p6, p7));
                }

                //创建时间
                if (StrUtil.isNotBlank(searchVo.getStartDate()) && StrUtil.isNotBlank(searchVo.getEndDate())) {
                    Date start = DateUtil.parse(searchVo.getStartDate());
                    Date end = DateUtil.parse(searchVo.getEndDate());
                    list.add(cb.between(createTimeField, start, DateUtil.endOfDay(end)));
                }

                Predicate[] arr = new Predicate[list.size()];
                cq.where(list.toArray(arr));
                return null;
            }
        }, pageable);
    }

    @Override
    public void deleteAll() {

        logDao.deleteAll();
    }
}
