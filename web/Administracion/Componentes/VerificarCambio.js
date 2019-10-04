const cuotaGlobal = document.getElementById("costoConstante");
function confirmarCambio(i){
    if ((cuotaGlobal.value-document.getElementById("costo"+i).value)===0){
        document.getElementById("label"+i).innerHTML="Global";
    }else{
        document.getElementById("label"+i).innerHTML="";
    }
}  

