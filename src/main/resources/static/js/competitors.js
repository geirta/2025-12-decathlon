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
        loadAllCompetitors(); // reloads competitor table
        document.getElementById("competitorForm").reset();
    })
    .catch(err => console.error(err));
});

// GET ALL COMPETITORS ON PAGE LOAD

async function loadAllCompetitors() {
    const response = await fetch("http://localhost:8080/competitors");
    const data = await response.json();
    data.sort((a, b) => a.id - b.id);

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

// LOAD DROPDOWN COUNTRIES

async function loadDropdowns() {
    const competitorCountries = ["Estonia", "Finland", "Latvia", "Sweden"];
    const countryDropdown = document.getElementById("country");
    countryDropdown.innerHTML = "";
    competitorCountries.forEach(c => {
        const option = document.createElement("option");
        option.value = c;
        option.text = c;
        countryDropdown.appendChild(option);
    });
}

loadDropdowns();
loadAllCompetitors();