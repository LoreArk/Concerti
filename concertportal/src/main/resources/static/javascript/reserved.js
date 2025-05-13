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
    if(formCheck()) {
        const formData = Object.fromEntries(new FormData(concertForm).entries());
        
        fetch("/reserved", {
            method: "POST",
            headers: {"Content-Type": "application/json"},
            body: JSON.stringify(formData)
        })
        .then(response => {
            return response.text().then(text => {
                if(response.status === 200 && text === "Operazione Eseguita") {
                window.location.href = "/reserved";
                }
            })
        })
        .catch(error => console.log(error))
  
    }
});

