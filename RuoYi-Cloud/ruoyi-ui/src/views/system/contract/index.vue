<template>
  <div class="app-container">
    <el-form :model="queryParams" ref="queryForm" :inline="true" v-show="showSearch" label-width="68px">
      <el-form-item label="铸造地址" prop="foundryContractAddress">
        <el-input
          v-model="queryParams.foundryContractAddress"
          placeholder="请输入铸造协议合约地址"
          clearable
          size="small"
          @keyup.enter.native="handleQuery"
        />
      </el-form-item>
      <el-form-item label="铸造类别" prop="foundryContractType">
        <el-select v-model="queryParams.foundryContractType" placeholder="请选择铸造协议类别" clearable size="small">
          <el-option
            v-for="dict in contractTypeOptions"
            :key="dict.dictValue"
            :label="dict.dictLabel"
            :value="dict.dictValue"
          />
        </el-select>
      </el-form-item>
      <el-form-item>
        <el-button type="primary" icon="el-icon-search" size="mini" @click="handleQuery">搜索</el-button>
        <el-button icon="el-icon-refresh" size="mini" @click="resetQuery">重置</el-button>
      </el-form-item>
    </el-form>

    <el-row :gutter="10" class="mb8">
<!--      <el-col :span="1.5">
        <el-button
          type="primary"
          plain
          icon="el-icon-plus"
          size="mini"
          @click="handleAdd"
          v-hasPermi="['system:contract:add']"
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
          v-hasPermi="['system:contract:edit']"
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
          v-hasPermi="['system:contract:remove']"
        >删除</el-button>
      </el-col>-->
      <el-col :span="1.5">
        <el-button
          type="warning"
          plain
          icon="el-icon-download"
          size="mini"
          @click="handleExport"
          v-hasPermi="['system:contract:export']"
        >导出</el-button>
      </el-col>
      <right-toolbar :showSearch.sync="showSearch" @queryTable="getList"></right-toolbar>
    </el-row>

    <el-table v-loading="loading" :data="contractList" @selection-change="handleSelectionChange">
      <el-table-column type="selection" width="55" align="center" />
      <el-table-column label="区块链网络" align="center" prop="blockchainNetwork" show-overflow-tooltip/>
      <el-table-column label="铸造协议合约地址" align="center" prop="foundryContractAddress" show-overflow-tooltip/>
      <el-table-column label="铸造协议类别" align="center" prop="foundryContractType" show-overflow-tooltip />
      <el-table-column label="铸造协议名称" align="center" prop="foundryContractName" show-overflow-tooltip/>
      <el-table-column label="官方网站" align="center" prop="officialWebsite" show-overflow-tooltip/>
      <el-table-column label="铸造合约特性描述" align="center" prop="foundryContractDescription" show-overflow-tooltip/>
      <el-table-column label="Twitter" align="center" prop="twitter" show-overflow-tooltip/>
      <el-table-column label="Email" align="center" prop="email" show-overflow-tooltip/>
      <el-table-column label="uri" align="center" prop="uri" show-overflow-tooltip/>
      <el-table-column label="元数据" align="center" prop="metadata" show-overflow-tooltip/>
