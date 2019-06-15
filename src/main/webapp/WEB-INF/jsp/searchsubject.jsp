<html>
<%@include file="header.jsp"%>
<body>
<%@include file="nav.jsp"%>
<div class="container">
    <div class="row">
        <%@include file="menu.jsp"%>
        <div class="col-md-9 well">
            <span style="text-align: center"><h3>Search for a subject</h3></span>

            <c:if test="${isSearchInitiated}">
                <div class="alert alert-warning alert-dismissible" role="alert">
                    <button type="button" class="close" data-dismiss="alert" aria-label="Close"><span aria-hidden="true">&times;</span></button>
                    <c:if test="${result != null}" >
                        <strong>Result:</strong>
                        <ul style="text-decoration: none">
                            <li>Id: <c:out value="${result.subjectId}"/></li>
                            <li>Title: <c:out value="${result.subtitle}"/></li>
                            <li>Hours: <c:out value="${result.durationInHrs}"/></li>
                            <li>Books: <c:forEach items="${result.books}" var="book">
                                <ol>Title: <c:out value="${book.title}" /></ol>
                            </c:forEach> </li>
                        </ul>
                    </c:if>
                    <c:if test="${result == null}">
                        <strong>Result:</strong>No subject found with given id
                    </c:if>
                </div>
            </c:if>

            <form class="form-horizontal" action="/searchsubject" method="post">
                <div class="form-group">
                    <label for="subjectId" class="col-sm-2 control-label">Id</label>
                    <div class="col-sm-10">
                        <input type="text" class="form-control" id="subjectId" name="id" placeholder="Enter subject id">
                    </div>
                </div>
                <div class="form-group">
                    <div class="col-sm-offset-2 col-sm-10">
                        <button type="submit" class="btn btn-primary">Search</button>
                    </div>
                </div>
            </form>
        </div>
    </div>
</div>
<%@include file="footer.jsp"%>
</body>
</html>
