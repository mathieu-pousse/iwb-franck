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



   .service('WastesService', function($resource, BASE_PATH_URL){
   	return $resource(BASE_PATH_URL+'/api/wastes', 
		{}, 
		{ 'wastes': {method: 'GET', isArray: true}})
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