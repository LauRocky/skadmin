package com.dxj.admin.query;

import com.dxj.common.annotation.Query;
import lombok.Data;

/**
 * @AUTHOR: sinkiang
 * @DATE: 2019-07-02 20:17
 */
@Data
public class DictQuery {

    @Query(blurry = "name,remark")
    private String blurry;
}
