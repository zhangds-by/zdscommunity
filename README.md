> 启动报错原因

    EmbeddedServletContainerException: Unable to start embedded Tomcat
    
    删除启动项目的servlet-api的依赖包
    
> 可登录账户

    admin/admin
    
> 通用语法

    POST请求传参：
    
        <script th:src="@{/js/common.js}"></script>
    
        data: JSON.stringify($('#userForm').serializeObject()),
        dataType: 'json',
        contentType: "application/json",