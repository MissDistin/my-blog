//关于评论相关的JS
// js动态加载表情
tuzkiNumber=1;
function getTuzki(obj){
    var tuzkiObj=$(obj).siblings('.b-tuzki');
    if(tuzkiNumber){
        tuzkiObj.show();
        var alt=['Kiss', 'Love', 'Yeah', '啊！', '背扭', '顶', '抖胸', '88', '汗', '瞌睡', '鲁拉', '拍砖', '揉脸', '生日快乐', '摊手', '睡觉', '瘫坐', '无聊', '星星闪', '旋转', '也不行', '郁闷', '正Music', '抓墙', '撞墙至死', '歪头', '戳眼', '飘过', '互相拍砖', '砍死你', '扔桌子', '少林寺', '什么？', '转头', '我爱牛奶', '我踢', '摇晃', '晕厥', '在笼子里', '震荡'];
        var str='';
        for (var i = 1; i < 41; i++) {
            var number=formatNum(i,4);
            str+='<img src="http://'+window.location.host+'/Public/emote/tuzki/t_'+number+'.gif" title="'+alt[i-1]+'" alt="雷小天博客">';
        };
        tuzkiObj.html(str);
        tuzkiNumber=0;
    }else{
        tuzkiObj.hide();
        tuzkiNumber=1;
    }
}

/**
 * 格式化数字为一个定长的字符串，前面补0
 * @param  int Source 待格式化的字符串
 * @param  int Length 需要得到的字符串的长度
 * @return int        处理后得到的数据
 */
function formatNum(Source,Length){
    var strTemp="";
    for(i=1;i<=Length-Source.toString().length;i++){
        strTemp+="0";
    }
    return strTemp+Source;
}

// 点击添加表情
$('html').on('click','.b-tuzki img', function(event) {
    var str=$(this).prop("outerHTML");
    $(this).parents('.b-box-textarea').eq(0).find('.b-box-content').focus();
    insertHtmlAtCaret(str);
    $(this).parents('.b-tuzki').hide();
    tuzkiNumber=1;
});

/**
 * 在textarea光标后插入内容
 * @param  string  str 需要插入的内容
 */
function insertHtmlAtCaret(str) {
    var sel, range;
    if(window.getSelection){
        sel = window.getSelection();
        if (sel.getRangeAt && sel.rangeCount) {
            range = sel.getRangeAt(0);
            range.deleteContents();
            var el = document.createElement("div");
            el.innerHTML = str;
            var frag = document.createDocumentFragment(), node, lastNode;
            while ( (node = el.firstChild) ) {
                lastNode = frag.appendChild(node);
            }
            range.insertNode(frag);
            if(lastNode){
                range = range.cloneRange();
                range.setStartAfter(lastNode);
                range.collapse(true);
                sel.removeAllRanges();
                sel.addRange(range);
            }
        }
    } else if (document.selection && document.selection.type != "Control") {
        document.selection.createRange().pasteHTML(str);
    }
}

function comment() {
    if ($("#u_name").val() == "" || $("#u_name").val().length < 2) {
        layer.msg('用户名不能为空或者昵称太短了喔', {icon: 2});
        return false;
    }

    if ($("#txaArticle").val() == "" || $("#txaArticle").val().length < 2) {
        layer.msg('亲啥都没写喔，或者内容太少啦！', {icon: 2});
        return false;
    }
    var content = $("#txaArticle").val();
    var author = $("#u_name").val();
    var mail = $("#mail").val();
    var url = $("#url").val();
    var type = '0';
    var cid = $("#chid").val();
    debugger
    $.ajax({
        type: "POST",
        url: "/comment",
        data: {
            "type" : type,
            "cid" : cid,
            "url" : url,
            "mail" : mail,
            "author" : author,
            "content" : content
        },
        dataType: "json",
        success: function(data){
            // var cont = "<ul class='re-comment'><li style='border-left:none;'><div class='admin-ava'><img src='"+data.ioc+"' alt='"+data.name+"回复：' title='"+data.name+"回复：' class='img-circle'><a href='javascript:;'></a></div><div class='re-info ahuifu'><span><img src='/APP/Home/Public/images/ok.png'><a href='javascript:;' title='"+data.name+"'>"+data.name+"</a><time>"+data.createtime+"</time> 回复 <a href='javascript:;' title=''>@"+data.toname+"</a>              <a onclick='huifu_form("+cid+","+pid+",this)' class='ahuifu' style='cursor:pointer;color:#ff6700' href='javascript:;'>回复</a></span><div class=' re-content'>"+data.cont+"</div><!--回复表单--></div></li></ul>";
            // $(ppd).append(cont);
            // $(".huifu").slideUp(100,function(){
            //     $(".huifu").remove();
            // });
            layer.alert('恭喜提交成功！待管理员审核通过后显示!', {icon: 1});
        }
    });

    if ($("#txaArticle").val() != "" && $("#u_name").val() != "") {
        layer.alert('恭喜提交成功！待管理员审核通过后显示!', {icon: 1});
        return true;
    }

    else {
        return true;
    }
}

