package com.whfp.anti_terrorism.bean;

import java.util.List;

/**
 * Created by 张明杨 on 2018-10-25-0025.
 */

public class CameraBean {

    /**
     * code : 200
     * data : [{"cameraId":"1","cameraType":0,"createTime":1529989581099,"decodetag":"hikvision","description":"","extraField":{"appCode":"100005","cameraDeviceType":"10030","chanNum":1,"deviceIdx":"125395576","elevationStr":"","enable":"1","installPlace":"","installPlacePinyin":"","latitudeStr":"","longitudeStr":"","matrixCode":"","nameStr":"3楼东","pinyin":"3ld","pinyinStr":"3ld","recLocation":"1","recordPos":"1","remark":"","seqIdx":1,"streamLinkType":"","streamType":"","structureFlag":"","treeNodeIndexcode":"42112304002160708266","treeNodePath":"0@42112304002160708266","treeNodePathStr":"0@42112304002160708266","vagIndexCode":"180626010020934790","viewshed":""},"indexCode":"1253955761310011638","isOnline":0,"latitude":"","longitude":"","name":"3楼东","parentIndexCode":"42112304002160708266","pixel":1,"updateTime":1529989581099},{"cameraId":"2","cameraType":0,"createTime":1529989581100,"decodetag":"hikvision","description":"","extraField":{"appCode":"100005","cameraDeviceType":"10030","chanNum":2,"deviceIdx":"125395576","elevationStr":"","enable":"1","installPlace":"","installPlacePinyin":"","latitudeStr":"","longitudeStr":"","matrixCode":"","nameStr":"1楼西","pinyin":"1lx","pinyinStr":"1lx","recLocation":"1","recordPos":"1","remark":"","seqIdx":2,"streamLinkType":"","streamType":"","structureFlag":"","treeNodeIndexcode":"42112304002160708266","treeNodePath":"0@42112304002160708266","treeNodePathStr":"0@42112304002160708266","vagIndexCode":"180626010020934790","viewshed":""},"indexCode":"1253955761310016084","isOnline":0,"latitude":"","longitude":"","name":"1楼西","parentIndexCode":"42112304002160708266","pixel":1,"updateTime":1529989581100},{"cameraId":"3","cameraType":0,"createTime":1529989581101,"decodetag":"hikvision","description":"","extraField":{"appCode":"100005","cameraDeviceType":"10030","chanNum":4,"deviceIdx":"125395576","elevationStr":"","enable":"1","installPlace":"","installPlacePinyin":"","latitudeStr":"","longitudeStr":"","matrixCode":"","nameStr":"1楼电梯前","pinyin":"1ldtq","pinyinStr":"1ldtq","recLocation":"1","recordPos":"1","remark":"","seqIdx":3,"streamLinkType":"","streamType":"","structureFlag":"","treeNodeIndexcode":"42112304002160708266","treeNodePath":"0@42112304002160708266","treeNodePathStr":"0@42112304002160708266","vagIndexCode":"180626010020934790","viewshed":""},"indexCode":"1253955761310011270","isOnline":0,"latitude":"","longitude":"","name":"1楼电梯前","parentIndexCode":"42112304002160708266","pixel":1,"updateTime":1529989581101},{"cameraId":"4","cameraType":0,"createTime":1529989581101,"decodetag":"hikvision","description":"","extraField":{"appCode":"100005","cameraDeviceType":"10030","chanNum":5,"deviceIdx":"125395576","elevationStr":"","enable":"1","installPlace":"","installPlacePinyin":"","latitudeStr":"","longitudeStr":"","matrixCode":"","nameStr":"1楼东","pinyin":"1ld","pinyinStr":"1ld","recLocation":"1","recordPos":"1","remark":"","seqIdx":4,"streamLinkType":"","streamType":"","structureFlag":"","treeNodeIndexcode":"42112304002160708266","treeNodePath":"0@42112304002160708266","treeNodePathStr":"0@42112304002160708266","vagIndexCode":"180626010020934790","viewshed":""},"indexCode":"1253955761310019217","isOnline":0,"latitude":"","longitude":"","name":"1楼东","parentIndexCode":"42112304002160708266","pixel":1,"updateTime":1529989581101},{"cameraId":"5","cameraType":0,"createTime":1529989581102,"decodetag":"hikvision","description":"","extraField":{"appCode":"100005","cameraDeviceType":"10030","chanNum":6,"deviceIdx":"125395576","elevationStr":"","enable":"1","installPlace":"","installPlacePinyin":"","latitudeStr":"","longitudeStr":"","matrixCode":"","nameStr":"1楼电梯","pinyin":"1ldt","pinyinStr":"1ldt","recLocation":"1","recordPos":"1","remark":"","seqIdx":5,"streamLinkType":"","streamType":"","structureFlag":"","treeNodeIndexcode":"42112304002160708266","treeNodePath":"0@42112304002160708266","treeNodePathStr":"0@42112304002160708266","vagIndexCode":"180626010020934790","viewshed":""},"indexCode":"1253955761310016793","isOnline":0,"latitude":"","longitude":"","name":"1楼电梯","parentIndexCode":"42112304002160708266","pixel":1,"updateTime":1529989581102},{"cameraId":"6","cameraType":0,"createTime":1529989581102,"decodetag":"hikvision","description":"","extraField":{"appCode":"100005","cameraDeviceType":"10030","chanNum":7,"deviceIdx":"125395576","elevationStr":"","enable":"1","installPlace":"","installPlacePinyin":"","latitudeStr":"","longitudeStr":"","matrixCode":"","nameStr":"5楼电梯","pinyin":"5ldt","pinyinStr":"5ldt","recLocation":"1","recordPos":"1","remark":"","seqIdx":6,"streamLinkType":"","streamType":"","structureFlag":"","treeNodeIndexcode":"42112304002160708266","treeNodePath":"0@42112304002160708266","treeNodePathStr":"0@42112304002160708266","vagIndexCode":"180626010020934790","viewshed":""},"indexCode":"1253955761310013472","isOnline":0,"latitude":"","longitude":"","name":"5楼电梯","parentIndexCode":"42112304002160708266","pixel":1,"updateTime":1529989581102},{"cameraId":"7","cameraType":0,"createTime":1529989581103,"decodetag":"hikvision","description":"","extraField":{"appCode":"100005","cameraDeviceType":"10030","chanNum":8,"deviceIdx":"125395576","elevationStr":"","enable":"1","installPlace":"","installPlacePinyin":"","latitudeStr":"","longitudeStr":"","matrixCode":"","nameStr":"6楼西","pinyin":"6lx","pinyinStr":"6lx","recLocation":"1","recordPos":"1","remark":"","seqIdx":7,"streamLinkType":"","streamType":"","structureFlag":"","treeNodeIndexcode":"42112304002160708266","treeNodePath":"0@42112304002160708266","treeNodePathStr":"0@42112304002160708266","vagIndexCode":"180626010020934790","viewshed":""},"indexCode":"1253955761310014544","isOnline":0,"latitude":"","longitude":"","name":"6楼西","parentIndexCode":"42112304002160708266","pixel":1,"updateTime":1529989581103},{"cameraId":"8","cameraType":0,"createTime":1529989581103,"decodetag":"hikvision","description":"","extraField":{"appCode":"100005","cameraDeviceType":"10030","chanNum":9,"deviceIdx":"125395576","elevationStr":"","enable":"1","installPlace":"","installPlacePinyin":"","latitudeStr":"","longitudeStr":"","matrixCode":"","nameStr":"5楼电梯前","pinyin":"5ldtq","pinyinStr":"5ldtq","recLocation":"1","recordPos":"1","remark":"","seqIdx":8,"streamLinkType":"","streamType":"","structureFlag":"","treeNodeIndexcode":"42112304002160708266","treeNodePath":"0@42112304002160708266","treeNodePathStr":"0@42112304002160708266","vagIndexCode":"180626010020934790","viewshed":""},"indexCode":"1253955761310018074","isOnline":0,"latitude":"","longitude":"","name":"5楼电梯前","parentIndexCode":"42112304002160708266","pixel":1,"updateTime":1529989581103},{"cameraId":"9","cameraType":0,"createTime":1529989581104,"decodetag":"hikvision","description":"","extraField":{"appCode":"100005","cameraDeviceType":"10030","chanNum":10,"deviceIdx":"125395576","elevationStr":"","enable":"1","installPlace":"","installPlacePinyin":"","latitudeStr":"","longitudeStr":"","matrixCode":"","nameStr":"4楼电梯","pinyin":"4ldt","pinyinStr":"4ldt","recLocation":"1","recordPos":"1","remark":"","seqIdx":9,"streamLinkType":"","streamType":"","structureFlag":"","treeNodeIndexcode":"42112304002160708266","treeNodePath":"0@42112304002160708266","treeNodePathStr":"0@42112304002160708266","vagIndexCode":"180626010020934790","viewshed":""},"indexCode":"1253955761310017195","isOnline":0,"latitude":"","longitude":"","name":"4楼电梯","parentIndexCode":"42112304002160708266","pixel":1,"updateTime":1529989581104},{"cameraId":"10","cameraType":0,"createTime":1529989581105,"decodetag":"hikvision","description":"","extraField":{"appCode":"100005","cameraDeviceType":"10030","chanNum":11,"deviceIdx":"125395576","elevationStr":"","enable":"1","installPlace":"","installPlacePinyin":"","latitudeStr":"","longitudeStr":"","matrixCode":"","nameStr":"6楼电梯","pinyin":"6ldt","pinyinStr":"6ldt","recLocation":"1","recordPos":"1","remark":"","seqIdx":10,"streamLinkType":"","streamType":"","structureFlag":"","treeNodeIndexcode":"42112304002160708266","treeNodePath":"0@42112304002160708266","treeNodePathStr":"0@42112304002160708266","vagIndexCode":"180626010020934790","viewshed":""},"indexCode":"1253955761310019521","isOnline":0,"latitude":"","longitude":"","name":"6楼电梯","parentIndexCode":"42112304002160708266","pixel":1,"updateTime":1529989581105},{"cameraId":"11","cameraType":0,"createTime":1529989581105,"decodetag":"hikvision","description":"","extraField":{"appCode":"100005","cameraDeviceType":"10030","chanNum":12,"deviceIdx":"125395576","elevationStr":"","enable":"1","installPlace":"","installPlacePinyin":"","latitudeStr":"","longitudeStr":"","matrixCode":"","nameStr":"3楼电梯","pinyin":"3ldt","pinyinStr":"3ldt","recLocation":"1","recordPos":"1","remark":"","seqIdx":11,"streamLinkType":"","streamType":"","structureFlag":"","treeNodeIndexcode":"42112304002160708266","treeNodePath":"0@42112304002160708266","treeNodePathStr":"0@42112304002160708266","vagIndexCode":"180626010020934790","viewshed":""},"indexCode":"1253955761310018894","isOnline":0,"latitude":"","longitude":"","name":"3楼电梯","parentIndexCode":"42112304002160708266","pixel":1,"updateTime":1529989581105},{"cameraId":"12","cameraType":0,"createTime":1529989581106,"decodetag":"hikvision","description":"","extraField":{"appCode":"100005","cameraDeviceType":"10030","chanNum":13,"deviceIdx":"125395576","elevationStr":"","enable":"1","installPlace":"","installPlacePinyin":"","latitudeStr":"","longitudeStr":"","matrixCode":"","nameStr":"4楼西","pinyin":"4lx","pinyinStr":"4lx","recLocation":"1","recordPos":"1","remark":"","seqIdx":12,"streamLinkType":"","streamType":"","structureFlag":"","treeNodeIndexcode":"42112304002160708266","treeNodePath":"0@42112304002160708266","treeNodePathStr":"0@42112304002160708266","vagIndexCode":"180626010020934790","viewshed":""},"indexCode":"1253955761310014266","isOnline":0,"latitude":"","longitude":"","name":"4楼西","parentIndexCode":"42112304002160708266","pixel":1,"updateTime":1529989581106},{"cameraId":"13","cameraType":0,"createTime":1529989581106,"decodetag":"hikvision","description":"","extraField":{"appCode":"100005","cameraDeviceType":"10030","chanNum":14,"deviceIdx":"125395576","elevationStr":"","enable":"1","installPlace":"","installPlacePinyin":"","latitudeStr":"","longitudeStr":"","matrixCode":"","nameStr":"4楼电梯前","pinyin":"4ldtq","pinyinStr":"4ldtq","recLocation":"1","recordPos":"1","remark":"","seqIdx":13,"streamLinkType":"","streamType":"","structureFlag":"","treeNodeIndexcode":"42112304002160708266","treeNodePath":"0@42112304002160708266","treeNodePathStr":"0@42112304002160708266","vagIndexCode":"180626010020934790","viewshed":""},"indexCode":"1253955761310011866","isOnline":0,"latitude":"","longitude":"","name":"4楼电梯前","parentIndexCode":"42112304002160708266","pixel":1,"updateTime":1529989581106},{"cameraId":"15","cameraType":0,"createTime":1529989581107,"decodetag":"hikvision","description":"","extraField":{"appCode":"100005","cameraDeviceType":"10030","chanNum":16,"deviceIdx":"125395576","elevationStr":"","enable":"1","installPlace":"","installPlacePinyin":"","latitudeStr":"","longitudeStr":"","matrixCode":"","nameStr":"4楼东","pinyin":"4ld","pinyinStr":"4ld","recLocation":"1","recordPos":"1","remark":"","seqIdx":15,"streamLinkType":"","streamType":"","structureFlag":"","treeNodeIndexcode":"42112304002160708266","treeNodePath":"0@42112304002160708266","treeNodePathStr":"0@42112304002160708266","vagIndexCode":"180626010020934790","viewshed":""},"indexCode":"1253955761310016429","isOnline":0,"latitude":"","longitude":"","name":"4楼东","parentIndexCode":"42112304002160708266","pixel":1,"updateTime":1529989581107},{"cameraId":"16","cameraType":0,"createTime":1529989581108,"decodetag":"hikvision","description":"","extraField":{"appCode":"100005","cameraDeviceType":"10030","chanNum":17,"deviceIdx":"125395576","elevationStr":"","enable":"1","installPlace":"","installPlacePinyin":"","latitudeStr":"","longitudeStr":"","matrixCode":"","nameStr":"5楼东","pinyin":"5ld","pinyinStr":"5ld","recLocation":"1","recordPos":"1","remark":"","seqIdx":16,"streamLinkType":"","streamType":"","structureFlag":"","treeNodeIndexcode":"42112304002160708266","treeNodePath":"0@42112304002160708266","treeNodePathStr":"0@42112304002160708266","vagIndexCode":"180626010020934790","viewshed":""},"indexCode":"1253955761310015058","isOnline":0,"latitude":"","longitude":"","name":"5楼东","parentIndexCode":"42112304002160708266","pixel":1,"updateTime":1529989581108},{"cameraId":"17","cameraType":0,"createTime":1529989581108,"decodetag":"hikvision","description":"","extraField":{"appCode":"100005","cameraDeviceType":"10030","chanNum":18,"deviceIdx":"125395576","elevationStr":"","enable":"1","installPlace":"","installPlacePinyin":"","latitudeStr":"","longitudeStr":"","matrixCode":"","nameStr":"6楼电梯前","pinyin":"6ldtq","pinyinStr":"6ldtq","recLocation":"1","recordPos":"1","remark":"","seqIdx":17,"streamLinkType":"","streamType":"","structureFlag":"","treeNodeIndexcode":"42112304002160708266","treeNodePath":"0@42112304002160708266","treeNodePathStr":"0@42112304002160708266","vagIndexCode":"180626010020934790","viewshed":""},"indexCode":"1253955761310017626","isOnline":0,"latitude":"","longitude":"","name":"6楼电梯前","parentIndexCode":"42112304002160708266","pixel":1,"updateTime":1529989581108},{"cameraId":"18","cameraType":0,"createTime":1529989581109,"decodetag":"hikvision","description":"","extraField":{"appCode":"100005","cameraDeviceType":"10030","chanNum":19,"deviceIdx":"125395576","elevationStr":"","enable":"1","installPlace":"","installPlacePinyin":"","latitudeStr":"","longitudeStr":"","matrixCode":"","nameStr":"3楼电梯前","pinyin":"3ldtq","pinyinStr":"3ldtq","recLocation":"1","recordPos":"1","remark":"","seqIdx":18,"streamLinkType":"","streamType":"","structureFlag":"","treeNodeIndexcode":"42112304002160708266","treeNodePath":"0@42112304002160708266","treeNodePathStr":"0@42112304002160708266","vagIndexCode":"180626010020934790","viewshed":""},"indexCode":"1253955761310017231","isOnline":0,"latitude":"","longitude":"","name":"3楼电梯前","parentIndexCode":"42112304002160708266","pixel":1,"updateTime":1529989581109},{"cameraId":"19","cameraType":0,"createTime":1529989581109,"decodetag":"hikvision","description":"","extraField":{"appCode":"100005","cameraDeviceType":"10030","chanNum":20,"deviceIdx":"125395576","elevationStr":"","enable":"1","installPlace":"","installPlacePinyin":"","latitudeStr":"","longitudeStr":"","matrixCode":"","nameStr":"3楼西","pinyin":"3lx","pinyinStr":"3lx","recLocation":"1","recordPos":"1","remark":"","seqIdx":19,"streamLinkType":"","streamType":"","structureFlag":"","treeNodeIndexcode":"42112304002160708266","treeNodePath":"0@42112304002160708266","treeNodePathStr":"0@42112304002160708266","vagIndexCode":"180626010020934790","viewshed":""},"indexCode":"1253955761310011859","isOnline":0,"latitude":"","longitude":"","name":"3楼西","parentIndexCode":"42112304002160708266","pixel":1,"updateTime":1529989581109},{"cameraId":"20","cameraType":0,"createTime":1529989581110,"decodetag":"hikvision","description":"","extraField":{"appCode":"100005","cameraDeviceType":"10030","chanNum":21,"deviceIdx":"125395576","elevationStr":"","enable":"1","installPlace":"","installPlacePinyin":"","latitudeStr":"","longitudeStr":"","matrixCode":"","nameStr":"2楼电梯","pinyin":"2ldt","pinyinStr":"2ldt","recLocation":"1","recordPos":"1","remark":"","seqIdx":20,"streamLinkType":"","streamType":"","structureFlag":"","treeNodeIndexcode":"42112304002160708266","treeNodePath":"0@42112304002160708266","treeNodePathStr":"0@42112304002160708266","vagIndexCode":"180626010020934790","viewshed":""},"indexCode":"1253955761310011565","isOnline":0,"latitude":"","longitude":"","name":"2楼电梯","parentIndexCode":"42112304002160708266","pixel":1,"updateTime":1529989581110},{"cameraId":"21","cameraType":0,"createTime":1529989581110,"decodetag":"hikvision","description":"","extraField":{"appCode":"100005","cameraDeviceType":"10030","chanNum":22,"deviceIdx":"125395576","elevationStr":"","enable":"1","installPlace":"","installPlacePinyin":"","latitudeStr":"","longitudeStr":"","matrixCode":"","nameStr":"2楼电梯前","pinyin":"2ldtq","pinyinStr":"2ldtq","recLocation":"1","recordPos":"1","remark":"","seqIdx":21,"streamLinkType":"","streamType":"","structureFlag":"","treeNodeIndexcode":"42112304002160708266","treeNodePath":"0@42112304002160708266","treeNodePathStr":"0@42112304002160708266","vagIndexCode":"180626010020934790","viewshed":""},"indexCode":"1253955761310018035","isOnline":0,"latitude":"","longitude":"","name":"2楼电梯前","parentIndexCode":"42112304002160708266","pixel":1,"updateTime":1529989581110},{"cameraId":"22","cameraType":0,"createTime":1529989581111,"decodetag":"hikvision","description":"","extraField":{"appCode":"100005","cameraDeviceType":"10030","chanNum":23,"deviceIdx":"125395576","elevationStr":"","enable":"1","installPlace":"","installPlacePinyin":"","latitudeStr":"","longitudeStr":"","matrixCode":"","nameStr":"2楼西","pinyin":"2lx","pinyinStr":"2lx","recLocation":"1","recordPos":"1","remark":"","seqIdx":22,"streamLinkType":"","streamType":"","structureFlag":"","treeNodeIndexcode":"42112304002160708266","treeNodePath":"0@42112304002160708266","treeNodePathStr":"0@42112304002160708266","vagIndexCode":"180626010020934790","viewshed":""},"indexCode":"1253955761310017949","isOnline":0,"latitude":"","longitude":"","name":"2楼西","parentIndexCode":"42112304002160708266","pixel":1,"updateTime":1529989581111},{"cameraId":"23","cameraType":0,"createTime":1529989581111,"decodetag":"hikvision","description":"","extraField":{"appCode":"100005","cameraDeviceType":"10030","chanNum":24,"deviceIdx":"125395576","elevationStr":"","enable":"1","installPlace":"","installPlacePinyin":"","latitudeStr":"","longitudeStr":"","matrixCode":"","nameStr":"2楼东","pinyin":"2ld","pinyinStr":"2ld","recLocation":"1","recordPos":"1","remark":"","seqIdx":23,"streamLinkType":"","streamType":"","structureFlag":"","treeNodeIndexcode":"42112304002160708266","treeNodePath":"0@42112304002160708266","treeNodePathStr":"0@42112304002160708266","vagIndexCode":"180626010020934790","viewshed":""},"indexCode":"1253955761310010525","isOnline":0,"latitude":"","longitude":"","name":"2楼东","parentIndexCode":"42112304002160708266","pixel":1,"updateTime":1529989581111},{"cameraId":"24","cameraType":0,"createTime":1529989581112,"decodetag":"hikvision","description":"","extraField":{"appCode":"100005","cameraDeviceType":"10030","chanNum":25,"deviceIdx":"125395576","elevationStr":"","enable":"1","installPlace":"","installPlacePinyin":"","latitudeStr":"","longitudeStr":"","matrixCode":"","nameStr":"大门右","pinyin":"dmy","pinyinStr":"dmy","recLocation":"1","recordPos":"1","remark":"","seqIdx":24,"streamLinkType":"","streamType":"","structureFlag":"","treeNodeIndexcode":"42112304002160708266","treeNodePath":"0@42112304002160708266","treeNodePathStr":"0@42112304002160708266","vagIndexCode":"180626010020934790","viewshed":""},"indexCode":"1253955761310018277","isOnline":0,"latitude":"","longitude":"","name":"大门右","parentIndexCode":"42112304002160708266","pixel":1,"updateTime":1529989581112},{"cameraId":"25","cameraType":0,"createTime":1529989581112,"decodetag":"hikvision","description":"","extraField":{"appCode":"100005","cameraDeviceType":"10030","chanNum":26,"deviceIdx":"125395576","elevationStr":"","enable":"1","installPlace":"","installPlacePinyin":"","latitudeStr":"","longitudeStr":"","matrixCode":"","nameStr":"后右大门","pinyin":"hydm","pinyinStr":"hydm","recLocation":"1","recordPos":"1","remark":"","seqIdx":25,"streamLinkType":"","streamType":"","structureFlag":"","treeNodeIndexcode":"42112304002160708266","treeNodePath":"0@42112304002160708266","treeNodePathStr":"0@42112304002160708266","vagIndexCode":"180626010020934790","viewshed":""},"indexCode":"1253955761310016330","isOnline":0,"latitude":"","longitude":"","name":"后右大门","parentIndexCode":"42112304002160708266","pixel":1,"updateTime":1529989581112},{"cameraId":"26","cameraType":0,"createTime":1529989581113,"decodetag":"hikvision","description":"","extraField":{"appCode":"100005","cameraDeviceType":"10030","chanNum":27,"deviceIdx":"125395576","elevationStr":"","enable":"1","installPlace":"","installPlacePinyin":"","latitudeStr":"","longitudeStr":"","matrixCode":"","nameStr":"后院2","pinyin":"hy2","pinyinStr":"hy2","recLocation":"1","recordPos":"1","remark":"","seqIdx":26,"streamLinkType":"","streamType":"","structureFlag":"","treeNodeIndexcode":"42112304002160708266","treeNodePath":"0@42112304002160708266","treeNodePathStr":"0@42112304002160708266","vagIndexCode":"180626010020934790","viewshed":""},"indexCode":"1253955761310015648","isOnline":0,"latitude":"","longitude":"","name":"后院2","parentIndexCode":"42112304002160708266","pixel":1,"updateTime":1529989581113},{"cameraId":"27","cameraType":0,"createTime":1529989581113,"decodetag":"hikvision","description":"","extraField":{"appCode":"100005","cameraDeviceType":"10030","chanNum":28,"deviceIdx":"125395576","elevationStr":"","enable":"1","installPlace":"","installPlacePinyin":"","latitudeStr":"","longitudeStr":"","matrixCode":"","nameStr":"后院","pinyin":"hy","pinyinStr":"hy","recLocation":"1","recordPos":"1","remark":"","seqIdx":27,"streamLinkType":"","streamType":"","structureFlag":"","treeNodeIndexcode":"42112304002160708266","treeNodePath":"0@42112304002160708266","treeNodePathStr":"0@42112304002160708266","vagIndexCode":"180626010020934790","viewshed":""},"indexCode":"1253955761310016341","isOnline":0,"latitude":"","longitude":"","name":"后院","parentIndexCode":"42112304002160708266","pixel":1,"updateTime":1529989581113},{"cameraId":"28","cameraType":0,"createTime":1529989581114,"decodetag":"hikvision","description":"","extraField":{"appCode":"100005","cameraDeviceType":"10030","chanNum":29,"deviceIdx":"125395576","elevationStr":"","enable":"1","installPlace":"","installPlacePinyin":"","latitudeStr":"","longitudeStr":"","matrixCode":"","nameStr":"大门左","pinyin":"dmz","pinyinStr":"dmz","recLocation":"1","recordPos":"1","remark":"","seqIdx":28,"streamLinkType":"","streamType":"","structureFlag":"","treeNodeIndexcode":"42112304002160708266","treeNodePath":"0@42112304002160708266","treeNodePathStr":"0@42112304002160708266","vagIndexCode":"180626010020934790","viewshed":""},"indexCode":"1253955761310019036","isOnline":0,"latitude":"","longitude":"","name":"大门左","parentIndexCode":"42112304002160708266","pixel":1,"updateTime":1529989581114},{"cameraId":"29","cameraType":0,"createTime":1529989581114,"decodetag":"hikvision","description":"","extraField":{"appCode":"100005","cameraDeviceType":"10030","chanNum":30,"deviceIdx":"125395576","elevationStr":"","enable":"1","installPlace":"","installPlacePinyin":"","latitudeStr":"","longitudeStr":"","matrixCode":"","nameStr":"后院食堂","pinyin":"hyst","pinyinStr":"hyst","recLocation":"1","recordPos":"1","remark":"","seqIdx":29,"streamLinkType":"","streamType":"","structureFlag":"","treeNodeIndexcode":"42112304002160708266","treeNodePath":"0@42112304002160708266","treeNodePathStr":"0@42112304002160708266","vagIndexCode":"180626010020934790","viewshed":""},"indexCode":"1253955761310017178","isOnline":0,"latitude":"","longitude":"","name":"后院食堂","parentIndexCode":"42112304002160708266","pixel":1,"updateTime":1529989581114},{"cameraId":"30","cameraType":0,"createTime":1529989581115,"decodetag":"hikvision","description":"","extraField":{"appCode":"100005","cameraDeviceType":"10030","chanNum":31,"deviceIdx":"125395576","elevationStr":"","enable":"1","installPlace":"","installPlacePinyin":"","latitudeStr":"","longitudeStr":"","matrixCode":"","nameStr":"门卫1","pinyin":"mw1","pinyinStr":"mw1","recLocation":"1","recordPos":"1","remark":"","seqIdx":30,"streamLinkType":"","streamType":"","structureFlag":"","treeNodeIndexcode":"42112304002160708266","treeNodePath":"0@42112304002160708266","treeNodePathStr":"0@42112304002160708266","vagIndexCode":"180626010020934790","viewshed":""},"indexCode":"1253955761310016667","isOnline":0,"latitude":"","longitude":"","name":"门卫1","parentIndexCode":"42112304002160708266","pixel":1,"updateTime":1529989581115},{"cameraId":"31","cameraType":0,"createTime":1529989581116,"decodetag":"hikvision","description":"","extraField":{"appCode":"100005","cameraDeviceType":"10030","chanNum":32,"deviceIdx":"125395576","elevationStr":"","enable":"1","installPlace":"","installPlacePinyin":"","latitudeStr":"","longitudeStr":"","matrixCode":"","nameStr":"门卫2","pinyin":"mw2","pinyinStr":"mw2","recLocation":"1","recordPos":"1","remark":"","seqIdx":31,"streamLinkType":"","streamType":"","structureFlag":"","treeNodeIndexcode":"42112304002160708266","treeNodePath":"0@42112304002160708266","treeNodePathStr":"0@42112304002160708266","vagIndexCode":"180626010020934790","viewshed":""},"indexCode":"1253955761310016291","isOnline":0,"latitude":"","longitude":"","name":"门卫2","parentIndexCode":"42112304002160708266","pixel":1,"updateTime":1529989581116},{"cameraId":"72","cameraType":0,"createTime":1539054801110,"decodetag":"hikvision","description":"","extraField":{"appCode":"100005","cameraDeviceType":"10030","chanNum":3,"deviceIdx":"125395576","elevationStr":"","enable":"1","installPlace":"","installPlacePinyin":"","latitudeStr":"","longitudeStr":"","matrixCode":"","nameStr":"1L大厅","pinyin":"1Ldt","pinyinStr":"1Ldt","recLocation":"","recordPos":"0","remark":"","seqIdx":41,"streamLinkType":"","streamType":"","structureFlag":"","treeNodeIndexcode":"42112304002160708266","treeNodePath":"0@42112304002160708266","treeNodePathStr":"0@42112304002160708266","vagIndexCode":"180626010020934790","viewshed":""},"indexCode":"1253955761310017610","isOnline":0,"latitude":"","longitude":"","name":"1L大厅","parentIndexCode":"42112304002160708266","pixel":1,"updateTime":1539054801110}]
     * msg : 成功
     * page : {"page":0,"size":1000,"total":31}
     */

