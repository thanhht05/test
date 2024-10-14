<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %> <%@ taglib
prefix="form" uri="http://www.springframework.org/tags/form" %> <%@ page
contentType="text/html;charset=UTF-8" language="java" %>
<!DOCTYPE html>
<html lang="en">
  <head>
    <meta charset="UTF-8" />
    <meta name="viewport" content="width=device-width, initial-scale=1.0" />
    <link
      href="https://cdn.jsdelivr.net/npm/bootstrap@5.3.3/dist/css/bootstrap.min.css"
      rel="stylesheet"
      integrity="sha384-QWTKZyjpPEjISv5WaRU9OFeRpok6YctnYmDr5pNlyT2bRjXh0JMhjY6hW+ALEwIH"
      crossorigin="anonymous"
    />

    <title>List User</title>
  </head>
  <body>
    <div class="container mt-5">
      <div class="d-flex justify-content-between">
        <h2>List user</h2>
        <a
          href="/admin/user/create"
          class="btn btn-warning d-flex align-items-center"
          >Create user</a
        >
      </div>
      <hr />
      <table class="table table-hover table-bordered">
        <thead>
          <tr>
            <th scope="col">ID</th>
            <th scope="col">Email</th>
            <th scope="col">Full name</th>
            <th scope="col">Action</th>
          </tr>
        </thead>
        <tbody>
          <c:forEach var="user" items="${users}">
            <tr>
              <th scope="row">${user.id}</th>
              <td>${user.email}</td>
              <td>${user.fullName}</td>
              <td>
                <a href="/admin/user/${user.id}" class="btn btn-primary"
                  >View</a
                >
                <a
                  href="/admin/user/update/${user.id}"
                  class="btn btn-secondary"
                  >Update</a
                >
                <a href="/admin/user/delete/${user.id}" class="btn btn-danger"
                  >Delete</a
                >
              </td>
            </tr>
          </c:forEach>
        </tbody>
      </table>
    </div>
  </body>
</html>
