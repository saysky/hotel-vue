<template>
  <div class="app-container home">
    <el-row :gutter="20">
      <el-col :sm="24" :lg="24">
        <h2>{{ user.nickName }}，你好！欢迎访问房屋租赁系统。</h2>
        <h3>你是 <b>{{ roleGroup }}</b>，具有以下功能：</h3>
        <div v-if="roleGroup == '酒店前台'">
          <p>客房管理：可以查询和修改所有客房信息</p>
          <p>客房分类管理：可以查询和修改客房分类信息</p>
          <p>订单管理：可以查询和处理所有订单信息</p>
          <p>订单评价管理：可以查询和删除订单评价信息</p>
          <p>账单管理：可以查询所有账单信息</p>
          <p>用户管理：可以查询所有用户信息</p>
          <p>个人信息：可以修改您的个人信息和登录密码</p>
        </div>
        <div v-if="roleGroup == '客户'">
          <p>订单管理：可以查看和处理您的订单信息</p>
          <p>订单评价管理：可以查看和处理您的订单评价信息</p>
          <p>账单管理：可以查看您的所有账单信息</p>
          <p>个人信息：可以修改您的个人信息和登录密码</p>
        </div>
        <div v-if="roleGroup == '管理员'">
          <p>客房管理：可以管理所有客房信息</p>
          <p>客房分类管理：可以管理所有客房分类信息</p>
          <p>订单管理：可以管理所有订单信息</p>
          <p>订单评价管理：可以管理所有订单信息</p>
          <p>账单管理：可以管理所有账单信息</p>
          <p>用户管理：可以管理所有用户信息</p>
          <p>个人信息：可以修改您的个人信息和登录密码</p>
        </div>
        <hr/>
        <el-button type="primary" @click="gotoPortal" size="mini" >访问前台</el-button>
      </el-col>
    </el-row>
  </div>
</template>

<script>
import { getUserProfile } from '@/api/system/user'

export default {
  name: 'Index',
  data() {
    return {
      // 版本号
      user: [],
      roleGroup: ''
    }
  },
  methods: {
    gotoPortal() {
      window.open(process.env.VUE_APP_PORTAL_URL)
    }
  },
  created() {
    getUserProfile().then(response => {
      this.user = response.data
      this.roleGroup = response.roleGroup
    })
  }
}
</script>

<style scoped lang="scss">
.home {
  blockquote {
    padding: 10px 20px;
    margin: 0 0 20px;
    font-size: 17.5px;
    border-left: 5px solid #eee;
  }

  hr {
    margin-top: 20px;
    margin-bottom: 20px;
    border: 0;
    border-top: 1px solid #eee;
  }

  .col-item {
    margin-bottom: 20px;
  }

  ul {
    padding: 0;
    margin: 0;
  }

  font-family: "open sans", "Helvetica Neue", Helvetica, Arial, sans-serif;
  font-size: 13px;
  color: #676a6c;
  overflow-x: hidden;

  ul {
    list-style-type: none;
  }

  h4 {
    margin-top: 0px;
  }

  h2 {
    margin-top: 10px;
    font-size: 26px;
    font-weight: 100;
  }

  p {
    margin-top: 10px;

    b {
      font-weight: 700;
    }
  }

  .update-log {
    ol {
      display: block;
      list-style-type: decimal;
      margin-block-start: 1em;
      margin-block-end: 1em;
      margin-inline-start: 0;
      margin-inline-end: 0;
      padding-inline-start: 40px;
    }
  }
}
</style>

