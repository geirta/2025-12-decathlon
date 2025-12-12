
// HANDLE RESULT SUBMISSION

document.getElementById("resultForm").addEventListener("submit", async function(e){
    e.preventDefault();
    const data = {
        competitorName: document.getElementById("competitorDropdown").value,
        categoryName: document.getElementById("categoryDropdown").value,
        result: parseFloat(document.getElementById("resultValue").value)
    };

    await fetch("http://localhost:8080/results", {
        method: "POST",
        headers: {"Content-Type": "application/json"},
        body: JSON.stringify(data)
    })
    .then(() => {
        loadAllResults();
        document.getElementById("resultForm").reset();
    });
});

// GET ALL RESULTS ON PAGE LOAD

async function loadAllResults() {
    const response = await fetch("http://localhost:8080/competitors");
    const data = await response.json();
    data.sort((a, b) => b.totalPoints - a.totalPoints);

    const tbody = document.querySelector("#leaderboard tbody");
    tbody.innerHTML = "";

    data.forEach((c, index) => {
        const row = document.createElement("tr");
        row.innerHTML = `
            <th scope="row">${index+1}</th>
            <td>${c.name}</td>
            <td>${c.country}</td>
            <td>${c.age}</td>
            <td>${c.totalPoints}</td>
        `;
        row.addEventListener('click', () => {
            window.location.href = `competitor.html?id=${c.id}`;
        });
        tbody.appendChild(row);
    });
}

// LOAD DROP DOWNS

async function loadDropdowns() {
    const responseCompetitors = await fetch("http://localhost:8080/competitors");
    const competitors = await responseCompetitors.json();

    const competitorDropdown = document.getElementById("competitorDropdown");
    competitorDropdown.innerHTML = "";
    competitors.forEach(c => {
        const option = document.createElement("option");
        option.value = c.name;
        option.text = c.name;
        competitorDropdown.appendChild(option);
    });

    const responseCategories = await fetch("http://localhost:8080/categories");
    const categories = await responseCategories.json();

    const categoryDropdown = document.getElementById("categoryDropdown");
    categoryDropdown.innerHTML = "";
    categories.forEach(cat => {
        const option = document.createElement("option");
        option.value = cat.name;
        option.text = cat.name;
        categoryDropdown.appendChild(option);
    });
}


loadDropdowns();
loadAllResults();