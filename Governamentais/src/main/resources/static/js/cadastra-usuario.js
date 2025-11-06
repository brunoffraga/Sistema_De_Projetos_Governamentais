document.addEventListener("DOMContentLoaded", () => {
    const form = document.querySelector("form");

    form.addEventListener("submit", (event) => {
        event.preventDefault(); // evita o envio padrão do formulário

        // pega os valores do formulário
        const nome = document.getElementById("nome").value.trim();
        const email = document.getElementById("email").value.trim();

        // cria o objeto que será enviado
        const usuario = {
            nome: nome,
            email: email
        };

        // envia via fetch para o backend
        fetch("http://localhost:8080/usuario", {
            method: "POST",
            headers: {
                "Content-Type": "application/json"
            },
            body: JSON.stringify(usuario)
        })
        .then(response => {
            if (!response.ok) {
                throw new Error(`Erro na requisição: ${response.status}`);
            }
            return response.text(); // ou response.json() se o backend retornar JSON
        })
        .then(data => {
            console.log("Resposta do servidor:", data);
            alert("Usuário cadastrado com sucesso!");
            form.reset(); // opcional: limpa o formulário
        })
        .catch(error => {
            console.error("Erro ao enviar usuário:", error);
            alert("Ocorreu um erro ao cadastrar o usuário.");
        });
    });
});