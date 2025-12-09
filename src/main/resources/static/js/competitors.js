// ADD NEW COMPETITOR

document.getElementById("competitorForm").addEventListener("submit", function(e) {
    e.preventDefault();

    const data = {
        name: document.getElementById("name").value,
        country: document.getElementById("country").value,
        age: parseInt(document.getElementById("age").value)
    };

    fetch("http://localhost:8080/competitors", {
        method: "POST",
        headers: {"Content-Type": "application/json"},
        body: JSON.stringify(data)
    })
    .then(res => res.json())
    .then(() => {
        updateAllCompetitors(); // reloads competitor table
        document.getElementById("competitorForm").reset();
    })
    .catch(err => console.error(err));
});

// UPDATE COMPETITOR LIST AFTER ADDING A NEW ONE

function updateAllCompetitors() {
    fetch("http://localhost:8080/competitors")
        .then(res => res.json())
        .then(competitors => {
            const tbody = document.querySelector("#allCompetitors tbody");
            tbody.innerHTML = "";

            competitors.forEach(c => {
                const row = `
                    <tr>
                        <th scope="row">${c.id}</th>
                        <td>${c.name}</td>
                        <td>${c.country}</td>
                        <td>${c.age}</td>
                    </tr>
                `;
                tbody.innerHTML += row;
            });
        });
}

// GET ALL COMPETITORS ON PAGE LOAD

async function loadAllCompetitors() {
    const response = await fetch("http://localhost:8080/competitors");
    const data = await response.json();

    const tbody = document.querySelector("#allCompetitors tbody");
    tbody.innerHTML = "";

    data.forEach(c => {
        const row = document.createElement("tr");
        row.innerHTML = `
            <th scope="row">${c.id}</th>
            <td>${c.name}</td>
            <td>${c.country}</td>
            <td>${c.age}</td>
        `;
        tbody.appendChild(row);
    });
}

loadAllCompetitors();