<%@ page language="java" contentType="text/html; charset=ISO-8859-1" pageEncoding="ISO-8859-1"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<c:set var="root" value="${pageContext.request.contextPath}"/>
<html>
<head>
    <title>AngularJS $http Example</title>

    <link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.5/css/bootstrap.min.css">
    <link href="<c:url value='/static/css/app.css' />" rel="stylesheet"></link>
</head>
<body ng-app="myApp" class="ng-cloak">
<div class="generic-container" ng-controller="ProductController as ctrl">



<div class="panel panel-default">
    <!-- Default panel contents -->
    <div class="panel-heading"><span class="lead">List of Products </span></div>
    <div class="panel-body">
        <div class="table-responsive">
            <table class="table table-hover">
                <thead>
                <tr>
                    <th>Brand</th>
                    <th>Product Name</th>
                    <th>Description</th>
                    <th>Price</th>
                    <th>Quantity</th>
                    <th width="100"></th>
                    <th width="100"></th>
                </tr>
                </thead>
                <tbody>
                <tr ng-repeat="product in ctrl.products">
                    <td><span ng-bind='product.brand'></span></td>
                    <td><span ng-bind='product.productName'></span></td>
                    <td><span ng-bind='product.description'></span></td>
                    <td><span ng-bind='product.price'></span></td>
                    <td><span ng-bind='product.quantity'></span></td>
                    <td><button type="button" ng-click="ctrl.edit(product.prod_id)" class="btn btn-success custom-width">Edit</button></td>
                    <td><button type="button" ng-click="ctrl.remove(product.prod_id)" class="btn btn-danger custom-width">Remove</button></td>
                </tr>
                </tbody>
            </table>
        </div>
    </div>
</div>
</div>
    <script src="https://ajax.googleapis.com/ajax/libs/angularjs/1.4.4/angular.js"></script>
    <script src="<c:url value='/static/js/app.js' />"></script>
    <script src="<c:url value='/static/js/productService.js' />"></script>
    <script src="<c:url value='/static/js/productController.js' />"></script>
</body>
</html>