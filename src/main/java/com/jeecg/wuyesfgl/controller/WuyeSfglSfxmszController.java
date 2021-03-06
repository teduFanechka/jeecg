package com.jeecg.wuyesfgl.controller;
import com.jeecg.wuyeglq.entity.WuyeFwxxEntity;
import com.jeecg.wuyeglq.entity.WuyeGlqEntity;
import com.jeecg.wuyesfgl.dao.WuyeSfglFjsfxmszDao;
import com.jeecg.wuyesfgl.dao.WuyeSfglSfxmszDao;
import com.jeecg.wuyesfgl.entity.WuyeSfglSfxmszEntity;
import com.jeecg.wuyesfgl.entity.WuyeSfglSfxmszPageEntity;
import com.jeecg.wuyesfgl.service.WuyeSfglSfxmszServiceI;
import java.util.ArrayList;
import java.util.List;
import java.text.SimpleDateFormat;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import org.jeecgframework.core.common.controller.BaseController;
import org.jeecgframework.core.common.exception.BusinessException;
import org.jeecgframework.core.common.hibernate.qbc.CriteriaQuery;
import org.jeecgframework.core.common.model.common.TreeChildCount;
import org.jeecgframework.core.common.model.json.AjaxJson;
import org.jeecgframework.core.common.model.json.DataGrid;
import org.jeecgframework.core.constant.Globals;
import org.jeecgframework.core.util.StringUtil;
import org.jeecgframework.tag.core.easyui.TagUtil;
import org.jeecgframework.web.system.pojo.base.TSDepart;
import org.jeecgframework.web.system.service.SystemService;
import org.jeecgframework.core.util.MyBeanUtils;

import java.io.OutputStream;
import org.jeecgframework.core.util.BrowserUtils;
import org.jeecgframework.poi.excel.ExcelExportUtil;
import org.jeecgframework.poi.excel.ExcelImportUtil;
import org.jeecgframework.poi.excel.entity.ExportParams;
import org.jeecgframework.poi.excel.entity.ImportParams;
import org.jeecgframework.poi.excel.entity.TemplateExportParams;
import org.jeecgframework.poi.excel.entity.vo.NormalExcelConstants;
import org.jeecgframework.poi.excel.entity.vo.TemplateExcelConstants;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.jeecgframework.core.util.ResourceUtil;
import java.io.IOException;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.multipart.MultipartHttpServletRequest;
import java.util.Map;
import java.util.HashMap;
import org.jeecgframework.core.util.ExceptionUtil;

import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.jeecgframework.core.beanvalidator.BeanValidators;
import java.util.Set;
import javax.validation.ConstraintViolation;
import javax.validation.Validator;
import java.net.URI;
import org.springframework.http.MediaType;
import org.springframework.web.util.UriComponentsBuilder;
import org.apache.commons.lang3.StringUtils;
import org.jeecgframework.jwt.util.GsonUtil;
import org.jeecgframework.jwt.util.ResponseMessage;
import org.jeecgframework.jwt.util.Result;
import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;

/**   
 * @Title: Controller  
 * @Description: 收费项目管理
 * @author onlineGenerator
 * @date 2018-03-15 10:54:06
 * @version V1.0   
 *
 */
@Controller
@RequestMapping("/wuyeSfglSfxmszController")
@Api(value="WuyeSfglSfxmsz",description="收费项目管理",tags="wuyeSfglSfxmszController")
public class WuyeSfglSfxmszController extends BaseController {
	/**
	 * Logger for this class
	 */
	private static final Logger logger = Logger.getLogger(WuyeSfglSfxmszController.class);

	@Autowired
	private WuyeSfglSfxmszServiceI wuyeSfglSfxmszService;
	@Autowired
	private SystemService systemService;
	@Autowired
	private Validator validator;
	@Autowired
	private WuyeSfglFjsfxmszDao wuyeSfglFjsfxmszDao;
	@Autowired
	private WuyeSfglSfxmszDao wuyeSfglSfxmszDao;


