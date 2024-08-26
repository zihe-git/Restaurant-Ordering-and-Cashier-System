package com.ischoolbar.programmer.interceptor.admin;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import net.sf.json.JSONObject;

import org.apache.commons.lang.StringUtils;
import org.springframework.web.servlet.HandlerInterceptor;
import org.springframework.web.servlet.ModelAndView;

//拦截器
public class LoginInterceptor implements HandlerInterceptor {

	@Override
	public void afterCompletion(HttpServletRequest arg0,
			HttpServletResponse arg1, Object arg2, Exception arg3)
			throws Exception {
		// TODO Auto-generated method stub

	}

	@Override
	public void postHandle(HttpServletRequest arg0, HttpServletResponse arg1,
			Object arg2, ModelAndView arg3) throws Exception {
		// TODO Auto-generated method stub

	}
//在请求处理之前进行拦截处理，该方法检查用户是否已登录
	@Override
	public boolean preHandle(HttpServletRequest request, HttpServletResponse response,
			Object arg2) throws Exception {
		// TODO Auto-generated method stub
		String requestURI = request.getRequestURI();
		Object admin = request.getSession().getAttribute("user");
		if(admin == null){
			//��ʾδ��¼���ߵ�¼ʧЧ
			System.out.println("����"+requestURI+"������������");
			String header = request.getHeader("X-Requested-With");
			//�ж��Ƿ���ajax����
			if("XMLHttpRequest".equals(header)){
				//��ʾ��ajax����
				Map<String, String> ret = new HashMap<String, String>();
				ret.put("type", "error");
				ret.put("msg", "��¼�Ự��ʱ��δ��¼�������µ�¼!");
				response.getWriter().write(JSONObject.fromObject(ret).toString());
				return false;
			}
			//��ʾ����ͨ������ת��ֱ���ض��򵽵�¼ҳ��
			response.sendRedirect(request.getServletContext().getContextPath() + "/login.html");
			return false;
		}
	
		return true;
	}

}
