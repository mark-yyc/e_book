<template>
  <div class="layout">
    <Layout>
      <Header/>
      <!--            <Header>-->
      <!--                <Menu mode="horizontal" theme="dark" active-name="1">-->
      <!--                    <div class="layout-logo">E-Books</div>-->
      <!--                    <div class="layout-nav">-->
      <!--                        <MenuItem name="1">-->
      <!--                            <Icon type="ios-navigate"></Icon>-->
      <!--                            Books-->
      <!--                        </MenuItem>-->
      <!--                        <MenuItem name="2">-->
      <!--                            <Icon type="ios-keypad"></Icon>-->
      <!--                            My Cart-->
      <!--                        </MenuItem>-->
      <!--                        <MenuItem name="3">-->
      <!--                            <Icon type="ios-analytics"></Icon>-->
      <!--                            My Orders-->
      <!--                        </MenuItem>-->
      <!--                        <MenuItem name="4">-->
      <!--                            <Icon type="ios-analytics"></Icon>-->
      <!--                            My profile-->
      <!--                        </MenuItem>-->
      <!--                    </div>-->
      <!--                </Menu>-->
      <!--            </Header>-->
      <Content :style="{padding: '0 50px'}" class="contentInfo">
        <Card>
          <div style="min-height: 200px;">

            <EditTable/>
          </div>
        </Card>
      </Content>
      <Footer class="layout-footer-center">Copyright &copy; By Mark</Footer>
    </Layout>
  </div>
</template>

<script>
import EditTable from "@/components/EditTable";
import Header from "@/components/Header";

export default {
  name: "Home",

  components: {
    EditTable,
    Header
  }
}
</script>

<style scoped>
.layout {
  border: 1px solid #d7dde4;
  background: #f5f7f9;
  position: relative;
  border-radius: 4px;
  overflow: hidden;
}


.layout-footer-center {
  text-align: center;
}

.contentInfo {
  margin-top: 30px;
}


</style>