	/**
	 * 收费项目管理列表 页面跳转
	 * 
	 * @return
	 */
	@RequestMapping(params = "list")
	public ModelAndView list(HttpServletRequest request) {
		return new ModelAndView("com/jeecg/wuyesfgl/wuyeSfglSfxmszList");
	}

	/**
	 * easyui AJAX请求数据
	 * 
	 * @param request
	 * @param response
	 * @param dataGrid
	 * @param user
	 */

	@RequestMapping(params = "datagrid")
	public void datagrid(WuyeSfglSfxmszPageEntity wuyeSfglSfxmsz,HttpServletRequest request, HttpServletResponse response, DataGrid dataGrid) {
		//System.err.println(wuyeSfglSfxmsz);
		//查询数据
		List<WuyeSfglSfxmszPageEntity> sfxmsz = wuyeSfglSfxmszDao.findList(wuyeSfglSfxmsz); 
		//查询数量
		int count = wuyeSfglSfxmszDao.findListCount(wuyeSfglSfxmsz); 

		dataGrid.setTotal(count);
		dataGrid.setResults(sfxmsz);
		TagUtil.datagrid(response, dataGrid);
		
	}
	
	
	
	/**
	 * 删除收费项目管理
	 * 
	 * @return
	 */
	@RequestMapping(params = "doDel")
	@ResponseBody
	public AjaxJson doDel(WuyeSfglSfxmszEntity wuyeSfglSfxmsz, HttpServletRequest request) {
		String message = null;
		AjaxJson j = new AjaxJson();
		wuyeSfglSfxmsz = systemService.getEntity(WuyeSfglSfxmszEntity.class, wuyeSfglSfxmsz.getId());
		message = "收费项目管理删除成功";
		try{
			wuyeSfglSfxmszService.delete(wuyeSfglSfxmsz);
			systemService.addLog(message, Globals.Log_Type_DEL, Globals.Log_Leavel_INFO);
		}catch(Exception e){
			e.printStackTrace();
			message = "收费项目管理删除失败";
			throw new BusinessException(e.getMessage());
		}
		j.setMsg(message);
		return j;
	}
	
	/**
	 * 批量删除收费项目管理
	 * 
	 * @return
	 */
	 @RequestMapping(params = "doBatchDel")
	@ResponseBody
	public AjaxJson doBatchDel(String ids,HttpServletRequest request){
		String message = null;
		AjaxJson j = new AjaxJson();
		message = "收费项目管理删除成功";
		try{
			for(String id:ids.split(",")){
				WuyeSfglSfxmszEntity wuyeSfglSfxmsz = systemService.getEntity(WuyeSfglSfxmszEntity.class, 
				id
				);
				wuyeSfglSfxmszService.delete(wuyeSfglSfxmsz);
				systemService.addLog(message, Globals.Log_Type_DEL, Globals.Log_Leavel_INFO);
			}
		}catch(Exception e){
			e.printStackTrace();
			message = "收费项目管理删除失败";
			throw new BusinessException(e.getMessage());
		}
		j.setMsg(message);
		return j;
	}


	/**
	 * 添加收费项目管理
	 * 
	 * @param ids
	 * @return
	 */
	@RequestMapping(params = "doAdd")
	@ResponseBody
	public AjaxJson doAdd(WuyeSfglSfxmszEntity wuyeSfglSfxmsz, HttpServletRequest request) {
		String message = null;
		AjaxJson j = new AjaxJson();
		message = "收费项目管理添加成功";
		try{
			wuyeSfglSfxmszService.save(wuyeSfglSfxmsz);
			systemService.addLog(message, Globals.Log_Type_INSERT, Globals.Log_Leavel_INFO);
		}catch(Exception e){
			e.printStackTrace();
			message = "收费项目管理添加失败";
			throw new BusinessException(e.getMessage());
		}
		j.setMsg(message);
		return j;
	}
	
