/**
 * 
 */
 
function view(idx){
   document.inout_list_page_frm.action = "fish.do?job=ioSelect&idx="+idx;
   document.inout_list_page_frm.submit();   
}


  var inout_serach_frm = document.inout_search_frm;
 if(inout_serach_frm!=null){
   inout_serach_frm.inout_search_btn_search.addEventListener('click', function(){
      inout_serach_frm.io_combiName.value=inout_serach_frm.io_combiName.value.split('-')[0];
      inout_serach_frm.io_contName.value=inout_serach_frm.io_contName.value.split('-')[0];
   
      inout_serach_frm.action='result.jsp?job=stats_select';
      inout_serach_frm.submit();
   });
}

var inout_stats_page_frm = document.inout_stats_page_frm;
var inout_list_page_frm = document.inout_list_page_frm;

if(inout_list_page_frm!=null){
   inout_list_page_frm.inout_list_button.addEventListener('click', function(){
      inout_list_page_frm.nowPage.value = 1;   
      inout_list_page_frm.action='fish.do?job=io_select';
      inout_list_page_frm.submit();
   })
}


function IoStatsMovePage(nowPage){
   inout_stats_page_frm.nowPage.value = nowPage;
   inout_stats_page_frm.action = 'fish.do?job=stats_select&page=move'+nowPage;
   inout_stats_page_frm.submit();   
}
function ioListMovePage(nowPage){
   inout_list_page_frm.nowPage.value = nowPage;
   inout_list_page_frm.action = 'fish.do?job=io_select&page=move'+nowPage;
   inout_list_page_frm.submit();   
}




/* 재고조회 */
const stock_list_search = document.stock_list_search;
if(stock_list_search != null){
   stock_list_search.stock_list_button.addEventListener('click', function(){
      stock_list_search.action='stock_list.do?job=stock_select';
       stock_list_search.nowPage.value = 1;
      stock_list_search.submit();
   })
   
   
    function movePage(page){
      stock_list_search.action = 'stock_list.do?job=stock_select&page=move'+page;
       stock_list_search.nowPage.value = page;
       stock_list_search.submit();
   }
}

/* 재고변동표 */
var stock_change_frm = document.stock_change_frm;
if(stock_change_frm != null){
   stock_change_frm.stock_change_btn_search.onclick = function(){
      stock_change_frm.action = 'stock.do?job=change_select';
      stock_change_frm.submit();
   }
}



/* 토글 버튼  */
const toggle = document.getElementById("toggle");
if(toggle  !=null){
	var link =  document.location.href;
	 var css_id = document.getElementById("css_id");
	function toggleFunc(){
		
		location.href="semi/mode_result.jsp?mode="+toggle.value+"&before="+link
	}
}
 


 