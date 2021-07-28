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
      <el-form-item label="类型" prop="addressType">
        <el-select v-model="queryParams.addressType" placeholder="地址类型" clearable size="small">
          <el-option
            v-for="dict in addressTypeOptions"
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
      <el-col :span="1.5">
        <el-button
          type="primary"
          plain
          icon="el-icon-plus"
          size="mini"
          :disabled="single"
          @click="handleAddExist"
          v-hasPermi="['system:platform:add']"
        >新增(已有平台)</el-button>
      </el-col>
      <right-toolbar :showSearch.sync="showSearch" @queryTable="getList"></right-toolbar>
    </el-row>

    <el-table v-loading="loading" :data="platformList" :span-method="rowSpanMethod" stripe @selection-change="handleSelectionChange" tooltip-effect="dark">
      <el-table-column type="selection" width="55" align="center" show-overflow-tooltip/>
      <el-table-column label="平台" align="center" prop="platform" show-overflow-tooltip/>
      <el-table-column label="官网" align="center" prop="website" show-overflow-tooltip/>
      <el-table-column label="合约地址" align="center" prop="contractAddress" show-overflow-tooltip/>
      <el-table-column label="类别" align="center" prop="type" show-overflow-tooltip/>
      <el-table-column label="标签" align="center" prop="tags" show-overflow-tooltip/>
<!--      <el-table-column label="Twitter" align="center" prop="twitter" show-overflow-tooltip/>
            <el-table-column label="Email" align="center" prop="email" show-overflow-tooltip/>
            <el-table-column label="Discord" align="center" prop="discord" show-overflow-tooltip/>
            <el-table-column label="Telegram" align="center" prop="telegram" show-overflow-tooltip/>
            <el-table-column label="Github" align="center" prop="github" show-overflow-tooltip/>
            <el-table-column label="Medium" align="center" prop="medium" show-overflow-tooltip/>-->
