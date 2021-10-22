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
import com.ruoyi.system.domain.NftApiUser;
import com.ruoyi.system.service.INftApiUserService;
import com.ruoyi.common.core.web.controller.BaseController;
import com.ruoyi.common.core.web.domain.AjaxResult;
import com.ruoyi.common.core.utils.poi.ExcelUtil;
import com.ruoyi.common.core.web.page.TableDataInfo;

/**
 * api平台用户Controller
 * 
 * @author ruoyi
 * @date 2021-10-20
 */
@RestController
@RequestMapping("/apiUser")
public class NftApiUserController extends BaseController
{
    @Autowired
    private INftApiUserService nftApiUserService;

    /**
     * 查询api平台用户列表
     */
    @PreAuthorize(hasPermi = "system:apiUser:list")
    @GetMapping("/list")
    public TableDataInfo list(NftApiUser nftApiUser)
    {
        startPage();
        List<NftApiUser> list = nftApiUserService.selectNftApiUserList(nftApiUser);



        return getDataTable(list);
    }

    /**
     * 导出api平台用户列表
     */
    @PreAuthorize(hasPermi = "system:apiUser:export")
    @Log(title = "api平台用户", businessType = BusinessType.EXPORT)
    @PostMapping("/export")
    public void export(HttpServletResponse response, NftApiUser nftApiUser) throws IOException
    {
        List<NftApiUser> list = nftApiUserService.selectNftApiUserList(nftApiUser);
        ExcelUtil<NftApiUser> util = new ExcelUtil<NftApiUser>(NftApiUser.class);
        util.exportExcel(response, list, "apiUser");
    }

    /**
     * 获取api平台用户详细信息
     */
    @PreAuthorize(hasPermi = "system:apiUser:query")
    @GetMapping(value = "/{id}")
    public AjaxResult getInfo(@PathVariable("id") Long id)
    {
        return AjaxResult.success(nftApiUserService.selectNftApiUserById(id));
    }

    /**
     * 新增api平台用户
     */
    @PreAuthorize(hasPermi = "system:apiUser:add")
    @Log(title = "api平台用户", businessType = BusinessType.INSERT)
    @PostMapping
    public AjaxResult add(@RequestBody NftApiUser nftApiUser)
    {
        return toAjax(nftApiUserService.insertNftApiUser(nftApiUser));
    }

    /**
     * 修改api平台用户
     */
    @PreAuthorize(hasPermi = "system:apiUser:edit")
    @Log(title = "api平台用户", businessType = BusinessType.UPDATE)
    @PutMapping
    public AjaxResult edit(@RequestBody NftApiUser nftApiUser)
    {
        return toAjax(nftApiUserService.updateNftApiUser(nftApiUser));
    }

    /**
     * 删除api平台用户
     */
    @PreAuthorize(hasPermi = "system:apiUser:remove")
    @Log(title = "api平台用户", businessType = BusinessType.DELETE)
	@DeleteMapping("/{ids}")
    public AjaxResult remove(@PathVariable Long[] ids)
    {
        return toAjax(nftApiUserService.deleteNftApiUserByIds(ids));
    }
}
