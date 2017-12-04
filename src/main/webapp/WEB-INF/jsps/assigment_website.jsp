<%@ page import="java.util.List" %>
<%@ page import="providesupport.model.WebsiteData" %><%--
  Created by IntelliJ IDEA.
  User: Zhenya
  Date: 22.11.2017
  Time: 18:22
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <link rel="stylesheet" href="https://cdnjs.cloudflare.com/ajax/libs/normalize/7.0.0/normalize.css">
   <link rel="stylesheet" href="/resouces/css/assigment_website.css">
    <title>Assigment</title>
</head>
<body>

<audio id="audio" src="http://www.soundjay.com/button/beep-07.wav" autostart="false" ></audio>
<%--<audio id="audio" src="https://www.soundjay.com/button/sounds/button-4.mp3" autostart="false" ></audio>--%>

<a onclick="playSound();"> Play</a>
<script>
    function playSound() {
        var sound = document.getElementById("audio");
        sound.play();
    }
</script>

<div class="web_start">




    <%--<div class="tools">--%>


        <form class="registr_form" action="/createWebsite" method="post">



    <div class="submit_tools">
        <input type="submit" class="button-blocl" value="Submit">
    </div>

    <div class="input">
    <div class="input_url">
        <input type="text" id="url" name="url" placeholder="URL">
    </div>
        <div class="input_url">
            <input type="text" id="waitingResponseCode" name="waitingResponseCode" placeholder="Response Code">
        </div>
    </div>

    <div class="input_time">
        <input type="text" id="time" name="time" placeholder="Time">
    </div>



<div class="head">

    <div class="input_byte">
        <div class="min_byte">
            <input type="text" id="min_byte" name="min_byte" placeholder="Min byte">
        </div>
        <div class="max_byte">
            <input type="text" id="max_byte" name="max_byte" placeholder="Max byte">
        </div>
    </div>


    <div class="input_time_load">
        <div class="time_ok">
            <input type="text" id="time_ok" name="time_ok" placeholder="Time OK">
        </div>
        <div class="time_warning">
            <input type="text" id="time_warning" name="time_warning" placeholder="Time WAR">
        </div>
        <div class="time_critical">
            <input type="text" id="time_critical" name="time_critical" placeholder="Time CR">
        </div>
    </div>
</div>
        </form>
<%--</div>--%>



<div class="title">
    <div class="title_menu">
        <div class="activ_menu">
            <h4>Activ</h4>
        </div>
        <div class="activ_input">
            <h4>URL </h4>
        </div>
        <div class="activ_input">
            <h4> Infirmation </h4>
        </div>
        <div class="activ_menu">
            <h4> Status </h4>
        </div>
        <div class="activ_menu">
            <h4> Update </h4>
        </div>
        <div class="activ_menu">
            <h4> Delite </h4>
        </div>
    </div>



    <div class="list">

        <%
            List <WebsiteData> websiteDataList = (List <WebsiteData>) request.getAttribute("websiteBy");
            for (WebsiteData list: websiteDataList){

                String goToEditWebsiteDataLink = "/edit-website/" + list.getId() + "/";
        %>

        <div class="title_list">

            <div class="input_button">
                <%--<form action="/edit-website" method="post">--%>
                    <%--<input type="hidden" class="input_button_in" name="website-id" value="<%out.print(list.getId());%>">--%>
                    <%--<button type="submit" class="input_button_in_n" ><%out.print(list.getActivity());%></button>--%>
                <%--</form>--%>

                    <a href="<%out.print(goToEditWebsiteDataLink);%>">
                        <button  class="input_button_in_n">
                            <%out.print(list.getActivity());%>
                        </button>
                    </a>
            </div>
            <div class="input_list">
                <div class="output">
                    <% out.print(list.getUrl());%>
                </div>
            </div>
            <div class="input_list">
                <div class="output">
fgggggddddddddddddddddddddddddddddddddhhhfbbbbbbbbbbbbbbbbbbbbbbgfddddddddd
                    </div>
            </div>
            <div class="input_status">
                <%out.print(list.getStatus());%>
            </div>
            <div class="input_button">
                <input type="submit" class="input_button_in" value="Update" >
            </div>
            <div class="input_button">
                <%--<input type="submit" class="input_button_in" value="Delite" >--%>

                <form action="/remove" method="post">
                    <input type="hidden" class="input_button_in" name="websitedata_id" value="<%out.print(list.getId());%>">
                    <button type="submit" class="input_button_in_n" >Delete</button>
                </form>
            </div>

        </div>
        <div class="info_slip">

            <%
                String sis = "No activ";
                if(list.getActivity().equals(sis)){
                        out.print("А>Здравствуйте, Аноним, Вы писали:\n" +
                                "\n" +
                                "А>>Интересно, существует ли в языке Java универсальный для всех платформ (операционных систем) способ перехода на новую строку?\n" +
                                "\n" +
                                "А>>В Windows этот символ равен '\\n' . А чему равен символ перехода на новую строку для операционной системы Unix");
                }
            %>
        </div>




        <%}%>
    </div>
</div>



</div>
</body>
</html>
