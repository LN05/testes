function toggleMenu(){
    const menuMobile = document.getElementById("menu-mobile")
    if(menuMobile.className === "menu-mobile-active"){
        menuMobile.className = "menu-mobile"
    } else{
        menuMobile.className = "menu-mobile-active"
    }
}

$('.agendamento').click(function(){
    $('.sidebar ul .itemAgendamento').toggleClass('mostra');
});

$('.agendamento').mouseover(function(){
    $('.sidebar ul .seta1').toggleClass('girar');
});

$('.agendamento').mouseout(function(){
    $('.sidebar ul .seta1').toggleClass('girar');
});
