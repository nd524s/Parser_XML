<%--
  Created by IntelliJ IDEA.
  User: Никита
  Date: 04.01.2016
  Time: 11:52
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html>
<head>
  <title>Result</title>
  <link rel="stylesheet" href="css/bootstrap.min.css">
</head>
<body>
<h1 align="center">${name} parser</h1>
<div class="container">
  <div class="row">
    <div class="col-md-2">
      <a class="btn btn-default" href="index.jsp">Back</a>
    </div>
  </div>

  <p><h3 class="text-center"> Greeting Cards</h3></p>

  <table class="table">
    <tr><th>Year</th><th>Thema</th><th>Sending</th><th>Country</th><th>Valuable</th>
      <th>Author name</th><th>Author birthDate</th><th>Author birthPlace</th></tr>
    <c:forEach var="temp" items="${greetingCard}">
      <tr>
        <td><c:out value="${temp.year}" /></td>
        <td><c:out value="${temp.thema}" /></td>
        <td><c:out value="${temp.sending}" /></td>
        <td><c:out value="${temp.country}" /></td>
        <td><c:out value="${temp.valuable}" /></td>
        <td><c:out value="${temp.author.name}" /></td>
        <td><c:out value="${temp.author.birthDate}" /></td>
        <td><c:out value="${temp.author.birthPlace}" /></td>
      </tr>
    </c:forEach>
  </table>

  <p><h3 class="text-center">Music Cards</h3></p>

  <table class="table">
    <tr><th>Year</th><th>Thema</th><th>Sending</th><th>Country</th><th>Valuable</th>
      <th>Author name</th><th>Author birthDate</th><th>Author birthPlace</th><th>Song</th></tr>
    <c:forEach var="temp" items="${musicCard}">
      <tr>
        <td><c:out value="${temp.year}" /></td>
        <td><c:out value="${temp.thema}" /></td>
        <td><c:out value="${temp.sending}" /></td>
        <td><c:out value="${temp.country}" /></td>
        <td><c:out value="${temp.valuable}" /></td>
        <td><c:out value="${temp.author.name}" /></td>
        <td><c:out value="${temp.author.birthDate}" /></td>
        <td><c:out value="${temp.author.birthPlace}" /></td>
        <td><c:out value="${temp.song}" /></td>
      </tr>
    </c:forEach>
  </table>

  <p><h3 class="text-center">Promotional Cards</h3></p>
  <table class="table">
    <tr><th>Year</th><th>Thema</th><th>Sending</th><th>Country</th><th>Valuable</th>
      <th>Company name</th><th>Advertising phone</th></tr>
    <c:forEach var="temp" items="${promotionalCard}">
      <tr>
        <td><c:out value="${temp.year}" /></td>
        <td><c:out value="${temp.thema}" /></td>
        <td><c:out value="${temp.sending}" /></td>
        <td><c:out value="${temp.country}" /></td>
        <td><c:out value="${temp.valuable}" /></td>
        <td><c:out value="${temp.companyName}" /></td>
        <td><c:out value="${temp.advertisingPhone}" /></td>
      </tr>
    </c:forEach>
  </table>
</div>

</body>
</html>