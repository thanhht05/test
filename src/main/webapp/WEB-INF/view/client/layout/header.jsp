<%@page contentType="text/html" pageEncoding="UTF-8" %>
    <%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
        <%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>


            <div class="container-fluid fixed-top">
                <div class="container px-0">
                    <nav class="navbar navbar-light bg-white navbar-expand-xl">
                        <a href="/" class="navbar-brand">
                            <h1 class="text-primary display-6">Laptopshop</h1>
                        </a>
                        <button class="navbar-toggler py-2 px-3" type="button" data-bs-toggle="collapse"
                            data-bs-target="#navbarCollapse">
                            <span class="fa fa-bars text-primary"></span>
                        </button>
                        <div class="collapse navbar-collapse bg-white" id="navbarCollapse">
                            <div class="navbar-nav mx-auto">
                                <a href="/" class="nav-item nav-link active">Trang Chủ</a>
                                <a href="/products" class="nav-item nav-link">Sản Phẩm</a>

                            </div>
                            <div class="d-flex m-3 me-0 align-items-center">
                                <c:if test="${pageContext.request.userPrincipal.name != null}">


                                    <a href="/cart" class="position-relative me-4 my-auto">
                                        <i class="fa fa-shopping-bag fa-2x"></i>
                                        <span
                                            class="position-absolute bg-secondary rounded-circle d-flex align-items-center justify-content-center text-dark px-1"
                                            style="top: -5px; left: 15px; height: 20px; min-width: 20px;">
                                            ${sessionScope.sum}
                                        </span>
                                    </a>

                                    <div class="dropdown position-relative">
                                        <a href="#" class="my-auto dropdown-toggle" data-bs-toggle="dropdown"
                                            aria-expanded="false">
                                            <i class="fas fa-user fa-2x"></i>

                                        </a>
                                        <ul class="dropdown-menu position-absolute mt-3 p-3"
                                            style="right: 0; left: unset;">
                                            <li class="dropdown-item">
                                                <div
                                                    class="d-flex justify-content-center align-items-center flex-column">
                                                    <img class="rounded-circle" style="width: 100px; height: 100px;"
                                                        src="/images/avatar/${sessionScope.avatar}" alt="">
                                                    <p class="mt-2" style="font-weight: bold;">
                                                        <c:out value="${sessionScope.fullName}" />
                                                    </p>
                                                </div>
                                            </li>
                                            <li><a class="dropdown-item" href="#">Quản lý tài khoản</a></li>
                                            <li><a class="dropdown-item" href="/order-history">Lịch sử mua hàng</a></li>
                                            <hr style="margin: 8px 0;">
                                            <li>
                                                <form method="post" action="/logout">
                                                    <div>
                                                        <input type="hidden" name="${_csrf.parameterName}"
                                                            value="${_csrf.token}" />
                                                    </div>

                                                    <button class="dropdown-item">Đăng xuất</button>
                                                </form>
                                            </li>
                                        </ul>

                                    </div>
                                </c:if>
                                <c:if test="${pageContext.request.userPrincipal.name == null}">
                                    <a href="/login">

                                        <button type="button" class="btn btn-success me-4 p-2" style="opacity: .8;">Đăng
                                            Nhập</button>
                                    </a>
                                </c:if>

                            </div>
                        </div>
                    </nav>
                </div>
            </div>