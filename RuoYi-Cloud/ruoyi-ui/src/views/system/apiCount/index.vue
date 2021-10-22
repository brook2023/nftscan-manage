<template>
  <div class="app-container">
    <el-form :model="queryParams" ref="queryForm" :inline="true" v-show="showSearch" label-width="68px">
      <el-form-item label="调用时间" prop="apiCallTime">
        <el-date-picker clearable size="small"
                        v-model="queryParams.apiCallTime"
                        type="date"
                        value-format="yyyy-MM-dd"
                        placeholder="选择调用时间">
        </el-date-picker>
      </el-form-item>
      <el-form-item>
        <el-button type="primary" icon="el-icon-search" size="mini" @click="handleQuery">搜索</el-button>
        <el-button icon="el-icon-refresh" size="mini" @click="resetQuery">重置</el-button>
      </el-form-item>
    </el-form>

    <el-row :gutter="10" class="mb8">
      <el-col :span="1.5">
        <el-button
          type="warning"
          plain
          icon="el-icon-download"
          size="mini"
          @click="handleExport"
          v-hasPermi="['system:apiCount:export']"
        >导出
        </el-button>
      </el-col>
      <right-toolbar :showSearch.sync="showSearch" @queryTable="getList"></right-toolbar>
    </el-row>

    <el-table v-loading="loading" :data="apiCountList">
      <el-table-column label="email" align="center" prop="email"/>

      <el-table-column label="调用时间" align="center" prop="apiCallTime" width="180">
        <template slot-scope="scope">
          <span>{{ parseTime(scope.row.apiCallTime, '{y}-{m}-{d}') }}</span>
        </template>
      </el-table-column>
      <el-table-column label="调用总数" align="center" prop="count"/>
      <el-table-column label="调用大小" align="center" prop="size"/>
      <el-table-column label="24小时 统计json" align="center" prop="apiUser24Count"/>

      <el-table-column label="方法调用次数">
        <template slot-scope="scope">
          <span v-for="item in scope.row.methodCount">{{ item.method }}: {{ item.count }}</br></span>
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


  </div>
</template>

<script>
import {listApiCount} from "@/api/system/apiCount";

export default {
  name: "ApiCount",
  components: {},
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
      // apiCount表格数据
      apiCountList: [],
      // 弹出层标题
      title: "",
      // 是否显示弹出层
      open: false,
      // 查询参数
      queryParams: {
        pageNum: 1,
        pageSize: 10,
        apiCallTime: null
      },
      // 表单参数
      form: {},
      // 表单校验
      rules: {}
    };
  },
  created() {
    this.getList();
  },
  methods: {
    /** 查询apiCount列表 */
    getList() {
      this.loading = true;
      listApiCount(this.queryParams).then(response => {
        this.apiCountList = response.rows;
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
      this.download('system/apiCount/export', {
        ...this.queryParams
      }, `system_apiCount.xlsx`)
    }
  }
};
</script>
