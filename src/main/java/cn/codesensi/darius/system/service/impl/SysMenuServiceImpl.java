package cn.codesensi.darius.system.service.impl;

import cn.codesensi.darius.common.constant.Constant;
import cn.codesensi.darius.system.entity.SysMenu;
import cn.codesensi.darius.system.mapper.SysMenuMapper;
import cn.codesensi.darius.system.service.ISysMenuService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * 菜单信息表 服务实现类
 *
 * @author codesensi
 * @since 2024-05-15 22:09:56
 */
@Service
public class SysMenuServiceImpl extends ServiceImpl<SysMenuMapper, SysMenu> implements ISysMenuService {

    /**
     * 查询用户菜单权限码
     * 先从缓存中查询，缓存中没有则从数据库查询，结果不为空则放入缓存
     *
     * @param userId 用户id
     * @return 用户菜单权限码
     */
    @Override
    public List<String> listMenuCodeByUserId(Long userId) {
        // 超级管理员
        if (Constant.ADMIN_ID.equals(userId)) {
            return List.of(Constant.PERMIT_ADMIN_CODE);
        }
        return baseMapper.listMenuCodeByUserId(userId);
    }
}
