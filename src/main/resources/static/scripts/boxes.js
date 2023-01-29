document.getElementById("luggageCost").hidden = true;
document.getElementById("registrationCost").hidden = true;
document.getElementById("insuranceCost").hidden = true;

const body = document.querySelector("body")
body.addEventListener('change', event => {
    if (event.target.type === 'checkbox') {
        const registrationPrice = 50
        const luggagePrice = 2500
        const insurancePrice = 600
        const luggageCount = document.querySelectorAll('input[id="luggage"]:checked').length ?? 0
        const registrationCount = document.querySelectorAll('input[id="registration"]:checked').length * luggageCount ?? 0
        const insuranceCount = document.querySelectorAll('input[id="insurance"]:checked').length * luggageCount ?? 0
        const ticketCost = Number(document.getElementById("ticketCost").textContent)
        const luggageCost = luggagePrice * luggageCount
        const registrationCost = registrationPrice * registrationCount
        const insuranceCost = insurancePrice * insuranceCount
        document.getElementById("luggageSum").textContent = `${luggageCost}`
        document.getElementById('luggageCount').textContent = `${luggageCount}`
        document.getElementById("registrationSum").textContent = `${registrationCost}`
        document.getElementById('registrationCount').textContent = `${registrationCount}`
        document.getElementById("insuranceSum").textContent = `${insuranceCost}`
        document.getElementById('insuranceCount').textContent = `${insuranceCount}`
        document.getElementById('generalSum').textContent = `${ticketCost +
        luggageCost + registrationCost + insuranceCost}`
        document.getElementById("luggageCost").hidden = luggageCost === 0;
        document.getElementById("registrationCost").hidden = registrationCost === 0;
        document.getElementById("insuranceCost").hidden = insuranceCost === 0;
    }
})
