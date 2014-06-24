'use strict';

angular.module('iwbApp').directive('scrollToBookmark', function() {
    return {
      link: function(scope, element, attrs) {
        var value = attrs.scrollToBookmark;
        $(element).click(function() {
          $('html, body').animate({scrollTop:$("#" + value).offset().top}, 500);

        });
      }
    };
});