import { getProjetoId } from './projeto';

const projetoId = getProjetoId();

const comentariosDiv = document.getElementById('comentarios');
const inputComentario = document.getElementById('input-comentario');
const botaoEnviar = document.getElementById('botao-enviar');

export async function listarComentarios() {
    try {
        // Pega todos os comentários ativos
        const response = await fetch(`http://localhost:8080/comentario?ativo=true`);
        const data = await response.json();

        // Limpa comentários antigos
        comentariosDiv.innerHTML = '';

        // Filtra comentários do projeto atual
        const comentariosProjeto = data.content.filter(c => c.idprojeto === projetoId);

        // Adiciona cada comentário ao DOM
        comentariosProjeto.forEach(c => {
            const div = document.createElement('div');
            div.classList.add('mensagem');
            div.innerHTML = `<strong>${c.usuairoNome}</strong>: ${c.comentario || '[Sem texto]'}`;
            comentariosDiv.appendChild(div);
        });
    } catch (error) {
        console.error('Erro ao listar comentários:', error);
    }
}

export async function enviarComentario(usuarioId, comentario) {
    if (!comentario.trim()) return; // evita enviar comentário vazio
    try {
        const payload = { projetoId, usuarioId, comentario };
        const response = await fetch('http://localhost:8080/comentario', {
            method: 'POST',
            headers: { 'Content-Type': 'application/json' },
            body: JSON.stringify(payload)
        });

        if (!response.ok) throw new Error('Falha ao enviar comentário');

        // Limpa input e atualiza lista
        inputComentario.value = '';
        listarComentarios();
    } catch (error) {
        console.error('Erro ao enviar comentário:', error);
    }
}

// Evento do botão
botaoEnviar.addEventListener('click', () => {
    const usuarioId = 1; // exemplo, substitua pelo id real do usuário
    const comentario = inputComentario.value;
    enviarComentario(usuarioId, comentario);
});

// Carrega comentários ao abrir a página
listarComentarios();
