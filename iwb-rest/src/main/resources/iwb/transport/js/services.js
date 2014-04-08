'use strict';

/* Services */

angular.module('iwbApp.services', ['ngResource']).



   service('AngularIssues', function($resource){
   	return $resource('/api/trashes/531f0f25e4b0f61504f12b1a', {}, {'query': {method: 'GET', isArray: false}})
   })


   .service('SearchItemService', function($resource){
   	return $resource('/api/items?query=:q_string', 
		{}, 
		{'query': {method: 'GET', isArray: true}})
   })


   .service('SearchItemWithIdService', function($resource){
   	return $resource('/api/items/:id', 
		{}, 
		{method: 'GET', isArray: false})
   })

   .service('UpdateItemService', function($resource){
		return $resource('/api/items/:id', {}, 
			{ 
				update: { method: 'PUT'}
			}
	)})

   	.service('WastesService', function($resource){
   	return $resource('/api/wastes', 
		{}, 
		{ 'wastes': {method: 'GET', isArray: true}})
   })

   .service('TransverseService',function(){
   	return {
   		"set_img": function(item, BASE_PATH_IMG){
   			var name_img = item.image;
			if(name_img && name_img.length >0){
				item.image = BASE_PATH_IMG+name_img;
			}else{
				item.image = BASE_PATH_IMG+'imageNotFound.jpg';
			}
	   	},
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