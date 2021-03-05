<template>
  <Header>
    <Menu mode="horizontal" theme="dark" active-name="1">
      <div class="layout-logo">E-Books</div>
      <div class="layout-nav">
        <MenuItem name="1">
          <Icon type="ios-navigate"></Icon>
          Books
        </MenuItem>
        <MenuItem name="2">
          <Icon type="ios-keypad"></Icon>
          My Cart
        </MenuItem>
        <MenuItem name="3">
          <Icon type="ios-analytics"></Icon>
          My Orders
        </MenuItem>
        <MenuItem name="4">
          <Icon type="ios-analytics"></Icon>
          My profile
        </MenuItem>
      </div>
    </Menu>
  </Header>
</template>

<script>
export default {
  name: "Header"

}
</script>

<style scoped>
.layout-logo {
  width: 100px;
  height: 30px;
  background: white;
  border-radius: 3px;
  float: left;
  position: relative;
  top: 15px;
  left: 20px;
  text-align: center;
  line-height: 30px;
}

.layout-nav {
  width: 600px;
  margin: 0 auto;
  margin-right: 0px;
}
</style>