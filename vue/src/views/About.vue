<template>
    <section>
    <el-container>
        <el-header>
            <span style="font-size: 30px"><i class="el-icon-s-home"></i>人员管理系统</span>
            <el-button @click="outSys"  style="position:relative; left:750px;"type="danger" plain>退出系统</el-button>
        </el-header>
        <el-herder>
            <el-row>
                <el-col :span="24"><div class="grid-content bg-purple-light">
                    <el-row :gutter="20">
                        <el-col :span="6"><div class="grid-content bg-purple-light">
                            <el-input placeholder="请输入员工姓名" v-model="input1">
                                <template slot="prepend">人员名称：</template>
                            </el-input>
                        </div></el-col>
                        <el-col :span="6"><div class="grid-content bg-purple-light">
                            <span>人员来源：</span>
                            <el-select clearable v-model="value" placeholder="请选择">
                                <el-option
                                        v-for="item in options"
                                        :key="item.value"
                                        :label="item.label"
                                        :value="item.value">
                                </el-option>
                            </el-select>

                        </div></el-col>
                        <el-col :span="6"><div class="grid-content bg-purple-light">
                            <span>所属部门：</span>
                            <el-select clearable v-model="value2" placeholder="请选择">
                                <el-option
                                        v-for="item in option"
                                        :key="item.value"
                                        :label="item.label"
                                        :value="item.value">
                                </el-option>
                            </el-select>
                        </div></el-col>
                        <el-col :span="6"><div style="align:center" class="grid-content bg-purple-light">
                            <el-button @click="searchPerson" type="primary" icon="el-icon-search">搜索</el-button>
                        </div></el-col>
                        <el-col :span="6"><div class="grid-content bg-purple-light"></div></el-col>
                    </el-row>
                </div></el-col>
            </el-row>

        </el-herder>
        <el-main>
            <!--<el-button  style="position:absolute; left:0px; top:250px;"type="primary" @click="HandleAdd">新建<i class="el-icon-upload el-icon-ice-cream-round"></i></el-button>-->
            <el-button type="primary" @click="handleAdd">新增</el-button>
            <el-button type="primary" @click="getUsers" icon="el-icon-refresh">刷新数据</el-button>
            <el-table
                    :data="tableData"
                    border
                    style="width: 100%">
                <el-table-column
                        fixed
                        prop="jobnumber"
                        label="工号"

                        width="150">
                </el-table-column>
                <el-table-column
                        prop="name"
                        label="姓名"
                        width="150">
                </el-table-column>
                <el-table-column
                        prop="sex"
                        label="性别"
                        width="150">
                </el-table-column>
                <el-table-column
                        prop="source"
                        label="来源"
                        width="150">
                </el-table-column>
                <el-table-column
                        prop="address"
                        label="地址"
                        width="150">
                </el-table-column>
                <el-table-column
                        prop="mail"
                        label="邮箱"
                        width="150">
                </el-table-column>
                <el-table-column
                        prop="dpt"
                        label="部门"
                        width="150">
                </el-table-column>
                <el-table-column
                        prop="post"
                        label="职位"
                        width="150">
                </el-table-column>
                <el-table-column
                        prop="tel"
                        label="电话"
                        width="150">
                </el-table-column>
                <el-table-column
                        fixed="right"
                        label="操作"
                        width="200">
                    <template slot-scope="scope">
                        <el-button
                                size="mini"
                                @click="handleEdit(scope.$index, scope.row)">编辑</el-button>
                        <el-button size="mini" type="danger"
                                   @click="removeUser(scope.$index, scope.row)">删除</el-button>
                    </template>
                </el-table-column>
            </el-table>
        </el-main>
    </el-container>
        <el-dialog
                title="新增员工"
                :visible.sync="dialogCreateVisible"
                style="text-align: left">
            <el-form :model="createForm" :rules="createRules" ref="createForm" label-width="100px" class="demo-createForm">
                <el-form-item label="工号" prop="jobnumber">
                    <el-input v-model="createForm.jobnumber"></el-input>
                </el-form-item>
                <el-form-item label="姓名" prop="name">
                    <el-input v-model="createForm.name"></el-input>
                </el-form-item>
                <el-form-item label="性别" prop="sex">
                    <el-radio-group v-model="createForm.sex">
                        <el-radio label="男" value="男"></el-radio>
                        <el-radio label="女" value="女"></el-radio>
                    </el-radio-group>
                </el-form-item>
                <el-form-item label="来源" prop="source">
                    <el-select v-model="createForm.source">
                        <el-option label="简历投递" value="简历投递"></el-option>
                        <el-option label="人员推荐" value="人员推荐"></el-option>
                    </el-select>
                </el-form-item>
                <el-form-item label="地址" prop="address">
                    <el-input v-model="createForm.address"></el-input>
                </el-form-item>
                <el-form-item label="邮箱" prop="mail">
                    <el-input v-model="createForm.mail"></el-input>
                </el-form-item>
                <el-form-item label="部门" prop="dpt">
                    <el-select v-model="createForm.dpt">
                        <el-option label="财务部" value="财务部"></el-option>
                        <el-option label="销售部" value="销售部"></el-option>
                        <el-option label="开发部" value="开发部"></el-option>
                    </el-select>
                </el-form-item>
                <el-form-item label="职位" prop="post">
                    <el-select v-model="createForm.post">
                        <el-option label="经理" value="经理"></el-option>
                        <el-option label="组长" value="组长"></el-option>
                    </el-select>
                </el-form-item>
                <el-form-item label="电话" prop="tel">
                    <el-input v-model="createForm.tel"></el-input>
                </el-form-item>
                    <el-button @click="createReset">取消</el-button>
                    <el-button @click.native="createSubmit" :loading="createLoading" type="primary">确定</el-button>
            </el-form>
        </el-dialog>
        <el-dialog
                title="修改信息"
                :visible.sync="dialogUpdateVisible"
                style="text-align: left">
            <el-form :model="updateForm" :rules="updateRules" ref="updateForm" label-width="100px" class="demo-updateForm">
                <el-form-item label="工号" prop="jobnumber">
                    <el-input v-model="updateForm.jobnumber"></el-input>
                </el-form-item>
                <el-form-item label="姓名" prop="name">
                    <el-input v-model="updateForm.name"></el-input>
                </el-form-item>
                <el-form-item label="性别" prop="sex">
                    <el-radio-group v-model="updateForm.sex">
                        <el-radio label="男"></el-radio>
                        <el-radio label="女"></el-radio>
                    </el-radio-group>
                </el-form-item>
                <el-form-item label="来源" prop="source">
                    <el-select v-model="updateForm.source">
                        <el-option label="简历投递" value="jianli"></el-option>
                        <el-option label="人员推荐" value="tuijian"></el-option>
                    </el-select>
                </el-form-item>
                <el-form-item label="地址" prop="address">
                    <el-input v-model="updateForm.address"></el-input>
                </el-form-item>
                <el-form-item label="邮箱" prop="mail">
                    <el-input v-model="updateForm.mail"></el-input>
                </el-form-item>
                <el-form-item label="部门" prop="dpt">
                    <el-select v-model="updateForm.dpt">
                        <el-option label="财务部" value="财务部"></el-option>
                        <el-option label="销售部" value="销售部"></el-option>
                        <el-option label="开发部" value="开发部"></el-option>
                    </el-select>
                </el-form-item>
                <el-form-item label="职位" prop="post">
                    <el-select v-model="updateForm.post">
                        <el-option label="经理" value="经理"></el-option>
                        <el-option label="组长" value="小组长"></el-option>
                    </el-select>
                </el-form-item>
                <el-form-item label="电话" prop="tel">
                    <el-input v-model="updateForm.tel"></el-input>
                </el-form-item>
                    <el-button @click="updateReset">取消</el-button>
                <el-button @click.native="updateSubmit" :loading="updateLoading" type="primary">确定</el-button>
            </el-form>
        </el-dialog>
    </section>
