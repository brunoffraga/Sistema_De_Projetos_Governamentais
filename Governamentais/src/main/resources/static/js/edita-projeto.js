document.addEventListener("DOMContentLoaded", () => {

    const form = document.querySelector("form");
    const nomeInput = document.getElementById("nome");
    const dataInicioInput = document.getElementById("dataInicio");
    const descricaoInput = document.getElementById("descricao");

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
        try {
            const response = await fetch(`/projeto/${projetoId}`);
            if (!response.ok) throw new Error("Projeto não encontrado");
            const projeto = await response.json();

            nomeInput.value = projeto.nome || "";
            descricaoInput.value = projeto.descricao || "";
            dataInicioInput.value = projeto.data;

        } catch (error) {
            console.error("Erro ao carregar projeto:", error);
        }
    }

    carregaProjeto();

    form.addEventListener("submit", async (e) => {
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
            const response = await fetch(`/projeto/${projetoId}`, {
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
            }

        } catch (error) {
            console.error("Erro ao atualizar o projeto:", error);
            alert("Erro ao atualizar o projeto");
        }
    });

});
