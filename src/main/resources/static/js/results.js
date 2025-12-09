
// HANDLE RESULT SUBMISSION

document.getElementById("resultForm").addEventListener("submit", async function(e){
    e.preventDefault();
    const data = {
        competitorName: document.getElementById("competitorDropdown").value,
        categoryName: document.getElementById("categoryDropdown").value,
        result: parseFloat(document.getElementById("resultValue").value)
    };

    await fetch("http://localhost:8080/result", {
        method: "POST",
        headers: {"Content-Type": "application/json"},
        body: JSON.stringify(data)
    })
    .then(() => {
        updateResults();
        document.getElementById("resultForm").reset();
    });
});


// UPDATE RESULT TABLE

function updateResults() {
    fetch("http://localhost:8080/competitors/results")
        .then(res => res.json())
        .then(competitors => {
            const tbody = document.querySelector("#leaderboard tbody");
            tbody.innerHTML = "";

            competitors.forEach(c => {
                const row = `
                    <tr>
                        <th scope="row">${c.id}</th>
                        <td>${c.name}</td>
                        <td>${c.country}</td>
                        <td>${c.age}</td>
                        <td>${c.totalPoints}</td>
                    </tr>
                `;
                tbody.innerHTML += row;
            });
        });
}


// GET ALL RESULTS ON PAGE LOAD

async function loadAllResults() {
    const response = await fetch("http://localhost:8080/competitors/results");
    const data = await response.json();

    const tbody = document.querySelector("#leaderboard tbody");
    tbody.innerHTML = "";

    data.forEach(c => {
        const row = document.createElement("tr");
        row.innerHTML = `
            <th scope="row">${c.id}</th>
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