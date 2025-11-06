// Espera o DOM carregar
document.addEventListener('DOMContentLoaded', () => {
    const areaBotoes = document.getElementById('area-botoes');

    // Função para carregar os projetos do backend
    async function carregaProjetos() {
        try {
            // Pega o ID da URL (?id=123)
            const params = new URLSearchParams(window.location.search);
            const id = params.get('id');

            // Busca os dados do projeto no backend
            const response = await fetch(`http://localhost:8080/projeto/${id}`);
            const projeto = await response.json();

            // Exibe os dados na página
            document.getElementById('nome').textContent = projeto.nome;
            document.getElementById('status').textContent = projeto.status;
            document.getElementById('porcentagem').textContent = projeto.porcentagem;
            document.getElementById('data').textContent = projeto.data;
            document.getElementById('descricao').textContent = projeto.descricao;
        } catch (error) {
            console.error("Erro ao carregar o projeto:", error);
            }
    }

    // Chama a função
    carregaProjetos();
});
