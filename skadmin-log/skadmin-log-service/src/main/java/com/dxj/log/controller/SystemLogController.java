package com.dxj.log.controller;

import com.dxj.common.util.PageUtil;
import com.dxj.common.util.ResultUtil;
import com.dxj.common.vo.PageVo;
import com.dxj.common.vo.Result;
import com.dxj.common.vo.SearchVo;
import com.dxj.log.entity.EsLog;
import com.dxj.log.entity.SystemLog;
import com.dxj.log.service.EsLogService;
import com.dxj.log.service.SystemLogService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.data.domain.Page;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.*;

/**
 * @Author: dxj
 * @Date: 2019-12-19 16:45
 */
@Slf4j
@RestController
@Api(description = "日志管理接口")
@RequestMapping("/xboot/log")
@Transactional
public class SystemLogController {

    @Value("${xboot.logRecord.es}")
    private Boolean esRecord;

    private final EsLogService esLogService;

    private final SystemLogService logService;

    @Autowired
    public SystemLogController(EsLogService esLogService, SystemLogService logService) {
        this.esLogService = esLogService;
        this.logService = logService;
    }

    @RequestMapping(value = "/getAllByPage", method = RequestMethod.GET)
    @ApiOperation(value = "分页获取全部")
    public Result<Object> getAllByPage(@RequestParam(required = false) Integer type,
                                       @RequestParam String key,
                                       @ModelAttribute SearchVo searchVo,
                                       @ModelAttribute PageVo pageVo) {

        if (esRecord) {
            Page<EsLog> es = esLogService.findByCondition(type, key, searchVo, PageUtil.initPage(pageVo));
            return new ResultUtil<>().setData(es);
        } else {
            Page<SystemLog> log = logService.findByCondition(type, key, searchVo, PageUtil.initPage(pageVo));
            return new ResultUtil<>().setData(log);
        }
    }

    @RequestMapping(value = "/delByIds/{ids}", method = RequestMethod.DELETE)
    @ApiOperation(value = "批量删除")
    public Result<Void> delByIds(@PathVariable String[] ids) {

        for (String id : ids) {
            if (esRecord) {
                esLogService.deleteLog(id);
            } else {
                logService.delete(id);
            }
        }
        return new ResultUtil<Void>().setSuccessMsg("删除成功");
    }

    @RequestMapping(value = "/delAll", method = RequestMethod.DELETE)
    @ApiOperation(value = "全部删除")
    public Result<Void> delAll() {

        if (esRecord) {
            esLogService.deleteAll();
        } else {
            logService.deleteAll();
        }
        return new ResultUtil<Void>().setSuccessMsg("删除成功");
    }
}
