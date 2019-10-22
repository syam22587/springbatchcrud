var app = angular.module('crudAppSkv',['ui.router','ngStorage']);

app.constant('urls', {
    BASE: 'http://localhost:8080/SpringBootCRUDApp',
    SKV_SERVICE_API : 'http://localhost:8080/SpringBootCRUDApp/skv/api/skv/'
});

app.config(['$stateProvider', '$urlRouterProvider',
    function($stateProvider, $urlRouterProvider) {

        $stateProvider
            .state('home', {
                url: '/',
                templateUrl: 'skv/skvlist',
                controller:'SkvController',
                controllerAs:'ctrl',
                resolve: {
                    users: function ($q, SkvService) {
                        console.log('Load all skvs');
                        var deferred = $q.defer();
                        SkvService.loadAllUsers().then(deferred.resolve, deferred.resolve);
                        return deferred.promise;
                    }
                }
            });
        $urlRouterProvider.otherwise('/');
    }]);

