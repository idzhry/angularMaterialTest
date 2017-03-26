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
                        firstIndex: 0
                      }
                  );
                  gIdx++;
                  var headerText = groupHeaders[nowGroupHeaderIndex];
                  if (!headerText) {
                    headerText = col.colDef.displayName || col.colDef.name;
                  }
                  groupHeaderContainers[gIdx].headerText = headerText;
                } else {
                  var preCol = renderedColumns[i-1];
                  var preGroupHeaderIndex;
                  if (preCol.colDef.groupHeaderIndex === undefined) {
                    preGroupHeaderIndex = groupHeaders.length -1;
                  } else {
                    preGroupHeaderIndex = preCol.colDef.groupHeaderIndex;
                  }
                  if (preGroupHeaderIndex != nowGroupHeaderIndex) {
                    groupHeaderContainers.push(
                        {
                          cols:[],
                          headerText:"",
                          firstIndex: i
                        }
                    );
                    gIdx++;
                    var headerText = groupHeaders[nowGroupHeaderIndex];
                    if (!headerText) {
                      headerText = col.colDef.displayName || col.colDef.name;
                    }
                    groupHeaderContainers[gIdx].headerText = headerText;
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