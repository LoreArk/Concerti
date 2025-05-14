const submitBtn = document.getElementById("concertSubmit");
const concertForm = document.getElementById("concertForm");
const citySelect = document.getElementById('citySelect');
const locationSelect = document.getElementById('locationSelect');

let uploadedImage = null;

const formCheck = () => {
    return true;
    //const verify = 
}

citySelect.addEventListener('change', function() {
        const cityId = this.value;
        console.log("Selected city id: " + cityId);
        // Svuota il locationSelect
        locationSelect.innerHTML = '<option value="">Seleziona una venue</option>';

        if (!cityId) return; // Se nessuna città è selezionata, esci

        fetch(`/locations?id=${cityId}`)
            .then(response => response.json())
            .then(locations => {
                console.log("Fetched locations:", locations);
                locations.forEach(loc => {
                    const option = document.createElement('option');
                    option.value = loc.id;
                    option.textContent = loc.name;
                    locationSelect.appendChild(option);
                });
            })
            .catch(error => {
                console.error('Errore nel caricamento delle venue:', error);
            });
    });

// document.getElementById('citySelector').addEventListener('change', function () {
//     const selectedCityId = this.value;
//     const urlParams = new URLSearchParams(window.location.search);

//     urlParams.set('cityId', selectedCityId);

//     // Se stai anche modificando un concert, conserva l'id nel redirect
//     const concertIdInput = document.querySelector('[name="id"]');
//     if (concertIdInput && concertIdInput.value) {
//         urlParams.set('id', concertIdInput.value);
//     }

//     // Ricarica la pagina con i nuovi parametri
//     window.location.href = '/reserved?' + urlParams.toString();
// });

concertForm.addEventListener("submit", event => {
    event.preventDefault();
    console.log("SUBMIT");
    if (formCheck()) {
        
          const formData = {
            id: document.querySelector("input[name='id']").value,
            concertName: document.querySelector("input[name='concertName']").value,
            artist: document.querySelector("input[name='artist']").value,
            genre: document.querySelector("input[name='genre']").value,
            date: document.querySelector("input[name='date']").value + "T" + document.querySelector("input[name='time']").value + ":00",
            location: {
                id: document.querySelector("select[name='locationSelect']").value, 
                name: document.querySelector("select[name='locationSelect'] option:checked").textContent,
                address: document.querySelector("select[name='locationSelect'] option:checked").textContent,
                city: {
                    id: document.querySelector("select[name='citySelect']").value, 
                    name: document.querySelector("select[name='citySelect'] option:checked").textContent 
                },
                // region: {
                //     id: document.querySelector("input[name='location.region.id']").value, 
                //     name: document.querySelector("input[name='location.region.name']").value
                // }
            }
        };

        console.log("Form Data:", formData); 

        // const dateField = formData.date;
        // const timeField = formData.time;

        // if (dateField && timeField) {
        //     formData.date = `${dateField}T${timeField}:00`;
        // }

        fetch("/reserved",
            {
                method: "POST",
                headers: { "Content-Type": "application/json" },
                body: JSON.stringify(formData)
            }
        )
        .then(response => {
            return response.text().then(text => {
                 console.log('Response:', text);
                if (response.status === 200 && text === "Operazione Eseguita") {
                    window.location.href = "/reserved";
                }
            })
        })
        .catch(error => console.log(error))

    }
});

