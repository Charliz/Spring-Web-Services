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


        $scope.create = function (product) {
            $http.post(baseUrl, product).success(function (product) {
                $scope.products.push(product);
                $scope.currentView = "table";
            });
        }

        $scope.update = function (product) {
            $http({
                url: baseUrl + "/" + product.prod_id,
                method: "PUT",
                data: product
            }).success(function (modifiedItem) {
                for (var i = 0; i < $scope.products.length; i++) {
                    if ($scope.products[i].prod_id == modifiedItem.prod_id) {
                        $scope.products[i] = modifiedItem;
                        break;
                    }
                }
                $scope.currentView = "table";
            });
        }

        // удаление элемента из модели
        $scope.delete = function (product) {
            // HTTP DELETE
            // отправка DELETE запроса по адресу http://localhost:8081/webstore/product/id что приводит к удалению записей на сервере
            $http({
                method: "DELETE",
                url: baseUrl + "/" + product.prod_id
            }).success(function () {
                $scope.products.splice($scope.products.indexOf(product), 1);
            });
        }

        // сохранение изменений
        $scope.saveEdit = function (product) {
            if (angular.isDefined(product.prod_id)) {
                $scope.update(product);
            } else {
                $scope.create(product);
            }
        }

        // отмена изменений и возврат в представление table
        $scope.cancelEdit = function () {
            $scope.currentProduct = {};
            $scope.currentView = "table";
        }

        $scope.sortType     = 'productName'; // set the default sort type
        $scope.sortReverse  = false;  // set the default sort order
        $scope.searchProduct   = '';     // set the default search/filter term

        $scope.showAll();

    });