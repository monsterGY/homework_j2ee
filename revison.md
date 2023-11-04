## 选择

### JSP  9个隐含对象类型

| 对象            | 描述                                                         |
| --------------- | ------------------------------------------------------------ |
| request         | Http ServletRequest 接口的实例                               |
| response        | Http ServletResponse 接口的实例                              |
| **out**         | **JspWriter类的实例，用于把结果输出在网页上**                |
| session         | HttpSession 类的实例                                         |
| application     | ServletContext类的实例，与应用上下文有关                     |
| **config**      | **ServletConfig类的实例**                                    |
| **pageContext** | **PageContext类的实例，提供对JSP页面所有对象以及命名空间的访问** |
| page            | 类似于Java类中的this关键字                                   |
| Exception       | Exception类的对象，代表发生错误的JSP页面中对应的异常对象     |

- pageContext对象是javax.servlet.jsp.PageContext 类的实例，用来代表整个JSP页面。

- 这个对象主要用来访问页面信息，同时过滤掉大部分实现细节。

- 这个对象存储了request对象和response对象的引用。application对象，config对象，session对象，out对象可以通过访问这个对象的属性来导出（get方法）。

- pageContext对象也包含了传给JSP页面的指令信息，包括缓存信息，ErrorPage URL,页面scope等。

- PageContext类定义了一些字段，包括PAGE_SCOPE，REQUEST_SCOPE，SESSION_SCOPE， APPLICATION_SCOPE。它也提供了40余种方法，有一半继承自javax.servlet.jsp.JspContext 类。

- 其中一个重要的方法就是 removeAttribute()，它可接受一个或两个参数。比如，pageContext.removeAttribute("attrName") 移除四个scope中相关属性，但是下面这种方法只移除特定 scope 中的相关属性：

>```jsp
>pageContext.removeAttribute("attrName", PAGE_SCOPE);
>```

### pagecontext方法

| 方 法                                                        | 说 明                                                        |
| ------------------------------------------------------------ | ------------------------------------------------------------ |
| Object findAttribute (String AttributeName)                  | 按 page、request、session、application 的顺序查找指定的属性，并返回对应的属性值。如果没有相应的属性，则返回 NULL |
| Object getAttribute (String AttributeName, int Scope)        | 在指定范围内获取属性值。与 findAttribute 不同的是，getAttribute 需要指定查找范围 |
| void removeAttribute(String AttributeName, int Scope)        | 在指定范围内删除某属性                                       |
| void setAttribute(String AttributeName, Object AttributeValue, int Scope) | 在指定范围内设置属性和属性值                                 |
| Exception getException()                                     | 返回当前页的 Exception 对象                                  |
| ServletRequest getRequest()                                  | 返回当前页的 request 对象                                    |
| ServletResponse getResponse()                                | 返回当前页的 response 对象                                   |
| ServletConfig getServletConfig()                             | 返回当前页的 ServletConfig 对象                              |
| HttpSession getSession()                                     | 返回当前页的 session 对象                                    |
| Object getPage()                                             | 返回当前页的 page 对象                                       |
| ServletContext getServletContext()                           | 返回当前页的 application 对象                                |

### EL  11个隐含对象类型

| 隐含对象         | 描述                          |
| ---------------- | ----------------------------- |
| pageScope        | page 作用域                   |
| requestScope     | request 作用域                |
| sessionScope     | session 作用域                |
| applicationScope | application 作用域            |
| param            | Request 对象的参数，字符串    |
| paramValues      | Request对象的参数，字符串集合 |
| header           | HTTP 信息头，字符串           |
| headerValues     | HTTP 信息头，字符串集合       |
| initParam        | 上下文初始化参数              |
| cookie           | Cookie值                      |
| pageContext      | 当前页面的pageContext         |

pageContext：pageContext对象是JSP中pageContext对象的引用。通过pageContext对象，可以访问request对象。比如，访问request对象传入的查询字符串，就像这样：

~~~jsp
${pageContext.request.queryString}
~~~

**其他对象都是Map形式的键值对**，这样访问：

