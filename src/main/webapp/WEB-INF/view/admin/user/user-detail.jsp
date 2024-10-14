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

    <title>User ${user.id}</title>
  </head>
  <body>
    <div class="container mt-5">
      <div>
        <h2>User Detail ${user.id}</h2>
      </div>
      <hr />
      <div class="card mx-auto" style="width: 50%">
        <div class="card-header">Information</div>
        <ul class="list-group list-group-flush">
          <li class="list-group-item">ID: ${user.id}</li>
          <li class="list-group-item">Email: ${user.email}</li>
          <li class="list-group-item">Password: ${user.password}</li>
          <li class="list-group-item">Full name: ${user.fullName}</li>
          <li class="list-group-item">Phone number: ${user.phoneNumber}</li>
          <li class="list-group-item">Address: ${user.address}</li>
        </ul>
      </div>
    </div>
  </body>
</html>