    private String code;
    private String msg;
    private PageBean page;
    private List<DataBean> data;

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public String getMsg() {
        return msg;
    }

    public void setMsg(String msg) {
        this.msg = msg;
    }

    public PageBean getPage() {
        return page;
    }

    public void setPage(PageBean page) {
        this.page = page;
    }

    public List<DataBean> getData() {
        return data;
    }

    public void setData(List<DataBean> data) {
        this.data = data;
    }

    public static class PageBean {
        /**
         * page : 0
         * size : 1000
         * total : 31
         */

        private int page;
        private int size;
        private int total;

        public int getPage() {
            return page;
        }

        public void setPage(int page) {
            this.page = page;
        }

        public int getSize() {
            return size;
        }

        public void setSize(int size) {
            this.size = size;
        }

        public int getTotal() {
            return total;
        }

        public void setTotal(int total) {
            this.total = total;
        }
    }

    public static class DataBean{
        /**
         * cameraId : 1
         * cameraType : 0
         * createTime : 1529989581099
         * decodetag : hikvision
         * description :
         * extraField : {"appCode":"100005","cameraDeviceType":"10030","chanNum":1,"deviceIdx":"125395576","elevationStr":"","enable":"1","installPlace":"","installPlacePinyin":"","latitudeStr":"","longitudeStr":"","matrixCode":"","nameStr":"3楼东","pinyin":"3ld","pinyinStr":"3ld","recLocation":"1","recordPos":"1","remark":"","seqIdx":1,"streamLinkType":"","streamType":"","structureFlag":"","treeNodeIndexcode":"42112304002160708266","treeNodePath":"0@42112304002160708266","treeNodePathStr":"0@42112304002160708266","vagIndexCode":"180626010020934790","viewshed":""}
         * indexCode : 1253955761310011638
         * isOnline : 0
         * latitude :
         * longitude :
         * name : 3楼东
         * parentIndexCode : 42112304002160708266
         * pixel : 1
         * updateTime : 1529989581099
         */

