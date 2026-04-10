# DSi-Phi3

**Running a local AI (Phi-3) on a Nintendo DSi via Spring Boot & Thymeleaf**

---

## Overview

This project demonstrates how to turn a **Nintendo DSi XL** into a functional AI client by connecting it to a **self-hosted Phi-3 model (via Ollama)** running on a modern machine.

Due to the DSi’s extremely limited browser (Opera 9.5), this required **creative workarounds**:

* Custom network setup (WPA2 via phone hotspot)
* Backend proxy using Spring Boot
* Local AI inference with Ollama (Phi-3)
* Ultra-light frontend using Thymeleaf (no heavy JS)

---

## Architecture

```
Nintendo DSi Browser (Opera 9.5)
            ↓
     Spring Boot Backend
            ↓
     Ollama (Phi-3 model)
            ↓
     Response → DS Screen
```

---

## Tech Stack

* **Java 17**
* **Spring Boot (Web + Thymeleaf)**
* **Ollama (local LLM runtime)**
* **Phi-3 Mini model**
* **Minimal HTML/CSS (DS-compatible)**

---

## Network Setup (DSi workaround)

Modern routers often use security protocols incompatible with the DSi.

### Solution:

* Used a **mobile phone hotspot**
* Configured with **WPA2 (legacy-compatible)**
* Connected DSi successfully to local backend

---

## Key Features

### AI Chat via Phi-3

* User inputs a question from the DSi browser
* Spring controller forwards request to Ollama
* Extracts and returns only the AI response text

---

### Lightweight UI (Thymeleaf)

* No JavaScript required for core functionality
* Simple form-based interaction (`GET /ask`)
* Optimized for low-memory browser

---

### Chat History (Session-based)

* Stored in `HttpSession`
* Accessible via a **burger menu UI**
* DS-friendly minimal rendering

---

### DS Optimization

* Responses trimmed to ~200 characters
* Minimal DOM usage
* No modern APIs (no fetch, no frameworks)

---

## Challenges Solved

* DSi does **not support modern HTTPS**
* No support for `fetch`, WebRTC, or modern JS
* Limited CSS + rendering performance

### Solutions:

* Backend handles ALL API communication
* Thymeleaf renders static HTML
* Network adapted for legacy compatibility

---

## Purpose of the project

This is an experiment in:

* Extending the life of legacy hardware
* Running modern AI on constrained devices
* Bridging old and new tech stacks

---

## Result

A **fully functional AI assistant running on a Nintendo DSi**, powered by a local LLM.

---

## Future Improvements

* Chat streaming (typewriter effect)
* More DS-friendly UI (terminal style)
* Multiple model support
* Offline fallback responses
* NintendoDS support - Homebrew cartidge

---

## Getting Started

1. Run Ollama:

```bash
ollama run phi3
```

2. Start Spring Boot app:

```bash
./gradlew bootRun
```

3. Connect DSi to your network

4. Open:

```
http://<your-local-ip>:8080
You can also use ngrok as I did to port forward temporairly.
```

---

## Final Thoughts

This project shows that even a **2009 handheld console** can interact with **modern AI models**, with the right architecture and optimizations.

---

### ⭐ If you found this interesting, feel free to star the repo or share!
