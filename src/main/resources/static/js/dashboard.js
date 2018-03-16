$(document).ready(function() {
	$("#amount").val(1000);	
	$("#fromSymbolSelect").change(function() {
		var fromSymbolval = $(".symbol option:selected").val();
		var toSymbolval = $(".toSymbol option:selected").val();
		var amount = $("#result").val();

		var fromSymDolarate = (1 / fromSymbolval);
		var toSymbolDollarRate = (1 / toSymbolval);

		var actualRate = (fromSymDolarate * toSymbolval);
		$('#actualLiveRate').text("Todays rate: " + actualRate);
	});
	getSerchedHistory();
});

$.ajax({
	url : 'get-realtime-exchange-rates',
	type : 'GET',
	cache : false,
	contentType : false,
	processData : false,
	headers : {
		"X-Auth-Token" : $.cookie("JSESSIONID")
	},
	beforeSend : function() {
		$('#imgLoaderDiv').show();
	},
	success : function(data) {
		console.log(data);
		symbolsArray = data;
		$('#imgLoaderDiv').hide();
		fillSymbolCombo();
	},
	error : function() {
		$('#imgLoaderDiv').hide();
	}
});

/**
 * @returns
 */
function fillSymbolCombo() {
	var symAndRate = "";
	//alert(roleNameArray.length);
	for (var i = 0; i < symbolsArray.length; i++) {
		symAndRate += "<option value='" + symbolsArray[i].liveRate + "'>"
				+ symbolsArray[i].symbolName + "</option>";
	}

	$('select[name="fromSymbolSelect"]').append(symAndRate);
	$('select[name="toSymbolSelect"]').append(symAndRate);
}
var amtToConvert = 0;
var actualRate = 0;

function convertCurrency() {
	var fromSymbolval = $(".symbol option:selected").val();
	var toSymbolval = $(".toSymbol option:selected").val();
	var amount = $("#result").val();

	var fromSymDolarate = (1 / fromSymbolval);
	var toSymbolDollarRate = (1 / toSymbolval);

	actualRate = (fromSymDolarate * toSymbolval);
	var conversionRate = (toSymbolDollarRate * fromSymbolval);
	$('#actualLiveRate').text("Todays rate: " + actualRate);
	
	amtToConvert =  ($("#amount").val() * actualRate);
	console.log(amtToConvert);
	$("#result").val(amtToConvert);
	saveResult();
}

function saveResult(){

	const logs = { 
	 "fromSymbolName":$(".symbol option:selected").text(),
	 "toSymbolName":$(".toSymbol option:selected").text(),
	 "toSymbolRate":actualRate,
	 "actualAmount":$("#amount").val(),
	 "convertedAmount":amtToConvert
   }

	$.ajax({
		type : 'POST',
		url : 'add-user-log',
		contentType : 'application/json',
		headers : {
			"X-Auth-Token" : $.cookie("JSESSIONID")
		},
		data : JSON.stringify(logs),
		beforeSend : function() {
			$('#imgLoaderDiv').show();
		},
		complete : function() {
			$('#imgLoaderDiv').hide();
		},
		success : function(data, statusText, xhr) {
			if ((xhr.status === 200) && (data.length !== 0)) {
				
			} else if (xhr.status === 226) {
				
			} else if (xhr.status === 226) {
				
			}
		},
		error : function() {
		}
	});
	console.log(logs);
}

function getSerchedHistory(){
	$
	.ajax({
		type : 'GET',
		url : 'get-user-currency-history',
		headers : {
			"X-Auth-Token" : $.cookie("JSESSIONID"),
		},
		beforeSend : function() {
			$('#imgLoaderDiv').show();
		},
		complete : function() {
			$('#imgLoaderDiv').hide();
		},
		success : function(data) {
			console.log(data)
			table = $('#history')
					.DataTable(
							{
								"scrollX" : true,
								"scrollY" : "310px",
								data : data,
								order: [[ 3, "desc" ]],
								columns : [
								        {
											"data" : "fromSymbolName"
										},
										{
											"data" : "toSymbolName"
										},
										{
											"data" : "toSymbolRate"
										},
										{
											"data" : "actualAmount"
										},
										{
											"data" : "convertedAmount"
										},
										{
											"data":"creationTime",
											"render" : function(data) {
												const dtStart = new Date(
														data);
												const dtStartWrapper = moment(dtStart);
												return dtStartWrapper
														.format('MMMM Do YYYY');
											}
										}
								]
							});
		},
		error : function(jqXHR, textStatus, errorThrown) {
		}
	});
}

function logout() {
	$.ajax({
		type : 'GET',
		url : 'logout',
		contentType : 'application/json',
		success : function(data) {
			window.location = "/currency-exchange-rate-converter";
		},
		error : function(data) {
		}
	});
	document.cookie = 'JSESSIONID' + '=; expires=Thu, 01-Jan-70 00:00:01 GMT;'
	window.location = "/currency-exchange-rate-converter/";
}

$.ajax({
	type : 'GET',
	url : 'get-loggedin-user',
	contentType : 'application/json',
	headers : {
		"X-Auth-Token" : $.cookie("JSESSIONID")
	},
	beforeSend : function() {
		$('#imgLoaderDiv').show();
	},
	complete : function() {
		$('#imgLoaderDiv').hide();
	},
	success : function(data) {
		if (data != null) {
			console.log(data);
			setTimeout(function() {
				$("#currentUser").html(" " + data.firstName);
			}, 00);	
		} else {
		}
	},
	error : function(data) {
		document.cookie = 'JSESSIONID'
				+ '=; expires=Thu, 01-Jan-70 00:00:01 GMT;'
		window.location = "/currency-exchange-rate-converter/";
	}
});
