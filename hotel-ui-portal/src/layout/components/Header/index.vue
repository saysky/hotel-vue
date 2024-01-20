<template>
  <!-- header start -->
  <header class="main_header_area">
    <div class="header-content">
      <div class="container">
        <div class="links links-left">
          <ul>
            <li><a href="#"><i class="fa fa-envelope" aria-hidden="true"></i> admin@liuyanzhao.com</a></li>
            <li><a href="#"><i class="fa fa-phone" aria-hidden="true"></i> 21-2327-2888</a></li>
            <li><a href="#"><i class="fa fa-map-marker" aria-hidden="true"></i> 中国上海市中山东一路32号 邮编
              ：200002</a></li>
          </ul>
        </div>
        <div class="links links-right pull-right">
          <ul v-if="loginUser == null">
            <li>
              <a @click="gotoLogin()">
                <i class="el-icon-user-solid"></i>
                登录
              </a>
            </li>
            <li>
              <a @click="gotoRegister()">
                <i class="el-icon-s-promotion"></i>
                注册
              </a>
            </li>
          </ul>
          <ul v-else>
            <li>
              <a @click="gotoAdmin()">
                <i class="fa fa-dashboard"></i> 进入后台
              </a>
            </li>
            <li>
              <a @click="logout" id="btn-logout">
                <i class="fa fa-user"></i>
                退出</a>
            </li>
          </ul>
        </div>
      </div>
    </div>
    <!-- Navigation Bar -->
    <div class="header_menu affix-top">
      <nav class="navbar navbar-default">
        <div class="container">
          <!-- Brand and toggle get grouped for better mobile display -->
          <div class="navbar-header">
            <a class="navbar-brand" href="/">
              <img alt="Image" src="@/assets/hotel/images/logo.png" class="logo-white">
              <img alt="Image" src="@/assets/hotel/images/logo-black.png" class="logo-black">
            </a>
          </div>
          <!-- Collect the nav links, forms, and other content for toggling -->
          <div class="collapse navbar-collapse" id="bs-example-navbar-collapse-1">
            <ul class="nav navbar-nav" id="responsive-menu">
              <li :class="isActive('/') ? 'active' : ''">
                <router-link to="/">首页</router-link>
              </li>
              <li :class="isActive('/category/'+item.id) ? 'active' : ''" v-for="item in roomCategoryList"
                  :key="item.id">
                <router-link :to="'/category/'+item.id">{{ item.name }}</router-link>
              </li>
            </ul>
          </div><!-- /.navbar-collapse -->
        </div><!-- /.container-fluid -->
        <div id="slicknav-mobile"></div>
      </nav>
    </div>
    <!-- Navigation Bar Ends -->
  </header>
  <!-- header Ends -->
</template>


<script>

import {getSimpleInfo} from '@/api/login'
import {listRoomCategory} from "@/api/roomCategory";

export default {
  components: {},
  data() {
    return {
      showDropdown: false,
      loginUser: null,
      roomCategoryList: []
    }
  },
  created() {
    this.getLoginUser();
    this.getRoomCategoryList();
  },
  methods: {
    gotoLogin() {
      let redirect = this.$route.path
      this.$router.push('/login?redirect=' + redirect)
    },
    gotoRegister() {
      let redirect = this.$route.path
      this.$router.push('/register?redirect=' + redirect)
    },
    getLoginUser() {
      // 已登录
      if (this.$store.getters.token != undefined && this.$store.getters.token != null) {
        getSimpleInfo().then(res => {
          if (res.user != undefined && res.user != null) {
            this.loginUser = res.user
            this.loginUser.avatar = process.env.VUE_APP_BASE_API + this.loginUser.avatar
          }
        })
      } else {
        this.loginUser = null
      }
    },
    logout() {
      this.$confirm('确定注销并退出系统吗？', '提示', {
        confirmButtonText: '确定',
        cancelButtonText: '取消',
        type: 'warning'
      }).then(() => {
        this.$store.dispatch('LogOut').then(() => {
          this.loginUser = null
        })
      }).catch(() => {
      })
    },
    gotoProfile() {
      window.open(process.env.VUE_APP_MANAGE_URL + '/user/profile')
    },
    gotoAdmin() {
      window.open(process.env.VUE_APP_MANAGE_URL + '/')
    },

    isActive(url) {
      let path = this.$route.path
      return path == url;
    },
    getRoomCategoryList() {
      listRoomCategory({
        pageNum: 1,
        pageSize: 100
      }).then(response => {
        this.roomCategoryList = response.rows;
      });
    },

  }
}
</script>

<style scoped>
.pagination-container {
  height: 50px;
}

.nav-menu a {
  font-size: 16px;
}
</style>
