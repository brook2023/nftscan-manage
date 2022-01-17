package com.ruoyi.system.service.impl;

import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.ruoyi.system.mapper.NftscanMintEventMapper;
import com.ruoyi.system.domain.NftscanMintEvent;
import com.ruoyi.system.service.INftscanMintEventService;

/**
 * eventService业务层处理
 * 
 * @author ruoyi
 * @date 2021-11-13
 */
@Service
public class NftscanMintEventServiceImpl implements INftscanMintEventService 
{
    @Autowired
    private NftscanMintEventMapper nftscanMintEventMapper;

    /**
     * 查询event
     * 
     * @param id eventID
     * @return event
     */
    @Override
    public NftscanMintEvent selectNftscanMintEventById(Integer id)
    {
        return nftscanMintEventMapper.selectNftscanMintEventById(id);
    }

    /**
     * 查询event列表
     * 
     * @param nftscanMintEvent event
     * @return event
     */
    @Override
    public List<NftscanMintEvent> selectNftscanMintEventList(NftscanMintEvent nftscanMintEvent)
    {
        return nftscanMintEventMapper.selectNftscanMintEventList(nftscanMintEvent);
    }

    /**
     * 新增event
     * 
     * @param nftscanMintEvent event
     * @return 结果
     */
    @Override
    public int insertNftscanMintEvent(NftscanMintEvent nftscanMintEvent)
    {
        return nftscanMintEventMapper.insertNftscanMintEvent(nftscanMintEvent);
    }

    /**
     * 修改event
     * 
     * @param nftscanMintEvent event
     * @return 结果
     */
    @Override
    public int updateNftscanMintEvent(NftscanMintEvent nftscanMintEvent)
    {
        return nftscanMintEventMapper.updateNftscanMintEvent(nftscanMintEvent);
    }

    /**
     * 批量删除event
     * 
     * @param ids 需要删除的eventID
     * @return 结果
     */
    @Override
    public int deleteNftscanMintEventByIds(Integer[] ids)
    {
        return nftscanMintEventMapper.deleteNftscanMintEventByIds(ids);
    }

    /**
     * 删除event信息
     * 
     * @param id eventID
     * @return 结果
     */
    @Override
    public int deleteNftscanMintEventById(Integer id)
    {
        return nftscanMintEventMapper.deleteNftscanMintEventById(id);
    }
}
