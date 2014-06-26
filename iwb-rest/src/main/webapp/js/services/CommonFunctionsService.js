'use strict';

angular.module('iwbApp').service('CommonFunctionsService', function() {
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
});