        private String cameraId;
        private int cameraType;
        private long createTime;
        private String decodetag;
        private String description;
        private ExtraFieldBean extraField;
        private String indexCode;
        private int isOnline;
        private String latitude;
        private String longitude;
        private String name;
        private String parentIndexCode;
        private int pixel;
        private long updateTime;

        public String getCameraId() {
            return cameraId;
        }

        public void setCameraId(String cameraId) {
            this.cameraId = cameraId;
        }

        public int getCameraType() {
            return cameraType;
        }

        public void setCameraType(int cameraType) {
            this.cameraType = cameraType;
        }

        public long getCreateTime() {
            return createTime;
        }

        public void setCreateTime(long createTime) {
            this.createTime = createTime;
        }

        public String getDecodetag() {
            return decodetag;
        }

        public void setDecodetag(String decodetag) {
            this.decodetag = decodetag;
        }

        public String getDescription() {
            return description;
        }

        public void setDescription(String description) {
            this.description = description;
        }

        public ExtraFieldBean getExtraField() {
            return extraField;
        }

        public void setExtraField(ExtraFieldBean extraField) {
            this.extraField = extraField;
        }

        public String getIndexCode() {
            return indexCode;
        }

        public void setIndexCode(String indexCode) {
            this.indexCode = indexCode;
        }

