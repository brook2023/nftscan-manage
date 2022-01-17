<template>
  <div class="app-container">
    <el-form :model="queryParams" ref="queryForm" :inline="true" v-show="showSearch" label-width="68px">
      <el-form-item label="合约地址" prop="contract">
        <el-input
          v-model="queryParams.name"
          placeholder="请输入合约地址"
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
          v-hasPermi="['system:tradeContract:add']"
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
          v-hasPermi="['system:tradeContract:edit']"
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
          v-hasPermi="['system:tradeContract:remove']"
        >删除</el-button>
      </el-col>
      <el-col :span="1.5">
        <el-button
          type="warning"
          plain
          icon="el-icon-download"
          size="mini"
          @click="handleExport"
          v-hasPermi="['system:tradeContract:export']"
        >导出</el-button>
      </el-col>
      <right-toolbar :showSearch.sync="showSearch" @queryTable="getList"></right-toolbar>
    </el-row>

    <el-table v-loading="loading" :data="tradeContractList" @selection-change="handleSelectionChange">
      <el-table-column type="selection" width="55" align="center" />
      <el-table-column label="合约地址" align="center" prop="contract" />
      <el-table-column label="合约名称" align="center" prop="name" />
      <el-table-column label="官网" align="center" prop="website" />
      <el-table-column label="twitter" align="center" prop="twitter" />
      <el-table-column label="邮箱" align="center" prop="email" />
      <el-table-column label="discord" align="center" prop="discord" />
      <el-table-column label="telegram" align="center" prop="telegram" />
      <el-table-column label="描述" align="center" prop="description" />
      <el-table-column label="instagram" align="center" prop="instagram" />
      <el-table-column label="标签" align="center" prop="tag" />
      <el-table-column label="余额" align="center" prop="balance" />
      <el-table-column label="部署时间" align="center" prop="deploymentTime" width="180">
        <template slot-scope="scope">
          <span>{{ parseTime(scope.row.deploymentTime, '{y}-{m}-{d}') }}</span>
        </template>
      </el-table-column>
      <el-table-column label="创建地址" align="center" prop="createContract" />
      <el-table-column label="创建hash" align="center" prop="createHash" />
      <el-table-column label="操作" align="center" class-name="small-padding fixed-width">
        <template slot-scope="scope">
          <el-button
            size="mini"
            type="text"
            icon="el-icon-edit"
            @click="handleUpdate(scope.row)"
            v-hasPermi="['system:tradeContract:edit']"
          >修改</el-button>
          <el-button
            size="mini"
            type="text"
            icon="el-icon-delete"
            @click="handleDelete(scope.row)"
            v-hasPermi="['system:tradeContract:remove']"
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

    <!-- 添加或修改tradeContract对话框 -->
    <el-dialog :title="title" :visible.sync="open" width="500px" append-to-body>
      <el-form ref="form" :model="form" :rules="rules" label-width="80px">
        <el-form-item label="合约地址" prop="name">
          <el-input v-model="form.contract" placeholder="请输入合约地址" />
        </el-form-item>
        <el-form-item label="合约名称" prop="name">
          <el-input v-model="form.name" placeholder="请输入合约名称" />
        </el-form-item>
        <el-form-item label="官网" prop="website">
          <el-input v-model="form.website" placeholder="请输入官网" />
        </el-form-item>
        <el-form-item label="twitter" prop="twitter">
          <el-input v-model="form.twitter" placeholder="请输入twitter" />
        </el-form-item>
        <el-form-item label="邮箱" prop="email">
          <el-input v-model="form.email" placeholder="请输入邮箱" />
        </el-form-item>
        <el-form-item label="discord" prop="discord">
          <el-input v-model="form.discord" placeholder="请输入discord" />
        </el-form-item>
        <el-form-item label="telegram" prop="telegram">
          <el-input v-model="form.telegram" placeholder="请输入telegram" />
        </el-form-item>
        <el-form-item label="描述" prop="description">
          <el-input v-model="form.description" type="textarea" placeholder="请输入内容" />
        </el-form-item>
        <el-form-item label="instagram" prop="instagram">
          <el-input v-model="form.instagram" placeholder="请输入instagram" />
        </el-form-item>
        <el-form-item label="标签" prop="tag">
          <el-input v-model="form.tag" placeholder="请输入标签" />
        </el-form-item>
        <el-form-item label="余额" prop="balance">
          <el-input v-model="form.balance" placeholder="请输入余额" />
        </el-form-item>
        <el-form-item label="部署时间" prop="deploymentTime">
          <el-date-picker clearable size="small"
            v-model="form.deploymentTime"
            type="date"
            value-format="yyyy-MM-dd"
            placeholder="选择部署时间">
          </el-date-picker>
        </el-form-item>
        <el-form-item label="创建地址" prop="createContract">
          <el-input v-model="form.createContract" placeholder="请输入创建地址" />
        </el-form-item>
        <el-form-item label="创建hash" prop="createHash">
          <el-input v-model="form.createHash" placeholder="请输入创建hash" />
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
import { listTradeContract, getTradeContract, delTradeContract, addTradeContract, updateTradeContract } from "@/api/system/tradeContract";

