<%@page contentType="text/html" pageEncoding="UTF-8" isELIgnored="false"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Staff</title>
<link href="css/bootstrap.min.css" rel="stylesheet" type="text/css" />
<script type="text/javascript" src="js/bootstrap.min.js"></script>
</head>
<body>
	<div class="container">
		<div class="row">
			<div class="col-md-12">
			
			
				<div class="panel panel-primary">
					<div class="panel-heading"><h3>Crew List</h3></div>
					<div class="panel-body">
						<form class="form-inline" action="controller" method="post">
							<div class="form-group">
								<input type="hidden" name="id" value="0"> 
								<input type="hidden" name="command" value="sel_crew">
							</div>
							<div class="form-group">
								<button type="submit" class="btn btn-primary">Insert Crew</button>
							</div>
							<div class="form-group">
								<a class="btn btn-primary" href="controller?command=user">Main Page</a>
							</div>
						</form>
					</div>
					<table class="table">
						<thead class="thead-inverse">
							<tr>
								<th>Attributes</th>
								<th>Crew</th>
								<th>Action</th>
							</tr>
						</thead>
						<tbody>
							<c:forEach var="crew" items="${requestScope.crew}">
								<tr>
									<td>
										<p>id: ${crew.id}</p>
										<p>date: <fmt:formatDate value="${crew.createDate}" pattern="dd.MM.yyyy" /></p>
										<p>user: ${crew.user.name} ${crew.user.surname}</p>
										<p>ready:	<c:choose>
       													<c:when test="${crew.ready eq 0}">no</c:when>
       													<c:otherwise>yes</c:otherwise>
      												</c:choose>
      									</p>
									</td>
									<td>
										<c:forEach var="item" items="${crew.members}">
											<p>${item.member.name}: ${item.name} ${item.surname}</p>
										</c:forEach>
										
									</td>
									<td>
										<table>
											<thead>
												<tr>
													<td>
														<form class="form-inline" action="controller" method="post">
															<input type="hidden" name="id" value="${crew.id}">
															<input type="hidden" name="command" value="sel_crew">
															<button type="submit" class="btn btn-secondary btn-xs">edit</button>
														</form>
													</td>
													<td>
														<form class="form-inline" action="controller" method="post">
															<input type="hidden" name="id" value="${crew.id}">
															<input type="hidden" name="command" value="del_crew">
															<button type="submit" class="btn btn-secondary btn-xs">delete</button>
														</form>
													</td>
												</tr>
											</thead>
										</table>
									</td>
								</tr>
							</c:forEach>
						</tbody>
					</table>
				</div>
				
				
				
			</div>
		</div>
	</div>
</body>
</html>