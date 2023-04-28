// Event listener para o botão de login
document.querySelector('#btnLogin').addEventListener('click', () => {
    const nome = document.querySelector('#userLabel').value;
    const senha = document.querySelector('#senhaLabel').value;
  
    const usuario = Autenticacao.autenticarUsuario(nome, senha);
  
    if (usuario != null) {
      if (usuario.nivelAcesso === 1) {
        // Redirecionar para a tela de Barbeiro
        window.location.href = "../home.html";
      } else if (usuario.nivelAcesso === 2) {
        // Redirecionar para a tela de MASTER
        window.location.href = "../adm.html";
      }
    } else {
      alert('Usuário ou senha inválidos');
    }
  });