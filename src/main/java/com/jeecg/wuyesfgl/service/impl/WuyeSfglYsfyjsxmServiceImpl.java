package com.jeecg.wuyesfgl.service.impl;
import com.jeecg.wuyesfgl.service.WuyeSfglYsfyjsxmServiceI;
import org.jeecgframework.core.common.service.impl.CommonServiceImpl;
import com.jeecg.wuyesfgl.entity.WuyeSfglYsfyjsxmEntity;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import java.util.HashMap;
import java.util.Map;
import java.util.UUID;
import java.io.Serializable;
import org.jeecgframework.core.util.ApplicationContextUtil;
import org.jeecgframework.core.util.MyClassLoader;
import org.jeecgframework.core.util.StringUtil;
import org.jeecgframework.web.cgform.enhance.CgformEnhanceJavaInter;

@Service("wuyeSfglYsfyjsxmService")
@Transactional
public class WuyeSfglYsfyjsxmServiceImpl extends CommonServiceImpl implements WuyeSfglYsfyjsxmServiceI {

	
 	public void delete(WuyeSfglYsfyjsxmEntity entity) throws Exception{
 		super.delete(entity);
 		//执行删除操作增强业务
		this.doDelBus(entity);
 	}
 	
 	public Serializable save(WuyeSfglYsfyjsxmEntity entity) throws Exception{
 		Serializable t = super.save(entity);
 		//执行新增操作增强业务
 		this.doAddBus(entity);
 		return t;
 	}
 	
 	public void saveOrUpdate(WuyeSfglYsfyjsxmEntity entity) throws Exception{
 		super.saveOrUpdate(entity);
 		//执行更新操作增强业务
 		this.doUpdateBus(entity);
 	}
 	
 	/**
	 * 新增操作增强业务
	 * @param t
	 * @return
	 */
	private void doAddBus(WuyeSfglYsfyjsxmEntity t) throws Exception{
		//-----------------sql增强 start----------------------------
	 	//-----------------sql增强 end------------------------------
	 	
	 	//-----------------java增强 start---------------------------
	 	//-----------------java增强 end-----------------------------
 	}
 	/**
	 * 更新操作增强业务
	 * @param t
	 * @return
	 */
	private void doUpdateBus(WuyeSfglYsfyjsxmEntity t) throws Exception{
		//-----------------sql增强 start----------------------------
	 	//-----------------sql增强 end------------------------------
	 	
	 	//-----------------java增强 start---------------------------
	 	//-----------------java增强 end-----------------------------
 	}
 	/**
	 * 删除操作增强业务
	 * @param id
	 * @return
	 */
	private void doDelBus(WuyeSfglYsfyjsxmEntity t) throws Exception{
	    //-----------------sql增强 start----------------------------
	 	//-----------------sql增强 end------------------------------
	 	
	 	//-----------------java增强 start---------------------------
	 	//-----------------java增强 end-----------------------------
 	}
 	
 	private Map<String,Object> populationMap(WuyeSfglYsfyjsxmEntity t){
		Map<String,Object> map = new HashMap<String,Object>();
		map.put("id", t.getId());
		map.put("create_name", t.getCreateName());
		map.put("create_by", t.getCreateBy());
		map.put("create_date", t.getCreateDate());
		map.put("update_name", t.getUpdateName());
		map.put("update_by", t.getUpdateBy());
		map.put("update_date", t.getUpdateDate());
		map.put("sys_org_code", t.getSysOrgCode());
		map.put("sys_company_code", t.getSysCompanyCode());
		map.put("bpm_status", t.getBpmStatus());
		map.put("glqid", t.getGlqid());
		map.put("sfxmdm", t.getSfxmdm());
		map.put("sfxmmc", t.getSfxmmc());
		map.put("jfqssj", t.getJfqssj());
		map.put("jfjzsj", t.getJfjzsj());
		map.put("sffs", t.getSffs());
		map.put("jffs", t.getJffs());
		map.put("jfzq", t.getJfzq());
		map.put("jfzqdw", t.getJfzqdw());
		map.put("jfdj", t.getJfdj());
		map.put("jfjd", t.getJfjd());
		map.put("znjl", t.getZnjl());
		map.put("znjksts", t.getZnjksts());
		map.put("jsgs", t.getJsgs());
		return map;
	}
 	
