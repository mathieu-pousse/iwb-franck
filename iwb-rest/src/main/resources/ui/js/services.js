'use strict';

/* Services */

angular.module('iwbApp.services', ['ngResource', 'iwbApp.configuration']).


   service('QueryItemService', function($resource, BASE_PATH_URL){
   	return $resource(BASE_PATH_URL+'/api/items?query=:q_string',{}, 
		   {'query': {method: 'GET', isArray: true}})
   })

   .service('ItemService', function($resource, BASE_PATH_URL){
      return $resource(BASE_PATH_URL+'/api/items/:id', {}, 
      {
         get: {method: 'GET', isArray: false},
         update: { method: 'PUT'},
         deleteItem: {method: 'DELETE'}
      }
   )})

   .service('ItemServicePost', function($resource, BASE_PATH_URL){
      return $resource(BASE_PATH_URL+'/api/items', {}, 
      {
         post: {method: 'POST'}
      }
   )})

   .service('TrashService', function($resource, BASE_PATH_URL){
      return $resource(BASE_PATH_URL+'/api/trash?acr=:p_acr', {}, 
      {
         get: {method: 'GET', isArray: false}
      }
   )})

   .service('WastesService', function($resource, BASE_PATH_URL){
   	return $resource(BASE_PATH_URL+'/api/wastes', 
		{}, 
		{ 'wastes': {method: 'GET', isArray: true}})
   })

   .service('DetailItemService', function($resource, BASE_PATH_URL){
      return $resource(BASE_PATH_URL+'/api/item/:id', {}, 
      {
         get: {method: 'GET' , isArray: false}
      }
   )})

   .service('ItemServiceTrash', function($http, BASE_PATH_URL){
      return {
         getItemObjectDetail: function(objectID){
            var promise = $http({
                  method: "GET",
                  url: BASE_PATH_URL+'/api/item/'+objectID
            });
            return promise;
         }
      }

   })

   .service('QueryItemService1', function($http, BASE_PATH_URL){
      return {
         query: function(queryString){
            var promise = $http({
                  method: "GET",
                  url: BASE_PATH_URL+'/api/items',
                  params: {query: queryString},
                  isArray: true
            });
            return promise;
         }
      }
   })


    /*
   	TransverseService
      contains common functions used by a variety of controllers
    */
   .service('CommonFunctionsService',function(){
   	return {
   		//Init the home css with custom bootsrapCSS functionalities
   		"init_home_css": function (){
   			document.getElementById("home_css").setAttribute("href","css/personalized.css");
   		},
   		//Unset bootstrapCSS functionalities afer leaving the home page
   		"unset_home_css": function (){
   			document.getElementById("home_css").setAttribute("href","");
   		}
   	}
   })


  .value('version', '0.1');