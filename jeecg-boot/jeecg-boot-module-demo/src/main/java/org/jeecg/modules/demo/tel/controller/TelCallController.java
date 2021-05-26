package org.jeecg.modules.demo.tel.controller;

import java.util.Arrays;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;
import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.net.URLDecoder;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.jeecg.common.api.vo.Result;
import org.jeecg.common.system.query.QueryGenerator;
import org.jeecg.common.util.oConvertUtils;
import org.jeecg.modules.demo.tel.entity.TelCall;
import org.jeecg.modules.demo.tel.service.ITelCallService;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import lombok.extern.slf4j.Slf4j;

import org.jeecg.modules.demo.tel.service.JeecgTestClient;
import org.jeecgframework.poi.excel.ExcelImportUtil;
import org.jeecgframework.poi.excel.def.NormalExcelConstants;
import org.jeecgframework.poi.excel.entity.ExportParams;
import org.jeecgframework.poi.excel.entity.ImportParams;
import org.jeecgframework.poi.excel.view.JeecgEntityExcelView;
import org.jeecg.common.system.base.controller.JeecgController;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.stereotype.Component;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.multipart.MultipartHttpServletRequest;
import org.springframework.web.servlet.ModelAndView;
import com.alibaba.fastjson.JSON;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.jeecg.common.aspect.annotation.AutoLog;

/**
 * @Description: 手机空号检测
 * @Author: jeecg-boot
 * @Date: 2021-02-04
 * @Version: V1.0
 */
@Api(tags = "手机空号检测")
@RestController
@RequestMapping("/tel/telCall")
@Slf4j
public class TelCallController extends JeecgController<TelCall, ITelCallService> {
    @Autowired
    private ITelCallService telCallService;

    /**
     * 分页列表查询
     *
     * @param telCall
     * @param pageNo
     * @param pageSize
     * @param req
     * @return
     */
    @AutoLog(value = "手机空号检测-分页列表查询")
    @ApiOperation(value = "手机空号检测-分页列表查询", notes = "手机空号检测-分页列表查询")
    @GetMapping(value = "/list")
    public Result<?> queryPageList(TelCall telCall,
                                   @RequestParam(name = "pageNo", defaultValue = "1") Integer pageNo,
                                   @RequestParam(name = "pageSize", defaultValue = "10") Integer pageSize,
                                   HttpServletRequest req) {
        QueryWrapper<TelCall> queryWrapper = QueryGenerator.initQueryWrapper(telCall, req.getParameterMap());
        Page<TelCall> page = new Page<TelCall>(pageNo, pageSize);
        IPage<TelCall> pageList = telCallService.page(page, queryWrapper);
        return Result.OK(pageList);
    }

    /**
     * 添加
     *
     * @param telCall
     * @return
     */
    @AutoLog(value = "手机空号检测-添加")
    @ApiOperation(value = "手机空号检测-添加", notes = "手机空号检测-添加")
    @PostMapping(value = "/add")
    public Result<?> add(@RequestBody TelCall telCall) {
        telCallService.save(telCall);
        return Result.OK("添加成功！");
    }

    /**
     * 编辑
     *
     * @param telCall
     * @return
     */
    @AutoLog(value = "手机空号检测-编辑")
    @ApiOperation(value = "手机空号检测-编辑", notes = "手机空号检测-编辑")
    @PutMapping(value = "/edit")
    public Result<?> edit(@RequestBody TelCall telCall) {
        telCallService.updateById(telCall);
        return Result.OK("编辑成功!");
    }

    /**
     * 通过id删除
     *
     * @param id
     * @return
     */
    @AutoLog(value = "手机空号检测-通过id删除")
    @ApiOperation(value = "手机空号检测-通过id删除", notes = "手机空号检测-通过id删除")
    @DeleteMapping(value = "/delete")
    public Result<?> delete(@RequestParam(name = "id", required = true) String id) {
        telCallService.removeById(id);
        return Result.OK("删除成功!");
    }

    /**
     * 批量删除
     *
     * @param ids
     * @return
     */
    @AutoLog(value = "手机空号检测-批量删除")
    @ApiOperation(value = "手机空号检测-批量删除", notes = "手机空号检测-批量删除")
    @DeleteMapping(value = "/deleteBatch")
    public Result<?> deleteBatch(@RequestParam(name = "ids", required = true) String ids) {
        this.telCallService.removeByIds(Arrays.asList(ids.split(",")));
        return Result.OK("批量删除成功!");
    }

    @AutoLog(value = "手机空号检测-检测")
    @ApiOperation(value = "手机空号检测-检测", notes = "手机空号检测-检测")
    @PostMapping(value = "/jiance")
    public Result<?> jiance() {
        this.telCallService.jiance();
        return Result.OK("检测完成!");
    }

    /**
     * 通过id查询
     *
     * @param id
     * @return
     */
    @AutoLog(value = "手机空号检测-通过id查询")
    @ApiOperation(value = "手机空号检测-通过id查询", notes = "手机空号检测-通过id查询")
    @GetMapping(value = "/queryById")
    public Result<?> queryById(@RequestParam(name = "id", required = true) String id) {
        TelCall telCall = telCallService.getById(id);
        if (telCall == null) {
            return Result.error("未找到对应数据");
        }
        return Result.OK(telCall);
    }

    /**
     * 导出excel
     *
     * @param request
     * @param telCall
     */
    @RequestMapping(value = "/exportXls")
    public ModelAndView exportXls(HttpServletRequest request, TelCall telCall) {
        return super.exportXls(request, telCall, TelCall.class, "手机空号检测");
    }

    /**
     * 通过excel导入数据
     *
     * @param request
     * @param response
     * @return
     */
    @RequestMapping(value = "/importExcel", method = RequestMethod.POST)
    public Result<?> importExcel(HttpServletRequest request, HttpServletResponse response) {
        return super.importExcel(request, response, TelCall.class);
    }

    @Autowired
    JeecgTestClient jeecgTestClient;
    @PostMapping(value = "/getFeign")
    public Result<?>  getFeign() {
        Result<String> feign = jeecgTestClient.getFeign();
        return feign;
    }


}
