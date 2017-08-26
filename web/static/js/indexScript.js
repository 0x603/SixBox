$(function(){
    // var switcher=$(".login-register-switch>a");
    // switcher.click(function(){
    //     if ($(this).text()==="创建账户") {
    //         $(this).text("登录");
    //         $(".info-header").text("创建账户");
    //         $("#login-page").css("display","none");
    //         $("#register-page").css("display","block");
    //     }else{
    //         $(this).text("创建账户");
    //         $(".info-header").text("登录");
    //         $("#login-page").css("display","block");
    //         $("#register-page").css("display","none");
    //     }
    // });

    // Validation for login form
    $("#loginForm").bootstrapValidator({
        message:'This value is not valid',
        feedbackIcons: {
                valid: 'glyphicon glyphicon-ok',
                invalid: 'glyphicon glyphicon-remove',
                validating: 'glyphicon glyphicon-refresh'
            },
        fields: {
            username: {
                validators: {
                        notEmpty: {
                            message: 'The username is required and cannot be empty'
                        }
                    }
                },
            password: {
                validators: {
                        notEmpty: {
                            message: 'The password is required and cannot be empty'
                        }
                    }
                }
        }
    });

    // Validation for register form
    $("#registerForm").bootstrapValidator({
        message:'This value is not valid',
        feedbackIcons: {
                valid: 'glyphicon glyphicon-ok',
                invalid: 'glyphicon glyphicon-remove',
                validating: 'glyphicon glyphicon-refresh'
            },
        fields: {
            username: {
                message: 'The username is not valid',
                validators: {
                        notEmpty: {
                            message: 'The username is required and cannot be empty'
                        },
                        regexp: {
                            regexp: /^[a-zA-Z0-9_]+$/,
                            message: 'The username can only contain a-z,A-Z,_'
                        }
                    }
                },
            password: {
                validators: {
                        notEmpty: {
                            message: 'The password is required and cannot be empty'
                        }
                    }
                },
            confirmPassword: {
                validators: {
                        notEmpty: {
                            message: 'The confirm password is required and cannot be empty'
                        },
                        identical: {
                            field:'password',
                            message: 'The confirm password is not the same as the password'
                        }
                    }
                }
         }
    });     
});