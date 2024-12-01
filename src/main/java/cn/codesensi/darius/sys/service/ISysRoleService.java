package cn.codesensi.darius.sys.service;

import cn.codesensi.darius.sys.entity.SysRole;
import com.baomidou.mybatisplus.extension.service.IService;

import java.util.List;

/**
 * 角色信息表 服务类
 *
 * @author codesensi
 * @since 2024-11-30 23:33:21
 */
public interface ISysRoleService extends IService<SysRole> {

    /**
     * 查询用户角色编码列表
     *
     * @param userId 用户ID
     * @return 角色编码列表
     */
    List<String> listRoleCodeByUserId(Long userId);
}
