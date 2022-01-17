<template>
  <div class="app-container">
    <el-form :model="queryParams" ref="queryForm" :inline="true" v-show="showSearch" label-width="68px">
      <el-form-item label="合约地址" prop="contract">
        <el-input
          v-model="queryParams.contract"
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

    <el-table v-loading="loading" :data="UserSubmittedContractList" @selection-change="handleSelectionChange">
      <el-table-column type="selection" width="55" align="center" />
      <el-table-column label="项目logo" align="center">
        <template slot-scope="scope">
          <img :src="scope.row.logo" height="80"/>
        </template>
      </el-table-column>
      <el-table-column label="合约地址" align="center" prop="contract" />
      <el-table-column label="NFT项目名称" align="center" prop="contractName" />
      <el-table-column label="项目描述" align="center" prop="description" />
      <el-table-column label="twitter" align="center" prop="twitter" />
      <el-table-column label="discord" align="center" prop="discord" />
      <el-table-column label="website" align="center" prop="website" />
      <el-table-column label="telegram" align="center" prop="telegram" />
      <el-table-column label="medium" align="center" prop="medium" />
      <el-table-column label="mirror" align="center" prop="mirror" />
      <el-table-column label="facebook" align="center" prop="facebook" />
      <!--<el-table-column label="github" align="center" prop="github" />-->
      <!--<el-table-column label="youtube" align="center" prop="youtube" />-->
      <!--<el-table-column label="twitch" align="center" prop="twitch" />-->
      <!--<el-table-column label="email" align="center" prop="email" />-->
      <!--<el-table-column label="ins" align="center" prop="ins" />-->
      <!--<el-table-column label="metadata_url" align="center" prop="metadataUrl" />-->
      <!--<el-table-column label="statement" align="center" prop="statement" />-->
      <el-table-column label="状态" align="center" prop="status" :formatter="statusFormat" />

      <el-table-column label="审核" align="center" class-name="small-padding fixed-width">
        <template slot-scope="scope">
          <el-button
            size="mini"
            type="text"
            icon="el-icon-edit"
            @click="handleUpdate(scope.row)"
            v-hasPermi="['system:UserSubmittedContract:edit']"
          >审核</el-button>
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

    <!-- 添加或修改NFT项目信息提交对话框 -->
    <el-dialog :title="title" :visible.sync="open" width="500px" append-to-body>
      <el-form ref="form" :model="form" :rules="rules" label-width="80px">
        <el-form-item label="合约地址" prop="contract">
          <el-input v-model="form.contract" disabled="true"/>
        </el-form-item>
        <el-form-item label="项目logo" prop="logo" hidden>
          <el-input v-model="form.logo"/>
        </el-form-item>
        <el-form-item label="项目名称" prop="contractName">
          <el-input v-model="form.contractName" disabled="true"/>
        </el-form-item>

        <el-form-item label="项目分类" prop="contractCategory" hidden>
          <el-input v-model="form.contractCategory" disabled="true"/>
        </el-form-item>

        <el-form-item label="twitter" prop="twitter">
          <el-input v-model="form.twitter" disabled="true"/>
        </el-form-item>
        <el-form-item label="discord" prop="discord">
          <el-input v-model="form.discord" disabled="true"/>
        </el-form-item>
        <el-form-item label="website" prop="website">
          <el-input v-model="form.website" disabled="true"/>
        </el-form-item>
        <el-form-item label="telegram" prop="telegram">
          <el-input v-model="form.telegram" disabled="true"/>
        </el-form-item>
        <el-form-item label="medium" prop="medium">
          <el-input v-model="form.medium" disabled="true"/>
        </el-form-item>
        <el-form-item label="mirror" prop="mirror">
          <el-input v-model="form.mirror" disabled="true"/>
        </el-form-item>
        <el-form-item label="facebook" prop="facebook">
          <el-input v-model="form.facebook" disabled="true"/>
        </el-form-item>
        <el-form-item label="github" prop="github">
          <el-input v-model="form.github" disabled="true"/>
        </el-form-item>
        <el-form-item label="youtube" prop="youtube">
          <el-input v-model="form.youtube" disabled="true"/>
        </el-form-item>
        <el-form-item label="twitch" prop="twitch">
          <el-input v-model="form.twitch" disabled="true"/>
        </el-form-item>
        <el-form-item label="email" prop="email">
          <el-input v-model="form.email" disabled="true"/>
        </el-form-item>
        <el-form-item label="ins" prop="ins">
          <el-input v-model="form.ins" disabled="true"/>
        </el-form-item>
        <el-form-item label="metadata" prop="metadataUrl">
          <el-input v-model="form.metadataUrl" disabled="true"/>
        </el-form-item>
        <el-form-item label="statement" prop="statement">
          <el-input v-model="form.statement" disabled="true"/>
        </el-form-item>

        <el-form-item label="项目描述" prop="description">
          <el-input class="myDescription" v-model="form.description" type="textarea" disabled="true"/>
        </el-form-item>

        <el-form-item label="状态" prop="status">
          <el-select v-model="form.status">
            <el-option
              v-for="dict in statusOptions"
              :key="dict.dictValue"
              :label="dict.dictLabel"
              :value="parseInt(dict.dictValue)"
            ></el-option>
          </el-select>
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
import { listUserSubmittedContract, getUserSubmittedContract, delUserSubmittedContract, addUserSubmittedContract, updateUserSubmittedContract } from "@/api/system/UserSubmittedContract";

