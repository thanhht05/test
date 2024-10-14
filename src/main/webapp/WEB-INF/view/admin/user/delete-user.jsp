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

    <title>Delete User</title>
  </head>
  <body>
    <div class="container mt-5">
      <h2>Delete user</h2>
      <hr />
      <div class="alert alert-danger" role="alert">
        Are you sure delete user?
      </div>
      <form:form
        modelAttribute="user"
        method="post"
        action="/admin/user/delete"
      >
        <div class="mb-3 d-none">
          <label for="id" class="form-label">id</label>
          <form:input
            type="text"
            class="form-control"
            id="id"
            path="id"
            value="${id}"
          />
        </div>
        <button class="btn btn-danger">Delete</button>
      </form:form>
    </div>
  </body>
</html>
