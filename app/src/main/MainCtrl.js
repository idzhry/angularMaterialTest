'use strict';
(function(){

  app.controller('MainCtrl',[
    'mainService', '$mdSidenav', '$log', MainCtrl
  ]);

  function MainCtrl( mainService, $mdSidenav, $log ) {
    var self = this;

    self.selected = null;
    self.toggleList = toggleList;
    self.menuItems = [];
    self.selectItem = selectItem;

    mainService
        .loadAllMenuItems()
        .then(function (menuItems) {
          self.menuItems = menuItems;
          self.selected = menuItems[0];
        });

    function toggleList() {
      $mdSidenav('left').toggle();
    }

    function selectItem(menuItem) {
      self.selected = menuItem;
    }

  }

})();