        public int getIsOnline() {
            return isOnline;
        }

        public void setIsOnline(int isOnline) {
            this.isOnline = isOnline;
        }

        public String getLatitude() {
            return latitude;
        }

        public void setLatitude(String latitude) {
            this.latitude = latitude;
        }

        public String getLongitude() {
            return longitude;
        }

        public void setLongitude(String longitude) {
            this.longitude = longitude;
        }

        public String getName() {
            return name;
        }

        public void setName(String name) {
            this.name = name;
        }

        public String getParentIndexCode() {
            return parentIndexCode;
        }

        public void setParentIndexCode(String parentIndexCode) {
            this.parentIndexCode = parentIndexCode;
        }

        public int getPixel() {
            return pixel;
        }

        public void setPixel(int pixel) {
            this.pixel = pixel;
        }

        public long getUpdateTime() {
            return updateTime;
        }

        public void setUpdateTime(long updateTime) {
            this.updateTime = updateTime;
        }


        public static class ExtraFieldBean {
            /**
             * appCode : 100005
             * cameraDeviceType : 10030
             * chanNum : 1
             * deviceIdx : 125395576
             * elevationStr :
             * enable : 1
             * installPlace :
             * installPlacePinyin :
             * latitudeStr :
             * longitudeStr :
             * matrixCode :
             * nameStr : 3楼东
             * pinyin : 3ld
             * pinyinStr : 3ld
             * recLocation : 1
             * recordPos : 1
             * remark :
             * seqIdx : 1
             * streamLinkType :
             * streamType :
             * structureFlag :
             * treeNodeIndexcode : 42112304002160708266
             * treeNodePath : 0@42112304002160708266
             * treeNodePathStr : 0@42112304002160708266
             * vagIndexCode : 180626010020934790
             * viewshed :
             */

