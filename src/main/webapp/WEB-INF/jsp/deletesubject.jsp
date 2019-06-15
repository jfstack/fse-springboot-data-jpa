<html>
<%@include file="header.jsp"%>
<body>
<%@include file="nav.jsp"%>
<div class="container">
    <div class="row">
        <%@include file="menu.jsp"%>
        <div class="col-md-9 well">
            <span style="text-align: center"><h3>Delete a subject</h3></span>

            <c:if test="${status != null}" >
                <div class="alert alert-warning alert-dismissible" role="alert">
                    <button type="button" class="close" data-dismiss="alert" aria-label="Close"><span aria-hidden="true">&times;</span></button>
                    <strong>Well done!</strong> <c:out value="${status}" />
                </div>
            </c:if>

            <form class="form-horizontal" action="/deletesubject" method="post">
                <c:if test="${errors.size() > 0}">
                    <div class="alert alert-warning alert-dismissible" role="alert">
                        <button type="button" class="close" data-dismiss="alert" aria-label="Close"><span aria-hidden="true">&times;</span></button>
                        <strong>Error:</strong>
                        <ul>
                            <c:forEach var="error" items="${errors}">
                                <li><c:out value="${error}" /></li>
                            </c:forEach>
                        </ul>
                    </div>
                </c:if>
                <div class="form-group">
                    <label for="subjectId" class="col-sm-2 control-label">Id</label>
                    <div class="col-sm-10">
                        <input type="text" class="form-control" id="subjectId" name="id" placeholder="Enter subject id to delete">
                    </div>
                </div>
                <div class="form-group">
                    <div class="col-sm-offset-2 col-sm-10">
                        <button type="submit" class="btn btn-danger">Delete</button>
                    </div>
                </div>
            </form>
        </div>
    </div>
</div>
<%@include file="footer.jsp"%>
</body>
</html>