	/**
	 * 更新收费项目管理
	 * 
	 * @param ids
	 * @return
	 */
	@RequestMapping(params = "doUpdate")
	@ResponseBody
	public AjaxJson doUpdate(WuyeSfglSfxmszEntity wuyeSfglSfxmsz, HttpServletRequest request) {
		String message = null;
		AjaxJson j = new AjaxJson();
		message = "收费项目管理更新成功";
		WuyeSfglSfxmszEntity t = wuyeSfglSfxmszService.get(WuyeSfglSfxmszEntity.class, wuyeSfglSfxmsz.getId());
		try {
			MyBeanUtils.copyBeanNotNull2Bean(wuyeSfglSfxmsz, t);
			wuyeSfglSfxmszService.saveOrUpdate(t);
			systemService.addLog(message, Globals.Log_Type_UPDATE, Globals.Log_Leavel_INFO);
		} catch (Exception e) {
			e.printStackTrace();
			message = "收费项目管理更新失败";
			throw new BusinessException(e.getMessage());
		}
		j.setMsg(message);
		return j;
	}
	

	/**
	 * 收费项目管理新增页面跳转
	 * 
	 * @return
	 */
	@RequestMapping(params = "goAdd")
	public ModelAndView goAdd(WuyeSfglSfxmszEntity wuyeSfglSfxmsz, HttpServletRequest req) {
		//查询管理区表
		List<WuyeGlqEntity> glqList = wuyeSfglFjsfxmszDao.findGlqList(); 
		req.setAttribute("glqList", glqList);
		if (StringUtil.isNotEmpty(wuyeSfglSfxmsz.getId())) {
			wuyeSfglSfxmsz = wuyeSfglSfxmszService.getEntity(WuyeSfglSfxmszEntity.class, wuyeSfglSfxmsz.getId());
			req.setAttribute("wuyeSfglSfxmszPage", wuyeSfglSfxmsz);
		}
		return new ModelAndView("com/jeecg/wuyesfgl/wuyeSfglSfxmsz-add");
	}
	/**
	 * 收费项目管理编辑页面跳转
	 * 
	 * @return
	 */
	@RequestMapping(params = "goUpdate")
	public ModelAndView goUpdate(WuyeSfglSfxmszEntity wuyeSfglSfxmsz, HttpServletRequest req) {
		//查询管理区表
		List<WuyeGlqEntity> glqList = wuyeSfglFjsfxmszDao.findGlqList(); 
		req.setAttribute("glqList", glqList);
		if (StringUtil.isNotEmpty(wuyeSfglSfxmsz.getId())) {
			wuyeSfglSfxmsz = wuyeSfglSfxmszService.getEntity(WuyeSfglSfxmszEntity.class, wuyeSfglSfxmsz.getId());
			//System.err.println(wuyeSfglSfxmsz);
			
			req.setAttribute("wuyeSfglSfxmszPage", wuyeSfglSfxmsz);
		}
		return new ModelAndView("com/jeecg/wuyesfgl/wuyeSfglSfxmsz-update");
	}
	
	/**
	 * 导入功能跳转
	 * 
	 * @return
	 */
	@RequestMapping(params = "upload")
	public ModelAndView upload(HttpServletRequest req) {
		req.setAttribute("controller_name","wuyeSfglSfxmszController");
		return new ModelAndView("common/upload/pub_excel_upload");
	}
	
