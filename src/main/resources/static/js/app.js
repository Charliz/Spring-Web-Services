

angular.module("AppProducts", [])
    .constant("baseUrl", "http://localhost:8081/webstore/product")
    .controller("ProductsCtrl", function ($scope, $http, baseUrl) {

        $scope.currentView = "table";

        $scope.showAll = function () {
            $http.get(baseUrl).success(function (data) {
                $scope.products = data;
            });
        }

        $scope.editOrCreate = function (product) {
            $scope.currentProduct = product ? angular.copy(product) : {};
            $scope.currentView = "edit";
        }

        // сохранение изменений
        $scope.saveEdit = function (product) {
            if (angular.isDefined(product.id)) {
                $scope.update(product);
            } else {
                $scope.create(product);
            }
        }

        $scope.create = function (product) {
            $http.post(baseUrl, product).success(function (product) {
                $scope.products.add(product);
                $scope.currentView = "table";
            });
        }

        $scope.update = function (product) {
            $http({
                url: baseUrl + product.id,
                method: "PUT",
                data: product
            }).success(function (modifiedItem) {
                for (var i = 0; i < $scope.products.length; i++) {
                    if ($scope.products[i].id == modifiedItem.id) {
                        $scope.products[i] = modifiedItem;
                        break;
                    }
                }
                $scope.currentView = "table";
            });
        }

        // отмена изменений и возврат в представление table
        $scope.cancelEdit = function () {
            $scope.currentItem = {};
            $scope.currentView = "table";
        }

        $scope.reset = function () {
            $scope.$setPristine(); //reset Form
        }

        $scope.showAll();


});
