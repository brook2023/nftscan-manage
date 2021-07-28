<template>
  <div class="app-container">
    <el-form :model="queryParams" ref="queryForm" :inline="true" v-show="showSearch" label-width="100px">
      <el-form-item label="铸造合约地址" prop="methodContract">
        <el-input
          v-model="queryParams.methodContract"
          placeholder="请输入铸造合约地址"
          clearable
          size="medium"
          @keyup.enter.native="handleQuery" style="width: 400px;"
        />
      </el-form-item>
      <el-form-item label="NFT资产地址" prop="tokenContract">
        <el-input
          v-model="queryParams.tokenContract"
          placeholder="请输入NFT资产地址"
          clearable
          size="medium"
          @keyup.enter.native="handleQuery" style="width: 400px;"
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
          type="success"
          plain
          icon="el-icon-edit"
          size="mini"
          :disabled="single"
          @click="handleUpdate"
          v-hasPermi="['system:asset:edit']"
        >编辑标签</el-button>
      </el-col>
      <el-col :span="1.5">
        <el-button
          type="warning"
          plain
          icon="el-icon-download"
          size="mini"
          @click="handleExport"
          v-hasPermi="['system:asset:export']"
        >导出</el-button>
      </el-col>
      <right-toolbar :showSearch.sync="showSearch" @queryTable="getList"></right-toolbar>
    </el-row>

    <el-table v-loading="loading" :data="assetList" stripe @selection-change="handleSelectionChange" tooltip-effect="dark">
      <el-table-column type="selection" width="55" align="center" />
      <el-table-column label="铸造合约地址" align="center" prop="methodContract" show-overflow-tooltip/>
      <el-table-column label="NFT资产地址" align="center" prop="tokenContract" show-overflow-tooltip/>
      <el-table-column label="NFT资产ID" align="center" prop="tokenId" show-overflow-tooltip/>
      <el-table-column label="标签" align="center" prop="tag" show-overflow-tooltip/>
      <el-table-column label="操作" align="center" class-name="small-padding fixed-width">
        <template slot-scope="scope">
          <el-button
            size="mini"
            type="text"
            icon="el-icon-edit"
            @click="handleUpdate(scope.row)"
            v-hasPermi="['system:asset:edit']"
          >修改</el-button>
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

    <!-- 添加或修改【请填写功能名称】对话框 -->
    <el-dialog :title="title" :visible.sync="open" width="600px" append-to-body>
      <el-form ref="form" :model="form" :rules="rules" label-width="100px">
        <el-form-item label="铸造合约" prop="methodContract">
          <el-input v-model="form.methodContract" readonly/>
        </el-form-item>
        <el-form-item label="NFT资产地址" prop="tokenContract">
          <el-input v-model="form.tokenContract" readonly/>
        </el-form-item>
        <el-form-item label="NFT资产ID" prop="tokenId">
          <el-input v-model="form.tokenId" readonly/>
        </el-form-item>
        <el-form-item label="标签" prop="tag">
          <el-select class="myOption" v-model="form.tag" filterable multiple placeholder="请选择">
            <el-option
              v-for="item in tagOptions"
              :key="item.id"
              :label="item.tag"
              :value="item.id">
            </el-option>
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
import { listAsset, getAsset, delAsset, addAsset, updateAsset, getAllTags } from "@/api/system/asset";
import ImageUpload from '@/components/ImageUpload';

export default {
  name: "Asset",
  components: {
    ImageUpload,
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
      // 【请填写功能名称】表格数据
      assetList: [],
      // 弹出层标题
      title: "",
      // 是否显示弹出层
      open: false,
      // 查询参数
      queryParams: {
        pageNum: 1,
        pageSize: 10,
        methodContract: null,
        tokenContract: null,
        symbol: null,
        tokenId: null,
        txHash: null
      },
      // 表单参数
      form: {},
      // 表单校验
      rules: {
      },
      tagOptions: []
    };
  },
  created() {
    this.getList();
    this.getTags();
  },
  methods: {
    /** 查询【请填写功能名称】列表 */
    getList() {
      this.loading = true;
      listAsset(this.queryParams).then(response => {
        this.assetList = response.rows;
        this.total = response.total;
        this.loading = false;
      });
    },
    // 查询标签信息
    getTags() {
      getAllTags().then(response => {
        this.tagOptions = response.rows;
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
        id: null,
        methodContract: null,
        tokenContract: null,
        tag: []
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
      this.title = "添加【请填写功能名称】";
    },
    /** 修改按钮操作 */
    handleUpdate(row) {
      this.reset();
      const id = row.id || this.ids
      getAsset(id).then(response => {
        this.form = {...response.data,tag:response.data.tag ? response.data.tag.split(',').map(e=>Number(e)):[]};
        this.open = true;
        this.title = "编辑标签信息";
      });
    },
    /** 提交按钮 */
    submitForm() {
      this.$refs["form"].validate(valid => {
        if (valid) {
          const formData = JSON.parse(JSON.stringify(this.form));
          formData.tag = formData.tag.join(',');
          console.log(this.form)
          if (this.form.id != null) {
            updateAsset(formData).then(response => {
              this.msgSuccess("修改成功");
              this.open = false;
              this.getList();
            });
          } else {
            addAsset(formData).then(response => {
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
      this.$confirm('是否确认删除【请填写功能名称】编号为"' + ids + '"的数据项?', "警告", {
          confirmButtonText: "确定",
          cancelButtonText: "取消",
          type: "warning"
        }).then(function() {
          return delAsset(ids);
        }).then(() => {
          this.getList();
          this.msgSuccess("删除成功");
        })
    },
    /** 导出按钮操作 */
    handleExport() {
      this.download('system/asset/export', {
        ...this.queryParams
      }, `system_asset.xlsx`)
    }
  }
};
</script>

<style lang="scss">
  .myOption{
    width: 100%;
  }
</style>
