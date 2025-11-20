export function listar() {
    const urlParams = new URLSearchParams(window.location.search);
    return urlParams.get('id');
}
