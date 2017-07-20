/**
 * Created by Dana on 2017/7/13.
 */
$('.toggle').on('click', function() {
    $('.container').stop().addClass('active');
});

$('.close').on('click', function() {
    $('.container').stop().removeClass('active');
});
