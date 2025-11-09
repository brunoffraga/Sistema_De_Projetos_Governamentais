const listaContainer = document.getElementById('usuarios-lista');

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

async function listarUsuarios() {
  try {
    const params = new URLSearchParams(window.location.search);
    const ativo = params.get('ativo'); // pega true/false da URL

    const response = await fetch(`http://localhost:8080/usuario?ativo=${ativo}`);

    if (!response.ok) throw new Error('Erro ao buscar usuários');

    const data = await response.json(); // pega objeto completo
    const usuarios = data.content || []; // pega array dentro de 'content'

    listaContainer.innerHTML = '';

    usuarios.forEach(usuario => {
      listaContainer.innerHTML += criarUsuarioHTML(usuario);
    });

  } catch (error) {
    console.error(error);
    listaContainer.innerHTML = '<p>Erro ao carregar usuários.</p>';
  }
}

window.addEventListener('DOMContentLoaded', listarUsuarios);
