/**
 * 
 */

var inout_input_frm = document.inout_input_frm;
var inout_input_btn_input = inout_input_frm.inout_input_btn_input;
var inout_input_btn_cancle = inout_input_frm.inout_input_btn_cancle;

var io_combiList = document.querySelector('#io_combiList');
var io_contList = document.querySelector('#io_contList');

var io_combiList_input = document.querySelector('#io_combiList_input');
var io_contList_input = document.querySelector('#io_contList_input');
var io_fishList_input = document.querySelector('#io_fishList_input');
var io_input_amt = document.querySelector('#io_input_amt');

var contOptions = [];
var combiOptions = [];

var combiCode = "";
var contCode = "";

for(var i=0; i<io_contList.options.length; i++){
	contOptions[i] = io_contList.options[i].outerHTML;
}

for(var i=0; i<io_combiList.options.length; i++){
	combiOptions[i] = io_combiList.options[i].outerHTML;
}

io_combiList_input.onchange = function(){
	if(io_combiList_input.value == ""){
		for(var i=0; i<combiOptions.length; i++){
			io_combiList.innerHTML += combiOptions[i];
		}
		return;
	}else{
		combiCode = io_combiList_input.value.split('-')[1];
	}
	
	io_contList.innerHTML = "";
	for(var i=0; i<contOptions.length; i++){
		io_contList.innerHTML += contOptions[i];
	}
	
	
	var target = "";
	for(var i=0; i<io_contList.options.length; i++){
		var contCode = io_contList.options[i].value.split('-')[1];
		if(combiCode == contCode.substr(0,3)){
			target = io_contList.options[i].outerHTML;				
			console.log(target);
			break ;
		}
	}
	
	
	io_contList.innerHTML = "";
	io_contList.innerHTML = target;
	
}

io_contList_input.onchange = function(){
	if(io_contList_input.value == ""){
		for(var i=0; i<contOptions.length; i++){
			io_contList.innerHTML += contOptions[i];
		}
		return;
	}else {
		contCode = io_contList_input.value.split('-')[1];
	}

	io_combiList.innerHTML = "";
	for(var i=0; i<combiOptions.length; i++){
		io_combiList.innerHTML += combiOptions[i];
	}
	
	
	var target = "";
	for(var i=0; i<io_combiList.options.length; i++){
		var combiCode = io_combiList.options[i].value.split('-')[1];
		if(contCode.substr(0,3) == combiCode){
			target = io_combiList.options[i].outerHTML;
			console.log(target);
			break;
		}
	}
	
	io_combiList.innerHTML = "";
	io_combiList.innerHTML = target;
	
}




inout_input_btn_input.onclick = function(){
	if(contCode.substr(0,3) != combiCode){
		alert('조합코드와 창고코드가 일치하지 않습니다');
		return;
	}
	inout_input_frm.action = "inoutInput.do";
	inout_input_frm.submit();
	
	io_combiList_input.value = "";
	io_contList_input.value = "";
	io_fishList_input.value = "";
	io_input_amt.value = "";
	
}

inout_input_btn_cancle.onclick = function(){
	location.href="index.jsp";
	
}