            private String appCode;
            private String cameraDeviceType;
            private int chanNum;
            private String deviceIdx;
            private String elevationStr;
            private String enable;
            private String installPlace;
            private String installPlacePinyin;
            private String latitudeStr;
            private String longitudeStr;
            private String matrixCode;
            private String nameStr;
            private String pinyin;
            private String pinyinStr;
            private String recLocation;
            private String recordPos;
            private String remark;
            private int seqIdx;
            private String streamLinkType;
            private String streamType;
            private String structureFlag;
            private String treeNodeIndexcode;
            private String treeNodePath;
            private String treeNodePathStr;
            private String vagIndexCode;
            private String viewshed;

            public String getAppCode() {
                return appCode;
            }

            public void setAppCode(String appCode) {
                this.appCode = appCode;
            }

            public String getCameraDeviceType() {
                return cameraDeviceType;
            }

            public void setCameraDeviceType(String cameraDeviceType) {
                this.cameraDeviceType = cameraDeviceType;
            }

            public int getChanNum() {
                return chanNum;
            }

            public void setChanNum(int chanNum) {
                this.chanNum = chanNum;
            }

            public String getDeviceIdx() {
                return deviceIdx;
            }

            public void setDeviceIdx(String deviceIdx) {
                this.deviceIdx = deviceIdx;
            }

