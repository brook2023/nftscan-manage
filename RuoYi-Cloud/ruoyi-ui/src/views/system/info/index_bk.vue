<template>
  <div class="app-container">
    <el-form :model="queryParams" ref="queryForm" :inline="true" v-show="showSearch" label-width="68px">
      <el-form-item label="标题" prop="title">
        <el-input
          v-model="queryParams.title"
          placeholder="请输入标题"
          clearable
          size="small"
          @keyup.enter.native="handleQuery"
        />
      </el-form-item>
      <el-form-item label="类型" prop="type">
        <el-select v-model="queryParams.type" placeholder="资讯类型" clearable size="small">
          <el-option
            v-for="dict in typeOptions"
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
          v-hasPermi="['system:info:add']"
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
          v-hasPermi="['system:info:edit']"
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
          v-hasPermi="['system:info:remove']"
        >删除</el-button>
      </el-col>
      <el-col :span="1.5">
        <el-button
          type="info"
          plain
          icon="el-icon-upload2"
          size="mini"
          @click="handleImport"
          v-hasPermi="['system:info:import']"
        >导入</el-button>
      </el-col>
      <right-toolbar :showSearch.sync="showSearch" @queryTable="getList"></right-toolbar>
    </el-row>

    <el-table v-loading="loading" :data="infoList" @selection-change="handleSelectionChange" tooltip-effect="dark">
      <el-table-column type="selection" width="55" align="center" show-overflow-tooltip/>
      <el-table-column label="标题" align="center" prop="title" show-overflow-tooltip/>
      <el-table-column label="内容" align="left" prop="content" show-overflow-tooltip/>
      <el-table-column label="类型" align="center" prop="type" :formatter="typeFormat" show-overflow-tooltip/>
      <el-table-column label="内容来源" align="center" prop="contentSource" :formatter="sourceFormat" show-overflow-tooltip/>
      <el-table-column label="链接" align="center" prop="link" show-overflow-tooltip/>
      <el-table-column label="作者" align="center" prop="author" show-overflow-tooltip/>
      <el-table-column label="标签" align="center" prop="tags" show-overflow-tooltip/>
      <el-table-column label="操作" align="center" class-name="small-padding fixed-width">
        <template slot-scope="scope">
          <el-button
            size="mini"
            type="text"
            icon="el-icon-edit"
            @click="handleUpdate(scope.row)"
            v-hasPermi="['system:info:edit']"
          >修改</el-button>
          <el-button
            size="mini"
            type="text"
            icon="el-icon-delete"
            @click="handleDelete(scope.row)"
            v-hasPermi="['system:info:remove']"
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

    <!-- 添加或修改岗位对话框 -->
    <el-dialog :title="title" :visible.sync="open" width="800px" height="600px" append-to-body>
      <el-form ref="form" :model="form" :rules="rules" label-width="80px">
        <el-form-item label="类型" prop="type">
          <el-radio-group v-model="form.type">
            <el-radio
              v-for="dict in typeOptions"
              :key="dict.dictValue"
              :label="dict.dictValue"
            >{{dict.dictLabel}}</el-radio>
          </el-radio-group>
        </el-form-item>
        <el-form-item label="资讯来源" prop="contentSource">
          <el-select v-model="form.contentSource" placeholder="请选择">
            <el-option
              v-for="dict in sourceOptions"
              :key="dict.dictValue"
              :label="dict.dictLabel"
              :value="dict.dictValue"
            ></el-option>
          </el-select>
        </el-form-item>
        <el-form-item label="标题" prop="title">
          <el-input v-model="form.title" placeholder="请输入标题" />
        </el-form-item>
        <el-form-item label="内容" prop="content">
          <el-input class="textarea-reset" v-model="form.content" type="textarea" placeholder="请输入内容"/>
        </el-form-item>

        <el-form-item label="链接" prop="platform" v-if="form.type==='0'">
          <el-input v-model="form.link" placeholder="请输入链接" />
        </el-form-item>
        <el-form-item label="作者" prop="author" v-if="form.type==='0'">
          <el-input v-model="form.author" placeholder="请输入作者" />
        </el-form-item>
        <el-form-item label="标签" prop="tags">
          <el-checkbox-group v-model="form.tags">
            <el-checkbox v-for="item in tagOptions" :label="item.id" :key="item.tag">{{item.tag}}</el-checkbox>
          </el-checkbox-group>
        </el-form-item>

        <el-form-item label="封面" prop="cover" v-if="form.type==='0'">
          <el-upload
            :action="uploadImgUrl"
            list-type="picture-card"
            :on-success="handleUploadSuccess"
            :before-upload="handleBeforeUpload"
            :on-error="handleUploadError"
            name="file"
            :show-file-list="false"
            :headers="headers"
            style="display: inline-block; vertical-align: top"
          >
            <el-image v-if="!form.cover" :src="form.cover">
              <div slot="error" class="image-slot">
                <i class="el-icon-plus" />
              </div>
            </el-image>
            <div v-else class="image">
              <el-image :src="form.cover" :style="`width:150px;height:150px;`" fit="fill"/>

            </div>
          </el-upload>
        </el-form-item>

      </el-form>
      <div slot="footer" class="dialog-footer">
        <el-button type="primary" @click="submitForm">发布</el-button>
        <el-button @click="cancel">取 消</el-button>
      </div>
    </el-dialog>

    <!-- 用户导入对话框 -->
    <el-dialog :title="upload.title" :visible.sync="upload.open" width="400px" append-to-body>
      <el-upload
        ref="upload"
        :limit="1"
        accept=".xlsx, .xls"
        :headers="upload.headers"
        :action="upload.url + '?updateSupport=' + upload.updateSupport"
        :disabled="upload.isUploading"
        :on-progress="handleFileUploadProgress"
        :on-success="handleFileSuccess"
        :auto-upload="false"
        drag
      >
        <i class="el-icon-upload"></i>
        <div class="el-upload__text">
          将文件拖到此处，或
          <em>点击上传</em>
        </div>
        <div class="el-upload__tip" slot="tip">
          <el-link type="info" style="font-size:16px; color: #3A71A8;" @click="importTemplate">下载导入模板</el-link>
        </div>
        <div class="el-upload__tip" style="color:red" slot="tip">提示：仅允许导入“xls”或“xlsx”格式文件！</div>
      </el-upload>
      <div slot="footer" class="dialog-footer">
        <el-button type="primary" @click="submitFileForm">确 定</el-button>
        <el-button @click="upload.open = false">取 消</el-button>
      </div>
    </el-dialog>
  </div>
