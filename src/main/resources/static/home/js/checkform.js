
function checkform_pinglun() {
    var text = $("#txtContent").val();
    if($(".user_name").val()==""){
        layer.alert('请先登录后评论', {icon:2},function(){
            layer.closeAll();
            $("#login").css("display","block");
        }); 
        return false;
    }else if(text==""){
        layer.alert('评论的内容不能为空喔！', {icon: 2}); 
        return false;
    }else if(text.length<6){
        layer.alert('评论内容6个汉字以上哦！', {icon: 2});
        return false;
    } else{
        return true;
    }
}


// 回复评论
function huifu_form(cid,pid,obj){
    if($(obj).parent().parent().find(".huifu").length<=0){
        $(".huifu").remove();
        var email = $(".feed-email>input").val();
        var str = "<div class='huifu'><input class='c_id' type='hidden' value='"+cid+"' /><input class='p_id' type='hidden' value='"+pid+"' /><span><i class='fa fa-times'></i></span><textarea class='cont' cols='100' rows='3' name='huifu' class='' datatype='*' sucmsg=' ' placeholder='请先登录后评论...'></textarea><input class='huifu_email' name='emall' type='text'  id='u_mail' value='"+email+"' size='16' placeholder='接收恢复的email' /><button class='huifu_btn'>评论</button></div>";
        $(obj).parent().parent().append(str);
        $(obj).parent().parent().find(".huifu .huifu_btn").click(function(){
            var cid = $(this).parent().find(".c_id").val();
            var pid = $(this).parent().find(".p_id").val();
            var cont = $(this).parent().find("textarea").val();
            var email = $(this).parent().find(".huifu_email").val();
            if($(".user_name").val()==""){
                layer.alert('请先登录后评论', {icon:2},function(){
                    layer.closeAll();
                    $("#login").css("display","block");
                }); 
            }else if(cont==""){
                top.layer.msg("请输入你要评论的内容哦！",{icon: 3});
            }else{
                var ppd = "#pid-"+ pid;
                $.ajax({
                    type: "POST",
                    url: "/index.php/Home/Right_Public/liuyan",
                    data: {
                        "cont" : cont,
                        "c_id" : cid,
                        "parent_id" : pid,
                        "emall" : email,
                    },
                    dataType: "json",
                    success: function(data){
                        var cont = "<ul class='re-comment'><li style='border-left:none;'><div class='admin-ava'><img src='"+data.ioc+"' alt='"+data.name+"回复：' title='"+data.name+"回复：' class='img-circle'><a href='javascript:;'></a></div><div class='re-info ahuifu'><span><img src='/APP/Home/Public/images/ok.png'><a href='javascript:;' title='"+data.name+"'>"+data.name+"</a><time>"+data.createtime+"</time> 回复 <a href='javascript:;' title=''>@"+data.toname+"</a>              <a onclick='huifu_form("+cid+","+pid+",this)' class='ahuifu' style='cursor:pointer;color:#ff6700' href='javascript:;'>回复</a></span><div class=' re-content'>"+data.cont+"</div><!--回复表单--></div></li></ul>";
                        $(ppd).append(cont);
                        $(".huifu").slideUp(100,function(){
                            $(".huifu").remove();
                        });
                    }
                });
            }
        });
    }
    $(".fa-times").click(function(){
        $(this).parent().parent().remove();
    });
}




/*function checkform()
{
     if($("#u_name").val()=="" || $("#u_name").val().length<2)
          {
              layer.msg('用户名不能为空或者昵称太短了喔', {icon: 2}); 
               return false;
          }

if($("#txaArticle").val()=="" || $("#txaArticle").val().length<2)
          {
              layer.msg('亲啥都没写喔，或者内容太少啦！', {icon: 2}); 
               return false;
          }
		  
		if($("#txaArticle").val()!="" && $("#u_name").val()!="")
          {
              layer.alert('恭喜提交成功！待管理员审核通过后显示!', {icon: 1}); 
               return true;
          }
		  
     else
     {
          return true;
     }
}
*/