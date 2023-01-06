

if(data["logStatus"] == true){
			/*document.getElementById("logstat").innerHTML=data["email"];*/
			document.getElementById("logoutbtn").style.visibility = 'visible';
			document.getElementById("signupbtn").style.visibility = 'hidden';
			document.getElementById("logInbtn").style.visibility = 'hidden';
			localStorage.setItem("logStatus",true)
			localStorage.setItem("email",data["email"])
			localStorage.setItem("admin",data["admin"])
			if(data["admin"] == false){
				document.getElementById("trAction").style.visibility = 'hidden';
			}
		}