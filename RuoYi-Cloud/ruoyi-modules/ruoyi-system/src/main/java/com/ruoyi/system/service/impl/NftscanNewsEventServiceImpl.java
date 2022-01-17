package com.ruoyi.system.service.impl;

import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.ruoyi.system.mapper.NftscanNewsEventMapper;
import com.ruoyi.system.domain.NftscanNewsEvent;
import com.ruoyi.system.service.INftscanNewsEventService;

/**
 * newsService业务层处理
 * 
 * @author ruoyi
 * @date 2021-11-15
 */
@Service
public class NftscanNewsEventServiceImpl implements INftscanNewsEventService 
{
    @Autowired
    private NftscanNewsEventMapper nftscanNewsEventMapper;

    /**
     * 查询news
     * 
     * @param id newsID
     * @return news
     */
    @Override
    public NftscanNewsEvent selectNftscanNewsEventById(Integer id)
    {
        return nftscanNewsEventMapper.selectNftscanNewsEventById(id);
    }

    /**
     * 查询news列表
     * 
     * @param nftscanNewsEvent news
     * @return news
     */
    @Override
    public List<NftscanNewsEvent> selectNftscanNewsEventList(NftscanNewsEvent nftscanNewsEvent)
    {
        return nftscanNewsEventMapper.selectNftscanNewsEventList(nftscanNewsEvent);
    }

    /**
     * 新增news
     * 
     * @param nftscanNewsEvent news
     * @return 结果
     */
    @Override
    public int insertNftscanNewsEvent(NftscanNewsEvent nftscanNewsEvent)
    {
        return nftscanNewsEventMapper.insertNftscanNewsEvent(nftscanNewsEvent);
    }

    /**
     * 修改news
     * 
     * @param nftscanNewsEvent news
     * @return 结果
     */
    @Override
    public int updateNftscanNewsEvent(NftscanNewsEvent nftscanNewsEvent)
    {
        return nftscanNewsEventMapper.updateNftscanNewsEvent(nftscanNewsEvent);
    }

    /**
     * 批量删除news
     * 
     * @param ids 需要删除的newsID
     * @return 结果
     */
    @Override
    public int deleteNftscanNewsEventByIds(Integer[] ids)
    {
        return nftscanNewsEventMapper.deleteNftscanNewsEventByIds(ids);
    }

    /**
     * 删除news信息
     * 
     * @param id newsID
     * @return 结果
     */
    @Override
    public int deleteNftscanNewsEventById(Integer id)
    {
        return nftscanNewsEventMapper.deleteNftscanNewsEventById(id);
    }
}
