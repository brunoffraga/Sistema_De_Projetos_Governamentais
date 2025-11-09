const listaContainer = document.getElementById('projeto-lista');

function criarProjetoHTML(projeto) {
  return `
    <div class="projeto">
        <h4>Projeto: ${projeto.nome}</h4>
        <p><strong>Status: </strong> ${projeto.status}</p>
        <p><strong>Porcentagem: </strong> ${projeto.porcentagem}</p>
        <p><strong>Data: </strong> ${projeto.data}</p>
        <button class="btn-entrar" data-id="${projeto.id}">Visualizar</button>
    </div>
    <hr>
  `;
}

async function listarProjeto() {
  try {
    const params = new URLSearchParams(window.location.search);
    const ativo = params.get('ativo');

    // usar ${ativo} para inserir o valor da variável
    const response = await fetch(`http://localhost:8080/projeto/semDescricao?ativo=${ativo}`);
    if (!response.ok) throw new Error('Erro ao buscar projeto');

    const data = await response.json();
    const projetos = data.content || [];

    listaContainer.innerHTML = '';

    let html = '';
    projetos.forEach(projeto => {
      html += criarProjetoHTML(projeto);
    });
    listaContainer.innerHTML = html;

    // Seleciona todos os botões
    const botoesVisualizar = document.querySelectorAll('.btn-entrar');

    botoesVisualizar.forEach(botao => {
      botao.addEventListener('click', () => {
        const idDoProjeto = botao.dataset.id; // pega o ID do botão
        console.log('teste')
        window.location.href = `http://localhost:8080/projeto/selecionado?id=${idDoProjeto}`;
      });
    });

  } catch (error) {
    console.error(error);
    listaContainer.innerHTML = '<p>Erro ao carregar projeto.</p>';
  }
}


window.addEventListener('DOMContentLoaded', listarProjeto);