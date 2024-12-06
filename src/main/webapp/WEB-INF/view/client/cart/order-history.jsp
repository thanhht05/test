<%@page contentType="text/html" pageEncoding="UTF-8" %>
    <%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
        <%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
            <%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>

                <!DOCTYPE html>
                <html lang="en">

                <head>
                    <meta charset="utf-8">
                    <title>Laptop-Shop</title>
                    <meta content="width=device-width, initial-scale=1.0" name="viewport">
                    <meta content="" name="keywords">
                    <meta content="" name="description">

                    <!-- Google Web Fonts -->
                    <link rel="preconnect" href="https://fonts.googleapis.com">
                    <link rel="preconnect" href="https://fonts.gstatic.com" crossorigin>
                    <link
                        href="https://fonts.googleapis.com/css2?family=Open+Sans:wght@400;600&family=Raleway:wght@600;800&display=swap"
                        rel="stylesheet">

                    <!-- Icon Font Stylesheet -->
                    <link rel="stylesheet" href="https://use.fontawesome.com/releases/v5.15.4/css/all.css" />
                    <link href="https://cdn.jsdelivr.net/npm/bootstrap-icons@1.4.1/font/bootstrap-icons.css"
                        rel="stylesheet">

                    <!-- Libraries Stylesheet -->
                    <link href="/client/lib/lightbox/css/lightbox.min.css" rel="stylesheet">
                    <link href="/client/lib/owlcarousel/assets/owl.carousel.min.css" rel="stylesheet">


                    <!-- Customized Bootstrap Stylesheet -->
                    <link href="/client/css/bootstrap.min.css" rel="stylesheet">

                    <!-- Template Stylesheet -->
                    <link href="/client/css/style.css" rel="stylesheet">
                </head>
                <style>
                    table {
                        table-layout: fixed;
                        width: 120px;
                    }



                    .table-image {

                        td,
                        th {
                            vertical-align: middle;
                        }
                    }
                </style>

                <body>



                    <!-- Navbar start -->
                    <jsp:include page="../layout/header.jsp" />
                    <!-- Navbar End -->


                    <!-- Cart Page Start -->
                    <div class="container-fluid py-5">
                        <div class="container py-5">
                            <nav aria-label="breadcrumb">
                                <ol class="breadcrumb">
                                    <li class="breadcrumb-item"><a href="/">Home</a></li>
                                    <li class="breadcrumb-item active" aria-current="page">History</li>
                                </ol>
                            </nav>
                            <div class="table-responsive">
                                <table class="table table-image">
                                    <thead>
                                        <tr>
                                            <th scope="col">Sản phẩm</th>
                                            <th scope="col">Tên</th>
                                            <th scope="col">Giá cả</th>
                                            <th scope="col">Số lượng</th>
                                            <th scope="col">Thành tiền</th>
                                            <th scope="col">Trạng thái</th>
                                        </tr>
                                    </thead>
                                    <tbody>

                                        <c:forEach var="order" items="${orders}" varStatus="status">
                                            <tr>
                                                <td colspan="2">
                                                    <p> Order id: ${order.id}</p>
                                                </td>
                                                <td colspan="1">
                                                    <p>Giá cả:

                                                        <fmt:formatNumber value=" ${order.totalPrice}" type="number"
                                                            currencySymbol="$" />đ
                                                    </p>
                                                </td>
                                                <td colspan="2"></td>
                                                <td colspan="1">
                                                    <p>Trạng thái: <b>${order.status}</b> </p>
                                                </td>
                                            </tr>

                                            <c:forEach var="orderDetail" items="${order.orderDetails}"
                                                varStatus="status">
                                                <tr>
                                                    <th scope="row">
                                                        <img class="img-fluid"
                                                            src="/images/product/${orderDetail.product.image}" alt="">
                                                    </th>
                                                    <td>
                                                        <a href="/product/${orderDetail.product.id}">
                                                            ${orderDetail.product.name}
                                                        </a>
                                                    </td>
                                                    <td>
                                                        <p>
                                                            <fmt:formatNumber value=" ${orderDetail.price}"
                                                                type="number" currencySymbol="$" />đ
                                                        </p>
                                                    </td>
                                                    <td>
                                                        <p>${orderDetail.quantity}</p>
                                                    </td>
                                                    <td>
                                                        <p>
                                                            <fmt:formatNumber
                                                                value=" ${orderDetail.price * orderDetail.quantity}"
                                                                type="number" currencySymbol="$" />đ
                                                        </p>
                                                    </td>
                                                </tr>

                                            </c:forEach>

                                        </c:forEach>


                                    </tbody>
                                </table>
                            </div>

                        </div>
                    </div>
                    <!-- Cart Page End -->


                    <!-- Footer Start -->
                    <jsp:include page="../layout/footer.jsp" />
                    <!-- Footer End -->





                    <!-- Back to Top -->
                    <a href="#" class="btn btn-primary border-3 border-primary rounded-circle back-to-top"><i
                            class="fa fa-arrow-up"></i></a>


                    <!-- JavaScript Libraries -->
                    <script src="https://ajax.googleapis.com/ajax/libs/jquery/3.6.4/jquery.min.js"></script>
                    <script src="https://cdn.jsdelivr.net/npm/bootstrap@5.0.0/dist/js/bootstrap.bundle.min.js"></script>
                    <script src="client/lib/easing/easing.min.js"></script>
                    <script src="client/lib/waypoints/waypoints.min.js"></script>
                    <script src="client/lib/lightbox/js/lightbox.min.js"></script>
                    <script src="client/lib/owlcarousel/owl.carousel.min.js"></script>

                    <!-- Template Javascript -->
                    <script src="/client/js/main.js"></script>
                </body>

                </html>