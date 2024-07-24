package cn.codesensi.darius.system.service.impl;

import cn.codesensi.darius.common.cache.caffeine.CaffeineConstant;
import cn.codesensi.darius.common.constant.Constant;
import cn.codesensi.darius.system.entity.SysRole;
import cn.codesensi.darius.system.mapper.SysRoleMapper;
import cn.codesensi.darius.system.service.ISysRoleService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * 角色信息表 服务实现类
 *
 * @author codesensi
 * @since 2024-05-15 22:08:36
 */
@Service
public class SysRoleServiceImpl extends ServiceImpl<SysRoleMapper, SysRole> implements ISysRoleService {

    /**
     * 查询用户角色权限码
     * 先从缓存中查询，缓存中没有则从数据库查询，结果不为空则放入缓存
     *
     * @param userId 用户id
     * @return 用户角色权限码
     */
    @Cacheable(cacheNames = CaffeineConstant.USER_ROLE, key = "#userId", unless = "#result == null")
    @Override
    public List<String> listRoleCodeByUserId(Long userId) {
        // 超级管理员
        if (Constant.ADMINISTRATOR_ID.equals(userId)) {
            return List.of(Constant.ADMINISTRATOR_ROLE_CODE);
        }
        return baseMapper.listRoleCodeByUserId(userId);
    }
}
