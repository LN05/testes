function toggleMenu() {
    const menuMobile = document.getElementById("menu-mobile")
    if (menuMobile.className === "menu-mobile-active") {
        menuMobile.className = "menu-mobile"
    } else {
        menuMobile.className = "menu-mobile-active"
    }
}

/*   MODAL  */
function mostrar_modalAgendamento() {
    const elemento = document.getElementById('modal_novoAgendamento')
    const myModal = new bootstrap.Modal(elemento);
    myModal.show();
}

function mostrar_modalEditar() {
    const elemento = document.getElementById('modal_editarAgendamento')
    const myModal = new bootstrap.Modal(elemento);
    myModal.show();
}

function mostrar_modalExcluir() {
    const elemento = document.getElementById('modal_excluirAgendamento')
    const myModal = new bootstrap.Modal(elemento);
    myModal.show();
}

function mostrar_modalDisponibilidade() {
    const elemento = document.getElementById('modal_addDisponibilidade')
    const myModal = new bootstrap.Modal(elemento);
    myModal.show();
}

