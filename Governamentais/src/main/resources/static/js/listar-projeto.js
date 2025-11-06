const listaContainer = document.getElementById('projeto-lista');

function criarProjetoHTML(projeto) {
  return `
    <div class="projeto">
      <h4>Projeto: ${projeto.nome}</h4>
      <p><strong>Status: </strong> ${projeto.status}</p>
      <p><strong>Porcentagem: </strong> ${projeto.porcentagem}</p>
      <p><strong>Data: </strong> ${projeto.data}</p>
      <p><strong>Descrição: </strong> ${projeto.descricao}</p>
      <button class="btn-entrar" data-id="${projeto.id}">Entrar no projeto</button>
    </div>
    <hr>
    <br><br><br>
  `;
}

async function listarProjeto() {
  try {
    const response = await fetch('http://localhost:8080/projeto/semDescricao?ativo=true');
    if (!response.ok) throw new Error('Erro ao buscar projeto');

    const data = await response.json();
    const projetos = data.content;

    listaContainer.innerHTML = '';
    let html = '';
    projetos.forEach(projeto => {
      html += criarProjetoHTML(projeto);
    });
    listaContainer.innerHTML = html;

    // Seleciona todos os botões
    const botoes = document.querySelectorAll('.btn-entrar');

    botoes.forEach(botao => {
      botao.addEventListener('click', () => {
        const idDoProjeto = botao.dataset.id; // pega o ID do botão
        // Redireciona para a página do projeto com o ID na URL
        window.location.href = `http://localhost:63342/Governamentais/templates/projeto-selecionado.html?id=${idDoProjeto}`;
      });
    });

  } catch (error) {
    console.error(error);
    listaContainer.innerHTML = '<p>Erro ao carregar projeto.</p>';
  }
}

window.addEventListener('DOMContentLoaded', listarProjeto);
