import { enviarComentario, listarComentarios } from './js/modulo/projeto';

document.addEventListener('DOMContentLoaded', () => {
    listarComentarios();

    document.querySelector('.btn-enviar')
        .addEventListener('click', async () => {
            let usuarioId = localStorage.getItem('usuarioId') || prompt('Selecione seu usuário pelo ID');
            const comentario = document.querySelector('.input-area input').value.trim();

            if (!usuarioId) return alert("Você precisa selecionar um usuário.");
            if (!comentario) return alert("Digite um comentário.");

            await enviarComentario(usuarioId, comentario);
        });
});
