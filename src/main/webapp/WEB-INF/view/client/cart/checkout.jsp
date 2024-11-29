<%@page contentType="text/html" pageEncoding="UTF-8" %>
    <%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
        <%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
            <%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>

                <!DOCTYPE html>
                <html lang="en">

                <head>
                    <meta charset="utf-8">
                    <title>Checkout</title>
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

                <body>



                    <!-- Navbar start -->
                    <jsp:include page="../layout/header.jsp" />
                    <!-- Navbar End -->


                    <!-- Cart Page Start -->
                    <div class="container-fluid py-5">
                        <div class="container py-5">
                            <div class="table-responsive">
                                <table class="table">
                                    <thead>
                                        <tr>
                                            <th scope="col">Products</th>
                                            <th scope="col">Name</th>
                                            <th scope="col">Price</th>
                                            <th scope="col">Quantity</th>
                                            <th scope="col">Total</th>

                                        </tr>
                                    </thead>
                                    <tbody>
                                        <c:forEach var="cartDetail" items="${cartDetails}" varStatus="status">

                                            <tr>
                                                <th scope="row">

                                                    <div class="d-flex align-items-center">
                                                        <img src="/images/product/${cartDetail.product.image}"
                                                            class="img-fluid me-5 rounded-circle"
                                                            style="width: 80px; height: 80px;" alt="">
                                                    </div>
                                                </th>
                                                <td>
                                                    <a href="/product/${cartDetail.product.id}">
                                                        <p class="mb-0 mt-4">${cartDetail.product.name}</p>

                                                    </a>
                                                </td>
                                                <td>
                                                    <p class="mb-0 mt-4">

                                                        <fmt:formatNumber value="${cartDetail.product.price}"
                                                            type="number" currencySymbol="$" />đ
                                                    </p>
                                                </td>
                                                <td>
                                                    <div class="input-group quantity mt-4" style="width: 100px;">

                                                        <input type="text"
                                                            class="form-control form-control-sm text-center border-0"
                                                            value="${cartDetail.quantity}"
                                                            data-cart-detail-id="${cartDetail.id}"
                                                            data-cart-detail-price="${cartDetail.price} "
                                                            data-cart-detail-index="${status.index}">


                                                    </div>
                                                </td>
                                                <td>
                                                    <p class="mb-0 mt-4" data-cart-detail-id="${cartDetail.id}">

                                                        <fmt:formatNumber
                                                            value="${cartDetail.price * cartDetail.quantity}"
                                                            type="number" currencySymbol="$" />đ
                                                    </p>
                                                </td>
                                                <td>
                                                    <form action="/delete-cart-product/${cartDetail.id}" method="post">


                                                        <div>
                                                            <input type="hidden" name="${_csrf.parameterName}"
                                                                value="${_csrf.token}" />
                                                        </div>
                                                    </form>
                                                </td>

                                            </tr>
                                        </c:forEach>

                                    </tbody>
                                </table>
                            </div>
                            <div class="mt-5">
                                <form:form class="row" action="/place-order" method="post" modelAttribute="cart">
                                    <div>
                                        <input type="hidden" name="${_csrf.parameterName}" value="${_csrf.token}" />
                                    </div>

                                    <div class="col-lg-6">

                                        <div class="mb-3">
                                            <label for="add" class="form-label">Địa chỉ</label>
                                            <input type="text" class="form-control" name="receiverAddress" />
                                        </div>
                                        <div class="mb-3">
                                            <label for="exampleInputEmail1" class="form-label">Tên người nhận</label>
                                            <input type="text" class="form-control" id="exampleInputEmail1"
                                                name="receiverName" />


                                        </div>
                                        <div class="mb-3">
                                            <label for="exampleInputPassword1" class="form-label">Số điện thoại</label>
                                            <input type="tel" class="form-control" id="exampleInputPassword1"
                                                name="receiverPhone" />
                                        </div>

                                    </div>
                                    <div class="col-sm-8 col-md-7 col-lg-6">
                                        <div class="bg-light rounded">
                                            <div class="p-4">
                                                <h1 class="display-6 mb-4">Cart <span class="fw-normal">Total</span>
                                                </h1>
                                                <div class="d-flex justify-content-between mb-4">
                                                    <h5 class="mb-0 me-4">Subtotal:</h5>
                                                    <p class="mb-0 pe-4" data-cart-total-price="${totalPrice}">
                                                        <fmt:formatNumber value="${totalPrice}" type="number"
                                                            currencySymbol="$" />đ
                                                    </p>
                                                </div>
                                                <div class="d-flex justify-content-between">
                                                    <h5 class="mb-0 me-4">Shipping</h5>
                                                    <div class="">
                                                        <p class="mb-0">Flat rate: $3.00</p>
                                                    </div>
                                                </div>
                                                <p class="mb-0 text-end">Shipping to Ukraine.</p>
                                            </div>
                                            <div
                                                class="py-4 mb-4 border-top border-bottom d-flex justify-content-between">
                                                <h5 class="mb-0 ps-4 me-4">Total</h5>
                                                <p class="mb-0 pe-4" data-cart-total-price="${totalPrice}">
                                                    <fmt:formatNumber value="${totalPrice}" type="number"
                                                        currencySymbol="$" />đ
                                                </p>
                                            </div>



                                            <button
                                                class="btn border-secondary rounded-pill px-4 py-3 text-primary text-uppercase mb-4 ms-4">Proceed
                                                Checkout</button>


                                        </div>
                                    </div>
                                </form:form>
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