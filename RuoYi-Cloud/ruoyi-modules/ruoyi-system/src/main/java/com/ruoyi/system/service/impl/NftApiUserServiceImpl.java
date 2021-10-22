package com.ruoyi.system.service.impl;

import java.util.List;
import com.ruoyi.common.core.utils.DateUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.ruoyi.system.mapper.NftApiUserMapper;
import com.ruoyi.system.domain.NftApiUser;
import com.ruoyi.system.service.INftApiUserService;

/**
 * api平台用户Service业务层处理
 * 
 * @author ruoyi
 * @date 2021-10-20
 */
@Service
public class NftApiUserServiceImpl implements INftApiUserService 
{
    @Autowired
    private NftApiUserMapper nftApiUserMapper;

    /**
     * 查询api平台用户
     * 
     * @param id api平台用户ID
     * @return api平台用户
     */
    @Override
    public NftApiUser selectNftApiUserById(Long id)
    {
        return nftApiUserMapper.selectNftApiUserById(id);
    }

    /**
     * 查询api平台用户列表
     * 
     * @param nftApiUser api平台用户
     * @return api平台用户
     */
    @Override
    public List<NftApiUser> selectNftApiUserList(NftApiUser nftApiUser)
    {
        return nftApiUserMapper.selectNftApiUserList(nftApiUser);
    }

    /**
     * 新增api平台用户
     * 
     * @param nftApiUser api平台用户
     * @return 结果
     */
    @Override
    public int insertNftApiUser(NftApiUser nftApiUser)
    {
        nftApiUser.setCreateTime(DateUtils.getNowDate());
        return nftApiUserMapper.insertNftApiUser(nftApiUser);
    }

    /**
     * 修改api平台用户
     * 
     * @param nftApiUser api平台用户
     * @return 结果
     */
    @Override
    public int updateNftApiUser(NftApiUser nftApiUser)
    {
        return nftApiUserMapper.updateNftApiUser(nftApiUser);
    }

    /**
     * 批量删除api平台用户
     * 
     * @param ids 需要删除的api平台用户ID
     * @return 结果
     */
    @Override
    public int deleteNftApiUserByIds(Long[] ids)
    {
        return nftApiUserMapper.deleteNftApiUserByIds(ids);
    }

    /**
     * 删除api平台用户信息
     * 
     * @param id api平台用户ID
     * @return 结果
     */
    @Override
    public int deleteNftApiUserById(Long id)
    {
        return nftApiUserMapper.deleteNftApiUserById(id);
    }
}