~~~jsp
${param["username"]}
${header["user-agent"]}
~~~

### servletConfig  接口

ServletConfig是每个servlet程序创建时，都对应一个ServletConfig对象

作用：

- 获取web.xml（web配置文件）servlet程序的别名，<servlet-name>
- 获取web.xml中的初始化值（<init-param>）根据param-name得到param-value
- 获取ServletContext对象 

方法：

| 方法类型        | 方法名                        |
| --------------- | ----------------------------- |
| String          | getInitParameter(String name) |
| **Enumeration** | getInitParameterNames()       |
| ServletContext  | getServletContext()           |
| String          | getServletName()              |

### servletContext接口

上下文对象，一个web工程只有一个servletContext对象实例（所以存数据都可取，但是是由打开存放数据的页面后再取才能得到，直接取会报null）

- 工程部署启动时创建，web工程停止工作时销毁

- 用于存取数据

- 使用时：

  > ServletContext servletContext = getServletContext();

- 作用:
  - 配置web.xml中配置的上下文参数<context-param>
  - 获取当前项目路径 格式为 /项目路径 
  - 获取当前项目在服务器上的绝对路径
  - K-V形式存取数据

|                                               |                                                 |
| --------------------------------------------- | ----------------------------------------------- |
| void setAttribute(String name,Object object); | 往域对象里面添加数据，添加时以key-value形式添加 |
| **Object** getAttribute(String name);         | 根据指定的key读取域对象里面的数据               |
| **Enumeration**<String> getAttributeNames()   |                                                 |
| void removeAttribute(String name);            | 根据指定的key从域对象里面删除数据               |

s结尾一般为Enumeration  paths特殊为Set

version 是int

path parameter type info name 一般为string

dispathcher还是它  resource是url  stream是inputstream

servlet还是它  log无参

**无法获得session**

### HttpServletRequest接口

**继承ServletRequest接口**，扩展了它，在HTTP协议下提供请求信息

|                  |                            |
| ---------------- | -------------------------- |
| String           | getContextPath()           |
| **Cookie[]**     | **getCookies()**           |
| long             | getDateHeader(String name) |
| String           | getMethod()                |
| String           | getRemoteUser()            |
| String           | getRequestedSessionId()    |
| **String**       | **getRequestURI()**        |
| **StringBuffer** | **getRequestURL()**        |
| **String**       | **getQueryString()**       |
| Principal        | getUserPrincipal()         |
| HttpSession      | getSession()               |
| HttpSession      | getSession(boolean create) |

**url是stringbuffer uri是string**

### HttpServletResponse接口

**扩展 ServletResponse 接口**以提供特定于 HTTP 的发送响应功能。例如，该接口拥有访问 HTTP 头和 cookie 的方法。

|          |                                      |
| -------- | ------------------------------------ |
| **void** | **addCookie(Cookie cookie)**         |
| **void** | **sendRedirect(String location)**    |
| String   | encodeURL(String url)                |
| String   | encodeRedirectURL(String url)        |
| boolean  | containsHeader(String name)          |
| void     | addHeader(String name, String value) |

### Cookie类

K-V 封装属性

Cookie(String name, String value) 

### HttpSession接口

|                |                                         |
| -------------- | --------------------------------------- |
| Object         | getAttribute(String name)               |
| Enumeration    | getAttributeNames()                     |
| ServletContext | getServletContext()                     |
| void           | removeAttribute(String name)            |
| void           | setAttribute(String name, Object value) |
| void           | setMaxInactiveInterval(int interval)    |
| int            | getMaxInactiveInterval()                |
| String         | getId()                                 |

### HttpSessionAttributeListener接口

继承EventListener接口  监听HttpSession中属性的添加、删除和修改事件

|      |                                               |
| ---- | --------------------------------------------- |
| void | attributeAdded(HttpSessionBindingEvent se)    |
| void | attributeRemoved(HttpSessionBindingEvent se)  |
| void | attributeReplaced(HttpSessionBindingEvent se) |

### HttpSessionEvent类

继承EventObject类

