var DropDownJS = function(data){
    var data = data;
    this.createDropDown = function(dropDownObj){
        var jsonlist = data;

        for(var i=0; i< jsonlist.length; i ++){
            var opt = document.createElement('option');
            opt.value = jsonlist[i].id;
            opt.innerHTML = "ViewStructure"+jsonlist[i].id;
            dropDownObj.appendChild(opt);
        }

        $(dropDownObj).change(function(){
            changeSchema($(this).val());
            $('#schemaDropDown').hide();
            $('#mainAnimationContainer').animate({height: actualHeight}, 500);
            $('#selectLbl').text("Select View");
            flag = true;
        });
    }
}
