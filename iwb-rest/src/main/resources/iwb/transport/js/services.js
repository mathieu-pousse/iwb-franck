'use strict';

/* Services */

angular.module('iwbApp.services', ['ngResource']).



   service('AngularIssues', function($resource){
   	return $resource('/api/trashes/531f0f25e4b0f61504f12b1a', {}, {'query': {method: 'GET', isArray: false}})
   })


   .service('QueryItemService', function($resource){
   	return $resource('/api/items?query=:q_string',{}, 
		   {'query': {method: 'GET', isArray: true}})
   })


   .service('ItemService', function($resource){
   	return $resource('/api/items/:id', {}, 
		{
			get: {method: 'GET', isArray: false},
			update: { method: 'PUT'}
		}

	)})

   	.service('WastesService', function($resource){
   	return $resource('/api/wastes', 
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
   		},
   		//Set the image base path url
   		"set_img": function(item, BASE_PATH_IMG){
   			var name_img = item.image;
			if(name_img && name_img.length >0){
				item.image = BASE_PATH_IMG+name_img;
			}else{
				item.image = BASE_PATH_IMG+'imageNotFound.jpg';
			}
	   	},
	   	//Unset the image base path url before commit changes
	   	"unset_img": function(item){
	   		var fullpath = item.image;
   			var name_img = fullpath.replace(/^.*[\\\/]/, '');
			if(name_img !== 'imageNotFound.jpg'){
				item.image = name_img;
			}else{
				item.image = null;
			}
	   	}
   	}
   })

    


  .value('version', '0.1');