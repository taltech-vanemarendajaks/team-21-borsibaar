# 🍻 Börsibaar (Team 21)

![Java](https://img.shields.io/badge/Java-21-orange) ![Spring Boot](https://img.shields.io/badge/Spring_Boot-3.x-green) ![Next.js](https://img.shields.io/badge/Next.js-15-black) ![PostgreSQL](https://img.shields.io/badge/PostgreSQL-16-blue)

## 📖 Project Overview

Börsibaar is a full-stack web application that gamifies bar pricing using stock market principles. Drink prices fluctuate in real-time based on demand—popular drinks get more expensive, while less popular ones drop in price to encourage sales.

**Key Features:**

* **Dynamic Pricing Engine:** Algorithms that adjust prices based on inventory velocity.
* **Public Ticker:** A "Wall Street" style display for patrons to track drink prices.
* **POS System:** Tablet-optimized interface for bartenders.
* **Inventory Management:** robust back-office for stock and organization management.

## 🏗 Architecture

* **Backend:** Spring Boot 3.x (Java 21), PostgreSQL, Spring Security (OAuth2/JWT).
* **Frontend:** Next.js 15 (App Router), TypeScript, Tailwind CSS, Shadcn UI.
* **DevOps:** Docker implementation for local development and database management.

## 🛠 Prerequisites

Ensure you have the following installed:

* **Java JDK 21**
* **Node.js 20+**
* **Docker & Docker Compose**

## 🚀 Quick Start

### 1. Environment Setup

1. Copy `.sample.env` to `.env` in the root directory.
2. Fill in the Google OAuth credentials (required for login).

### 2. Start Infrastructure (Database)

```bash
docker compose up -d
```

### 3. Start Backend

```bash
cd backend
./mvnw spring-boot:run
```

*API docs available at: `http://localhost:8080/swagger-ui.html`*

### 4. Start Frontend

```bash
cd frontend
npm install
npm run dev
```

*App available at: `http://localhost:3000`*

## ⚙️ Configuration Details

<details>
<summary><strong>Click to view Sample .env configuration</strong></summary>

```env
POSTGRES_DB=borsibaar
POSTGRES_USER=postgres
POSTGRES_PASSWORD=postgres

# JDBC URL
SPRING_DATASOURCE_URL=jdbc:postgresql://localhost:5432/borsibaar
SPRING_DATASOURCE_USERNAME=postgres
SPRING_DATASOURCE_PASSWORD=postgres

# OAuth2 (Required for Auth)
GOOGLE_CLIENT_ID=your_google_client_id
GOOGLE_CLIENT_SECRET=your_google_client_secret

# Security
JWT_SECRET="use_openssl_rand_base64_32_here"
```

</details>

<details>
<summary><strong>Click to view application.properties</strong></summary>

```properties
spring.application.name=Borsibaar
# ... (rest of your properties)
```

</details>

## 📂 Project Structure

### Backend (`/backend`)

Follows a layered architecture:

* `controller/` - REST API endpoints.
* `service/` - Business logic and dynamic pricing calculations.
* `repository/` - Data access (Spring Data JPA).
* `entity/` - DB definitions.
* `mapper/` - MapStruct interfaces.

### Frontend (`/frontend`)

Next.js App Router structure:

* `app/(protected)/` - Authenticated routes (Dashboard, POS).
* `app/client/` - Public facing price-ticker.
* `components/ui/` - Shadcn/Radix UI primitives.

## 🚧 Roadmap & Known Issues (Tech Debt)

> *Note: For a detailed breakdown of refactoring tasks, please refer to the issue tracker.*

<details>
<summary><strong>View Detailed Tech Debt Analysis</strong></summary>

### Backend

* **Inventory & Pricing:** `InventoryService` manually fetches related entities causing N+1 issues. Dynamic pricing logic needs encapsulation in a dedicated domain service.

* **Validation:** Stronger DTO validation required for negative prices and quantity changes.
* **Testing:** Increase coverage for negative flows (concurrency, invalid inputs).

### Frontend

* **State Management:** The Inventory page (`page.tsx`) is monolithic; needs splitting into smaller components.

* **Type Safety:** Shared DTO types between Backend and Frontend are missing.
* **UX:** Public view needs better responsiveness for smaller screens.

</details>

## 🤝 Contributing

1. Fork the repository.
2. Create a feature branch (`git checkout -b feature/amazing-feature`).
3. Commit changes (`git commit -m 'Add amazing feature'`).
4. Push to branch (`git push origin feature/amazing-feature`).
5. Open a Pull Request.
