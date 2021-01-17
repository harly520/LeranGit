<template>
    <div class="mg10" style="height:100%;width: 100%;position: relative; ">
        <Row>
            <Col :span="6" v-for="(table,index) in tableList" style="margin-bottom: 15px;">
                <Card class="box-card" :title="table.name" icon="ios-film-outline" :bordered="true">
                    <a href="#" slot="extra" @click.prevent="removeTable(index)" v-if="index !== 0">
                      <Icon type="ios-loop-strong"></Icon>
                      删除
                    </a>
                    <a href="#" slot="extra" @click.prevent="handleAdd(index)">
                      <Icon type="ios-loop-strong"></Icon>
                      编辑
                    </a>
                    <div v-for="col in table.colList"  class="text item" @dblclick="handleAdd(index)">
                        <Badge dot   v-if="col.name === table.primary">
                            {{col.name }}&nbsp;&nbsp;{{col.type }}({{col.length }})&nbsp;&nbsp;{{table.auto?'自增':''}}主键
                        </Badge>
                        <em v-else>
                            {{col.name }}&nbsp;&nbsp;{{col.type }}({{col.length }})
                        </em>
                    </div>
                </Card>
            </Col>
        </Row>
        <!-- 编辑弹出框 -->
        <Modal title="编辑数据表" v-model="addVisible" width="800" draggable scrollable>
          <Tabs value="first">
            <TabPane label="基本信息" name="second">
              <Form ref="form" :model="form" :label-width="70" :rules="rules" >
                <FormItem label="表名" prop="name" >
                  <Input v-model="form.name" ></Input>
                </FormItem>
                <FormItem label="备注" prop="comment">
                  <Input v-model="form.comment"></Input>
                </FormItem>
                <FormItem label="主键" prop="primary">
                  <Select v-model="form.primary" >
                    <Option :value="col.name" v-for="col in tableList[activeTable].colList">{{col.name}}</Option>
                  </Select>
                </FormItem>
                <FormItem label="主键自增">
                  <i-switch size="large" v-model="form.auto">
                    <span slot="open">ON</span>
                    <span slot="close">OFF</span>
                  </i-switch>
                </FormItem>
              </Form>
            </TabPane>
            <TabPane label="数据项" name="first">
                <Row :gutter="16">
                  <Col :span="5"><div><span class="redDot">*</span>字段名</div></Col>
                  <Col :span="4"><div><span class="redDot">*</span>数据类型</div></Col>
                  <Col :span="3"><div>默认值</div></Col>
                  <Col :span="3"><div><span class="redDot">*</span>长度</div></Col>
                  <Col :span="8"><div><span class="redDot">*</span>备注</div></Col>
                  <Col :span="1"><div class="grid-content bg-purple">
                    <div class="addRow" @click="addRow">
                      <Icon type="ios-add-circle" />
                    </div>
                  </div></Col>
                </Row>
                <Row  v-for="(item,index) in colList" :gutter="16">
                  <Form  :ref="'colList'" :model="item"  :rules="tableRules" >
                  <Col :span="5">
                    <FormItem prop="name">
                      <Input v-model="item.name" placeholder="英文字段名"></Input>
                    </FormItem>
                  </Col>
                  <Col :span="4">
                    <FormItem prop="type">
                      <Select v-model="item.type" placeholder="数据类型">
                        <Option label="varchar" value="varchar"></Option>
                        <Option label="int" value="int"></Option>
                      </Select>
                    </FormItem>
                  </Col>
                  <Col :span="3">
                    <FormItem>
                      <Input v-model="item.default" placeholder="选填"></Input>
                    </FormItem>
                 </Col>
                  <Col :span="3">
                    <FormItem prop="length">
                      <InputNumber  type="integer" :min="1" v-model="item.length" placeholder="数据长度"></InputNumber>
                    </FormItem>
                  </Col>
                  <Col :span="8">
                    <FormItem prop="comment">
                      <Input v-model="item.comment" placeholder="字段注释"></Input>
                    </FormItem>
                  </Col>
                  <Col :span="1"><div class="grid-content bg-purple">
                    <div class="removeRow" @click="removeRow(table)">
                      <Icon type="ios-remove-circle-outline" />
                    </div>
                  </div></Col>
                  </Form>
                </Row>
            </TabPane>
          </Tabs>
          <div slot="footer">
                <Button @click="addVisible = false">取 消</Button>
                <Button type="primary" @click="saveAdd">确 定</Button>
            </div>
        </Modal>
        <!-- 编辑弹出框 -->
        <Modal title="新增数据表"   v-model="tableVisible"  draggable scrollable>
            <Form ref="addForm" :model="form" :label-width="70" :rules="rules">
                <FormItem label="表名" prop="name">
                    <Input v-model="form.name"></Input>
                </FormItem>
                <FormItem label="备注" prop="comment">
                    <Input v-model="form.comment"></Input>
                </FormItem>
                <FormItem label="主键" prop="primary">
                    <Input v-model="form.primary"></Input>
                </FormItem>
                <FormItem label="主键自增" >
                  <i-switch size="large" v-model="form.auto">
                    <span slot="open">ON</span>
                    <span slot="close">OFF</span>
                  </i-switch>
                </FormItem>
            </Form>
            <div slot="footer">
                <Button @click="tableVisible = false">取 消</Button>
                <Button type="primary" @click="saveTable">确 定</Button>
            </div>
        </Modal>
      <Drawer
        title="保存数据表"
        v-model="drawer"
        width="360"
        :styles="styles"
      >
          <Form :model="formItem" :label-width="80" ref="formItem" :rules="formItemRules" hide-required-mark>
              <FormItem label="服务器IP" prop="ip">
                  <Input v-model="formItem.ip" placeholder="服务器IP"></Input>
              </FormItem>
              <FormItem label="端口号" prop="port">
                  <Input v-model="formItem.port" placeholder="端口号"></Input>
              </FormItem>
              <FormItem label="用户名" prop="username">
                  <Input v-model="formItem.username" placeholder="用户名"></Input>
              </FormItem>
              <FormItem label="密码" prop="password">
                  <Input type="password" v-model="formItem.password" placeholder="密码"></Input>
              </FormItem>
              <FormItem label="数据库名" prop="dataName">
                  <Input v-model="formItem.dataName" placeholder="数据库名"></Input>
              </FormItem>
              <FormItem label="保存数据源">
                  <i-switch v-model="formItem.save" size="large">
                      <span slot="open">On</span>
                      <span slot="close">Off</span>
                  </i-switch>
              </FormItem>
              <FormItem label="不连数据库">
                  <i-switch v-model="formItem.not" size="large">
                      <span slot="open">On</span>
                      <span slot="close">Off</span>
                  </i-switch>
              </FormItem>
          </Form>
        <div class="demo-drawer-footer">
          <Button style="margin-right: 8px" @click="drawer = false">取消</Button>
          <Button type="primary" @click="saveAll">保存</Button>
        </div>
      </Drawer>
        <div class="edit">
          <Button type="primary" shape="circle" icon="md-add" size="large" class="butt" @click="tableVisible = true"></Button>
          <Button type="primary" shape="circle" icon="md-done-all" size="large" class="butt" @click="drawer = true"></Button>
        </div>
    </div>
