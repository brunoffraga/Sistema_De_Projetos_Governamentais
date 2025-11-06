const menuContainer = document.getElementById('menu-lateral');

fetch('/static/html/menu/menu.html') // ou o caminho correto do menu
    .then(response => response.text())
    .then(html => {
        menuContainer.innerHTML = html;
    })
    .catch(error => console.error('Erro ao carregar o menu:', error));