<template>
  <div class="app-container">
    <el-form :model="queryParams" ref="queryForm" :inline="true" v-show="showSearch" label-width="68px">
      <el-form-item label="平台名称" prop="platform">
        <el-input
          v-model="queryParams.platform"
          placeholder="请输入平台名称"
          clearable
          size="small"
          @keyup.enter.native="handleQuery"
        />
      </el-form-item>
      <el-form-item>
        <el-button type="primary" icon="el-icon-search" size="mini" @click="handleQuery">搜索</el-button>
        <el-button icon="el-icon-refresh" size="mini" @click="resetQuery">重置</el-button>
      </el-form-item>
    </el-form>

    <el-row :gutter="10" class="mb8">
      <el-col :span="1.5">
        <el-button
          type="primary"
          plain
          icon="el-icon-plus"
          size="mini"
          @click="handleAdd"
          v-hasPermi="['system:platform:add']"
        >新增</el-button>
      </el-col>
      <el-col :span="1.5">
        <el-button
          type="success"
          plain
          icon="el-icon-edit"
          size="mini"
          :disabled="single"
          @click="handleUpdate"
          v-hasPermi="['system:platform:edit']"
        >修改</el-button>
      </el-col>
      <el-col :span="1.5">
        <el-button
          type="danger"
          plain
          icon="el-icon-delete"
          size="mini"
          :disabled="multiple"
          @click="handleDelete"
          v-hasPermi="['system:platform:remove']"
        >删除</el-button>
      </el-col>
      <right-toolbar :showSearch.sync="showSearch" @queryTable="getList"></right-toolbar>
    </el-row>

    <el-table v-loading="loading" :data="platformList" @selection-change="handleSelectionChange">
      <el-table-column type="selection" width="55" align="center" />
      <el-table-column label="平台" align="center" prop="platform" />
      <el-table-column label="官网" align="center" prop="website"/>
      <el-table-column label="合约地址" align="center" prop="contractAddress"/>
      <el-table-column label="类别" align="center" prop="type"/>
      <el-table-column label="标签" align="center" prop="tags"/>
      <el-table-column label="Twitter" align="center" prop="twitter"/>
      <el-table-column label="Email" align="center" prop="email"/>
      <el-table-column label="Discord" align="center" prop="discord"/>
      <el-table-column label="Telegram" align="center" prop="telegram"/>
      <el-table-column label="Github" align="center" prop="github"/>
      <el-table-column label="Medium" align="center" prop="medium"/>
      <el-table-column label="描述(英文)" align="center" prop="descriptionEn"/>
      <el-table-column label="描述(中文)" align="center" prop="descriptionCn"/>
      <el-table-column label="操作" align="center" class-name="small-padding fixed-width">
        <template slot-scope="scope">
          <el-button
            size="mini"
            type="text"
            icon="el-icon-edit"
            @click="handleUpdate(scope.row)"
            v-hasPermi="['system:platform:edit']"
          >修改</el-button>
          <el-button
            size="mini"
            type="text"
            icon="el-icon-delete"
            @click="handleDelete(scope.row)"
            v-hasPermi="['system:platform:remove']"
          >删除</el-button>
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

    <!-- 添加或修改平台对话框 -->
    <el-dialog :title="title" :visible.sync="open" width="500px" append-to-body>
      <el-form ref="form" :model="form" :rules="rules" label-width="80px">
        <el-form-item label="平台名称" prop="title">
          <el-input v-model="form.platform" placeholder="请输入平台名称" />
        </el-form-item>
        <el-form-item label="官网" prop="website">
          <el-input v-model="form.website" placeholder="请输入官网" />
        </el-form-item>
        <el-form-item label="合约地址" prop="contractAddress">
          <el-input v-model="form.contractAddress" placeholder="请输入合约地址" />
        </el-form-item>

        <el-form-item label="类别" prop="type">
          <el-input v-model="form.type" placeholder="请输入类别" />
        </el-form-item>
        <el-form-item label="标签" prop="tags">
          <el-input v-model="form.tags" placeholder="请输入标签" />
        </el-form-item>
        <el-form-item label="Twitter" prop="twitter">
          <el-input v-model="form.twitter" placeholder="请输入Twitter地址" />
        </el-form-item>
        <el-form-item label="Email" prop="email">
          <el-input v-model="form.email" placeholder="请输入Email" />
        </el-form-item>
        <el-form-item label="Discord" prop="discord">
          <el-input v-model="form.discord" placeholder="请输入Discord" />
        </el-form-item>
        <el-form-item label="Telegram" prop="telegram">
          <el-input v-model="form.telegram" placeholder="请输入telegram" />
        </el-form-item>
        <el-form-item label="Github" prop="github">
        <el-input v-model="form.github" placeholder="请输入Github" />
      </el-form-item>
        <el-form-item label="Medium" prop="medium">
          <el-input v-model="form.medium" placeholder="请输入Medium" />
        </el-form-item>
        <el-form-item label="描述(英文)" prop="descriptionEn">
          <el-input v-model="form.descriptionEn" placeholder="请输入描述信息" />
        </el-form-item>
        <el-form-item label="描述(中文)" prop="descriptionCn">
          <el-input v-model="form.descriptionCn" placeholder="请输入描述信息" />
        </el-form-item>


      </el-form>
      <div slot="footer" class="dialog-footer">
        <el-button type="primary" @click="submitForm">确 定</el-button>
        <el-button @click="cancel">取 消</el-button>
      </div>
    </el-dialog>
  </div>
