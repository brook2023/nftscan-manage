package com.ruoyi.system.controller;

import java.util.List;
import java.io.IOException;
import javax.servlet.http.HttpServletResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import com.ruoyi.common.log.annotation.Log;
import com.ruoyi.common.log.enums.BusinessType;
import com.ruoyi.common.security.annotation.PreAuthorize;
import com.ruoyi.system.domain.NftscanNewsEvent;
import com.ruoyi.system.service.INftscanNewsEventService;
import com.ruoyi.common.core.web.controller.BaseController;
import com.ruoyi.common.core.web.domain.AjaxResult;
import com.ruoyi.common.core.utils.poi.ExcelUtil;
import com.ruoyi.common.core.web.page.TableDataInfo;

/**
 * newsController
 * 
 * @author ruoyi
 * @date 2021-11-15
 */
@RestController
@RequestMapping("/news")
public class NftscanNewsEventController extends BaseController
{
    @Autowired
    private INftscanNewsEventService nftscanNewsEventService;

    /**
     * 查询news列表
     */
    @PreAuthorize(hasPermi = "system:news:list")
    @GetMapping("/list")
    public TableDataInfo list(NftscanNewsEvent nftscanNewsEvent)
    {
        startPage();
        List<NftscanNewsEvent> list = nftscanNewsEventService.selectNftscanNewsEventList(nftscanNewsEvent);
        return getDataTable(list);
    }

    /**
     * 导出news列表
     */
    @PreAuthorize(hasPermi = "system:news:export")
    @Log(title = "news", businessType = BusinessType.EXPORT)
    @PostMapping("/export")
    public void export(HttpServletResponse response, NftscanNewsEvent nftscanNewsEvent) throws IOException
    {
        List<NftscanNewsEvent> list = nftscanNewsEventService.selectNftscanNewsEventList(nftscanNewsEvent);
        ExcelUtil<NftscanNewsEvent> util = new ExcelUtil<NftscanNewsEvent>(NftscanNewsEvent.class);
        util.exportExcel(response, list, "news");
    }

    /**
     * 获取news详细信息
     */
    @PreAuthorize(hasPermi = "system:news:query")
    @GetMapping(value = "/{id}")
    public AjaxResult getInfo(@PathVariable("id") Integer id)
    {
        return AjaxResult.success(nftscanNewsEventService.selectNftscanNewsEventById(id));
    }

    /**
     * 新增news
     */
    @PreAuthorize(hasPermi = "system:news:add")
    @Log(title = "news", businessType = BusinessType.INSERT)
    @PostMapping
    public AjaxResult add(@RequestBody NftscanNewsEvent nftscanNewsEvent)
    {
        return toAjax(nftscanNewsEventService.insertNftscanNewsEvent(nftscanNewsEvent));
    }

    /**
     * 修改news
     */
    @PreAuthorize(hasPermi = "system:news:edit")
    @Log(title = "news", businessType = BusinessType.UPDATE)
    @PutMapping
    public AjaxResult edit(@RequestBody NftscanNewsEvent nftscanNewsEvent)
    {
        return toAjax(nftscanNewsEventService.updateNftscanNewsEvent(nftscanNewsEvent));
    }

    /**
     * 删除news
     */
    @PreAuthorize(hasPermi = "system:news:remove")
    @Log(title = "news", businessType = BusinessType.DELETE)
	@DeleteMapping("/{ids}")
    public AjaxResult remove(@PathVariable Integer[] ids)
    {
        return toAjax(nftscanNewsEventService.deleteNftscanNewsEventByIds(ids));
    }
}
