
// search Flight By Source DeSTINATION

var searchFlightForm = document.getElementById('searchFlight')

searchFlightForm.addEventListener('submit', function(e){
    e.preventDefault()
    console.log('Clicked on login...')
    searchFlightByDestSource()
//    document.getElementById('form-button').classList.add("hidden");

})
var searchFlightData = {
        'Source':null,
        'Destination':null,
        'TravelDate': null
    }

//var Auth_Token

function searchFlightByDestSource(){

    searchFlightData.Source = searchFlightForm.source.value
    searchFlightData.Destination = searchFlightForm.dest.value
    searchFlightData.TravelDate = searchFlightForm.TravelDate.value

    console.log(searchFlightData)
    

    }


// search Flight By Flight Number
var searchFlightNumberForm = document.getElementById('searchFlightNo')

searchFlightNumberForm.addEventListener('submit', function(e){
    e.preventDefault()
    console.log('Clicked on login...')
    searchFlight()
//    document.getElementById('form-button').classList.add("hidden");

})
var FlightformData = {
        'trNo':null,
        'TravelDate': null
    }

//var Auth_Token

function searchFlight(){

    FlightformData.trNo = searchFlightNumberForm.trNo.value
    FlightformData.TravelDate = searchFlightNumberForm.jdate.value

	var trNo=FlightformData.trNo
	console.log(typeof(trNo),"Type of TrNo")
	window.localStorage.setItem("travelDate",FlightformData.TravelDate);
	window.localStorage.setItem("flightNo",FlightformData.trNo);
    var url = "http://localhost:8080/flight/getFlight/"+trNo

    console.log(FlightformData)
    window.location.href=url
    
    
}