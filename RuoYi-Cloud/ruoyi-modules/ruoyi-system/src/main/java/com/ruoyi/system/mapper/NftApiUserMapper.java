package com.ruoyi.system.mapper;

import java.util.List;
import com.ruoyi.system.domain.NftApiUser;

/**
 * api平台用户Mapper接口
 * 
 * @author ruoyi
 * @date 2021-10-20
 */
public interface NftApiUserMapper 
{
    /**
     * 查询api平台用户
     * 
     * @param id api平台用户ID
     * @return api平台用户
     */
    public NftApiUser selectNftApiUserById(Long id);

    /**
     * 查询api平台用户列表
     * 
     * @param nftApiUser api平台用户
     * @return api平台用户集合
     */
    public List<NftApiUser> selectNftApiUserList(NftApiUser nftApiUser);

    /**
     * 新增api平台用户
     * 
     * @param nftApiUser api平台用户
     * @return 结果
     */
    public int insertNftApiUser(NftApiUser nftApiUser);

    /**
     * 修改api平台用户
     * 
     * @param nftApiUser api平台用户
     * @return 结果
     */
    public int updateNftApiUser(NftApiUser nftApiUser);

    /**
     * 删除api平台用户
     * 
     * @param id api平台用户ID
     * @return 结果
     */
    public int deleteNftApiUserById(Long id);

    /**
     * 批量删除api平台用户
     * 
     * @param ids 需要删除的数据ID
     * @return 结果
     */
    public int deleteNftApiUserByIds(Long[] ids);
}