            public String getElevationStr() {
                return elevationStr;
            }

            public void setElevationStr(String elevationStr) {
                this.elevationStr = elevationStr;
            }

            public String getEnable() {
                return enable;
            }

            public void setEnable(String enable) {
                this.enable = enable;
            }

            public String getInstallPlace() {
                return installPlace;
            }

            public void setInstallPlace(String installPlace) {
                this.installPlace = installPlace;
            }

            public String getInstallPlacePinyin() {
                return installPlacePinyin;
            }

            public void setInstallPlacePinyin(String installPlacePinyin) {
                this.installPlacePinyin = installPlacePinyin;
            }

            public String getLatitudeStr() {
                return latitudeStr;
            }

            public void setLatitudeStr(String latitudeStr) {
                this.latitudeStr = latitudeStr;
            }

            public String getLongitudeStr() {
                return longitudeStr;
            }

            public void setLongitudeStr(String longitudeStr) {
                this.longitudeStr = longitudeStr;
            }

            public String getMatrixCode() {
                return matrixCode;
            }

            public void setMatrixCode(String matrixCode) {
                this.matrixCode = matrixCode;
            }

            public String getNameStr() {
                return nameStr;
            }

            public void setNameStr(String nameStr) {
                this.nameStr = nameStr;
            }

