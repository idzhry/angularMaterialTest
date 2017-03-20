'use strict';
(function(){

  app.service('mainService', ['$q', MainService]);

  function MainService($q){
    var menuItems = [
      {
        id: 'ID001',
        name: 'メニュー1',
        iconName: 'person',
        comment: 'メニュー1のコメント'
      },
      {
        id: 'ID002',
        name: 'メニュー2',
        iconName: 'person_add',
        comment: 'メニュー2のコメント'
      },
      {
        id: 'ID003',
        name: 'メニュー3',
        iconName: 'group',
        comment: 'メニュー3のコメント'
      },
      {
        id: 'ID004',
        name: 'メニュー4',
        iconName: 'group_add',
        comment: 'メニュー4のコメント'
      }
    ];

    // Promise-based API
    return {
      loadAllMenuItems : function() {
        // Simulate async nature of real remote calls
        return $q.when(menuItems);
      }
    };
  }

})();
