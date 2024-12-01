<%@page contentType="text/html" pageEncoding="UTF-8" %>
    <%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
        <%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>

            <!DOCTYPE html>
            <html lang="en">

            <head>
                <meta charset="utf-8" />
                <meta http-equiv="X-UA-Compatible" content="IE=edge" />
                <meta name="viewport" content="width=device-width, initial-scale=1, shrink-to-fit=no" />
                <meta name="description" content="Hỏi Dân IT - Dự án laptopshop" />
                <meta name="author" content="Hỏi Dân IT" />
                <title>orderDetail</title>
                <link href="/css/styles.css" rel="stylesheet" />
                <script src="https://use.fontawesome.com/releases/v6.3.0/js/all.js" crossorigin="anonymous"></script>
            </head>
            <style>
                .table-image {

                    td,
                    th {
                        vertical-align: middle;
                    }
                }

                table {
                    table-layout: fixed;
                    width: 120px;

                }

                td {
                    width: 30px;
                }
            </style>

            <body class="sb-nav-fixed">
                <jsp:include page="../layout/header.jsp" />
                <div id="layoutSidenav">
                    <jsp:include page="../layout/sidebar.jsp" />
                    <div id="layoutSidenav_content">
                        <main>
                            <div class="container-fluid px-4">
                                <h1 class="mt-4">Manage orderDetail</h1>
                                <ol class="breadcrumb mb-4">
                                    <li class="breadcrumb-item"><a href="/admin">Dashboard</a></li>
                                    <li class="breadcrumb-item active">orderDetail</li>
                                </ol>
                                <div class="mt-5">
                                    <div class="row">
                                        <div class="col-12 mx-auto">
                                            <div class="d-flex justify-content-between">
                                                <h3>Table orderDetail</h3>
                                            </div>

                                            <hr />
                                            <table class=" table table-hover table-image">
                                                <thead>
                                                    <tr>
                                                        <th scope="col">Sản phẩm</th>
                                                        <th scope="col">Tên</th>
                                                        <th scope="col">Giá cả</th>
                                                        <th scope="col">Số lượng</th>
                                                        <th scope="col">Thành tiền</th>
                                                    </tr>
                                                </thead>

                                                <tbody>
                                                    <c:forEach var="orderDetail" items="${orderDetails}">

                                                        <tr class="pb-3">
                                                            <td>
                                                                <div>
                                                                    <img style="width: 100%;"
                                                                        src="/images/product/${orderDetail.product.image}"
                                                                        alt="">

                                                                </div>
                                                            </td>
                                                            <td><a
                                                                    href="/product/${orderDetail.product.id}">${orderDetail.product.name}</a>
                                                            </td>
                                                            <td>

                                                                <fmt:formatNumber value="${orderDetail.price}"
                                                                    type="number" currencySymbol="$" />đ
                                                            </td>
                                                            <td>${orderDetail.quantity}</td>
                                                            <td>
                                                                <fmt:formatNumber
                                                                    value="${orderDetail.quantity * orderDetail.price}"
                                                                    type="number" currencySymbol="$" />đ

                                                            </td>


                                                        </tr>

                                                    </c:forEach>

                                                </tbody>
                                            </table>
                                        </div>

                                    </div>

                                </div>
                            </div>
                        </main>
                        <jsp:include page="../layout/footer.jsp" />
                    </div>
                </div>
                <script src="https://cdn.jsdelivr.net/npm/bootstrap@5.2.3/dist/js/bootstrap.bundle.min.js"
                    crossorigin="anonymous"></script>
                <script src="/js/scripts.js"></script>

            </body>

            </html>