function search() {
    var input = document.getElementById('searchProgram').value;
    input = input.toLowerCase();
    var x = document.getElementsByClassName('content-item');

    for (var i = 0; i < x.length; i++) {
        if (!x[i].innerHTML.toLowerCase().includes(input)) {
            x[i].style.display = "none";
        }
    }
}

function filter(criteria) {

    var filterItems = document.getElementsByName("filter-checkbox-"+criteria);
    var contentFilterItems = document.getElementsByClassName("content-item-"+criteria);
    var contentItems = document.getElementsByClassName('content-item');
    var values = [];
    for (var i = 0; i < filterItems.length; i++) {
        if (filterItems[i].checked) {
            //place filter in array
            values.push(filterItems[i].getAttribute('value'));
        }
    }
    if(values.length > 0){
        for (var j = 0; j < contentFilterItems.length; j++) {
            if(contentItems[j].style.display == "list-item"){
            console.log(contentFilterItems[j].getAttribute('value'));
                if (values.indexOf(contentFilterItems[j].getAttribute('value')) === -1) {
                            contentItems[j].style.display = "none";
                        } else{
                            contentItems[j].style.display="list-item";
                }
            }
        }
    }
}
function multipleFilter(criteria) {

    var filterItems = document.getElementsByName("filter-checkbox-"+criteria);
    var contentFilterItems = document.getElementsByClassName("content-item-"+criteria);
    var contentItems = document.getElementsByClassName('content-item');
    var values = [];
    for (var i = 0; i < filterItems.length; i++) {
        if (filterItems[i].checked) {
            //place filter in array
            values.push(filterItems[i].getAttribute('value'));
        }
    }
    if(values.length > 0){
        for (var j = 0; j < contentFilterItems.length; j++) {
            if(contentItems[j].style.display == "list-item"){
            var inFilter = false;
                var children = contentFilterItems[j].getElementsByTagName("p");
                for (var i = 0; i < children.length; i++) {
                    if (values.indexOf(children[i].getAttribute('value')) === 0) {
                            inFilter = true;
                        }
                }
                    if(inFilter){
                                contentItems[j].style.display="list-item";
                    }
                    else{
                    contentItems[j].style.display = "none";
                    }
            }
        }
    }
}
function filtering(){
    var x = document.getElementsByClassName('content-item');
        for (i = 0; i < x.length; i++) {
            x[i].style.display="list-item";
        }
    filter('fieldOfStudy');
    filter('programType');
    multipleFilter('specialization');
    search();
}