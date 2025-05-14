const submitBtn = document.getElementById("concertSubmit");
const concertForm = document.getElementById("concertForm");
const citySelect = document.getElementById('citySelect');
const locationSelect = document.getElementById('locationSelect');
const posterImage = document.getElementById('poster');
const artistiImage = document.getElementById('artistPhoto');

let uploadedPosterImg = null;
let uploadedArtistImg = null;

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

posterImage.addEventListener("change", function() {
    const maxSize = 1 * 1024 * 1024;
    const file = this.files[0];
    if(file && file.size <= maxSize) {
        const reader = new FileReader();
        reader.onload = function (e) {
            uploadedPosterImg = e.target.result;
            posterImage.src = uploadedPosterImg;
            //posterImage.value = "";
        };
        reader.readAsDataURL(file);
        console.log("File uploaded");
    } else {
        this.style.border = "1px solid red";
        console.log("File troppo grande o qualcos'altro");
    }
});

artistiImage.addEventListener("change", function() {
    const maxSize = 1 * 1024 * 1024;
    const file = this.files[0];
    if(file && file.size <= maxSize) {
        const reader = new FileReader();
        reader.onload = function (e) {
            uploadedArtistImg = e.target.result;
            artistiImage.src = uploadedArtistImg;
            //posterImage.value = "";
        };
        reader.readAsDataURL(file);
        console.log("File uploaded");
        this.style.border = "1px solid white";
    } else {
        this.style.border = "1px solid red";
        console.log("File troppo grande o qualcos'altro");
    }
});


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
            poster: uploadedPosterImg,
            foto: uploadedArtistImg,
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
                if (response.status === 200 && text === "Operazione Eseguita") {
                    window.location.href = "/reserved";
                }
            })
        })
        .catch(error => console.log(error))

    }
});

