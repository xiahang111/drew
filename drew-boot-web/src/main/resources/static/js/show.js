var articleId = "";


$.ajax({
    type: 'HEAD', // 获取头信息，type=HEAD即可
    url: window.location.href,
    async: false,
    success: function (data, status, xhr) {
        articleId = xhr.getResponseHeader("articleId");
    }
});

//通过文章id请求文章信息
$.ajax({
    type:'post',
    url:'/article/getArticleById',
    dataType:'json',
    async:false,
    data:{
        articleId : articleId,
    },
    success:function (data1) {
        if(data1.status == "200"){
            var data = data1['data'];
            alert("解析开始" + data['articleHeadline']);
            var html_headline = '<a href="article.html">'+data['articleHeadline']+'</a>';
            $('#article-headline').append(html_headline);

            var html_article_meta_time = '<time class="time" data-toggle="tooltip" data-placement="bottom" title="时间：2016-1-4 10:29:39"><i class="glyphicon glyphicon-time"></i> '+data['articleDate']+'</time>';
            $('#article-meta-time').append(html_article_meta_time);

            var html_article_meta = '<span class="item article-meta-source" data-toggle="tooltip" data-placement="bottom" title="来源：德鲁大叔">'+
                '<i class="glyphicon glyphicon-globe"></i> 德鲁大叔的博客</span>'+
                '<span class="item article-meta-category" data-toggle="tooltip" data-placement="bottom" title="栏目：'+data['articleCategoryName']+'"><i class="glyphicon glyphicon-list"></i> <a href="program" title="">'+data['articleCategoryName']+'</a></span>'+
                '<span class="item article-meta-views" data-toggle="tooltip" data-placement="bottom" title="查看：'+data['articleVisitor']+'"><i class="glyphicon glyphicon-eye-open"></i> 共'+data['articleVisitor']+'次浏览</span>'+
                '<span class="item article-meta-comment" data-toggle="tooltip" data-placement="bottom" title="评论：'+data['articleCommentsNum']+'"><i class="glyphicon glyphicon-comment"></i> '+data['articleCommentsNum']+'条评论</span>'
            $('#article-meta').append(html_article_meta);

            var html_article_content = data['articleCommentsNum'];
            $('#article-content').text(html_article_meta);
        } else {
            $('.content').html('');
            var error = $('<div class="article"><div class="zhy-article-top"><div class="error">' +
                '<img src="https://zhy-myblog.oss-cn-shenzhen.aliyuncs.com/static/img/register_success.jpg">' +
                '<p>没有找到这篇文章哦</p>' +
                '<p>可能不小心被博主手残删掉了吧</p>' +
                '<div class="row">' +
                '<a href="/">返回首页</a>' +
                '</div>' +
                '</div></div></div>');
            $('.content').append(error);
        }
    },
    error:function () {

    }
});

//填充文章
function putInArticle(data) {

    var html_headline = '<a href="article.html">'+data['articleHeadline']+'</a>';
    $('#article-headline').append(html_headline);

    var html_article_meta_time = '<time class="time" data-toggle="tooltip" data-placement="bottom" title="时间：2016-1-4 10:29:39"><i class="glyphicon glyphicon-time"></i> '+data['articleDate']+'</time>';
    $('#article-meta-time').append(html_article_meta_time);

    var html_article_meta = '<span class="item article-meta-source" data-toggle="tooltip" data-placement="bottom" title="来源：德鲁大叔">'+
        '<i class="glyphicon glyphicon-globe"></i> 德鲁大叔的博客</span>'+
        '<span class="item article-meta-category" data-toggle="tooltip" data-placement="bottom" title="栏目：'+data['articleCategoryName']+'"><i class="glyphicon glyphicon-list"></i> <a href="program" title="">'+data['articleCategoryName']+'</a></span>'+
        '<span class="item article-meta-views" data-toggle="tooltip" data-placement="bottom" title="查看：'+data['articleVisitor']+'"><i class="glyphicon glyphicon-eye-open"></i> 共'+data['articleVisitor']+'次浏览</span>'+
        '<span class="item article-meta-comment" data-toggle="tooltip" data-placement="bottom" title="评论：'+data['articleCommentsNum']+'"><i class="glyphicon glyphicon-comment"></i> '+data['articleCommentsNum']+'条评论</span>'
    $('#article-meta').append(html_article_meta);

    var html_article_content = data['articleCommentsNum'];
    $('#article-content').text(html_article_meta);
}