<!--      <el-table-column label="推荐语(英文)" align="center" prop="recommendEn" show-overflow-tooltip/>
      <el-table-column label="推荐语(中文)" align="center" prop="recommendCn" show-overflow-tooltip/>-->
      <el-table-column label="地址类型" align="center" prop="addressType" :formatter="addressTypeFormat" show-overflow-tooltip/>
      <el-table-column label="是否热点推荐" align="center" key="isHot">
        <template slot-scope="scope">
          <el-switch
            v-model="scope.row.isHot"
            active-value="Y"
            inactive-value="N"
            @change="handleStatusChange(scope.row)"
          ></el-switch>
        </template>
      </el-table-column>
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
    <el-dialog :title="title" :visible.sync="open" width="600px" append-to-body>
      <el-form ref="form" :model="form" :rules="rules" label-width="120px">
        <el-form-item label="平台名称" prop="title">
          <el-input v-model="form.platform" :disabled="existPlatform" placeholder="请输入平台名称" />
        </el-form-item>
        <el-form-item label="官网" prop="website">
          <el-input v-model="form.website" :disabled="existPlatform" placeholder="请输入官网" />
        </el-form-item>
        <el-form-item label="合约地址" prop="contractAddress">
          <el-input v-model="form.contractAddress" placeholder="请输入合约地址,多个相同地址类型用 / 隔开" />
        </el-form-item>

        <el-form-item label="类别" prop="type">
          <el-input v-model="form.type" :disabled="existPlatform" placeholder="请输入类别" />
        </el-form-item>
        <el-form-item label="标签" prop="tags">
          <el-select class="myOption" v-model="form.tags" :disabled="existPlatform" filterable multiple placeholder="请选择">
            <el-option
              v-for="item in tagOptions"
              :key="item.id"
              :label="item.tag"
              :value="item.id">
            </el-option>
          </el-select>
        </el-form-item>
        <el-form-item label="Twitter" prop="twitter">
          <el-input v-model="form.twitter" :disabled="existPlatform" placeholder="请输入Twitter地址" />
        </el-form-item>
        <el-form-item label="Email" prop="email">
          <el-input v-model="form.email" :disabled="existPlatform" placeholder="请输入Email" />
        </el-form-item>
        <el-form-item label="Discord" prop="discord">
          <el-input v-model="form.discord" :disabled="existPlatform" placeholder="请输入Discord" />
        </el-form-item>
        <el-form-item label="Telegram" prop="telegram">
          <el-input v-model="form.telegram" :disabled="existPlatform" placeholder="请输入telegram" />
        </el-form-item>
        <el-form-item label="Github" prop="github">
        <el-input v-model="form.github" placeholder="请输入Github" :disabled="existPlatform"/>
      </el-form-item>
        <el-form-item label="Medium" prop="medium">
          <el-input v-model="form.medium" placeholder="请输入Medium" :disabled="existPlatform"/>
        </el-form-item>
        <el-form-item label="描述(英文)" prop="descriptionEn">
          <el-input v-model="form.descriptionEn" type="textarea" placeholder="请输入描述信息" :disabled="existPlatform"/>
        </el-form-item>
        <el-form-item label="描述(中文)" prop="descriptionCn">
          <el-input v-model="form.descriptionCn" type="textarea" placeholder="请输入描述信息" :disabled="existPlatform"/>
        </el-form-item>
        <el-form-item label="推荐语(英文)" prop="recommendEn">
          <el-input v-model="form.recommendEn" type="textarea" placeholder="请输入推荐语" :disabled="existPlatform"/>
        </el-form-item>
        <el-form-item label="推荐语(中文)" prop="recommendCn">
          <el-input v-model="form.recommendCn" type="textarea" placeholder="请输入推荐语" :disabled="existPlatform"/>
        </el-form-item>
        <el-form-item label="地址类型" prop="addressType">
          <el-select v-model="form.addressType" placeholder="请选择">
            <el-option
              v-for="dict in addressTypeOptions"
              :key="dict.dictValue"
              :label="dict.dictLabel"
              :value="dict.dictValue"
            ></el-option>
          </el-select>
        </el-form-item>
        <el-form-item label="是否为热点推荐" prop="isHot">
          <el-radio-group v-model="form.isHot" :disabled="existPlatform">
            <el-radio
              v-for="dict in statusOptions"
              :key="dict.dictValue"
              :label="dict.dictValue"
            >{{dict.dictLabel}}</el-radio>
          </el-radio-group>
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
import { listPlatform, getPlatform, delPlatform, addPlatform, updatePlatform,changePlatformStatus,updatePlatformByName } from "@/api/system/platform";
import { getAllTags } from "@/api/system/asset";

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
      // 已存在平台
      existPlatform: false,
      // 总条数
      total: 0,
      // 平台表格数据
      platformList: [],
      // 平台地址类型
      addressTypeOptions: [],
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
        ],
        contractAddress: [
          { required: true, message: "合约地址不能为空", trigger: "blur" }
        ],
        addressType: [
          { required: true, message: "地址类型不能为空", trigger: "blur" }
        ]
      },
      tagOptions: [],
      statusOptions: []
    };
  },

  created() {
    this.getList();
    this.getTags();
    this.getDicts("sys_yes_no").then(response => {
      this.statusOptions = response.data;
    });
    this.getDicts("platform_address_type").then(response => {
      this.addressTypeOptions = response.data;
    });
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
    // 数据状态字典翻译
    addressTypeFormat(row, column) {
      return this.selectDictLabel(this.addressTypeOptions, row.addressType);
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
        id: undefined,
        platform: undefined,
        website: undefined,
        contractAddress: undefined,
        uriDescription: undefined,
        uriAddress: undefined,
        tags: []
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
      this.existPlatform = false;
      this.open = true;
      this.title = "添加平台";
    },
    /** 已有平台新增地址按钮操作 */
    handleAddExist(row) {
      this.reset();
      const platformId = row.id || this.ids
      getPlatform(platformId).then(response => {
        this.form = {...response.data,tags:response.data.tags ? response.data.tags.split(',').map(e=>Number(e)):[]};
        this.open = true;
        this.title = "添加已有平台地址";
      });
      this.existPlatform = true;
    },

    /** 修改按钮操作 */
    handleUpdate(row) {
      this.reset();
      const platformId = row.id || this.ids
      getPlatform(platformId).then(response => {
        this.form = {...response.data,tags:response.data.tags ? response.data.tags.split(',').map(e=>Number(e)):[]};
        this.open = true;
        this.title = "修改平台";
      });
      this.existPlatform = false;
      console.log(this.existPlatform);
    },

    handleStatusChange(row) {
      let text = row.isHot === "Y" ? "设置" : "取消设置";
      this.$confirm('确认要"' + text + '""' + row.platform + '"为热点推荐吗?', "警告", {
        confirmButtonText: "确定",
        cancelButtonText: "取消",
        type: "warning"
      }).then(function() {
        return changePlatformStatus(row.platform, row.isHot);
      }).then(response => {
        this.msgSuccess(text + "成功");
        this.getList();
      }).catch(function() {
        row.isHot = row.isHot === "N" ? "Y" : "N";
      });
    },
    /** 提交按钮 */
    submitForm: function() {
      this.$refs["form"].validate(valid => {
        if (valid) {
          const formData = JSON.parse(JSON.stringify(this.form));
          formData.tags = formData.tags.join(',');
          if (this.form.id != undefined && this.existPlatform == false) {
            updatePlatform(formData).then(response => {
              this.msgSuccess("修改成功");
              this.open = false;
              updatePlatformByName(formData);
              this.getList();
            });
          } else {
            addPlatform(formData).then(response => {
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

<style lang="scss">
  .myOption{
    width: 100%;
  }
</style>
