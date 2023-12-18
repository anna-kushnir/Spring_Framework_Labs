// Get the form data from an HTML form with id "myForm"
const body = document.getElementById("body");
const submitButton = document.getElementById("submitButton");
const form = document.getElementById("form");
const div = document.getElementById("resultDiv");

// URL where you want to send the request
const url = "/currencies";
body.addEventListener("click", (event) => {
    if (submitButton.contains(event.target)) {

        const currency = {
            name: document.getElementById("input").value, // Replace with the updated name
        }
        event.preventDefault()

        if (validateISOCode(currency.name)) {
            // Create a fetch request
            fetch(url, {
                method: "POST", // You can change this to "GET" or other HTTP methods
                headers: {
                    'Content-Type': 'application/json',
                },
                body: JSON.stringify(currency)
            })
                .then(response => {
                    if (response.ok) {
                        return response.text(); // or response.json() if the server returns JSON
                    } else {
                        throw new Error(response.text());
                    }
                })
                .then(() => {
                    // Handle the response data here
                    form.remove();
                    div.innerHTML = "Done";
                })
                .catch(error => {
                    div.innerHTML = error.message;
                });
        }
    }

})


function validateISOCode(code) {
    const regex = /^[A-Z]{3}$/;
    if (regex.test(code)) {
        div.innerHTML = "";
        return true;
    } else {
        div.innerHTML = "ISO Code must be 3 capital letters";
        return false;
    }
}