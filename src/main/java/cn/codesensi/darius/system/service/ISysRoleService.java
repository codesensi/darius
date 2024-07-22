package cn.codesensi.darius.system.service;

import cn.codesensi.darius.system.entity.SysRole;
import com.baomidou.mybatisplus.extension.service.IService;

import java.util.List;

/**
 * 角色信息表 服务类
 *
 * @author codesensi
 * @since 2024-05-15 22:08:36
 */
public interface ISysRoleService extends IService<SysRole> {

    /**
     * 查询用户角色权限码
     *
     * @param userId
     * @return
     */
    List<String> listRoleCodeByUserId(Long userId);
}
