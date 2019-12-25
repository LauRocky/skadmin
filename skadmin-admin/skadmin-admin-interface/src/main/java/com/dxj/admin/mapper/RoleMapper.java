package com.dxj.admin.mapper;

import com.dxj.admin.entity.Role;
import com.dxj.admin.entity.dto.RoleDTO;
import com.dxj.common.mapper.EntityMapper;
import org.mapstruct.Mapper;
import org.mapstruct.ReportingPolicy;
import org.springframework.stereotype.Service;

/**
 * @author dxj
 * @date 2019-03-23
 */
@Service
@Mapper(componentModel = "spring", uses = {MenuMapper.class, DeptMapper.class}, unmappedTargetPolicy = ReportingPolicy.IGNORE)
public interface RoleMapper extends EntityMapper<RoleDTO, Role> {

}
