// Espera o DOM carregar
document.addEventListener('DOMContentLoaded', () => {
    const areaBotoes = document.getElementById('area-botoes');

    // Função para carregar os projetos do backend
    async function carregaProjetos() {
        try {
            const response = await fetch('http://localhost:8080/projeto/semDescricao?ativo=true'); // endpoint Spring Boot
            const data = await response.json();
            const projetos = data.content; // array de projetos

            // Cria os botões dinamicamente
            projetos.forEach(projeto => {
                const botao = document.createElement('button');
                botao.classList.add('botao-obra');
                botao.textContent = projeto.nome;

                botao.addEventListener('click', () => {
                    const idDoProjeto = projeto.id; // pega do JSON ou array
                    window.location.href = `http://localhost:8080/projeto/selecionado?id=${projeto.id}`;
                });

                areaBotoes.appendChild(botao);
            });

        } catch (error) {
            console.error('Erro ao carregar os projetos:', error);
            areaBotoes.textContent = 'Não foi possível carregar os projetos.';
        }
    }

    // Chama a função
    carregaProjetos();
});
