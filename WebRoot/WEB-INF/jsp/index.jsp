<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>

<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <title>挑战10秒</title>
    <link href="${pageContext.request.contextPath }/statics/images/favicon.ico" type="image/x-icon">
    <link rel="shortcut icon" href="${pageContext.request.contextPath }/statics/images/favicon.ico" type="image/x-icon">
	<link type="text/css" rel="stylesheet" href="${pageContext.request.contextPath }/statics/css/layout.css" />
</head>
<body>

<section>
    <div>
        <div>
            <span id="show">00:00:00</span>
            <span id="result"></span>
        </div>
        <div>
            <input type="button" onclick="stop();" value="开始" id="button">
        </div>
    </div>
</section>
<input type="hidden" name="token" value="${token }" id="token">
<input type="hidden" id="path" name="path" value="${pageContext.request.contextPath }"/>
</body>
<script type="text/javascript" src="${pageContext.request.contextPath }/statics/js/jquery-1.8.3.min.js"></script>
<script type="text/javascript" src="${pageContext.request.contextPath }/statics/js/main2.js"></script>
</html>