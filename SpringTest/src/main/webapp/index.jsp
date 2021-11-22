<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>第一个springmvc</title>
</head>
<body>
    <a href="some.do">发起some.do的Get请求</a>
    <br>
    <br>
    <form action="other.do" method="post">
        <input type="submit" value="post请求">
    </form>

    <p>逐个接收请求参数</p>
    <form action="receive-property.do" method="post">
        姓名： <input type="text" name="name"> <br>
        年龄： <input type="text" name="age"> <br>
        <input type="submit" value="提交参数">
    </form>
</body>
</html>