构造函数：HttpSessionEvent(HttpSession source) 

**方法： HttpSession getSession();** 

### Tag接口 TagSupport类

- Tag接口

|      |                  |                         |
| ---- | ---------------- | ----------------------- |
| int  | **doStartTag()** | **throws JspException** |
| int  | **doEndTag()**   | **throws JspException** |

- TagSupport类

实现Tag接口 使用时继承即可

### SimpleTag接口

|          |             |                                              |
| -------- | ----------- | -------------------------------------------- |
| **void** | **doTag()** | **throws JspException, java.io.IOException** |

### filter接口 

|          |                                                              |                                              |
| -------- | ------------------------------------------------------------ | -------------------------------------------- |
| void     | destroy()                                                    |                                              |
| void     | doFilter(ServletRequest request, ServletResponse response, FilterChain chain) | throws java.io.IOException, ServletException |
| **void** | **init(FilterConfig filterConfig)**                          |                                              |

### FilterChain接口

|          |                                                              |                                                  |
| -------- | ------------------------------------------------------------ | ------------------------------------------------ |
| **void** | **doFilter(ServletRequest request, ServletResponse response)** | **throws java.io.IOException, ServletException** |

执行顺序  检查 请求 记录日志 响应

### 注解Annotation

- **@WebServlet** 

| 属性名          | 类型           | 描述                                             |
| --------------- | -------------- | ------------------------------------------------ |
| name            | String         | 等价于<servlet-name>，不显示指定则为类的全限定名 |
| value           | String[]       | 等价于urlPatterns属性，二者不可同时使用          |
| urlPatterns     | String[]       | 指定url匹配模式 等价于<url-pattern>              |
| loadOnStartup   | int            | 指定加载顺序 等价于<load-on-startup>             |
| initParams      | WebInitParam[] | 指定一组初始化参数 等价于<init-param>            |
| asyncSuppoerted | boolean        | 声明是否支持异步操作模式                         |
| description     | String         | 描述信息                                         |
| displayName     | String         | 显示名                                           |

@WebServlet("/login.do")

- @WebListener

| **Name**  | **Type** | **Required** | **Description**              |
| --------- | -------- | ------------ | ---------------------------- |
| **value** | *String* | Optional     | Description of the listener. |

@WebListener("Session listener for the application")

- @WebFilter

| 属性名          | 描述                                                         |
| --------------- | ------------------------------------------------------------ |
| filterName      | 指定过滤器的名称，可选属性。                                 |
| urlPatterns     | 指定过滤器拦截的URL模式，可以是一个字符串数组，表示多个URL模式。 |
| value           | urlPatterns的别名属性，可以用来指定过滤器拦截的URL模式。     |
| servletNames    | 指定过滤器拦截的Servlet名称，可以是一个字符串数组，表示多个Servlet名称。 |
| dispatcherTypes | 指定过滤器的调度类型，可以是DispatcherType枚举类型的数组，默认为REQUEST |
| asyncSupported  | 指定过滤器是否支持异步请求，默认为false。                    |
| initParams      | 指定过滤器的初始化参数，以@WebInitParam注解的数组形式提供。  |

- @WebInitParam

```java
@WebServlet(
	name = "WebInitParamExample", urlPatterns = {"/hello"}
	,initParams = {
	@WebInitParam(name= "Site :", value="http://roseindia.net"),
	@WebInitParam(name= "Rose", value= "India"),
	}
)
```

### 路径匹配

Servlet的访问路径的配置

Servlet中的urlPattern的配置

<url-pattern>的配置方式

- **精确匹配**

以 / 开始 比如：/ServletDemo1 /aaa/ServletDemo2

例如： <url-pattern>/servletDemo1<urll-pattern>

- **路径匹配**

以 / 开始，以 **结束 比如： /aaa/\* 或者/aaa/bbb/\*

