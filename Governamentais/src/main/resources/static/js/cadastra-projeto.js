document.addEventListener("DOMContentLoaded", () => {
    const form = document.querySelector("form");

    form.addEventListener("submit", (event) => {
        event.preventDefault(); // evita o envio padrão do formulário

        // pega os valores do formulário
        const nome = document.getElementById("nome").value.trim();
        const dataInicio = document.getElementById("dataInicio").value.trim();
        const descricao = document.getElementById("descricao").value.trim();

        // cria o objeto que será enviado
        const projeto = {
            nome: nome,
            dataInicio: dataInicio,
            descricao: descricao
        };

        // envia via fetch para o backend
        fetch("http://localhost:8080/api/projeto", {
            method: "POST",
            headers: {
                "Content-Type": "application/json"
            },
            body: JSON.stringify(projeto)
        })
        .then(response => {
            if (!response.ok) {
                throw new Error(`Erro na requisição: ${response.status}`);
            }
            return response.text(); // ou response.json() se o backend retornar JSON
        })
        .then(data => {
            console.log("Resposta do servidor:", data);
            alert("Projeto cadastrado com sucesso!");
            form.reset(); // opcional: limpa o formulário
        })
        .catch(error => {
            console.error("Erro ao enviar projeto:", error);
            alert("Ocorreu um erro ao cadastrar o projeto.");
        });
    });
});
