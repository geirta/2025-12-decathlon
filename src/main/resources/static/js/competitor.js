const urlParams = new URLSearchParams(window.location.search);
const competitorId = urlParams.get('id');

async function loadCompetitorResults() {
    const response = await fetch(`http://localhost:8080/competitor/${competitorId}/results`);

    const results = await response.json();

    if (results.length > 0) {
        document.getElementById('competitorName').innerHTML = results[0].competitor.name + " Results";
    }

    const tbody = document.getElementById('individualResultTable');
    tbody.innerHTML = '';

    results.forEach(r => {
        const row = document.createElement('tr');
        row.innerHTML = `
            <td>${r.category.name}</td>
            <td>${r.result}</td>
            <td>${r.points}</td>
        `;
        tbody.appendChild(row);
    });
}

loadCompetitorResults();