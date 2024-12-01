package cn.codesensi.darius.sys.service.impl;

import cn.codesensi.darius.common.constant.CacheConstant;
import cn.codesensi.darius.common.constant.Constant;
import cn.codesensi.darius.sys.entity.SysRole;
import cn.codesensi.darius.sys.mapper.SysRoleMapper;
import cn.codesensi.darius.sys.service.ISysRoleService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * 角色信息表 服务实现类
 *
 * @author codesensi
 * @since 2024-11-30 23:33:21
 */
@Service
public class SysRoleServiceImpl extends ServiceImpl<SysRoleMapper, SysRole> implements ISysRoleService {

    /**
     * 查询用户角色编码列表
     *
     * @param userId 用户ID
     * @return 角色编码列表
     */
    @Cacheable(cacheNames = CacheConstant.CACHE_USER, key = "'role:' + #userId")
    @Override
    public List<String> listRoleCodeByUserId(Long userId) {
        // 超级管理员
        if (Constant.ADMIN_ID.equals(userId)) {
            return List.of(Constant.PERMIT_ADMIN_CODE);
        }
        return baseMapper.listRoleCodeByUserId(userId);
    }
}