 	/**
	 * 替换sql中的变量
	 * @param sql
	 * @param t
	 * @return
	 */
 	public String replaceVal(String sql,WuyeSfglYsfyjsxmEntity t){
 		sql  = sql.replace("#{id}",String.valueOf(t.getId()));
 		sql  = sql.replace("#{create_name}",String.valueOf(t.getCreateName()));
 		sql  = sql.replace("#{create_by}",String.valueOf(t.getCreateBy()));
 		sql  = sql.replace("#{create_date}",String.valueOf(t.getCreateDate()));
 		sql  = sql.replace("#{update_name}",String.valueOf(t.getUpdateName()));
 		sql  = sql.replace("#{update_by}",String.valueOf(t.getUpdateBy()));
 		sql  = sql.replace("#{update_date}",String.valueOf(t.getUpdateDate()));
 		sql  = sql.replace("#{sys_org_code}",String.valueOf(t.getSysOrgCode()));
 		sql  = sql.replace("#{sys_company_code}",String.valueOf(t.getSysCompanyCode()));
 		sql  = sql.replace("#{bpm_status}",String.valueOf(t.getBpmStatus()));
 		sql  = sql.replace("#{glqid}",String.valueOf(t.getGlqid()));
 		sql  = sql.replace("#{sfxmdm}",String.valueOf(t.getSfxmdm()));
 		sql  = sql.replace("#{sfxmmc}",String.valueOf(t.getSfxmmc()));
 		sql  = sql.replace("#{jfqssj}",String.valueOf(t.getJfqssj()));
 		sql  = sql.replace("#{jfjzsj}",String.valueOf(t.getJfjzsj()));
 		sql  = sql.replace("#{sffs}",String.valueOf(t.getSffs()));
 		sql  = sql.replace("#{jffs}",String.valueOf(t.getJffs()));
 		sql  = sql.replace("#{jfzq}",String.valueOf(t.getJfzq()));
 		sql  = sql.replace("#{jfzqdw}",String.valueOf(t.getJfzqdw()));
 		sql  = sql.replace("#{jfdj}",String.valueOf(t.getJfdj()));
 		sql  = sql.replace("#{jfjd}",String.valueOf(t.getJfjd()));
 		sql  = sql.replace("#{znjl}",String.valueOf(t.getZnjl()));
 		sql  = sql.replace("#{znjksts}",String.valueOf(t.getZnjksts()));
 		sql  = sql.replace("#{jsgs}",String.valueOf(t.getJsgs()));
 		sql  = sql.replace("#{UUID}",UUID.randomUUID().toString());
 		return sql;
 	}
 	
 	/**
	 * 执行JAVA增强
	 */
 	private void executeJavaExtend(String cgJavaType,String cgJavaValue,Map<String,Object> data) throws Exception {
 		if(StringUtil.isNotEmpty(cgJavaValue)){
			Object obj = null;
			try {
				if("class".equals(cgJavaType)){
					//因新增时已经校验了实例化是否可以成功，所以这块就不需要再做一次判断
					obj = MyClassLoader.getClassByScn(cgJavaValue).newInstance();
				}else if("spring".equals(cgJavaType)){
					obj = ApplicationContextUtil.getContext().getBean(cgJavaValue);
				}
				if(obj instanceof CgformEnhanceJavaInter){
					CgformEnhanceJavaInter javaInter = (CgformEnhanceJavaInter) obj;
					javaInter.execute("wuye_sfgl_ysfyjsxm",data);
				}
			} catch (Exception e) {
				e.printStackTrace();
				throw new Exception("执行JAVA增强出现异常！");
			} 
		}
 	}
}