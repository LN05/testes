function confirmaSenha() {
    var senha = document.getElementById("senha").value
    var confSenha = document.getElementById("confSenha").value
    if(senha != confSenha) {
        document.getElementById('erro').innerHTML='As Senhas não conferem!!!';
    } else {
        document.getElementById('erro').innerHTML='';
    }
} 

function passchk(){
    alert('hi');

}

function sizePass(){
    var senha = document.getElementById("senha").value
    if(senha.length < 8) {
        document.getElementById('erroPass').innerHTML='A senha deve conter pelo menos 8 caracteres!!!';
        document.getElementById("senha").focus();
    } else {
        document.getElementById('erroPass').innerHTML='';
    }
}

/*
<form id="form" action="" method="post">
<label for="pass">Password</label>
<input type="password" id="pass" class="text" name="your_pass" value="" onblur="passchk()"/>
<label for="c_pass">Confirm Password</label>
<input type="password" id="c_pass" class="text" name="your_c_pass" value="" onblur="confirmaSenha()"/><br>*/
