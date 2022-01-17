package com.ruoyi.system.mapper;

import com.ruoyi.system.domain.UserSubmittedContract;
import org.apache.ibatis.annotations.Update;

import java.util.List;

/**
 * NFT项目信息提交Mapper接口
 * 
 * @author ruoyi
 * @date 2022-01-13
 */
public interface UserSubmittedContractMapper 
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
     * 删除NFT项目信息提交
     * 
     * @param id NFT项目信息提交ID
     * @return 结果
     */
    public int deleteUserSubmittedContractById(Integer id);

    /**
     * 批量删除NFT项目信息提交
     * 
     * @param ids 需要删除的数据ID
     * @return 结果
     */
    public int deleteUserSubmittedContractByIds(Integer[] ids);

    @Update("update nft_full_address set platform = #{contractName},website=#{website},twitter=#{twitter},email=#{email},discord=#{discord},telegram=#{telegram},description_en=#{description},github=#{github},medium=#{medium},auth_flag=1,instagram=#{ins},mirror=#{mirror},facebook=#{facebook},youtube=#{youtube},twitch=#{twitch},statement=#{statement},category=#{contractCategory},metadata=#{metadataUrl},image=#{logo} where address = #{contract}")
    void updateNftPlatform(UserSubmittedContract userSubmittedContract);
}
