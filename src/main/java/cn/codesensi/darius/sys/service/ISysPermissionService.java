package cn.codesensi.darius.sys.service;

import cn.codesensi.darius.sys.entity.SysPermission;
import com.baomidou.mybatisplus.extension.service.IService;

import java.util.List;

/**
 * 权限信息表 服务类
 *
 * @author codesensi
 * @since 2024-11-30 23:33:21
 */
public interface ISysPermissionService extends IService<SysPermission> {

    /**
     * 查询用户权限编码列表
     *
     * @param userId 用户ID
     * @return 权限编码列表
     */
    List<String> listPermissionCodeByUserId(Long userId);
}
