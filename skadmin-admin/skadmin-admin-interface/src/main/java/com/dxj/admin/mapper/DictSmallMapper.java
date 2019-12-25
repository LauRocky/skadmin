package com.dxj.admin.mapper;

import com.dxj.admin.entity.Dict;
import com.dxj.admin.entity.dto.DictSmallDTO;
import com.dxj.common.mapper.EntityMapper;
import org.mapstruct.Mapper;
import org.mapstruct.ReportingPolicy;

/**
* @author Zheng Jie
* @date 2019-04-10
*/
@Mapper(componentModel = "spring",unmappedTargetPolicy = ReportingPolicy.IGNORE)
public interface DictSmallMapper extends EntityMapper<DictSmallDTO, Dict> {

}
