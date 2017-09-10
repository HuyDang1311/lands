<%@page import="org.springframework.security.core.context.SecurityContextHolder"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@include file="/templates/taglib.jsp" %>    
<div class="page-content container">
        <div class="row">
            <div class="col-md-4 col-md-offset-4">
                <div class="login-wrapper">
                    <div class="box">
                    	<%-- <%
                    	Object principal = SecurityContextHolder.getContext().getAuthentication().getPrincipal();
                    	out.print(principal);
                    	%> --%>
                        <div class="content-wrap">
                            <img width="100px" height="100px" class="img-circle" src="${pageContext.request.contextPath}/templates/admin/images/icon-180x180.png">
                            <h6>Đăng nhập</h6>
                            <c:if test="${param.error != null}">
                            	<p style="color:red">Sai username hoặc password</p>
                            </c:if>
							<form action="${pageContext.request.contextPath}/login" method="post">
	                            <div class="form-group">
	                                <label class="text-left pull-left" for="username">Tên đăng nhập</label>
	                                <input class="form-control" type="text" name="username" placeholder="Username">
	                            </div>
	
	                            <div class="form-group">
	                                <label class="text-left pull-left" for="password">Mật khẩu</label>
	                                <input class="form-control" type="password" name="password" placeholder="Password">
	                            </div>
	
	                            <div class="action">
	                            	<input type="submit" class="btn btn-primary signup btn-block" value="Login" />
	                            </div>
                            </form>
                        </div>
                    </div>

                    <div class="already">
                        <p>Don't have an account yet?</p>
                        <a href="javascript:void(0)">Sign Up</a>
                    </div>
                </div>
            </div>
        </div>
    </div>