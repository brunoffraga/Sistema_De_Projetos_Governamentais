fetch('projetos.json')
    .then(res => res.json())
    .then(data => console.log(data))
    .catch(err => console.error('Erro ao carregar JSON:', err));