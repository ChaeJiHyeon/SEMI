/**
 * 
 */

var inout_modify_frm = document.inout_modify_frm;

var modify_btn_modify = inout_modify_frm.inout_modify_btn_modify;
var modify_btn_delete = inout_modify_frm.inout_modify_btn_delete;
var modify_btn_cancle = inout_modify_frm.inout_modify_btn_cancle;

var io_combiList = document.querySelector('#io_combiList');
var io_contList = document.querySelector('#io_contList');

var io_combiList_modify = document.querySelector('#io_combiList_modify');
var io_contList_modify = document.querySelector('#io_contList_modify');

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

io_combiList_modify.onchange = function(){
	if(io_combiList_modify.value == ""){
		for(var i=0; i<combiOptions.length; i++){
			io_combiList.innerHTML += combiOptions[i];
		}
		return;
	}else{
		combiCode = io_combiList_modify.value.split('-')[1];
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

io_contList_modify.onchange = function(){
	if(io_contList_modify.value == ""){
		for(var i=0; i<contOptions.length; i++){
			io_contList.innerHTML += contOptions[i];
		}
		return;
	}else {
		contCode = io_contList_modify.value.split('-')[1];
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

modify_btn_modify.onclick = function(){
	inout_modify_frm.action = 'fish.do?job=ioUpdateR';
	inout_modify_frm.submit();
}

modify_btn_delete.onclick = function(){
	inout_modify_frm.action = 'fish.do?job=ioDelete';
	inout_modify_frm.submit();
}

modify_btn_cancle.onclick = function(){
	inout_modify_frm.action = 'fish.do?job=io_select';
	inout_modify_frm.submit();
	
}