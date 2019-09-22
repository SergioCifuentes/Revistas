/* 
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
var pass = document.getElementById("pass");
var longitudmin=8;
var confirmacion = document.getElementById("confirmacion");
var espacio = document.getElementById("errorConfirmacion");
var espacioLongitud = document.getElementById("errorPassword");
function confirmarLongitud() {
    if (pass.value.length<longitudmin) {
        espacioLongitud.innerHTML="minimo "+longitudmin+" caracteres";
    }else{
        espacioLongitud.innerHTML=" ";
    }
}
function confirmarPassword() {
    if (pass.value!==confirmacion.value) {
        espacio.innerHTML=" No son Iguales";
    }else{
        espacio.innerHTML=" ";
    }
}



