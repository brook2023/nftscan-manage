package com.ruoyi.system.service;

import java.util.List;

import com.ruoyi.system.domain.NftApiUser;

/**
 * api平台用户Service接口
 *
 * @author ruoyi
 * @date 2021-10-20
 */
public interface INftApiUserService {
    /**
     * 查询api平台用户
     *
     * @param id api平台用户ID
     * @return api平台用户
     */
    NftApiUser selectNftApiUserById(Long id);


    /**
     * 查询api平台用户列表
     *
     * @param nftApiUser api平台用户
     * @return api平台用户集合
     */
    List<NftApiUser> selectNftApiUserList(NftApiUser nftApiUser);

    /**
     * 新增api平台用户
     *
     * @param nftApiUser api平台用户
     * @return 结果
     */
    int insertNftApiUser(NftApiUser nftApiUser);

    /**
     * 修改api平台用户
     *
     * @param nftApiUser api平台用户
     * @return 结果
     */
    int updateNftApiUser(NftApiUser nftApiUser);

    /**
     * 批量删除api平台用户
     *
     * @param ids 需要删除的api平台用户ID
     * @return 结果
     */
    int deleteNftApiUserByIds(Long[] ids);

    /**
     * 删除api平台用户信息
     *
     * @param id api平台用户ID
     * @return 结果
     */
    int deleteNftApiUserById(Long id);


}
