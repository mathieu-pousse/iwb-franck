'use strict';

angular.module('iwbApp').service('WasteService', function($http, BASE_PATH_URL) {
  return {
         getAllWastes: function(){
            var promise = $http({
                  method: "GET",
                  url: BASE_PATH_URL+'/api/wastes',
                  isArray: true
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
         saveWaste: function(objectID, wasteToUpdate){
            var promise = $http({
                  method: "PUT",
                  url: BASE_PATH_URL+'/api/wastes/'+objectID,
                  data: wasteToUpdate
            });
            return promise;
         },
         createWaste: function(wasteToCreate){
            var promise = $http({
                  method: "POST",
                  url: BASE_PATH_URL+'/api/wastes',
                  data: wasteToCreate
            });
            return promise;
         },
         deleteWaste: function(objectID){
            var promise = $http({
                  method: "DELETE",
                  url: BASE_PATH_URL+'/api/wastes/'+objectID
            });
            return promise;
         }

    }
});