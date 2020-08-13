var btnExcel = document.getElementById("btnExcel"),
        lblExcel = document.getElementById("lblExcel"),
        selectExcel = document.getElementById("SExcel"),
        fondoExcel = document.getElementById("fondoExc"),
        btnSearch = document.getElementById("btnSearch"),
        lblSearch = document.getElementById("lblSearch"),
        selectSearch = document.getElementById("SSearch"),
        fondoSearch = document.getElementById("fondoSea")
        ;

btnExcel.addEventListener('click',function(){
   
   lblExcel.classList.toggle("active");
   selectExcel.classList.toggle("active");
   fondoExcel.classList.toggle("active");
   lblSearch.classList.remove("active");
   selectSearch.classList.remove("active");
   fondoSearch.classList.remove("active");
    
});

btnSearch.addEventListener('click',function(){
   
   lblExcel.classList.remove("active");
   selectExcel.classList.remove("active");
   fondoExcel.classList.remove("active");
   lblSearch.classList.toggle("active");
   selectSearch.classList.toggle("active");
   fondoSearch.classList.toggle("active");
    
});
