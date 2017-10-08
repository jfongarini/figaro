function getToday(){
	var d = new Date(),
	month = '' + (d.getMonth() + 1),
	day = '' + d.getDate(),
	year = d.getFullYear();	
	if (month.length < 2) month = '0' + month;
	if (day.length < 2) day = '0' + day;	
	return [year, month, day].join('-');
}

function getStringMonth(date){
	month = '' + (date.getMonth() + 1),
	day = ''
	year = date.getFullYear();
	if (month.length < 2) month = '0' + month;	
	return [year, month, day].join('-');

}

function getStringDate(date){
	month = '' + (date.getMonth() + 1),
	day = '' + date.getDate(),
	year = date.getFullYear();
	if (month.length < 2) month = '0' + month;
	if (day.length < 2) day = '0' + day;
	return [year, month, day].join('-');

}

function stringToDate(date){
	return new Date(date + " 00:00:00");
}

function openModal(){
	$('#modal').addClass("modal-on");
	$('#content').addClass("blur");
	$('#menu').addClass("blur");
	$('#focus').focus();
};

function closeModal(){
	$('#modal').removeClass("modal-on");
	$('#content').removeClass("blur");
	$('#menu').removeClass("blur");
};


function openModal(modal){
	$('#'+modal).addClass("modal-on");
	$('#content').addClass("blur");
	$('#menu').addClass("blur");
	$('#focus').focus();
};


function closeModal(modal){
	$('#'+modal).removeClass("modal-on");
	$('#content').removeClass("blur");
	$('#menu').removeClass("blur");
};


var app = angular.module('figaro', []);