</template>

<script>
  import { listInfo, getInfo, delInfo, addInfo, updateInfo } from "@/api/system/info";
  import { getAllTags } from "@/api/system/asset";
  import { getToken } from "@/utils/auth";

  export default {
    name: "Info",
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
        // 岗位表格数据
        infoList: [],
        // 弹出层标题
        title: "",
        // 是否显示弹出层
        open: false,
        // 状态数据字典
        typeOptions: [],
        // 内容来源
        sourceOptions: [],
        value: "",
        // 导入参数
        upload: {
          // 是否显示弹出层（用户导入）
          open: false,
          // 弹出层标题（用户导入）
          title: "",
          // 是否禁用上传
          isUploading: false,
          // 是否更新已经存在的用户数据
          updateSupport: 0,
          // 设置上传的请求头部
          headers: { Authorization: "Bearer " + getToken() },
          // 上传的地址
          url: process.env.VUE_APP_BASE_API + "/system/info/importData"
        },
        // 查询参数
        queryParams: {
          pageNum: 1,
          pageSize: 10,
          title: undefined,
          type: undefined
        },
        // 表单参数
        form: {},
        // 表单校验
        rules: {
          title: [
            { required: true, message: "标题不能为空", trigger: "blur" }
          ],
          content: [
            { required: true, message: "内容不能为空", trigger: "blur" }
          ],
          type: [
            { required: true, message: "类型不能为空", trigger: "blur" }
          ],
          contentSource: [
            { required: true, message: "资讯来源不能为空", trigger: "blur" }
          ]
        },
        tagOptions: [],
        dialogVisible: false,
        uploadImgUrl: process.env.VUE_APP_BASE_API + "/file/upload", // 上传的图片服务器地址
        headers: {
          Authorization: "Bearer " + getToken(),
        }
      };
    },

    created() {
      this.getList();
      this.getTags();
      this.getDicts("nft_info_type").then(response => {
        this.typeOptions = response.data;
      });

      // 内容来源
      this.getDicts("sys_content_source").then(response => {
        this.sourceOptions = response.data;
      });
    },
    methods: {
      /** 查询岗位列表 */
      getList() {
        this.loading = true;
        listInfo(this.queryParams).then(response => {
          this.infoList = response.rows;
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
      // 岗位状态字典翻译
      typeFormat(row, column) {
        return this.selectDictLabel(this.typeOptions, row.type);
      },

      // 内容来源
      sourceFormat(row, column) {
        return this.selectDictLabel(this.sourceOptions, row.contentSource);
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
          title: undefined,
          content: undefined,
          platform: undefined,
          author: undefined,
          type: "1",
          cover: "",
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
        this.open = true;
        this.title = "发布资讯";
      },
      /** 修改按钮操作 */
      handleUpdate(row) {
        this.reset();
        const infoId = row.id || this.ids
        getInfo(infoId).then(response => {
          this.form = {...response.data,tags:response.data.tags ? response.data.tags.split(',').map(e=>Number(e)):[], cover: response.data.cover};
          this.open = true;
          this.title = "修改资讯";
        });
      },
      /** 提交按钮 */
      submitForm: function() {
        this.$refs["form"].validate(valid => {
          if (valid) {
            const formData = JSON.parse(JSON.stringify(this.form));
            formData.tags = formData.tags.join(',');
            if (this.form.id != undefined) {
              updateInfo(formData).then(response => {
                this.msgSuccess("修改成功");
                this.open = false;
                this.getList();
              });
            } else {
              addInfo(formData).then(response => {
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
        const infoIds = row.id || this.ids;
        this.$confirm('是否确认删除所选数据项?', "警告", {
          confirmButtonText: "确定",
          cancelButtonText: "取消",
          type: "warning"
        }).then(function() {
          return delInfo(infoIds);
        }).then(() => {
          this.getList();
          this.msgSuccess("删除成功");
        })
      },
      /** 导出按钮操作 */
      handleExport() {
        this.download('system/info/export', {
          ...this.queryParams
        }, `info_${new Date().getTime()}.xlsx`)
      },


      removeImage() {
        this.form.cover = '';
        this.value = '';
      },
      handleUploadSuccess: function (res) {
        this.form.cover = res.data.url;
        this.value = res.data.url;
        this.loading.close();
      },
      handleBeforeUpload(res) {
        this.loading = this.$loading({
          lock: true,
          text: "上传中",
          background: "rgba(0, 0, 0, 0.7)",
        });
      },
      handleUploadError() {
        this.$message({
          type: "error",
          message: "上传失败",
        });
        this.loading.close();
      },
      /** 导入按钮操作 */
      handleImport() {
        this.upload.title = "资讯导入";
        this.upload.open = true;
      },
      /** 下载模板操作 */
      importTemplate() {
        var name = "资讯数据导入模板";
        var url = './info_template.xlsx';
        var suffix = url.substring(url.lastIndexOf("."), url.length);
        const a = document.createElement('a')
        a.setAttribute('download', name + suffix)
        a.setAttribute('target', '_blank')
        a.setAttribute('href', url)
        a.click()
      },
      // 文件上传中处理
      handleFileUploadProgress(event, file, fileList) {
        this.upload.isUploading = true;
      },
      // 文件上传成功处理
      handleFileSuccess(response, file, fileList) {
        this.upload.open = false;
        this.upload.isUploading = false;
        this.$refs.upload.clearFiles();
        this.$alert(response.msg, "导入结果", { dangerouslyUseHTMLString: true });
        this.getList();
      },
      // 提交上传文件
      submitFileForm() {
        this.$refs.upload.submit();
      }
    },
  };
</script>

<style lang="scss">
  .image {
    position: relative;
    .mask {
      opacity: 0;
      position: absolute;
      top: 0;
      width: 100%;
      background-color: rgba(0, 0, 0, 0.5);
      transition: all 0.3s;
    }
    &:hover .mask {
      opacity: 1;
    }
  }
  .textarea-reset{
    textarea{
      min-height: 150px !important;
    }
  }
</style>
