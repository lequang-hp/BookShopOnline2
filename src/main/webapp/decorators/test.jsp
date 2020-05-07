<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<%@ include file="/common/taglib.jsp" %>      
<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<title>Insert title here</title>
<link rel="stylesheet" href="https://stackpath.bootstrapcdn.com/bootstrap/4.1.2/css/bootstrap.min.css">
<script src="https://code.jquery.com/jquery-3.3.1.min.js"></script>
<script src="https://stackpath.bootstrapcdn.com/bootstrap/4.1.2/js/bootstrap.min.js"></script>
<script src="<c:url value='/template/paging/jquery.twbsPagination.js' />"></script>
</head>
<body>
<ul class="pagination" id="pagination"></ul>

<script type="text/javascript">
        $(function () {
            window.pagObj = $('#pagination').twbsPagination({
                totalPages: 10,
                visiblePages: 3,
                onPageClick: function (event, page) {
                    console.info(page + ' (from options)');
                }
            }).on('page', function (event, page) {
                console.info(page + ' (from event listening)');
            });
        });
    </script>
</body>
</html>