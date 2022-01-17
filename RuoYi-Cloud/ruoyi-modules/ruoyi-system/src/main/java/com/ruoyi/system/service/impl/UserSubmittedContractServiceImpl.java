package com.ruoyi.system.service.impl;

import com.jcraft.jsch.*;
import com.ruoyi.common.core.utils.DateUtils;
import com.ruoyi.system.domain.UserSubmittedContract;
import com.ruoyi.system.mapper.UserSubmittedContractMapper;
import com.ruoyi.system.service.IUserSubmittedContractService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Properties;

/**
 * NFT项目信息提交Service业务层处理
 * 
 * @author ruoyi
 * @date 2022-01-13
 */
@Service
public class UserSubmittedContractServiceImpl implements IUserSubmittedContractService 
{
    @Autowired
    private UserSubmittedContractMapper userSubmittedContractMapper;

    /**
     * 查询NFT项目信息提交
     * 
     * @param id NFT项目信息提交ID
     * @return NFT项目信息提交
     */
    @Override
    public UserSubmittedContract selectUserSubmittedContractById(Integer id)
    {
        return userSubmittedContractMapper.selectUserSubmittedContractById(id);
    }

    /**
     * 查询NFT项目信息提交列表
     * 
     * @param userSubmittedContract NFT项目信息提交
     * @return NFT项目信息提交
     */
    @Override
    public List<UserSubmittedContract> selectUserSubmittedContractList(UserSubmittedContract userSubmittedContract)
    {
        return userSubmittedContractMapper.selectUserSubmittedContractList(userSubmittedContract);
    }

    /**
     * 新增NFT项目信息提交
     * 
     * @param userSubmittedContract NFT项目信息提交
     * @return 结果
     */
    @Override
    public int insertUserSubmittedContract(UserSubmittedContract userSubmittedContract)
    {
        userSubmittedContract.setCreateTime(DateUtils.getNowDate());
        return userSubmittedContractMapper.insertUserSubmittedContract(userSubmittedContract);
    }

    /**
     * 修改NFT项目信息提交
     * 
     * @param userSubmittedContract NFT项目信息提交
     * @return 结果
     */
    @Transactional
    @Override
    public int updateUserSubmittedContract(UserSubmittedContract userSubmittedContract)
    {
        if (userSubmittedContract.getStatus() == 1) {
            // 审核成功，更换logo
            String logoUrl = userSubmittedContract.getLogo();

            String[] split = logoUrl.split("/");

            String logoName = split[split.length-1];

            // 文件类型
            String[] fileNameArr = logoName.split("\\.");
            String fileType = fileNameArr[fileNameArr.length - 1];

            JSch jsch = new JSch();
            Session session = null;
            try {
                session = jsch.getSession("root", "47.98.127.233", 5622);
            } catch (JSchException e) {
                e.printStackTrace();
            }
            session.setPassword("Ee5vJVinZ@F@yTW7jDkf4nNIaT%X6U");
            Properties config = new Properties();
            config.put("StrictHostKeyChecking", "no");
            session.setConfig(config);
            try {
                session.connect();
                Channel channel = session.openChannel("sftp");
                ChannelSftp sftp = (ChannelSftp) channel;
                sftp.connect();
                sftp.cd("/opt/nftscan/media_logo/");
                sftp.rename("/opt/nftscan/media_logo/" + logoName, "/opt/nftscan/media_logo/" + userSubmittedContract.getContract() +"." + fileType);
                session.disconnect();
            } catch (Exception e) {
                e.printStackTrace();
            }
            // 更新项目信息
            userSubmittedContract.setLogo(userSubmittedContract.getContract() + "." + fileType);
            userSubmittedContractMapper.updateNftPlatform(userSubmittedContract);
        }

        return userSubmittedContractMapper.deleteUserSubmittedContractById(userSubmittedContract.getId());
    }

    /**
     * 批量删除NFT项目信息提交
     * 
     * @param ids 需要删除的NFT项目信息提交ID
     * @return 结果
     */
    @Override
    public int deleteUserSubmittedContractByIds(Integer[] ids)
    {
        return userSubmittedContractMapper.deleteUserSubmittedContractByIds(ids);
    }

    /**
     * 删除NFT项目信息提交信息
     * 
     * @param id NFT项目信息提交ID
     * @return 结果
     */
    @Override
    public int deleteUserSubmittedContractById(Integer id)
    {
        return userSubmittedContractMapper.deleteUserSubmittedContractById(id);
    }
}
