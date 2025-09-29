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

            /* tr:nth-child(even){
            background-color: azure;
        } */
            textarea {
                width: 400px;
                height: 200px;
                resize: none;
                /* 크기 조절 막기 (선택) */
            }
        </style>
    </head>

    <body>
        <div id="app">
            <!-- html 코드는 id가 app인 태그 안에서 작업 -->

            <table>
                <tr>
                    <th>제목</th>
                    <td><input type="text" v-model="title"></td>

                </tr>
                <tr>
                    <th>작성자</th>
                    <td><input type="text" v-model="userId" disabled></td>
                </tr>
                <tr>
                    <th>내용</th>
                    <td><textarea v-model="contents"></textarea></td>
                </tr>
        </table>
        <button @click="fnAdd">추가</button>
    </div>
</body>
</html>

<script>
    const app = Vue.createApp({
        data() {
            return {
                // 변수 - (key : value)
                title : "",
                userId : "${sessionId}",
                contents : "",
                sessionId : "${sessionId}"
            };
        },
        methods: {
            // 함수(메소드) - (key : function())
            fnAdd: function () {
                if(!confirm("추가하시겠습니까?")){
                    return;
                }
                let self = this;
                let param = {
                    title : self.title,
                    userId : self.userId,
                    contents : self.contents
                };
                $.ajax({
                    url: "board-add.dox",
                    dataType: "json",
                    type: "POST",
                    data: param,
                    success: function (data) {
                        alert("추가되었습니다");
                        location.href = "board-list.do";
                    }
                });
            }
        }, // methods
        mounted() {
            // 처음 시작할 때 실행되는 부분
            let self = this;
            if(self.sessionId == ""){
                alert("로그인 후 이용해주세요");
                location.href = "/member/login.do";
            }
        }
    });

    app.mount('#app');
</script>