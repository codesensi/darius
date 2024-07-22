package cn.codesensi.darius.system.mapper;

import cn.codesensi.darius.system.entity.SysRole;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;

/**
 * 角色信息表 Mapper接口
 *
 * @author codesensi
 * @since 2024-05-15 22:08:36
 */
public interface SysRoleMapper extends BaseMapper<SysRole> {

    /**
     * 查询用户角色权限码
     *
     * @param userId
     * @return
     */
    List<String> listRoleCodeByUserId(@Param("userId") Long userId);
}
