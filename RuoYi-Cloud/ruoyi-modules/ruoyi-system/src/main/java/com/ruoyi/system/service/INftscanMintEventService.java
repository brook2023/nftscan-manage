package com.ruoyi.system.service;

import com.ruoyi.system.domain.NftscanMintEvent;

import java.util.List;

/**
 * eventService接口
 * 
 * @author ruoyi
 * @date 2021-11-13
 */
public interface INftscanMintEventService 
{
    /**
     * 查询event
     * 
     * @param id eventID
     * @return event
     */
    public NftscanMintEvent selectNftscanMintEventById(Integer id);

    /**
     * 查询event列表
     * 
     * @param nftscanMintEvent event
     * @return event集合
     */
    public List<NftscanMintEvent> selectNftscanMintEventList(NftscanMintEvent nftscanMintEvent);

    /**
     * 新增event
     * 
     * @param nftscanMintEvent event
     * @return 结果
     */
    public int insertNftscanMintEvent(NftscanMintEvent nftscanMintEvent);

    /**
     * 修改event
     * 
     * @param nftscanMintEvent event
     * @return 结果
     */
    public int updateNftscanMintEvent(NftscanMintEvent nftscanMintEvent);

    /**
     * 批量删除event
     * 
     * @param ids 需要删除的eventID
     * @return 结果
     */
    public int deleteNftscanMintEventByIds(Integer[] ids);

    /**
     * 删除event信息
     * 
     * @param id eventID
     * @return 结果
     */
    public int deleteNftscanMintEventById(Integer id);
}
