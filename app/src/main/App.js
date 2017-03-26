'use strict';
var app = angular.module('starterApp', ['ngMaterial', 'ui.router', 'ui.grid', 'ui.grid.edit', 'ui.grid.autoResize', 'ui.grid.resizeColumns', 'ui.grid.selection', 'ui.grid.pinning', 'ui.grid.cellNav', 'ui.grid.draggable-rows', 'users']);

// controller for temp test
(function(){

  app.controller('HomeCtrl',[
    'mainService', '$mdSidenav', '$log', '$timeout', HomeCtrl
  ]);

  function HomeCtrl( mainService, $mdSidenav, $log, $timeout) {
    var self = this;
    self.testData = {
      name: " test",
      test1: "testestt1"
    };

    self.rowBreakFlgMap = {};
    self.makeCellClass = function(grid, row, col, rowRenderIndex, colRenderIndex, keyFields, groupFields) {
      if (rowRenderIndex == 0 && colRenderIndex == 0) {
        self.rowBreakFlgMap = {};
      }
      var bMap = self.rowBreakFlgMap;
      var breakFlg = false;
      if (bMap[rowRenderIndex]) {
        breakFlg = bMap[rowRenderIndex];
      } else {
        var renderRow = grid.renderContainers.body.renderedRows;
        if (rowRenderIndex != 0) {
          for (var j=0;j<keyFields.length;j++) {
            var keyField = keyFields[j];
            if (row.entity[keyField] != renderRow[rowRenderIndex -1].entity[keyField]) {
              breakFlg = true;
              break;
            }
          }
        } else {
          breakFlg = true;
        }
        bMap[rowRenderIndex] = breakFlg;
      }
      var rtnClass = "";
      if (breakFlg) {
        rtnClass = rtnClass + " group-divider";
        if (groupFields.indexOf(col.name) > -1) {
          rtnClass = rtnClass + " group-header";
        }
      } else {
        if (groupFields.indexOf(col.name) > -1) {
          rtnClass = rtnClass + " group-content";
        }
      }
      return rtnClass;

    };

    self.groupCellClass = function(grid, row, col, rowRenderIndex, colRenderIndex ) {

      return self.makeCellClass(grid, row, col, rowRenderIndex
          , colRenderIndex, ["col1","col2"],["col1","col2"]);
    }

    self.scrollGridToBottom = function(grid) {
      $timeout(function () {
        var visRows = grid.api.core.getVisibleRows(grid);
        var visCols = grid.getOnlyDataColumns();
        var visCol;
        for(var i=0;i<visCols.length;i++) {
          if (visCols[i].visible) {
            visCol = visCols[i];
            break;
          }
        }
        grid.api.core.scrollToIfNecessary(visRows[visRows.length -1],visCol).then(
            function () {}
        );

        //var idx = self.gridOptions.data.length -1;
        //this.grid.api.core.scrollTo(self.gridOptions.data[idx], self.gridOptions.columnDefs[0]);

        //this.grid.api.core.scrollTo(visRows[visRows.length -1].entity, visCol.colDef);
      });
    }

    self.gridOptions = {
      enableSorting: true,
      enableFiltering: true,
      enableGridMenu: true,
      //rowTemplate: "my-drag-grid-row-template.html",
      headerTemplate: "my-header-template.html",
      columnVirtualizationThreshold: 50,
      excessRows: 4,
      columnDefs: [
        { name:'col1', field: 'col1',width: 100,cellClass: self.groupCellClass,groupHeaderIndex:0 },
        { name:'col2', field: 'col2',width: 100,cellClass: self.groupCellClass,groupHeaderIndex:0,enableFiltering:false },
        { name:'col3', field: 'col3',width: 100,groupHeaderIndex:0,enableFiltering:false},
        { name:'col4', field: 'col4', enableCellEdit:true,width: 100,groupHeaderIndex:1},
        { name:'col5', field: 'col5', enableCellEdit:true,width: 100,groupHeaderIndex:1},
        { name:'col6', field: 'col6', enableCellEdit:true,width: 100,groupHeaderIndex:1},
        { name:'col7', field: 'col7', enableCellEdit:true,width: 100},
        { name:'col8', field: 'col8', enableCellEdit:true,width: 100},
        { name:'col9', field: 'col9', enableCellEdit:false,width: 100},
        { name:'col10', field: 'col10', enableCellEdit:false,width: 100},
        { name:'col11', field: 'col11', enableCellEdit:false,width: 100},
        { name:'col12', field: 'col12', enableCellEdit:false,width: 100},
        { name:'col13', field: 'col13', enableCellEdit:false,width: 100},
        { name:'col14', field: 'col14', enableCellEdit:false,width: 100}
      ],
      groupHeaders: ["header1","header2","header3"],
      gridMenuCustomItems: [
        {
          title: 'addrow',
          action: function ($event) {
            $event.stopPropagation();
            var grid = this.grid;
            var n = self.gridOptions.data.length + 1;
            self.gridOptions.data.push({
              col1: "newcol1", col2: "newcol2", col3: "1", col4: "9", col5: "newrow" + n, col6: "1111", col7: "1111", col8: "1111", col9: "1111", col10: "1111"
            });
            self.scrollGridToBottom(grid);
          },
          order: 10
        },
        {
          title: 'scroll to bottom',
          action: function ($event) {
            $event.stopPropagation();
            var grid = this.grid;
            self.scrollGridToBottom(grid);
          },
          order: 11,
          leaveOpen:true
        },
        {
          title: 'drop on/off',
          action: function ($event) {
            $event.stopPropagation();
            self.disableDragDrop = !self.disableDragDrop;
            this.grid.api.dragndrop.setDragDisabled(self.disableDragDrop);
          },
          order: 12,
          leaveOpen:false
        }
      ],
    };

    self.disableDragDrop = false;
    self.gridOptions.onRegisterApi = function(gridApi){
      self.gridApi = gridApi;
      gridApi.core.on.rowsRendered(null,function() {
        self.rowBreakFlgMap = {};
      });

      gridApi.dragndrop.setDragDisabled(self.disableDragDrop);
      gridApi.draggableRows.on.rowDropped(null, function (info, dropTarget) {
        console.log("Dropped", info);
      });
    };

    //var gData = [];
    //for (var i=1;i<100;i++) {
    //  var nowRow = {
    //    col1: "row" + i + "col1",
    //    col2: "row" + i + "col2",
    //    col3: "row" + i + "col3",
    //    col4: "row" + i + "col4",
    //    col5: "row" + i + "col5",
    //    col6: "row" + i + "col6",
    //    col7: "row" + i + "col7",
    //    col8: "row" + i + "col8",
    //    col9: "row" + i + "col9",
    //    col10: "row" + i + "col10",
    //    col11: "row" + i + "col11",
    //    col12: "row" + i + "col12",
    //    col13: "row" + i + "col13",
    //    col14: "row" + i + "col14"
    //  }
    //  gData.push(nowRow);
    //}
    //self.gridOptions.data = gData;

    var tData = [
      {col1: "G1col1", col2: "G1col2", col3: "1", col4: "9", col5: "row1", col6: "1111", col7: "1111", col8: "1111", col9: "1111", col10: "1111"},
      {col1: "G1col1", col2: "G1col2", col3: "9", col4: "1", col5: "row2", col6: "1111", col7: "1111", col8: "1111", col9: "1111", col10: "1111"},
      {col1: "G1col1", col2: "G1col2", col3: "9", col4: "1", col5: "row3", col6: "1111", col7: "1111", col8: "1111", col9: "1111", col10: "1111"},
      {col1: "G2col1", col2: "G2col2", col3: "9", col4: "9", col5: "row4", col6: "1111", col7: "1111", col8: "1111", col9: "1111", col10: "1111"},
      {col1: "G2col1", col2: "G2col2", col3: "1", col4: "1", col5: "row5", col6: "1111", col7: "1111", col8: "1111", col9: "1111", col10: "1111"},
      {col1: "G2col1", col2: "G2col2", col3: "9", col4: "1", col5: "row6", col6: "1111", col7: "1111", col8: "1111", col9: "1111", col10: "1111"},
      {col1: "G3col1", col2: "G3col2", col3: "9", col4: "1", col5: "row7", col6: "1111", col7: "1111", col8: "1111", col9: "1111", col10: "1111"},
      {col1: "G3col1", col2: "G3col2", col3: "9", col4: "9", col5: "row8", col6: "1111", col7: "1111", col8: "1111", col9: "1111", col10: "1111"},
      {col1: "G3col1", col2: "G3col2", col3: "1", col4: "1", col5: "row9", col6: "1111", col7: "1111", col8: "1111", col9: "1111", col10: "1111"},
      {col1: "G3col1", col2: "G3col2", col3: "9", col4: "1", col5: "row10", col6: "1111", col7: "1111", col8: "1111", col9: "1111", col10: "1111"},
      {col1: "G4col1", col2: "G4col2", col3: "1", col4: "9", col5: "row11", col6: "1111", col7: "1111", col8: "1111", col9: "1111", col10: "1111"},
      {col1: "G4col1", col2: "G4col2", col3: "9", col4: "1", col5: "row12", col6: "1111", col7: "1111", col8: "1111", col9: "1111", col10: "1111"},
      {col1: "G5col1", col2: "G5col2", col3: "1", col4: "1", col5: "row13", col6: "1111", col7: "1111", col8: "1111", col9: "1111", col10: "1111"},
      {col1: "G6col1", col2: "G6col2", col3: "1", col4: "9", col5: "row14", col6: "1111", col7: "1111", col8: "1111", col9: "1111", col10: "1111"},
      {col1: "G6col1", col2: "G6col2", col3: "9", col4: "1", col5: "row15", col6: "1111", col7: "1111", col8: "1111", col9: "1111", col10: "1111"},
      {col1: "G6col1", col2: "G6col2", col3: "9", col4: "1", col5: "row16", col6: "1111", col7: "1111", col8: "1111", col9: "1111", col10: "1111"},
      {col1: "G7col1", col2: "G7col2", col3: "9", col4: "9", col5: "row17", col6: "1111", col7: "1111", col8: "1111", col9: "1111", col10: "1111"},
      {col1: "G7col1", col2: "G7col2", col3: "9", col4: "1", col5: "row18", col6: "1111", col7: "1111", col8: "1111", col9: "1111", col10: "1111"},
      {col1: "G7col1", col2: "G7col2", col3: "1", col4: "1", col5: "row19", col6: "1111", col7: "1111", col8: "1111", col9: "1111", col10: "1111"},
      {col1: "G8col1", col2: "G8col2", col3: "1", col4: "1", col5: "row20", col6: "1111", col7: "1111", col8: "1111", col9: "1111", col10: "1111"},
      {col1: "G1col1", col2: "G1col2", col3: "1", col4: "9", col5: "row1", col6: "1111", col7: "1111", col8: "1111", col9: "1111", col10: "1111"},
      {col1: "G1col1", col2: "G1col2", col3: "9", col4: "1", col5: "row2", col6: "1111", col7: "1111", col8: "1111", col9: "1111", col10: "1111"},
      {col1: "G1col1", col2: "G1col2", col3: "9", col4: "1", col5: "row3", col6: "1111", col7: "1111", col8: "1111", col9: "1111", col10: "1111"},
      {col1: "G2col1", col2: "G2col2", col3: "9", col4: "9", col5: "row4", col6: "1111", col7: "1111", col8: "1111", col9: "1111", col10: "1111"},
      {col1: "G2col1", col2: "G2col2", col3: "1", col4: "1", col5: "row5", col6: "1111", col7: "1111", col8: "1111", col9: "1111", col10: "1111"},
      {col1: "G2col1", col2: "G2col2", col3: "9", col4: "1", col5: "row6", col6: "1111", col7: "1111", col8: "1111", col9: "1111", col10: "1111"},
      {col1: "G3col1", col2: "G3col2", col3: "9", col4: "1", col5: "row7", col6: "1111", col7: "1111", col8: "1111", col9: "1111", col10: "1111"},
      {col1: "G3col1", col2: "G3col2", col3: "9", col4: "9", col5: "row8", col6: "1111", col7: "1111", col8: "1111", col9: "1111", col10: "1111"},
      {col1: "G3col1", col2: "G3col2", col3: "1", col4: "1", col5: "row9", col6: "1111", col7: "1111", col8: "1111", col9: "1111", col10: "1111"},
      {col1: "G3col1", col2: "G3col2", col3: "9", col4: "1", col5: "row10", col6: "1111", col7: "1111", col8: "1111", col9: "1111", col10: "1111"},
      {col1: "G4col1", col2: "G4col2", col3: "1", col4: "9", col5: "row11", col6: "1111", col7: "1111", col8: "1111", col9: "1111", col10: "1111"},
      {col1: "G4col1", col2: "G4col2", col3: "9", col4: "1", col5: "row12", col6: "1111", col7: "1111", col8: "1111", col9: "1111", col10: "1111"},
      {col1: "G5col1", col2: "G5col2", col3: "1", col4: "1", col5: "row13", col6: "1111", col7: "1111", col8: "1111", col9: "1111", col10: "1111"},
      {col1: "G6col1", col2: "G6col2", col3: "1", col4: "9", col5: "row14", col6: "1111", col7: "1111", col8: "1111", col9: "1111", col10: "1111"},
      {col1: "G6col1", col2: "G6col2", col3: "9", col4: "1", col5: "row15", col6: "1111", col7: "1111", col8: "1111", col9: "1111", col10: "1111"},
      {col1: "G6col1", col2: "G6col2", col3: "9", col4: "1", col5: "row16", col6: "1111", col7: "1111", col8: "1111", col9: "1111", col10: "1111"},
      {col1: "G7col1", col2: "G7col2", col3: "9", col4: "9", col5: "row17", col6: "1111", col7: "1111", col8: "1111", col9: "1111", col10: "1111"},
      {col1: "G7col1", col2: "G7col2", col3: "9", col4: "1", col5: "row18", col6: "1111", col7: "1111", col8: "1111", col9: "1111", col10: "1111"},
      {col1: "G7col1", col2: "G7col2", col3: "1", col4: "1", col5: "row19", col6: "1111", col7: "1111", col8: "1111", col9: "1111", col10: "1111"},
      {col1: "G8col1", col2: "G8col2", col3: "1", col4: "1", col5: "row20", col6: "1111", col7: "1111", col8: "1111", col9: "1111", col10: "1111"}
    ];
    self.gridOptions.data = tData;

  }

})();

