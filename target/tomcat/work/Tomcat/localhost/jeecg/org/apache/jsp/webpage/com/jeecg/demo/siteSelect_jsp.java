/*
 * Generated by the Jasper component of Apache Tomcat
 * Version: Apache Tomcat/7.0.47
 * Generated at: 2018-04-03 06:23:36 UTC
 * Note: The last modified time of this file was set to
 *       the last modified time of the source file after
 *       generation to assist with modification tracking.
 */
package org.apache.jsp.webpage.com.jeecg.demo;

import javax.servlet.*;
import javax.servlet.http.*;
import javax.servlet.jsp.*;

public final class siteSelect_jsp extends org.apache.jasper.runtime.HttpJspBase
    implements org.apache.jasper.runtime.JspSourceDependent {

  private static final javax.servlet.jsp.JspFactory _jspxFactory =
          javax.servlet.jsp.JspFactory.getDefaultFactory();

  private static java.util.Map<java.lang.String,java.lang.Long> _jspx_dependants;

  static {
    _jspx_dependants = new java.util.HashMap<java.lang.String,java.lang.Long>(2);
    _jspx_dependants.put("/WEB-INF/tld/easyui.tld", Long.valueOf(1521446129221L));
    _jspx_dependants.put("/context/mytags.jsp", Long.valueOf(1521446129245L));
  }

  private org.apache.jasper.runtime.TagHandlerPool _005fjspx_005ftagPool_005fc_005fset_0026_005fvar_005fvalue_005fnobody;
  private org.apache.jasper.runtime.TagHandlerPool _005fjspx_005ftagPool_005ft_005fbase_0026_005ftype_005fnobody;

  private javax.el.ExpressionFactory _el_expressionfactory;
  private org.apache.tomcat.InstanceManager _jsp_instancemanager;

  public java.util.Map<java.lang.String,java.lang.Long> getDependants() {
    return _jspx_dependants;
  }

  public void _jspInit() {
    _005fjspx_005ftagPool_005fc_005fset_0026_005fvar_005fvalue_005fnobody = org.apache.jasper.runtime.TagHandlerPool.getTagHandlerPool(getServletConfig());
    _005fjspx_005ftagPool_005ft_005fbase_0026_005ftype_005fnobody = org.apache.jasper.runtime.TagHandlerPool.getTagHandlerPool(getServletConfig());
    _el_expressionfactory = _jspxFactory.getJspApplicationContext(getServletConfig().getServletContext()).getExpressionFactory();
    _jsp_instancemanager = org.apache.jasper.runtime.InstanceManagerFactory.getInstanceManager(getServletConfig());
  }

  public void _jspDestroy() {
    _005fjspx_005ftagPool_005fc_005fset_0026_005fvar_005fvalue_005fnobody.release();
    _005fjspx_005ftagPool_005ft_005fbase_0026_005ftype_005fnobody.release();
  }

  public void _jspService(final javax.servlet.http.HttpServletRequest request, final javax.servlet.http.HttpServletResponse response)
        throws java.io.IOException, javax.servlet.ServletException {

    final javax.servlet.jsp.PageContext pageContext;
    javax.servlet.http.HttpSession session = null;
    final javax.servlet.ServletContext application;
    final javax.servlet.ServletConfig config;
    javax.servlet.jsp.JspWriter out = null;
    final java.lang.Object page = this;
    javax.servlet.jsp.JspWriter _jspx_out = null;
    javax.servlet.jsp.PageContext _jspx_page_context = null;


    try {
      response.setContentType("text/html; charset=UTF-8");
      pageContext = _jspxFactory.getPageContext(this, request, response,
      			null, true, 8192, true);
      _jspx_page_context = pageContext;
      application = pageContext.getServletContext();
      config = pageContext.getServletConfig();
      session = pageContext.getSession();
      out = pageContext.getOut();
      _jspx_out = out;

      out.write('\n');
      out.write("\n");
      out.write("\n");
      out.write("\n");
      out.write("\n");
 
String path = request.getContextPath();
String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path;

      out.write('\n');
      //  c:set
      org.apache.taglibs.standard.tag.rt.core.SetTag _jspx_th_c_005fset_005f0 = (org.apache.taglibs.standard.tag.rt.core.SetTag) _005fjspx_005ftagPool_005fc_005fset_0026_005fvar_005fvalue_005fnobody.get(org.apache.taglibs.standard.tag.rt.core.SetTag.class);
      _jspx_th_c_005fset_005f0.setPageContext(_jspx_page_context);
      _jspx_th_c_005fset_005f0.setParent(null);
      // /context/mytags.jsp(9,0) name = var type = java.lang.String reqTime = false required = false fragment = false deferredValue = false expectedTypeName = null deferredMethod = false methodSignature = null
      _jspx_th_c_005fset_005f0.setVar("webRoot");
      // /context/mytags.jsp(9,0) name = value type = javax.el.ValueExpression reqTime = true required = false fragment = false deferredValue = true expectedTypeName = java.lang.Object deferredMethod = false methodSignature = null
      _jspx_th_c_005fset_005f0.setValue(basePath);
      int _jspx_eval_c_005fset_005f0 = _jspx_th_c_005fset_005f0.doStartTag();
      if (_jspx_th_c_005fset_005f0.doEndTag() == javax.servlet.jsp.tagext.Tag.SKIP_PAGE) {
        _005fjspx_005ftagPool_005fc_005fset_0026_005fvar_005fvalue_005fnobody.reuse(_jspx_th_c_005fset_005f0);
        return;
      }
      _005fjspx_005ftagPool_005fc_005fset_0026_005fvar_005fvalue_005fnobody.reuse(_jspx_th_c_005fset_005f0);
      out.write('\n');
      if (_jspx_meth_t_005fbase_005f0(_jspx_page_context))
        return;
      out.write("\n");
      out.write("\n");
      out.write("<link rel=\"stylesheet\" type=\"text/css\" href=\"plug-in/ztree/css/zTreeStyle.css\">\n");
      out.write("<script type=\"text/javascript\" src=\"plug-in/ztree/js/jquery.ztree.core-3.5.min.js\"></script>\n");
      out.write("<script type=\"text/javascript\" src=\"plug-in/ztree/js/jquery.ztree.excheck-3.5.min.js\"></script>\n");
      out.write("<script type=\"text/javascript\">\n");
      out.write("    var setting = {\n");
      out.write("        check: {\n");
      out.write("            enable: false,\n");
      out.write("            chkboxType: { \"Y\": \"\", \"N\": \"\" }\n");
      out.write("        },\n");
      out.write("        data: {\n");
      out.write("            simpleData: {\n");
      out.write("                enable: true\n");
      out.write("            }\n");
      out.write("        },callback: {\n");
      out.write("            onClick:onClick\n");
      out.write("        }\n");
      out.write("    };\n");
      out.write("    \n");
      out.write("    function onClick(event, treeId, treeNode){\n");
      out.write("    \t$(\"#sitePanel\").panel({\n");
      out.write("            title:treeNode.name,\n");
      out.write("\t\t    content:'<iframe src=\"' + treeNode.site + '\" frameborder=0 height=100% width=100% ></iframe>'\n");
      out.write("\t\t});\n");
      out.write("    }\n");
      out.write("\n");
      out.write("    //加载tree\n");
      out.write("    $(function(){\n");
      out.write("    \t$.getJSON(\"");
      out.write((java.lang.String) org.apache.jasper.runtime.PageContextImpl.proprietaryEvaluate("${webRoot}", java.lang.String.class, (javax.servlet.jsp.PageContext)_jspx_page_context, null, false));
      out.write("/webpage/com/jeecg/demo/siteData.json\",function(data){ \n");
      out.write("    \t\tvar d = data;//$.parseJSON(data);\n");
      out.write("            if (d.success) {\n");
      out.write("                var dbDate = eval(d.msg);\n");
      out.write("                $.fn.zTree.init($(\"#siteSelect\"), setting, dbDate);               \n");
      out.write("                \n");
      out.write("                $(\"#sitePanel\").panel({\n");
      out.write("                \ttitle:dbDate[0].name,\n");
      out.write("\t\t\t\t    content:'<iframe src=\"' + dbDate[0].site + '\" frameborder=0 height=100% width=100% ></iframe>'\n");
      out.write("\t\t\t\t});\n");
      out.write("            }\n");
      out.write("    \t});\n");
      out.write("    });\n");
      out.write("</script>\n");
      out.write("<div class=\"easyui-layout\" fit=\"true\" style=\"width:1000px;height:600px;\">\n");
      out.write("    <div region=\"west\" split=\"true\" title=\"网址列表\" style=\"width:200px;\">\n");
      out.write("        <ul id=\"siteSelect\" class=\"ztree\" ></ul>\n");
      out.write("    </div>\n");
      out.write("    <div region=\"center\" title=\"webSite\" style=\"width:800px;\" id=\"sitePanel\" class=\"easyui-panel\">\n");
      out.write("    </div>\n");
      out.write("</div>");
    } catch (java.lang.Throwable t) {
      if (!(t instanceof javax.servlet.jsp.SkipPageException)){
        out = _jspx_out;
        if (out != null && out.getBufferSize() != 0)
          try { out.clearBuffer(); } catch (java.io.IOException e) {}
        if (_jspx_page_context != null) _jspx_page_context.handlePageException(t);
        else throw new ServletException(t);
      }
    } finally {
      _jspxFactory.releasePageContext(_jspx_page_context);
    }
  }

  private boolean _jspx_meth_t_005fbase_005f0(javax.servlet.jsp.PageContext _jspx_page_context)
          throws java.lang.Throwable {
    javax.servlet.jsp.PageContext pageContext = _jspx_page_context;
    javax.servlet.jsp.JspWriter out = _jspx_page_context.getOut();
    //  t:base
    org.jeecgframework.tag.core.easyui.BaseTag _jspx_th_t_005fbase_005f0 = (org.jeecgframework.tag.core.easyui.BaseTag) _005fjspx_005ftagPool_005ft_005fbase_0026_005ftype_005fnobody.get(org.jeecgframework.tag.core.easyui.BaseTag.class);
    _jspx_th_t_005fbase_005f0.setPageContext(_jspx_page_context);
    _jspx_th_t_005fbase_005f0.setParent(null);
    // /webpage/com/jeecg/demo/siteSelect.jsp(3,0) name = type type = null reqTime = true required = true fragment = false deferredValue = false expectedTypeName = null deferredMethod = false methodSignature = null
    _jspx_th_t_005fbase_005f0.setType("jquery,easyui,tools,DatePicker");
    int _jspx_eval_t_005fbase_005f0 = _jspx_th_t_005fbase_005f0.doStartTag();
    if (_jspx_th_t_005fbase_005f0.doEndTag() == javax.servlet.jsp.tagext.Tag.SKIP_PAGE) {
      _005fjspx_005ftagPool_005ft_005fbase_0026_005ftype_005fnobody.reuse(_jspx_th_t_005fbase_005f0);
      return true;
    }
    _005fjspx_005ftagPool_005ft_005fbase_0026_005ftype_005fnobody.reuse(_jspx_th_t_005fbase_005f0);
    return false;
  }
}