例如： <url-pattern>/aaa/*<url-pattern>

*的意思是在访问的时候只要你前面输入了/aaa/。后面的内容写任何内容都可以访问

- **后缀名匹配**

不能以 / 开始，必须以*开始 比如：*.action *.do *.jsp

例如： <url-pattern>*.abcd<urll-pattern>

*就是不管你在*的位置写任何内容只要是以.abcd结尾的都可以正常访问

- **缺省匹配** 

固定值/

**注意**：路径匹配的时候，以*结尾，后缀匹配的时候*必须在最前面所以，/api/*.html这是非法的路径。启动服务器的时候报错

**访问的优先级：**

**精确匹配 > 路径匹配 > 后缀名匹配 > 缺省匹配**

同一个Servlet可以配置多个url路径 ，也可以配置多个servlet-mapping

[Servlet中/和/*的区别详解_java_脚本之家 (jb51.net)](https://www.jb51.net/article/216853.htm)


### Spring Controller的相关注解，以及执行函数的返回值类型

后端控制器支持的常见参数类型列表

@PathVariable
@RequestParam   常用

>public String add(@RequestParam("v1") String v1, @RequestParam("v2") String v2)
>public String add(@RequestParam Map<String, Object> params)

@RequestHeader
@CookieValue
@RequestBody
@RequestPart

> Controller 处理函数可以返回多种数据类型，根据具体需求选择适合的返回类型：
>
> 1. ModelAndView：返回一个 ModelAndView 对象，包含模型数据和视图信息。
> 2. Model：返回一个 Model 对象，用于存储模型数据，在视图中使用。
> 3. ModelMap：类似于 Model，也用于存储模型数据。
> 4. Map：返回一个 Map 对象，存储模型数据，可以在视图中使用。
> 5. View：返回一个 View 对象，指示要渲染的视图。
> 6. String：返回一个字符串，可以是视图名称或重定向的 URL。
> 7. void：可以在处理函数中直接操作 HttpServletResponse 对象，通常用于直接输出数据或进行重定向等操作。
> 8. @ResponseBody Object：通过注解 @ResponseBody 返回一个对象，会自动序列化为 JSON 或 XML 格式的数据。
>
> 根据具体需要和框架要求，选择合适的返回类型进行数据处理。
>
> chatgpt↑
>
> [Controller类中方法返回值详解_controller不同的返回值-CSDN博客](https://blog.csdn.net/qq_34598667/article/details/84110353)

### org.springframework.web.servlet.view接口中的函数

- public default String getContentType();
- public void render(@Nullable Map<String,?> model,
              HttpServletRequest request,
              HttpServletResponse response) throws Exception

### EL表达式语法

### jsp语法

<%  java代码  %>

<%@  指令    %>

<%!  声明变量or方法      %>

<%=   表达式%>

<%--  注释    %>

## 填空

### DispatchAction （34分）

C:\Users\lenovo\Desktop\J2EE\期末填空\DispatchAction.java

C:\Users\lenovo\Desktop\J2EE\期末填空\LookupDispatchAction.java

### Struts 1 （6分）

猜数游戏

## 拓展

### servlet接口

| void          | destroy()                                            |                                                  |
| ------------- | ---------------------------------------------------- | ------------------------------------------------ |
| servletconfig | getServletConig()                                    |                                                  |
| string        | getServletInfo                                       |                                                  |
| void          | init()                                               |                                                  |
| **void**      | **service(ServletRequest req, ServletResponse res)** | **throws ServletException, java.io.IOException** |

### Include指令

JSP可以通过include指令来包含其他文件。被包含的文件可以是**JSP文件、HTML文件或文本文件**。包含的文件就好像是该JSP文件的一部分，会被同时编译执行。

### servlet生命周期

加载类 实例化 初始化 请求处理  销毁

### J2EE框架struts  struts2和MVC有哪些共同点和不同点

- Struts2 的核心是基于一个Filter即StrutsPreparedAndExcuteFilter
  SpringMvc的核心是基于一个Servlet即DispatcherServlet(前端控制器)
- Struts2是基于类开发的，传递的参数是通过类的属性传递
  SpringMvc是基于类中的方法开发的，也就是一个url对应一个方法，
  传递参数是传到方法的形参上面，所以既可以是单例模式也可以是多例模式