angular.module('ui.grid').directive('groupGridHeader', ['$compile', '$timeout', '$window', '$document', 'gridUtil', 'uiGridConstants',
  function ($compile, $timeout, $window, $document, gridUtil, uiGridConstants) {
    var groupGridHeader = {
      priority: 0,
      scope: false,
      //require: ['^uiGrid', '^uiGridRenderContainer'],
      replace: true,
      compile: function() {
        return {
          pre: function ($scope, $elm, $attrs) {
          },

          post: function ($scope, $elm, $attrs, controllers) {
            var updateHeaderOptions = function() {
              var groupHeaderContainers = [];
              var groupHeaders = $scope.colContainer.grid.options.groupHeaders;
              if (!groupHeaders) {
                groupHeaders = [];
              }
              var gIdx = -1;
              var renderedColumns = $scope.colContainer.renderedColumns;
              for (var i=0;i<renderedColumns.length;i++) {
                var col = renderedColumns[i];
                var nowGroupHeaderIndex;
                if (col.colDef.groupHeaderIndex === undefined) {
                  nowGroupHeaderIndex = groupHeaders.length -1;
                } else {
                  nowGroupHeaderIndex = col.colDef.groupHeaderIndex;
                }
                if (i == 0) {
                  groupHeaderContainers.push(
                      {
                        cols:[],
                        headerText:"",
                        firstIndex: 0,
                        isRowHeaderColumn: false
                      }
                  );
                  gIdx++;
                  if ($scope.colContainer.grid.isRowHeaderColumn(col)) {
                    groupHeaderContainers[gIdx].headerText = "*";
                    groupHeaderContainers[gIdx].isRowHeaderColumn = true;
                  } else {
                    var headerText = groupHeaders[nowGroupHeaderIndex];
                    if (!headerText) {
                      headerText = col.colDef.displayName || col.colDef.name;
                    }
                    groupHeaderContainers[gIdx].headerText = headerText;
                  }
                } else {
                  var preCol = renderedColumns[i-1];
                  var preGroupHeaderIndex;
                  if (preCol.colDef.groupHeaderIndex === undefined) {
                    preGroupHeaderIndex = groupHeaders.length -1;
                  } else {
                    preGroupHeaderIndex = preCol.colDef.groupHeaderIndex;
                  }
                  if (preGroupHeaderIndex != nowGroupHeaderIndex || $scope.colContainer.grid.isRowHeaderColumn(col) || $scope.colContainer.grid.isRowHeaderColumn(preCol)) {
                    groupHeaderContainers.push(
                        {
                          cols:[],
                          headerText:"",
                          firstIndex: i,
                          isRowHeaderColumn: false
                        }
                    );
                    gIdx++;
                    if ($scope.colContainer.grid.isRowHeaderColumn(col)) {
                      groupHeaderContainers[gIdx].headerText = "*";
                      groupHeaderContainers[gIdx].isRowHeaderColumn = true;
                    } else {
                      var headerText = groupHeaders[nowGroupHeaderIndex];
                      if (!headerText) {
                        headerText = col.colDef.displayName || col.colDef.name;
                      }
                      groupHeaderContainers[gIdx].headerText = headerText;
                    }
                  }
                }
                groupHeaderContainers[gIdx].cols.push(col);
              }
              $scope.groupHeaderContainers = groupHeaderContainers;
            }
            updateHeaderOptions();
            $scope.$watchCollection('colContainer.renderedColumns', function() {
              updateHeaderOptions();
            });
            $timeout(function(){
              $scope.grid.api.core.handleWindowResize();
            });
          }
        };
      }
    };

    return groupGridHeader;
  }
]);