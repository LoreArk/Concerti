const submitBtn = document.getElementById("concertSubmit");
const concertForm = document.getElementById("concertForm");

const formCheck = () => {
    return true;
    //const verify = 
}

concertForm.addEventListener("submit", event => {
    event.preventDefault();
    if(formCheck()) {
        const formData = new FormData(concertForm);
        fetch
        (
            "/reserved",
            {
                method: "POST",
                body: formData
            }
        )
        .then(text => {
            if (text === "Operazione Eseguita") {
                window.location.href = "/reserved";
            }
        })
        .catch(error => console.log(error));
    }
});