	/**
	 * 导出excel
	 * 
	 * @param request
	 * @param response
	 */
	@RequestMapping(params = "exportXls")
	public String exportXls(WuyeSfglSfxmszEntity wuyeSfglSfxmsz,HttpServletRequest request,HttpServletResponse response
			, DataGrid dataGrid,ModelMap modelMap) {
		CriteriaQuery cq = new CriteriaQuery(WuyeSfglSfxmszEntity.class, dataGrid);
		org.jeecgframework.core.extend.hqlsearch.HqlGenerateUtil.installHql(cq, wuyeSfglSfxmsz, request.getParameterMap());
		List<WuyeSfglSfxmszEntity> wuyeSfglSfxmszs = this.wuyeSfglSfxmszService.getListByCriteriaQuery(cq,false);
		modelMap.put(NormalExcelConstants.FILE_NAME,"收费项目管理");
		modelMap.put(NormalExcelConstants.CLASS,WuyeSfglSfxmszEntity.class);
		modelMap.put(NormalExcelConstants.PARAMS,new ExportParams("收费项目管理列表", "导出人:"+ResourceUtil.getSessionUser().getRealName(),
			"导出信息"));
		modelMap.put(NormalExcelConstants.DATA_LIST,wuyeSfglSfxmszs);
		return NormalExcelConstants.JEECG_EXCEL_VIEW;
	}
	/**
	 * 导出excel 使模板
	 * 
	 * @param request
	 * @param response
	 */
	@RequestMapping(params = "exportXlsByT")
	public String exportXlsByT(WuyeSfglSfxmszEntity wuyeSfglSfxmsz,HttpServletRequest request,HttpServletResponse response
			, DataGrid dataGrid,ModelMap modelMap) {
    	modelMap.put(NormalExcelConstants.FILE_NAME,"收费项目管理");
    	modelMap.put(NormalExcelConstants.CLASS,WuyeSfglSfxmszEntity.class);
    	modelMap.put(NormalExcelConstants.PARAMS,new ExportParams("收费项目管理列表", "导出人:"+ResourceUtil.getSessionUser().getRealName(),
    	"导出信息"));
    	modelMap.put(NormalExcelConstants.DATA_LIST,new ArrayList());
    	return NormalExcelConstants.JEECG_EXCEL_VIEW;
	}
	
	@SuppressWarnings("unchecked")
	@RequestMapping(params = "importExcel", method = RequestMethod.POST)
	@ResponseBody
	public AjaxJson importExcel(HttpServletRequest request, HttpServletResponse response) {
		AjaxJson j = new AjaxJson();
		
		MultipartHttpServletRequest multipartRequest = (MultipartHttpServletRequest) request;
		Map<String, MultipartFile> fileMap = multipartRequest.getFileMap();
		for (Map.Entry<String, MultipartFile> entity : fileMap.entrySet()) {
			MultipartFile file = entity.getValue();// 获取上传文件对象
			ImportParams params = new ImportParams();
			params.setTitleRows(2);
			params.setHeadRows(1);
			params.setNeedSave(true);
			try {
				List<WuyeSfglSfxmszEntity> listWuyeSfglSfxmszEntitys = ExcelImportUtil.importExcel(file.getInputStream(),WuyeSfglSfxmszEntity.class,params);
				for (WuyeSfglSfxmszEntity wuyeSfglSfxmsz : listWuyeSfglSfxmszEntitys) {
					wuyeSfglSfxmszService.save(wuyeSfglSfxmsz);
				}
				j.setMsg("文件导入成功！");
			} catch (Exception e) {
				j.setMsg("文件导入失败！");
				logger.error(ExceptionUtil.getExceptionMessage(e));
			}finally{
				try {
					file.getInputStream().close();
				} catch (IOException e) {
					e.printStackTrace();
				}
			}
		}
		return j;
	}
	
	@RequestMapping(method = RequestMethod.GET)
	@ResponseBody
	@ApiOperation(value="收费项目管理列表信息",produces="application/json",httpMethod="GET")
	public ResponseMessage<List<WuyeSfglSfxmszEntity>> list() {
		List<WuyeSfglSfxmszEntity> listWuyeSfglSfxmszs=wuyeSfglSfxmszService.getList(WuyeSfglSfxmszEntity.class);
		return Result.success(listWuyeSfglSfxmszs);
	}
	
	@RequestMapping(value = "/{id}", method = RequestMethod.GET)
	@ResponseBody
	@ApiOperation(value="根据ID获取收费项目管理信息",notes="根据ID获取收费项目管理信息",httpMethod="GET",produces="application/json")
	public ResponseMessage<?> get(@ApiParam(required=true,name="id",value="ID")@PathVariable("id") String id) {
		WuyeSfglSfxmszEntity task = wuyeSfglSfxmszService.get(WuyeSfglSfxmszEntity.class, id);
		if (task == null) {
			return Result.error("根据ID获取收费项目管理信息为空");
		}
		return Result.success(task);
	}