export default {
  name: "UserSubmittedContract",
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
      // NFT项目信息提交表格数据
      UserSubmittedContractList: [],
      // 弹出层标题
      title: "",
      // 是否显示弹出层
      open: false,
      // 状态字典
      statusOptions: [],
      // 查询参数
      queryParams: {
        pageNum: 1,
        pageSize: 10,
        contract: null,
        status: null,
        logo: null,
        contractName: null,
        contractCategory: null,
        description: null,
        twitter: null,
        discord: null,
        website: null,
        telegram: null,
        medium: null,
        mirror: null,
        facebook: null,
        github: null,
        youtube: null,
        twitch: null,
        metadataUrl: null,
        statement: null
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
    this.getDicts("sys_check_status").then(response => {
      this.statusOptions = response.data;
    });
  },
  methods: {
    /** 查询NFT项目信息提交列表 */
    getList() {
      this.loading = true;
      listUserSubmittedContract(this.queryParams).then(response => {
        this.UserSubmittedContractList = response.rows;
        this.total = response.total;
        this.loading = false;
      });
    },
    // 状态字典翻译
    statusFormat(row, column) {
      return this.selectDictLabel(this.statusOptions, row.status);
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
        contract: null,
        createTime: null,
        status: null,
        logo: null,
        contractName: null,
        contractCategory: null,
        description: null,
        twitter: null,
        discord: null,
        website: null,
        telegram: null,
        medium: null,
        mirror: null,
        facebook: null,
        github: null,
        youtube: null,
        twitch: null,
        metadataUrl: null,
        statement: null
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
      this.title = "添加NFT项目信息提交";
    },
    /** 修改按钮操作 */
    handleUpdate(row) {
      this.reset();
      const id = row.id || this.ids
      getUserSubmittedContract(id).then(response => {
        this.form = response.data;
        this.open = true;
        this.title = "NFT项目信息审核";
      });
    },
    /** 提交按钮 */
    submitForm() {
      this.$refs["form"].validate(valid => {
        if (valid) {
          if (this.form.id != null) {
            updateUserSubmittedContract(this.form).then(response => {
              this.msgSuccess("修改成功");
              this.open = false;
              this.getList();
            });
          } else {
            addUserSubmittedContract(this.form).then(response => {
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
      this.$confirm('是否确认删除NFT项目信息提交编号为"' + ids + '"的数据项?', "警告", {
          confirmButtonText: "确定",
          cancelButtonText: "取消",
          type: "warning"
        }).then(function() {
          return delUserSubmittedContract(ids);
        }).then(() => {
          this.getList();
          this.msgSuccess("删除成功");
        })
    },
    /** 导出按钮操作 */
    handleExport() {
      this.download('system/UserSubmittedContract/export', {
        ...this.queryParams
      }, `system_UserSubmittedContract.xlsx`)
    }
  }
};
</script>
