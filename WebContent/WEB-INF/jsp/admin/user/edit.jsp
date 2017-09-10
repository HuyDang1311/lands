<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@include file="/templates/taglib.jsp" %>    
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>      
				<div class="row">
                    <div class="col-md-12 panel-info">
                        <div class="content-box-header panel-heading">
                            <div class="panel-title ">Cập nhật nguời dùng</div>
                        </div>
                        <div class="content-box-large box-with-header">
                        <c:if test="${err ne null}">
                        <ul class="alert alert-warning">
	                        	<c:forEach items="${err}" var="objErr">
	                        		<li>${objErr}</li>
				                </c:forEach>
			             </ul>
			             </c:if>
                            <div>
                            	<form action="" method="post">
	                                <div class="row mb-10"></div>
	                                <div class="row">
	                                    <div class="col-sm-12">
	                                        <div class="form-group">
	                                            <label for="name">Tên đăng nhập</label>
	                                            <input type="text" required="required" name="username" value="${objUser.username}" disabled class="form-control" placeholder="Nhập tên đăng nhập">
	                                        </div>
	                                    </div>
	                                    <div class="col-sm-12">
	                                        <div class="form-group">
	                                            <label for="name">Họ và tên</label>
	                                            <input type="text" required="required" minlength="3" maxlength="32" class="form-control" value="${objUser.fullname}" name="fullname" placeholder="Nhập họ và tên">
	                                        </div>
	                                    </div>
	                                    <div class="col-sm-12">
	                                      	<div class="form-group">
	                                            <label>Phân cấp:</label>
	                                            <select name="rid" class="form-control" >
	                                            	<c:forEach items="${listRank}" var="objRank">
	                                            	<c:choose>
	                                            		<c:when test="${objUser.rank_id == objRank.id}">
	                                            		 	<option value="${objRank.id}" selected>${objRank.rankname}</option>
												        </c:when>
												        <c:otherwise>
												           <option value="${objRank.id}" >${objRank.rankname}</option>
												        </c:otherwise>
												   </c:choose>
												   </c:forEach>
												</select>
	                                   		 </div>
	                                   	</div>
	                                    <div class="col-sm-12">
	                                        <div class="form-group">
	                                            <label for="name">Nhập mật khẩu cũ</label>
	                                            <input type="password" class="form-control" name="oldpassword" placeholder="Nhập mật khẩu cũ">
	                                        </div>
	                                    </div>
	                                    <div class="col-sm-12">
	                                        <div class="form-group">
	                                            <label for="name">Nhập mật khẩu mới</label>
	                                            <input type="password" minlength="8" maxlength="32" class="form-control" name="password" placeholder="Nhập mật khẩu mới">
	                                        </div>
	                                    </div>
	                                    <div class="col-sm-12">
	                                        <div class="form-group">
	                                            <label for="name">Nhập lại mật khẩu mới</label>
	                                            <input type="password" minlength="8" maxlength="32" class="form-control" name="repassword" placeholder="Nhập lại mật khẩu mới">
	                                        </div>
	                                    </div>
	                                </div>
	                                <hr>
	                                <div class="row">
	                                    <div class="col-sm-12">
	                                        <input type="submit" value="Cập nhật" class="btn btn-success" />
	                                        <input type="reset" value="Nhập lại" class="btn btn-default" />
	                                    </div>
	                                </div>
                                </form>
                            </div>
                        </div>
                    </div>
                </div>