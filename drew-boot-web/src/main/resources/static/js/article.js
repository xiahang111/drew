var articleId = "";
$(document).ready(function () {

    $.ajax({
        type: 'HEAD', // 获取头信息，type=HEAD即可
        url: window.location.href,
        async: false,
        success: function (data, status, xhr) {
            articleId = xhr.getResponseHeader("articleId");
        }
    });

    $.getJSON('/article/getArticleById', {articleId: articleId}, function (result) {

        var data = result['data'];

        var html_headline = '<a href="article.html">' + data['articleHeadline'] + '</a>';
        $('#article-headline').append(html_headline);

        var html_article_meta_time = '<time class="time" data-toggle="tooltip" data-placement="bottom" title="时间：2016-1-4 10:29:39"><i class="glyphicon glyphicon-time"></i> ' + data['articleDate'] + '</time>';
        $('#article-meta-time').append(html_article_meta_time);

        var html_article_meta = '<span class="item article-meta-source" data-toggle="tooltip" data-placement="bottom" title="来源：德鲁大叔">' +
            '<i class="glyphicon glyphicon-globe"></i> 德鲁大叔的博客</span>' +
            '<span class="item article-meta-category" data-toggle="tooltip" data-placement="bottom" title="栏目：' + data['articleCategoryName'] + '"><i class="glyphicon glyphicon-list"></i> <a href="program" title="">' + data['articleCategoryName'] + '</a></span>' +
            '<span class="item article-meta-views" data-toggle="tooltip" data-placement="bottom" title="查看：' + data['articleVisitor'] + '"><i class="glyphicon glyphicon-eye-open"></i> 共' + data['articleVisitor'] + '次浏览</span>' +
            '<span class="item article-meta-comment" data-toggle="tooltip" data-placement="bottom" title="评论：' + data['articleCommentsNum'] + '"><i class="glyphicon glyphicon-comment"></i> ' + data['articleCommentsNum'] + '条评论</span>'
        $('#article-meta').append(html_article_meta);

        var html_article_content = data['articleContent'];
        $('#article-content').append(html_article_content);

    });

    /*获取评论并展示*/
    $.getJSON('/comment/getByArticleId', {articleId: articleId}, function (result) {
        var data = result['data'];

        var html_comment = ''

        $.each(result["data"], function (i, item) {


            html_comment += '<li class="comment-content"><span class="comment-f">#'+item['floor']+'</span>'+
                '<div class="comment-avatar"><img class="avatar" src="../static/images/icon/icon.png" alt="" /></div>'+
                '<div class="comment-main">'+
                '<p>来自<span class="address">地球村</span>的用户<span class="time">('+item['commentDate']+')</span><br />'+item['comment']+'</p>'+
                '</div></li>'

        });

        $('.commentlist').append(html_comment);
    });

});

