package cn.codesensi.darius.system.service.impl;

import cn.codesensi.darius.system.entity.SysUser;
import cn.codesensi.darius.system.mapper.SysUserMapper;
import cn.codesensi.darius.system.service.ISysUserService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.stereotype.Service;

/**
 * 用户信息表 服务实现类
 *
 * @author codesensi
 * @since 2024-05-05 19:17:53
 */
@Service
public class SysUserServiceImpl extends ServiceImpl<SysUserMapper, SysUser> implements ISysUserService {

}
