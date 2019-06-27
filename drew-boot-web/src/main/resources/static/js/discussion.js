/*本站留言*/
$(function(){
    $("#comment-submit").click(function(){
        var commentContent = $("#comment-textarea");
        var commentButton = $("#comment-submit");
        var promptBox = $('.comment-prompt');
        var promptText = $('.comment-prompt-text');
        var articleid = $('.articleid').val();
        promptBox.fadeIn(400);
        if(commentContent.val() === ''){
            promptText.text('请留下您的评论');
            return false;
        }
        commentButton.attr('disabled',true);
        commentButton.addClass('disabled');
        promptText.text('正在提交...');
        $.ajax({
            type:"POST",
            url:"/discussion/add",
            //url:"/Article/comment/id/" + articleid,
            data:"discussion=" + replace_em(commentContent.val()),
            cache:false, //不缓存此页面
            success:function(data){
                promptText.text('评论成功!');
                commentContent.val(null);
                $(".commentlist").fadeIn(300);
                /*$(".commentlist").append();*/
                commentButton.attr('disabled',false);
                commentButton.removeClass('disabled')
                window.location.reload();
            }
        });
        /*$(".commentlist").append(replace_em(commentContent.val()));*/
        promptBox.fadeOut(100);
        return false;
    });
});

//对文章内容进行替换
function replace_em(str){
    str = str.replace(/\</g,'&lt;');
    str = str.replace(/\>/g,'&gt;');
    str = str.replace(/\[em_([0-9]*)\]/g,'<img src="/static/images/arclist/$1.gif" border="0" />');
    return str;
}

function publish_cmt() {
    cmt_text = $('#J_Textarea').val();
    alert(cmt_text);
    jQuery.ajax({
        url: '/getConmts',
        type: 'post',
        data: {},
        success: function (args) {
            console.log(args);
            jQuery(ths).parent().next().children('.loading-ico-top').css('display', 'none');
            jQuery(ths).parent().next().children('.conment-list').css('display', 'block');
        }
    })

}

function replay_show(ths) {
    if ($(ths).parent().next().hasClass('hidden')) {
        $(ths).parent().next().removeClass('hidden');
        $(ths).text('收起');
        $(ths).addClass('operate-visited')

    } else {
        $(ths).parent().next().addClass('hidden');
        $(ths).text('回复');
        $(ths).removeClass('operate-visited')
    }

}

function cmt_reply(ths) {
    alert($(ths).attr('cmt_id'));
    console.log($(ths).prev().children('.reply-box-textarea').val())

}

$(document).ready(function () {

    /*获取评论并展示*/
    $.getJSON('/discussion/getAll', function (result) {

        var data = result['data'];

        var html_discussion = '';

        $.each(result["data"], function (i, item) {

            html_discussion += '<div class="comment" comment-id="">' +
                '<div class="common-avatar J_User" data-userid="15850091">'+
                '<img src="'+item['avatarUrl']+'" width="100%" height="100%"/></div>'+
                '<div class="comment-block" comment-id="J_CommentBlock6337232328259750900">'+
                '<p class="comment-user"><span class="comment-username J_User" data-userid="15850091"> '+item['nickName']+' </span> <span class="comment-time">'+item['createTime']+'</span></p>'+
                '<div class="comment-content J_CommentContent">'+item['discussion']+'</div>'+
                '<div class="comment-operate J_CommentOperate clearfix" data-targetid="2234842266" data-id="6337232328259750900">'+
                '<span class="J_Vote comment-operate-item comment-operate-up">赞<i>16</i></span>'+
                '<span class="J_Reply comment-operate-item comment-operate-reply J_ReplyVisited" onclick="replay_show(this);" id="J_Reply6337232328259750900">匿名回复</span></div>'+
                '<div class="reply-box J_ReplyBox hidden" id="J_ReplyBox6337232328259750900">'+
                '<div class="reply-box-block"> <textarea class="reply-box-textarea J_ReplyTextArea"></textarea></div>'+
                '<div class="reply-box-btn J_ReplyBtn" cmt_id="123" id="J_ReplyBtn" onclick="cmt_reply(this);" data-targetid="2234842266" data-id="6337232328259750900" data-nick="" data-userid="">回复</div>'+
                '</div>'+
                '</div>'+
                '</div>'+
                ''
        });
        html_discussion += '<div class="quotes"><span class="disabled">首页</span><span class="disabled">上一页</span><a class="current">1</a><a href="">2</a><span class="disabled">下一页</span><span class="disabled">尾页</span></div>';

        $('#postcomments').append(html_discussion);

    });
});