<%@ page import="java.util.Random" %>
<%--
  Created by IntelliJ IDEA.
  User: luke
  Date: 2018/8/1
  Time: 9:35
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title></title>
    <link rel="stylesheet" href="js/jquery/plugins/ztree/metroStyle/metroStyle.css" type="text/css">
    <link rel="stylesheet" href="app/Style.css" type="text/css">
  <script src="js/jquery/jquery-1.12.4.js"></script>
  <script src="js/jquery/plugins/ztree/jquery.ztree.all.js"></script>
  <script src="testTree.js?r=<%=Math.random()%>"></script>
</head>
<body >
<div style="height: 300px ;overflow:scroll;">
    <ul id="treeDemo" class="ztree"></ul>

</div>

</body>
</html>
