// Get the form data from an HTML form with id "myForm"
const body = document.getElementById("body");
const submitButton = document.getElementById("submitButton");
const form = document.getElementById("form");
const div = document.getElementById("resultDiv");
const currentUrl = window.location.href;
const match = currentUrl.match(/\/currencies\/(\d+)\/edit/);
const currencyId = match[1];


// URL where you want to send the request
const url = "/currencies/" + currencyId;

body.addEventListener("click", (event) => {
    if (submitButton.contains(event.target)) {

        const currency = {
            id: currencyId, // Replace with the appropriate ID
            name: document.getElementById("input").value, // Replace with the updated name
        }
        event.preventDefault()
        console.log(currency)
        // Create a fetch request
        fetch(url, {
            method: "PUT", // You can change this to "GET" or other HTTP methods
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

})