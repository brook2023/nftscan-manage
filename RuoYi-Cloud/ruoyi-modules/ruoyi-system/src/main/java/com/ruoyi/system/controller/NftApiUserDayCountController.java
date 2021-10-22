package com.ruoyi.system.controller;

import com.alibaba.fastjson.JSONObject;
import com.ruoyi.common.core.utils.poi.ExcelUtil;
import com.ruoyi.common.core.web.controller.BaseController;
import com.ruoyi.common.core.web.domain.AjaxResult;
import com.ruoyi.common.core.web.page.TableDataInfo;
import com.ruoyi.common.log.annotation.Log;
import com.ruoyi.common.log.enums.BusinessType;
import com.ruoyi.common.security.annotation.PreAuthorize;
import com.ruoyi.system.domain.NftApiUserDayCount;
import com.ruoyi.system.service.INftApiUserDayCountService;
import com.ruoyi.system.service.INftApiUserService;
import com.ruoyi.system.utils.IStringUtils;
import com.ruoyi.system.vo.ApiUserVo;
import lombok.RequiredArgsConstructor;
import org.springframework.util.CollectionUtils;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

/**
 * apiCountController
 *
 * @author ruoyi
 * @date 2021-10-20
 */
@RestController
@RequestMapping("/apiCount")
@RequiredArgsConstructor
public class NftApiUserDayCountController extends BaseController {

    private final INftApiUserDayCountService nftApiUserDayCountService;
    private final INftApiUserService iNftApiUserService;

    /**
     * 查询apiCount列表
     */
    @PreAuthorize(hasPermi = "system:apiCount:list")
    @GetMapping("/list")
    public TableDataInfo list(NftApiUserDayCount nftApiUserDayCount) {
        startPage();
        List<NftApiUserDayCount> list = nftApiUserDayCountService.selectNftApiUserDayCountList(nftApiUserDayCount);
        TableDataInfo dataTable = getDataTable(list);
        List<ApiUserVo> data = list.stream().map(count -> {
            String apiUserMethodCount = count.getApiUserMethodCount();
            List<Map<String,Integer>> l=JSONObject.parseObject(apiUserMethodCount, List.class);
            List<ApiUserVo.MethodCount> collect = new ArrayList<>();

            if (!CollectionUtils.isEmpty(l)) {
                for (Map<String, Integer> map : l) {
                    for (String k : map.keySet())
                    {
                        ApiUserVo.MethodCount methodCount=new ApiUserVo.MethodCount();
                        methodCount.setMethod(IStringUtils.getMethod(k,":").get(4));
                        methodCount.setCount(map.get(k));
                        collect.add(methodCount);
                    }

                }
            }

            return ApiUserVo.builder()
                    .apiCallTime(count.getApiCallTime())
                    .methodCount(collect)
                    .count(count.getApiUserCount())
                    .apiUser24Count(count.getApiUser24Count())
                    .size(IStringUtils.getSize(count.getApiUserSize()))
                    .email(iNftApiUserService.selectNftApiUserById(count.getApiUserId()).getEmail())
                    .build();
        }).collect(Collectors.toList());
        dataTable.setRows(data);
        return dataTable;
    }

    /**
     * 导出apiCount列表
     */
    @PreAuthorize(hasPermi = "system:apiCount:export")
    @Log(title = "apiCount", businessType = BusinessType.EXPORT)
    @PostMapping("/export")
    public void export(HttpServletResponse response, NftApiUserDayCount nftApiUserDayCount) throws IOException {
        List<NftApiUserDayCount> list = nftApiUserDayCountService.selectNftApiUserDayCountList(nftApiUserDayCount);

        List<ApiUserVo> data = list.stream().map(count -> {
            String apiUserMethodCount = count.getApiUserMethodCount();
            List<Map<String,Integer>> l=JSONObject.parseObject(apiUserMethodCount, List.class);
            List<ApiUserVo.MethodCount> collect = new ArrayList<>();

            if (!CollectionUtils.isEmpty(l)) {
                for (Map<String, Integer> map : l) {
                    for (String k : map.keySet())
                    {
                        ApiUserVo.MethodCount methodCount=new ApiUserVo.MethodCount();
                        methodCount.setMethod(IStringUtils.getMethod(k,":").get(4));
                        methodCount.setCount(map.get(k));
                        collect.add(methodCount);
                    }

                }
            }

            return ApiUserVo.builder()
                    .apiCallTime(count.getApiCallTime())
                    .methodCount(collect)
                    .count(count.getApiUserCount())
                    .apiUser24Count(count.getApiUser24Count())
                    .size(IStringUtils.getSize(count.getApiUserSize()))
                    .email(iNftApiUserService.selectNftApiUserById(count.getApiUserId()).getEmail())
                    .build();
        }).collect(Collectors.toList());

        ExcelUtil<ApiUserVo> util = new ExcelUtil<>(ApiUserVo.class);
        util.exportExcel(response, data, "apiCount");
    }

    /**
     * 获取apiCount详细信息
     */
    @PreAuthorize(hasPermi = "system:apiCount:query")
    @GetMapping(value = "/{id}")
    public AjaxResult getInfo(@PathVariable("id") Long id) {
        return AjaxResult.success(nftApiUserDayCountService.selectNftApiUserDayCountById(id));
    }

    /**
     * 新增apiCount
     */
    @PreAuthorize(hasPermi = "system:apiCount:add")
    @Log(title = "apiCount", businessType = BusinessType.INSERT)
    @PostMapping
    public AjaxResult add(@RequestBody NftApiUserDayCount nftApiUserDayCount) {
        return toAjax(nftApiUserDayCountService.insertNftApiUserDayCount(nftApiUserDayCount));
    }

    /**
     * 修改apiCount
     */
    @PreAuthorize(hasPermi = "system:apiCount:edit")
    @Log(title = "apiCount", businessType = BusinessType.UPDATE)
    @PutMapping
    public AjaxResult edit(@RequestBody NftApiUserDayCount nftApiUserDayCount) {
        return toAjax(nftApiUserDayCountService.updateNftApiUserDayCount(nftApiUserDayCount));
    }

    /**
     * 删除apiCount
     */
    @PreAuthorize(hasPermi = "system:apiCount:remove")
    @Log(title = "apiCount", businessType = BusinessType.DELETE)
    @DeleteMapping("/{ids}")
    public AjaxResult remove(@PathVariable Long[] ids) {
        return toAjax(nftApiUserDayCountService.deleteNftApiUserDayCountByIds(ids));
    }
}
