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
	   	//Unset the image base path url
	   	"unset_path": function(fullpathName){
            var nameWihoutPath = fullpathName;
            if(nameWihoutPath){
               nameWihoutPath = nameWihoutPath.replace(/^.*[\\\/]/, '');
            }
            return nameWihoutPath;
	   	},

         "removeLinks": function(item){
            item.link = null;
            var nameImg = this.unset_path(item.image);
            item.image = (nameImg === 'imageNotFound.jpg') ? null : nameImg;
            if(item.constituents && item.constituents.length >0){
               for(var i=0, len=item.constituents.length; i < len; i++){
                  if(typeof item.constituents[i].wasteType !== 'undefined'){
                     if(typeof item.constituents[i].wasteType.link !== 'undefined'){item.constituents[i].wasteType.link = null;}
                  }
                  var nameImgComponent = this.unset_path(item.constituents[i].image);
                  item.constituents[i].image = (nameImgComponent === 'imageNotFound.jpg') ? null : nameImgComponent;
               }
            }else if(typeof item.wasteType !== 'undefined'){
               if(typeof item.wasteType.link !== 'undefined'){item.wasteType.link = null;}
            }
         }

   	}
   })

    


  .value('version', '0.1');