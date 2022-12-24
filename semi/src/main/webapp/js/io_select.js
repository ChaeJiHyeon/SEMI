/**
 * 
 */
/**
 * 
 */

var inout_input_frm = document.inout_search_frm;
var inout_search_frm_type_detail = document.querySelector('#inout_search_frm_type_detail');

var io_combiList = document.querySelector('#io_combiName_list');
var io_contList = document.querySelector('#io_contName_list');

var io_combiList_input = document.querySelector('#io_combiName_list_input');
var io_contList_input = document.querySelector('#io_contName_list_input');

var io_fishList_input = document.querySelector('#io_fishName_list_input');
io_fishList_input.readOnly=true;


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



function input_check_disable(obj){
   console.log("바뀜");
   if(obj.value=="품목별"){
      io_fishList_input.value="";
      io_fishList_input.setAttribute("id", "io_fishName_list_input");
      io_fishList_input.readOnly=true;
   }else{
      io_fishList_input.setAttribute("id", "io_fishName_list_inputtype");
      io_fishList_input.readOnly=false;
   }
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
/**
 * 
 */
/**
 * 
 */

var inout_input_frm = document.inout_search_frm;
var inout_search_frm_type_detail = document.querySelector('#inout_search_frm_type_detail');

var io_combiList = document.querySelector('#io_combiName_list');
var io_contList = document.querySelector('#io_contName_list');

var io_combiList_input = document.querySelector('#io_combiName_list_input');
var io_contList_input = document.querySelector('#io_contName_list_input');

var io_fishList_input = document.querySelector('#io_fishName_list_input');
io_fishList_input.readOnly=true;


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



function input_check_disable(obj){
   console.log("바뀜");
   if(obj.value=="품목별"){
      io_fishList_input.value="";
      io_fishList_input.setAttribute("id", "io_fishName_list_input");
      io_fishList_input.readOnly=true;
   }else{
      io_fishList_input.setAttribute("id", "io_fishName_list_inputtype");
      io_fishList_input.readOnly=false;
   }
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
