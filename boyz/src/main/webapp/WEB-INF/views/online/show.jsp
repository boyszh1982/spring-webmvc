<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
	<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
    <title></title>
    <!-- Favicon -->
    <link rel="shortcut icon" href="<%=request.getContextPath()%>/online/img/favicon.ico" />
    <!-- CSS -->
    <link href="<%=request.getContextPath()%>/online/css/bootstrap.css" rel="stylesheet" />
    <link href="<%=request.getContextPath()%>/online/css/font-awesome-local.css" rel="stylesheet" />
    <link href="<%=request.getContextPath()%>/online/css/style.css" rel="stylesheet" />
</head>
<body>
    <!-- Start: Preloader -->
    <div id="preloader">
        <div id="loader"></div>
    </div>
    <!-- End: Preloader -->
    <!-- Start: Main Content -->
    <div class="main-container">
		<!-- Start: Content -->
        <section class="content-section">
            <div class="container">
				<div class="row">
					<p class="lead text-muted"></p>					
					<div class="col-md-12">
						<div class="lt-input-group">
							<div class="input-group">
								<input type="text" class="form-control" id="tags" value="${demsgs }">
								<span class="input-group-btn">
									<button type="button" class="btn btn-danger">Go!</button>
								</span>
							</div>
						</div>
					</div>
				</div>
				<div class="row">
					<div class="col-md-12">
						<p class="lead text-muted"></p>
						<div class="lt-navs">
							<div class="panel-group" id="accordion">
								<c:if test="${empty onlineApps}">
									<div class="row">
										<div class="col-md-12">
											<div class="lt-jumbotrons">												
												<div class="jumbotron">
													<h1>没有关于“${demsgs }”的搜索结果。</h1>
													<p>您可以在搜索框中这样写：“2016-06 用户 流量包”。
													<br/>请重新搜索，您还可以试试“默认条件”！
													</p>
													<p><a href="<%=request.getContextPath()%>/OnlineController/show.do" class="btn btn-primary btn-lg" role="button">默认条件</a></p>
												</div>
											</div>
										</div>
									</div>
								</c:if>
								<c:forEach items="${onlineApps}" var="onlineApp" varStatus="status">
									<div class="panel panel-primary">
									   <div class="panel-heading">
										  <h4 class="panel-title">
											 <a data-toggle="collapse" data-parent="#accordion" href="#collapse${onlineApp.opDay}_${onlineApp.idx}">
											 <b>${onlineApp.menuTitle }</b>&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;<font size="-2">匹配关键字</font><font color="red" size="-2">${onlineApp.markedBy }</font>
											 </a>
										  </h4>
									   </div>
									   <c:choose>  
											<c:when test="${ status.first }">
												<div id="collapse${onlineApp.opDay}_${onlineApp.idx}" class="panel-collapse collapse in">
											</c:when>                     
											<c:otherwise>
												<div id="collapse${onlineApp.opDay}_${onlineApp.idx}" class="panel-collapse collapse">
											</c:otherwise>                    
										</c:choose> 
										  <div class="panel-body">
										  	 公告日期：${onlineApp.recordInsertTime }&nbsp;&nbsp;数据周期：${onlineApp.menuCycle }<br>
											 关系人员：${onlineApp.menuOwner}&nbsp;&nbsp;需求部门：${onlineApp.menuOrg }<br>
											 应用路径：${onlineApp.menuPath }<br>
											 相关信息：${onlineApp.menuMemo }<br>
											 附件下载： 
										  </div>
									   </div>
									</div>
								</c:forEach>
							 </div>
						</div>
					</div>
				</div>
				<!-- 暂不实现分页 
				<div class="row">
					<div class="col-md-12">	
						<div class="lt-box">
							<ul id="pagination_nm" class="pagination-nm"> 							
								<li><a>First</a></li>
								<li><a></a></li>
								<li><a>1</a></li>
								<li><a>2</a></li>
								<li><a>3</a></li>
								<li><a>4</a></li>
								<li><a>5</a></li>
								<li><a>6</a></li>
								<li><a>7</a></li>
								<li><a></a></li>
								<li><a>Last</a></li> 
							</ul>
						</div>
					</div>
				</div>
				-->
            </div>
        </section>
        <!-- End: Content -->
        <!-- Start: Footer-->
        <footer class="footer">
        </footer>
        <!-- End: Footer -->
        
    </div>
    <!-- End: Main Content -->
    
    <!-- Javascript Files -->
    
    <script type="text/javascript" src="<%=request.getContextPath()%>/online/js/jquery-1.10.2.js"></script>
    <script type="text/javascript" src="<%=request.getContextPath()%>/online/js/jquery.pagination.js"></script>
	<script type="text/javascript" src="<%=request.getContextPath()%>/online/js/bootstrap.min.js"></script>
    <script type="text/javascript" src="<%=request.getContextPath()%>/online/js/main.js"></script>
    
    <form id="form0" action="show.do" method="post" >
    	<input id="tags_hidden" name="msgs" type="hidden" />
    </form>
</body>
</html>
