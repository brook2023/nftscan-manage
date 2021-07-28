package com.ruoyi.system.mapper;

import com.ruoyi.system.domain.UserNftFoundryContract;

import java.util.List;

/**
 * userContractMapper接口
 * 
 * @author ruoyi
 * @date 2021-07-22
 */
public interface UserNftFoundryContractMapper 
{
    /**
     * 查询userContract
     * 
     * @param id userContractID
     * @return userContract
     */
    public UserNftFoundryContract selectUserNftFoundryContractById(Long id);

    /**
     * 查询userContract列表
     * 
     * @param userNftFoundryContract userContract
     * @return userContract集合
     */
    public List<UserNftFoundryContract> selectUserNftFoundryContractList(UserNftFoundryContract userNftFoundryContract);

    /**
     * 新增userContract
     * 
     * @param userNftFoundryContract userContract
     * @return 结果
     */
    public int insertUserNftFoundryContract(UserNftFoundryContract userNftFoundryContract);

    /**
     * 修改userContract
     * 
     * @param userNftFoundryContract userContract
     * @return 结果
     */
    public int updateUserNftFoundryContract(UserNftFoundryContract userNftFoundryContract);

    /**
     * 删除userContract
     * 
     * @param id userContractID
     * @return 结果
     */
    public int deleteUserNftFoundryContractById(Long id);

    /**
     * 批量删除userContract
     * 
     * @param ids 需要删除的数据ID
     * @return 结果
     */
    public int deleteUserNftFoundryContractByIds(Long[] ids);
}
