document.addEventListener("DOMContentLoaded", () => {

    const nomeInput = document.getElementById("nome");
    const dataInicioInput = document.getElementById("dataInicio");
    const descricaoInput = document.getElementById("descricao");

    const botaoAtualiza = document.querySelector(".botao-atualiza");
    const botaoVolta = document.querySelector(".botao-volta");

    // Pega ID da URL
    const params = new URLSearchParams(window.location.search);
    const projetoId = params.get("id");

    function formatDateParaInput(dateStr) {
        console.log(dateStr);
        if (!dateStr) return "";
        if (dateStr.includes("-")) return dateStr;
        const [dia, mes, ano] = dateStr.split("/");
        return `${ano}-${mes.padStart(2,'0')}-${dia.padStart(2,'0')}`;
    }

    async function carregaProjeto() {
        if (!projetoId){
            console.error("Nenhum ID de projeto encontrado na URL!");
            alert("ID do projeto não encontrado. Retornando à página anterior...");
            window.location.href = "http://localhost:8080/projetos"; // ou alguma página padrão
            return;
        }

        try {
            const response = await fetch(`/api/projeto/${projetoId}`);
            if (!response.ok) throw new Error("Projeto não encontrado");
            const projeto = await response.json();

            nomeInput.value = projeto.nome || "";
            descricaoInput.value = projeto.descricao || "";
            dataInicioInput.value = projeto.data;

        } catch (error) {
            console.error("Erro ao carregar projeto:", error);
        }
    }

    if (botaoAtualiza) {
        botaoAtualiza.addEventListener('click', async (e) => {
            e.preventDefault();

            const nome = nomeInput.value.trim();
            const descricao = descricaoInput.value.trim();
            const dataInicio = dataInicioInput.value;

            const hoje = new Date();
            const dataInicioObj = new Date(dataInicio);

            if (dataInicioObj < hoje) {
                alert("A data de início deve ser hoje ou futura.");
                return;
            }

            const dados = { nome, dataInicio, descricao };

            try {
                const response = await fetch(`/api/projeto/${projetoId}`, {
                    method: "PUT",
                    headers: { "Content-Type": "application/json" },
                    body: JSON.stringify(dados)
                });

                const result = await response.json();

                if (!response.ok) {
                    console.error("Erro do servidor:", result);
                    alert("Erro ao atualizar o projeto: " + JSON.stringify(result));
                } else {
                    alert("Projeto atualizado com sucesso!");
                    window.location.href = `http://localhost:8080/projeto/selecionado?id=${projetoId}`
                }

            } catch (error) {
                console.error("Erro ao atualizar o projeto:", error);
                alert("Erro ao atualizar o projeto");
            }
        });
    }

    if (botaoVolta){
        botaoVolta.addEventListener('click', (e) => {
            console.error("Nenhum ID de projeto encontrado na URL!");
            e.preventDefault();
            if (!projetoId) return;
            window.location.href = `http://localhost:8080/projeto/selecionado?id=${projetoId}`;
        })
    }

    carregaProjeto();
});
