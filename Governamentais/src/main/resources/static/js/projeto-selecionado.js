document.addEventListener('DOMContentLoaded', () => {

    const botaoEditar = document.querySelector('.botao-editar');
    const botaoComentario = document.querySelector('.botao-comentario');
    const botaoHistorico = document.querySelector('.botao-historico');
    const botaoExcluir = document.querySelector('.botao-excluir');

    let projetoAtual = null;

    // Função para carregar o projeto
    async function carregaProjetos() {
        try {
            const params = new URLSearchParams(window.location.search);
            const id = params.get('id');
            if (!id) return;

            const response = await fetch(`http://localhost:8080/api/projeto/${id}`);
            if (!response.ok) throw new Error("Projeto não encontrado");

            const projeto = await response.json();
            projetoAtual = projeto;

            const campos = ['nome', 'data', 'descricao'];
            campos.forEach(campo => {
                const elemento = document.getElementById(campo);
                if (elemento) elemento.textContent = projeto[campo] ?? '';
            });

            // Atualiza o texto do botão Excluir/Ativar ao carregar
            if (botaoExcluir) {
                botaoExcluir.textContent = projeto.ativo ? "Excluir" : "Ativar";
            }

        } catch (error) {
            console.error("Erro ao carregar o projeto:", error);
        }
    }

    // BOTÃO EDITAR
    if (botaoEditar) {
        botaoEditar.addEventListener('click', (e) => {
            e.preventDefault();
            if (!projetoAtual) return;

            // Redireciona para a página de edição passando o id na URL
            window.location.href = `http://localhost:8080/projeto/edita?id=${projetoAtual.id}`;
        });
    }

    // BOTÃO EXCLUIR / ATIVAR
    if (botaoExcluir) {
        botaoExcluir.addEventListener('click', async (e) => {
            e.preventDefault();
            if (!projetoAtual) return;

            // Alterna o valor de "ativo"
            const novoStatus = !projetoAtual.ativo;
            const acao = novoStatus ? "ativar" : "excluir";

            if (!confirm(`Tem certeza que deseja ${acao} este projeto?`)) return;

            try {
                await fetch(`http://localhost:8080/projeto/${projetoAtual.id}/status?ativo=${novoStatus}`, {
                    method: 'PUT',
                    headers: { 'Content-Type': 'application/json' }
                });

                alert(`Projeto ${acao === "ativar" ? "ativado" : "excluído"} com sucesso!`);

                // Atualiza a flag local e o texto do botão
                projetoAtual.ativo = novoStatus;
                botaoExcluir.textContent = novoStatus ? "Excluir" : "Ativar";

            } catch (error) {
                console.error(`Erro ao ${acao} o projeto:`, error);
            }
        });
    }

    // BOTÃO COMENTÁRIO
    if (botaoComentario) {
        botaoComentario.addEventListener('click', (e) => {
            e.preventDefault();
            if (!projetoAtual) return;
            window.location.href = `http://localhost:8080/projeto/comentario?id=${projetoAtual.id}`;
        });
    }

    // BOTÃO HISTÓRICO
    if (botaoHistorico) {
        botaoHistorico.addEventListener('click', (e) => {
            e.preventDefault();
            if (!projetoAtual) return;
            window.location.href = `http://localhost:8080/projeto/selecionado?id=${projetoAtual.id}`;
        });
    }

    carregaProjetos();
});
