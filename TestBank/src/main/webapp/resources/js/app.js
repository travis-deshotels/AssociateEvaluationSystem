var app = angular.module("AESapp", [ 'ui.router', 'ngFileUpload','ngProgress']);

app.config(['$stateProvider', '$urlRouterProvider', function($stateProvider) {
    
 
    $stateProvider
        .state('category', {
            url:'/category',
            templateUrl: 'categoryTemplate.html',
            controller: 'MasterCtrl as ctrl'
        })
        .state('tag', {
            url:'/',
            templateUrl: 'about.html',
            controller: 'MasterCtrl'
        })
        .state('question', {
            url:'/',
            templateUrl: 'about.html',
            controller: 'MasterCtrl'
        })
        .state('upload', {
            url:'/upload',
            templateUrl: 'uploadTemplate.html',
            controller: 'MasterCtrl'
        })
 
}]);

app.directive('fileModel', ['$parse', function ($parse) {
    return {
        restrict: 'A',
        link: function(scope, element, attrs) {
            var model = $parse(attrs.fileModel);
            var modelSetter = model.assign;
            
            element.bind('change', function(){
                scope.$apply(function(){
                    modelSetter(scope, element[0].files[0]);
                });
            });
        }
    };
}]);

app.service('fileUpload', ['$http', function ($http) {
    this.uploadFileToUrl = function(file, uploadUrl){
        var fd = new FormData();
        fd.append('file', file);
        return $http.post(uploadUrl, fd, {
            transformRequest: angular.identity,
            headers: {'Content-Type': undefined}
        })
    }
}]);