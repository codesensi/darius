package cn.codesensi.darius.common.config.satoken;

import cn.codesensi.darius.sys.service.ISysPermissionService;
import cn.codesensi.darius.sys.service.ISysRoleService;
import cn.dev33.satoken.stp.StpInterface;
import jakarta.annotation.Resource;
import org.springframework.stereotype.Component;

import java.util.List;

/**
 * 自定义权限验证接口扩展
 */
@Component
public class StpInterfaceImpl implements StpInterface {

    @Resource
    private ISysRoleService sysRoleService;
    @Resource
    private ISysPermissionService sysPermissionService;

    /**
     * 返回一个账号所拥有的权限编码列表
     *
     * @param loginId   用户ID
     * @param loginType 登录设备
     * @return 权限编码列表
     */
    @Override
    public List<String> getPermissionList(Object loginId, String loginType) {
        return sysPermissionService.listPermissionCodeByUserId(Long.valueOf(String.valueOf(loginId)));
    }

    /**
     * 返回一个账号所拥有的角色编码列表
     *
     * @param loginId   用户ID
     * @param loginType 登录设备
     * @return 角色编码列表
     */
    @Override
    public List<String> getRoleList(Object loginId, String loginType) {
        return sysRoleService.listRoleCodeByUserId(Long.valueOf(String.valueOf(loginId)));
    }

}
