package com.ruoyi.system.controller;

import com.ruoyi.common.core.utils.poi.ExcelUtil;
import com.ruoyi.common.core.web.controller.BaseController;
import com.ruoyi.common.core.web.domain.AjaxResult;
import com.ruoyi.common.core.web.page.TableDataInfo;
import com.ruoyi.common.log.annotation.Log;
import com.ruoyi.common.log.enums.BusinessType;
import com.ruoyi.common.security.annotation.PreAuthorize;
import com.ruoyi.system.domain.NftTradeContract;
import com.ruoyi.system.service.INftTradeContractService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.List;

/**
 * tradeContractController
 * 
 * @author ruoyi
 * @date 2021-11-27
 */
@RestController
@RequestMapping("/tradeContract")
public class NftTradeContractController extends BaseController
{
    @Autowired
    private INftTradeContractService nftTradeContractService;

    /**
     * 查询tradeContract列表
     */
    @PreAuthorize(hasPermi = "system:tradeContract:list")
    @GetMapping("/list")
    public TableDataInfo list(NftTradeContract nftTradeContract)
    {
        startPage();
        List<NftTradeContract> list = nftTradeContractService.selectNftTradeContractList(nftTradeContract);
        return getDataTable(list);
    }

    /**
     * 导出tradeContract列表
     */
    @PreAuthorize(hasPermi = "system:tradeContract:export")
    @Log(title = "tradeContract", businessType = BusinessType.EXPORT)
    @PostMapping("/export")
    public void export(HttpServletResponse response, NftTradeContract nftTradeContract) throws IOException
    {
        List<NftTradeContract> list = nftTradeContractService.selectNftTradeContractList(nftTradeContract);
        ExcelUtil<NftTradeContract> util = new ExcelUtil<NftTradeContract>(NftTradeContract.class);
        util.exportExcel(response, list, "tradeContract");
    }

    /**
     * 获取tradeContract详细信息
     */
    @PreAuthorize(hasPermi = "system:tradeContract:query")
    @GetMapping(value = "/{contract}")
    public AjaxResult getInfo(@PathVariable("contract") String contract)
    {
        return AjaxResult.success(nftTradeContractService.selectNftTradeContractById(contract));
    }

    /**
     * 新增tradeContract
     */
    @PreAuthorize(hasPermi = "system:tradeContract:add")
    @Log(title = "tradeContract", businessType = BusinessType.INSERT)
    @PostMapping
    public AjaxResult add(@RequestBody NftTradeContract nftTradeContract)
    {
        return toAjax(nftTradeContractService.insertNftTradeContract(nftTradeContract));
    }

    /**
     * 修改tradeContract
     */
    @PreAuthorize(hasPermi = "system:tradeContract:edit")
    @Log(title = "tradeContract", businessType = BusinessType.UPDATE)
    @PutMapping
    public AjaxResult edit(@RequestBody NftTradeContract nftTradeContract)
    {
        return toAjax(nftTradeContractService.updateNftTradeContract(nftTradeContract));
    }

    /**
     * 删除tradeContract
     */
    @PreAuthorize(hasPermi = "system:tradeContract:remove")
    @Log(title = "tradeContract", businessType = BusinessType.DELETE)
	@DeleteMapping("/{contracts}")
    public AjaxResult remove(@PathVariable String[] contracts)
    {
        return toAjax(nftTradeContractService.deleteNftTradeContractByIds(contracts));
    }
}
