<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<%@include file="/common/taglib.jsp" %>	
<c:url var="userAPI" value="/api/find-all-user"/>
<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<title>List user</title>
</head>
<body>
<!--  
	<div class="container-fluid">

	
		<ol class="breadcrumb">
			<li class="breadcrumb-item">Setting</li>
			<li class="breadcrumb-item active">List user</li>
		</ol>

		<div class="card mb-3">
			<div class="card-header">
				<i class="fas fa-table"></i> Data Table
			</div>
			<div class="card-body">
				<div class="table-responsive">
					<table class="table table-bordered" id="dataTable" width="100%" cellspacing="0">
						<thead>
							<tr>
								<th>Id</th>
								<th>User name</th>
								<th>Email</th>
								<th>Address</th>
								<th>Phone</th>
								<th>Create Date</th>
							</tr>
						</thead>
						<tfoot>
							<tr>
								<th>Id</th>
								<th>User name</th>
								<th>Email</th>
								<th>Address</th>
								<th>Phone</th>
								<th>Create Date</th>
							</tr>
						</tfoot>
						<tbody>
							<c:forEach var="item" items="${model.listResult}">
								<tr>
									<td>${item.id}</td>
									<td>${item.userName}</td>
									<td>${item.email}</td>
									<td>${item.address}</td>
									<td>${item.phone}</td>
									<td>${item.createdDate}</td>
								</tr>
							</c:forEach>
						</tbody>
					</table>
				</div>
			</div>
			<div class="card-footer small text-muted">Updated yesterday at 11:59 PM</div>
		</div>
	</div>
-->	

<div id = "header">
	UserName: <input id="search-userName" type="text">
	Email: <input id="search-email" type="text">
	Address: <input id="search-address" type="text">
	Phone: <input id="search-phone" type="text">
	<br/><br/>
	
	<div class="form-check-inline">
      <label class="form-check-label" for="check1">
        <input type="checkbox" class="form-check-input" id="check1" value=1 >Admin
      </label>
    </div>
    
    <div class="form-check-inline">
      <label class="form-check-label" for="check2">
        <input type="checkbox" class="form-check-input" id="check2" value=2>User
      </label>
    </div>
	<button id= "search">Search</button>
	
	
</div>
	
<!-- show list user -->
<div class="container">            
  <table class="table" id="list-user">
 	
  </table>
</div>	

<!-- Pagination -->
<ul class = "pagination"></ul>

<script>
var data = {};
$( document ).ready(function() {
	getAllUser(data);
});

function getAllUser(data){
	$.ajax({
		url: '${userAPI}',
		type: 'POST',
        dataType: 'json',
        contentType:'application/json',
        data: JSON.stringify(data), // convert js to json
        success: function(res){
        	var result = '';
        	result += '<tr>';
        	result += '<th>UserName</th>';
        	result += '<th>Email</th>';
        	result += '<th>Address</th>';
        	result += '<th>Phone</th>';
        	result += '<th>Created Date</th>';
        	result += '</tr>';
        	$.each( res.listResult, function( key, value ) {
        		result += '<tr>';
          		result += '<td>' + value.userName + '</td>';
          		result += '<td>' + value.email + '</td>';  
          		result += '<td>' + value.address + '</td>';
          		result += '<td>' + value.phone + '</td>';
          		result += '<td>' + value.createdDate + '</td>';
          	    result += '</tr>';
        	});
        	$("#list-user").html(result);
        	pagination(res.totalPage,res.page);
        },
        error: function(res){
        	console.log(res);
        }
	});
}

function pagination(totalPage,indexPage){
	var result = '<li class="page-item"><a class="page-link" href ="" onclick = "clickBackPage(event,' + indexPage + ')">back</a></li>';
	
	for(var i= 1; i <= totalPage ; i++){
		if(i == indexPage){
			result += '<li class="page-item active"><a class="page-link" href=""  onclick = "clickChangePage(event,' + i + ')">' + i + '</a></li>';
		}else{
			result += '<li class="page-item"><a class="page-link" href="" onclick = "clickChangePage(event,' + i + ')">' + i + '</a></li>';
		}
	}
	
	result += '<li class="page-item"><a class="page-link" href="" onclick = "clickNextPage(event,' + indexPage + ', ' + totalPage + ')">next</a></li>'
	$(".pagination").html(result);
}

/*Done*/
function clickChangePage(event,page){
	event.preventDefault();
	data["page"] = page;
	getAllBook(data);
} 

/*Done*/
function clickBackPage(event,indexPage){
	event.preventDefault();
	if(indexPage > 1){
		data["page"] = indexPage - 1;
		getAllBook(data);
	}
}

/*Done*/
function clickNextPage(event, indexPage,totalPage){
	event.preventDefault();
	if(indexPage < totalPage){
		data["page"] = indexPage + 1;
		getAllBook(data);
	}
}

$('#search').click(function(event){
	event.preventDefault();
	data["userName"] = $('#search-userName').val();
	data["email"] = $('#search-email').val();
	data["address"] = $('#search-address').val();
	data["phone"] = $('#search-phone').val();
	
	getAllUser(data);
});
</script>
</body>
</html>