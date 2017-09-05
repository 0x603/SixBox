/**
 * Created by zc on 2017/9/5.
 */
$(function () {
    // 个人信息面板的显示
    $(".personalInfo>a").click(function(event){
        $(this).siblings("div").css("display","block");
        event.stopPropagation();
    });
    // 个人信息面板的隐藏
    $(document).click(function(){
        $(".info-panel").css("display","none");
    });
});