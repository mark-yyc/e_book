<template>
  <!--    <div style="width: 600px">-->
  <div>
    <vue-table-dynamic id="editTable"
                       :params="params"
                       @cell-change="onCellChange"
                       ref="table"
    >
    </vue-table-dynamic>
  </div>
</template>

<script>

import VueTableDynamic from 'vue-table-dynamic'

export default {
  name: 'EditTable',
  data() {
    return {
      params: {
        data: [
          ["Book", "Author", "Language", "Published", "Sales"],
          ["The Lord of the Rings", "J. R. R. Tolkien", "English", "1954-1955", "150 million"],
          ["Le Petit Prince (The Little Prince)", "Antoine de Saint-Exupéry", "French", "1943", "140 million"],
          ["Harry Potter and the Philosopher's Stone", "J. K. Rowling", "English", "1997", "107 million"],
          ["And Then There Were None", "Agatha Christie", "English", "1939", "100 million"],
          ["Dream of the Red Chamber", "Cao Xueqin", "Chinese", "1754-1791", "100 million"],
          ["The Hobbit", "J. R. R. Tolkien", "English", "1937", "100 million"],
          ["She: A History of Adventure", "H. Rider Haggard", "English", "1887", "100 million"],
        ],
        header: 'row',
        enableSearch: true,
        edit: {
          row: [1],
          column: [0, 1, 2, 3, 4],
          cell: [[-1, -1]]
        }
      }
    }
  },
  methods: {
    onCellChange(rowIndex, columnIndex, data) {
      console.log('onCellChange: ', rowIndex, columnIndex, data)
      console.log('table data: ', this.$refs.table.getData())
    }
  },
  components: {VueTableDynamic}
}
</script>

<style scoped>
#editTable {
  height: 500px;
  font-size: 14px;
  margin-top: 5px;
}

</style>