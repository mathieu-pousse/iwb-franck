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
         }
    }
});