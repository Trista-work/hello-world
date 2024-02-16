<template>
    <div class="v-admin-sy" >
      {{setting.title}} 系统数据概览
      <div id="middle">
        <div ref="mychart3" id="divhigh3"></div>
        <div ref="mychart1" id="divhigh"></div>
        <div ref="mychart2" id="divhigh2"></div>
      </div>

    </div>
</template>
<style type="text/scss" lang="scss">
#divhigh{
  float: left;
  width: 500px;
  height: 500px;

}
#middle{
  width: 100%;
  height: 100%;
  margin: 20px;
}
#divhigh2{
  float: left;
  width: 500px;
  height: 500px;

}
#divhigh3{
  float: left;
  width: 500px;
  height: 500px;
}
</style>

<script>
import * as echarts from "echarts"

    const setting = require('@/setting')
    export default {
        name: "v-admin-sy",
        data() {
            return {
                setting
            }
        },
        watch: {},
        computed: {},
        methods: {},
        created() {

        },
        mounted() {
          let myEcharts =  echarts.init(this.$refs.mychart1)
          myEcharts.setOption({
            title: {
              text: '景点数量分布',
              left: 'center'
            },
            tooltip: {
              trigger: 'item'
            },
            legend: {
              orient: 'vertical',
              left: 'left'
            },
            series: [
              {
                number: '数量',
                type: 'pie',
                radius: '50%',
                data: [
                  { value: 12, name: '青岛' },
                  { value: 13, name: '杭州' },
                  { value: 8, name: '厦门' },
                  { value: 9, name: '丽江' },
                  { value: 10, name: '西安' },
                  { value: 9, name: '北京' },
                  { value: 6, name: '长沙' },
                  { value: 8, name: '重庆' },
                  { value: 5, name: '南京' },
                  { value: 6, name: '桂林' }
                   ]
              }
            ]
          });

          let myEcharts2 =  echarts.init(this.$refs.mychart2)
          myEcharts2.setOption({
            title: {
              text: '特色美食概览',
              left: 'center'
            },
             xAxis: {
               type: 'category',
               data: ['川菜', '粤菜', '中餐', '鲁菜']
               },
             yAxis: {
               type: 'value'
               },
            series: [
              {
                 data: [5, 2, 15, 6],
                 type: 'bar',
                 showBackground: true,
                 backgroundStyle: {
                 color: 'rgba(180, 180, 180, 0.2)'
              }
            }
          ]
          });

  let myEcharts3 =  echarts.init(this.$refs.mychart3)
  myEcharts3.setOption({
   title: {
    text: '景点票价'
  },
  tooltip: {
    trigger: 'axis',
    axisPointer: {
      type: 'shadow'
    }
  },
  legend: {},
  grid: {
    left: '3%',
    right: '4%',
    bottom: '3%',
    containLabel: true
  },
  xAxis: {
    type: 'value',
    boundaryGap: [0, 0.01]
  },
  yAxis: {
    type: 'category',
    data: ['青岛', '杭州', '厦门', '丽江', '西安', '北京', '长沙', '重庆' , '南京', '桂林']
  },
  series: [
    {
      name: '<=50',
      type: 'bar',
      data: [5,8 , 6,3 ,4 , 7,5,4,3,3]
    },
    {
      name: '>50',
      type: 'bar',
      data: [8, 5,2 , 6, 6, 2,1,4,2,3]
    }

  ]
});

        },
        destroyed() {
        }
    }
</script>

