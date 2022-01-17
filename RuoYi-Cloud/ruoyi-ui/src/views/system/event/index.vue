<template>
  <div class="app-container">
    <el-form :model="queryParams" ref="queryForm" :inline="true" v-show="showSearch" label-width="68px">
      <el-form-item label="项目名称" prop="projectName">
        <el-input
          v-model="queryParams.projectName"
          placeholder="请输入项目名称"
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
          v-hasPermi="['system:event:add']"
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
          v-hasPermi="['system:event:edit']"
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
          v-hasPermi="['system:event:remove']"
        >删除</el-button>
      </el-col>
      <el-col :span="1.5">
        <el-button
          type="warning"
          plain
          icon="el-icon-download"
          size="mini"
          @click="handleExport"
          v-hasPermi="['system:event:export']"
        >导出</el-button>
      </el-col>
      <right-toolbar :showSearch.sync="showSearch" @queryTable="getList"></right-toolbar>
    </el-row>

    <el-table v-loading="loading" :data="eventList" @selection-change="handleSelectionChange">
      <el-table-column type="selection" width="55" align="center" />
      <el-table-column label="项目logo" align="center">
        <template slot-scope="scope">
          <img :src="scope.row.logo" height="80"/>
        </template>
      </el-table-column>
      <el-table-column label="项目名称" align="center" prop="projectName" />
      <el-table-column label="铸造日期" align="center" prop="eventDate" width="180">
        <template slot-scope="scope">
          <span>{{ parseTime(scope.row.eventDate, '{y}-{m}-{d}') }}</span>
        </template>
      </el-table-column>
      <el-table-column label="铸造时间" align="center" prop="mintTime" />
      <el-table-column label="铸造价格" align="center" prop="price" />
      <el-table-column label="数量" align="center" prop="quantity" />
      <el-table-column label="官网" align="center" prop="website" />
      <el-table-column label="twitter" align="center" prop="twitter" />
      <el-table-column label="discord" align="center" prop="discord" />
      <el-table-column label="twitter关注人数" align="center" prop="twitterFollowers" />
      <el-table-column label="discord在线人数" align="center" prop="discordOnline" />
      <el-table-column label="discord总人数" align="center" prop="discordTotal" />
      <el-table-column label="创建者" align="center" prop="creator" />
      <el-table-column label="审核状态" align="center" prop="status" :formatter="statusFormat"/>
      <el-table-column label="操作" align="center" class-name="small-padding fixed-width">
        <template slot-scope="scope">
          <el-button
            size="mini"
            type="text"
            icon="el-icon-edit"
            @click="handleUpdate(scope.row)"
            v-hasPermi="['system:event:edit']"
          >修改</el-button>
          <el-button
            size="mini"
            type="text"
            icon="el-icon-delete"
            @click="handleDelete(scope.row)"
            v-hasPermi="['system:event:remove']"
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

    <!-- 添加或修改event对话框 -->
    <el-dialog :title="title" :visible.sync="open" width="500px" append-to-body>
      <el-form ref="form" :model="form" :rules="rules" label-width="80px">
        <el-form-item label="项目名称" prop="projectName">
          <el-input v-model="form.projectName" placeholder="请输入项目名称" />
        </el-form-item>
        <el-form-item label="项目logo" prop="logo">
          <div class="text-center">
            <mintAvatar :user="form.logo" v-on:valueChange="logoChange" />
          </div>
        </el-form-item>
        <el-form-item label="铸造日期" prop="eventDate">
          <el-date-picker clearable size="small"
                          v-model="form.eventDate"
                          type="date"
                          value-format="yyyy-MM-dd"
                          placeholder="选择铸造时间">
          </el-date-picker>
        </el-form-item>
        <el-form-item label="铸造时间" prop="mintTime">
          <el-input v-model="form.mintTime" placeholder="请输入铸造时间" />
        </el-form-item>
        <el-form-item label="铸造价格" prop="price">
          <el-input v-model="form.price" placeholder="请输入铸造价格" />
        </el-form-item>
        <el-form-item label="数量" prop="quantity">
          <el-input v-model="form.quantity" placeholder="请输入数量" />
        </el-form-item>
        <el-form-item label="官网" prop="website">
          <el-input v-model="form.website" placeholder="请输入官网" />
        </el-form-item>
        <el-form-item label="twitter" prop="twitter">
          <el-input v-model="form.twitter" placeholder="请输入twitter" />
        </el-form-item>
        <el-form-item label="discord" prop="discord">
          <el-input v-model="form.discord" placeholder="请输入discord" />
        </el-form-item>
        <el-form-item label="twitter关注人数" prop="twitterFollowers">
          <el-input v-model="form.twitterFollowers" placeholder="请输入twitter关注人数" />
        </el-form-item>
        <el-form-item label="discord在线人数" prop="discordOnline">
          <el-input v-model="form.discordOnline" placeholder="请输入discord在线人数" />
        </el-form-item>
        <el-form-item label="discord总人数" prop="discordTotal">
          <el-input v-model="form.discordTotal" placeholder="请输入discord总人数" />
        </el-form-item>
        <el-form-item label="创建者" prop="creator">
          <el-input v-model="form.creator" placeholder="请输入创建者" />
        </el-form-item>
        <el-form-item label="审核状态" prop="status">
          <el-select v-model="form.status" placeholder="请选择审核状态">
            <el-option
              v-for="dict in statusOptions"
              :key="dict.dictValue"
              :label="dict.dictLabel"
              :value="dict.dictValue"
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
import { listEvent, getEvent, delEvent, addEvent, updateEvent } from "@/api/system/event";
import mintAvatar from "./mintAvatar";
export default {
  name: "Event",
  components: { mintAvatar },
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
      // event表格数据
      eventList: [],
      // 审核状态
      statusOptions: [],
      // 弹出层标题
      title: "",
      // 是否显示弹出层
      open: false,
      // 查询参数
      queryParams: {
        pageNum: 1,
        pageSize: 10,
        projectName: null,
        mintTime: null,
        price: null,
        quantity: null,
        website: null,
        twitter: null,
        discord: null,
        twitterFollowers: null,
        discordOnline: null,
        discordTotal: null,
        creator: null
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

    // 内容来源
    this.getDicts("sys_check_status").then(response => {
      this.statusOptions = response.data;
    });
  },
  methods: {
    logoChange(e){
      this.form.logo = e;
    },
    /** 查询event列表 */
    getList() {
      this.loading = true;
      listEvent(this.queryParams).then(response => {
        this.eventList = response.rows;
        this.total = response.total;
        this.loading = false;
      });
    },

    // 审核状态
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
        projectName: null,
        mintTime: null,
        price: null,
        quantity: null,
        website: null,
        twitter: null,
        discord: null,
        twitterFollowers: null,
        discordOnline: null,
        discordTotal: null,
        creator: null,
        logo:null
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
      this.title = "添加event";
    },
    /** 修改按钮操作 */
    handleUpdate(row) {
      this.reset();
      const id = row.id || this.ids
      getEvent(id).then(response => {
        this.form = response.data;
        this.open = true;
        this.title = "修改event";
      });
    },
    /** 提交按钮 */
    submitForm() {
      this.$refs["form"].validate(valid => {
        if (valid) {
          if (this.form.id != null) {
            updateEvent(this.form).then(response => {
              this.msgSuccess("修改成功");
              this.open = false;
              this.getList();
            });
          } else {
            addEvent(this.form).then(response => {
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
      this.$confirm('是否确认删除event编号为"' + ids + '"的数据项?', "警告", {
          confirmButtonText: "确定",
          cancelButtonText: "取消",
          type: "warning"
        }).then(function() {
          return delEvent(ids);
        }).then(() => {
          this.getList();
          this.msgSuccess("删除成功");
        })
    },
    /** 导出按钮操作 */
    handleExport() {
      this.download('system/event/export', {
        ...this.queryParams
      }, `system_event.xlsx`)
    }
  }
};
</script>