</template>

<script>
import { listPlatform, getPlatform, delPlatform, addPlatform, updatePlatform } from "@/api/system/platform";

export default {
  name: "Platform",
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
      // 平台表格数据
      platformList: [],
      // 弹出层标题
      title: "",
      // 是否显示弹出层
      open: false,
      // 查询参数
      queryParams: {
        pageNum: 1,
        pageSize: 10,
        platform: undefined
      },
      // 表单参数
      form: {},
      // 表单校验
      rules: {
        platform: [
          { required: true, message: "平台不能为空", trigger: "blur" }
        ],
        website: [
          { required: true, message: "官网不能为空", trigger: "blur" }
        ]
      }
    };
  },
  methods: {
    /** 查询平台列表 */
    getList() {
      this.loading = true;
      listPlatform(this.queryParams).then(response => {
        this.platformList = response.rows;
        this.total = response.total;
        this.loading = false;
      });
    },
    // 取消按钮
    cancel() {
      this.open = false;
      this.reset();
    },
    // 表单重置
    reset() {
      this.form = {
        id: undefined,
        platform: undefined,
        website: undefined,
        contractAddress: undefined,
        uriDescription: undefined,
        uriAddress: undefined
      };
      this.resetForm("form");
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
    // 多选框选中数据
    handleSelectionChange(selection) {
      this.ids = selection.map(item => item.id)
      this.single = selection.length!=1
      this.multiple = !selection.length
    },
    /** 新增按钮操作 */
    handleAdd() {
      this.reset();
      this.open = true;
      this.title = "添加平台";
    },
    /** 修改按钮操作 */
    handleUpdate(row) {
      this.reset();
      const platformId = row.id || this.ids
      getPlatform(platformId).then(response => {
        this.form = response.data;
        this.open = true;
        this.title = "修改平台";
      });
    },
    /** 提交按钮 */
    submitForm: function() {
      this.$refs["form"].validate(valid => {
        if (valid) {
          if (this.form.id != undefined) {
            updatePlatform(this.form).then(response => {
              this.msgSuccess("修改成功");
              this.open = false;
              this.getList();
            });
          } else {
            addPlatform(this.form).then(response => {
              this.msgSuccess("新增成功");
              this.open = false;
              this.getList();
            });
          }
        }
      });
    },
    /** 删除按钮操作 */
    handleDelete(row) {
      const platformIds = row.id || this.ids;
      this.$confirm('是否确认删除平台编号为"' + platformIds + '"的数据项?', "警告", {
          confirmButtonText: "确定",
          cancelButtonText: "取消",
          type: "warning"
        }).then(function() {
          return delPlatform(platformIds);
        }).then(() => {
          this.getList();
          this.msgSuccess("删除成功");
        })
    },
    /** 导出按钮操作 */
    handleExport() {
      this.download('system/platform/export', {
        ...this.queryParams
      }, `info_${new Date().getTime()}.xlsx`)
    }
  }
};
</script>
