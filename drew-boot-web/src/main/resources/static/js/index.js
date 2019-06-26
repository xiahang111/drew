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

});