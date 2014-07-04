'use strict';

angular.module('iwbApp').service('TrashService', function($http, BASE_PATH_URL) {
  return {
         getTrashesPagination: function(NUMBER_OF_ITEMS, PAGE_NUMBER){
            var promise = $http(
            	{
                  method: "GET",
                  url: BASE_PATH_URL+'/api/trashes',
                  params: {number: NUMBER_OF_ITEMS, page: PAGE_NUMBER},
                  isArray: true
            	});
            return promise;
         },
         getTrashNumber: function(){
            var promise = $http({
                  method: "GET",
                  url: BASE_PATH_URL+'/api/trashes/count',
                  isArray: false
            });
            return promise;
         },
         getAllAcronyms: function(){
            var promise = $http({
                  method: "GET",
                  url: BASE_PATH_URL+'/api/wastes/acronyms',
                  isArray: true
            });
            return promise;
         },
         saveTrash: function(objectID, trashToUpdate){
            var promise = $http({
                  method: "PUT",
                  url: BASE_PATH_URL+'/api/trashes/'+objectID,
                  data: wasteToUpdate
            });
            return promise;
         },
         createTrash: function(wasteToCreate){
            var promise = $http({
                  method: "POST",
                  url: BASE_PATH_URL+'/api/trashes',
                  data: wasteToCreate
            });
            return promise;
         },
         deleteTrash: function(objectID){
            var promise = $http({
                  method: "DELETE",
                  url: BASE_PATH_URL+'/api/wastes/'+objectID
            });
            return promise;
         }

    }
});