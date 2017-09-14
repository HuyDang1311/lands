<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@include file="/templates/taglib.jsp" %>
	<!DOCTYPE html>
<html xmlns="http://www.w3.org/1999/xhtml" lang="en">

<head>
		<title>CLand | VinaEnter Edu</title>
		<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
		<meta name="viewport" content="width=device-width, initial-scale=1.0">
		<meta charset="utf-8">
       <meta name="viewport" content="width=device-width, initial-scale=1">
		<!--Oswald Font -->
		<link href='http://fonts.googleapis.com/css?family=Oswald:400,300,700' rel='stylesheet' type='text/css'>
		<link rel="stylesheet" type="text/css" href="<%=request.getContextPath() %>/templates/public/css/tooltipster.css" />
		<!-- home slider-->
		<link href="<%=request.getContextPath() %>/templates/public/css/pgwslider.css" rel="stylesheet">
		<!-- Font Awesome -->
		<link rel="stylesheet" href="<%=request.getContextPath() %>/templates/public/css/font-awesome.min.css">
		<link href="<%=request.getContextPath() %>/templates/public/css/bootstrap.min.css" rel="stylesheet">
		<link href="<%=request.getContextPath() %>/templates/public/style.css" rel="stylesheet" media="screen">	
		<link href="<%=request.getContextPath() %>/templates/public/responsive.css" rel="stylesheet" media="screen">
		 <script src="https://ajax.googleapis.com/ajax/libs/jquery/3.2.1/jquery.min.js"></script>
 		 <script src="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.7/js/bootstrap.min.js"></script>		
	</head>

<body>

    <section id="header_area">
        <div class="wrapper header">
            <div class="clearfix header_top">
                <div class="clearfix logo floatleft">
                    <a href="${pageContext.request.contextPath}/">
                        <h1><span>C</span>Land</h1>
                    </a>
                </div>
                <div class="clearfix search floatright">
                    <form action="${pageContext.request.contextPath}/search" method="post">
                        <input type="text" name="search" required="required" value="${param.search}" placeholder="Search" />
                        <input type="submit" />
                    </form>
                </div>
            </div>
            <div class="header_bottom">
                <nav>
                    <ul id="nav">
                        <li><a href="${pageContext.request.contextPath}/">Trang chủ</a></li>
                        <li><a href="${pageContext.request.contextPath}/gioi-thieu">Giới thiệu</a></li>
                        <li id="dropdown"><a href="javascript:void(0)">Bất động sản</a>
                                <ul>
								<c:forEach items="${listCat}" var="objCat">
									<li><a href="${pageContext.request.contextPath}/danh-muc/${slugUtil.makeSlug(objCat.cname)}-${objCat.cid}">${objCat.cname}</a></li>
								</c:forEach>
                            </ul>
                        </li>
                        <li><a href="${pageContext.request.contextPath}/lien-he">Liên hệ</a></li>
                    </ul>
                </nav>
            </div>
        </div>
    </section>