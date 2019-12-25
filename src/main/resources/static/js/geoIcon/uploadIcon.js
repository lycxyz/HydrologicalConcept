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
        geoIcon["geoId"] = generateGUID();
        geoIcon["name"]=$("#nameInput").val().split('.')[0];
        geoIcon["description"] = $("#descInput").val();
        geoIcon["pathUrl"] = $("#nameInput").val();
        geoIcon["iconClass"] = $("#classInput").val();
        geoIcon["tags"] = $("#tagInput").val().split(',');
        geoIcon["relatedConceptMaps"] = [];
        geoIcon["relatedGeoIcons"] = [];
        console.log(geoIcon);
        var formData = new FormData(document.getElementById("form1"));
        formData.append("geoIcon",JSON.stringify(geoIcon));
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

    function generateGUID () {
        var s = [];
        var hexDigits = "0123456789abcdef";
        for (var i = 0; i < 36; i++) {
            s[i] = hexDigits.substr(Math.floor(Math.random() * 0x10), 1);
        }
        s[14] = "4";  // bits 12-15 of the time_hi_and_version field to 0010
        s[19] = hexDigits.substr((s[19] & 0x3) | 0x8, 1);  // bits 6-7 of the clock_seq_hi_and_reserved to 01
        s[8] = s[13] = s[18] = s[23] = "-";

        var uuid = s.join("");
        return uuid;
    }
});