	@RequestMapping(method = RequestMethod.POST, consumes = MediaType.APPLICATION_JSON_VALUE)
	@ResponseBody
	@ApiOperation(value="创建收费项目管理")
	public ResponseMessage<?> create(@ApiParam(name="收费项目管理对象")@RequestBody WuyeSfglSfxmszEntity wuyeSfglSfxmsz, UriComponentsBuilder uriBuilder) {
		//调用JSR303 Bean Validator进行校验，如果出错返回含400错误码及json格式的错误信息.
		Set<ConstraintViolation<WuyeSfglSfxmszEntity>> failures = validator.validate(wuyeSfglSfxmsz);
		if (!failures.isEmpty()) {
			return Result.error(JSONArray.toJSONString(BeanValidators.extractPropertyAndMessage(failures)));
		}

		//保存
		try{
			wuyeSfglSfxmszService.save(wuyeSfglSfxmsz);
		} catch (Exception e) {
			e.printStackTrace();
			return Result.error("收费项目管理信息保存失败");
		}
		return Result.success(wuyeSfglSfxmsz);
	}

	@RequestMapping(value = "/{id}", method = RequestMethod.PUT, consumes = MediaType.APPLICATION_JSON_VALUE)
	@ResponseBody
	@ApiOperation(value="更新收费项目管理",notes="更新收费项目管理")
	public ResponseMessage<?> update(@ApiParam(name="收费项目管理对象")@RequestBody WuyeSfglSfxmszEntity wuyeSfglSfxmsz) {
		//调用JSR303 Bean Validator进行校验，如果出错返回含400错误码及json格式的错误信息.
		Set<ConstraintViolation<WuyeSfglSfxmszEntity>> failures = validator.validate(wuyeSfglSfxmsz);
		if (!failures.isEmpty()) {
			return Result.error(JSONArray.toJSONString(BeanValidators.extractPropertyAndMessage(failures)));
		}

		//保存
		try{
			wuyeSfglSfxmszService.saveOrUpdate(wuyeSfglSfxmsz);
		} catch (Exception e) {
			e.printStackTrace();
			return Result.error("更新收费项目管理信息失败");
		}

		//按Restful约定，返回204状态码, 无内容. 也可以返回200状态码.
		return Result.success("更新收费项目管理信息成功");
	}

	@RequestMapping(value = "/{id}", method = RequestMethod.DELETE)
	@ResponseStatus(HttpStatus.NO_CONTENT)
	@ApiOperation(value="删除收费项目管理")
	public ResponseMessage<?> delete(@ApiParam(name="id",value="ID",required=true)@PathVariable("id") String id) {
		logger.info("delete[{}]" + id);
		// 验证
		if (StringUtils.isEmpty(id)) {
			return Result.error("ID不能为空");
		}
		try {
			wuyeSfglSfxmszService.deleteEntityById(WuyeSfglSfxmszEntity.class, id);
		} catch (Exception e) {
			e.printStackTrace();
			return Result.error("收费项目管理删除失败");
		}

		return Result.success();
	}
	/**
	 * 验证输入的项目代码是否存在
	 * */
	@ResponseBody
	@RequestMapping(params = "existence")
	public String existence(String id,String xmdm, HttpServletRequest req) {
		String str1 = "'";
		String str2 = "'";
		String str = "from WuyeSfglSfxmszEntity where xmdm = "+str1+xmdm+str2+"and id <> "+str1+id+str2;
		//先查询房屋信息
		List<WuyeSfglSfxmszEntity> wuyeSfxmsz = systemService.findByQueryString(str);
		//当查不到时候
		if(wuyeSfxmsz.isEmpty()){
			return "success";
		}else{
			return "err";
		}
		
	}
}
