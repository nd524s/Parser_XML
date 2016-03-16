<%--
  Created by IntelliJ IDEA.
  User: Никита
  Date: 05.01.2016
  Time: 13:01
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<!DOCTYPE html>
<html>
<head>
  <title>Start</title>
</head>
<link rel="stylesheet" href="css/bootstrap.min.css">
<body>
<div class="container">

  <div class="col-md-2 col-md-offset-5" style="margin-top: 20% ">
    <form name="parser" action="/epam" method="POST" class="form-inline">
      <select name="type" class="form-control">
        <option value="sax">SAX</option>
        <option value="stax">STAX</option>
        <option value="dom">DOM</option>
      </select>
      <input type="submit" name="button" value="parse" class="btn btn-default"/>
    </form>
  </div>
</div>

</body>
</html>
