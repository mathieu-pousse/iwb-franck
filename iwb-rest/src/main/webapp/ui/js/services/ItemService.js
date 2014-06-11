'use strict';

angular.module('iwbApp').service('ItemService', function($http, BASE_PATH_URL) {
  return {
         query: function(queryString){
            var promise = $http({
                  method: "GET",
                  url: BASE_PATH_URL+'/api/items',
                  params: {query: queryString},
                  isArray: true
            });
            return promise;
         },
         getItemObjectDetail: function(objectID){
            var promise = $http({
                  method: "GET",
                  url: BASE_PATH_URL+'/api/item/'+objectID
            });
            return promise;
        },
        getItem: function(objectID){
        	var promise = $http({
                  method: "GET",
                  url: BASE_PATH_URL+'/api/items/'+objectID
            });
            return promise;
        },
        updateItem: function(objectID, itemToUpdate){
        	var promise = $http({
                  method: "PUT",
                  url: BASE_PATH_URL+'/api/items'+objectID,
                  data: itemToUpdate
            });
            return promise;
        },
        deleteItem: function(objectID){
        	var promise = $http({
                  method: "DELETE",
                  url: BASE_PATH_URL+'/api/items'+objectID
            });
            return promise;
        },
        createItem: function(itemToCreate){
        	var promise = $http({
                  method: "POST",
                  url: BASE_PATH_URL+'/api/items',
                  data: itemToCreate
            });
            return promise;
        }
    }
});