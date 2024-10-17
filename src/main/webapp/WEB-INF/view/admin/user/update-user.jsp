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

    <title>Update User</title>
  </head>
  <body>
    <div class="container mt-5">
      <h2>Update user ${id}</h2>
      <hr />
      <form:form
        method="post"
        action="/admin/user/update"
        modelAttribute="user"
      >
        <div class="mb-3 d-none">
          <label for="id" class="form-label">ID </label>
          <form:input type="text" class="form-control" id="id" path="id" />
        </div>
        <div class="mb-3">
          <label for="exampleInputEmail1" class="form-label">Email </label>
          <form:input
            type="email"
            class="form-control"
            id="exampleInputEmail1"
            aria-describedby="emailHelp"
            path="email"
            disabled="true"
          />
        </div>
        <div class="mb-3">
          <label for="fullName" class="form-label">Full name</label>
          <form:input
            type="text"
            class="form-control"
            id="fullName"
            path="fullName"
          />
        </div>
        <div class="mb-3 d-none">
          <label for="password" class="form-label">Password</label>
          <form:input
            type="password"
            class="form-control"
            id="password"
            path="password"
          />
        </div>
        <div class="mb-3">
          <label for="phone" class="form-label">Phone number</label>
          <form:input
            type="tel"
            class="form-control"
            id="phone"
            path="phoneNumber"
          />
        </div>
        <div class="mb-3">
          <label for="Address" class="form-label">Address</label>
          <form:input
            type="text"
            class="form-control"
            id="Address"
            path="address"
          />
        </div>

        <button type="submit" class="btn btn-primary">Update</button>
      </form:form>
    </div>
  </body>
</html>
