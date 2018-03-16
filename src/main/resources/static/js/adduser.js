var roleNameArray = [];

$(document).ready(function() {
	$("#userRegister").submit(function(e) {
		console.log("adduser");
		e.preventDefault();
		submitForm();
	});
});

function submitForm() {
	const firstName = $("#firstName").val();
	const lastName = $("#lastName").val();
	const emailId = $("#email").val();
	const address = $("#address").val();
	const password = $("#pwd").val();
	const postcode = $("#postcode").val();
	const city = $("#city").val();
	const country = $("#country").val();
	const dob = $("#dob").val();

	const user = {
		"firstName" : firstName,
		"lastName" : lastName,
		"email" : emailId,
		"address" : address,
		"password" : password,
		"postcode":postcode,
		"city":city,
		"country":country,
		"dob":dob
	};

	const nameValidator = /^[a-zA-Z]+$/;
	const emailValidator = /^([\w-\.]+)@((\[[0-9]{1,3}\.[0-9]{1,3}\.[0-9]{1,3}\.)|(([\w-]+\.)+))([a-zA-Z]{2,4}|[0-9]{1,3})(\]?)$/;
	let msg = "";
	
	if (password.length === 0) {
		msg = msg + "Password cannot be blank <br/>";
	}
	
	if (firstName.length === 0) {
		msg = msg + "Firstname cannot be blank <br/>";
	}
	
	if (lastName.length === 0) {
		msg = msg + "LastName cannot be blank <br/>";
	}

	if (emailId.length === 0) {
		msg = msg + "Email cannot be blank <br/>";
	}

	if (address.length === 0) {
		msg = msg + "Address cannot be blank <br/>";
	}

	if (country.length === 0) {
		msg = msg + "Please select country <br/>";
	}


	if (dob.length === 0) {
		msg = msg + "Please select DOB <br/>";
	}

	if (password !== $("#password_two").val()) {
		msg = msg + "Password and confirm password must be same <br/>";
	}
	
	if(firstName.length && !nameValidator.test(firstName)){
		msg = msg + "First Name should only contain letters <br/>";
	}
	

	if(city.length && !nameValidator.test(city)){
		msg = msg + "City should only contain letters <br/>";
	}
	
	
	if(lastName.length && !nameValidator.test(lastName)){
		msg = msg + "Last Name should only contain letters <br/>";
	}
	
	if(emailId.length && !emailValidator.test(emailId)){
		msg = msg + "Please enter valid E-mail. i.e. user@mailserver.com <br/>";
	}
	
	if (!checkPwd(password)) {
		msg = msg
				+ "Password Contains  At least 8 characters , 1 small letter, 1 capital letter,  1 special character , 1 digit <br/>"
	}

	if (msg.length === 0) {
		addUser(user);		
	} else {
		showAlert(msg, "alert-danger", "alertContainer", "alertRow");
	}
}
function addUser(user){
	$
	.ajax({
		type : 'POST',
		url : 'add-user',
		contentType : 'application/json',
		data : JSON.stringify(user),
		beforeSend : function() {
			$('#imgLoaderDiv').show();
		},
		complete : function() {
			$('#imgLoaderDiv').hide();
		},
		success : function(data, statusText, xhr) {
			if ((xhr.status === 200) && (data.length !== 0)) {
				showAlert("User Added Successfully",
						"alert-success", "alertContainer",
						"alertRow");
				const delay = 3000;
				setTimeout(function() {
					window.location = "/currency-exchange-rate-converter";
				}, delay);
			} else if (xhr.status === 226) {
				showAlert("Error:User already exists",
						"alert-danger", "alertContainer",
						"alertRow");
			} else if (xhr.status === 226) {
				showAlert(
						"Warning : User is in-active. Ask admin to re-activate the user.",
						"alert-warning", "alertContainer",
						"alertRow");
			}
		},
		error : function() {

			showAlert("Error:Server error occur", "alert-danger",
					"alertContainer", "alertRow");
		}
	});
}
function checkPwd(str) {
	const ucase = new RegExp("[A-Z]+");
	const lcase = new RegExp("[a-z]+");
	const num = new RegExp("[0-9]+");
	const schar = /^[a-zA-Z0-9 ]*$/;

	if (str.length < 8) {
		return false;
	}

	if (ucase.test(str) === false) {
		return false;
	}

	if (lcase.test(str) === false) {
		return false;
	}

	if (num.test(str) === false) {
		return false;
	}

	if (schar.test(str)) {
		return false;
	}
	return true;
}

function isNumber(evt) {
    evt = (evt) ? evt : window.event;
    var charCode = (evt.which) ? evt.which : evt.keyCode;
    if (charCode > 31 && (charCode < 48 || charCode > 57)) {
        return false;
    }
    return true;
}