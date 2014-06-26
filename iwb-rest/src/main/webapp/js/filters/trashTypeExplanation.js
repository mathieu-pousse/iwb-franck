'use strict';

angular.module('iwbApp')
  .filter('trashTypeExplanation', function() {
    return function(input, scope) {
      var acronymString = angular.copy(input);
      if (acronymString !== null && acronymString !== undefined){
      	switch(acronymString){
      		case "BIN": 
      			acronymString = "Borne d'Apport volontaire";
      			break;
      		case "HOME":
      			acronymString = "Bac à ordures";
      			break;
      		case "SAC":
      			acronymString = "Sac distribué par la Communauté de Commune";
      			break;
      		case "COMPOST":
      			acronymString = "Récipient dédié au compost";
      			break;
      		case "GARBAGE":
      			acronymString = "Décheterie";
      			break;
      		default:
      			acronymString = "Acronyme non reconnu";
      	}
      }else{
      	acronymString = "aucun acronym définit";
      }
      return acronymString;
    }
});