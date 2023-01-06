// register API call
console.log("home page loaded")


var regform = document.getElementById('regform')
regform.addEventListener('submit', function(e){
    e.preventDefault()
    console.log('Clicked on register ...')

     newpass = regform.passwd.value
     cnfPass = regform.cnfpasswd.value

     if (newpass == cnfPass ){

         Bool = CheckLength(newpass)
         if (Bool){
             register()
         }
         else{
         alert('Panjang password paling sedikit harus 8 karakter')
         }

     }
     else{
     alert("Password tidak sesuai, silahkan coba lagi")
     }

//    document.getElementById('form-button').classList.add("hidden");

})


// Change Password API Call
function CheckLength(NewPassword){

 if (NewPassword.length  < 8){

     return false;
 }
 else{
     return true;
 }
}


function register(){

    const userdata = {};
    userdata["email"] = regform.Email.value;
        //userFormData["email"] = "sachin6734@gmisl.com"
        //userFormData["password"] = "password"
   userdata["password"] = regform.passwd.value;
       

    console.log('userFormData ka  data  :', userdata)

    var urlreg = "http://localhost:8080/register"
   
    fetch(urlreg, {
        method:'POST',
        headers:{
            "Content-Type": "application/json",
            'dataType': 'json'
            
        },
        body:JSON.stringify(userdata)
        

    })
    .then((response) => response.json())
    .then((data) =>CheckRegister(data))
    
    
    function CheckRegister(data){
		console.log(data)
		if (data == 'OK'){
			window.location.href = "http://localhost:8080"
		}
		else{
			alert("Email telah terdaftar")
		}
	}

}


var searchFlightForm = document.getElementById('searchFlight')

searchFlightForm.addEventListener('submit', function(e){
    e.preventDefault()
    console.log('Clicked on login...')
    searchFlightByDestSource()

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
    
    todayDate = new Date();
    selectedDate = new Date(searchFlightData.TravelDate);
    
    if(todayDate > selectedDate){
	alert(" Select future date only ")
    }
    else{
    
        window.localStorage.setItem("travelDate",searchFlightData.TravelDate);
        console.log(searchFlightData)
        var urlflightsearch = "http://localhost:8080/searchFlight/"+searchFlightData.Source+"/"+searchFlightData.Destination
        window.location.href=urlflightsearch
    }
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
	
	todayDate = new Date();
    selectedDate = new Date(FlightformData.TravelDate);
    
    if(todayDate > selectedDate){
	alert(" Select future date only ")
    }
    else{
	
	window.localStorage.setItem("travelDate",FlightformData.TravelDate);
	window.localStorage.setItem("flightNo",FlightformData.trNo);
    var url = "http://localhost:8080/flight/getFlight/"+trNo

    console.log(FlightformData)
    window.location.href=url
    
  }  
}