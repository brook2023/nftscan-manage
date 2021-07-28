package com.ruoyi.system.service.impl;

import com.ruoyi.system.domain.NftPlatform;
import com.ruoyi.system.mapper.NftPlatformMapper;
import com.ruoyi.system.service.INftPlatformService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * @author king
 * @description TODO
 * @date 2021/6/7
 **/
@Service("nftPlatformService")
public class NftPlatformServiceImpl implements INftPlatformService {

    @Autowired
    private NftPlatformMapper nftPlatformMapper;

    @Override
    public List<NftPlatform> selectPlatformList(NftPlatform platform) {
        return nftPlatformMapper.selectPlatformList(platform);
    }

    @Override
    public int insertPlatform(NftPlatform platform) {
        return nftPlatformMapper.insertPlatform(platform);
    }

    @Override
    public int updatePlatform(NftPlatform platform) {
        // 更新平台信息
        return nftPlatformMapper.updatePlatform(platform);
    }

    @Override
    public int deletePlatformByIds(Long[] platformIds) {
        return nftPlatformMapper.deletePlatformByIds(platformIds);
    }

    @Override
    public NftPlatform selectPlatformById(Long platformId) {
        return nftPlatformMapper.selectPlatformById(platformId);
    }

    @Override
    public int updatePlatformIsHot(NftPlatform platform) {
        return nftPlatformMapper.updatePlatformIsHot(platform);
    }

    @Override
    public int updatePlatformByName(NftPlatform platform) {
        return nftPlatformMapper.updatePlatformByName(platform);
    }
}
