<%@ page contentType="text/html;charset=UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<title>SSE-微信接口</title>
</head>

<body>
<div align="center">
<h1>微信接口已启动......</h1>
<table border="1">
<tr>
    <th>缓存时间</th>
    <th>token</th>
    <th>ticket</th>
  </tr>
  <tr>
    <td>${cacheTime}</td>
    <td>${token}</td>
    <td>${ticket}</td>
  </tr>
</table>
<h3>当前服务器时间：${nowTime}</h3>
</div>
</body>
</html>