/* 
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
var userName = document.getElementById("user");
var espacio = document.getElementById("userSpace");
function confirmarPassword() {
    if (userName==="a") {
        espacio.innerHTML=espacio.innerHTML+" No son Iguales";
    }
}
