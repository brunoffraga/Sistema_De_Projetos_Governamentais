document.addEventListener('DOMContentLoaded', () => {
    const form = document.querySelector('form');

    if (!form) {
        console.error("Erro: Formulário não encontrado no DOM!");
        return;
    }

    form.addEventListener('submit', async (e) => {
        e.preventDefault();

        const nome = document.getElementById('nome')?.value.trim();
        const dataInicial = document.getElementById('dataInicio')?.value.trim(); // campo correto
        const descricao = document.getElementById('descricao')?.value.trim();

        if (!nome || !dataInicial || !descricao) {
            alert("Preencha todos os campos!");
            return;
        }

        // Monta o JSON conforme o backend espera
        const body = {
            id: "", // se for cadastro novo, deixa vazio ou gera no backend
            nome,
            status: "PLANEJADO_INICIAL",
            dataInicial,
        };

        console.log("JSON enviado (POST):", body);

        try {
            const response = await fetch('http://localhost:8080/projeto', {
                method: 'POST',
                headers: { 'Content-Type': 'application/json' },
                body: JSON.stringify(body)
            });

            if (!response.ok) {
                const errorText = await response.text();
                console.error("Resposta do servidor:", errorText);
                throw new Error("Erro ao cadastrar projeto");
            }

            alert("Projeto cadastrado com sucesso!");
            window.location.href = "http://localhost:63342/Governamentais/templates/index.html";

        } catch (error) {
            console.error("Erro ao cadastrar o projeto:", error);
            alert("Ocorreu um erro ao cadastrar o projeto. Verifique o console para mais detalhes.");
        }
    });
});
