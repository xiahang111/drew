$(document).ready(function () {

    $.getJSON('/article/getArticles',function (result) {


        var html_title='';


        $.each(result["data"],function (i,item) {

            var html_resultinfo='';
            html_resultinfo += '<article class="excerpt excerpt-1"><a class="focus" id="index_comment" href="article/'+item['articleInfoId']+'" title=""><img class="thumb" data-original="images/excerpt.jpg" src="images/excerpt.jpg" alt=""></a>'+
                '<header><a class="cat" href="program">'+ item['articleCategoryName'] +'<i></i></a>' +
                '<h2><a href="article/'+item['articleInfoId']+'" title="">'+item['articleHeadline']+'</a></h2>'+ '</header>' + ' <p class="meta">' +
                ' <time class="time"><i class="glyphicon glyphicon-time"></i>'+ item['articleDate'] +'</time>'+
                ' <span class="views"><i class="glyphicon glyphicon-eye-open"></i> 共' + item['articleVisitor'] +
                '人浏览过</span> <a class="comment" href="article'+item['articleInfoId']+'"><i class="glyphicon glyphicon-comment"></i>'+ item['articleCommentsNum'] + '条评论</a></p>'+
                '<p class="note">'+ item['articleHeadline'] +' </p>';

            $('#index_title').append(html_resultinfo);

        });



    });

    $.getJSON('/article/getArticleById',{articleId:"16"},function (result) {

        var data = result['data'];

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

        var html_article_content = data['articleContent'];
        $('#article-content').append(html_article_content);

    });

    $.getJSON('/data/article.json',function (result) {

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

        $('#summary-article').append(html_article);


    });
    
    $.getJSON('/data/hot_title.json',function (hotResult) {

        var html_hot = '<ul>';

        var html_hotResultInfo='';

        $.each(hotResult["data"],function (i,item1) {


            html_hotResultInfo += '<li><a href="'+item1['title_url']+
                '"><span class="thumbnail"><img class="thumb" data-original="'+item1['img_url']+
                '" src="'+item1['img_url']+'" alt=""></span><span class="text">'+item1['head_line']+
                '</span><span class="muted"><i class="glyphicon glyphicon-time"></i> '+ item1['date'] +
                ' </span><span class="muted"><i class="glyphicon glyphicon-eye-open"></i>'+item1['visit']+'</span></a></li>'

        });

        var html_result = html_hot + html_hotResultInfo + '</ul>';

        $('#hot_title').append(html_result);
    });



    $.getJSON('/sentence/getToday',function (everydayResult1) {

       /* var  everydayResult = everydayResult1['data'][0];*/

        /*var html_everyday = '<h4>'+everydayResult['year']+'年'+everydayResult['month']
            +'月'+everydayResult['day']+'日星期'+everydayResult['week']+'</h4>' +
            '<p>'+everydayResult['sentence']+'</p>';*/
        /*var html_everyday = '<h4> 2018 年 06月 19日 </h4><p> 天生我材必有用！</p>'*/


       /* $('.widget-sentence-content').append(html_everyday);*/

        var  everydayResult = everydayResult1['data'];

        var html_everyday = '<h4>'+everydayResult['showTime']+'</h4>' +
            '<p style="font-size: large">'+everydayResult['content']+'</p>';

        $('.widget-sentence-content').append(html_everyday);

    });

});

















