<%@taglib prefix="spring" uri="http://www.springframework.org/tags" %>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>

<html>
<%@include file="header.jsp"%>
<body>
<%@include file="nav.jsp"%>
<div class="container">
    <div class="row">
        <%@include file="menu.jsp"%>
        <div class="col-md-9 well">
            <span style="text-align: center"><h3>Search for a book</h3></span>

            <c:if test="${isSearchInitiated}">
                <div class="alert alert-warning alert-dismissible" role="alert">
                    <button type="button" class="close" data-dismiss="alert" aria-label="Close"><span aria-hidden="true">&times;</span></button>
                    <c:if test="${result != null}" >
                            <strong>Result:</strong>
                            <ul>
                                <li>Id: <c:out value="${result.bookId}"/></li>
                                <li>Title: <c:out value="${result.title}"/></li>
                                <li>Price: <c:out value="${result.price}"/></li>
                                <li>Volume: <c:out value="${result.volume}"/></li>
                                <li>Publish Date: <c:out value="${result.publishDate}"/></li>
                            </ul>
                    </c:if>
                    <c:if test="${result == null}">
                    <strong>Result:</strong> Book not found with the given id
                    </c:if>
                </div>
            </c:if>

            <form class="form-horizontal" action="/searchbook" method="post">
                <div class="form-group">
                    <label for="bookId" class="col-sm-2 control-label">Id</label>
                    <div class="col-sm-10">
                        <input type="text" class="form-control" id="bookId" name="id" placeholder="Enter book id">
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
