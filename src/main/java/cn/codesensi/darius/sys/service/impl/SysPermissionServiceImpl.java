package cn.codesensi.darius.sys.service.impl;

import cn.codesensi.darius.common.constant.CacheConstant;
import cn.codesensi.darius.common.constant.Constant;
import cn.codesensi.darius.sys.entity.SysPermission;
import cn.codesensi.darius.sys.mapper.SysPermissionMapper;
import cn.codesensi.darius.sys.service.ISysPermissionService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * 权限信息表 服务实现类
 *
 * @author codesensi
 * @since 2024-11-30 23:33:21
 */
@Service
public class SysPermissionServiceImpl extends ServiceImpl<SysPermissionMapper, SysPermission> implements ISysPermissionService {

    /**
     * 查询用户权限编码列表
     *
     * @param userId 用户ID
     * @return 权限编码列表
     */
    @Cacheable(cacheNames = CacheConstant.CACHE_USER, key ="'permission:' + #userId")
    @Override
    public List<String> listPermissionCodeByUserId(Long userId) {
        // 超级管理员
        if (Constant.ADMIN_ID.equals(userId)) {
            return List.of(Constant.PERMIT_ADMIN_CODE);
        }
        return baseMapper.listPermissionCodeByUserId(userId);
    }
}
