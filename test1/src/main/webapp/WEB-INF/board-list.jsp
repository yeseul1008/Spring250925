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
        <script src="/js/page-change.js"></script>
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
            <div>
                <select v-model="kind" @change="fnList()"> <!--@change="fnList": 클릭할때마다 호출-->
                    <option value="">:: 전체 ::</option>
                    <option value="1">:: 공지사항 ::</option>
                    <option value="2">:: 자유게시판 ::</option>
                    <option value="3">:: 문의게시판 ::</option>
                </select>

                <select v-model="sort" @change="fnList()">
                    <option value="num">:: 번호순 ::</option> <!--오름차순-->
                    <option value="title">:: 제목순 ::</option> <!--오름차순-->
                    <option value="cnt">:: 조회순 ::</option> <!--내림차순-->
                </select>
            </div>
            <div>
                <table>
                    <tr>
                        <th>번호</th>
                        <th>제목</th>
                        <th>작성자</th>
                        <th>조회수</th>
                        <th>작성일</th>
                        <th>삭제</th>
                    </tr>
                    <tr v-for="item in list">
                        <td>{{item.boardno}}</td>
                        <td><a href="javascript:;" @click="fnView(item.boardno)">{{item.title}}</a></td>
                        <td>{{item.userid}}</td>
                        <td>{{item.cnt}}</td>
                        <td>{{item.cdate}}</td>
                        <td v-if="sessionId == item.userid || status == 'A'">
                            <button @click="fnRemove(item.boardno)">삭제</button>
                        </td>
                        <td v-else>
                            ---
                        </td>
                    </tr>
                </table>
                <button @click="fnAdd()">게시글 등록</button>
            </div>
        </div>
    </body>

    </html>

    <script>
        const app = Vue.createApp({

            data() {
                return {
                    // 변수 - (key : value)
                    keyword: "",
                    list: [],
                    boardno: "",
                    kind: "",
                    sort: "num",
                    sessionId: "${sessionId}",
                    status: "${sessionStatus}"
                };
            },
            methods: {
                // 함수(메소드) - (key : function())
                fnList: function () {

                    let self = this;
                    let param = {
                        kind: self.kind,
                        sort: self.sort
                    };
                    $.ajax({
                        url: "board-list.dox",
                        dataType: "json",
                        type: "POST",
                        data: param,
                        success: function (data) {
                            console.log(data);
                            self.list = data.list;
                        }
                    });
                },
                fnRemove: function (boardno) {
                    if (!confirm("삭제하시겠습니까?")) {
                        return;
                    }
                    let self = this;
                    let param = {
                        boardno: boardno
                        //변수에 있는걸 보낼때만 self.을 씀. 지금은 파라미터로 받은 값을 보내는거기 때문에 안씀
                    };
                    $.ajax({
                        url: "board-delete.dox",
                        dataType: "json",
                        type: "POST",
                        data: param,
                        success: function (data) {
                            alert("삭제되었습니다.");
                            self.fnList();
                        }
                    });
                },
                fnAdd: function () {
                    let self = this;
                    
                    location.href = "board-add.do";
                },
                fnView: function (boardno) {
                    pageChange("board-view.do", { boardno: boardno });
                }
            }, // methods
            mounted() {
                // 처음 시작할 때 실행되는 부분
                let self = this;
                self.fnList();
            }
        });

        app.mount('#app');
    </script>