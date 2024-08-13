<template>
  <div id="app">
    <h1>Chatbot</h1>
    <div class="chat-window">
      <div v-for="(message, index) in messages" :key="index">
        <p :class="message.sender">{{ message.text }}</p>
      </div>
    </div>
    <input
      v-model="userMessage"
      @keyup.enter="sendMessage"
      placeholder="Type your message here..."
    />
  </div>
</template>

<script>
// import axios from "axios";
// import SockJS from 'sockjs-client';

export default {
  data() {
    return {
      userMessage: "",
      messages: [],
      socket: null,
    };
  },
  methods: {
    sendMessage() {
      if (this.userMessage.trim()) {
        this.messages.push({ text: this.userMessage, sender: "user" });
        if (this.socket) {
          this.socket.send(this.userMessage);
          this.userMessage = "";
        }
      }
    },
    setupWebSocket() {
      this.socket = new WebSocket("http://localhost:8080/ws/messages");
      console.log("abcd");
      this.socket.onmessage = (event) => {
        console.log("WebSocket message received:", event.data);
        const message = JSON.parse(event.data);
        this.messages.push({ text: message, sender: "bot" });
      };
      this.socket.onopen = () => {
        console.log("WebSocket connection established");
      };
      this.socket.onclose = () => {
        console.log("WebSocket connection closed");
      };
      this.socket.onerror = (error) => {
        console.error("WebSocket error:", error);
      };
    },
  },
  //   async getBotResponse() {
  //     try {
  //       const response = await axios.get("http://localhost:8080/api/v1/messages/bot");
  //       console.log(response);
  //       if (response.data) {
  //         this.messages.push({ text: response.data.content, sender: "bot" });
  //       }
  //     } catch (error) {
  //       console.error(error);
  //     }
  //   },
  //   async getMessages() {
  //     try {
  //       const response = await axios.get("http://localhost:8080/api/v1/messages");
  //       this.messages = response.data;
  //     } catch (error) {
  //       console.error(error);
  //     }
  //   },
  // },
  created() {
    this.setupWebSocket();
  },
};
</script>

<style>
/* Thêm style đơn giản */
#app {
  text-align: center;
}
.chat-window {
  border: 1px solid #ccc;
  height: 300px;
  overflow-y: scroll;
}
.user {
  text-align: right;
}
.bot {
  text-align: left;
}
</style>
