<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ include file="/templates/taglib.jsp" %>
				<div class="row">
                    <div class="col-md-12 panel-info">
                        <div class="content-box-header panel-heading">
                            <div class="panel-title ">Thêm nguời dùng</div>
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
	                                            <label class="require" for="name">Tên đăng nhập:</label>
	                                            <input type="text" class="form-control" name="username" id="username" value="${param.username}" required="required" placeholder="Nhập tên đăng nhập" />
	                                        </div>
	                                    </div>
	                                    <div class="col-sm-12">
	                                        <div class="form-group">
	                                            <label class="require" for="name">Họ và tên:</label>
	                                            <input type="text" required="required" minlength="3" maxlength="32" value="${param.fullname}" class="form-control" name="fullname" placeholder="Nhập họ và tên">
	                                        </div>
	                                    </div>
	                                     <div class="col-sm-12">
	                                      	<div class="form-group">
	                                            <label class="require">Phân cấp:</label>
	                                            <select name="rid" class="form-control" >
	                                            	<option value="0">---Cấp bậc---</option>
	                                            	<c:forEach items="${listRank}" var="objRank">
												   		<option value="${objRank.id}">${objRank.rankname}</option>
												   </c:forEach>
												</select>
	                                   		 </div>
	                                   	</div>
	                                    <div class="col-sm-12">
	                                        <div class="form-group">
	                                            <label class="require" for="name">Nhập mật khẩu:</label>
	                                            <input type="password" required="required" minlength="8" maxlength="32" class="form-control" name="password" placeholder="Nhập mật khẩu">
	                                        </div>
	                                    </div>
	                                    <div class="col-sm-12">
	                                        <div class="form-group">
	                                            <label class="require" for="name">Nhập lại mật khẩu:</label>
	                                            <input type="password" required="required" minlength="8" maxlength="32" class="form-control" name="repassword" placeholder="Nhập lại mật khẩu">
	                                        </div>
	                                    </div>
	                                </div>
	                                <hr>
	                                <div class="row">
	                                    <div class="col-sm-12">
	                                        <input type="submit" value="Thêm" class="btn btn-success" />
	                                        <input type="reset" value="Nhập lại" class="btn btn-default" />
	                                    </div>
	                                </div>
                                </form>
                            </div>
                        </div>
                    </div>
                </div>