<!--      <el-table-column label="操作" align="center" class-name="small-padding fixed-width">
        <template slot-scope="scope">
          <el-button
            size="mini"
            type="text"
            icon="el-icon-edit"
            @click="handleUpdate(scope.row)"
            v-hasPermi="['system:contract:edit']"
          >修改</el-button>
          <el-button
            size="mini"
            type="text"
            icon="el-icon-delete"
            @click="handleDelete(scope.row)"
            v-hasPermi="['system:contract:remove']"
          >删除</el-button>
        </template>
      </el-table-column>-->
    </el-table>
    
    <pagination
      v-show="total>0"
      :total="total"
      :page.sync="queryParams.pageNum"
      :limit.sync="queryParams.pageSize"
      @pagination="getList"
    />

    <!-- 添加或修改userContract对话框 -->
    <el-dialog :title="title" :visible.sync="open" width="500px" append-to-body>
      <el-form ref="form" :model="form" :rules="rules" label-width="80px">
        <el-form-item label="区块链网络" prop="blockchainNetwork">
          <el-input v-model="form.blockchainNetwork" placeholder="请输入区块链网络" />
        </el-form-item>
        <el-form-item label="铸造协议合约地址" prop="foundryContractAddress">
          <el-input v-model="form.foundryContractAddress" placeholder="请输入铸造协议合约地址" />
        </el-form-item>
        <el-form-item label="铸造协议类别" prop="foundryContractType">
          <el-select v-model="form.foundryContractType" placeholder="请选择铸造协议类别">
            <el-option label="请选择字典生成" value="" />
          </el-select>
        </el-form-item>
        <el-form-item label="铸造协议名称" prop="foundryContractName">
          <el-input v-model="form.foundryContractName" placeholder="请输入铸造协议名称" />
        </el-form-item>
        <el-form-item label="官方网站" prop="officialWebsite">
          <el-input v-model="form.officialWebsite" placeholder="请输入官方网站" />
        </el-form-item>
        <el-form-item label="铸造合约特性描述" prop="foundryContractDescription">
          <el-input v-model="form.foundryContractDescription" placeholder="请输入铸造合约特性描述" />
        </el-form-item>
        <el-form-item label="Twitter" prop="twitter">
          <el-input v-model="form.twitter" placeholder="请输入Twitter" />
        </el-form-item>
        <el-form-item label="Email" prop="email">
          <el-input v-model="form.email" placeholder="请输入Email" />
        </el-form-item>
        <el-form-item label="uri" prop="uri">
          <el-input v-model="form.uri" placeholder="请输入uri" />
        </el-form-item>
        <el-form-item label="元数据" prop="metadata">
          <el-input v-model="form.metadata" type="textarea" placeholder="请输入内容" />
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
import { listContract, getContract, delContract, addContract, updateContract } from "@/api/system/contract";

export default {
  name: "Contract",
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
      // userContract表格数据
      contractList: [],
      // 弹出层标题
      title: "",
      // 是否显示弹出层
      open: false,
      // 铸造类型
      contractTypeOptions: [],
      // 查询参数
      queryParams: {
        pageNum: 1,
        pageSize: 10,
        blockchainNetwork: null,
        foundryContractAddress: null,
        foundryContractType: null,
        foundryContractName: null,
        officialWebsite: null,
        foundryContractDescription: null,
        twitter: null,
        email: null,
        uri: null,
        metadata: null
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
    this.getDicts("contract_category").then(response => {
      this.contractTypeOptions = response.data;
    });
  },
  methods: {
    /** 查询userContract列表 */
    getList() {
      this.loading = true;
      listContract(this.queryParams).then(response => {
        this.contractList = response.rows;
        this.total = response.total;
        this.loading = false;
      });
    },

    // 数据状态字典翻译
    contractTypeFormat(row, column) {
      return this.selectDictLabel(this.contractTypeOptions, row.foundryContractType);
    },
    // 取消按钮
    cancel() {
      this.open = false;
      this.reset();
    },
    // 表单重置
    reset() {
      this.form = {
        id: null,
        blockchainNetwork: null,
        foundryContractAddress: null,
        foundryContractType: null,
        foundryContractName: null,
        officialWebsite: null,
        foundryContractDescription: null,
        twitter: null,
        email: null,
        uri: null,
        metadata: null
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
      this.single = selection.length!==1
      this.multiple = !selection.length
    },
    /** 新增按钮操作 */
    handleAdd() {
      this.reset();
      this.open = true;
      this.title = "添加userContract";
    },
    /** 修改按钮操作 */
    handleUpdate(row) {
      this.reset();
      const id = row.id || this.ids
      getContract(id).then(response => {
        this.form = response.data;
        this.open = true;
        this.title = "修改userContract";
      });
    },
    /** 提交按钮 */
    submitForm() {
      this.$refs["form"].validate(valid => {
        if (valid) {
          if (this.form.id != null) {
            updateContract(this.form).then(response => {
              this.msgSuccess("修改成功");
              this.open = false;
              this.getList();
            });
          } else {
            addContract(this.form).then(response => {
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
      const ids = row.id || this.ids;
      this.$confirm('是否确认删除userContract编号为"' + ids + '"的数据项?', "警告", {
          confirmButtonText: "确定",
          cancelButtonText: "取消",
          type: "warning"
        }).then(function() {
          return delContract(ids);
        }).then(() => {
          this.getList();
          this.msgSuccess("删除成功");
        })
    },
    /** 导出按钮操作 */
    handleExport() {
      this.download('system/contract/export', {
        ...this.queryParams
      }, `system_contract.xlsx`)
    }
  }
};
</script>