// 发布留言
function feedback(){
    if ($("#u_name").val() == "" || $("#u_name").val().length < 2) {
        layer.msg('用户名不能为空或者昵称太短了喔', {icon: 2});
        return false;
    }

    if ($("#txaArticle").val() == "" || $("#txaArticle").val().length < 2) {
        layer.msg('亲啥都没写喔，或者内容太少啦！', {icon: 2});
        return false;
    }
    var content = $("#txaArticle").val();
    var author = $("#u_name").val();
    var mail = $("#mail").val();
    var url = $("#url").val();

    $.ajax({
        type: "POST",
        url: "/feedback",
        data: {
            "url" : url,
            "mail" : mail,
            "author" : author,
            "content" : content
        },
        dataType: "json",
        success: function(data){
            // var cont = "<ul class='re-comment'><li style='border-left:none;'><div class='admin-ava'><img src='"+data.ioc+"' alt='"+data.name+"回复：' title='"+data.name+"回复：' class='img-circle'><a href='javascript:;'></a></div><div class='re-info ahuifu'><span><img src='/APP/Home/Public/images/ok.png'><a href='javascript:;' title='"+data.name+"'>"+data.name+"</a><time>"+data.createtime+"</time> 回复 <a href='javascript:;' title=''>@"+data.toname+"</a>              <a onclick='huifu_form("+cid+","+pid+",this)' class='ahuifu' style='cursor:pointer;color:#ff6700' href='javascript:;'>回复</a></span><div class=' re-content'>"+data.cont+"</div><!--回复表单--></div></li></ul>";
            // $(ppd).append(cont);
            // $(".huifu").slideUp(100,function(){
            //     $(".huifu").remove();
            // });
            layer.alert('恭喜提交成功！待管理员审核通过后显示!', {icon: 1});
        }
    });

    if ($("#txaArticle").val() != "" && $("#u_name").val() != "") {
        layer.alert('恭喜提交成功！待管理员审核通过后显示!', {icon: 1});
        return true;
    }

    else {
        return true;
    }
}

// 回复评论
function reply(obj){
    var boxTextarea=$('.b-user-comment').find('.b-box-textarea');
    if(boxTextarea.length==1){
        boxTextarea.remove();
    }
    var aid=$(obj).attr('aid');
    var pid=$(obj).attr('pid');
    // var username=$(obj).attr('username');
    var check=$(obj).attr('childname');
    if(check==undefined){
        var username=$(obj).attr('username');
    }else{
        var username=check;
    }

    var str='<div class="b-box-textarea"><div class="b-box-content" contenteditable="true" onfocus="delete_hint(this)">请先登陆后回复评论</div><ul class="b-emote-submit"><li class="b-emote"><i class="fa fa-smile-o" onclick="getTuzki(this)"></i><input style="height:30px;margin-left:-4px;" class="form-control b-email" type="text" name="email" placeholder="接收回复的email地址" value="'+userEmail+'"><div class="b-tuzki"></div></li><li class="b-submit-button"><input type="button" value="评 论" aid="'+aid+'" pid="'+pid+'" username="'+username+'" onclick="comment(this)"></li><li class="b-clear-float"></li></ul></div>';
    var parentObj=$(obj).parents('.comment-info').eq(0).append(str);
}

// 删除提示和样式
function delete_hint(obj){
    var word=$(obj).text();
    if(word=='请先登陆后发表评论' || word=='请先登陆后回复评论'){
        $(obj).text('');
        $(obj).css('color', '#333');
    }
}
// 检查是不是自己评论自己,没用
function checkouid(pid){
    $.ajax({
        type:"POST",
        url:"http://www.100txy.com/Home/Index/checkouid",
        data:"pid="+pid,
        dataType:"json",
        success:function(data){
            return data.type;
        },
    });
}