</template>
<script>
    export default {
        data() {
            return {
                updateForm:{
                    jobnumber: '',
                    name: '',
                    radio: '',
                    source: '',
                    address: '',
                    mail: '',
                    dpt: '',
                    post: '',
                    tel: ''
                },
                createForm: {
                    jobnumber: '',
                    name: '',
                    radio: '',
                    source: '',
                    address: '',
                    mail: '',
                    dpt: '',
                    post: '',
                    tel: ''
                },
                createRules: {
                    name: [
                        { required: true, message: '请输入员工姓名', trigger: 'blur' },
                        { min: 2,  message: '长度大于两个字符', trigger: 'blur' }
                    ],
                    jobnumber: [
                        { required: true, message: '请输入员工号', trigger: 'blur' },
                        { min: 2,  message: '长度大于两个字符', trigger: 'blur' }
                    ],
                    source: [
                        { required: true, message: '请选择员工来源', trigger: 'change' }
                    ],
                    sex: [
                        { required: true, message: '请选择性别', trigger: 'change' }
                    ],
                    address: [
                        { required: true, message: '请输入员工地址', trigger: 'blur' },
                        { min: 2,  message: '长度大于两个字符', trigger: 'blur' }
                    ],
                    mail: [
                        { required: true, message: '请输入员工邮箱', trigger: 'blur' },
                        { min: 2,  message: '长度大于两个字符', trigger: 'blur' }
                    ],
                    dpt: [
                        { required: true, message: '请选择部门', trigger: 'change' }
                    ],
                    post: [
                        { required: true, message: '请选择职位', trigger: 'change' }
                    ],
                    tel: [
                        { required: true, message: '请输入员工手机号', trigger: 'blur' },
                        { min: 2,  message: '长度大于8个字符', trigger: 'blur' }
                    ],
                },
                updateRules: {
                    jobnumber: [
                        { required: true, message: '请输入员工工号', trigger: 'blur' },
                    ],
                    name: [
                        { required: true, message: '请输入员工姓名', trigger: 'blur' },
                    ],
                    source: [
                        { required: true, message: '请选择员工来源', trigger: 'change' }
                    ],
                    sex: [
                        { required: true, message: '请选择性别', trigger: 'change' }
                    ],
                    address: [
                        { required: true, message: '请输入员工地址', trigger: 'blur' },
                        { min: 2,  message: '长度大于两个字符', trigger: 'blur' }
                    ],
                    mail: [
                        { required: true, message: '请输入员工姓名', trigger: 'blur' },
                        { min: 2,  message: '长度大于两个字符', trigger: 'blur' }
                    ],
                    dpt: [
                        { required: true, message: '请选择部门', trigger: 'change' }
                    ],
                    post: [
                        { required: true, message: '请选择职位', trigger: 'change' }
                    ],
                    tel: [
                        { required: true, message: '请输入员工手机号', trigger: 'blur' },
                        { min: 2,  message: '长度大于8个字符', trigger: 'blur' }
                    ],
                },
                options: [{
                    value: '人员推荐',
                    label: '人员推荐'
                }, {
                    value: '简历投递',
                    label: '简历投递'
                }],
                value: '',
                option:[{
                    value: '销售部',
                    label: '销售部'
                },{
                    value: '开发部',
                    label: '开发部'
                }, {
                    value: '财务部',
                    label: '财务部'
                }],
                value2:'',
                input1:'',
                tableData: [{
                    jobnumber: '',
                    name: '',
                    sex: '',
                    source: '',
                    address: '',
                    mail:'',
                    dpt: '',
                    post:'',
                    tel:''
                }],
                createLoading: false,
                updateLoading: false,
                dialogCreateVisible: false,
                dialogUpdateVisible:false,
                listLoading: false,
            }
        },
        methods: {
            searchPerson(){
                let searchParas= new URLSearchParams();
                searchParas.append("name",this.input1);
                searchParas.append("source",this.value);
                searchParas.append("dpt",this.value2);
                this.$axios({
                    method: 'post',
                    url: '/searchPerson',
                    data:searchParas,
                }).then(res=> {
                    if(res.data.length>0) {
                        this.jobnumber = res.data[0].jobnumber;
                        this.tableData = res.data;
                    }else {
                        this.$message({
                            message: '没有相应人员数据',
                            type: 'warning'
                        });
                    }
                }).catch(err=> {
                    console.log(err);
                })
            },
            createSubmit() {
                this.$refs.createForm.validate((valid) => {
                    if (valid) {
                        this.$confirm('确认提交？', '提示', {}).then(() => {
                            this.createLoading = true;
                            let addPara = new URLSearchParams();
                            addPara.append("jobnumber", this.createForm.jobnumber);
                            addPara.append("name", this.createForm.name);
                            addPara.append("sex", this.createForm.sex);
                            addPara.append("source", this.createForm.source);
                            addPara.append("address", this.createForm.address);
                            addPara.append("mail", this.createForm.mail);
                            addPara.append("dpt", this.createForm.dpt);
                            addPara.append("post", this.createForm.post);
                            addPara.append("tel", this.createForm.tel);
                            this.$axios({
                                method: 'post',
                                url: 'http://localhost:8080/api/addPerson',
                                data:addPara
                            }).then(res => {
                                console.log(eval(res.data));
                                console.log(addPara);
                                console.log(addPara.jobnumber);
                                console.log(res.data);
                                this.createLoading = false;
                                this.$message({
                                    type: 'success',
                                    message: '提交成功!'
                                });
                                this.dialogCreateVisible = false;
                                this.getUsers();
                            })
                        }).catch(() => {
                            this.$message({
                                type: 'info',
                                message: '已取消新增'
                            });
                        })
                    }
                    else {
                        console.log('error');
                        return false;
                    }
                })
            },
            createReset() {
                this.dialogCreateVisible= false;
                this.createLoading = false;
                this.$refs.createForm.resetFields();
            },
            updateSubmit() {
                this.$refs.updateForm.validate((valid1) => {
                    if (valid1) {
                        this.$confirm('确认提交？', '提示', {}).then(() => {
                            this.updateLoading = true;
                            let editParas= new URLSearchParams();
                            editParas.append("jobnumber", this.updateForm.jobnumber);
                            editParas.append("name", this.updateForm.name);
                            editParas.append("sex", this.updateForm.sex);
                            editParas.append("source", this.updateForm.source);
                            editParas.append("address", this.updateForm.address);
                            editParas.append("mail", this.updateForm.mail);
                            editParas.append("dpt", this.updateForm.dpt);
                            editParas.append("post", this.updateForm.post);
                            editParas.append("tel", this.updateForm.tel);
                            console.log(editParas);
                            this.$axios({
                                method: 'post',
                                url: '/updatePerson',
                                data:editParas,
                            }).then(res => {
                               /* console.log(eval(res.data));
                                console.log(editParas);
                                console.log(editParas.jobnumber);*/
                                console.log(res.data);
                                //this.tableData = res.data;
                                this.updateLoading = false;
                                this.$message({
                                    type: 'success',
                                    message: '提交成功!'
                                });
                                this.dialogUpdateVisible = false;
                                this.getUsers();
                            })
                        }).catch(() => {
                            this.$message({
                                type: 'info',
                                message: '已取消新增'
                            });
                        })
                    }
                    else {
                        console.log('error');
                        return false;
                    }
                })
            },
            updateReset() {
                this.dialogUpdateVisible= false;
                this.updateLoading = false;
                this.$refs.updateForm.resetFields();
            },
            /*handleDelete(index, row) {
                console.log(index, row);
            },*/
            submitForm(formName) {
                this.$refs[formName].validate((valid) => {
                    if (valid) {
                        alert('submit!');
                    } else {
                        console.log('error submit!!');
                        return false;
                    }
                });
            },
            resetForm(formName) {
                this.$refs[formName].resetFields();
            },
            handleAdd() {
                this.dialogCreateVisible = true;
                /*this.createForm = {
                    jobnumber:'',
                    name: '',
                    radio: '',
                    source: '',
                    address: '',
                    mail: '',
                    dpt: '',
                    post: '',
                    tel: ''
                };*/
            },
            getUsers() {
                let para=new URLSearchParams();
                para.append("name",this.input1)
                this.listLoading=true;
                this.$axios({
                    methods: 'get',
                    url:'/selectAll',
                    data:para,
                }).then(res=> {
                    /*this.total.data.length;*/
                    /*this.pageSize=20;*/
                    this.jobnumber=res.data[0].jobnumber;
                    this.tableData=res.data;
                    this.listLoading=false;
                }).catch(err=> {
                    console.log(err);
                })
            },

            removeUser(index, row) {
                this.$confirm('确定删除该记录吗？ ', '提示', {
                    type: 'warning'
                }).then(() => {
                    this.listLoading = true;
                    let delParas = new URLSearchParams();
                    delParas.append("jobnumber", row.jobnumber ); // 响应template中scope.row，实现向后台传送当前row的id

                    //  向服务端请求删除
                    this.$axios({
                        method: 'post',
                        url: '/deleteOne',
                        data:delParas,
                    }).then(res => {
                        this.listLoading = false;
                        this.$message({
                            message: '删除成功',
                            type: 'success'
                        });
                        this.getUsers();
                    });
                }).catch(() => {
                    console.log('删除失败');
                })
            },
            handleEdit(index, row) {
                console.log('click success');
                //this.updateForm = Object.assign({}, row)
                let updateParas = new URLSearchParams();
                updateParas.append("jobnumber",row.jobnumber)
                this.$axios({
                    method: 'post',
                    url: '/getPerson',
                    data: updateParas
                }).then(res => {
                    this.updateForm = res.data;
                    this.dialogUpdateVisible = true;
                })
            },
            outSys(){
                this.$confirm('此操作将登出此系统并删除登录信息, 是否继续?', '提示', {
                    confirmButtonText: '确定',
                    cancelButtonText: '取消',
                    type: 'warning'
                }).then(() => {
                    this.$message({
                        type: 'success',
                        message: '欢迎再次使用本系统~'
                    });
                    window.localStorage.clear()
                    this.$router.push({path: '/login'})
                }).catch(() => {
                    this.$message({
                        type: 'info',
                        message: '已取消登出'
                    });
                });
            }
        },
        mounted() {
            this.getUsers();
        }

    };
</script>
<style>
    .el-header, .el-footer {
        background-color: #B3C0D1;
        color: #333;
        text-align: center;
        line-height: 60px;
    }

    .el-aside {
        background-color: #D3DCE6;
        color: #333;
        text-align: center;
        line-height: 200px;
    }

    .el-main {
        background-color: #E9EEF3;
        color: #333;
        text-align: left;
        line-height: 60px;
    }

    body > .el-container {
        margin-bottom: 40px;
    }

    .el-container:nth-child(5) .el-aside,
    .el-container:nth-child(6) .el-aside {
        line-height: 260px;
    }

    .el-container:nth-child(7) .el-aside {
        line-height: 320px;
    }
    .el-row {
        margin-bottom: 20px;
        &:last-child {
            margin-bottom: 0;
        }
    }
    .el-col {
        border-radius: 4px;
    }
    .bg-purple-dark {
        background: #99a9bf;
    }
    .bg-purple {
        background: #d3dce6;
    }
    .bg-purple-light {
        background: #e5e9f2;
    }
    .grid-content {
        border-radius: 4px;
        min-height: 100px;
    }
    .row-bg {
        padding: 10px 0;
        background-color: #f9fafc;
    }
</style>