export default {
  name: "TradeContract",
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
      // tradeContract表格数据
      tradeContractList: [],
      // 弹出层标题
      title: "",
      // 是否显示弹出层
      open: false,
      // 查询参数
      queryParams: {
        pageNum: 1,
        pageSize: 10,
        name: null,
        website: null,
        twitter: null,
        email: null,
        discord: null,
        telegram: null,
        description: null,
        github: null,
        instagram: null,
        tag: null,
        balance: null,
        deploymentTime: null,
        createContract: null,
        createHash: null
      },
      // 表单参数
      form: {},
      // 表单校验
      rules: {
      },
      flag: ""
    };
  },
  created() {
    this.getList();
  },
  methods: {
    /** 查询tradeContract列表 */
    getList() {
      this.loading = true;
      listTradeContract(this.queryParams).then(response => {
        this.tradeContractList = response.rows;
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
        contract: null,
        name: null,
        website: null,
        twitter: null,
        email: null,
        discord: null,
        telegram: null,
        description: null,
        github: null,
        instagram: null,
        tag: null,
        balance: null,
        deploymentTime: null,
        createContract: null,
        createHash: null
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
      this.ids = selection.map(item => item.contract)
      this.single = selection.length!==1
      this.multiple = !selection.length
    },
    /** 新增按钮操作 */
    handleAdd() {
      this.reset();
      this.open = true;
      this.title = "添加tradeContract";
      this.flag = "add";
    },
    /** 修改按钮操作 */
    handleUpdate(row) {
      this.reset();
      const contract = row.contract || this.ids
      getTradeContract(contract).then(response => {
        this.form = response.data;
        this.open = true;
        this.title = "修改tradeContract";
      });
      this.flag = "update";
    },
    /** 提交按钮 */
    submitForm() {
      this.$refs["form"].validate(valid => {
        if (valid) {
          if (this.flag == "update") {
            updateTradeContract(this.form).then(response => {
              this.msgSuccess("修改成功");
              this.open = false;
              this.getList();
            });
          } else {
            addTradeContract(this.form).then(response => {
              this.msgSuccess("新增成功");
              this.open = false;
              this.getList();
            });
          }
        }
      });
    },
    /** 修改按钮 */
    updateForm() {
      this.$refs["form"].validate(valid => {
        if (valid) {
          if (this.form.contract != null) {
            updateTradeContract(this.form).then(response => {
              this.msgSuccess("修改成功");
              this.open = false;
              this.getList();
            });
          }
        }
      });
    },
    /** 删除按钮操作 */
    handleDelete(row) {
      const contracts = row.contract || this.ids;
      this.$confirm('是否确认删除tradeContract为"' + contracts + '"的数据项?', "警告", {
          confirmButtonText: "确定",
          cancelButtonText: "取消",
          type: "warning"
        }).then(function() {
          return delTradeContract(contracts);
        }).then(() => {
          this.getList();
          this.msgSuccess("删除成功");
        })
    },
    /** 导出按钮操作 */
    handleExport() {
      this.download('system/tradeContract/export', {
        ...this.queryParams
      }, `system_tradeContract.xlsx`)
    }
  }
};
</script>
