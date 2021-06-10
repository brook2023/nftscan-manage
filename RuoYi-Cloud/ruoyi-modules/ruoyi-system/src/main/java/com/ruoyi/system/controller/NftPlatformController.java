package com.ruoyi.system.controller;


import com.ruoyi.common.core.utils.poi.ExcelUtil;
import com.ruoyi.common.core.web.controller.BaseController;
import com.ruoyi.common.core.web.domain.AjaxResult;
import com.ruoyi.common.core.web.page.TableDataInfo;
import com.ruoyi.common.log.annotation.Log;
import com.ruoyi.common.log.enums.BusinessType;
import com.ruoyi.common.security.annotation.PreAuthorize;
import com.ruoyi.system.domain.NftPlatform;
import com.ruoyi.system.service.INftPlatformService;
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
@RequestMapping("/platform")
public class NftPlatformController extends BaseController{

    @Autowired
    private INftPlatformService nftPlatformService;

    /**
     * 获取列表
     */
    @PreAuthorize(hasPermi = "system:platform:list")
    @GetMapping("/list")
    public TableDataInfo list(NftPlatform platform)
    {
        startPage();
        List<NftPlatform> list = nftPlatformService.selectPlatformList(platform);
        return getDataTable(list);
    }

    /**
     * 新增
     */
    @PreAuthorize(hasPermi = "system:platform:add")
    @Log(title = "平台管理", businessType = BusinessType.INSERT)
    @PostMapping
    public AjaxResult add(@Validated @RequestBody NftPlatform platform)
    {
        return toAjax(nftPlatformService.insertPlatform(platform));
    }

    /**
     * 修改岗位
     */
    @PreAuthorize(hasPermi = "system:platform:edit")
    @Log(title = "平台管理", businessType = BusinessType.UPDATE)
    @PutMapping
    public AjaxResult edit(@Validated @RequestBody NftPlatform platform)
    {
        return toAjax(nftPlatformService.updatePlatform(platform));
    }

    /**
     * 删除岗位
     */
    @PreAuthorize(hasPermi = "system:platform:remove")
    @Log(title = "平台管理", businessType = BusinessType.DELETE)
    @DeleteMapping("/{platformIds}")
    public AjaxResult remove(@PathVariable Long[] platformIds)
    {
        return toAjax(nftPlatformService.deletePlatformByIds(platformIds));
    }

    /**
     * 根据岗位编号获取详细信息
     */
    @PreAuthorize(hasPermi = "system:platform:query")
    @GetMapping(value = "/{platformId}")
    public AjaxResult getPlatform(@PathVariable Long platformId)
    {
        return AjaxResult.success(nftPlatformService.selectPlatformById(platformId));
    }

    @Log(title = "平台管理", businessType = BusinessType.EXPORT)
    @PreAuthorize(hasPermi = "system:platform:export")
    @PostMapping("/export")
    public void export(HttpServletResponse response, NftPlatform platform) throws IOException
    {
        List<NftPlatform> list = nftPlatformService.selectPlatformList(platform);
        ExcelUtil<NftPlatform> util = new ExcelUtil<NftPlatform>(NftPlatform.class);
        util.exportExcel(response, list, "平台数据");
    }
}
