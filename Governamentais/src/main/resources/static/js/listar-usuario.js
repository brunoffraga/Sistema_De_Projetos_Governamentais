// Pega o container
const listaContainer = document.getElementById('usuarios-lista');

// Função para criar o HTML de cada usuário
function criarUsuarioHTML(usuario) {
  return `
    <br>
    <div class="usuario">
      <h4>Usuario: ${usuario.nome}</h4>
      <p><strong>E-mail: </strong> ${usuario.email}</p>
    </div>
    <br>
    <hr>
  `;
}

// Função para buscar usuários do backend
async function listarUsuarios() {
  try {
    const response = await fetch('http://localhost:8080/usuario?ativo=true');
    if (!response.ok) throw new Error('Erro ao buscar usuários');

    const data = await response.json();      // data é o objeto completo
    const usuarios = data.content;           // pega apenas o array dentro de 'content'

    // Limpa o container antes de inserir
    listaContainer.innerHTML = '';

    // Monta todo o HTML de uma vez
    let html = '';
    usuarios.forEach(usuario => {
      html += criarUsuarioHTML(usuario);
    });
    listaContainer.innerHTML = html;

  } catch (error) {
    console.error(error);
    listaContainer.innerHTML = '<p>Erro ao carregar usuários.</p>';
  }
}

// Chama a função quando a página carrega
window.addEventListener('DOMContentLoaded', listarUsuarios);

