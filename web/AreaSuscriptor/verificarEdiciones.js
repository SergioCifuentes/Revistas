function verificar(i , j){
    var boton =document.getElementById("boton"+i+"-"+j);
    if (boton.disabled===true) {
        alert("Debes Pagar El atrazo Para Ver Esta Edicion")
    }
}

