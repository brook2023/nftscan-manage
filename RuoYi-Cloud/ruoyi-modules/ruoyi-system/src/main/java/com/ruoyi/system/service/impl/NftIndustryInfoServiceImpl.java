package com.ruoyi.system.service.impl;

import com.ruoyi.system.domain.NftIndustryInfo;
import com.ruoyi.system.mapper.NftIndustryInfoMapper;
import com.ruoyi.system.service.INftIndustryInfoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * @author king
 * @description TODO
 * @date 2021/6/4
 **/
@Service
public class NftIndustryInfoServiceImpl implements INftIndustryInfoService {

    @Autowired
    private NftIndustryInfoMapper nftIndustryInfoMapper;

    @Override
    public List<NftIndustryInfo> selectInfoList(NftIndustryInfo info) {
        return nftIndustryInfoMapper.selectInfoList(info);
    }

    @Override
    public int insertInfo(NftIndustryInfo info) {
        return nftIndustryInfoMapper.insertInfo(info);
    }

    @Override
    public int updateInfo(NftIndustryInfo info) {
        return nftIndustryInfoMapper.updateInfo(info);
    }

    @Override
    public int deleteInfoByIds(Long[] infoIds) {
        return nftIndustryInfoMapper.deleteInfoByIds(infoIds);
    }

    @Override
    public NftIndustryInfo selectInfoById(Long infoId) {
        return nftIndustryInfoMapper.selectInfoById(infoId);
    }
}
