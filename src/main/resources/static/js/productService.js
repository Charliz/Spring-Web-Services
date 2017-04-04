/**
 * Created by Champion on 04.04.2017.
 */
'use strict';

angular.module('myApp').factory('ProductService', ['$http', '$q', function($http, $q){

    var REST_SERVICE_URI = 'http://localhost:8081/webstore/product/';

    var factory = {
        getAllProducts: getAllProducts,
        createProduct: createProduct,
        updateProduct:updateProduct,
        deleteProduct:deleteProduct
    };

    return factory;

    function getAllProducts() {
        var deferred = $q.defer();
        $http.get(REST_SERVICE_URI)
            .then(
                function (response) {
                    deferred.resolve(response.data);
                },
                function(errResponse){
                    console.error('Error while fetching Products');
                    deferred.reject(errResponse);
                }
            );
        return deferred.promise;
    }

    function createProduct(product) {
        var deferred = $q.defer();
        $http.post(REST_SERVICE_URI, product)
            .then(
                function (response) {
                    deferred.resolve(response.data);
                },
                function(errResponse){
                    console.error('Error while creating Product');
                    deferred.reject(errResponse);
                }
            );
        return deferred.promise;
    }


    function updateProduct(id, product) {
        var deferred = $q.defer();
        $http.put(REST_SERVICE_URI+id, product)
            .then(
                function (response) {
                    deferred.resolve(response.data);
                },
                function(errResponse){
                    console.error('Error while updating Product');
                    deferred.reject(errResponse);
                }
            );
        return deferred.promise;
    }

    function deleteProduct(id) {
        var deferred = $q.defer();
        $http.delete(REST_SERVICE_URI+id)
            .then(
                function (response) {
                    deferred.resolve(response.data);
                },
                function(errResponse){
                    console.error('Error while deleting Product');
                    deferred.reject(errResponse);
                }
            );
        return deferred.promise;
    }

}]);