package cn.codesensi.darius.business.satoken;

import cn.codesensi.darius.system.service.ISysMenuService;
import cn.codesensi.darius.system.service.ISysRoleService;
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
    private ISysMenuService sysMenuService;

    /**
     * 返回一个账号所拥有的权限码集合
     *
     * @param loginId   登录id
     * @param loginType 登录设备
     * @return 权限码集合
     */
    @Override
    public List<String> getPermissionList(Object loginId, String loginType) {
        return sysMenuService.listMenuCodeByUserId((Long) loginId);
    }

    /**
     * 返回一个账号所拥有的角色标识集合
     *
     * @param loginId   登录id
     * @param loginType 登录设备
     * @return 角色标识集合
     */
    @Override
    public List<String> getRoleList(Object loginId, String loginType) {
        return sysRoleService.listRoleCodeByUserId((Long) loginId);
    }

}
