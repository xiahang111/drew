var articleId = "";

$(document).ready(function () {

    $.ajax({
        type: 'HEAD', // 获取头信息，type=HEAD即可
        url : window.location.href,
        async:false,
        success:function (data, status, xhr) {
            articleId = xhr.getResponseHeader("articleId");
        }
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

















