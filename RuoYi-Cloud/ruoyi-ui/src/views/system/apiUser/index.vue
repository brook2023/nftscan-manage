<template>
  <div class="app-container">
    <el-form :model="queryParams" ref="queryForm" :inline="true" v-show="showSearch" label-width="108px">
      <el-form-item label="用户登录邮箱" prop="email">
        <el-input
          v-model="queryParams.email"
          placeholder="请输入用户登录邮箱"
          clearable
          size="small"
          @keyup.enter.native="handleQuery"
        />
      </el-form-item>
      <el-form-item label="创建时间" prop="createTime">
        <el-date-picker clearable size="small"
          v-model="queryParams.createTime"
          type="date"
          value-format="yyyy-MM-dd"
          placeholder="选择创建时间">
        </el-date-picker>
      </el-form-item>
      <el-form-item>
        <el-button type="primary" icon="el-icon-search" size="mini" @click="handleQuery">搜索</el-button>
        <el-button icon="el-icon-refresh" size="mini" @click="resetQuery">重置</el-button>
      </el-form-item>
    </el-form>

    <el-row :gutter="10" class="mb8">
<!--      <el-col :span="1.5">-->
<!--        <el-button-->
<!--          type="primary"-->
<!--          plain-->
<!--          icon="el-icon-plus"-->
<!--          size="mini"-->
<!--          @click="handleAdd"-->
<!--          v-hasPermi="['system:apiUser:add']"-->
<!--        >新增</el-button>-->
<!--      </el-col>-->
<!--      <el-col :span="1.5">-->
<!--        <el-button-->
<!--          type="success"-->
<!--          plain-->
<!--          icon="el-icon-edit"-->
<!--          size="mini"-->
<!--          :disabled="single"-->
<!--          @click="handleUpdate"-->
<!--          v-hasPermi="['system:apiUser:edit']"-->
<!--        >修改</el-button>-->
<!--      </el-col>-->
<!--      <el-col :span="1.5">-->
<!--        <el-button-->
<!--          type="danger"-->
<!--          plain-->
<!--          icon="el-icon-delete"-->
<!--          size="mini"-->
<!--          :disabled="multiple"-->
<!--          @click="handleDelete"-->
<!--          v-hasPermi="['system:apiUser:remove']"-->
<!--        >删除</el-button>-->
<!--      </el-col>-->
      <el-col :span="1.5">
        <el-button
          type="warning"
          plain
          icon="el-icon-download"
          size="mini"
          @click="handleExport"
          v-hasPermi="['system:apiUser:export']"
        >导出</el-button>
      </el-col>
      <right-toolbar :showSearch.sync="showSearch" @queryTable="getList"></right-toolbar>
    </el-row>

    <el-table v-loading="loading" :data="apiUserList" >
      <el-table-column label="用户ID" align="center" prop="id" />
      <el-table-column label="用户登录邮箱" align="center" prop="email" />
      <el-table-column label="api_ID" align="center" prop="apiKey" />
      <el-table-column label="API_SECRET" align="center" prop="apiSecret" />
      <el-table-column label="创建时间" align="center" prop="createTime" width="180">
        <template slot-scope="scope">
          <span>{{ parseTime(scope.row.createTime, '{y}-{m}-{d}') }}</span>
        </template>
      </el-table-column>
      <el-table-column label="最后登录时间" align="center" prop="lastLoginTime" width="180">
        <template slot-scope="scope">
          <span>{{ parseTime(scope.row.lastLoginTime, '{y}-{m}-{d}') }}</span>
        </template>
      </el-table-column>
    </el-table>

    <pagination
      v-show="total>0"
      :total="total"
      :page.sync="queryParams.pageNum"
      :limit.sync="queryParams.pageSize"
      @pagination="getList"
    />

    <!-- 添加或修改api平台用户对话框 -->
    <el-dialog :title="title" :visible.sync="open" width="500px" append-to-body>
      <el-form ref="form" :model="form" :rules="rules" label-width="80px">
        <el-form-item label="用户登录邮箱" prop="email">
          <el-input v-model="form.email" placeholder="请输入用户登录邮箱" />
        </el-form-item>
        <el-form-item label="api_ID" prop="apiKey">
          <el-input v-model="form.apiKey" placeholder="请输入api_ID" />
        </el-form-item>
        <el-form-item label="API_SECRET" prop="apiSecret">
          <el-input v-model="form.apiSecret" placeholder="请输入API_SECRET" />
        </el-form-item>
        <el-form-item label="最后登录时间" prop="lastLoginTime">
          <el-date-picker clearable size="small"
            v-model="form.lastLoginTime"
            type="date"
            value-format="yyyy-MM-dd"
            placeholder="选择最后登录时间">
          </el-date-picker>
        </el-form-item>
      </el-form>
<!--      <div slot="footer" class="dialog-footer">-->
<!--        <el-button type="primary" @click="submitForm">确 定</el-button>-->
<!--        <el-button @click="cancel">取 消</el-button>-->
<!--      </div>-->
    </el-dialog>
  </div>
</template>

<script>
import {listApiUser } from "@/api/system/apiUser.js";

export default {
  name: "ApiUser",
  components: {
  },
  data() {
    return {
      // 遮罩层
      loading: true,
      // 选中数组
      ids: [],
      // 非单个禁用
      single: true,
      // 非多个禁用
      multiple: true,
      // 显示搜索条件
      showSearch: true,
      // 总条数
      total: 0,
      // api平台用户表格数据
      apiUserList: [],
      // 弹出层标题
      title: "",
      // 是否显示弹出层
      open: false,
      // 查询参数
      queryParams: {
        pageNum: 1,
        pageSize: 10,
        email: null,
        createTime: null,
      },
      // 表单参数
      form: {},
      // 表单校验
      rules: {
      }
    };
  },
  created() {
    this.getList();
  },
  methods: {
    /** 查询api平台用户列表 */
    getList() {
      this.loading = true;
      listApiUser(this.queryParams).then(response => {
        this.apiUserList = response.rows;
        this.total = response.total;
        this.loading = false;
      });
    },
    // 取消按钮
    cancel() {
      this.open = false;
      this.reset();
    },
    /** 搜索按钮操作 */
    handleQuery() {
      this.queryParams.pageNum = 1;
      this.getList();
    },
    /** 重置按钮操作 */
    resetQuery() {
      this.resetForm("queryForm");
      this.handleQuery();
    },



    /** 导出按钮操作 */
    handleExport() {
      this.download('system/apiUser/export', {
        ...this.queryParams
      }, `system_apiUser.xlsx`)
    }
  }
};
</script>
