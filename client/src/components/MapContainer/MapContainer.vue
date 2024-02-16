<template>
  <div>
    <div class="mapSearch">
      <el-input placeholder="请输入你要查找的关键词" v-model="userInput" id="tishikuang"></el-input>
      <el-button type="primary" @click="biu">确定选址</el-button>
    </div>
    <div id="container">
    </div>
  </div>

</template>

<script>
import AMapLoader from '@amap/amap-jsapi-loader'
window._AMapSecurityConfig = {
  securityJsCode: 'eb2f242f31e31853bb63723064dedb33'//在这里填写你的安全密钥
}
export default {
  data() {
    return {
      map: null,
      autoOptions: {
        input: 'tishikuang' //绑定的搜索关键字的input标签ID，用这个注册
      },
      auto: null,
      userInput: '', //绑定的搜索关键字的的内容
      placeSearch: null,
      searchHere: null, //根据搜索组件搜索到以后的地点信息
    }
  },
  mounted() {
    this.initMap()
  },
  methods: {

    // poi搜索以后 点击确认选址以后的操作
    biu() {
      if (this.searchHere) {
        this.$message({
          message: '确认地点：'+this.searchHere.name,
          type: 'success'
        });
        // this.$emit('mapHere',this.searchHere);

      }
    },
    initMap() {
      AMapLoader.load({
        key: "2435fec20f979f10c5abb471835e5980", // 申请好的Web端开发者Key，首次调用 load 时必填
        version: "2.0", // 指定要加载的 JSAPI 的版本，缺省时默认为 1.4.15
        plugins: ['AMap.ToolBar', 'AMap.Scale', 'AMap.HawkEye', 'AMap.MapType',
          'AMap.Geolocation', 'AMap.AutoComplete', 'AMap.PlaceSearch', 'AMap.Geocoder'
        ], // 需要使用的的插件列表，如比例尺'AMap.Scale'等
      }).then((AMap) => {
        this.map = new AMap.Map("container", { //设置地图容器id
          viewMode: "3D", //是否为3D地图模式
          zoom: 17, //初始化地图级别
          center: [105.602725, 37.076636], //初始化地图中心点位置
        });
        //引入高德地图的空间绑定使用
        this.map.addControl(new AMap.Scale())
        this.map.addControl(new AMap.ToolBar())
        this.map.addControl(new AMap.HawkEye())
        this.map.addControl(new AMap.MapType())
        this.map.addControl(new AMap.Geolocation())
        this.auto = new AMap.AutoComplete(this.autoOptions)
        //
        this.placeSearch = new AMap.PlaceSearch({
          map: this.map
        }) //构造地点查询类
        this.auto.on('select', this.select)//绑定查询事件

      }).catch(e => {
        console.log(e);
      })
    },

    //poi搜索 这里面的这个参数 e 就是搜索以后的地址信息 你可以把它打印出来看一看经纬度什么的都有
    select(e) {
      this.placeSearch.setCity(e.poi.adcode) //依据城市ID搜索
      this.placeSearch.search(e.poi.name) //关键字查询查询
      this.map.setZoom(10,true)
      this.searchHere = e.poi
      this.$message({
        message: '选择地点:' + this.searchHere.name,
        type: 'success'
      });
    }
  },

}
</script>

<style lang="less" scoped>
#container {
  padding: 0px;
  margin: 0px;
  width: 100%;
  height: 500px;
}

.mapSearch {
  display: flex;
  margin-bottom: 8px;

  .el-button {
    margin-left: 8px;
  }
}

#tishikuang {
  margin-bottom: 200px;
  display: block;
}
</style>
