$(document).ready(function(){
    $("#tagInput").tagEditor();
    $(".list-group li").click(function(){
        $(this).addClass("active").siblings().removeClass("active");
        $("#classInput").val($(this).html());
    });
    $("#upImg").click(function(){
        $("#upGeoIcon").click();
    });
    $("#upGeoIcon").change(function () {
        var preview = document.getElementById("upImgShow");
        var file  = document.getElementById("upGeoIcon").files[0];
        $("#nameInput").val(file.name);
        var reader = new FileReader();
        reader.onloadend = function () {
            preview.src = reader.result;
        };
        if (file) {
            reader.readAsDataURL(file);
        } else {
            preview.src = "images/upImg.png";
        }
    });
    // 点击该按钮上传图标
    $("#uploadBtn").click(function(){
        var geoIcon = new Object();
        geoIcon["geoId"] = "";
        geoIcon["name"]=$("#nameInput").val().split('.')[0];
        geoIcon["description"] = "";
        geoIcon["pathUrl"] = $("#nameInput").val();
        geoIcon["iconClass"] = $("#classInput").val();
        geoIcon["tags"] = $("#tagInput").val().split(',');
        geoIcon["relatedConceptMaps"] = [];
        geoIcon["relatedGeoIcons"] = [];
        console.log(geoIcon);
        var formData = new FormData(document.getElementById("form1"));
        formData.append("geoIcon",JSON.stringify(geoIcon) );
            $.ajax({
                url: "/geoIcon/upload",
                type:"post",
                data:formData,
                async:true,
                contentType:false,
                cache: false,
                processData: false,
                success:function(msg){
                    alert(msg);
                    window.location.reload();
                },

            })
    })
});



