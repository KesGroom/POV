var actUsers = document.getElementById("act1");
var actRol = document.getElementById("act2");
var formUser = document.getElementById("formAdd1");
var formRol = document.getElementById("formAdd2");
var iconUser = document.getElementById("icon1");
var iconRol = document.getElementById("icon2");

actUsers.addEventListener('click', function() {
    actUsers.classList.toggle("active");
    formUser.classList.toggle("active");
    actRol.classList.remove("active");
    formRol.classList.remove("active");
    
    var nameIcon = iconUser.getAttribute("name");
    if( nameIcon === 'person-add'){
        iconUser.setAttribute('name' , 'close-circle');
    } else{
        iconUser.setAttribute('name' , 'person-add');
    }
    
    iconRol.setAttribute('name','body');   
});

actRol.addEventListener('click', function() {
    actRol.classList.toggle("active");
    formRol.classList.toggle("active");
    actUsers.classList.remove("active");
    formUser.classList.remove("active");
    
    var nameIcon2 = iconRol.getAttribute("name");
    if( nameIcon2 === 'body'){
        iconRol.setAttribute('name' , 'close-circle');
    } else{
        iconRol.setAttribute('name' , 'body');
    }
    
    iconUser.setAttribute('name','person-add');
    
});
