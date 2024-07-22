package cn.codesensi.darius.system.service;

import cn.codesensi.darius.system.entity.SysMenu;
import com.baomidou.mybatisplus.extension.service.IService;

import java.util.List;

/**
 * 菜单信息表 服务类
 *
 * @author codesensi
 * @since 2024-05-15 22:09:56
 */
public interface ISysMenuService extends IService<SysMenu> {

    /**
     * 查询用户菜单权限码
     *
     * @param userId
     * @return
     */
    List<String> listMenuCodeByUserId(Long userId);
}
