<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<!DOCTYPE html>
<html lang="en">

<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>Document</title>
    <script src="https://code.jquery.com/jquery-3.7.1.js"
        integrity="sha256-eKhayi8LEQwp4NKxN+CfCh+3qOVUtJn3QNZ0TciWLP4=" crossorigin="anonymous"></script>
    <script src="https://unpkg.com/vue@3/dist/vue.global.js"></script>
    <style>
        table,
        tr,
        td,
        th {
            border: 1px solid black;
            border-collapse: collapse;
            padding: 5px 10px;
            text-align: center;
        }

        th {
            background-color: beige;
        }

        tr:nth-child(even) {
            background-color: azure;
        }

        #index {
            margin: 5px;
            text-decoration: none;
        }

        .active {
            color: black;
            font-weight: bold;
        }
    </style>
</head>

<body>
    <div id="app">
        <!-- html 코드는 id가 app인 태그 안에서 작업 -->
        <div>
            <div>
                도/특별시 :  
                <select v-model="si" @change="fnList">
                    <option value="all">:: 전체 ::</option>
                    <option :value="item.si" v-for="item in siList">{{item.si}}</option>
                    <!-- 동적인 값은 앞에 :붙임 -->
                </select>
            </div>
            <table>
                <tr>
                    <th>시</th>
                    <th>구</th>
                    <th>동</th>
                    <th>NX</th>
                    <th>NY</th>
                </tr>
                <tr v-for="item in list">
                    <td>{{item.si}}</td>
                    <td>{{item.gu}}</td>
                    <td>{{item.dong}}</td>
                    <td>{{item.nx}}</td>
                    <td>{{item.ny}}</td>
                </tr>
            </table>
        </div>
        <div>
            <a @click="left" href="javascript:;" id="index" v-if="page != 1">◀</a>

            <a id="index" href="javascript:;" v-for="num in index" @click="fnpage(num)">
                <span :class="{active : page == num}">{{num}}</span>
                <!--page == num일때만 active 반영-->
            </a>

            <a @click="right" href="javascript:;" id="index" v-if="page != index">▶</a>
        </div>
    </div>
</body>

</html>

<script>
    const app = Vue.createApp({
        data() {
            return {
                // 변수 - (key : value)
                list: [],
                pageSize: "20", // 한 페이지에 출력할 개수
                page: 1,        // 선택한 페이지
                index: 0,        // 최대 페이지 값
                siList: [],
                si: "all" // 선택한 시(도)의 값
            };
        },
        methods: {
            // 함수(메소드) - (key : function())
            fnList: function () {
                let self = this;
                let param = {
                    pageSize: self.pageSize,
                    page: (self.page - 1) * self.pageSize,
                    si : self.si
                };
                $.ajax({
                    url: "/area-list.dox",
                    dataType: "json",
                    type: "POST",
                    data: param,
                    success: function (data) {
                        console.log(data);
                        self.list = data.list;
                        self.index = Math.ceil(data.cnt / self.pageSize);
                    }
                });
            },
            fnSiList: function () { // 시 리스트 17개만 가져오기
                let self = this;
                let param = {
                
                };
                $.ajax({
                    url: "/area/si.dox",
                    dataType: "json",
                    type: "POST",
                    data: param,
                    success: function (data) {
                        console.log(data);
                        self.siList = data.list;
                    }
                });
            },
            fnpage: function (num) {
                let self = this;
                self.page = num;
                self.fnList();
            },
            left: function () {
                let self = this;
                self.page = self.page - 1;
                self.fnList();
            },
            right: function () {
                let self = this;
                self.page = self.page + 1;
                self.fnList();
            }
        }, // methods
        mounted() {
            // 처음 시작할 때 실행되는 부분
            let self = this;
            self.fnList();
            self.fnSiList();
        }
    });

    app.mount('#app');
</script>
