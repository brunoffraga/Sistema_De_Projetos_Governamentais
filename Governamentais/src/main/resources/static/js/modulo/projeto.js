export function getProjetoId() {
    const urlParams = new URLSearchParams(window.location.search);
    return urlParams.get('id');
}