package com.dxj.admin.entity.dto;

import lombok.Data;

import java.io.Serializable;
import java.sql.Timestamp;
import java.util.List;

/**
* @author dxj
* @date 2019-04-10
*/
@Data
public class DictDTO implements Serializable {

    private Long id;

    // 字典名称
    private String name;

    // 描述
    private String remark;

    private List<DictDetailDTO> dictDetails;

    private Timestamp createTime;
}
