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
                <select v-model="searchOption">
                    <option value="all">:: 전체 ::</option>
                    <option value="title">:: 제목 ::</option>
                    <option value="id">:: 작성자 ::</option>
                </select>
                <input v-model="keyword">
                <button @click="fnList">검색</button>

            </div>
            <div>
                <select v-model="pageSize" @change="fnList">
                    <option value="5">5개씩</option>
                    <option value="10">10개씩</option>
                    <option value="20">20개씩</option>
                </select>

                <select v-model="kind" @change="fnList">
                    <option value="">:: 전체 ::</option>
                    <option value="1">:: 공지사항 ::</option>
                    <option value="2">:: 자유게시판 ::</option>
                    <option value="3">:: 문의게시판 ::</option>
                </select>

                <select v-model="order" @change="fnList">
                    <option value="num">:: 번호순 ::</option>
                    <option value="title">:: 제목순 ::</option>
                    <option value="cnt">:: 조회수 ::</option>
                    <option value="time">:: 등록순 ::</option>
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
                        <td>{{item.boardNo}}</td>
                        <td>
                            <a href="javascript:;" @click="fnView(item.boardNo)">{{item.title}}</a>
                            <span v-if="item.commentCnt != 0" style="color:red;"> [{{item.commentCnt}}]</span>
                        </td>
                        <td>{{item.userId}}</td>
                        <td> {{item.cnt}}</td>
                        <td>{{item.cdate}}</td>
                        <td>
                            <button v-if="sessionId == item.userId || status == 'A'"
                                @click="fnRemove(item.boardNo)">삭제</button>
                        </td>
                    </tr>
                </table>
                <div>
                    <a @click="left" href="javascript:;" id="index" v-if="page != 1">◀</a>
                    <a id="index" href="javascript:;" v-for="num in index" @click="fnpage(num)">
                        <span :class="{active : page == num}">{{num}}</span>
                        <!--page == num일때만 active 반영-->
                    </a>
                    <a @click="right" href="javascript:;" id="index" v-if="page != index">▶</a>
                </div>
            </div>
            <div>
                <a href="board-add.do"><button>글쓰기</button></a>
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
                    kind: "",
                    order: "num",
                    keyword: "", // 검색어
                    searchOption: "all", // 검색 옵션(기본 : 전체)

                    pageSize: "5", // 한 페이지에 출력할 개수
                    page: 1, // 선택한 페이지
                    index: 0, // 최대 페이지 값

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
                        order: self.order,
                        keyword: self.keyword,
                        searchOption: self.searchOption,
                        pageSize: self.pageSize,
                        page: (self.page - 1) * self.pageSize
                    };
                    $.ajax({
                        url: "board-list.dox",
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
                fnRemove: function (boardNo) {
                    let self = this;
                    let param = {
                        boardNo: boardNo
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
                fnView: function (boardNo) {
                    pageChange("board-view.do", { boardNo: boardNo });
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
            }
        });

        app.mount('#app');
    </script>