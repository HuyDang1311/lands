<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@include file="/templates/taglib.jsp" %>   
				<div class="row">
                    <div class="col-md-12 panel-info">
                        <div class="content-box-header panel-heading">
                            <div class="panel-title ">Sửa danh mục</div>
                        </div>
                        <div class="content-box-large box-with-header">
                            <div>
                            	<form action="" method="post">
	                                <div class="row mb-10"></div>
	                                <div class="row">
	                                    <div class="col-sm-6">
	                                        <div class="form-group">
	                                            <label for="name">Tên danh mục</label>
	                                            <c:if test="${err != null}">
							                    	<div class="alert alert-warning">
								                	${err}
								                	</div>
								                </c:if>
	                                            <input type="text" required="required" minlength="3" class="form-control" name="cname" value="${objCat.cname}" placeholder="Nhập tên danh mục">
	                                        </div>
	                                    </div>
	                                    <div class="col-sm-6"></div>
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