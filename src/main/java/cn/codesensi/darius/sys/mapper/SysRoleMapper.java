package cn.codesensi.darius.sys.mapper;

import cn.codesensi.darius.sys.entity.SysRole;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;

import java.util.List;

/**
 * 角色信息表 Mapper接口
 *
 * @author codesensi
 * @since 2024-11-30 23:33:21
 */
public interface SysRoleMapper extends BaseMapper<SysRole> {

    /**
     * 查询用户角色编码列表
     *
     * @param userId 用户ID
     * @return 角色编码列表
     */
    List<String> listRoleCodeByUserId(Long userId);
}
