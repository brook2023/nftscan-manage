package com.ruoyi.system.controller;

import com.ruoyi.common.core.utils.poi.ExcelUtil;
import com.ruoyi.common.core.web.controller.BaseController;
import com.ruoyi.common.core.web.domain.AjaxResult;
import com.ruoyi.common.core.web.page.TableDataInfo;
import com.ruoyi.common.log.annotation.Log;
import com.ruoyi.common.log.enums.BusinessType;
import com.ruoyi.common.security.annotation.PreAuthorize;
import com.ruoyi.system.domain.NftscanMintEvent;
import com.ruoyi.system.service.INftscanMintEventService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.List;

/**
 * eventController
 * 
 * @author ruoyi
 * @date 2021-11-13
 */
@RestController
@RequestMapping("/event")
public class NftscanMintEventController extends BaseController
{
    @Autowired
    private INftscanMintEventService nftscanMintEventService;

    /**
     * 查询event列表
     */
    @PreAuthorize(hasPermi = "system:event:list")
    @GetMapping("/list")
    public TableDataInfo list(NftscanMintEvent nftscanMintEvent)
    {
        startPage();
        List<NftscanMintEvent> list = nftscanMintEventService.selectNftscanMintEventList(nftscanMintEvent);
        return getDataTable(list);
    }

    /**
     * 导出event列表
     */
    @PreAuthorize(hasPermi = "system:event:export")
    @Log(title = "event", businessType = BusinessType.EXPORT)
    @PostMapping("/export")
    public void export(HttpServletResponse response, NftscanMintEvent nftscanMintEvent) throws IOException
    {
        List<NftscanMintEvent> list = nftscanMintEventService.selectNftscanMintEventList(nftscanMintEvent);
        ExcelUtil<NftscanMintEvent> util = new ExcelUtil<NftscanMintEvent>(NftscanMintEvent.class);
        util.exportExcel(response, list, "event");
    }

    /**
     * 获取event详细信息
     */
    @PreAuthorize(hasPermi = "system:event:query")
    @GetMapping(value = "/{id}")
    public AjaxResult getInfo(@PathVariable("id") Integer id)
    {
        return AjaxResult.success(nftscanMintEventService.selectNftscanMintEventById(id));
    }

    /**
     * 新增event
     */
    @PreAuthorize(hasPermi = "system:event:add")
    @Log(title = "event", businessType = BusinessType.INSERT)
    @PostMapping
    public AjaxResult add(@RequestBody NftscanMintEvent nftscanMintEvent)
    {
        return toAjax(nftscanMintEventService.insertNftscanMintEvent(nftscanMintEvent));
    }

    /**
     * 修改event
     */
    @PreAuthorize(hasPermi = "system:event:edit")
    @Log(title = "event", businessType = BusinessType.UPDATE)
    @PutMapping
    public AjaxResult edit(@RequestBody NftscanMintEvent nftscanMintEvent)
    {
        return toAjax(nftscanMintEventService.updateNftscanMintEvent(nftscanMintEvent));
    }

    /**
     * 删除event
     */
    @PreAuthorize(hasPermi = "system:event:remove")
    @Log(title = "event", businessType = BusinessType.DELETE)
	@DeleteMapping("/{ids}")
    public AjaxResult remove(@PathVariable Integer[] ids)
    {
        return toAjax(nftscanMintEventService.deleteNftscanMintEventByIds(ids));
    }
}
