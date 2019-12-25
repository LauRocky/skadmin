package com.dxj.admin.entity.dto;

import lombok.Data;

import java.io.Serializable;
import java.sql.Timestamp;

/**
* @author dxj
* @date 2019-04-10
*/
@Data
public class DictDetailDTO implements Serializable {

    private Long id;

    // 字典标签
    private String label;

    // 字典值
    private String value;

    // 排序
    private String sort;

    private DictSmallDTO dict;

    private Timestamp createTime;
}
