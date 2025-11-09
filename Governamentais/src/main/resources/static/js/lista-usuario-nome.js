// Exemplo: URL da sua API Spring Boot
const apiUrl = 'http://localhost:8080/usuario/nome?ativo=true';

// Faz a requisição para buscar os usuários
fetch(apiUrl)
.then(response => response.json())
    .then(data => {
        // data.content é o array de usuários
        const users = data.content;

        // Seleciona o elemento <select>
        const select = document.getElementById('usuario');

        // Preenche o select com os usuários
        users.forEach(user => {
            const option = document.createElement('option');
            option.value = user.id;   // Valor do option
            option.textContent = user.nome; // Texto exibido
            select.appendChild(option);
        });
    })
    .catch(error => {
    console.error('Erro ao carregar usuários:', error);
});