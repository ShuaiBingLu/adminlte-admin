$(function(){

	// input iCheck
    $('input').iCheck({
      checkboxClass: 'icheckbox_square-blue',
      radioClass: 'iradio_square-blue',
      increaseArea: '20%' // optional
    });
    
	// login Form Valid
	var loginFormValid = $("#loginForm").validate({
		errorElement : 'span',  
        errorClass : 'help-block',
        focusInvalid : true,  
        rules : {
            username : {
        		required : true ,
                minlength: 5,
                maxlength: 18
            },  
            password : {  
            	required : true ,
                minlength: 5,
                maxlength: 18
            } 
        }, 
        messages : {  
        	userName : {  

            },
            password : {

                /*,maxlength:"登录密码不应超过18位"*/
            }
        }, 
		highlight : function(element) {  
            $(element).closest('.form-group').addClass('has-error');  
        },
        success : function(label) {  
            label.closest('.form-group').removeClass('has-error');  
            label.remove();  
        },
        errorPlacement : function(error, element) {  
            element.parent('div').append(error);  
        },
        submitHandler : function(form) {
			$.post("/login", $("#loginForm").serialize(), function(data, status) {
				if (data.code == "200") {
                    layer.msg( 'cusse' );
                    setTimeout(function(){
                        window.location.href = base_url;
                    }, 500);
				} else {
                    layer.open({
                        title: '123',
                        btn: [ 'ok' ],
                        content: (data.msg || 'fail' ),
                        icon: '2'
                    });
				}
			});
		}
	});
});