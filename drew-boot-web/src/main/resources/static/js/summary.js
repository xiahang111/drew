
var categoryId = "";

$(document).ready(function () {

    $.ajax({
        type: 'HEAD', // 获取头信息，type=HEAD即可
        url: window.location.href,
        async: false,
        success: function (data, status, xhr) {
            categoryId = xhr.getResponseHeader("categoryId");
        }
    });

    $.getJSON('/article/getArticles',{categoryId:categoryId},function (result) {

        var html_article = '';


        $.each(result["data"],function (i,item) {

            html_article += '<article class="excerpt excerpt-1">' + '<a class="focus" href="article.html" title="">' +
                '<img class="thumb" data-original="http://localhost:10010/api/static/'+item['articleImgUrl']+'" src="http://localhost:10010/api/manage'+item['articleImgUrl']+'" alt=""></a>'+
                '<header><a class="cat" href="program">'+item['articleCategoryName']+'<i></i></a>' +
                '<h2><a href="article.html" title="">'+item['articleHeadline']+'</a></h2></header>'+
                '<p class="meta">' + '<time class="time"><i class="glyphicon glyphicon-time"></i> '+item['articleDate']+'</time>' +
                '<span class="views"><i class="glyphicon glyphicon-eye-open"></i> 共'+item['articleVisitor']+'人围观</span>'+
                '<a class="comment" href="article.html#comment"><i class="glyphicon glyphicon-comment"></i> '+item['articleCommentsNum']+'个不明物体</a></p>'+
                '<p class="note">可以用strtotime()把日期（$date）转成时间戳，再用date()按需要验证的格式转成一个日期，来跟$date比较是否相同来验证这个日期的格式是否是正确的。所以要验证日期格式 ... </p>'+
                ' </article>'
        });

        $('.show-article').append(html_article);


    });

});