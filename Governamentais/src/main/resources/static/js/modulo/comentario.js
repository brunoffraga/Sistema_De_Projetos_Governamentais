// Pega o projetoId da URL
const urlParams = new URLSearchParams(window.location.search);
const projetoId = urlParams.get('id');

// Pega os comentários do backend
fetch(`/api/projeto/comentarios?ativo=true&projetoId=${projetoId}`)
    .then(response => response.json())
    .then(data => {
        // data.content é o array de comentários (paginado)
        renderizaComentarios(data.content); // lista todos retornados
    })
    .catch(error => console.error('Erro ao carregar comentários:', error));

// Função que renderiza os comentários na página
function renderizaComentarios(comentarios) {
    const container = document.getElementById('comentarios');
    container.innerHTML = ''; // limpa comentários antigos

    comentarios.forEach(c => {
        const div = document.createElement('div');
        div.classList.add('mensagem');

        div.innerHTML = `
            <p style="font-size: 0.9em;">
              <strong>${c.usuarioNome}</strong> ${c.dataComentario ? new Date(c.dataComentario).toLocaleString() : ''}
            </p>
            <p>${c.descricao || ''}</p>
        `;

        container.appendChild(div);
    });
}
