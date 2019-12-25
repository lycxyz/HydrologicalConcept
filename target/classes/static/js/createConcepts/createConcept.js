
$(document).ready(function () {
    //折叠组件
    $(function () {
        $('#collapseThree').collapse('toggle');
    });
    $(".label").click(function () {
        $("#shape").val($(this).text());
    });
    $("#addProperty").click(function () {
        let text = $("#property").find("option:selected").text();
        let div = $("#propertyDiv");
        let propertyBlankDiv = $('<div style="display:block"></div>');
        let div2 = $("<div style='display:flex;margin-top:5px;height:25px'></div>");
        let label = $(`<label for="shape" style="line-height:25px;margin-right:20px" class="propertyType">` +
            text + `</label>`);
        let PropertyName = $(
            `<input type="text" class="form-control propertyName" placeholder="属性名" autocomplete="off" style="width:20%;margin-right:20px;height:25px">`
        );
        let PropertyValue = $(
            `<input type="text" class="form-control propertyValue" placeholder="属性描述" autocomplete="off" style="width:20%;height:25px">`
        );
        let btn = $(
            `<button type="button" class="btn btn-danger btn small deleteBtn" style="margin-left:10px;height:25px">` +
            `<span class=\"glyphicon glyphicon-minus\" aria-hidden=\"true\"></span>` + `</button>`);
        btn.click(function () {
            $(this).parent().remove();
        });
        div2.append(label);
        div2.append(PropertyName);
        div2.append(PropertyValue);
        div2.append(btn);
        propertyBlankDiv.append(div2);
        div.append(propertyBlankDiv);
    });
    $("#elements").change = function () {
        // console.log($("#elements").val());
    };
    $("#saveProcessInfo").click(function () {
        let list = $("#elements").val();
        list = list.split('，');
        if (list !== "") {
            var elementDiv = $("#elementDiv");
            for (var i = 0; i < list.length; i++) {
                // console.log(list[i]);
                let input = $(`<label class="checkbox-inline">` + `<input type="checkbox" name="element"` + `id=` +
                    i + " " + `value=` + list[i] + `>` + list[i] + `</label>`);
                elementDiv.append(input);
            }
            elementDiv.append(`<br>`);
        }
    });
    $("#addClassify").click(function(){
        //添加分类体系的函数
        let containerDiv = $("#conceptDescribeDiv");
        let div=$(`<div style="display:flex"></div>`);
        let label1 = $(`<label class="label label-default labels">分类体系:</label>`);
        let input1 = $(`<input type="text" class="classifyDependency" style="height:26px;width:100px;margin-right:10px">`);
        let label2 = $(`<label class="label label-default labels">子类概念:</label>`);
        let input2 = $(`<input type="text" class="subconceptDesc" placeholder="概念间请以逗号分隔" style="height:26px;margin-right:10px">`);
        let btn = $(`<button type="button" class="btn btn-danger btn small minusClassify" style="margin-left:20px;height:26px"><span class="glyphicon glyphicon-minus" aria-hidden="true" style="top:-1px"></span></button>`);
        btn.click(function () {
            $(this).parent().remove();
        });
        div.append(label1);
        div.append(input1);
        div.append(label2);
        div.append(input2);
        div.append(btn);
        containerDiv.append(div);
    });
});
    $("#addProcess").click(function() {
        let div1 = $("#processDiv");
        let div2 = $('<div class="subProcess"></div>');
        let div3 = $(`<div style="display:flex;margin-top:20px;" class="processCon"><label class="label label-default labels">过程名</label><input type="text" class="processName" style="height:25px;width:80px;">
                     <label class="label label-default labels" style="margin-left:20px;">过程描述</label><input type="text" class="processDesc" style="height:25px;min-width:400px;">
                     </div>
                     <div style="display:flex;margin-top:10px"><label class="label label-default labels">发生条件</label><textarea class="condition" placeholder="条件1：....,条件2：....,," rows="4" cols="50"></textarea>
                     <label class="label label-default labels" style="margin-left:20px;">影响因素</label><textarea class="factor" placeholder="因素1：,因素2:," rows="4" cols="50"></textarea></div>
                     <div style="display:flex;margin-top:10px"><label class="label label-default labels">过程特征</label><textarea class="feature" placeholder="特征1：,特征2:," rows="4" cols="50"></textarea>
                     <label class="label label-default labels" style="margin-left:20px;">参与要素</label><textarea class="elements" placeholder="要素1：,要素2:," rows="4" cols="50"></textarea></div>
                     <div style="display:flex;margin-top:10px"><label class="label label-default labels">发生时间</label><span class="spanTitle">精确信息</span><input type="text" class="accurateTime" placeholder="2017年5月8日" style="width:35%;margin-right:10px">
                     <span class="spanTitle" style="margin-left: 20px">模糊信息</span><input type="text" class="inAccurateTime" placeholder="春季居多" style="width:35%">
                     </div>`);
        let btn = $(`<button type="button" class="btn btn-danger btn small minusProcess" style="margin-left:20px;height:26px;margin-top:20px;"><span class="glyphicon glyphicon-minus" aria-hidden="true" style="top:-1px"></span></button>`);
        btn.click(function () {
            $(this).parent().remove();
        });
        div2.append(btn);
        div2.append(div3);
        div1.append(div2);
    });
