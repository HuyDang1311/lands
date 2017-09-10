<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@include file="/templates/taglib.jsp" %>
<tiles:insertAttribute name="header"></tiles:insertAttribute>
    <div class="page-content">
    	<div class="row">
		  <div class="col-md-2">
		  	<div class="sidebar content-box" style="display: block;">
				<tiles:insertAttribute name="left-bar"></tiles:insertAttribute>
             </div>
		  </div>
		  <div class="col-md-10">
				<tiles:insertAttribute name="body"></tiles:insertAttribute>
		  </div>
		</div>
    </div>
   <tiles:insertAttribute name="footer"></tiles:insertAttribute>
