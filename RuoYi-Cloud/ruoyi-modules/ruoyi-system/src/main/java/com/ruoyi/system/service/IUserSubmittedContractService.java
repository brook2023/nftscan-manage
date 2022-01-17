package com.ruoyi.system.service;

import com.ruoyi.system.domain.UserSubmittedContract;

import java.util.List;

/**
 * NFT项目信息提交Service接口
 * 
 * @author ruoyi
 * @date 2022-01-13
 */
public interface IUserSubmittedContractService 
{
    /**
     * 查询NFT项目信息提交
     * 
     * @param id NFT项目信息提交ID
     * @return NFT项目信息提交
     */
    public UserSubmittedContract selectUserSubmittedContractById(Integer id);

    /**
     * 查询NFT项目信息提交列表
     * 
     * @param userSubmittedContract NFT项目信息提交
     * @return NFT项目信息提交集合
     */
    public List<UserSubmittedContract> selectUserSubmittedContractList(UserSubmittedContract userSubmittedContract);

    /**
     * 新增NFT项目信息提交
     * 
     * @param userSubmittedContract NFT项目信息提交
     * @return 结果
     */
    public int insertUserSubmittedContract(UserSubmittedContract userSubmittedContract);

    /**
     * 修改NFT项目信息提交
     * 
     * @param userSubmittedContract NFT项目信息提交
     * @return 结果
     */
    public int updateUserSubmittedContract(UserSubmittedContract userSubmittedContract);

    /**
     * 批量删除NFT项目信息提交
     * 
     * @param ids 需要删除的NFT项目信息提交ID
     * @return 结果
     */
    public int deleteUserSubmittedContractByIds(Integer[] ids);

    /**
     * 删除NFT项目信息提交信息
     * 
     * @param id NFT项目信息提交ID
     * @return 结果
     */
    public int deleteUserSubmittedContractById(Integer id);
}