            public String getPinyin() {
                return pinyin;
            }

            public void setPinyin(String pinyin) {
                this.pinyin = pinyin;
            }

            public String getPinyinStr() {
                return pinyinStr;
            }

            public void setPinyinStr(String pinyinStr) {
                this.pinyinStr = pinyinStr;
            }

            public String getRecLocation() {
                return recLocation;
            }

            public void setRecLocation(String recLocation) {
                this.recLocation = recLocation;
            }

            public String getRecordPos() {
                return recordPos;
            }

            public void setRecordPos(String recordPos) {
                this.recordPos = recordPos;
            }

            public String getRemark() {
                return remark;
            }

            public void setRemark(String remark) {
                this.remark = remark;
            }

            public int getSeqIdx() {
                return seqIdx;
            }

            public void setSeqIdx(int seqIdx) {
                this.seqIdx = seqIdx;
            }

            public String getStreamLinkType() {
                return streamLinkType;
            }

            public void setStreamLinkType(String streamLinkType) {
                this.streamLinkType = streamLinkType;
            }

            public String getStreamType() {
                return streamType;
            }

            public void setStreamType(String streamType) {
                this.streamType = streamType;
            }

            public String getStructureFlag() {
                return structureFlag;
            }

            public void setStructureFlag(String structureFlag) {
                this.structureFlag = structureFlag;
            }

            public String getTreeNodeIndexcode() {
                return treeNodeIndexcode;
            }

            public void setTreeNodeIndexcode(String treeNodeIndexcode) {
                this.treeNodeIndexcode = treeNodeIndexcode;
            }

            public String getTreeNodePath() {
                return treeNodePath;
            }

            public void setTreeNodePath(String treeNodePath) {
                this.treeNodePath = treeNodePath;
            }

            public String getTreeNodePathStr() {
                return treeNodePathStr;
            }

            public void setTreeNodePathStr(String treeNodePathStr) {
                this.treeNodePathStr = treeNodePathStr;
            }

            public String getVagIndexCode() {
                return vagIndexCode;
            }

            public void setVagIndexCode(String vagIndexCode) {
                this.vagIndexCode = vagIndexCode;
            }

            public String getViewshed() {
                return viewshed;
            }

            public void setViewshed(String viewshed) {
                this.viewshed = viewshed;
            }
        }
    }
}
