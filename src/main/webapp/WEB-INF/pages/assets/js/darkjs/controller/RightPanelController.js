
var rendererData;

$(document).bind("TREE_ITEM_CLICKED", function itemClickedHandler(e){
    rendererData = {"mydata":e.uiData};
    loadViewItems(rendererData, "assets/js/darkjs/renderers/TileViewRenderer.html");
});

function handleViewChange(evt){

    switch(evt.currentTarget.id)
    {
        case "tileView":
            //console.log(":: Load Tile View Button Clicked ::");
            loadViewItems(rendererData, "assets/js/darkjs/renderers/TileViewRenderer.html");
        break;

        case "listView":
            //console.log(":: Load List View Button Clicked ::");
            loadViewItems(rendererData, "assets/js/darkjs/renderers/listViewRenderer.html");
        break;

        case "detailView":
            //console.log(":: Load Detail View Button Clicked ::");
            loadViewItems(rendererData, "assets/js/darkjs/renderers/DetailViewRenderer.html");
        break;
    }
}

function loadViewItems(evt, currentTemplateView){
    MustacheWrapper.createUI(currentTemplateView,evt, function(currentViewStr){
        $('#viewHolder').html(currentViewStr);
    });
}