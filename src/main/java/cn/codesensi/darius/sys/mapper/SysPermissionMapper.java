package cn.codesensi.darius.sys.mapper;

import cn.codesensi.darius.sys.entity.SysPermission;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;

import java.util.List;

/**
 * 权限信息表 Mapper接口
 *
 * @author codesensi
 * @since 2024-11-30 23:33:21
 */
public interface SysPermissionMapper extends BaseMapper<SysPermission> {

    /**
     * 查询用户权限编码列表
     *
     * @param userId 用户ID
     * @return 权限编码列表
     */
    List<String> listPermissionCodeByUserId(Long userId);
}
