<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<%@include file="/common/taglib.jsp"%>
<c:url var="bookURL" value="/admin/book/list" />
<c:url var="bookAPI" value="/api/book" />
<c:url var="editBookURL" value="/admin/book/edit" />

<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<title>Insert title here</title>
</head>
<body>
	<c:if test="${not empty message}">
		<div class="alert alert-${alert}">
			<strong>${message}</strong>
		</div>
	</c:if>
	
	<form:form class="form-horizontal" role="form" id="formSubmit" modelAttribute="model">
		<div class="form-group">
			<label class="col-sm-3 control-label"
				for="form-field-1"><b>Title</b></label>
			<div class="col-sm-9">
				<form:input path="title" cssClass="col-xs-10 col-sm-5" id="title" />
			</div>
		</div>
		
		
		<div class="form-group">
			<label class="col-sm-3 control-label" for="form-field-1"><b>Category</b></label>
			<div class="col-sm-9">
				<form:select path="categoryName" id="categoryName">
					<form:option value="" label="-- Category --" />
					<form:options items="${categories}" itemValue="name" itemLabel="name"/>
				</form:select>
			</div>
		</div>
		
		<div class="form-group">
			<label class="col-sm-3 control-label no-padding-right"
				for="form-field-1"><b>Image File</b></label>

			<div class="col-sm-9">
				<form:input path="imageFile" cssClass="col-xs-10 col-sm-5" id="imageFile" type="file"/>
				<img id="blah" style="display:none" />
			</div>
		</div>

		<div class="form-group">
			<label for="shortDescription" class="col-sm-3 control-label no-padding-right"><b>Short Description</b></label>
				<div class="col-sm-9">
					<form:textarea path="shortDescription" rows="5" cols="10" cssClass="form-control" id="shortDescription" />
				</div>
		</div>
		
		<div class="form-group">
			<label for="price" class="col-sm-3 control-label no-padding-right"><b>Price $</b></label>
			<div class="col-sm-9">
				<form:input path="price" cssClass="col-xs-10 col-sm-5" id="price"/>
			</div>
		</div>

		<div class="form-group">
			<label for="discount" class="col-sm-3 control-label no-padding-right"><b>Discount %</b></label>
			<div class="col-sm-9">
				<form:input path="discount" cssClass="col-xs-10 col-sm-5" id="discount"/> 
			</div>
		</div>

		<form:hidden path="id" id="bookId" />
		
		<div class="clearfix form-actions">
			<div class="col-md-offset-3 col-md-9">
				<c:if test="${not empty model.id}">
					<button class="btn btn-info" type="button" id="btnAddOrUpdateBook">
						<i class="ace-icon fa fa-check bigger-110"></i> Update book
					</button>
				</c:if>
				
				<c:if test="${empty model.id}">
					<button class="btn btn-info" type="button" id="btnAddOrUpdateBook">
						<i class="ace-icon fa fa-check bigger-110"></i> Insert book
					</button>
				</c:if>
				&nbsp; &nbsp; &nbsp;
				<button class="btn" type="reset">
					<i class="ace-icon fa fa-undo bigger-110"></i> Cancel
				</button>
			</div>
		</div>
	</form:form>
	
	<script  type="text/javascript"><!--Nho phai khai bao jquey len trc-->
		/*Get image*/
		var dataImage ;		
		$("#imageFile").change(function(e){
			dataImage = e.target.files[0];
			$("#blah").css("display", "block");
			$("#blah").css("width", "200px");
			$("#blah").css("height", "200px");
			readURL(this);
		})
		
		/*When submit form consist image, use FormData, append*/
		$('#btnAddOrUpdateBook').click(function(e) {
			e.preventDefault(); // Bat buoc phai co, neu ko se submit vo url hien tai
			var data = new FormData($('#formSubmit')[0]);
			
			data.append('imageFile',dataImage);
			
			var id = $('#bookId').val();
			if (id == ""){
				addBook(data);
			}
			else {
				updateBook(data);
			}
			/*
			let data = new FormData();
			data.append('imageFile',dataImage);
			data.append('title',$("#title").val());
			data.append('categoryName',$("#categoryName").val());
			data.append('shortDescription',$("#shortDescription").val());
			data.append('price',$("#price").val());
			data.append('discount',$("#discount").val()); 
			*/
		});

		
		function addBook(data) {
			$.ajax({
		        enctype: 'multipart/form-data',
		        url: "${bookAPI}",
		        type: "POST",
		        data: data,
		        processData: false, //prevent jQuery from automatically transforming the data into a query string
		        contentType: false,
		        cache: false,
		        timeout: 600000,
		        success : function(result) {
					window.location.href = "${editBookURL}?id="+ result.id + "&message=insert_success";},
				error : function(error) {
					window.location.href = "${bookURL}?message=error_system";
				}
			});
		}
		
		function updateBook(data) {
			$.ajax({
				url : '${bookAPI}',
				type : 'PUT',
				contentType : 'application/json',
				data : JSON.stringify(data),
				dataType : 'json',
				success : function(result) {
					window.location.href = "${editBookURL}?id=" + result.id
							+ "&message=update_success";
				},
				error : function(error) {
					window.location.href = "${editBookURL}?id=" + result.id
							+ "&message=error_system";
				}
			});
		}
		
		/*Show image input*/
		function readURL(input) {
			  if (input.files && input.files[0]) {
			    var reader = new FileReader();
			    
			    reader.onload = function(e) {
			      $('#blah').attr('src', e.target.result);
			    }
			    
			    reader.readAsDataURL(input.files[0]); // convert to base64 string
			  }
		}
	</script>
</body>
</html>