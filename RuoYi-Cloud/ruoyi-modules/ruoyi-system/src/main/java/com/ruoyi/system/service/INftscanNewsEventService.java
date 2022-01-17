package com.ruoyi.system.service;

import com.ruoyi.system.domain.NftscanNewsEvent;

import java.util.List;

/**
 * newsService接口
 * 
 * @author ruoyi
 * @date 2021-11-15
 */
public interface INftscanNewsEventService 
{
    /**
     * 查询news
     * 
     * @param id newsID
     * @return news
     */
    public NftscanNewsEvent selectNftscanNewsEventById(Integer id);

    /**
     * 查询news列表
     * 
     * @param nftscanNewsEvent news
     * @return news集合
     */
    public List<NftscanNewsEvent> selectNftscanNewsEventList(NftscanNewsEvent nftscanNewsEvent);

    /**
     * 新增news
     * 
     * @param nftscanNewsEvent news
     * @return 结果
     */
    public int insertNftscanNewsEvent(NftscanNewsEvent nftscanNewsEvent);

    /**
     * 修改news
     * 
     * @param nftscanNewsEvent news
     * @return 结果
     */
    public int updateNftscanNewsEvent(NftscanNewsEvent nftscanNewsEvent);

    /**
     * 批量删除news
     * 
     * @param ids 需要删除的newsID
     * @return 结果
     */
    public int deleteNftscanNewsEventByIds(Integer[] ids);

    /**
     * 删除news信息
     * 
     * @param id newsID
     * @return 结果
     */
    public int deleteNftscanNewsEventById(Integer id);
}