</template>
<script>
    //redDotrt { fetchData } from '@/api/table-design/index';
    export default {
        data() {
            return {
              drawer:false,
                currentDate: new Date(),
                colList:[{name:'',type:'',length:0,default:'',comment:''}],
                query: {
                    address: '',
                    name: '',
                    pageIndex: 1,
                    pageSize: 10,
                    url:'./table-desgin.json'
                },
                addVisible:false,
                editVisible: false,
                tableVisible:false,
                activeName: 'second',
                form: {name:'',comment:'',primary:'',auto:false,colList:[]},
                rules: {
                  name: [
                    { required: true, message: '请输入表名', trigger: 'blur' }
                  ],
                  comment: [
                    { required: true, message: '请填写备注', trigger: 'blur' }
                  ],
                  primary: [
                    { required: true, message: '请输入主键', trigger: 'blur' }
                  ]
                },
                tableRules:{
                  name: [
                    { required: true, message: '请输入字段名', trigger: 'blur' }
                  ],
                  type: [
                    { required: true, message: '请选择类型', trigger: 'change' }
                  ],
                  length: [
                    { required: true, type:"integer",message: '请输入长度', trigger: 'blur' }
                  ],
                  comment: [
                    { required: true, message: '请填写备注', trigger: 'blur' }
                  ]
                },
                formItemRules:{
                    ip: [
                        { required: true, message: '请填写IP', trigger: 'blur' }
                    ],
                    port: [
                        { required: true, message: '请填写端口号', trigger: 'blur' }
                    ],
                    username: [
                        { required: true,message: '请填写用户名', trigger: 'blur' }
                    ],
                    password: [
                        { required: true, message: '请填写密码', trigger: 'blur' }
                    ],
                    dataName: [
                        { required: true, message: '请填写数据库名称', trigger: 'blur' }
                    ]
                },
                activeTable:0,
                styles: {
                    height: 'calc(100% - 55px)',
                    overflow: 'auto',
                    paddingBottom: '53px',
                    position: 'static'
                },
                formItem: {
                    ip: '',
                    port: '',
                    username: '',
                    password: '',
                    dataName: '',
                    save:true,
                    not:false
                },
                tableList:[
                  {
                    "name":"表名",
                    "comment":"22",
                    "primary":"字段名1",
                    "auto":true,
                    "colList":[
                      {
                        "name":"字段名1",
                        "type":"int",
                        "default":'11',
                        "length":10,
                        "comment":"第一个字段"
                      },
                      {
                        "name":"字段名2",
                        "type":"varchar",
                        "default":'22',
                        "length":20,
                        "comment":"第二个字段"

                      }
                    ]
                  }
                ]
            };
        },
        mounted(){
            // fetchData(this.query).then(res => {
            //     console.log(res);
            //     this.tableList = res.tableList;
            // });
        },
        methods:{
            saveAll(){
                if(this.formItem.not){
                    this.$Message.loading("数据生成中...");
                    return;
                }
                this.$refs['formItem'].validate((valid) => {
                    if(valid){

                    }else{
                        this.$Message.error("Error Submit!");
                    }
                })

            },
            // 回显表信息
            handleEdit(index) {
                this.form.name = this.tableList[this.activeTable].name ;
                this.form.comment = this.tableList[this.activeTable].comment;
                this.form.primary = this.tableList[this.activeTable].primary;
                this.form.auto = this.tableList[this.activeTable].auto;
            },
          //显示编辑表窗口
            handleAdd(index){
                this.addVisible = true;
                this.activeTable = index;
                this.handleEdit(index);
                this.colList = this.cloneObjectFn(this.tableList[this.activeTable].colList);
            },
          //添加行
            addRow(){
                this.colList.push({name:'',type:'',length:0,default:'',comment:''})
            },
            // 对象复制
            cloneObjectFn(obj) {
                return JSON.parse(JSON.stringify(obj))
            },
          //添加表
            saveTable(){
              this.$refs['addForm'].validate((valid) => {
                if (valid) {
                  let obj = this.cloneObjectFn(this.form);
                  console.log(obj.auto)
                  if(obj.primary){
                    obj.colList.push({name:obj.primary,type:'varchar',length:'10',comment:'主键'})
                  }
                  this.tableList.push(obj);
                  this.$Message.success('数据表【'+obj.name+'】新增成功！');
                } else {
                  this.$Message.error('Error Submit!');
                }
              });

            },
          //删除表
            removeTable(index){
              this.$Modal.confirm({
                title: '确认删除',
                content: '<p>此操作不可撤销。</p>',
                onOk: () => {
                  this.tableList.splice(index,1)
                  this.$Message.info('Success!');
                },
                onCancel: () => {
                  this.$Message.info('Cancel!');
                }
              });

            },
          //删除行
            removeRow(index){
                this.colList.splice(index,1)
            },
          //保存修改表字段
            saveAdd(){
                console.log(this.colList)
                console.log(this.activeTable)
              let _this = this;
              let flag = false;
              console.log(this.$refs.colList)
              this.$refs['addForm'].validate((valid) => {
                if (valid) {
                  this.$refs.colList.forEach(item =>{
                    if(flag){
                      return ;
                    }
                    item.validate((valid1) => {
                      if (valid1) {
                      }else{
                        _this.$Message.error('Error Submit!');
                        flag = true ;
                      }
                    })
                  })
                  if(!flag){
                    _this.tableList[this.activeTable].colList = _this.colList;
                    _this.saveEdit();
                    _this.addVisible = false;
                    _this.$Message.success('Success!');
                  }
                } else {
                  this.$Message.error('Base Error Submit!');
                }
              })
            },
          //保存修改表信息
            saveEdit(){
                this.tableList[this.activeTable].name = this.form.name;
                this.tableList[this.activeTable].comment = this.form.comment;
                this.tableList[this.activeTable].primary = this.form.primary;
                this.tableList[this.activeTable].auto = this.form.auto;
                console.log(this.tableList[this.activeTable].colList);
                this.editVisible = false;
            }

        }
    }
