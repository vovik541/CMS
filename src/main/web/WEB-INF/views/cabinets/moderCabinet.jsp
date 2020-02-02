<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<c:set var="language"
       value="${not empty param.language ? param.language : not empty language ? language : 'en'}"
       scope="session"/>
<fmt:setLocale value="${language}"/>
<fmt:setBundle basename="messages"/>

<html>
<head>
    <title>Moder</title>
    <link rel="stylesheet" href="https://www.w3schools.com/w3css/4/w3.css">
    <link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/4.0.0-alpha.6/css/bootstrap.min.css">
</head>
<body class="w3-light-grey">

<jsp:include page="../template/header.jsp"></jsp:include>

<h1>MODER</h1>

<div class="w3-card-4">
    <div>
        <form>
            <input type="hidden" name="command" value="moder_cabinet" />
            <input type="hidden" name="action" value="set_records_per_page" />

            <input type="hidden" name="currentPage" value="1">

            <div class="form-group col-md-4">

                <label for="records">Select records per page:</label>

                <select class="form-control" id="records" name="recordsPerPage">
                    <option value="5">5</option>
                    <option value="10" selected>10</option>
                    <option value="15">15</option>
                    <option value="20">20</option>
                    <option value="25">25</option>
                </select>

            </div>

            <button type="submit" class="btn btn-primary">Submit</button>

        </form>

        <table>
            <tr>
                <th>user ID</th>
                <th>First Name</th>
                <th>Last Name</th>
                <th>Login</th>
                <th>Email</th>
                <th>role</th>
                <th>Make Speaker</th>
            </tr>
            <c:forEach var="user" items="${sessionScope.usersForModerView}">
                <tr>
                    <td>${user.customerId}</td>
                    <td>${user.firstName}</td>
                    <td>${user.lastName}</td>
                    <td>${user.login}</td>
                    <td>${user.email}</td>
                    <td>${user.role}</td>
                </tr>
            </c:forEach>

        </table>

       <nav>
           <ul class="pagination">
               <c:if test="${requestScope.currentPage != 1}">
                   <li class="page-item">
                       <form>
                           <input type="hidden" name="command" value="moder_cabinet" />
                           <input type="hidden" name="action" value="set_records_per_page" />
                           <input type="hidden" name="currentPage" value="${requestScope.currentPage - 1}" />
                           <input type="hidden" name="recordsPerPage" value="${requestScope.recordsPerPage}" />
                           <button class="page-link">Previous</button>
                       </form>
                   </li>
               </c:if>
               <c:forEach begin="1" end="${requestScope.nOfPages}" var="i">
                   <li class="page-item">
                       <form>
                           <input type="hidden" name="command" value="moder_cabinet" />
                           <input type="hidden" name="action" value="set_records_per_page" />
                           <input type="hidden" name="currentPage" value="${i}" />
                           <input type="hidden" name="recordsPerPage" value="${requestScope.recordsPerPage}" />
                           <button class="page-link">${i}</button>
                       </form>
                   </li>
               </c:forEach>
               <c:if test="${requestScope.currentPage != requestScope.nOfPages}">
                   <li class="page-item">
                       <form>
                           <input type="hidden" name="command" value="moder_cabinet" />
                           <input type="hidden" name="action" value="set_records_per_page" />
                           <input type="hidden" name="currentPage" value="${requestScope.currentPage + 1}" />
                           <input type="hidden" name="recordsPerPage" value="${requestScope.recordsPerPage}" />
                           <button class="page-link">Next</button>
                       </form>
                   </li>
               </c:if>
           </ul>

       </nav>
        <%--<nav aria-label="Navigation for countries">
            <ul class="pagination">
                <c:if test="${currentPage != 1}">
                    <li class="page-item"><a class="page-link"
                                             href="?recordsPerPage=${recordsPerPage}&currentPage=${currentPage-1}">Previous</a>
                    </li>
                </c:if>

                <c:forEach begin="1" end="${nOfPages}" var="i">
                    <c:choose>
                        <c:when test="${currentPage eq i}">
                            <li class="page-item active"><a class="page-link">
                                    ${i} <span class="sr-only">(current)</span></a>
                            </li>
                        </c:when>
                        <c:otherwise>
                            <li class="page-item"><a class="page-link"
                                                     href="?command=moder_cabinet&action=set_records_per_page&recordsPerPage=${recordsPerPage}&currentPage=${i}">${i}</a>
                            </li>
                        </c:otherwise>
                    </c:choose>
                </c:forEach>

                <c:if test="${currentPage lt nOfPages}">
                    <li class="page-item"><a class="page-link"
                                             href="?recordsPerPage=${recordsPerPage}&currentPage=${currentPage+1}">Next</a>
                    </li>
                </c:if>
            </ul>
        </nav>--%>

    </div>


    <footer class="w3-container w3-grey w3-opacity w3-right-align w3-padding">
        <form>
            <input type="hidden" name="command" value="sign_out" />
            <button>SIGN OUT</button>
        </form>
    </footer>
</div>


<script src="https://code.jquery.com/jquery-3.1.1.slim.min.js" ></script>
<script src="https://cdnjs.cloudflare.com/ajax/libs/tether/1.4.0/js/tether.min.js" ></script>
<script src="https://maxcdn.bootstrapcdn.com/bootstrap/4.0.0-alpha.6/js/bootstrap.min.js" ></script>

</body>
</html>
