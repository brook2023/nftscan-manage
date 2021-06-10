package com.ruoyi.system.controller;


import com.ruoyi.common.core.utils.SecurityUtils;
import com.ruoyi.common.core.utils.poi.ExcelUtil;
import com.ruoyi.common.core.web.controller.BaseController;
import com.ruoyi.common.core.web.domain.AjaxResult;
import com.ruoyi.common.core.web.page.TableDataInfo;
import com.ruoyi.common.log.annotation.Log;
import com.ruoyi.common.log.enums.BusinessType;
import com.ruoyi.common.security.annotation.PreAuthorize;
import com.ruoyi.system.domain.NftIndustryInfo;
import com.ruoyi.system.service.INftIndustryInfoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.List;

/**
 * @author king
 * @description TODO
 * @date 2021/6/4
 **/
@RestController
@RequestMapping("/info")
public class NftIndustryInfoController extends BaseController{

    @Autowired
    private INftIndustryInfoService nftIndustryInfoService;

    /**
     * 获取列表
     */
    @PreAuthorize(hasPermi = "system:info:list")
    @GetMapping("/list")
    public TableDataInfo list(NftIndustryInfo info)
    {
        startPage();
        info.setCreateBy(SecurityUtils.getUsername());
        List<NftIndustryInfo> list = nftIndustryInfoService.selectInfoList(info);
        return getDataTable(list);
    }

    /**
     * 新增
     */
    @PreAuthorize(hasPermi = "system:info:add")
    @Log(title = "资讯管理", businessType = BusinessType.INSERT)
    @PostMapping
    public AjaxResult add(@Validated @RequestBody NftIndustryInfo info)
    {
        info.setCreateBy(SecurityUtils.getUsername());
        return toAjax(nftIndustryInfoService.insertInfo(info));
    }

    /**
     * 修改岗位
     */
    @PreAuthorize(hasPermi = "system:info:edit")
    @Log(title = "资讯管理", businessType = BusinessType.UPDATE)
    @PutMapping
    public AjaxResult edit(@Validated @RequestBody NftIndustryInfo info)
    {
        return toAjax(nftIndustryInfoService.updateInfo(info));
    }

    /**
     * 删除岗位
     */
    @PreAuthorize(hasPermi = "system:info:remove")
    @Log(title = "资讯管理", businessType = BusinessType.DELETE)
    @DeleteMapping("/{infoIds}")
    public AjaxResult remove(@PathVariable Long[] infoIds)
    {
        return toAjax(nftIndustryInfoService.deleteInfoByIds(infoIds));
    }

    /**
     * 根据岗位编号获取详细信息
     */
    @PreAuthorize(hasPermi = "system:info:query")
    @GetMapping(value = "/{infoId}")
    public AjaxResult getInfo(@PathVariable Long infoId)
    {
        return AjaxResult.success(nftIndustryInfoService.selectInfoById(infoId));
    }

    @Log(title = "资讯管理", businessType = BusinessType.EXPORT)
    @PreAuthorize(hasPermi = "system:info:export")
    @PostMapping("/export")
    public void export(HttpServletResponse response, NftIndustryInfo info) throws IOException
    {
        List<NftIndustryInfo> list = nftIndustryInfoService.selectInfoList(info);
        ExcelUtil<NftIndustryInfo> util = new ExcelUtil<NftIndustryInfo>(NftIndustryInfo.class);
        util.exportExcel(response, list, "资讯数据");
    }
}
