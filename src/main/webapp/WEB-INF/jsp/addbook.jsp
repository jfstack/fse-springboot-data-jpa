<html>
    <%@include file="header.jsp"%>
    <body>
        <%@include file="nav.jsp"%>
        <div class="container">
            <div class="row">
                <%@include file="menu.jsp"%>
                <div class="col-md-9 well">
                    <span style="text-align: center"><h3>Add a book</h3></span>

                    <c:if test="${status != null}" >
                        <div class="alert alert-warning alert-dismissible" role="alert">
                            <button type="button" class="close" data-dismiss="alert" aria-label="Close"><span aria-hidden="true">&times;</span></button>
                            <strong>Well done!</strong> <c:out value="${status}" />
                        </div>
                    </c:if>

                    <form class="form-horizontal" action="/addbook" method="post">
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
                            <label for="bookId" class="col-sm-2 control-label">Id</label>
                            <div class="col-sm-10">
                                <input type="text" class="form-control" id="bookId" name="bookId" placeholder="Enter Book Id">
                            </div>
                        </div>
                        <div class="form-group">
                            <label for="bookTitle" class="col-sm-2 control-label">Title</label>
                            <div class="col-sm-10">
                                <input type="text" class="form-control" id="bookTitle" name="title" placeholder="Enter Book title">
                            </div>
                        </div>
                        <div class="form-group">
                            <label for="bookPrice" class="col-sm-2 control-label">Price</label>
                            <div class="col-sm-10">
                                <input type="text" class="form-control" id="bookPrice" name="price" placeholder="Enter Book price">
                            </div>
                        </div>
                        <div class="form-group">
                            <label for="bookVolume" class="col-sm-2 control-label">Volume</label>
                            <div class="col-sm-10">
                                <input type="text" class="form-control" id="bookVolume" name="volume" placeholder="Enter volume #">
                            </div>
                        </div>
                        <div class="form-group">
                            <label for="publishDate" class="col-sm-2 control-label">Published Date</label>
                            <div class="col-sm-10">
                                <input type="date" class="form-control" id="publishDate" name="publishDate" placeholder="Enter Book publish date">
                            </div>
                        </div>
                        <div class="form-group">
                            <div class="col-sm-offset-2 col-sm-10">
                                <button type="submit" class="btn btn-success">Add</button>
                            </div>
                        </div>
                    </form>
                </div>
            </div>
        </div>

        <%@include file="footer.jsp"%>
    </body>
</html>
