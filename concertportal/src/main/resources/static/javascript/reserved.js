// File: reserved.js
// Script per la gestione del form concerti

// Elementi DOM principali
const submitBtn = document.getElementById("concertSubmit");
const concertForm = document.getElementById("concertForm");
const citySelect = document.getElementById('citySelect');
const locationSelect = document.getElementById('locationSelect');
const posterImage = document.getElementById('poster');
const artistImage = document.getElementById('artistPhoto'); // Corretto il nome della variabile

// Variabili per le immagini caricate
let uploadedPosterImg = null;
let uploadedArtistImg = null;

/*
 * Validazione del form
 * @returns {boolean} - Se il form è valido
 */
const formCheck = () => {
    return true;
    // Qui puoi implementare la logica di validazione se necessario
}

/*
 * Gestisce il cambio della città selezionata
 * Carica le location disponibili per la città scelta
 */
citySelect.addEventListener('change', function() {
    const cityId = this.value;
    console.log("Selected city id: " + cityId);
    
    // Svuota il locationSelect
    locationSelect.innerHTML = '<option value="" hidden>Seleziona una venue</option>';
    locationSelect.disabled = false;

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

/*
 * Gestisce il caricamento dell'immagine della locandina
 */
posterImage.addEventListener("change", function() {
    const maxSize = 1 * 1024 * 1024; // 1MB
    const file = this.files[0];
    
    if(file && file.size <= maxSize) {
        const reader = new FileReader();
        reader.onload = function(e) {
            uploadedPosterImg = e.target.result;
            this.style.border = "1px solid green"; // Feedback visivo positivo
        }.bind(this);
        reader.readAsDataURL(file);
        console.log("Locandina caricata correttamente");
    } else {
        this.style.border = "1px solid red";
        console.log("File troppo grande o non valido");
        alert("La locandina deve essere inferiore a 1MB");
    }
});

/*
 * Gestisce il caricamento dell'immagine dell'artista
 */
artistImage.addEventListener("change", function() {
    const maxSize = 1 * 1024 * 1024; // 1MB
    const file = this.files[0];
    
    if(file && file.size <= maxSize) {
        const reader = new FileReader();
        reader.onload = function(e) {
            uploadedArtistImg = e.target.result;
            this.style.border = "1px solid green"; 
        }.bind(this);
        reader.readAsDataURL(file);
        console.log("Foto artista caricata correttamente");
    } else {
        this.style.border = "1px solid red";
        console.log("File troppo grande o non valido");
        alert("La foto dell'artista deve essere inferiore a 1MB");
    }
});

concertForm.addEventListener("submit", event => {
    event.preventDefault();
    console.log("SUBMIT");
    if (formCheck()) {
        
        const id = document.querySelector("input[name='id']").value;
        const artist = document.querySelector("input[name='artist']").value;

        const confirmMessage = id
            ? `Confermi la modifica a ${artist}?`
            : "Confermi l'aggiunta dell'evento?";

        if (!confirm(confirmMessage)) {
            return;
        }

          const formData = {
            id: document.querySelector("input[name='id']").value,
            concertName: document.querySelector("input[name='concertName']").value,
            artist: document.querySelector("input[name='artist']").value,
            genre: document.querySelector("input[name='genre']").value,
            date: document.querySelector("input[name='date']").value + "T" + document.querySelector("input[name='time']").value + ":00",
            description: document.querySelector("textarea[name='description']").value,
            price: document.querySelector("input[name='price']").value,
            foto: uploadedArtistImg,
            poster: uploadedPosterImg,
            location: {
                id: document.querySelector("select[name='locationSelect']").value, 
                name: document.querySelector("select[name='locationSelect'] option:checked").textContent,
                address: document.querySelector("select[name='locationSelect'] option:checked").textContent,
                city: {
                    id: document.querySelector("select[name='citySelect']").value, 
                    name: document.querySelector("select[name='citySelect'] option:checked").textContent 
                }
            }
        };

        console.log("Form Data:", formData); 

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
                if (response.status === 200 && text === "Operation completed") {
                    window.location.href = "/reserved";
                    console.log("reload page");
                }
            })
        })
        .catch(error => console.log(error))

    }
});
