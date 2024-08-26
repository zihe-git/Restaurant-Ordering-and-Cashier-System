<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%
	response.sendRedirect("login.html");//response对象重定向
%>

<!-- JSP（JavaServer Pages）是一种用于创建动态 Web 内容的技术，
它允许开发者将 Java 代码嵌入到 HTML 页面中，以便生成动态内容。
包含 Java 代码的 HTML 文件。它们以 .jsp 后缀结尾，并且可以包含 HTML、XML、JavaScript 和 Java 代码。
 当 Web 服务器（如Tomcat）接收到一个 JSP 页面的请求时，它将该 JSP 文件转换为对应的 Servlet。
 这个 Servlet 包含了 JSP 页面中的静态内容和动态生成的 Java 代码。
 Servlet 根据请求和 JSP 文件中的 Java 代码生成 HTML 输出，然后将其发送回客户端浏览器。
 在客户端浏览器中，用户可以看到根据请求生成的动态网页内容。
  -->
  
  <!-- 就像其他普通的网页一样，浏览器发送一个 HTTP 请求给服务器。
Web 服务器识别出这是一个对 JSP 网页的请求，并且将该请求传递给 JSP 引擎。通过使用 URL或者 .jsp 文件来完成。
JSP 引擎从磁盘中载入JSP文件，然后将它们转化为Servlet。这种转化只是简单地将所有模板文本改用 println() 语句，并且将所有的 JSP 元素转化成 Java 代码。
JSP 引擎将 Servlet 编译成可执行类，并且将原始请求传递给 Servlet 引擎。
Web 服务器的某组件将会调用 Servlet 引擎，然后载入并执行 Servlet 类。在执行过程中，Servlet 产生 HTML 格式的输出并将其内嵌于 HTTP response 中上交给 Web 服务器。
Web 服务器以静态 HTML 网页的形式将 HTTP response 返回到您的浏览器中。
最终，Web 浏览器处理 HTTP response 中动态产生的HTML网页，就好像在处理静态网页一样。 -->

<!-- 指令标签
@page定义页面依赖属性，脚本语言，error错误
@include包含其他文件
@taglib引入标签库定义 -->
