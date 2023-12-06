// Get the form data from an HTML form with id "myForm"
const body = document.getElementById("body");
const submitButton = document.getElementById("submitButton");
const form = document.getElementById("form");
const div = document.getElementById("resultDiv");

// URL where you want to send the request
const url = "/currency";
body.addEventListener("click", (event) => {
    if (submitButton.contains(event.target)) {

        const currency = {
            name: document.getElementById("input").value, // Replace with the updated name
        }
        event.preventDefault()
        console.log(currency)
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
            .then(data => {
                // Handle the response data here
                form.remove();
                div.innerHTML = "Done";
            })
            .catch(error => {
                div.innerHTML = error.message;
            });
    }

})