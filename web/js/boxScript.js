$(function(){
    // 个人信息面板的显示
    $(".personalInfo>a").click(function(event){
        $(this).siblings("div").css("display","block");
        event.stopPropagation(); 
    });
    // 个人信息面板的隐藏
    $(document).click(function(){
            $(".info-panel").css("display","none");
    });
    // 按钮列表的显示与隐藏
    var trList=$(".table-content tr");
    $(trList).each(function(){
        $(this).hover(function(){
            if (countSelectedChechbox()==0) {$(this).find(".btn-group").css("visibility","visible");}
        },function(){
            $(this).find(".btn-group").css("visibility","hidden");
            }
        );
    });

    // 全选
    var selectAll=$(".table-fixed input[type='checkbox']");
    var checkboxList=$(".table-content input[type='checkbox']");

    $(checkboxList).each(function(){
        $(this).click(function(){
            $(this).parent()
            .siblings(".button-th")
            .find(".btn-group")
            .css("visibility","hidden");
        if (countSelectedChechbox()===0) {
            $(".none-item-action").css("display","block");
            $(".single-item-action").css("display","none");
        }else if (countSelectedChechbox()===1) {
            $(".none-item-action").css("display","none");
            $(".single-item-action").css("display","block");
        }else if (countSelectedChechbox()>1){
            $(".none-item-action").css("display","none");
            $(".single-item-action").css("display","block");
            $(".single-item-action").children().last().css("display","none");
        }
        });
    });
});

function countSelectedChechbox() {
   return $(".table-content input[type='checkbox']:checked").length;
}