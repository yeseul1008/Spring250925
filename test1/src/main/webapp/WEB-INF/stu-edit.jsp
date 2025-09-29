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
        </style>
    </head>

    <body>
        <div id="app">
            <!-- html 코드는 id가 app인 태그 안에서 작업 -->
            <table>
                <tr>
                    <th>이름</th>
                    <td><input v-model="stuName"></td>

                </tr>
                <tr>
                    <th>학과</th>
                    <td><input v-model="stuDept"></td>
                </tr>
            </table>
            <button @click="fnEdit">수정</button>
        </div>
    </body>

    </html>

    <script>
        const app = Vue.createApp({
            data() {
                return {
                    // 변수 - (key : value)
                    stuNo: "${stuNo}",
                    stuName: "",
                    stuDept: ""
                };
            },
            methods: {
                // 함수(메소드) - (key : function())
                fninfo: function () {
                    let self = this;
                    let param = { stuNo: self.stuNo };
                    $.ajax({
                        url: "stu-view.dox",
                        dataType: "json",
                        type: "POST",
                        data: param,
                        success: function (data) {
                            self.stuName = data.info.stuName;
                            self.stuDept = data.info.stuDept;
                        }
                    });
                },
                fnEdit : function() {
                    let self = this;
                    let param = { 
                        stuName : self.stuName,
                        stuDept : self.stuDept
                    };
                    $.ajax({
                        url: "stu-edit.dox",
                        dataType: "json",
                        type: "POST",
                        data: param,
                        success: function (data) {
                        self.fninfo();
                        }
                    });
                }
            }, // methods
            mounted() {
                // 처음 시작할 때 실행되는 부분
                let self = this;
                self.fninfo();
            }
        });

        app.mount('#app');
    </script>