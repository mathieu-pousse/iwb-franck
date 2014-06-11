'use strict';

angular.module('iwbApp')
  .filter('acronymExplanation', function() {
    return function(input, scope) {
      var acronymString = angular.copy(input);
      if (acronymString !== null && acronymString !== undefined){
      	switch(acronymString){
      		case "OMR": 
      			acronymString = "Ordure ménagère recyclable";
      			break;
      		case "OMNR":
      			acronymString = "Ordure ménagère non recyclable";
      			break;
      		case "VER":
      			acronymString = "Verre d'emballage recyclable";
      			break;
      		case "VENR":
      			acronymString = "Verre non recyclable";
      			break;
      		case "PAR":
      			acronymString = "Plastique d'emballage recyclable";
      			break;
      		case "PANR":
      			acronymString = "Plastique non recyclable";
      			break;
      		case "JM":
      			acronymString = "Journeaux magazines";
      			break;
      		case "COMP":
      			acronymString = "Compost";
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