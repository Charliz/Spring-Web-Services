/**
 * Created by Champion on 04.04.2017.
 */
'use strict';

angular.module('myApp').controller('ProductController', ['$scope', 'ProductService', function($scope, ProductService) {
    var self = this;
    self.product={id:null, brand:'',productName:'',description:'',price:'',quantity:''};
    self.products=[];

    self.submit = submit;
    self.edit = edit;
    self.remove = remove;
    self.reset = reset;


    getAllProducts();

    function getAllProducts(){
        ProductService.getAllProducts()
            .then(
                function(d) {
                    self.products = d;
                },
                function(errResponse){
                    console.error('Error while fetching Products');
                }
            );
    }

    function createProduct(product){
        UserService.createProduct(product)
            .then(
                getAllProducts,
                function(errResponse){
                    console.error('Error while creating Product');
                }
            );
    }

    function updateProduct(id, product){
        UserService.updateProduct(id, product)
            .then(
                getAllProducts,
                function(errResponse){
                    console.error('Error while updating Product');
                }
            );
    }

    function deleteProduct(id){
        UserService.deleteProduct(id)
            .then(
                getAllProducts,
                function(errResponse){
                    console.error('Error while deleting Product');
                }
            );
    }

    function submit() {
        if(self.product.id===null){
            console.log('Saving New Product', self.product);
            createProduct(self.product);
        }else{
            updateProduct(self.product, self.product.id);
            console.log('Product updated with id ', self.product.id);
        }
        reset();
    }

    function edit(id){
        console.log('id to be edited', id);
        for(var i = 0; i < self.products.length; i++){
            if(self.products[i].id === id) {
                self.product = angular.copy(self.products[i]);
                break;
            }
        }
    }

    function remove(id){
        console.log('id to be deleted', id);
        if(self.product.id === id) {//clean form if the product to be deleted is shown there.
            reset();
        }
        deleteProduct(id);
    }


    function reset(){
        self.product={id:null, brand:'',productName:'',description:'',price:'',quantity:''};
        $scope.myForm.$setPristine(); //reset Form
    }

}]);