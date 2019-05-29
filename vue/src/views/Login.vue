<template>
    <body id="poster">
    <el-form class="login-container" label-position="left"
             label-width="0px" size="500px">
        <h3 class="login_title">系统登录</h3>
        <el-form-item class="login-input">
            <el-input type="text" v-model="loginForm.username"
                      auto-complete="off" placeholder="账号" style="border-radius:30px"></el-input>
        </el-form-item>
        <br/>
        <el-form-item class="login-input">
            <el-input type="password" v-model="loginForm.password"
                      auto-complete="off" placeholder="密码"></el-input>
        </el-form-item>
        <br/><br/><br/>
        <el-form-item style="width: 100%">
            <el-button type="primary" style="width: 80px;background: #505458;border: none; font-size:20px"
                       v-on:click="login">登录
            </el-button>
        </el-form-item>
    </el-form>
    </body>
</template>


<script>


    export default {
        name: 'login',
        data() {
            return {
                loginForm: {
                    username: '',
                    password: ''
                },
                responseResult: []
            }
        },
        methods: {
            login() {
                var _this = this
                console.log(this.$store.state)
                this.$axios
                    .post('/login', {
                        username: this.loginForm.username,
                        password: this.loginForm.password
                    })
                    .then(successResponse => {
                        if (successResponse.data.code === 200) {
                            _this.$store.commit('login', _this.loginForm)
                            var path = this.$route.query.redirect
                            this.$router.replace({path: path === '/' || path === undefined ? '/about' : path})
                        }
                        if (successResponse.data.code === 400) {
                            alert("帐号密码错误")
                        }
                    })
                    .catch(failResponse => {
                    })
            }
        }
    }

</script>
<style>
    .login-container {
        border-radius: 30px;
        background-clip: padding-box;
        margin: 200px auto;
        width: 800px;
        padding: 100px 35px 100px 35px;
        background: #fff;
        border: 2px solid #eaeaea;
        box-shadow: 0 0 25px #cac6c6;
        opacity: 0.7;
    }

    .login_title {
        margin: 0px auto 40px auto;
        text-align: center;
        color: #505458;
        size: 100px;
        font-size: 20px;
    }

    .login-input {
        border-radius: 20px;
    }

    #poster {
        background: url("../assets/123456.jpg") no-repeat;
        background-position: center;
        height: 100%;
        width: 100%;
        background-size: cover;
        position: fixed;
    }

    body {
        margin: 0px;
    }
</style>
