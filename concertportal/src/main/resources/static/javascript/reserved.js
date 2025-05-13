const submitBtn = document.getElementById("concertSubmit");
const concertForm = document.getElementById("concertForm");

let uploadedImage = null;

const formCheck = () => {
    return true;
    //const verify = 
}


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
                id: document.querySelector("input[name='location.id']").value,  // id location
                name: document.querySelector("input[name='location.name']").value,
                address: document.querySelector("input[name='location.address']").value,
                city: {
                    id: document.querySelector("input[name='location.city.id']").value,  // id city
                    name: document.querySelector("input[name='location.city.name']").value
                },
                region: {
                    id: document.querySelector("input[name='location.region.id']").value,  // id region
                    name: document.querySelector("input[name='location.region.name']").value
                }
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