</script>
<style  scoped>
.demo-drawer-footer{
    width: 100%;
    position: absolute;
    bottom: 0;
    left: 0;
    border-top: 1px solid #e8e8e8;
    padding: 10px 16px;
    text-align: right;
    background: #fff;
}
.butt{
  margin-top: 10px;
  margin-left: 10px;
  font-size: 24px;
}
.redDot{
  display: inline-block;
  margin-right: 4px;
  line-height: 1;
  font-family: SimSun;
  font-size: 14px;
  color: #ed4014;
}
/deep/.ivu-card-extra{
  top:12px;
}
/deep/.ivu-card-head{
  background: #fff;
}
.addRow{
  float: right;
  font-size: 20px;
  cursor: pointer;
}
.edit{
  position:absolute;
  right: -18px;
  bottom:50%;
  width: 60px;
  height: 110px;
  background: #bbe9f6;
  border-radius: 5px;
}
.removeRow{
  float: right;
  font-size: 20px;
  cursor: pointer;
  height: 30px;
  line-height: 30px;
}
.text {
  font-size: 14px;
}

.item {
  margin-bottom: 8px;
}

.clearfix:before,
.clearfix:after {
  display: table;
  content: "";
}
.clearfix:after {
  clear: both
}

.box-card {
  width: 240px;
}
</style>
