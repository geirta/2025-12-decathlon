
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
    const response = await fetch("http://localhost:8080/competitors/results");
    const data = await response.json();

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
    const response = await fetch("http://localhost:8080/names");
    const data = await response.json();

    const competitorDropdown = document.getElementById("competitorDropdown");
    competitorDropdown.innerHTML = "";
    data.competitorNames.forEach(name => {
        const option = document.createElement("option");
        option.value = name;
        option.text = name;
        competitorDropdown.appendChild(option);
    });

    const categoryDropdown = document.getElementById("categoryDropdown");
    categoryDropdown.innerHTML = "";
    data.categoryNames.forEach(name => {
        const option = document.createElement("option");
        option.value = name;
        option.text = name;
        categoryDropdown.appendChild(option);
    });
}


loadDropdowns();
loadAllResults();