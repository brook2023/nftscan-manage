package com.ruoyi.system.service.impl;

import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.ruoyi.system.mapper.UserNftFoundryContractMapper;
import com.ruoyi.system.domain.UserNftFoundryContract;
import com.ruoyi.system.service.IUserNftFoundryContractService;

/**
 * userContractService业务层处理
 * 
 * @author ruoyi
 * @date 2021-07-22
 */
@Service
public class UserNftFoundryContractServiceImpl implements IUserNftFoundryContractService 
{
    @Autowired
    private UserNftFoundryContractMapper userNftFoundryContractMapper;

    /**
     * 查询userContract
     * 
     * @param id userContractID
     * @return userContract
     */
    @Override
    public UserNftFoundryContract selectUserNftFoundryContractById(Long id)
    {
        return userNftFoundryContractMapper.selectUserNftFoundryContractById(id);
    }

    /**
     * 查询userContract列表
     * 
     * @param userNftFoundryContract userContract
     * @return userContract
     */
    @Override
    public List<UserNftFoundryContract> selectUserNftFoundryContractList(UserNftFoundryContract userNftFoundryContract)
    {
        return userNftFoundryContractMapper.selectUserNftFoundryContractList(userNftFoundryContract);
    }

    /**
     * 新增userContract
     * 
     * @param userNftFoundryContract userContract
     * @return 结果
     */
    @Override
    public int insertUserNftFoundryContract(UserNftFoundryContract userNftFoundryContract)
    {
        return userNftFoundryContractMapper.insertUserNftFoundryContract(userNftFoundryContract);
    }

    /**
     * 修改userContract
     * 
     * @param userNftFoundryContract userContract
     * @return 结果
     */
    @Override
    public int updateUserNftFoundryContract(UserNftFoundryContract userNftFoundryContract)
    {
        return userNftFoundryContractMapper.updateUserNftFoundryContract(userNftFoundryContract);
    }

    /**
     * 批量删除userContract
     * 
     * @param ids 需要删除的userContractID
     * @return 结果
     */
    @Override
    public int deleteUserNftFoundryContractByIds(Long[] ids)
    {
        return userNftFoundryContractMapper.deleteUserNftFoundryContractByIds(ids);
    }

    /**
     * 删除userContract信息
     * 
     * @param id userContractID
     * @return 结果
     */
    @Override
    public int deleteUserNftFoundryContractById(Long id)
    {
        return userNftFoundryContractMapper.deleteUserNftFoundryContractById(id);
    }
}