$("#addRelationDescribe").click(function () {
    var ids = [];
    $("input[name='element']:checked").each(function(){
        ids.push($(this).val());
    });
    // console.log("选中的元素是" + ids);
    createElementDescription(ids);
    $("input[name='element']").removeAttr("checked");
    // console.log("ids是"+ids);
});
$("#addRelation").click(function () {
    let list = $(".elements");
    // console.log(list.length);
    let elementArray = [];

    for (var m =0;m<list.length;m++){
        let elementGroup = list[m].value.split('，');
        elementArray = [...new Set( elementArray.concat(elementGroup))];
    }
    // console.log("elementArray是"+ elementArray);
    // console.log("elementArray长度是"+ elementArray.length);
    if (elementArray!=[]&&elementArray.length>1){
        createElementCheckBox(elementArray);
    }else{
        alert("请先补充参与要素");
    }
});
$("#addSpaceInfo").click(function() {
    let text = $("#spaceInfoType").find("option:selected").text();
    let div = $("#positionDiv");
    let spaceInfoBlankDiv = $('<div style="display:flex"></div>');
    let div2 = $("<div style='display:flex;margin-top:5px;height:25px'></div>");
    let label = $(`<label for="shape" style="line-height:25px;margin-right:20px" class="spaceInfoType">` +
        text + `</label>`);
    let PropertyValue = $(
        `<input type="text" class="form-control spaceInfoValue" placeholder="空间位置描述信息" autocomplete="off" style="width:40%;height:25px">`
    );
    let btn = $(
        `<button type="button" class="btn btn-danger btn small deleteBtn" style="margin-left:10px;height:25px">` +
        `<span class=\"glyphicon glyphicon-minus\" aria-hidden=\"true\"></span>` + `</button>`);
    btn.click(function () {
        $(this).parent().remove();
    });
    div2.append(label);
    div2.append(PropertyValue);
    div2.append(btn);
    spaceInfoBlankDiv.append(div2);
    div.append(spaceInfoBlankDiv);
});
function createElementCheckBox(list){
    let elementDiv = $("#elementDiv");
    $(".checkbox-inline").remove();
    if (list!==""){
        for(var i=0;i<list.length;i++){
            // console.log(list[i]);
            let input = $(`<label class="checkbox-inline">`+`<input type="checkbox" name="element"`+ `id=`+ i + " " + `value=` + list[i]+ `>`+ list[i] + `</label>`);
            elementDiv.append(input);
        }
    }
}
function createElementDescription(elements) {
    let elementDiv = $("#elementDiv");
    let div = $("<div style='margin-top:10px'></div>");
    if (elements.length > 1) {
        let label = $(`<label class="label label-success relateElements" style="margin-right:20px;min-width:100px;">` + elements + ":" + `</label>`);
        let relationType = $(`<input type="text" class="relationType" placeholder="关系类型" style="width:100px;margin-right:20px;">`);
        let relationContent = $(`<input type="text"  class="relationValue" placeholder="关系描述" style="auto">`);
        let btnRemove = $(
            `<button type="button" class="btn btn-danger btn small" style="height:25px;margin-left:10px;margin-top:-2px"><span class="glyphicon glyphicon-minus" aria-hidden="true" style="text-align:center;display:block;"></span></button>`
        );
        btnRemove.click(function () {
            $(this).parent().remove();
        });
        div.append(label);
        div.append(relationType);
        div.append(relationContent);
        div.append(btnRemove);
        elementDiv.append(div);
    } else if (elements.length == 1) {
        alert("请至少选择一个元素!");
    } else {
        alert("您没有选择元素!");
    }

}
//生成随机guid数
function getGuidGenerator() {
    var S4 = function() {
        return (((1+Math.random())*0x10000)|0).toString(16).substring(1);
    };
    return (S4()+S4()+"-"+S4()+"-"+S4()+"-"+S4()+"-"+S4()+S4()+S4());
}
//点击submit按钮
function saveFill() {
    let formData = [];
    let conceptDescription = $("#description").val();
    formData["description"] = conceptDescription;
    let conceptShape = $("#shape").val();
    formData["shape"] = conceptShape;
    //梳理属性特征的信息
    let propertyTypeList = $(".propertyType");
    let PropertyNameList = $(".propertyName");
    let PropertyValueList = $(".propertyValue");
    let PropertyGroup = [];
    for (var i = 0; i < PropertyNameList.length; i++) {
        PropertyGroup.push({
            "propertyType": propertyTypeList[i].innerText,
            "name": PropertyNameList[i].value,
            "description": PropertyValueList[i].value
        });
    }
    //梳理过程的信息
    let processDiv = $('.subProcess');
    let conditions = $('.condition');
    var condition,factor,feature,element,assureTime,inassureTime;
    var conditionInfo=[],factorInfo=[],featureInfo=[],elementInfo=[],timeInfo=[],assureInfo=[],inassureInfo=[];
    for (var i=0;i<conditions.length;i++){
        condition = conditions[i].value.split('，');
        conditionInfo.push(condition);
    }
    let factors = $('.factor');
    for (var i=0;i<factors.length;i++){
        factor = factors[i].value.split('，');
        factorInfo.push(factor);
    }
    let elements = $('.elements');
    for (var i=0;i<elements.length;i++){
        element = elements[i].value.split('，');
        elementInfo.push(element);
    }
    let features = $('.feature');
    for (var i=0;i<features.length;i++){
        feature = features[i].value.split('，');
        featureInfo.push(feature);
    };
    var timeInfo = [];
    var accurateTimeInfo = $(".accurateTime");
    var inaccurateTimeInfo = $(".inAccurateTime");
    for (var i=0;i<processDiv.length;i++){
        timeInfo.push([{type:"精确",description:accurateTimeInfo[i].value},{type:"模糊",description:inaccurateTimeInfo[i].value}]);
    }
    let processInfo = [];
    for (var j = 0 ;j < processDiv.length;j++){
        processInfo.push({"elements":elementInfo[j],"effectFactors":factorInfo[j],"processFeatures":featureInfo[j],"happenConditions":conditionInfo[j],"happenTime":timeInfo[j]});
    }
    //语义描述框架
    let conceptObj = new Object();
    conceptObj["name"] = $("#conceptName").val();
    conceptObj["id"] = getGuidGenerator();
    conceptObj["description"] = $("#conceptDesc").val();
    conceptObj["xml"] = "";
    conceptObj["pathUrl"] = "";
    conceptObj["classification"] = "";
    //关联概念
    let arr = [];
    let relateConcepts = $("#relateConcept").val().split('，');
    relateConcepts.forEach(value=>{
        arr.push({"name":value,"id":getGuidGenerator()});
    });
    let classification = [];
    let depend = $(".classifyDependency");
    let subconceptDesc = $(".subconceptDesc");
    for (i = 0;i<depend.length;i++){
        let subArray = subconceptDesc[i].value.split('，');
        let arr2 = [];
        subArray.forEach(value=>{
            arr2.push({"name":value, "id":getGuidGenerator()})
        });
        classification.push({"depend":depend[i].value,"subConcepts":arr2});
        // console.log(depend[i].value);
    }
    //语义描述框架结束
    //空间位置描述框架
    let spaceInfoType = $('.spaceInfoType');
    let spaceInfoValue = $('.spaceInfoValue');
    // console.log(spaceInfoType);
    // console.log(spaceInfoValue);
    let spacePositions =[];
    for (var j =0;j<spaceInfoValue.length;j++){
        spacePositions.push({"preciseType":spaceInfoType[j].innerText,"description":spaceInfoValue[j].value})
    }
    //空间位置描述框架结束
    //要素关系描述框架开始
    let relateEle = $('.relateElements');
    let relationType = $('.relationType');
    let relationValue = $('.relationValue');
    let elementRelations = [];
    for (var j =0;j<relateEle.length;j++){
        //这里是英文逗号
        let elementArray = relateEle[j].innerText.split(',');
        let elementObj = [];
        elementArray.forEach(value=>{
            elementObj.push({"name":value, "id":getGuidGenerator()})
        });
        elementRelations.push({"relateElements":elementObj,"relateType":relationType[j].value,"relateValue":relationValue[j].value});

    }
    // console.log("要素关系是:" + elementRelations);
    //要素关系描述框架结束
    conceptObj["concepts"] = {"definition":$("#conceptDesc").val(),"relatedConcepts": arr,"classifications":classification};
    conceptObj["properties"] = PropertyGroup;
    conceptObj["shapeInfo"] = $("#shape").val();
    conceptObj["spacePositions"] = spacePositions;
    conceptObj["elementRelations"] = elementRelations;
    conceptObj["processes"] = processInfo;
    conceptObj["name"] = $("#conceptName").val();
    conceptObj["description"] = $("#conceptDescription").val();
    console.log(conceptObj);

}