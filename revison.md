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

<img src="revison.assets/image-20231101214523335.png" alt="image-20231101214523335" style="zoom:150%;" />

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

其他对象都是Map形式的键值对，这样访问：

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

vesion 是int

path parameter type info name 一般为string

dispathcher还是它  resource是url  stream是inputstream

servlet还是它  log无参

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

### HttpServletResponse接口

**扩展 ServletResponse 接口**以提供特定于 HTTP 的发送响应功能。例如，该接口拥有访问 HTTP 头和 cookie 的方法。

|          |                                   |
| -------- | --------------------------------- |
| **void** | **addCookie(Cookie cookie)**      |
| **void** | **sendRedirect(String location)** |
| String   | encodeURL(String url)             |
| String   | encodeRedirectURL(String url)     |
| boolean  | containsHeader(String name)       |

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

方法： HttpSession getSession(); 

### Tag接口 TagSupport类

- Tag接口

|         |                  |                         |
| ------- | ---------------- | ----------------------- |
| int**** | **doStartTag()** | **throws JspException** |
| int     | **doEndTag()**   | **throws JspException** |

- TagSupport类

实现Tag接口 使用时继承即可

### SimpleTag接口

|          |             |                                              |
| -------- | ----------- | -------------------------------------------- |
| **void** | **doTag()** | **throws JspException, java.io.IOException** |

### filter接口 FilterChain接口

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

- **完全路径匹配**

以 / 开始 比如：/ServletDemo1 /aaa/ServletDemo2

例如： <url-pattern>/servletDemo1<urll-pattern>

- **目录匹配**

以 / 开始，以 /*结束 比如：/* 或者 /aaa/* 或者/aaa/bbb/*

例如： <url-pattern>/aaa/*<url-pattern>

*的意思是在访问的时候只要你前面输入了/aaa/。后面的内容写任何内容都可以访问

- **扩展名匹配**

不能以 / 开始，必须以*开始 比如：*.action *.do *.jsp

例如： <url-pattern>*.abcd<urll-pattern>

*就是不管你在*的位置写任何内容只要是以.abcd结尾的都可以正常访问

**访问的优先级：**

**完全路径匹配  >  目录匹配  >  扩展名匹配**

同一个Servlet可以配置多个url路径 ，也可以配置多个servlet-mapping


### Spring Controller的相关注解，以及执行函数的返回值类型



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

Package org.springframework.web.servlet.view
**Interface AbstractCachingViewResolver.CacheFilter**

This is a functional interface and can therefore be used as the assignment target for a lambda expression or method reference.

这是一个函数接口，因此可以用作lambda表达式或方法引用的赋值目标。

| **Modifier and Type** | **Method**                                        | **Description**          |
| --------------------- | ------------------------------------------------- | ------------------------ |
| boolean               | filter(View view, String viewName, Locale locale) | 指示是否应缓存给定视图。 |

### EL表达式语法

### jsp语法

## 填空

### DispatchAction （34分）

### Struts 1 （6分）

