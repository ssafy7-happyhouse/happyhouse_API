$(document).ready(function() {
//    let url = "https://grpc-proxy-server-mkvo6j4wsq-du.a.run.app/v1/regcodes";
//    let regcode = "*00000000";
    // 사이트 참고해서 활용해보세요: https://juso.dev/docs/reg-code-api/
    // 전국 특별/광역시, 도
    // https://grpc-proxy-server-mkvo6j4wsq-du.a.run.app/v1/regcodes?regcode_pattern=*00000000
//    $.ajax({
//        url: url,
//        type: "GET",
//        data: {
//            regcode_pattern: regcode,
//        },
//        dataType: "json",
//        success: function(response) {
//            let code = ``;
//            $.each(response.regcodes, function(i, regcode) {
//                code += `
//          <option value="${regcode.code}">${regcode.name}</option>
//          `;
//            });
//            $("#sido").empty().append('<option value="">시도선택</option>').append(code);
//        },
//        error: function(xhr, status, msg) {
//            console.log("상태값 : " + status + " Http에러메시지 : " + msg);
//        },
//    });

//    $(document).on("change", "#sido", function() {
//        regcode = $(this).val().substr(0, 2) + "*00000";
//        $.ajax({
//            url: url,
//            type: "GET",
//            data: {
//                regcode_pattern: regcode,
//                is_ignore_zero: true,
//            },
//            dataType: "json",
//            success: function(response) {
//                let code = ``;
//                $.each(response.regcodes, function(i, regcode) {
//                    code += `
//          <option value="${regcode.code}">${regcode.name.split(" ")[1]}</option>
//          `;
//                });
//                $("#gugun").empty().append('<option value="">구군선택</option>').append(code);
//            },
//            error: function(xhr, status, msg) {
//                console.log("상태값 : " + status + " Http에러메시지 : " + msg);
//            },
//        });
//    });

    $(document).on("change", "#sido", function() {
      let regcode = $(this).val();
        $.ajax({
            url: "gugun",
            type: "GET",
            data: {
            	sidoCode: regcode,
            },
            dataType: "json",
            success: function(response) {
                let code = ``;
                $.each(response, function(i, response) {
                    code += `
          <option value="${response.gugunCode}">${response.gugunName.split(" ")[1]}</option>
          `;
                });
                $("#gugun").empty().append('<option value="">구군선택</option>').append(code);
            },
            error: function(xhr, status, msg) {
                console.log("상태값 : " + status + " Http에러메시지 : " + msg);
            },
        });
    });
    
//    $(document).on("change", "#gugun", function() {
//        regcode = $(this).val().substr(0, 4) + "*";
//        console.log(regcode);
//        $.ajax({
//            url: url,
//            type: "GET",
//            data: {
//                regcode_pattern: regcode,
//                is_ignore_zero: true,
//            },
//            dataType: "json",
//            success: function(response) {
//                let code = ``;
//                $.each(response.regcodes, function(i, regcode) {
//                    code += `
//          <option value="${regcode.code}">${regcode.name.split(" ")[2]}</option>
//          `;
//                });
//                $("#dong").empty().append('<option value="">동선택</option>').append(code);
//            },
//            error: function(xhr, status, msg) {
//                console.log("상태값 : " + status + " Http에러메시지 : " + msg);
//            },
//        });
//    });
    
    $(document).on("change", "#gugun", function() {
        let regcode = $(this).val();
        $.ajax({
            url: "dong",
            type: "GET",
            data: {
                gugunCode: regcode,
            },
            dataType: "json",
            success: function(response) {
                let code = ``;
                $.each(response, function(i, response) {
                    code += `
          <option value="${response.dongCode}">${response.dongName}</option>
          `;
                });
                $("#dong").empty().append('<option value="">동선택</option>').append(code);
            },
            error: function(xhr, status, msg) {
                console.log("상태값 : " + status + " Http에러메시지 : " + msg);
            },
        });
    });

});